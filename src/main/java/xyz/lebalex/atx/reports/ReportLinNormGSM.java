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
import xyz.lebalex.atx.AtxLogin;
import xyz.lebalex.atx.controls.ManagerPages;
import xyz.lebalex.atx.modelReport.Expl01Model;
import xyz.lebalex.atx.modelReport.LinNormGSMModel;

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
public class ReportLinNormGSM extends AtxBase implements Serializable {
    static Logger logger = Logger.getLogger(ReportExpl01.class);
    private List<LinNormGSMModel> listLinNormGSMModel = new ArrayList();
    private String caption;
    private String period;
    private int what;


    @Inject
    AtxLogin atxLogin;


    @Inject
    ManagerPages managerPages;

    public String getCaption() {
        switch(what){
            case 4: return "Расчет линейных норм ГСМ по марке " + this.period;
            case 5: return "Расчет линейных норм ГСМ по марке (с моделью ДВС) " + this.period;
            default: return "Расчет линейных норм ГСМ " + this.period;
        }
    }

    public List<LinNormGSMModel> getListLinNormGSMModel() {
        return listLinNormGSMModel;
    }

    public long getTotalCount(String name) {
        return listLinNormGSMModel.stream().filter(listLinNormGSMModel -> name.equals(listLinNormGSMModel.getGroupField())).count();
    }
    public long getAllCount() {
        return listLinNormGSMModel.stream().count();
    }
    public double getTotalSumProbegLim(String name, int i) {
        switch(i) {
            case 1: return listLinNormGSMModel.stream().filter(listLinNormGSMModel -> name.equals(listLinNormGSMModel.getGroupField()))
                    .mapToDouble(LinNormGSMModel::getNorma_run)
                    .sum();
            case 2: return listLinNormGSMModel.stream().filter(listLinNormGSMModel -> name.equals(listLinNormGSMModel.getGroupField()))
                    .mapToDouble(LinNormGSMModel::getLeto_2)
                    .sum();
            case 3: return listLinNormGSMModel.stream().filter(listLinNormGSMModel -> name.equals(listLinNormGSMModel.getGroupField()))
                    .mapToDouble(LinNormGSMModel::getM0_2)
                    .sum();
            case 4: return listLinNormGSMModel.stream().filter(listLinNormGSMModel -> name.equals(listLinNormGSMModel.getGroupField()))
                    .mapToDouble(LinNormGSMModel::getM1_2)
                    .sum();
            case 5: return listLinNormGSMModel.stream().filter(listLinNormGSMModel -> name.equals(listLinNormGSMModel.getGroupField()))
                    .mapToDouble(LinNormGSMModel::getM2_2)
                    .sum();
            case 6: return listLinNormGSMModel.stream().filter(listLinNormGSMModel -> name.equals(listLinNormGSMModel.getGroupField()))
                    .mapToDouble(LinNormGSMModel::getM3_2)
                    .sum();
            case 7: return listLinNormGSMModel.stream().filter(listLinNormGSMModel -> name.equals(listLinNormGSMModel.getGroupField()))
                    .mapToDouble(LinNormGSMModel::getM4_2)
                    .sum();
            case 8: return listLinNormGSMModel.stream().filter(listLinNormGSMModel -> name.equals(listLinNormGSMModel.getGroupField()))
                    .mapToDouble(LinNormGSMModel::getM5_2)
                    .sum();
            case 9: return listLinNormGSMModel.stream().filter(listLinNormGSMModel -> name.equals(listLinNormGSMModel.getGroupField()))
                    .mapToDouble(LinNormGSMModel::getKv1)
                    .sum();
            case 10: return listLinNormGSMModel.stream().filter(listLinNormGSMModel -> name.equals(listLinNormGSMModel.getGroupField()))
                    .mapToDouble(LinNormGSMModel::getKv2)
                    .sum();
            case 11: return listLinNormGSMModel.stream().filter(listLinNormGSMModel -> name.equals(listLinNormGSMModel.getGroupField()))
                    .mapToDouble(LinNormGSMModel::getKv3)
                    .sum();
            case 12: return listLinNormGSMModel.stream().filter(listLinNormGSMModel -> name.equals(listLinNormGSMModel.getGroupField()))
                    .mapToDouble(LinNormGSMModel::getKv4)
                    .sum();
            case 13: return listLinNormGSMModel.stream().filter(listLinNormGSMModel -> name.equals(listLinNormGSMModel.getGroupField()))
                    .mapToDouble(LinNormGSMModel::getGod)
                    .sum();


            default:return 0;
        }

    }
    public double getAllSumProbegLim(int i) {
        switch(i) {
            case 1: return listLinNormGSMModel.stream()
                    .mapToDouble(LinNormGSMModel::getNorma_run)
                    .sum();
            case 2: return listLinNormGSMModel.stream()
                    .mapToDouble(LinNormGSMModel::getLeto_2)
                    .sum();
            case 3: return listLinNormGSMModel.stream()
                    .mapToDouble(LinNormGSMModel::getM0_2)
                    .sum();
            case 4: return listLinNormGSMModel.stream()
                    .mapToDouble(LinNormGSMModel::getM1_2)
                    .sum();
            case 5: return listLinNormGSMModel.stream()
                    .mapToDouble(LinNormGSMModel::getM2_2)
                    .sum();
            case 6: return listLinNormGSMModel.stream()
                    .mapToDouble(LinNormGSMModel::getM3_2)
                    .sum();
            case 7: return listLinNormGSMModel.stream()
                    .mapToDouble(LinNormGSMModel::getM4_2)
                    .sum();
            case 8: return listLinNormGSMModel.stream()
                    .mapToDouble(LinNormGSMModel::getM5_2)
                    .sum();
            case 9: return listLinNormGSMModel.stream()
                    .mapToDouble(LinNormGSMModel::getKv1)
                    .sum();
            case 10: return listLinNormGSMModel.stream()
                    .mapToDouble(LinNormGSMModel::getKv2)
                    .sum();
            case 11: return listLinNormGSMModel.stream()
                    .mapToDouble(LinNormGSMModel::getKv3)
                    .sum();
            case 12: return listLinNormGSMModel.stream()
                    .mapToDouble(LinNormGSMModel::getKv4)
                    .sum();
            case 13: return listLinNormGSMModel.stream()
                    .mapToDouble(LinNormGSMModel::getGod)
                    .sum();


            default:return 0;
        }

    }
    public void excelLinNormGsm() throws IOException {
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

        HSSFCellStyle styleCaption = (HSSFCellStyle) wb.createCellStyle();
        fontHeader.setBold(true);
        styleCaption.setFont(fontHeader);
        styleCaption.setAlignment(HorizontalAlignment.CENTER);

        HSSFSheet sheet = wb.createSheet("sheet");

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(getCaption());
        cell.setCellStyle(styleCaption);


        int rn = 1;
        String grf = "";
        float count=0;
        float norm=0;
        float leto2=0;
        float m0_2=0;
        float m1_2=0;
        float m2_2=0;
        float m3_2=0;
        float m4_2=0;
        float m5_2=0;
        float kv1=0;
        float kv2=0;
        float kv3=0;
        float kv4=0;
        float god=0;

        float all_count=0;
        float all_norm=0;
        float all_leto2=0;
        float all_m0_2=0;
        float all_m1_2=0;
        float all_m2_2=0;
        float all_m3_2=0;
        float all_m4_2=0;
        float all_m5_2=0;
        float all_kv1=0;
        float all_kv2=0;
        float all_kv3=0;
        float all_kv4=0;
        float all_god=0;

        for (LinNormGSMModel linNormGSMModel: listLinNormGSMModel) {


            if (!grf.equalsIgnoreCase(linNormGSMModel.getGroupField())) {
                if(!grf.equals(""))
                {
                    row = sheet.createRow(rn);rn++;
                    cell = row.createCell(0);
                    cell.setCellValue(count);
                    cell.setCellStyle(styleBodyNum);
                    cell = row.createCell(17);
                    cell.setCellValue(norm);
                    cell.setCellStyle(styleBodyNumFloat);
                    cell = row.createCell(18);
                    cell.setCellValue(leto2);
                    cell.setCellStyle(styleBodyNumFloat);
                    cell = row.createCell(19);
                    cell.setCellValue(m0_2);
                    cell.setCellStyle(styleBodyNumFloat);
                    cell = row.createCell(20);
                    cell.setCellValue(m1_2);
                    cell.setCellStyle(styleBodyNumFloat);
                    cell = row.createCell(21);
                    cell.setCellValue(m2_2);
                    cell.setCellStyle(styleBodyNumFloat);
                    cell = row.createCell(22);
                    cell.setCellValue(m3_2);
                    cell.setCellStyle(styleBodyNumFloat);
                    cell = row.createCell(23);
                    cell.setCellValue(m4_2);
                    cell.setCellStyle(styleBodyNumFloat);
                    cell = row.createCell(24);
                    cell.setCellValue(m5_2);
                    cell.setCellStyle(styleBodyNumFloat);
                    cell = row.createCell(25);
                    cell.setCellValue(kv1);
                    cell.setCellStyle(styleBodyNumFloat);
                    cell = row.createCell(26);
                    cell.setCellValue(kv2);
                    cell.setCellStyle(styleBodyNumFloat);
                    cell = row.createCell(27);
                    cell.setCellValue(kv3);
                    cell.setCellStyle(styleBodyNumFloat);
                    cell = row.createCell(28);
                    cell.setCellValue(kv4);
                    cell.setCellStyle(styleBodyNumFloat);
                    cell = row.createCell(29);
                    cell.setCellValue(god);
                    cell.setCellStyle(styleBodyNumFloat);

                    count=0;
                    norm=0;
                    leto2=0;
                    m0_2=0;
                    m1_2=0;
                    m2_2=0;
                    m3_2=0;
                    m4_2=0;
                    m5_2=0;
                    kv1=0;
                    kv2=0;
                    kv3=0;
                    kv4=0;
                    god=0;
                    rn++;
                }

                row = sheet.createRow(rn);
                rn++;
                grf = linNormGSMModel.getGroupField();
                cell = row.createCell(0);
                cell.setCellValue(linNormGSMModel.getDepart());
                cell.setCellStyle(styleHeader2);
                CellRangeAddress range = new CellRangeAddress(rn - 1, rn - 1, 0, 29);
                sheet.addMergedRegion(range);

                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                cell.setCellValue(linNormGSMModel.getFin());
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, 29);
                sheet.addMergedRegion(range);


                row = sheet.createRow(rn);rn++;
                cell = row.createCell(0);
                cell.setCellValue("Модель");setMergedAndBorder(rn-1, rn, 0, 0,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(1);
                cell.setCellValue("гос №");setMergedAndBorder(rn-1, rn, 1, 1,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(2);
                cell.setCellValue("гв");setMergedAndBorder(rn-1, rn, 2, 2,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(3);
                cell.setCellValue("служба");setMergedAndBorder(rn-1, rn, 3, 3,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(4);
                cell.setCellValue("Марка ГСМ");sheet.autoSizeColumn(4);setMergedAndBorder(rn-1, rn, 4, 4,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(5);
                cell.setCellValue("базовая норма");sheet.autoSizeColumn(5);setMergedAndBorder(rn-1, rn, 5, 5,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(6);
                cell.setCellValue("% за населенность");sheet.autoSizeColumn(6);setMergedAndBorder(rn-1, rn, 6, 6,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(7);
                cell.setCellValue("% за нижнегорье");sheet.autoSizeColumn(7);setMergedAndBorder(rn-1, rn, 7, 7,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(8);
                cell.setCellValue("% за старение");sheet.autoSizeColumn(8);setMergedAndBorder(rn-1, rn, 8, 8,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(9);
                cell.setCellValue("дополнительный %");sheet.autoSizeColumn(9);setMergedAndBorder(rn-1, rn, 9, 9,sheet,true);
                cell.setCellStyle(styleHeader);

                cell = row.createCell(10);
                cell.setCellValue("Линейные нормы, л");
                cell.setCellStyle(styleHeader);

                cell = row.createCell(17);
                cell.setCellValue("Лимит пробега");sheet.autoSizeColumn(17);setMergedAndBorder(rn-1, rn, 17, 17,sheet,true);
                cell.setCellStyle(styleHeader);

                cell = row.createCell(18);
                cell.setCellValue("потребность, л");
                cell.setCellStyle(styleHeader);

                setMergedAndBorder(rn-1, rn-1, 10, 16,sheet,true);
                setMergedAndBorder(rn-1, rn-1, 18, 29,sheet,true);

                row = sheet.createRow(rn);rn++;

                cell = row.createCell(10);
                cell.setCellValue("летняя");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(11);
                cell.setCellValue("ноябрь");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(12);
                cell.setCellValue("декабрь");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(13);
                cell.setCellValue("январь");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(14);
                cell.setCellValue("февраль");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(15);
                cell.setCellValue("март");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(16);
                cell.setCellValue("апрель");
                cell.setCellStyle(styleHeader);

                cell = row.createCell(18);
                cell.setCellValue("летняя");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(19);
                cell.setCellValue("ноябрь");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(20);
                cell.setCellValue("декабрь");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(21);
                cell.setCellValue("январь");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(22);
                cell.setCellValue("февраль");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(23);
                cell.setCellValue("март");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(24);
                cell.setCellValue("апрель");
                cell.setCellStyle(styleHeader);

                cell = row.createCell(25);
                cell.setCellValue("1 кв-л");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(26);
                cell.setCellValue("2 кв-л");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(27);
                cell.setCellValue("3 кв-л");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(28);
                cell.setCellValue("4 кв-л");
                cell.setCellStyle(styleHeader);
                cell = row.createCell(29);
                cell.setCellValue("год");
                cell.setCellStyle(styleHeader);



            }
            row = sheet.createRow(rn);
            rn++;

            cell = row.createCell(0);
            String value = linNormGSMModel.getModel();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);

            cell = row.createCell(1);
            value = linNormGSMModel.getNumb_1();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(2);
            value = linNormGSMModel.getM_year();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(3);
            value = linNormGSMModel.getGroup_used();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(4);
            value = linNormGSMModel.getMark_gsm();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);

            cell = row.createCell(5);
            cell.setCellValue(linNormGSMModel.getNorm());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(6);
            cell.setCellValue(linNormGSMModel.getNasel());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(7);
            cell.setCellValue(linNormGSMModel.getNignegor());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(8);
            cell.setCellValue(linNormGSMModel.getOld());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(9);
            cell.setCellValue(linNormGSMModel.getProcent_gsm());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(10);
            cell.setCellValue(linNormGSMModel.getLeto());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(11);
            cell.setCellValue(linNormGSMModel.getM0());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(12);
            cell.setCellValue(linNormGSMModel.getM1());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(13);
            cell.setCellValue(linNormGSMModel.getM2());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(14);
            cell.setCellValue(linNormGSMModel.getM3());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(15);
            cell.setCellValue(linNormGSMModel.getM4());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(16);
            cell.setCellValue(linNormGSMModel.getM5());
            cell.setCellStyle(styleBodyNumFloat);



            cell = row.createCell(17);
            cell.setCellValue(linNormGSMModel.getNorma_run());norm+=linNormGSMModel.getNorma_run();all_norm+=linNormGSMModel.getNorma_run();
            cell.setCellStyle(styleBodyNumFloat);

            cell = row.createCell(18);
            cell.setCellValue(linNormGSMModel.getLeto_2());leto2+=linNormGSMModel.getNorma_run();all_leto2+=linNormGSMModel.getNorma_run();
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(19);
            cell.setCellValue(linNormGSMModel.getM0_2());m0_2+=linNormGSMModel.getM0_2();all_m0_2+=linNormGSMModel.getM0_2();
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(20);
            cell.setCellValue(linNormGSMModel.getM1_2());m1_2+=linNormGSMModel.getM1_2();all_m1_2+=linNormGSMModel.getM1_2();
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(21);
            cell.setCellValue(linNormGSMModel.getM2_2());m2_2+=linNormGSMModel.getM2_2();all_m2_2+=linNormGSMModel.getM2_2();
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(22);
            cell.setCellValue(linNormGSMModel.getM3_2());m3_2+=linNormGSMModel.getM3_2();all_m3_2+=linNormGSMModel.getM3_2();
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(23);
            cell.setCellValue(linNormGSMModel.getM4_2());m4_2+=linNormGSMModel.getM4_2();all_m4_2+=linNormGSMModel.getM4_2();
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(24);
            cell.setCellValue(linNormGSMModel.getM5_2());m5_2+=linNormGSMModel.getM5_2();all_m5_2+=linNormGSMModel.getM5_2();
            cell.setCellStyle(styleBodyNumFloat);

            cell = row.createCell(25);
            cell.setCellValue(linNormGSMModel.getKv1());kv1+=linNormGSMModel.getKv1();all_kv1+=linNormGSMModel.getKv1();
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(26);
            cell.setCellValue(linNormGSMModel.getKv2());kv2+=linNormGSMModel.getKv2();all_kv2+=linNormGSMModel.getKv2();
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(27);
            cell.setCellValue(linNormGSMModel.getKv3());kv3+=linNormGSMModel.getKv3();all_kv3+=linNormGSMModel.getKv3();
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(28);
            cell.setCellValue(linNormGSMModel.getKv4());kv4+=linNormGSMModel.getKv4();all_kv4+=linNormGSMModel.getKv4();
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(29);
            cell.setCellValue(linNormGSMModel.getGod());god+=linNormGSMModel.getGod();all_god+=linNormGSMModel.getGod();
            cell.setCellStyle(styleBodyNumFloat);

            count++;
            all_count++;
            all_norm+=linNormGSMModel.getNorma_run();


        }
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue(count);
        cell.setCellStyle(styleBodyNum);
        cell = row.createCell(17);
        cell.setCellValue(norm);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(18);
        cell.setCellValue(leto2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(19);
        cell.setCellValue(m0_2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(20);
        cell.setCellValue(m1_2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(21);
        cell.setCellValue(m2_2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(22);
        cell.setCellValue(m3_2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(23);
        cell.setCellValue(m4_2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(24);
        cell.setCellValue(m5_2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(25);
        cell.setCellValue(kv1);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(26);
        cell.setCellValue(kv2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(27);
        cell.setCellValue(kv3);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(28);
        cell.setCellValue(kv4);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(29);
        cell.setCellValue(god);
        cell.setCellStyle(styleBodyNumFloat);

        rn++;
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue(all_count);
        cell.setCellStyle(styleBodyNum);
        cell = row.createCell(17);
        cell.setCellValue(all_norm);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(18);
        cell.setCellValue(all_leto2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(19);
        cell.setCellValue(all_m0_2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(20);
        cell.setCellValue(all_m1_2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(21);
        cell.setCellValue(all_m2_2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(22);
        cell.setCellValue(all_m3_2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(23);
        cell.setCellValue(m4_2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(24);
        cell.setCellValue(all_m5_2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(25);
        cell.setCellValue(all_kv1);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(26);
        cell.setCellValue(all_kv2);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(27);
        cell.setCellValue(all_kv3);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(28);
        cell.setCellValue(all_kv4);
        cell.setCellStyle(styleBodyNumFloat);
        cell = row.createCell(29);
        cell.setCellValue(all_god);
        cell.setCellStyle(styleBodyNumFloat);


        CellRangeAddress range = new CellRangeAddress(0, 0, 0, 29);
        sheet.addMergedRegion(range);
        for (int i = 0; i <= 3; i++)
            sheet.autoSizeColumn(i);
        for (int i = 10; i <= 16; i++)
            sheet.autoSizeColumn(i);
        for (int i = 18; i <= 29; i++)
            sheet.autoSizeColumn(i);




        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/vnd.ms-excel");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"linnormgsm.xls\"");

        wb.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();
    }

    public void excelLinNormGsm5() throws IOException {
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


        HSSFCellStyle styleCaption = (HSSFCellStyle) wb.createCellStyle();
        fontHeader.setBold(true);
        styleCaption.setFont(fontHeader);
        styleCaption.setAlignment(HorizontalAlignment.CENTER);


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
        cell.setCellStyle(styleCaption);


        int rn = 1;
        String grf = "";
        float count=0;
        float norm=0;


        float all_count=0;
        float all_norm=0;


        for (LinNormGSMModel linNormGSMModel: listLinNormGSMModel) {


            if (!grf.equalsIgnoreCase(linNormGSMModel.getGroupField())) {
                if(!grf.equals(""))
                {
                    row = sheet.createRow(rn);rn++;
                    cell = row.createCell(0);
                    cell.setCellValue(count);
                    cell.setCellStyle(styleBodyNum);
                    cell = row.createCell(20);
                    cell.setCellValue(norm);
                    cell.setCellStyle(styleBodyNumFloat);


                    count=0;
                    norm=0;
                    rn++;
                }

                row = sheet.createRow(rn);
                rn++;
                grf = linNormGSMModel.getGroupField();
                cell = row.createCell(0);
                cell.setCellValue(linNormGSMModel.getDepart());
                cell.setCellStyle(styleHeader2);
                CellRangeAddress range = new CellRangeAddress(rn - 1, rn - 1, 0, 20);
                sheet.addMergedRegion(range);

                row = sheet.createRow(rn);
                rn++;
                cell = row.createCell(0);
                cell.setCellValue(linNormGSMModel.getFin());
                cell.setCellStyle(styleHeader2);
                range = new CellRangeAddress(rn - 1, rn - 1, 0, 20);
                sheet.addMergedRegion(range);


                row = sheet.createRow(rn);rn++;
                cell = row.createCell(0);
                cell.setCellValue("Модель");setMergedAndBorder(rn-1, rn, 0, 0,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(1);
                cell.setCellValue("гос №");setMergedAndBorder(rn-1, rn, 1, 1,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(2);
                cell.setCellValue("гв");setMergedAndBorder(rn-1, rn, 2, 2,sheet,true);
                cell.setCellStyle(styleHeader);

                cell = row.createCell(3);
                cell.setCellValue("модель ДВС");
                sheet.autoSizeColumn(3);
                setMergedAndBorder(rn-1, rn, 3, 3,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(4);
                cell.setCellValue("объем ДВС");
                sheet.autoSizeColumn(4);
                setMergedAndBorder(rn-1, rn, 4, 4,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(5);
                cell.setCellValue("мощность");
                sheet.autoSizeColumn(5);
                setMergedAndBorder(rn-1, rn, 5, 5,sheet,true);
                cell.setCellStyle(styleHeader);


                cell = row.createCell(6);
                cell.setCellValue("группа эксплуатации");
                sheet.autoSizeColumn(6);setMergedAndBorder(rn-1, rn, 6, 6,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(7);
                cell.setCellValue("Марка ГСМ");sheet.autoSizeColumn(7);setMergedAndBorder(rn-1, rn, 7, 7,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(8);
                cell.setCellValue("базовая норма");sheet.autoSizeColumn(8);setMergedAndBorder(rn-1, rn, 8, 8,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(9);
                cell.setCellValue("% за населенность");sheet.autoSizeColumn(9);setMergedAndBorder(rn-1, rn, 9, 9,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(10);
                cell.setCellValue("% за нижнегорье");sheet.autoSizeColumn(10);setMergedAndBorder(rn-1, rn, 10, 10,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(11);
                cell.setCellValue("% за старение");sheet.autoSizeColumn(11);setMergedAndBorder(rn-1, rn, 11, 11,sheet,true);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(12);
                cell.setCellValue("дополнительный %");sheet.autoSizeColumn(12);setMergedAndBorder(rn-1, rn, 12, 12,sheet,true);
                cell.setCellStyle(styleHeader);

                cell = row.createCell(13);
                cell.setCellValue("Линейные нормы, л");
                cell.setCellStyle(styleHeader);

                cell = row.createCell(20);
                cell.setCellValue("Лимит пробега");sheet.autoSizeColumn(20);setMergedAndBorder(rn-1, rn, 20, 20,sheet,true);
                cell.setCellStyle(styleHeader);



                setMergedAndBorder(rn-1, rn-1, 13, 19,sheet,true);


                row = sheet.createRow(rn);rn++;

                cell = row.createCell(13);
                cell.setCellValue("летняя");sheet.autoSizeColumn(13);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(14);
                cell.setCellValue("ноябрь");sheet.autoSizeColumn(14);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(15);
                cell.setCellValue("декабрь");sheet.autoSizeColumn(15);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(16);
                cell.setCellValue("январь");sheet.autoSizeColumn(16);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(17);
                cell.setCellValue("февраль");sheet.autoSizeColumn(17);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(18);
                cell.setCellValue("март");sheet.autoSizeColumn(18);
                cell.setCellStyle(styleHeader);
                cell = row.createCell(19);
                cell.setCellValue("апрель");sheet.autoSizeColumn(19);
                cell.setCellStyle(styleHeader);





            }
            row = sheet.createRow(rn);
            rn++;

            cell = row.createCell(0);
            String value = linNormGSMModel.getModel();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);

            cell = row.createCell(1);
            value = linNormGSMModel.getNumb_1();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(2);
            value = linNormGSMModel.getM_year();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);

            cell = row.createCell(3);
            value = linNormGSMModel.getModelDVC();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(4);
            value = linNormGSMModel.getVolumeDVC();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(5);
            value = linNormGSMModel.getPower_avt();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);

            cell = row.createCell(6);
            value = linNormGSMModel.getGroup_used();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);
            cell = row.createCell(7);
            value = linNormGSMModel.getMark_gsm();
            cell.setCellValue((value != null) ? value.trim() : "");
            cell.setCellStyle(styleBodyNum);

            cell = row.createCell(8);
            cell.setCellValue(linNormGSMModel.getNorm());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(9);
            cell.setCellValue(linNormGSMModel.getNasel());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(10);
            cell.setCellValue(linNormGSMModel.getNignegor());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(11);
            cell.setCellValue(linNormGSMModel.getOld());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(12);
            cell.setCellValue(linNormGSMModel.getProcent_gsm());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(13);
            cell.setCellValue(linNormGSMModel.getLeto());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(14);
            cell.setCellValue(linNormGSMModel.getM0());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(15);
            cell.setCellValue(linNormGSMModel.getM1());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(16);
            cell.setCellValue(linNormGSMModel.getM2());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(17);
            cell.setCellValue(linNormGSMModel.getM3());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(18);
            cell.setCellValue(linNormGSMModel.getM4());
            cell.setCellStyle(styleBodyNumFloat);
            cell = row.createCell(19);
            cell.setCellValue(linNormGSMModel.getM5());
            cell.setCellStyle(styleBodyNumFloat);



            cell = row.createCell(20);
            cell.setCellValue(linNormGSMModel.getNorma_run());norm+=linNormGSMModel.getNorma_run();all_norm+=linNormGSMModel.getNorma_run();
            cell.setCellStyle(styleBodyNumFloat);



            count++;
            all_count++;
            all_norm+=linNormGSMModel.getNorma_run();


        }
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue(count);
        cell.setCellStyle(styleBodyNum);
        cell = row.createCell(20);
        cell.setCellValue(norm);
        cell.setCellStyle(styleBodyNumFloat);


        rn++;
        row = sheet.createRow(rn);rn++;
        cell = row.createCell(0);
        cell.setCellValue(all_count);
        cell.setCellStyle(styleBodyNum);
        cell = row.createCell(20);
        cell.setCellValue(all_norm);
        cell.setCellStyle(styleBodyNumFloat);

        CellRangeAddress range = new CellRangeAddress(0, 0, 0, 20);
        sheet.addMergedRegion(range);

        for (int i = 0; i <= 2; i++)
            sheet.autoSizeColumn(i);




        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/vnd.ms-excel");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"linnormgsm5.xls\"");

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



    public void rep01(int id_region, int id_division, int id_depart, int id_preDepart, int id_serv, int id_PreServ, int id_dislocation, int id_mark, int id_source_bay,
                       int id_source_fin, int id_group,
                       String modelAvt, String numb_1, String numb_2, String m_year1, String m_year2, String vin, String n_engine, String n_body, String n_chassis, String n_pts, String insur,
                       String inAc1, String inAc2, String drD1, String drD2, int id_typetc, int spisat, int fuel_p,
                       int notATX_p, String volumDVC, int typeDVC, int manufactureTC, int spisat_next, int year_selected, int what) {

        this.period = "за " + year_selected;
        this.what = what;

        try {
            try (Connection con = getConnection()) {
                try (CallableStatement cStmt = con.prepareCall("{call ATX_UVD.dbo.GetReportLineNormGSM(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
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
                    cStmt.setInt(28, fuel_p);

                    cStmt.setInt(29, id_dislocation);
                    cStmt.setInt(30, id_PreServ);
                    cStmt.setInt(31, notATX_p);//notATX_p;




                    boolean hadResults = cStmt.execute();
                    if (hadResults) {

                        try (ResultSet rs = cStmt.getResultSet()) {
                            listLinNormGSMModel.clear();
                            while (rs.next()) {
                                listLinNormGSMModel.add(new LinNormGSMModel(rs.getString(1), rs.getString(2),
                                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7),
                                        rs.getFloat(8), rs.getFloat(9),rs.getFloat(10), rs.getFloat(11),
                                        rs.getFloat(12), rs.getFloat(13),rs.getFloat(14), rs.getFloat(15),
                                        rs.getFloat(16), rs.getFloat(17),rs.getFloat(18), rs.getFloat(19),
                                        rs.getFloat(20), rs.getFloat(21),rs.getFloat(22), rs.getFloat(23),
                                        rs.getFloat(24), rs.getFloat(25),rs.getFloat(26), rs.getFloat(27),
                                        rs.getFloat(28), rs.getFloat(29),rs.getFloat(30), rs.getFloat(31),
                                        rs.getFloat(32), rs.getFloat(33),
                                        rs.getString(34), rs.getString(35), rs.getString(36), what));
                            }
                            managerPages.setPage("reports/reportLinNormGsm"+what+".xhtml");
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
