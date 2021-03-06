package xyz.lebalex.atx.prints;

import com.sun.jndi.toolkit.url.Uri;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.*;
import xyz.lebalex.atx.models.AvtoModel;
import xyz.lebalex.atx.models.RemontZayDifrModel;
import xyz.lebalex.atx.utils.AtxUtils;
import xyz.lebalex.atx.utils.DateHelper;
import xyz.lebalex.atx.utils.FacesUtils;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.apache.poi.hslf.record.RecordTypes.List;

public class ZayvkaWord {
    static Logger logger = Logger.getLogger(ZayvkaWord.class);


    public XWPFDocument  zayvkaAct(AvtoModel selectedAvto, String mark){
        XWPFDocument  doc = null;
        try {
            String resourcePath = "/template_act.docx";
            /*URL url = this.getClass().getResource(resourcePath);
            String a = this.getClass().getResource(resourcePath).toURI().toString();*/

            Path templatePath = Paths.get(ZayvkaWord.class.getClassLoader().getResource(resourcePath).toURI());
            doc = new XWPFDocument (Files.newInputStream(templatePath));
            String[] result = AtxUtils.getAvtoMarkModelNumber(selectedAvto.getId());

            doc = replaceTextFor(doc, "ZN", Integer.toString(selectedAvto.getSelectedRemontZay().getNum()));
            doc = replaceTextFor(doc, "DATE", DateHelper.DateToString(selectedAvto.getSelectedRemontZay().getDate(),"dd.MM.yyyy"));
            doc = replaceTextFor(doc, "AVTO", mark+" "+selectedAvto.getModel());
            doc = replaceTextFor(doc, "GOSN", selectedAvto.getNumb_1());

        }catch(Exception e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return doc;
    }
    public XWPFDocument  zayvkaPrint(AvtoModel selectedAvto, String mark, String ovd, String group){
        XWPFDocument  doc = null;
        try {
            String resourcePath = "/template_zay.docx";

            Path templatePath = Paths.get(ZayvkaWord.class.getClassLoader().getResource(resourcePath).toURI());
            doc = new XWPFDocument (Files.newInputStream(templatePath));
            String[] result = AtxUtils.getAvtoMarkModelNumber(selectedAvto.getId());

            doc = replaceTextFor(doc, "NUM", Integer.toString(selectedAvto.getSelectedRemontZay().getNum()));
            doc = replaceTextForTable(doc, "ORG", selectedAvto.getSelectedRemontZay().getOrg());
            doc = replaceTextForTable(doc, "OVD", ovd);
            doc = replaceTextForTable(doc, "GROUP", group);
            doc = replaceTextForTable(doc, "MARKMODEL", mark+" "+selectedAvto.getModel());
            doc = replaceTextForTable(doc, "GOSZ", selectedAvto.getNumb_1());
            doc = replaceTextForTable(doc, "NDV", selectedAvto.getN_engine());
            doc = replaceTextForTable(doc, "NKUZ", selectedAvto.getN_body());
            doc = replaceTextForTable(doc, "SHASSI", selectedAvto.getN_chassis());
            doc = replaceTextForTable(doc, "VVN", selectedAvto.getVin());
            doc = replaceTextForTable(doc, "INV", ((selectedAvto.getInv_t()==null)?"":selectedAvto.getInv_t())+" "+((selectedAvto.getInv_b()==null)?"":selectedAvto.getInv_b()));
            doc = replaceTextForTable(doc, "SPEED", selectedAvto.getNumb_1());
            doc = replaceTextForTable(doc, "COLOR", selectedAvto.getColor());
            doc = replaceTextForTable(doc, "VODIT", selectedAvto.getSelectedRemontZay().getFio());
            doc = replaceTextForTable(doc, "DATE", DateHelper.DateToString(selectedAvto.getSelectedRemontZay().getDate(),"dd.MM.yyyy"));

            doc = replaceTextForTable(doc, "VIDTO", selectedAvto.getSelectedRemontZay().getVid_to());
            doc = replaceTextForTable(doc, "GRAFF", (selectedAvto.getSelectedRemontZay().getGraf()==0)?"По графику":"С нарушентем");


            doc = replaceTextForTableList(doc, "TEXT", selectedAvto.getSelectedRemontZay().getListRemontZayDifrModel());



        }catch(Exception e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return doc;
    }
    private XWPFDocument replaceTextFor(XWPFDocument doc, String findText, String replaceText){
        doc.getParagraphs().forEach(p ->{
            p.getRuns().forEach(run -> {
                String text = run.text();
                if(text.contains(findText)) {
                    run.setText(text.replace(findText, (replaceText==null)?"":replaceText), 0);
                }
            });
        });

        return doc;
    }
    private XWPFDocument replaceTextForTable(XWPFDocument doc, String findText, String replaceText){
        doc.getTables().forEach(t ->{
            t.getRows().forEach(r -> {
                r.getTableCells().forEach(c -> {
                    c.getParagraphs().forEach(p ->{
                        p.getRuns().forEach(run -> {
                            String text = run.text();
                            if(text.contains(findText)) {
                                run.setText(text.replace(findText, (replaceText==null)?"":replaceText), 0);
                            }
                        });
                    });
                });
            });
        });

        return doc;
    }
        private XWPFDocument replaceTextForTableList(XWPFDocument doc, String findText, java.util.List<RemontZayDifrModel> replaceList){
            {
                doc.getTables().forEach(t ->{
                    t.getRows().forEach(r -> {
                        r.getTableCells().forEach(c -> {
                            c.getParagraphs().forEach(p ->{
                                p.getRuns().forEach(run -> {
                                    String text = run.text();
                                    if(text.contains(findText)) {
                                        run.setText(text.replace(findText, ""), 0);
                                        for(RemontZayDifrModel difr: replaceList) {
                                            run.setText(difr.getZay());
                                            run.addBreak();
                                        }
                                    }
                                });
                            });
                        });
                    });
                });

                return doc;
            }
        /*for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);
                            if (text != null && text.contains("needle")) {
                                text = text.replace(findText, replaceText);
                                r.setText(text,0);
                            }
                        }
                    }
                }
            }
        }*/
    }
}
