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
import xyz.lebalex.atx.modelReport.ReportExpl_3Model;

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
public class ReportExpl_3 extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(ReportExpl_3.class);
    private List<ReportExpl_3Model> listReportExplModel = new ArrayList();
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

    public List<ReportExpl_3Model> getListReportExplModel() {
        return listReportExplModel;
    }

    public double getTotalSum(String name, int i) {
        switch(i) {
            case 1: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getD_out)
                    .sum();
            case 2: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getD_g)
                    .sum();
            case 3: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getD_oper)
                    .sum();
            case 4: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getD_rem)
                    .sum();
            case 5: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getH_or)
                    .sum();
            case 6: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getGsm_nor)
                    .sum();
            case 7: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getGsm_out)
                    .sum();
            case 8: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getGsm_b)
                    .sum();
            case 9: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getGsm_g)
                    .sum();
            case 10: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getGsm_end)
                    .sum();
            case 11: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getN_run)
                    .sum();
            case 12: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getF_run)
                    .sum();
            case 13: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getTo1)
                    .sum();
            case 14: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getTo2)
                    .sum();
            case 15: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getKp)
                    .sum();
            case 16: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getOil_s)
                    .sum();
            case 17: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getOil_ps)
                    .sum();
            case 18: return listReportExplModel.stream().filter(listReportExplModel -> name.equals(listReportExplModel.getGroupField()))
                    .mapToDouble(ReportExpl_3Model::getOil_m)
                    .sum();
            default:return 0;
        }

    }

    public double getAllSum(int i) {
        switch(i) {
            case 1: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getD_out)
                    .sum();
            case 2: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getD_g)
                    .sum();
            case 3: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getD_oper)
                    .sum();
            case 4: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getD_rem)
                    .sum();
            case 5: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getH_or)
                    .sum();
            case 6: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getGsm_nor)
                    .sum();
            case 7: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getGsm_out)
                    .sum();
            case 8: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getGsm_b)
                    .sum();
            case 9: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getGsm_g)
                    .sum();
            case 10: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getGsm_end)
                    .sum();
            case 11: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getN_run)
                    .sum();
            case 12: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getF_run)
                    .sum();
            case 13: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getTo1)
                    .sum();
            case 14: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getTo2)
                    .sum();
            case 15: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getKp)
                    .sum();
            case 16: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getOil_s)
                    .sum();
            case 17: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getOil_ps)
                    .sum();
            case 18: return listReportExplModel.stream()
                    .mapToDouble(ReportExpl_3Model::getOil_m)
                    .sum();
            default:return 0;
        }

    }

    public void report(int id_region, int id_division, int id_depart, int id_preDepart, int id_serv, int id_PreServ, int id_dislocation, int id_mark, int id_source_bay,
                       int id_source_fin, int id_group,
                       String modelAvt, String numb_1, String numb_2, String m_year1, String m_year2, String vin, String n_engine, String n_body, String n_chassis, String n_pts,
                       String inAc1, String inAc2, String drD1, String drD2, int state_p, int uotAc_p, int life_p,
                       int notATX_p, int id_markGsm, boolean on_includeOutSpisan, int what) {
        this.what = what;
        this.caption="Эксплуатационная карта транспортного средства с " + (drD1.equals("-1") ? " " : drD1) + " по " + (drD2.equals("-1") ? " " : drD2);

        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetReportExpl_pokaz2(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
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
                    cStmt.setInt(31, id_markGsm);
                    cStmt.setBoolean(32, on_includeOutSpisan);



                    boolean hadResults = cStmt.execute();
                    if (hadResults) {

                        try (ResultSet rs = cStmt.getResultSet()) {
                            listReportExplModel.clear();
                            while (rs.next()) {
                                listReportExplModel.add(new ReportExpl_3Model(rs.getString(1), rs.getString(2),
                                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                                        rs.getString(9),rs.getString(10),
                                        rs.getFloat(11), rs.getFloat(12), rs.getFloat(13), rs.getFloat(14), rs.getFloat(15),
                                        rs.getFloat(16), rs.getFloat(17), rs.getFloat(18), rs.getFloat(19), rs.getFloat(20), rs.getFloat(21), rs.getFloat(22),
                                        rs.getFloat(23), rs.getFloat(24), rs.getFloat(25), rs.getFloat(26), rs.getFloat(27)
                                        , rs.getFloat(28), rs.getFloat(29), rs.getFloat(30)));
                            }
                            managerPages.setPage("reports/reportExpl_3.xhtml");
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
                    cell.setCellValue("Итого");setMergedAndBorder(rn-1, rn-1, 1, 3,sheet,true);
                    cell.setCellStyle(styleHeaderLeft);

                    for(int k=1;k<19;k++) {
                        if(k<6)
                            cell = row.createCell(k+6);else cell = row.createCell(k+7);
                        cell.setCellValue(getTotalSum(grf, k));
                        if(k<11)cell.setCellStyle(styleBodyNum);
                        else
                            cell.setCellStyle(styleBodyNumFloat);
                    }
                    cell.setCellStyle(styleBodyNum);

                    rn++;
                }
                row = sheet.createRow(rn);
                rn++;

                cell = row.createCell(0);
                cell.setCellValue(listReportExplModel.get(j).getName()+" "+listReportExplModel.get(j).getExpr2()+" "+listReportExplModel.get(j).getExpr3());
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, 19);
                sheet.addMergedRegion(range);

                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                cell.setCellValue(listReportExplModel.get(j).getExpr6());
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, 19);
                sheet.addMergedRegion(range);

                row = sheet.createRow(rn);rn++;
                cell = row.createCell(0);
                cell.setCellValue("№");
                cell.setCellStyle(styleHeader);


                cell = row.createCell(1);
                cell.setCellValue("Инв №");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(2);
                cell.setCellValue("Служба");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(3);
                cell.setCellValue("Марка автомобиля");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(4);
                cell.setCellValue("Гос№");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(5);
                cell.setCellValue("№ и дата приказа ввода в эксп.");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(6);
                cell.setCellValue("Пок.спид.");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(7);
                cell.setCellValue("Машино-дни");
                cell.setCellStyle(styleHeader);setMergedAndBorder(rn-1, rn-1, 7, 10,sheet,true);
                cell = row.createCell(11);
                cell.setCellValue("Часы в наряде");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(12);
                cell.setCellValue("Расход топлива в литрах");
                cell.setCellStyle(styleHeader);setMergedAndBorder(rn-1, rn-1, 12, 17,sheet,true);
                cell = row.createCell(18);
                cell.setCellValue("Пробег");
                cell.setCellStyle(styleHeader);setMergedAndBorder(rn-1, rn-1, 18, 19,sheet,true);
                cell = row.createCell(20);
                cell.setCellValue("Тех обсл-е");
                cell.setCellStyle(styleHeader);setMergedAndBorder(rn-1, rn-1, 20, 22,sheet,true);
                cell = row.createCell(23);
                cell.setCellValue("Расход масла");
                cell.setCellStyle(styleHeader);setMergedAndBorder(rn-1, rn-1, 23, 25,sheet,true);


                row = sheet.createRow(rn);rn++;
                cell = row.createCell(7);
                cell.setCellValue("спис");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(8);
                cell.setCellValue("испр");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(9);
                cell.setCellValue("экспл");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(10);
                cell.setCellValue("рем");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(12);
                cell.setCellValue("Расход на 100 км");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(13);
                cell.setCellValue("Расход по норме");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(14);
                cell.setCellValue("Расход факт");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(15);
                cell.setCellValue("Остаток на нач.");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(16);
                cell.setCellValue("Выдано");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(17);
                cell.setCellValue("Остаток на кон.");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(18);
                cell.setCellValue("Норма");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(19);
                cell.setCellValue("Факт");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(20);
                cell.setCellValue("Выполн ТО1");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(21);
                cell.setCellValue("Выполн ТО2");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(22);
                cell.setCellValue("Выполн КР");
                cell.setCellStyle(styleHeader);

                cell = row.createCell(23);
                cell.setCellValue("синт");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(24);
                cell.setCellValue("п/синт");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(25);
                cell.setCellValue("мин");
                cell.setCellStyle(styleHeader);



                setMergedAndBorder(rn-2, rn-1, 0, 0,sheet,true);
                setMergedAndBorder(rn-2, rn-1, 1, 1,sheet,true);
                setMergedAndBorder(rn-2, rn-1, 2, 2,sheet,true);
                setMergedAndBorder(rn-2, rn-1, 3, 3,sheet,true);
                setMergedAndBorder(rn-2, rn-1, 4, 4,sheet,true);
                setMergedAndBorder(rn-2, rn-1, 5, 5,sheet,true);
                setMergedAndBorder(rn-2, rn-1, 6, 6,sheet,true);
                setMergedAndBorder(rn-2, rn-1, 11, 11,sheet,true);
                grf = listReportExplModel.get(j).getGroupField();
                nnn=1;


            }
            row = sheet.createRow(rn);
            rn++;

            cell = row.createCell(0);
            cell.setCellValue(nnn);nnn++;
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(1);
            String value = listReportExplModel.get(j).getInv_t();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(2);
            value = listReportExplModel.get(j).getExpr5();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(3);
            cell.setCellValue(listReportExplModel.get(j).getMark());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(4);
            cell.setCellValue(listReportExplModel.get(j).getNumb_1());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(5);
            cell.setCellValue(listReportExplModel.get(j).getIn_pr());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(6);
            cell.setCellValue(listReportExplModel.get(j).getSpeed_end());
            cell.setCellStyle(styleBodyNum);


            cell = row.createCell(7);
            cell.setCellValue(listReportExplModel.get(j).getD_out());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(8);
            cell.setCellValue(listReportExplModel.get(j).getD_g());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(9);
            cell.setCellValue(listReportExplModel.get(j).getD_oper());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(10);
            cell.setCellValue(listReportExplModel.get(j).getD_rem());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(11);
            cell.setCellValue(listReportExplModel.get(j).getH_or());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(12);
            cell.setCellValue(listReportExplModel.get(j).getNorm());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(13);
            cell.setCellValue(listReportExplModel.get(j).getGsm_nor());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(14);
            cell.setCellValue(listReportExplModel.get(j).getGsm_out());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(15);
            cell.setCellValue(listReportExplModel.get(j).getGsm_b());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(16);
            cell.setCellValue(listReportExplModel.get(j).getGsm_g());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(17);
            cell.setCellValue(listReportExplModel.get(j).getGsm_end());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(18);
            cell.setCellValue(listReportExplModel.get(j).getN_run());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(19);
            cell.setCellValue(listReportExplModel.get(j).getF_run());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(20);
            cell.setCellValue(listReportExplModel.get(j).getTo1());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(21);
            cell.setCellValue(listReportExplModel.get(j).getTo2());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(22);
            cell.setCellValue(listReportExplModel.get(j).getKp());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(23);
            cell.setCellValue(listReportExplModel.get(j).getOil_s());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(24);
            cell.setCellValue(listReportExplModel.get(j).getOil_ps());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(25);
            cell.setCellValue(listReportExplModel.get(j).getOil_m());
            cell.setCellStyle(styleBodyNumFloat);




        }
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(1);
        cell.setCellValue("Итого");setMergedAndBorder(rn-1, rn-1, 1, 3,sheet,true);
        cell.setCellStyle(styleHeaderLeft);

        for(int k=1;k<19;k++) {
            if(k<6)
                cell = row.createCell(k+6);else cell = row.createCell(k+7);
            cell.setCellValue(getTotalSum(grf, k));
            if(k<11)cell.setCellStyle(styleBodyNum);
            else
                cell.setCellStyle(styleBodyNumFloat);
        }
        cell.setCellStyle(styleBodyNum);

        rn++;
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(1);
        cell.setCellValue("Итого");setMergedAndBorder(rn-1, rn-1, 1, 3,sheet,true);
        cell.setCellStyle(styleHeaderLeft);

        for(int k=1;k<19;k++) {
            if(k<6)
            cell = row.createCell(k+6);else cell = row.createCell(k+7);
            cell.setCellValue(getAllSum(k));
            if(k<11)cell.setCellStyle(styleBodyNum);
            else
                cell.setCellStyle(styleBodyNumFloat);
        }
        cell.setCellStyle(styleBodyNum);
        for (int i = 1; i <= 19; i++)
        {
            sheet.setColumnWidth(i, 10 * 256);
        }
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(5);



        //sheet.setDefaultColumnWidth(15);



        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/vnd.ms-excel");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"explcard02.xls\"");

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
