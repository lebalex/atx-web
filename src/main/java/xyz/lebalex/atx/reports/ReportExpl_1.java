package xyz.lebalex.atx.reports;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import xyz.lebalex.atx.AtxBase;
import xyz.lebalex.atx.controls.ManagerPages;
import xyz.lebalex.atx.modelReport.InventarModel;
import xyz.lebalex.atx.modelReport.LinNormGSMModel;
import xyz.lebalex.atx.modelReport.ReportExpl_1Model;

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
public class ReportExpl_1 extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(ReportExpl_1.class);
    private List<ReportExpl_1Model> listReportExpl_1Model = new ArrayList();
    private int what;
    private String caption;

    @Inject
    ManagerPages managerPages;

    public String getCaption() {
        return caption;
    }

    public List<ReportExpl_1Model> getListReportExpl_1Model() {
        return listReportExpl_1Model;
    }

    public double getTotalSum(String name, int i) {
        switch(i) {
            case 1: return listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getNorma_run)
                    .sum();
            case 2: return listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getFact_run)
                    .sum();
            case 3: return listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getD_out)
                    .sum();
            case 4: return listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getD_good)
                    .sum();
            case 5: return listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getD_operational)
                    .sum();
            case 6: return listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getGsm_begin_m)
                    .sum();
            case 7: return listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getGsm_given)
                    .sum();
            case 8: return listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getGsm_end_m)
                    .sum();
            case 9: return listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getGsm_out)
                    .sum();
            case 10: return listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getGsm_norma)
                    .sum();
            case 11: double sp = listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getPere)
                    .sum(); if(sp<0) return -1*sp; else return 0;
            case 12: double spp = listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getPere)
                    .sum(); if(spp>0) return spp; else return 0;
            case 13: return listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getTo1)
                    .sum();
            case 14: return listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getTo2)
                    .sum();
            case 15: return listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getKp)
                    .sum();
            case 16: double d_g = listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getD_good)
                    .sum();
                    double d_o = listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                        .mapToDouble(ReportExpl_1Model::getD_out)
                        .sum();
                    if(d_g>d_o) return 1; else return d_g/d_o;
            case 17: double d_op = listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getD_operational)
                    .sum();
                    double d_o1 = listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                        .mapToDouble(ReportExpl_1Model::getD_out)
                        .sum();
                    return d_op/d_o1;
            case 18: double nr = listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getNorma_run)
                    .sum();
                    double fr = listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                        .mapToDouble(ReportExpl_1Model::getFact_run)
                        .sum();
                    return nr-fr;
            case 19: return listReportExpl_1Model.stream().filter(listReportExpl_1Model -> name.equals(listReportExpl_1Model.getKvString()))
                    .mapToDouble(ReportExpl_1Model::getSpeed_end)
                    .max().orElseThrow(NoSuchElementException::new);


            default:return 0;
        }

    }

    public double getAllSum(int i) {
        switch(i) {
            case 1: return listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getNorma_run)
                    .sum();
            case 2: return listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getFact_run)
                    .sum();
            case 3: return listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getD_out)
                    .sum();
            case 4: return listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getD_good)
                    .sum();
            case 5: return listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getD_operational)
                    .sum();
            case 6: return listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getGsm_begin_m)
                    .sum();
            case 7: return listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getGsm_given)
                    .sum();
            case 8: return listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getGsm_end_m)
                    .sum();
            case 9: return listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getGsm_out)
                    .sum();
            case 10: return listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getGsm_norma)
                    .sum();
            case 11: double sp = listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getPere)
                    .sum();
            if(sp<0) return -1*sp; else return 0;
            case 12: double spp = listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getPere)
                    .sum();
            if(spp>0) return spp; else return 0;
            case 13: return listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getTo1)
                    .sum();
            case 14: return listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getTo2)
                    .sum();
            case 15: return listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getKp)
                    .sum();
            case 16: double d_g = listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getD_good)
                    .sum();
                double d_o = listReportExpl_1Model.stream()
                        .mapToDouble(ReportExpl_1Model::getD_out)
                        .sum();
                if(d_g>d_o) return 1; else return d_g/d_o;
            case 17: double d_op = listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getD_operational)
                    .sum();
                double d_o1 = listReportExpl_1Model.stream()
                        .mapToDouble(ReportExpl_1Model::getD_out)
                        .sum();
                return d_op/d_o1;
            case 18: double nr = listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getNorma_run)
                    .sum();
                double fr = listReportExpl_1Model.stream()
                        .mapToDouble(ReportExpl_1Model::getFact_run)
                        .sum();
                return nr-fr;
            case 19: return listReportExpl_1Model.stream()
                    .mapToDouble(ReportExpl_1Model::getSpeed_end)
                    .max().orElseThrow(NoSuchElementException::new);


            default:return 0;
        }

    }
    public String getMark()
    {
        if(listReportExpl_1Model.size()>0) return listReportExpl_1Model.get(0).getExpr1(); else return "";
    }
    public String getNumb_1()
    {
        if(listReportExpl_1Model.size()>0) return listReportExpl_1Model.get(0).getNumb_1(); else return "";
    }
    public String getSourceFin()
    {
        if(listReportExpl_1Model.size()>0) return listReportExpl_1Model.get(0).getExpr6(); else return "";    }
    public String getZakrep()
    {
        if(listReportExpl_1Model.size()>0)
            return listReportExpl_1Model.get(0).getName()+" "+listReportExpl_1Model.get(0).getExpr2()+" "+((listReportExpl_1Model.get(0).getExpr3()!=null)?listReportExpl_1Model.get(0).getExpr3():"");
        else return "";
    }

    public void report(int id_region, int id_division, int id_depart, int id_preDepart, int id_serv, int id_PreServ, int id_dislocation, int id_mark, int id_source_bay,
                         int id_source_fin, int id_group,
                         String modelAvt, String numb_1, String numb_2, String m_year1, String m_year2, String vin, String n_engine, String n_body, String n_chassis, String n_pts,
                         String inAc1, String inAc2, String drD1, String drD2, int state_p, int uotAc_p, int life_p,
                         int notATX_p, int what) {
        this.what = what;
        this.caption="Эксплуатационная карта транспортного средства с " + (drD1.equals("-1") ? " " : drD1) + " по " + (drD2.equals("-1") ? " " : drD2);

        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetReportExpl_Avto_1(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
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
                            listReportExpl_1Model.clear();
                            while (rs.next()) {
                                listReportExpl_1Model.add(new ReportExpl_1Model(rs.getString(1), rs.getString(2),
                                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                                        rs.getFloat(10), rs.getFloat(11), rs.getFloat(12), rs.getFloat(13), rs.getFloat(14), rs.getFloat(15),
                                        rs.getFloat(16), rs.getFloat(17), rs.getFloat(18), rs.getFloat(19), rs.getFloat(20), rs.getFloat(21), rs.getFloat(22),
                                        rs.getFloat(23), rs.getInt(24), rs.getFloat(25)));
                            }
                            managerPages.setPage("reports/reportExpl_1.xhtml");
                            //FacesContext.getCurrentInstance().getExternalContext().redirect("listavto2.jsf");
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

        rn++;
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue(getMark());
        cell.setCellStyle(styleHeader2);
        range = new CellRangeAddress(rn-1, rn-1, 0, 1);
        sheet.addMergedRegion(range);

        cell = row.createCell(2);
        cell.setCellValue(getNumb_1());
        cell.setCellStyle(styleHeader2);
        range = new CellRangeAddress(rn-1, rn-1, 2, 19);
        sheet.addMergedRegion(range);

        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue(getSourceFin());
        cell.setCellStyle(styleHeader2);
        range = new CellRangeAddress(rn-1, rn-1, 0, 1);
        sheet.addMergedRegion(range);

        cell = row.createCell(2);
        cell.setCellValue(getZakrep());
        cell.setCellStyle(styleHeader2);
        range = new CellRangeAddress(rn-1, rn-1, 2, 19);
        sheet.addMergedRegion(range);
        rn++;











        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue("Дата");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(1);
        cell.setCellValue("Норма эксп.");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(2);
        cell.setCellValue("Факт пробег");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(3);
        cell.setCellValue("Маш дни спис");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(4);
        cell.setCellValue("Маш дни испр");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(5);
        cell.setCellValue("Маш дни экспл");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(6);
        cell.setCellValue("Топл ост нач м");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(7);
        cell.setCellValue("Топл выд теч м");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(8);
        cell.setCellValue("Топл ост кон м");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(9);
        cell.setCellValue("Топл израсх факт");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(10);
        cell.setCellValue("Топл израсх норма");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(11);
        cell.setCellValue("Топл эконом");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(12);
        cell.setCellValue("Топл перерасх");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(13);
        cell.setCellValue("Выполн ТО1");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(14);
        cell.setCellValue("Выполн ТО2");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(15);
        cell.setCellValue("Выполн КР");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(16);
        cell.setCellValue("КТГ");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(17);
        cell.setCellValue("КТВ");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(18);
        cell.setCellValue("Перепробег");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(19);
        cell.setCellValue("Пок. спид.");
        cell.setCellStyle(styleHeader);

        row.setHeight((short) 1000);



        for (int j = 0; j < listReportExpl_1Model.size(); j++) {


            if (!grf.equalsIgnoreCase(listReportExpl_1Model.get(j).getKvString())) {
                if(!grf.equals(""))
                {
                    row = sheet.createRow(rn);rn++;
                    cell = row.createCell(0);
                    cell.setCellValue(grf+" квартал");
                    cell.setCellStyle(styleHeader);
                    cell = row.createCell(1);
                    cell.setCellValue(getTotalSum(grf,1));
                    cell.setCellStyle(styleBodyNum);
                    for(int k=2;k<20;k++) {
                        cell = row.createCell(k);
                        cell.setCellValue(getTotalSum(grf, k));
                        if(k<6)cell.setCellStyle(styleBodyNum);
                        else
                            cell.setCellStyle(styleBodyNumFloat);
                    }
                    cell.setCellStyle(styleBodyNum);

                    rn++;
                }
                grf = listReportExpl_1Model.get(j).getKvString();


            }
            row = sheet.createRow(rn);
            rn++;

            cell = row.createCell(0);
            String value = listReportExpl_1Model.get(j).getDat();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(1);
            cell.setCellValue(listReportExpl_1Model.get(j).getNorma_run());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(2);
            cell.setCellValue(listReportExpl_1Model.get(j).getFact_run());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(3);
            cell.setCellValue(listReportExpl_1Model.get(j).getD_out());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(4);
            cell.setCellValue(listReportExpl_1Model.get(j).getD_good());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(5);
            cell.setCellValue(listReportExpl_1Model.get(j).getD_operational());
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(6);
            cell.setCellValue(listReportExpl_1Model.get(j).getGsm_begin_m());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(7);
            cell.setCellValue(listReportExpl_1Model.get(j).getGsm_given());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(8);
            cell.setCellValue(listReportExpl_1Model.get(j).getGsm_end_m());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(9);
            cell.setCellValue(listReportExpl_1Model.get(j).getGsm_out());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(10);
            cell.setCellValue(listReportExpl_1Model.get(j).getGsm_norma());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(11);
            cell.setCellValue((listReportExpl_1Model.get(j).getPere()<0)?-1*listReportExpl_1Model.get(j).getPere():0);
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(12);
            cell.setCellValue((listReportExpl_1Model.get(j).getPere()>=0)?listReportExpl_1Model.get(j).getPere():0);
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(13);
            cell.setCellValue(listReportExpl_1Model.get(j).getTo1());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(14);
            cell.setCellValue(listReportExpl_1Model.get(j).getTo2());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(15);
            cell.setCellValue(listReportExpl_1Model.get(j).getKp());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(16);
            cell.setCellValue(((listReportExpl_1Model.get(j).getD_good()>listReportExpl_1Model.get(j).getD_out()) || listReportExpl_1Model.get(j).getD_out()==0)?1:listReportExpl_1Model.get(j).getD_good()/listReportExpl_1Model.get(j).getD_out());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(17);
            cell.setCellValue((listReportExpl_1Model.get(j).getD_out()!=0)?listReportExpl_1Model.get(j).getD_operational()/listReportExpl_1Model.get(j).getD_out():0);
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(18);
            cell.setCellValue(listReportExpl_1Model.get(j).getNorma_run()-listReportExpl_1Model.get(j).getFact_run());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(19);
            cell.setCellValue(listReportExpl_1Model.get(j).getSpeed_end());
            cell.setCellStyle(styleBodyNum);



        }
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue(grf+" квартал");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(1);
        cell.setCellValue(getTotalSum(grf,1));
        cell.setCellStyle(styleBodyNum);
        for(int k=2;k<20;k++) {
            cell = row.createCell(k);
            cell.setCellValue(getTotalSum(grf, k));
            if(k<6)cell.setCellStyle(styleBodyNum);
            else
                cell.setCellStyle(styleBodyNumFloat);
        }
        cell.setCellStyle(styleBodyNum);

        rn++;
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue("Итого");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(1);
        cell.setCellValue(getAllSum(1));
        cell.setCellStyle(styleBodyNum);
        for(int k=2;k<20;k++) {
            cell = row.createCell(k);
            cell.setCellValue(getAllSum(k));
            if(k<6)cell.setCellStyle(styleBodyNum);
            else
            cell.setCellStyle(styleBodyNumFloat);
        }
        cell.setCellStyle(styleBodyNum);
        for (int i = 1; i <= 19; i++)
        {
            sheet.setColumnWidth(i, 10 * 256);
        }
            sheet.autoSizeColumn(0);


        //sheet.setDefaultColumnWidth(15);



        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/vnd.ms-excel");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"explcard01.xls\"");

        wb.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();
    }
}
