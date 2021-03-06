/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.lebalex.atx.utils;

import java.util.Date;
import java.sql.Time;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author ivc_lebedevav
 */
public class DateHelper {
    /**
     * Преобразует дату в строку в необходимом формате
     * 
     * @param date
     * @param cal Дата
     * @param format Необходимый формат даты вида ddmmyyyy
     * @return  Текущая дата строкой
     */
    public static String DateToString(java.sql.Date date, String format) {
        String date_str = "";
        try{
        SimpleDateFormat sd = new SimpleDateFormat(format, myDateFormatSymbols);
        date_str = sd.format(date);
        }catch(Exception e){}
        return date_str;
       
    }
    /*public static String DateToString(java.sql.Date date, String format) {
        String date_str = "";
        try{
        SimpleDateFormat sd = new SimpleDateFormat("dd MMMM yyyy", myDateFormatSymbols );
        date_str = sd.format(date);
        }catch(Exception e){}
        return date_str;
  
    }*/
    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){

        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }
        
    };

    public static String DateToString(java.util.Date date, String format) {

            String date_str = "";
        try {
            SimpleDateFormat sd = new SimpleDateFormat(format);
            date_str = sd.format(date);
        }catch(Exception e){}
            return date_str;


    }

    public static String DateToString(java.sql.Time time, String format) {
        String date_str = "";
        try{SimpleDateFormat sd = new SimpleDateFormat(format);
        date_str = sd.format(time);}catch(Exception e){}
        return date_str;
    }
    public static Time ParseTime(String time)
    {
        try{
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date res = format.parse(time);
        Time t = new Time(res.getTime());
        return t;
        }catch (Exception e)
        {
            return null;
        }
    }
    public static String getDateString(java.sql.Date date)
    {
        Calendar calen = Calendar.getInstance();
        calen.setTime(date);
        return "«"+calen.get(Calendar.DAY_OF_MONTH) + "» " + getMName(calen.get(Calendar.MONTH)) + " " + calen.get(Calendar.YEAR);
    }
    private static String getMName(int i) {
        String a = null;
        switch (i) {
            case 0:
                a = "января";
                break;
            case 1:
                a = "февраля";
                break;
            case 2:
                a = "марта";
                break;
            case 3:
                a = "апреля";
                break;
            case 4:
                a = "мая";
                break;
            case 5:
                a = "июня";
                break;
            case 6:
                a = "июля";
                break;
            case 7:
                a = "августа";
                break;
            case 8:
                a = "сентября";
                break;
            case 9:
                a = "октября";
                break;
            case 10:
                a = "ноября";
                break;
            case 11:
                a = "декабря";
                break;
        }
        return a;
    }
}
