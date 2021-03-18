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
import xyz.lebalex.atx.AtxLogin;
import xyz.lebalex.atx.controls.ManagerPages;
import xyz.lebalex.atx.modelReport.Expl01Model;
import xyz.lebalex.atx.modelReport.InventarModel;

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

@Named
@ViewScoped
public class ReportExpl01 extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(ReportExpl01.class);
    private List<Expl01Model> listExpl01Model = new ArrayList();
    private String caption;
    private String period;


    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;

    public List<Expl01Model> getListExpl01Model() {
        return listExpl01Model;
    }

    public String getCaption() {
        return "Эксплутационные показатели (кратко) " + this.period;
    }

    public long getTotalCount(String name) {
        return listExpl01Model.stream().filter(listExpl01Model -> name.equals(listExpl01Model.getExpr2())).count();
    }

    public double getTotalSumProbeg(String name) {
        double sum = listExpl01Model.stream().filter(listExpl01Model -> name.equals(listExpl01Model.getExpr2()))
                .mapToDouble(Expl01Model::getFact_run)
                .sum();
        return sum;
    }

    public double getTotalSumPereProbeg(String name) {
        double sum = listExpl01Model.stream().filter(listExpl01Model -> name.equals(listExpl01Model.getExpr2()))
                .mapToDouble(Expl01Model::getPere)
                .sum();
        return sum;
    }

    public double getTotalSumKtg(String name) {
        double count = listExpl01Model.stream().filter(listExpl01Model -> name.equals(listExpl01Model.getExpr2()))
                .mapToDouble(Expl01Model::getD_out)
                .sum();
        double sum = listExpl01Model.stream().filter(listExpl01Model -> name.equals(listExpl01Model.getExpr2()))
                .mapToDouble(Expl01Model::getD_g)
                .sum();
        return (sum / count) > 1 ? 1 : (sum / count);
    }

    public double getTotalSumKtv(String name) {
        double count = listExpl01Model.stream().filter(listExpl01Model -> name.equals(listExpl01Model.getExpr2()))
                .mapToDouble(Expl01Model::getD_out)
                .sum();
        double sum = listExpl01Model.stream().filter(listExpl01Model -> name.equals(listExpl01Model.getExpr2()))
                .mapToDouble(Expl01Model::getD_o)
                .sum();
        return (sum / count) > 1 ? 1 : (sum / count);
    }

    public long getTotalCount() {
        return listExpl01Model.stream().count();
    }

    public double getTotalSumProbegFull() {
        double sum = listExpl01Model.stream()
                .mapToDouble(Expl01Model::getFact_run)
                .sum();
        return sum;
    }

    public double getTotalSumPereProbegFull() {
        double sum = listExpl01Model.stream()
                .mapToDouble(Expl01Model::getPere)
                .sum();
        return sum;
    }

    public double getTotalSumKtgFull() {
        double count = listExpl01Model.stream()
                .mapToDouble(Expl01Model::getD_out)
                .sum();
        double sum = listExpl01Model.stream()
                .mapToDouble(Expl01Model::getD_g)
                .sum();
        return (sum / count) > 1 ? 1 : (sum / count);
    }

    public double getTotalSumKtvFull() {
        double count = listExpl01Model.stream()
                .mapToDouble(Expl01Model::getD_out)
                .sum();
        double sum = listExpl01Model.stream()
                .mapToDouble(Expl01Model::getD_o)
                .sum();
        return (sum / count) > 1 ? 1 : (sum / count);
    }

    public void excelInventar() throws IOException {
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
        cell.setCellStyle(styleHeader);


        int rn = 1;
        String grf = "";
        float dep_fact_run=0;
        float dep_ktg=0;
        float dep_ktv=0;
        float dep_out=0;
        float dep_pere=0;

        float all_fact_run=0;
        float all_ktg=0;
        float all_ktv=0;
        float all_out=0;
        float all_pere=0;

        for (int j = 0; j < listExpl01Model.size(); j++) {


            if (!grf.equalsIgnoreCase(listExpl01Model.get(j).getGroupField())) {
                if(!grf.equals(""))
                {
                    row = sheet.createRow(rn);rn++;
                    cell = row.createCell(0);
                    cell.setCellValue("Итого по отделу");
                    cell.setCellStyle(styleBodyNum);
                    cell = row.createCell(1);
                    cell.setCellValue(dep_fact_run);
                    cell.setCellStyle(styleBodyNum);
                    cell = row.createCell(2);
                    cell.setCellValue((dep_out>0)?dep_ktg/dep_out:0);
                    cell.setCellStyle(styleBodyNumFloat);
                    cell = row.createCell(3);
                    cell.setCellValue((dep_out>0)?dep_ktv/dep_out:0);
                    cell.setCellStyle(styleBodyNumFloat);
                    cell = row.createCell(4);
                    cell.setCellValue(dep_pere);
                    cell.setCellStyle(styleBodyNum);
                    dep_fact_run=0;
                    dep_ktg=0;
                    dep_ktv=0;
                    dep_out=0;
                    dep_pere=0;
                    rn++;
                }

                row = sheet.createRow(rn);
                rn++;
                grf = listExpl01Model.get(j).getGroupField();
                cell = row.createCell(0);
                cell.setCellValue(listExpl01Model.get(j).getName());
                cell.setCellStyle(styleHeader2);
                CellRangeAddress range = new CellRangeAddress(rn - 1, rn - 1, 0, 4);
                sheet.addMergedRegion(range);

                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                cell.setCellValue("Управление:  " + listExpl01Model.get(j).getExpr1());
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, 4);
                sheet.addMergedRegion(range);

                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                cell.setCellValue("Отдел:  " + listExpl01Model.get(j).getExpr2());
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, 4);
                sheet.addMergedRegion(range);

                row = sheet.createRow(rn);rn++;
                cell = row.createCell(0);
                cell.setCellValue("Служба");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(1);
                cell.setCellValue("Пробег (факт)");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(2);
                cell.setCellValue("КТГ");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(3);
                cell.setCellValue("КТВ");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(4);
                cell.setCellValue("Перепробег");
                cell.setCellStyle(styleHeader);
            }
            row = sheet.createRow(rn);
            rn++;

            cell = row.createCell(0);
            String value = listExpl01Model.get(j).getExpr4();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(1);
            cell.setCellValue(listExpl01Model.get(j).getFact_run());dep_fact_run+=listExpl01Model.get(j).getFact_run();
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(2);
            cell.setCellValue(listExpl01Model.get(j).getKtg());dep_ktg+=listExpl01Model.get(j).getD_g();
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(3);
            cell.setCellValue(listExpl01Model.get(j).getKtv());dep_ktv+=listExpl01Model.get(j).getD_o();
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(4);
            cell.setCellValue(listExpl01Model.get(j).getPere());dep_pere+=listExpl01Model.get(j).getPere();
            cell.setCellStyle(styleBodyNum);

            dep_out+=listExpl01Model.get(j).getD_out();

            all_fact_run+=listExpl01Model.get(j).getFact_run();
            all_ktg+=listExpl01Model.get(j).getD_g();
            all_ktv+=listExpl01Model.get(j).getD_o();
            all_pere+=listExpl01Model.get(j).getPere();
            all_out+=listExpl01Model.get(j).getD_out();

        }
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue("Итого по отделу");
        cell.setCellStyle(styleBodyNum);
        cell = row.createCell(1);
        cell.setCellValue(dep_fact_run);
        cell.setCellStyle(styleBodyNum);
        cell = row.createCell(2);
        cell.setCellValue((dep_out>0)?dep_ktg/dep_out:0);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(3);
        cell.setCellValue((dep_out>0)?dep_ktv/dep_out:0);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(4);
        cell.setCellValue(dep_pere);
        cell.setCellStyle(styleBodyNum);

        rn++;
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue("Итого");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(1);
        cell.setCellValue(all_fact_run);
        cell.setCellStyle(styleBodyNum);
        cell = row.createCell(2);
        cell.setCellValue((all_out>0)?all_ktg/all_out:0);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(3);
        cell.setCellValue((all_out>0)?all_ktv/all_out:0);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(4);
        cell.setCellValue(all_pere);
        cell.setCellStyle(styleBodyNum);
        for (int i = 0; i <= 4; i++)
            sheet.autoSizeColumn(i);

        CellRangeAddress range = new CellRangeAddress(0, 0, 0, 4);
        sheet.addMergedRegion(range);

        /*CellRangeAddress range = new CellRangeAddress(1, rn-1, 0, c1-1);
            RegionUtil.setBorderBottom(BorderStyle.THIN, range, sheet);
            RegionUtil.setBorderTop(BorderStyle.THIN, range, sheet);
            RegionUtil.setBorderLeft(BorderStyle.THIN, range, sheet);
            RegionUtil.setBorderRight(BorderStyle.THIN, range, sheet);*/


        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/vnd.ms-excel");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"expl01.xls\"");

        wb.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();
    }

    public void expl01(int id_region, int id_division, int id_depart, int id_preDepart, int id_serv, int id_PreServ, int id_dislocation, int id_mark, int id_source_bay,
                       int id_source_fin, int id_group,
                       String modelAvt, String numb_1, String numb_2, String m_year1, String m_year2, String vin, String n_engine, String n_body, String n_chassis, String n_pts, String insur,
                       String inAc1, String inAc2, String drD1, String drD2, int id_typetc, int arenda, int spisat, int fuel_p,
                       int notATX_p, String volumDVC, int typeDVC, int manufactureTC, int spisat_next) {

        this.period = "с " + (drD1.equals("-1") ? " " : drD1) + " по " + (drD2.equals("-1") ? " " : drD2);

        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetReportExpl_pokaz(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
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
                    cStmt.setInt(23, 0);//State_p;
                    cStmt.setInt(24, 0);//UotAc_p;
                    cStmt.setInt(25, 0);//life_p;
                    cStmt.setString(26, drD1);//outD1;
                    cStmt.setString(27, drD2);//outD2;

                    cStmt.setInt(28, id_dislocation);
                    cStmt.setInt(29, id_PreServ);
                    cStmt.setInt(30, notATX_p);//notATX_p;


                    boolean hadResults = cStmt.execute();
                    if (hadResults) {

                        try (ResultSet rs = cStmt.getResultSet()) {
                            listExpl01Model.clear();
                            while (rs.next()) {
                                listExpl01Model.add(new Expl01Model(rs.getString(1), rs.getString(2),
                                        rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getFloat(6),
                                        rs.getFloat(7), rs.getFloat(8), rs.getFloat(9), rs.getFloat(10), rs.getFloat(11)));
                            }
                            managerPages.setPage("reports/reportExpl01.xhtml");
                            //FacesContext.getCurrentInstance().getExternalContext().redirect("listavto2.jsf");
                        }
                    } else logger.log(Level.ERROR, "false");
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }


    }
}
