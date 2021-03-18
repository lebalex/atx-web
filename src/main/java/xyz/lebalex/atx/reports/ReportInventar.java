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
public class ReportInventar extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(ReportInventar.class);
    private List<InventarModel> listInventarModel = new ArrayList();
    private List<Boolean> filds = new ArrayList();
    private List<String> fildsName = new ArrayList();
    private int what;
    private String caption;

    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;

    public List<InventarModel> getListInventarModel() {
        return listInventarModel;
    }

    public void setListInventarModel(List<InventarModel> listInventarModel) {
        this.listInventarModel = listInventarModel;
    }

    public int getWhat() {
        return what;
    }

    public List<Boolean> getFilds() {
        return filds;
    }
    public long getTotalCount(String name) {
        if(this.what==1)
            return listInventarModel.stream().filter(listInventarModel -> name.equals(listInventarModel.getGroupField())).count();
        else
            return listInventarModel.stream().count();
    }
    public long getTotalCountAll() {
        return listInventarModel.stream().count();
    }

    public String getCaption() {
        return caption;
    }

    public void inventar(int id_region, int id_division, int id_depart, int id_preDepart, int id_serv, int id_PreServ, int id_dislocation, int id_mark, int id_source_bay,
                         int id_source_fin, int id_group,
                         String modelAvt, String numb_1, String numb_2, String m_year1, String m_year2, String vin, String n_engine, String n_body, String n_chassis, String n_pts, String insur,
                         String inAc1, String inAc2, int state_p, int uotAc_p, int life_p, String outD1, String outD2, int id_typetc, int arenda, int spisat, int fuel_p,
                         int notATX_p, String volumDVC, int typeDVC, int manufactureTC, int spisat_next, List<Boolean> filds, int what) {
        this.filds = filds;
        this.what = what;
        this.caption="Отчет по инвентаризации";
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
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetReportInventar(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
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
                    cStmt.setInt(38, -1);//State_priz
                    cStmt.setString(39, "-1");//modelDVC;
                    cStmt.setString(40, volumDVC);
                    cStmt.setInt(41, typeDVC);
                    cStmt.setInt(42, manufactureTC);
                    cStmt.setInt(43, spisat_next);
                    cStmt.setString(44, "-1");//color


                    boolean hadResults = cStmt.execute();
                    if (hadResults) {

                        try (ResultSet rs = cStmt.getResultSet()) {
                            listInventarModel.clear();
                            while (rs.next()) {
                                listInventarModel.add(new InventarModel(rs.getString(1), rs.getString(2),
                                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
                                        rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22),
                                        rs.getFloat(23), rs.getString(24), rs.getString(25), rs.getString(26), rs.getInt(27), rs.getString(28), rs.getString(29),
                                        rs.getString(30), rs.getString(31), rs.getString(32), what));
                            }
                            managerPages.setPage("reports/reportInventar.xhtml");
                            //FacesContext.getCurrentInstance().getExternalContext().redirect("listavto2.jsf");
                        }
                    } else logger.log(Level.ERROR, "false");
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
    }
    public void govNumber(int id_region, int id_division, int id_depart, int id_preDepart, int id_serv, int id_PreServ, int id_dislocation, int id_mark, int id_source_bay,
                         int id_source_fin, int id_group,
                         String modelAvt, String numb_1, String numb_2, String m_year1, String m_year2, String vin, String n_engine, String n_body, String n_chassis, String n_pts, String insur,
                         String inAc1, String inAc2, String drD1, String drD2, int state_p, int uotAc_p, int life_p, String outD1, String outD2, int id_typetc, int arenda, int spisat, int fuel_p,
                         int notATX_p, String volumDVC, int typeDVC, int manufactureTC, int spisat_next, List<Boolean> filds, int what) {
        this.filds = filds;
        this.what = what;
        this.caption="Отчет о замене гос.номера с " + (drD1.equals("-1") ? " " : drD1) + " по " + (drD2.equals("-1") ? " " : drD2);
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
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetReportGovNumber(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
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
                    cStmt.setInt(38, -1);//State_priz
                    cStmt.setString(39, "-1");//modelDVC;
                    cStmt.setString(40, volumDVC);
                    cStmt.setInt(41, typeDVC);
                    cStmt.setInt(42, manufactureTC);
                    cStmt.setInt(43, spisat_next);
                    cStmt.setString(44, drD1);//datt1;
                    cStmt.setString(45, drD2);//datt2;




                    boolean hadResults = cStmt.execute();
                    if (hadResults) {

                        try (ResultSet rs = cStmt.getResultSet()) {
                            listInventarModel.clear();
                            while (rs.next()) {
                                listInventarModel.add(new InventarModel(rs.getString(1), rs.getString(2),
                                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
                                        rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22),
                                        rs.getFloat(23), rs.getString(24), rs.getString(25), rs.getString(26), rs.getInt(27), rs.getString(28), rs.getString(29),
                                        rs.getString(30), rs.getString(31), rs.getString(32), what));
                            }
                            managerPages.setPage("reports/reportInventar.xhtml");
                            //FacesContext.getCurrentInstance().getExternalContext().redirect("listavto2.jsf");
                        }
                    } else logger.log(Level.ERROR, "false");
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, ex);
        }
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

        for(int j=0;j<listInventarModel.size(); j++){
            c1=0;

            if(!grf.equalsIgnoreCase(listInventarModel.get(j).getGroupField()))
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


                grf = listInventarModel.get(j).getGroupField();
                if(!grf.equals("0")) {
                    row = sheet.createRow(rn);rn++;
                    cell = row.createCell(0);
                    cell.setCellValue(listInventarModel.get(j).getName());
                    cell.setCellStyle(styleHeader2);
                    CellRangeAddress range = new CellRangeAddress(rn - 1, rn - 1, 0, colCount);
                    sheet.addMergedRegion(range);

                    row = sheet.createRow(rn);
                    rn++;
                    cell = row.createCell(0);
                    cell.setCellValue("Управление:  " + listInventarModel.get(j).getExpr1());
                    cell.setCellStyle(styleHeader2);
                    range = new CellRangeAddress(rn - 1, rn - 1, 0, colCount);
                    sheet.addMergedRegion(range);

                    row = sheet.createRow(rn);
                    rn++;
                    cell = row.createCell(0);
                    cell.setCellValue("Отдел:  " + listInventarModel.get(j).getExpr2());
                    cell.setCellStyle(styleHeader2);
                    range = new CellRangeAddress(rn - 1, rn - 1, 0, colCount);
                    sheet.addMergedRegion(range);

                    row = sheet.createRow(rn);
                    rn++;
                    cell = row.createCell(0);
                    cell.setCellValue("Под Отдел:  " + (listInventarModel.get(j).getExpr3() != null ? listInventarModel.get(j).getExpr3() : ""));
                    cell.setCellStyle(styleHeader2);
                    range = new CellRangeAddress(rn - 1, rn - 1, 0, colCount);
                    sheet.addMergedRegion(range);
                }
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
                        case 0:value=listInventarModel.get(j).getMark();break;
                        case 1:value=listInventarModel.get(j).getTypetc();break;
                        case 2:value=listInventarModel.get(j).getNumb_1();break;
                        case 3:value=listInventarModel.get(j).getM_year();break;
                        case 4:value=listInventarModel.get(j).getColor();break;
                        case 5:value=listInventarModel.get(j).getVin();break;
                        case 6:value=listInventarModel.get(j).getN_engine();break;
                        case 7:value=listInventarModel.get(j).getN_body();break;
                        case 8:value=listInventarModel.get(j).getN_chassis();break;
                        case 9:value=listInventarModel.get(j).getModelDVC();break;
                        case 10:value=listInventarModel.get(j).getVolumeDVC();break;
                        case 11:value=listInventarModel.get(j).getTypeDVC();break;
                        case 12:value=listInventarModel.get(j).getManufactureTC();break;
                        case 13:value=listInventarModel.get(j).getExpr5();break;
                        case 14:value=listInventarModel.get(j).getExpr6();break;
                        case 15:value=listInventarModel.get(j).getExpr6p();break;
                        case 16:value=listInventarModel.get(j).getExpr7();break;
                        case 17:value=listInventarModel.get(j).getExpr8();break;
                        case 18:value=listInventarModel.get(j).getExpr8d();break;
                        case 19:value=listInventarModel.get(j).getExpr10();break;
                        case 20:value=listInventarModel.get(j).getExpr9();break;
                        case 21:value=listInventarModel.get(j).getExpr11();break;
                        case 22:value=listInventarModel.get(j).getExpr4();break;
                        case 23:try{value=Float.toString(listInventarModel.get(j).getSpeed_end());}catch(Exception e){value="0,0";}break;
                        case 24:if(listInventarModel.get(j).getaD1()!=null) value=listInventarModel.get(j).getaD1()+" - "+listInventarModel.get(j).getaD2();break;
                        case 25:value=listInventarModel.get(j).getExplGroup();break;
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

        if(!grf.equals("0")) {
            row = sheet.createRow(rn + 1);
            cell = row.createCell(0);
            cell.setCellValue("Итого");
            cell.setCellStyle(styleHeader2);
            cell = row.createCell(1);
            cell.setCellValue(countAll);
            //cell.setCellStyle(styleBodyNum);
        }

        //CellRangeAddress range = new CellRangeAddress(rn+1, rn+1, 1, colCount-1);
        //sheet.addMergedRegion(range);

        for(int i=0;i<=c1;i++)
            sheet.autoSizeColumn(i);

        CellRangeAddress range = new CellRangeAddress(0, 0, 0, colCount-1);
        sheet.addMergedRegion(range);

        /*CellRangeAddress range = new CellRangeAddress(1, rn-1, 0, c1-1);
            RegionUtil.setBorderBottom(BorderStyle.THIN, range, sheet);
            RegionUtil.setBorderTop(BorderStyle.THIN, range, sheet);
            RegionUtil.setBorderLeft(BorderStyle.THIN, range, sheet);
            RegionUtil.setBorderRight(BorderStyle.THIN, range, sheet);*/


        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/vnd.ms-excel");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"inventar.xls\"");

        wb.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();


    }
}
