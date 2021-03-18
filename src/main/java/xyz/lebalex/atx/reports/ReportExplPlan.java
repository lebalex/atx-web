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
public class ReportExplPlan extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(ReportExplPlan.class);
    private List<ReportExplPlanModel> listReportExplModel = new ArrayList();
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

    public List<ReportExplPlanModel> getListReportExplModel() {
        return listReportExplModel;
    }

    public double getTotalSum(String name, int i) {
        switch(i) {
            case 1: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getCountTC1)
                    .sum();
            case 2: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getCountTC2)
                    .sum();
            case 3: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getCountTC3)
                    .sum();
            case 4: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getCountTC4)
                    .sum();
            case 5: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getCountTC5)
                    .sum();
            case 6: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_year1)
                    .sum();
            case 7: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_year2)
                    .sum();
            case 8: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_year3)
                    .sum();
            case 9: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_year4)
                    .sum();
            case 10: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_year5)
                    .sum();
            case 11: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_kv1)
                    .sum();
            case 12: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_kv2)
                    .sum();
            case 13: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_kv3)
                    .sum();
            case 14: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_kv4)
                    .sum();
            case 15: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_kv5)
                    .sum();

            default:return 0;
        }

    }

    public double getAllSum(int i) {
        switch(i) {
            case 1: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getCountTC1)
                    .sum();
            case 2: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getCountTC2)
                    .sum();
            case 3: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getCountTC3)
                    .sum();
            case 4: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getCountTC4)
                    .sum();
            case 5: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getCountTC5)
                    .sum();
            case 6: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_year1)
                    .sum();
            case 7: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_year2)
                    .sum();
            case 8: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_year3)
                    .sum();
            case 9: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_year4)
                    .sum();
            case 10: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_year5)
                    .sum();
            case 11: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_kv1)
                    .sum();
            case 12: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_kv2)
                    .sum();
            case 13: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_kv3)
                    .sum();
            case 14: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_kv4)
                    .sum();
            case 15: return listReportExplModel.stream()
                    .mapToDouble(ReportExplPlanModel::getNorm_probeg_kv5)
                    .sum();
            default:return 0;
        }

    }

    public void report(int id_region, int id_division, int id_depart, int id_preDepart, int id_serv, int id_PreServ, int id_dislocation, int id_mark, int id_source_bay,
                       int id_source_fin, int id_group,
                       String modelAvt, String numb_1, String numb_2, String m_year1, String m_year2, String vin, String n_engine, String n_body, String n_chassis, String n_pts,
                       String inAc1, String inAc2, String drD1, String drD2, int state_p, int uotAc_p, int life_p,
                       int notATX_p, int what) {
        this.what = what;
        //this.caption="Эксплуатационная карта транспортного средства с " + (drD1.equals("-1") ? " " : drD1) + " по " + (drD2.equals("-1") ? " " : drD2);
        this.caption="Годовой план эксплуатации";

        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetReportPlanExpl(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
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



                    boolean hadResults = cStmt.execute();
                    if (hadResults) {

                        try (ResultSet rs = cStmt.getResultSet()) {
                            listReportExplModel.clear();
                            while (rs.next()) {
                                listReportExplModel.add(new ReportExplPlanModel(rs.getString(1), rs.getString(2),rs.getString(3),
                                        rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8),
                                        rs.getFloat(9),rs.getFloat(10),
                                        rs.getFloat(11), rs.getFloat(12), rs.getFloat(13), rs.getFloat(14), rs.getFloat(15),
                                        rs.getFloat(16), rs.getFloat(17), rs.getFloat(18)));
                            }
                            managerPages.setPage("reports/reportExplPlan.xhtml");
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
        CellRangeAddress range = new CellRangeAddress(0, 0, 0, 19);
        sheet.addMergedRegion(range);

        int rn = 1;
        String grf = "";
        int nnn=1;
        //row.setHeight((short) 1000);



        for (int j = 0; j < listReportExplModel.size(); j++) {


            if (!grf.equalsIgnoreCase(listReportExplModel.get(j).getGroupField())) {
                if(!grf.equals(""))
                {
                    row = sheet.createRow(rn);rn++;
                    cell = row.createCell(1);
                    cell.setCellValue("Итого");
                    cell.setCellStyle(styleHeaderLeft);

                    for(int k=1;k<16;k++) {
                        cell.setCellValue(getTotalSum(grf, k));
                        if(k<6)cell.setCellStyle(styleBodyNum);
                        else
                            cell.setCellStyle(styleBodyNumFloat);
                    }


                    rn++;
                }
                row = sheet.createRow(rn);
                rn++;

                cell = row.createCell(0);
                cell.setCellValue(listReportExplModel.get(j).getRegion()+" "+listReportExplModel.get(j).getDivision());
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, 15);
                sheet.addMergedRegion(range);


                row = sheet.createRow(rn);rn++;
                cell = row.createCell(0);
                cell.setCellValue("Отдел");
                cell.setCellStyle(styleHeader);



                cell = row.createCell(1);
                cell.setCellValue("Кол-во транспорта, ед");
                cell.setCellStyle(styleHeader);setMergedAndBorder(rn-1, rn-1, 1, 5,sheet,true);
                cell = row.createCell(6);
                cell.setCellValue("Годовой пробег, тыч.км");
                cell.setCellStyle(styleHeader);setMergedAndBorder(rn-1, rn-1, 6, 10,sheet,true);
                cell = row.createCell(11);
                cell.setCellValue("Квартальный пробег, тыч.км");
                cell.setCellStyle(styleHeader);setMergedAndBorder(rn-1, rn-1, 11, 15,sheet,true);

                row = sheet.createRow(rn);rn++;
                for(int k=1;k<16;k++) {
                    cell = row.createCell(k);
                    cell.setCellValue(Integer.toString(k));
                    cell.setCellStyle(styleHeader);
                }



                row = sheet.createRow(rn);rn++;
                cell = row.createCell(1);
                cell.setCellValue("легк");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(2);
                cell.setCellValue("груз");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(3);
                cell.setCellValue("автоб");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(4);
                cell.setCellValue("мотоц");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(5);
                cell.setCellValue("снегох");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(6);
                cell.setCellValue("легк");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(7);
                cell.setCellValue("груз");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(8);
                cell.setCellValue("автоб");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(9);
                cell.setCellValue("мотоц");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(10);
                cell.setCellValue("снегох");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(11);
                cell.setCellValue("легк");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(12);
                cell.setCellValue("груз");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(13);
                cell.setCellValue("автоб");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(14);
                cell.setCellValue("мотоц");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(15);
                cell.setCellValue("снегох");
                cell.setCellStyle(styleHeader);



                setMergedAndBorder(rn-3, rn-1, 0, 0,sheet,true);

                grf = listReportExplModel.get(j).getGroupField();
                nnn=1;


            }
            row = sheet.createRow(rn);
            rn++;


            cell = row.createCell(0);
            String value = listReportExplModel.get(j).getDepart();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(1);
            cell.setCellValue(listReportExplModel.get(j).getCountTC1());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(2);
            cell.setCellValue(listReportExplModel.get(j).getCountTC2());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(3);
            cell.setCellValue(listReportExplModel.get(j).getCountTC3());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(4);
            cell.setCellValue(listReportExplModel.get(j).getCountTC4());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(5);
            cell.setCellValue(listReportExplModel.get(j).getCountTC5());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(6);
            cell.setCellValue(listReportExplModel.get(j).getNorm_probeg_year1());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(7);
            cell.setCellValue(listReportExplModel.get(j).getNorm_probeg_year2());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(8);
            cell.setCellValue(listReportExplModel.get(j).getNorm_probeg_year3());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(9);
            cell.setCellValue(listReportExplModel.get(j).getNorm_probeg_year4());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(10);
            cell.setCellValue(listReportExplModel.get(j).getNorm_probeg_year5());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(11);
            cell.setCellValue(listReportExplModel.get(j).getNorm_probeg_kv1());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(12);
            cell.setCellValue(listReportExplModel.get(j).getNorm_probeg_kv2());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(13);
            cell.setCellValue(listReportExplModel.get(j).getNorm_probeg_kv3());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(14);
            cell.setCellValue(listReportExplModel.get(j).getNorm_probeg_kv4());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(15);
            cell.setCellValue(listReportExplModel.get(j).getNorm_probeg_kv5());
            cell.setCellStyle(styleBodyNumFloat);





        }
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(1);
        cell.setCellValue("Итого");
        cell.setCellStyle(styleHeaderLeft);

        for(int k=1;k<16;k++) {
            cell.setCellValue(getTotalSum(grf, k));
            if(k<6)cell.setCellStyle(styleBodyNum);
            else
                cell.setCellStyle(styleBodyNumFloat);
        }




        for (int i = 1; i <= 15; i++)
        {
            sheet.setColumnWidth(i, 10 * 256);
        }
        sheet.autoSizeColumn(0);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/vnd.ms-excel");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"explplan.xls\"");

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
