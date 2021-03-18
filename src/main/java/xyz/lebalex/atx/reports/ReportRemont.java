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
import xyz.lebalex.atx.modelReport.LinNormGSMModel;
import xyz.lebalex.atx.modelReport.RemontModel;
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
import java.util.stream.Stream;

@Named
@ViewScoped
public class ReportRemont extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(ReportRemont.class);
    private List<RemontModel> listRemontModel = new ArrayList();
    private String caption;
    private String period;
    private List<Boolean> filds = new ArrayList();
    private List<String> fildsName = new ArrayList();

    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;

    public String getCaption() {
        return "Отчет по ремонту" + this.period;
    }
    public List<RemontModel> getListRemontModel() {
        return listRemontModel;
    }

    public void setListRemontModel(List<RemontModel> listRemontModel) {
        this.listRemontModel = listRemontModel;
    }

    public List<Boolean> getFilds() {
        return filds;
    }

    public long getTotalCount(String name) {
        return listRemontModel.stream().filter(listRemontModel -> name.equals(listRemontModel.getGroupField())).count();
    }
    public double getTotalCoast(String name) {
        double c1 = listRemontModel.stream().filter(listRemontModel -> name.equals(listRemontModel.getGroupField())).mapToDouble(RemontModel::getCoast1).sum();
        double c2 = listRemontModel.stream().filter(listRemontModel -> name.equals(listRemontModel.getGroupField())).mapToDouble(RemontModel::getCoast2).sum();
        double c3 = listRemontModel.stream().filter(listRemontModel -> name.equals(listRemontModel.getGroupField())).mapToDouble(RemontModel::getCoast3).sum();
        double c4 = listRemontModel.stream().filter(listRemontModel -> name.equals(listRemontModel.getGroupField())).mapToDouble(RemontModel::getCoast4).sum();

        return c1+c2+c3+c4;

    }

    public long getTotalCountAll() {
        return listRemontModel.stream().count();
    }

    public double getTotalCoastAll() {

        double c1 = listRemontModel.stream().mapToDouble(RemontModel::getCoast1).sum();
        double c2 = listRemontModel.stream().mapToDouble(RemontModel::getCoast2).sum();
        double c3 = listRemontModel.stream().mapToDouble(RemontModel::getCoast3).sum();
        double c4 = listRemontModel.stream().mapToDouble(RemontModel::getCoast4).sum();
        return c1+c2+c3+c4;

    }

    public int getSpam()
    {
        int result = 0;
        for (boolean a:this.filds) {
            if(a) result++;
        }
        return result-1;
    }
    public void remont(int id_region, int id_division, int id_depart, int id_preDepart, int id_serv, int id_PreServ, int id_dislocation, int id_mark, int id_source_bay,
                       int id_source_fin, int id_group,
                       String modelAvt, String numb_1, String numb_2, String m_year1, String m_year2, String vin, String n_engine, String n_body, String n_chassis, String n_pts, String insur,
                       String inAc1, String inAc2, String drD1, String drD2, int state_p, int uotAc_p, int life_p, String outD1, String outD2, int id_typetc, int arenda, int spisat, int fuel_p,
                       int notATX_p, String volumDVC, int typeDVC, int manufactureTC, int spisat_next, List<Boolean> filds) {
        this.period = "с " + (drD1.equals("-1") ? " " : drD1) + " по " + (drD2.equals("-1") ? " " : drD2);
        this.filds = filds;
        fildsName.clear();
        fildsName.add("Марка");
        fildsName.add("гос№");
        fildsName.add("Год выпуска");
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
        fildsName.add("Наимен. ремонта");
        fildsName.add("Сумма ремонта");
        fildsName.add("Наимен. з/ч");
        fildsName.add("Сумма на з/ч");
        fildsName.add("Наимен. ТМЦ");
        fildsName.add("Сумма ТМЦ");
        fildsName.add("ТМЦ (склад)");
        fildsName.add("Сумма ТМЦ (склад)");
        fildsName.add("Масло");
        fildsName.add("Всего затрачено");
        fildsName.add("Кол-во дней");
        fildsName.add("Ремонт. замена");
        fildsName.add("Период ремонта");
        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetReportRemont(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
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
                    cStmt.setString(31, drD1);//datt1;
                    cStmt.setString(32, drD2);//datt2;
                    cStmt.setInt(33, id_dislocation);
                    cStmt.setInt(34, id_PreServ);
                    cStmt.setInt(35, notATX_p);//notATX_p;
                    cStmt.setString(36, "-1");//modelDVC;
                    cStmt.setString(37, volumDVC);
                    cStmt.setInt(38, typeDVC);
                    cStmt.setInt(39, manufactureTC);



                    boolean hadResults = cStmt.execute();
                    if (hadResults) {

                        try (ResultSet rs = cStmt.getResultSet()) {
                            listRemontModel.clear();
                            while (rs.next()) {
                                listRemontModel.add(new RemontModel(rs.getString(1), rs.getString(2),
                                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
                                        rs.getFloat(14),rs.getFloat(15),rs.getFloat(16),rs.getFloat(17),rs.getInt(18),
                                        rs.getDate(19), rs.getDate(20),
                                        rs.getInt(21),
                                        rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25),
                                        rs.getFloat(26),
                                        rs.getString(27), rs.getString(28), rs.getString(29), rs.getString(30)));
                            }
                            managerPages.setPage("reports/reportRemont.xhtml");
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
        styleBody.setWrapText(true);


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
        float coastGroup=0;
        float coastAll=0;

        for(int j=0;j<listRemontModel.size(); j++){
            c1=0;

            if(!grf.equalsIgnoreCase(listRemontModel.get(j).getGroupField()))
            {
                if(!grf.equals(""))
                {
                    row = sheet.createRow(rn);rn++;
                    cell = row.createCell(0);
                    cell.setCellValue("Кол-во автотранспорта");
                    cell.setCellStyle(styleBodyNum);
                    cell = row.createCell(1);
                    cell.setCellValue(countGroup);
                    cell.setCellStyle(styleBodyNum);
                    cell = row.createCell(2);
                    cell.setCellValue("на сумму");
                    cell.setCellStyle(styleBodyNum);
                    cell = row.createCell(3);
                    cell.setCellValue(coastGroup);
                    cell.setCellStyle(styleBodyNum);
                    countGroup=0;
                    coastGroup=0;

                    rn++;
                }
                row = sheet.createRow(rn);rn++;
                grf=listRemontModel.get(j).getGroupField();


                grf = listRemontModel.get(j).getGroupField();
                cell = row.createCell(0);
                cell.setCellValue(listRemontModel.get(j).getName());
                cell.setCellStyle(styleHeader2);
                CellRangeAddress range = new CellRangeAddress(rn - 1, rn - 1, 0, colCount);
                sheet.addMergedRegion(range);

                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                cell.setCellValue("Управление:  " + listRemontModel.get(j).getExpr1());
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, colCount);
                sheet.addMergedRegion(range);

                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                cell.setCellValue("Отдел:  " + listRemontModel.get(j).getExpr2());
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, colCount);
                sheet.addMergedRegion(range);

                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                cell.setCellValue("Под Отдел:  " + (listRemontModel.get(j).getExpr3()!=null?listRemontModel.get(j).getExpr3():""));
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
                    float val=0;
                    switch(c2)
                    {
                        case 0:value=listRemontModel.get(j).getMark();break;
                        case 1:value=listRemontModel.get(j).getNumb_1();break;
                        case 2:value=listRemontModel.get(j).getM_year();break;
                        case 3:value=listRemontModel.get(j).getVin();break;
                        case 4:value=listRemontModel.get(j).getN_engine();break;
                        case 5:value=listRemontModel.get(j).getN_body();break;
                        case 6:value=listRemontModel.get(j).getN_chassis();break;
                        case 7:value=listRemontModel.get(j).getModelDVC();break;
                        case 8:value=listRemontModel.get(j).getVolumeDVC();break;
                        case 9:value=listRemontModel.get(j).getTypeDVC();break;
                        case 10:value=listRemontModel.get(j).getManufactureTC();break;
                        case 11:value=listRemontModel.get(j).getExpr5();break;
                        case 12:value=listRemontModel.get(j).getExpr6();break;
                        case 13:value=listRemontModel.get(j).getN1();break;
                        case 14:val=listRemontModel.get(j).getCoast1();coastGroup+=val;coastAll+=val;break;
                        case 15:value=listRemontModel.get(j).getN2();break;
                        case 16:val=listRemontModel.get(j).getCoast2();coastGroup+=val;coastAll+=val;break;
                        case 17:value=listRemontModel.get(j).getN3();break;
                        case 18:val=listRemontModel.get(j).getCoast3();coastGroup+=val;coastAll+=val;break;
                        case 19:value=listRemontModel.get(j).getN4();break;
                        case 20:val=listRemontModel.get(j).getCoast4();coastGroup+=val;coastAll+=val;break;
                        case 21:val=listRemontModel.get(j).getOil_l();break;

                        case 22:val=listRemontModel.get(j).getCoast1()+listRemontModel.get(j).getCoast2()+listRemontModel.get(j).getCoast3();break;

                        case 23:try{value=Integer.toString(listRemontModel.get(j).getDdd());}catch(Exception e){value="0";}break;
                        case 24:try{value=Integer.toString(listRemontModel.get(j).getExpr4());}catch(Exception e){value="0";}break;
                        case 25:if(listRemontModel.get(j).getDate_begin()!=null)
                            value= DateHelper.DateToString(listRemontModel.get(j).getDate_begin(),"dd.MM.yyyy")
                                    +" - "+DateHelper.DateToString(listRemontModel.get(j).getDate_end(),"dd.MM.yyyy");break;
                    }
                    if(c2==14 || c2==16 || c2==18 || c2==20 || c2==21 || c2==22)
                    {
                        cell.setCellValue(val);
                        cell.setCellStyle(styleBodyNum);
                    }else{
                        if(value!=null)
                            value = value.replaceAll("(\r\r)", "\r\n");
                        cell.setCellValue((value!=null)?value.trim():"");
                        cell.setCellStyle(styleBody);}


                }c2++;
            }
        }
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue("Кол-во автотранспорта");
        cell.setCellStyle(styleBodyNum);
        cell = row.createCell(1);
        cell.setCellValue(countGroup);
        cell.setCellStyle(styleBodyNum);
        cell = row.createCell(2);
        cell.setCellValue("на сумму");
        cell.setCellStyle(styleBodyNum);
        cell = row.createCell(3);
        cell.setCellValue(coastGroup);
        cell.setCellStyle(styleBodyNum);

        row = sheet.createRow(rn+1);
        cell = row.createCell(0);
        cell.setCellValue("Кол-во автотранспорта");
        cell.setCellStyle(styleBodyNum);
        cell = row.createCell(1);
        cell.setCellValue(countAll);
        cell.setCellStyle(styleBodyNum);
        cell = row.createCell(2);
        cell.setCellValue("на сумму");
        cell.setCellStyle(styleBodyNum);
        cell = row.createCell(3);
        cell.setCellValue(coastAll);
        cell.setCellStyle(styleBodyNum);

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
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"remont.xls\"");

        wb.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();


    }
}
