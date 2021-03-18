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
import xyz.lebalex.atx.modelReport.InventarModel;
import xyz.lebalex.atx.modelReport.ReportTOModel;
import xyz.lebalex.atx.utils.DateHelper;

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
public class ReportTO extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(ReportTO.class);
    private List<ReportTOModel> listReportTOModel = new ArrayList();
    private List<Boolean> filds = new ArrayList();
    private List<String> fildsName = new ArrayList();
    private String caption;
    private int what;

    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;

    public String getCaption() {
        return caption;
    }

    public List<ReportTOModel> getListReportTOModel() {
        return listReportTOModel;
    }

    public void setListReportTOModel(List<ReportTOModel> listReportTOModel) {
        this.listReportTOModel = listReportTOModel;
    }

    public long getTotalCount(String name) {
        return listReportTOModel.stream().filter(listReportTOModel -> name.equals(listReportTOModel.getGroupField())).count();
    }
    public long getTotalCountAll() {
        return listReportTOModel.stream().count();
    }


    public List<Boolean> getFilds() {
        return filds;
    }

    public String getTitle20()
    {
        return fildsName.get(19);
    }

    public int getWhat() {
        return what;
    }
    public int getSpam()
    {
        int result = 0;
        for (boolean a:this.filds) {
            if(a) result++;
        }
        return result-1;
    }

    public void to(int id_region, int id_division, int id_depart, int id_preDepart, int id_serv, int id_PreServ, int id_dislocation, int id_mark, int id_source_bay,
                   int id_source_fin, int id_group,
                   String modelAvt, String numb_1, String numb_2, String m_year1, String m_year2, String vin, String n_engine, String n_body, String n_chassis, String n_pts, String insur,
                   String inAc1, String inAc2, int state_p, int uotAc_p, int life_p, String outD1, String outD2, int id_typetc, int arenda, int spisat, int fuel_p,
                   int notATX_p, String volumDVC, int typeDVC, int manufactureTC, int spisat_next, List<Boolean> filds, int what) {
        this.caption="Необходимо пройти ТО в текущем году";
        this.filds = filds;
        this.what=what;
        fildsName.clear();
        fildsName.add("Марка");
        fildsName.add("Категория");
        fildsName.add("гос№");
        fildsName.add("Год выпуска");
        fildsName.add("Цвет");
        fildsName.add("VIN");
        fildsName.add("№ двигателя");
        fildsName.add("№ кузова");
        fildsName.add("№ шасси");
        fildsName.add("Модель ДВС");
        fildsName.add("Объем");
        fildsName.add("Тип ДВС");
        fildsName.add("Производитель ТС");
        fildsName.add("Бюджет");
        fildsName.add("Служба");
        fildsName.add("Под.служба");
        fildsName.add("№ техпаспорта");
        fildsName.add("№ страховки");
        fildsName.add("Дата страховки");
        fildsName.add("Страховая компания");
        fildsName.add("Мощность");
        fildsName.add("Дата на учет");
        fildsName.add("Дислокация");
        fildsName.add("Спидометр");
        fildsName.add("Аренда");
        fildsName.add("Группа эксплуатации");
        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetReportTO(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
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
                    cStmt.setString(26, outD1);//outD1;
                    cStmt.setString(27, outD2);//outD2;
                    cStmt.setString(28, insur);
                    cStmt.setString(29, "-1");//d_insur1;
                    cStmt.setString(30, "-1");//d_insur2;
                    cStmt.setInt(31, id_dislocation);
                    cStmt.setInt(32, id_typetc);
                    cStmt.setInt(33, arenda);
                    cStmt.setInt(34, spisat);
                    cStmt.setInt(35, fuel_p);
                    cStmt.setInt(36, id_PreServ);
                    cStmt.setInt(37, notATX_p);//notATX_p;






                    boolean hadResults = cStmt.execute();
                    if (hadResults) {

                        try (ResultSet rs = cStmt.getResultSet()) {
                            listReportTOModel.clear();
                            while (rs.next()) {
                                listReportTOModel.add(new ReportTOModel(rs.getString(1), rs.getString(2),
                                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
                                        rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22),
                                        rs.getFloat(23), rs.getString(24), rs.getString(25), rs.getString(26), rs.getInt(27), rs.getString(28)
                                        , rs.getString(29), rs.getString(30), rs.getString(31), rs.getString(32),null));
                            }
                            managerPages.setPage("reports/reportTO.xhtml");
                        }
                    } else logger.log(Level.ERROR, "false");
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }


    public void notTo(int id_region, int id_division, int id_depart, int id_preDepart, int id_serv, int id_PreServ, int id_dislocation, int id_mark, int id_source_bay,
                   int id_source_fin, int id_group,
                   String modelAvt, String numb_1, String numb_2, String m_year1, String m_year2, String vin, String n_engine, String n_body, String n_chassis, String n_pts, String insur,
                   String inAc1, String inAc2, String drD1, String drD2, int state_p, int uotAc_p, int life_p, String outD1, String outD2, int id_typetc, int arenda, int spisat, int fuel_p,
                   int notATX_p, String volumDVC, int typeDVC, int manufactureTC, int spisat_next, List<Boolean> filds, int what) {
        String period = "с " + (drD1.equals("-1") ? " " : drD1) + " по " + (drD2.equals("-1") ? " " : drD2);
        this.caption="Отсутствие ТО более 3 месяцев (отсутствие ТО в период "+period+")";
        this.filds = filds;
        this.what = what;
        fildsName.clear();
        fildsName.add("Марка");
        fildsName.add("Категория");
        fildsName.add("гос№");
        fildsName.add("Год выпуска");
        fildsName.add("Цвет");
        fildsName.add("VIN");
        fildsName.add("№ двигателя");
        fildsName.add("№ кузова");
        fildsName.add("№ шасси");
        fildsName.add("Модель ДВС");
        fildsName.add("Объем");
        fildsName.add("Тип ДВС");
        fildsName.add("Производитель ТС");
        fildsName.add("Бюджет");
        fildsName.add("Служба");
        fildsName.add("Под.служба");
        fildsName.add("№ техпаспорта");
        fildsName.add("№ страховки");
        fildsName.add("Дата страховки");
        fildsName.add("Последнее ТО");
        fildsName.add("Мощность");
        fildsName.add("Дата на учет");
        fildsName.add("Дислокация");
        fildsName.add("Спидометр");
        fildsName.add("Аренда");
        fildsName.add("Группа эксплуатации");
        try {
            try (Connection con = getConnection()) {
                con.setAutoCommit(false);
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetReportNotTO(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
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
                    cStmt.setString(26, outD1);//outD1;
                    cStmt.setString(27, outD2);//outD2;
                    cStmt.setString(28, insur);
                    cStmt.setString(29, "-1");//d_insur1;
                    cStmt.setString(30, "-1");//d_insur2;
                    cStmt.setInt(31, id_dislocation);
                    cStmt.setInt(32, id_typetc);
                    cStmt.setInt(33, arenda);
                    cStmt.setInt(34, spisat);
                    cStmt.setInt(35, fuel_p);
                    cStmt.setInt(36, id_PreServ);
                    cStmt.setInt(37, notATX_p);//notATX_p;
                    cStmt.setString(38, drD1);//outD1;
                    cStmt.setString(39, drD2);//outD2;




                    boolean hadResults = cStmt.execute();
                    while (!hadResults)
                        hadResults = cStmt.getMoreResults();

                    if (hadResults) {

                        try (ResultSet rs = cStmt.getResultSet()) {
                            listReportTOModel.clear();
                            while (rs.next()) {
                                listReportTOModel.add(new ReportTOModel(rs.getString(1), rs.getString(2),
                                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
                                        rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22),
                                        rs.getFloat(23), rs.getString(24), rs.getString(25), rs.getString(26), rs.getInt(27), rs.getString(28)
                                        , rs.getString(30), rs.getString(31), rs.getString(32), rs.getString(33), rs.getDate(29)));
                            }
                            managerPages.setPage("reports/reportTO.xhtml");
                        }
                    } else logger.log(Level.ERROR, "false");
                }
                con.commit();
                con.setAutoCommit(true);
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }

    public void excelReportTO() throws IOException {
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

        HSSFSheet sheet = wb.createSheet("sheet");

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(getCaption());
        cell.setCellStyle(styleHeaderTitle);


        int colCount=0;
        for (Boolean f : filds) {
            if(f)colCount++;
        }

        int c1=0;
        int c2=0;

        int rn=1;
        String grf="";
        int countGroup=0;
        int countAll=0;

        for(int j=0;j<listReportTOModel.size(); j++){
            c1=0;

            if(!grf.equalsIgnoreCase(listReportTOModel.get(j).getGroupField()))
            {
                if(!grf.equals(""))
                {
                    row = sheet.createRow(rn);rn++;
                    cell = row.createCell(0);
                    cell.setCellValue("Итого");
                    cell.setCellStyle(styleBodyNum);
                    cell = row.createCell(1);
                    cell.setCellValue(countGroup);
                    cell.setCellStyle(styleBodyNum);
                    countGroup=0;

                    rn++;
                }
                row = sheet.createRow(rn);rn++;
                grf=listReportTOModel.get(j).getGroupField();
                /*cell = row.createCell(0);
                cell.setCellValue(listReportTOModel.get(j).getGroupField());
                cell.setCellStyle(styleHeader2);
                CellRangeAddress range = new CellRangeAddress(rn-1, rn-1, 0, colCount-1);
                sheet.addMergedRegion(range);*/

                grf = listReportTOModel.get(j).getGroupField();
                cell = row.createCell(0);
                cell.setCellValue(listReportTOModel.get(j).getName());
                cell.setCellStyle(styleHeader2);
                CellRangeAddress range = new CellRangeAddress(rn - 1, rn - 1, 0, colCount);
                sheet.addMergedRegion(range);

                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                cell.setCellValue("Управление:  " + listReportTOModel.get(j).getExpr1());
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, colCount);
                sheet.addMergedRegion(range);

                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                cell.setCellValue("Отдел:  " + listReportTOModel.get(j).getExpr2());
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, colCount);
                sheet.addMergedRegion(range);

                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                cell.setCellValue("Под Отдел:  " + (listReportTOModel.get(j).getExpr3()!=null?listReportTOModel.get(j).getExpr3():""));
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, colCount);
                sheet.addMergedRegion(range);

                c1=0;c2=0;
                row = sheet.createRow(rn);rn++;
                for (Boolean f : filds) {
                    cell = row.createCell(c1);
                    if (f) {
                        cell.setCellValue(fildsName.get(c2));
                        cell.setCellStyle(styleHeader);
                        c1++;
                    }
                    c2++;
                }
            }
            row = sheet.createRow(rn);rn++;
            countAll++;
            countGroup++;
            c1=0;c2=0;
            for(Boolean f:filds)
            {
                if(f)
                {
                    cell = row.createCell(c1);c1++;
                    String value="";
                    switch(c2)
                    {
                        case 0:value=listReportTOModel.get(j).getMark();break;
                        case 1:value=listReportTOModel.get(j).getTypetc();break;
                        case 2:value=listReportTOModel.get(j).getNumb_1();break;
                        case 3:value=listReportTOModel.get(j).getM_year();break;
                        case 4:value=listReportTOModel.get(j).getColor();break;
                        case 5:value=listReportTOModel.get(j).getVin();break;
                        case 6:value=listReportTOModel.get(j).getN_engine();break;
                        case 7:value=listReportTOModel.get(j).getN_body();break;
                        case 8:value=listReportTOModel.get(j).getN_chassis();break;
                        case 9:value=listReportTOModel.get(j).getModelDVC();break;
                        case 10:value=listReportTOModel.get(j).getVolumeDVC();break;
                        case 11:value=listReportTOModel.get(j).getTypeDVC();break;
                        case 12:value=listReportTOModel.get(j).getManufactureTC();break;
                        case 13:value=listReportTOModel.get(j).getExpr5();break;
                        case 14:value=listReportTOModel.get(j).getExpr6();break;
                        case 15:value=listReportTOModel.get(j).getExpr6p();break;
                        case 16:value=listReportTOModel.get(j).getExpr7();break;
                        case 17:value=listReportTOModel.get(j).getExpr8();break;
                        case 18:value=listReportTOModel.get(j).getExpr8d();break;

                        case 19:if(what==7)value=listReportTOModel.get(j).getExpr10(); else value= DateHelper.DateToString(listReportTOModel.get(j).getMaxOperDate(),"dd.MM.yyyy");break;

                        case 20:value=listReportTOModel.get(j).getExpr9();break;
                        case 21:value=listReportTOModel.get(j).getExpr11();break;
                        case 22:value=listReportTOModel.get(j).getExpr4();break;
                        case 23:try{value=Float.toString(listReportTOModel.get(j).getSpeed_end());}catch(Exception e){value="0,0";}break;
                        case 24:if(listReportTOModel.get(j).getaD1()!=null) value=listReportTOModel.get(j).getaD1()+" - "+listReportTOModel.get(j).getaD2();break;
                        case 25:value=listReportTOModel.get(j).getExplGroup();break;
                    }
                    cell.setCellValue((value!=null)?value.trim():"");

                    cell.setCellStyle(styleBody);
                }c2++;
            }
        }
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue("Итого");
        cell.setCellStyle(styleBodyNum);
        cell = row.createCell(1);
        cell.setCellValue(countGroup);
        cell.setCellStyle(styleBodyNum);

        row = sheet.createRow(rn+1);
        cell = row.createCell(0);
        cell.setCellValue("Итого");
        cell.setCellStyle(styleHeader2);
        cell = row.createCell(1);
        cell.setCellValue(countAll);
        cell.setCellStyle(styleHeader2);

        for(int i=0;i<=c1;i++)
            sheet.autoSizeColumn(i);

        CellRangeAddress range = new CellRangeAddress(0, 0, 0, colCount-1);
        sheet.addMergedRegion(range);




        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/vnd.ms-excel");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"reportTO.xls\"");

        wb.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();


    }
}
