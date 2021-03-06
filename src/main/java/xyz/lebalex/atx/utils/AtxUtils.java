/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.lebalex.atx.utils;

import xyz.lebalex.atx.AtxApp;
import xyz.lebalex.atx.AtxBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ivc_lebedevav
 */
public class AtxUtils {
public static void copyFile(File src, File dest) {

         try{
            FileInputStream src2 = new FileInputStream(src);
            FileOutputStream dest2 = new FileOutputStream(dest);
            FileChannel srcChannel = src2.getChannel();
            FileChannel destChannel = dest2.getChannel();
            srcChannel.transferTo(0, srcChannel.size(), destChannel);
            srcChannel.close();
            destChannel.close();
            src2.close();
            dest2.close();
         }catch(IOException e){}
    }
    public static String[] getAvtoMarkModelNumber(int id)
    {
        String mark="",model="",numb = "";
        try {
            AtxApp atxApp = AtxApp.getInstance();
            try (Connection con = atxApp.getConnection()) {
                try (PreparedStatement prst = con.prepareStatement("select m.name, model, numb_1 from tAvto a inner join tMark_avt m on m.id=a.id_mark where a.id=?")) {
                    prst.setInt(1, id);
                    try (ResultSet rs = prst.executeQuery()) {
                        if(rs.next()) {
                            mark = rs.getString("name");
                            model = rs.getString("model");
                            numb = rs.getString("numb_1");
                        }
                    }
                }
            }
        }catch(Exception ex)
        {
            return null;
        }
        return new String[]{mark,model,numb};
    }
}
