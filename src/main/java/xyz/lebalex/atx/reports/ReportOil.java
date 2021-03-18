package xyz.lebalex.atx.reports;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import xyz.lebalex.atx.AtxBase;
import xyz.lebalex.atx.controls.ManagerPages;
import xyz.lebalex.atx.modelReport.*;
import xyz.lebalex.atx.modelReport.ReportExplPlanModel;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Named
@ViewScoped
public class ReportOil extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(ReportOil.class);
    private List<ReportOilModel> listReportModel = new ArrayList();
    private int what;
    private String caption;
    private int idx=0;
    private String groupField="";

    @Inject
    ManagerPages managerPages;

    public String getCaption() {
        return caption;
    }

    public int getIdx(String groupField)
    {
        if(!this.groupField.equals(groupField))
        {
            idx=0;
            this.groupField = groupField;
        }
        idx++;
        return idx;
    }

    public List<ReportOilModel> getListReportModel() {
        return listReportModel;
    }

    public long getTotalCount(String name) {
        return listReportModel.stream().filter(listReportModel -> name.equals(listReportModel.getGroupField())).count();
    }
    public long getTotalCountAll() {
        return listReportModel.stream().count();
    }

    public double getTotalSum(String name, int i) {
        switch(i) {
            case 1: return listReportModel.stream().filter(listReportModel -> name.equals(listReportModel.getGroupField()))
                    .mapToDouble(ReportOilModel::getOil_s)
                    .sum();
            case 2: return listReportModel.stream().filter(listReportModel -> name.equals(listReportModel.getGroupField()))
                    .mapToDouble(ReportOilModel::getOil_ps)
                    .sum();
            case 3: return listReportModel.stream().filter(listReportModel -> name.equals(listReportModel.getGroupField()))
                    .mapToDouble(ReportOilModel::getOil_m)
                    .sum();
            default:return 0;
        }

    }

    public double getAllSum(int i) {
        switch(i) {
            case 1: return listReportModel.stream()
                    .mapToDouble(ReportOilModel::getOil_s)
                    .sum();
            case 2: return listReportModel.stream()
                    .mapToDouble(ReportOilModel::getOil_ps)
                    .sum();
            case 3: return listReportModel.stream()
                    .mapToDouble(ReportOilModel::getOil_m)
                    .sum();
            default:return 0;
        }

    }

    public void report(int id_region, int id_division, int id_depart, int id_preDepart, int id_serv, int id_PreServ, int id_dislocation, int id_mark, int id_source_bay,
                       int id_source_fin, int id_group,
                       String modelAvt, String numb_1, String numb_2, String m_year1, String m_year2, String vin, String n_engine, String n_body, String n_chassis, String n_pts,
                       String inAc1, String inAc2, String drD1, String drD2, int state_p, int uotAc_p, int life_p,
                       int id_typetc, int id_remont, int notATX_p, int what) {
        this.what = what;
        this.caption="Отчет по маслу с " + (drD1.equals("-1") ? " " : drD1) + " по " + (drD2.equals("-1") ? " " : drD2);


        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetReportOil(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
                    cStmt.setInt(1, id_region);
                    cStmt.setInt(2, id_division);
                    cStmt.setInt(3, id_depart);
                    cStmt.setInt(4, id_preDepart);
                    cStmt.setInt(5, id_serv);
                    cStmt.setInt(6, id_mark);
                    cStmt.setString(7, modelAvt);
                    cStmt.setInt(8, id_source_bay);
                    cStmt.setInt(9, id_source_fin);
                    cStmt.setInt(10, id_group);
                    cStmt.setString(11, numb_1);
                    cStmt.setString(12, numb_2);
                    cStmt.setString(13, "-1");//oldnumb_p;
                    cStmt.setString(14, m_year1);
                    cStmt.setString(15, m_year2);
                    cStmt.setString(16, vin);
                    cStmt.setString(17, n_engine);
                    cStmt.setString(18, n_body);
                    cStmt.setString(19, n_chassis);
                    cStmt.setString(20, n_pts);
                    cStmt.setString(21, inAc1);//InAc1_p;
                    cStmt.setString(22, inAc2);//InAc2_p;
                    cStmt.setInt(23, state_p);//State_p;
                    cStmt.setInt(24, uotAc_p);//UotAc_p;
                    cStmt.setInt(25, life_p);//life_p;
                    cStmt.setString(26, drD1);//datt1;
                    cStmt.setString(27, drD2);//datt2;



                    cStmt.setInt(28, id_dislocation);


                    cStmt.setInt(29, id_PreServ);
                    cStmt.setInt(30, notATX_p);//notATX_p;
                    cStmt.setInt(31, id_remont);
                    cStmt.setInt(32, 71);





                    boolean hadResults = cStmt.execute();
                    if (hadResults) {

                        try (ResultSet rs = cStmt.getResultSet()) {
                            listReportModel.clear();
                            while (rs.next()) {
                                listReportModel.add(new ReportOilModel(rs.getString(1), rs.getString(2),rs.getString(3),
                                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getFloat(7),
                                        rs.getFloat(8),
                                        rs.getFloat(9)));
                            }
                            managerPages.setPage("reports/reportOil.xhtml");
                        }
                    } else logger.log(Level.ERROR, "false");
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }
    public void excelReport() throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle styleHeader = (HSSFCellStyle) wb.createCellStyle();
        HSSFFont fontHeader = (HSSFFont) wb.createFont();
        fontHeader.setBold(true);
        styleHeader.setFont(fontHeader);
        styleHeader.setAlignment(HorizontalAlignment.CENTER);
        styleHeader.setBorderBottom(BorderStyle.THIN);
        styleHeader.setBorderTop(BorderStyle.THIN);
        styleHeader.setBorderRight(BorderStyle.THIN);
        styleHeader.setBorderLeft(BorderStyle.THIN);
        styleHeader.setWrapText(true);
        HSSFCellStyle styleHeaderLeft = (HSSFCellStyle) wb.createCellStyle();
        styleHeaderLeft.setFont(fontHeader);
        styleHeaderLeft.setAlignment(HorizontalAlignment.LEFT);
        styleHeaderLeft.setBorderBottom(BorderStyle.THIN);
        styleHeaderLeft.setBorderTop(BorderStyle.THIN);
        styleHeaderLeft.setBorderRight(BorderStyle.THIN);
        styleHeaderLeft.setBorderLeft(BorderStyle.THIN);
        styleHeaderLeft.setWrapText(true);

        HSSFCellStyle styleBody = (HSSFCellStyle) wb.createCellStyle();
        styleBody.setVerticalAlignment(VerticalAlignment.TOP);
        styleBody.setBorderBottom(BorderStyle.THIN);
        styleBody.setBorderTop(BorderStyle.THIN);
        styleBody.setBorderRight(BorderStyle.THIN);
        styleBody.setBorderLeft(BorderStyle.THIN);


        HSSFCellStyle styleBodyTop = (HSSFCellStyle) wb.createCellStyle();
        styleBodyTop.setVerticalAlignment(VerticalAlignment.TOP);
        //styleBodyTop.setBorderBottom(BorderStyle.THIN);
        styleBodyTop.setBorderTop(BorderStyle.THIN);
        styleBodyTop.setBorderRight(BorderStyle.THIN);
        styleBodyTop.setBorderLeft(BorderStyle.THIN);
        HSSFCellStyle styleBodyLR = (HSSFCellStyle) wb.createCellStyle();
        styleBodyLR.setVerticalAlignment(VerticalAlignment.TOP);
        //styleBodyLR.setBorderBottom(BorderStyle.THIN);
        //styleBodyLR.setBorderTop(BorderStyle.THIN);
        styleBodyLR.setBorderRight(BorderStyle.THIN);
        styleBodyLR.setBorderLeft(BorderStyle.THIN);


        HSSFCellStyle styleHeader2 = (HSSFCellStyle) wb.createCellStyle();
        styleHeader2.setFont(fontHeader);
        styleHeader2.setAlignment(HorizontalAlignment.LEFT);
        styleHeader2.setVerticalAlignment(VerticalAlignment.TOP);

        HSSFCellStyle styleHeaderTitle = (HSSFCellStyle) wb.createCellStyle();
        styleHeaderTitle.setFont(fontHeader);
        styleHeaderTitle.setAlignment(HorizontalAlignment.CENTER);
        styleHeaderTitle.setVerticalAlignment(VerticalAlignment.TOP);

        HSSFCellStyle styleBodyNum = (HSSFCellStyle) wb.createCellStyle();
        styleBodyNum.setVerticalAlignment(VerticalAlignment.TOP);
        styleBodyNum.setBorderBottom(BorderStyle.THIN);
        styleBodyNum.setBorderTop(BorderStyle.THIN);
        styleBodyNum.setBorderRight(BorderStyle.THIN);
        styleBodyNum.setBorderLeft(BorderStyle.THIN);
        DataFormat format = wb.createDataFormat();
        styleBodyNum.setDataFormat(format.getFormat("#,##0"));


        HSSFCellStyle styleBodyNumFloat = (HSSFCellStyle) wb.createCellStyle();
        styleBodyNumFloat.setVerticalAlignment(VerticalAlignment.TOP);
        styleBodyNumFloat.setBorderBottom(BorderStyle.THIN);
        styleBodyNumFloat.setBorderTop(BorderStyle.THIN);
        styleBodyNumFloat.setBorderRight(BorderStyle.THIN);
        styleBodyNumFloat.setBorderLeft(BorderStyle.THIN);
        DataFormat formatFloat = wb.createDataFormat();
        styleBodyNumFloat.setDataFormat(formatFloat.getFormat("#,##0.00"));

        HSSFSheet sheet = wb.createSheet("sheet");

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(getCaption());
        cell.setCellStyle(styleHeaderTitle);
        CellRangeAddress range = new CellRangeAddress(0, 0, 0, 4);
        sheet.addMergedRegion(range);

        int rn = 1;
        String grf = "";
        String avto = "";
        int nnn=1;
        //row.setHeight((short) 1000);



        for (int j = 0; j < listReportModel.size(); j++) {


            if (!grf.equalsIgnoreCase(listReportModel.get(j).getGroupField())) {
                if(!grf.equals(""))
                {
                    row = sheet.createRow(rn);rn++;
                    cell = row.createCell(0);
                    cell.setCellValue("Итого");
                    cell.setCellStyle(styleHeaderLeft);

                    for(int k=1;k<4;k++) {
                        cell = row.createCell(k+1);
                        cell.setCellValue(getTotalSum(grf, k));
                        cell.setCellStyle(styleBodyNumFloat);
                    }




                    rn++;
                }
                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                cell.setCellValue(listReportModel.get(j).getRegion());
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, 8);
                sheet.addMergedRegion(range);

                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                cell.setCellValue(listReportModel.get(j).getDivision());
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, 8);
                sheet.addMergedRegion(range);
                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                cell.setCellValue(listReportModel.get(j).getDepart());
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, 8);
                sheet.addMergedRegion(range);
                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                String value = listReportModel.get(j).getService();
                cell.setCellValue((value != null) ? value.trim() : "");
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, 8);
                sheet.addMergedRegion(range);




                row = sheet.createRow(rn);rn++;
                cell = row.createCell(0);
                cell.setCellValue("Марка");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(1);
                cell.setCellValue("Гос №");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(2);
                cell.setCellValue("синтетика");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(3);
                cell.setCellValue("п/синтетика");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(4);
                cell.setCellValue("минеральное");
                cell.setCellStyle(styleHeader);


                grf = listReportModel.get(j).getGroupField();
                nnn=1;


            }
            row = sheet.createRow(rn);
            rn++;


                cell = row.createCell(0);
                String value = listReportModel.get(j).getMark();
                cell.setCellValue((value != null) ? value.trim() : "");
                cell.setCellStyle(styleBody);
                cell = row.createCell(1);
                cell.setCellValue(listReportModel.get(j).getNumb_1());
                cell.setCellStyle(styleBody);




            cell = row.createCell(2);
            cell.setCellValue(listReportModel.get(j).getOil_s());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(3);
            cell.setCellValue(listReportModel.get(j).getOil_ps());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(4);
            cell.setCellValue(listReportModel.get(j).getOil_m());
            cell.setCellStyle(styleBodyNumFloat);






        }
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue("Итого");
        cell.setCellStyle(styleHeaderLeft);

        for(int k=1;k<4;k++) {
            cell = row.createCell(k+1);
            cell.setCellValue(getTotalSum(grf, k));
            cell.setCellStyle(styleBodyNumFloat);
        }
        rn++;
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue("Итого");
        cell.setCellStyle(styleHeaderLeft);

        for(int k=1;k<4;k++) {
            cell = row.createCell(k+1);
            cell.setCellValue(getAllSum(k));
            cell.setCellStyle(styleBodyNumFloat);
        }




        for (int i = 0; i <= 4; i++)
        {
            sheet.autoSizeColumn(i);
        }
        /*sheet.setColumnWidth(3, 20 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 20 * 256);*/

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/vnd.ms-excel");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"reportoil.xls\"");

        wb.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();
    }
    private void setMergedAndBorder(int firstRow,int lastRow,int firstCol,int lastCol, HSSFSheet sheet, boolean border)
    {
        CellRangeAddress range = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        sheet.addMergedRegion(range);
        if(border) {
            RegionUtil.setBorderBottom(BorderStyle.THIN, range, sheet);
            RegionUtil.setBorderTop(BorderStyle.THIN, range, sheet);
            RegionUtil.setBorderLeft(BorderStyle.THIN, range, sheet);
            RegionUtil.setBorderRight(BorderStyle.THIN, range, sheet);
        }
    }
}
