package xyz.lebalex.atx.prints;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import xyz.lebalex.atx.AtxBase;
import xyz.lebalex.atx.models.RemontPrintModel;
import xyz.lebalex.atx.utils.AtxUtils;
import xyz.lebalex.atx.utils.DateHelper;
import xyz.lebalex.atx.utils.FacesUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class RemontPrint extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(RemontPrint.class);
    private  int id_remont;
    private int num_remont;
    private String avto;
    private List<RemontPrintModel> remontPrintModel=new ArrayList<>();

    @PostConstruct
    public void initialize() {
        try {
            setId_remont(Integer.parseInt(FacesUtils.getRequestParameter("id")));
            String[] result = AtxUtils.getAvtoMarkModelNumber(Integer.parseInt(FacesUtils.getRequestParameter("id_avt")));
            this.avto = result[0]+" "+result[1]+" гос № "+result[2];
            getCard();
        }catch(Exception e)
        {
            setId_remont(0);
            logger.log(Level.ERROR, e.getMessage());
        }
    }


    private void getCard()
    {
        try {
            remontPrintModel.clear();
            try (Connection con = getConnection()) {
                /*try (PreparedStatement prst = con.prepareStatement("with a0 as(\n" +
                        "select r.id, r.num, r.date_begin, r.date_end,  v.name, \n" +
                        "oil_l, p.name as place, speed_begin, speed_to\n" +
                        "from tRemontAvt r  inner join tVidRemont v on v.id=r.id_vid inner join tPlaceRemont p on p.id=r.id_place where r.id=?)\n" +
                        "select a0.num, a0.date_begin, a0.date_end,  a0.name as vid, \n" +
                        "a1.name as rem_n,a1.coast as rem_c,a2.name as z_n,a2.coast as z_c,a3.name as t_n,a3.coast as t_c,a4.name as t2_n,a4.coast as t2_c,agr.name as r_n,agr.num_old,agr.num_new,\n" +
                        "a0.oil_l, DATEDIFF(day, a0.date_begin, a0.date_end)+1 as days, a0.place, a0.speed_begin, a0.speed_to\n" +
                        "from a0 left join tRemDifr a1 on a1.id_remont=a0.id and a1.type=1 left join tRemDifr a2 on a2.id_remont=a0.id and a2.type=2 left join tRemDifr a3 on a3.id_remont=a0.id and a3.type=3 left join tRemDifr a4 on a4.id_remont=a0.id and a4.type=4\n" +
                        "left join tRemAgr agr on agr.id_remont=a0.id")) {*/
                try (PreparedStatement prst = con.prepareStatement("select r.id, r.num, r.date_begin, r.date_end,  v.name as vid, \n" +
                        "oil_l, p.name as place, speed_begin, speed_to, DATEDIFF(day, r.date_begin, r.date_end)+1 as days\n" +
                        "from tRemontAvt r  inner join tVidRemont v on v.id=r.id_vid inner join tPlaceRemont p on p.id=r.id_place where r.id=?")) {
                    prst.setInt(1, getId_remont());
                    try (ResultSet rs = prst.executeQuery()) {
                        if(rs.next()) {
                            this.num_remont = rs.getInt("num");
                            remontPrintModel.add(new RemontPrintModel(rs.getDate("date_begin"),
                                    rs.getDate("date_end"),
                                    rs.getString("vid"),
                                    rs.getInt("oil_l"),
                                    rs.getInt("days"),
                                    rs.getString("place"),
                                    rs.getInt("speed_begin"),
                                    rs.getInt("speed_to")
                            ));

                        }
                    }
                }
                try (PreparedStatement prst = con.prepareStatement("select name, num_old, num_new from tRemAgr where id_remont=?")) {
                    prst.setInt(1, getId_remont());
                    try (ResultSet rs = prst.executeQuery()) {
                        int i=0;
                        while (rs.next()) {
                            if(remontPrintModel.size()>i)
                            {
                                RemontPrintModel r = remontPrintModel.get(i);
                                r.setArg_name(rs.getString("name"));
                                r.setArg_old_num(rs.getString("num_old"));
                                r.setArg_new_num(rs.getString("num_new"));
                                i++;
                            }else
                            {
                                RemontPrintModel r = remontPrintModel.get(i-1);
                                RemontPrintModel r_new = new RemontPrintModel(r.getDate_begin(), r.getDate_end(), r.getVid(), r.getOil(), r.getDays(), r.getPlace(), r.getSpeed_b(), r.getSpeed_n());
                                r_new.setArg_name(rs.getString("name"));
                                r_new.setArg_old_num(rs.getString("num_old"));
                                r_new.setArg_new_num(rs.getString("num_new"));
                                remontPrintModel.add(r_new);
                                i++;
                            }

                        }
                    }
                }
                for(int type=1;type<5;type++) {
                    try (PreparedStatement prst = con.prepareStatement("select name, coast, type from tRemDifr where id_remont=? and type=? order by type, num")) {
                        prst.setInt(1, getId_remont());
                        prst.setInt(2, type);
                        try (ResultSet rs = prst.executeQuery()) {
                            int i = 0;
                            while (rs.next()) {
                                if (remontPrintModel.size() > i) {
                                    RemontPrintModel r = remontPrintModel.get(i);
                                    if (type == 1) {
                                        r.setRemont_name(rs.getString("name"));
                                        r.setRemont_coast(rs.getFloat("coast"));
                                    } else if (type == 2) {
                                        r.setZap_name(rs.getString("name"));
                                        r.setZap_coast(rs.getFloat("coast"));
                                    } else if (type == 3) {
                                        r.setTmc_name(rs.getString("name"));
                                        r.setTmc_coast(rs.getFloat("coast"));
                                    } else if (type == 4) {
                                        r.setTmc2_name(rs.getString("name"));
                                        r.setTmc2_coast(rs.getFloat("coast"));
                                    }
                                    i++;
                                } else {
                                    RemontPrintModel r = remontPrintModel.get(i - 1);
                                    RemontPrintModel r_new = new RemontPrintModel(r.getDate_begin(), r.getDate_end(), r.getVid(), r.getOil(), r.getDays(), r.getPlace(), r.getSpeed_b(), r.getSpeed_n());
                                    r_new.setArg_name(r.getArg_name());
                                    r_new.setArg_old_num(r.getArg_old_num());
                                    r_new.setArg_new_num(r.getArg_new_num());
                                    if (type == 1) {
                                        r_new.setRemont_name(rs.getString("name"));
                                        r_new.setRemont_coast(rs.getFloat("coast"));
                                    } else if (type == 2) {
                                        r_new.setZap_name(rs.getString("name"));
                                        r_new.setZap_coast(rs.getFloat("coast"));
                                    } else if (type == 3) {
                                        r_new.setTmc_name(rs.getString("name"));
                                        r_new.setTmc_coast(rs.getFloat("coast"));
                                    } else if (type == 4) {
                                        r_new.setTmc2_name(rs.getString("name"));
                                        r_new.setTmc2_coast(rs.getFloat("coast"));
                                    }
                                    remontPrintModel.add(r_new);
                                    i++;
                                }

                            }
                        }
                    }
                }
            }
        }catch(Exception ex)
        {
            logger.log(Level.ERROR, ex);
        }
    }

    public int getId_remont() {
        return id_remont;
    }

    public void setId_remont(int id_remont) {
        this.id_remont = id_remont;
    }

    public List<RemontPrintModel> getRemontPrintModel() {
        return remontPrintModel;
    }

    public void setRemontPrintModel(List<RemontPrintModel> remontPrintModel) {
        this.remontPrintModel = remontPrintModel;
    }

    public int getNum_remont() {
        return num_remont;
    }

    public String getAvto() {
        return avto;
    }

    public void getReportData() throws IOException {
        /*HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(0.0);*/


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
        //styleBody.setAlignment(HorizontalAlignment.CENTER);


        HSSFSheet sheet = wb.createSheet("sheet");

        //sheet.addMergedRegion(CellRangeAddress.valueOf("A1:B2"));

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("Ремонтная карта №"+this.num_remont+" "+this.avto);
        cell.setCellStyle(styleHeader);

        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("Дата");
        cell.setCellStyle(styleHeader);


        row = sheet.getRow(1);
        cell = row.createCell(2);
        cell.setCellValue("Вид");
        cell.setCellStyle(styleHeader);

        row = sheet.getRow(1);
        cell = row.createCell(3);
        cell.setCellValue("Сумма затрат");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(1);
        cell = row.createCell(11);
        cell.setCellValue("Замена агрегатов");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(1);
        cell = row.createCell(14);
        cell.setCellValue("Масло (л)");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(1);
        cell = row.createCell(15);
        cell.setCellValue("Кол-во дней");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(1);
        cell = row.createCell(16);
        cell.setCellValue("Место ремонта");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(1);
        cell = row.createCell(17);
        cell.setCellValue("Показания спидометра");
        cell.setCellStyle(styleHeader);

        row = sheet.createRow(2);
        cell = row.createCell(3);
        cell.setCellValue("Ремонт");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(2);
        cell = row.createCell(5);
        cell.setCellValue("Зап.частия");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(2);
        cell = row.createCell(7);
        cell.setCellValue("ТМЦ");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(2);
        cell = row.createCell(9);
        cell.setCellValue("ТМЦ (склад)");
        cell.setCellStyle(styleHeader);

        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellValue("Начало");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(3);
        cell = row.createCell(1);
        cell.setCellValue("Окончание");
        cell.setCellStyle(styleHeader);

        row = sheet.getRow(3);
        cell = row.createCell(3);
        cell.setCellValue("Наименование");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(3);
        cell = row.createCell(4);
        cell.setCellValue("Стоимость");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(3);
        cell = row.createCell(5);
        cell.setCellValue("Наименование");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(3);
        cell = row.createCell(6);
        cell.setCellValue("Стоимость");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(3);
        cell = row.createCell(7);
        cell.setCellValue("Наименование");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(3);
        cell = row.createCell(8);
        cell.setCellValue("Стоимость");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(3);
        cell = row.createCell(9);
        cell.setCellValue("Наименование");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(3);
        cell = row.createCell(10);
        cell.setCellValue("Стоимость");
        cell.setCellStyle(styleHeader);

        row = sheet.getRow(3);
        cell = row.createCell(11);
        cell.setCellValue("Вид");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(3);
        cell = row.createCell(12);
        cell.setCellValue("старый номер");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(3);
        cell = row.createCell(13);
        cell.setCellValue("новый номер");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(3);
        cell = row.createCell(17);
        cell.setCellValue("На начало работ");
        cell.setCellStyle(styleHeader);
        row = sheet.getRow(3);
        cell = row.createCell(18);
        cell.setCellValue("на следующее ТО");
        cell.setCellStyle(styleHeader);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:S1"));


        setMergedAndBorder(1, 2, 0, 1, sheet, true);
        setMergedAndBorder(1, 3, 2, 2, sheet, true);
        setMergedAndBorder(1, 1, 3, 10, sheet, true);
        setMergedAndBorder(1, 2, 11, 13, sheet, true);
        setMergedAndBorder(1, 3, 14, 14, sheet, true);
        setMergedAndBorder(1, 3, 15, 15, sheet, true);
        setMergedAndBorder(1, 3, 16, 16, sheet, true);
        setMergedAndBorder(1, 2, 17, 18, sheet, true);
        setMergedAndBorder(2, 2, 3, 4, sheet, true);
        setMergedAndBorder(2, 2, 5, 6, sheet, true);
        setMergedAndBorder(2, 2, 7, 8, sheet, true);
        setMergedAndBorder(2, 2, 9, 10, sheet, true);






//sheet.setColumnWidth(0,25 * 256);


int rc=4;
        /*for (RemontPrintModel rows: remontPrintModel
             ) */
        for(int j=0;j<remontPrintModel.size(); j++){
            String value = (j>0)? DateHelper.DateToString(remontPrintModel.get(j-1).getDate_begin(),"dd.MM.yyyy"):"";
            row = sheet.createRow(rc);
            cell = row.createCell(0);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getDate_begin()!=null && !value.equalsIgnoreCase(DateHelper.DateToString(remontPrintModel.get(j).getDate_begin(),"dd.MM.yyyy")))
                cell.setCellValue(DateHelper.DateToString(remontPrintModel.get(j).getDate_begin(),"dd.MM.yyyy"));

            value = (j>0)?DateHelper.DateToString(remontPrintModel.get(j-1).getDate_end(),"dd.MM.yyyy"):"";
            cell = row.createCell(1);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getDate_end()!=null && !value.equalsIgnoreCase(DateHelper.DateToString(remontPrintModel.get(j).getDate_end(),"dd.MM.yyyy")))
                cell.setCellValue(DateHelper.DateToString(remontPrintModel.get(j).getDate_end(),"dd.MM.yyyy"));

            value = (j>0)?remontPrintModel.get(j-1).getVid():"";
            cell = row.createCell(2);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getVid()!=null && !value.equalsIgnoreCase(remontPrintModel.get(j).getVid()))
                cell.setCellValue(remontPrintModel.get(j).getVid());

            value = (j>0)?remontPrintModel.get(j-1).getRemont_name():"";
            cell = row.createCell(3);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getRemont_name()!=null && !value.equalsIgnoreCase(remontPrintModel.get(j).getRemont_name()))
                cell.setCellValue(remontPrintModel.get(j).getRemont_name());


            cell = row.createCell(4);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getRemont_name()!=null && !value.equalsIgnoreCase(remontPrintModel.get(j).getRemont_name()))
                cell.setCellValue(remontPrintModel.get(j).getRemont_coast());

            value = (j>0)?remontPrintModel.get(j-1).getZap_name():"";
            cell = row.createCell(5);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getZap_name()!=null && !value.equalsIgnoreCase(remontPrintModel.get(j).getZap_name()))
                cell.setCellValue(remontPrintModel.get(j).getZap_name());

            cell = row.createCell(6);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getZap_name()!=null && !value.equalsIgnoreCase(remontPrintModel.get(j).getZap_name()))
                cell.setCellValue(remontPrintModel.get(j).getZap_coast());

            value = (j>0)?remontPrintModel.get(j-1).getTmc_name():"";
            cell = row.createCell(7);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getTmc_name()!=null && !value.equalsIgnoreCase(remontPrintModel.get(j).getTmc_name()))
                cell.setCellValue(remontPrintModel.get(j).getTmc_name());

            cell = row.createCell(8);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getTmc_name()!=null && !value.equalsIgnoreCase(remontPrintModel.get(j).getTmc_name()))
                cell.setCellValue(remontPrintModel.get(j).getTmc_coast());

            value = (j>0)?remontPrintModel.get(j-1).getTmc2_name():"";
            cell = row.createCell(9);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getTmc2_name()!=null && !value.equalsIgnoreCase(remontPrintModel.get(j).getTmc2_name()))
                cell.setCellValue(remontPrintModel.get(j).getTmc2_name());

            cell = row.createCell(10);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getTmc2_name()!=null && !value.equalsIgnoreCase(remontPrintModel.get(j).getTmc2_name()))
                cell.setCellValue(remontPrintModel.get(j).getTmc2_coast());


            value = (j>0)?remontPrintModel.get(j-1).getArg_name():"";
            cell = row.createCell(11);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getArg_name()!=null && !value.equalsIgnoreCase(remontPrintModel.get(j).getArg_name()))
                cell.setCellValue(remontPrintModel.get(j).getArg_name());
            value = (j>0)?remontPrintModel.get(j-1).getArg_old_num():"";
            cell = row.createCell(12);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getArg_old_num()!=null && !value.equalsIgnoreCase(remontPrintModel.get(j).getArg_old_num()))
                cell.setCellValue(remontPrintModel.get(j).getArg_old_num());
            value = (j>0)?remontPrintModel.get(j-1).getArg_new_num():"";
            cell = row.createCell(13);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getArg_new_num()!=null && !value.equalsIgnoreCase(remontPrintModel.get(j).getArg_new_num()))
                cell.setCellValue(remontPrintModel.get(j).getArg_new_num());

            int value_int = (j>0)?remontPrintModel.get(j-1).getOil():0;
            cell = row.createCell(14);cell.setCellStyle(styleBody);
            if(value_int!=remontPrintModel.get(j).getOil())
                cell.setCellValue(remontPrintModel.get(j).getOil());
            value_int = (j>0)?remontPrintModel.get(j-1).getDays():0;
            cell = row.createCell(15);cell.setCellStyle(styleBody);
            if(value_int!=remontPrintModel.get(j).getDays())
                cell.setCellValue(remontPrintModel.get(j).getDays());

            value = (j>0)?remontPrintModel.get(j-1).getPlace():"";
            cell = row.createCell(16);cell.setCellStyle(styleBody);
            if(remontPrintModel.get(j).getPlace()!=null && !value.equalsIgnoreCase(remontPrintModel.get(j).getPlace()))
                cell.setCellValue(remontPrintModel.get(j).getPlace());

            value_int = (j>0)?remontPrintModel.get(j-1).getSpeed_b():0;
            cell = row.createCell(17);cell.setCellStyle(styleBody);
            if(value_int!=remontPrintModel.get(j).getSpeed_b())
                cell.setCellValue(remontPrintModel.get(j).getSpeed_b());
            value_int = (j>0)?remontPrintModel.get(j-1).getSpeed_n():0;
            cell = row.createCell(18);cell.setCellStyle(styleBody);
            if(value_int!=remontPrintModel.get(j).getSpeed_n())
                cell.setCellValue(remontPrintModel.get(j).getSpeed_n());

            rc++;
        }

        for(int i=0;i<=18;i++)
            sheet.autoSizeColumn(i);

        sheet.setColumnWidth(14,12 * 256);
        sheet.setColumnWidth(15,12 * 256);

        for(int i=0;i<3;i++)
            setMergedAndBorder(4, rc - 1, i, i, sheet, false);

        for(int i=14;i<19;i++)
            setMergedAndBorder(4, rc - 1, i, i, sheet, false);




        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/vnd.ms-excel");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"remont.xls\"");

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
