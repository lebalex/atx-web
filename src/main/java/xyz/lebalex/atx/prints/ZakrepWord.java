package xyz.lebalex.atx.prints;

import com.sun.jndi.toolkit.url.Uri;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import xyz.lebalex.atx.AtxBase;
import xyz.lebalex.atx.models.AvtoModel;
import xyz.lebalex.atx.models.RemontZayDifrModel;
import xyz.lebalex.atx.models.ZakrepModel;
import xyz.lebalex.atx.utils.AtxUtils;
import xyz.lebalex.atx.utils.DateHelper;
import xyz.lebalex.atx.utils.FacesUtils;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.apache.poi.hslf.record.RecordTypes.List;

public class ZakrepWord extends AtxBase {
    static Logger logger = Logger.getLogger(ZayvkaWord.class);


    public XWPFDocument zakrepCard(AvtoModel selectedAvto, String mark, String depart, List<ZakrepModel> listZakrepModel) {
        XWPFDocument doc = null;
        try {
            String resourcePath = "/template_zakrep_card.docx";

            Path templatePath = Paths.get(ZayvkaWord.class.getClassLoader().getResource(resourcePath).toURI());
            doc = new XWPFDocument(Files.newInputStream(templatePath));
            String[] result = AtxUtils.getAvtoMarkModelNumber(selectedAvto.getId());

            int count = listZakrepModel.size();

            XWPFTable table = doc.getTables().get(count - 1);
            int position = doc.getPosOfTable(table);
            int summary = doc.getBodyElements().size();

            for (int i = summary - 1; i > position; i--) {
                doc.removeBodyElement(i);
            }
            doc = replaceTextForTable(doc, "HEAD", getSettingData("head_zakr_card"));
            doc = replaceTextForTable(doc, "AVTO", mark + " " + selectedAvto.getModel() + ", " + selectedAvto.getNumb_1());
            doc = replaceTextForTable(doc, "DEPART", depart);

            int idx = 1;
            for (ZakrepModel x : listZakrepModel) {

                doc = replaceTextForTable(doc, "FIO" + getLitter(idx), x.getFio());
                doc = replaceTextForTable(doc, "NUM" + getLitter(idx), x.getPrikaz());
                idx++;
            }



            /*doc = replaceTextFor(doc, "ZN", Integer.toString(selectedAvto.getSelectedRemontZay().getNum()));
            doc = replaceTextFor(doc, "DATE", DateHelper.DateToString(selectedAvto.getSelectedRemontZay().getDate(),"dd.MM.yyyy"));
            doc = replaceTextFor(doc, "AVTO", mark+" "+selectedAvto.getModel());
            doc = replaceTextFor(doc, "GOSN", selectedAvto.getNumb_1());*/

        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return doc;
    }


    public XWPFDocument zakrepSpravka(AvtoModel selectedAvto, String mark, String depart, List<ZakrepModel> listZakrepModel) {
        XWPFDocument doc = null;
        try {
            String resourcePath = "/template_zakrep_spravka.docx";

            Path templatePath = Paths.get(ZayvkaWord.class.getClassLoader().getResource(resourcePath).toURI());
            doc = new XWPFDocument(Files.newInputStream(templatePath));
            String[] result = AtxUtils.getAvtoMarkModelNumber(selectedAvto.getId());

            doc = replaceTextFor(doc, "HEAD", getSettingData("head_zakr_card"));
            doc = replaceTextForTable(doc, "AVTO", mark + " " + selectedAvto.getModel() + ", " + selectedAvto.getNumb_1());
            doc = replaceTextForTable(doc, "DEPART", depart);
            doc = replaceTextForTable(doc, "DOLG", getSettingData("footer_zakr_card"));

            int idx = 1;
            for (ZakrepModel x : listZakrepModel) {

                doc = replaceTextForTable(doc, "FIO" + idx, x.getFio());
                doc = replaceTextForTable(doc, "NUM" + idx, "Приказ № " + x.getPrikaz() + " от " + DateHelper.DateToString(x.getDate_prikaz(), "dd.MM.yyyy"));
                idx++;
            }
            for (int i = 2; i < 6; i++) {
                doc = replaceTextForTable(doc, "FIO" + i, "");
                doc = replaceTextForTable(doc, "NUM" + i, "");
            }

        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return doc;
    }

    public XWPFDocument zakrepAkt(AvtoModel selectedAvto, String mark) {
        XWPFDocument doc = null;
        try {
            String resourcePath = "/template_act_zakrep.docx";

            Path templatePath = Paths.get(ZayvkaWord.class.getClassLoader().getResource(resourcePath).toURI());
            doc = new XWPFDocument(Files.newInputStream(templatePath));
            String[] result = AtxUtils.getAvtoMarkModelNumber(selectedAvto.getId());

            doc = replaceTextForTable(doc, "HEAD", getSettingData("head_act"));
            doc = replaceTextForTable(doc, "FIO", getSettingData("head_act_name"));

            doc = replaceTextForTable(doc, "AVTO", mark + " " + selectedAvto.getModel() + ", " + selectedAvto.getNumb_1());
            doc = replaceTextForTable(doc, "VIN", selectedAvto.getVin());
            doc = replaceTextForTable(doc, "INV", selectedAvto.getInv_b());
            doc = replaceTextForTable(doc, "GOSZ", selectedAvto.getNumb_1());
            doc = replaceTextForTable(doc, "YEAR", selectedAvto.getM_year());
            doc = replaceTextForTable(doc, "PTS", selectedAvto.getN_pts() + " от " + DateHelper.DateToString(selectedAvto.getDate_texpasport(), "dd.MM.yyyy"));
            doc = replaceTextForTable(doc, "DV", selectedAvto.getN_engine());
            doc = replaceTextForTable(doc, "KUZ", selectedAvto.getN_body());
            doc = replaceTextForTable(doc, "SHASSI", selectedAvto.getN_chassis());
            doc = replaceTextForTable(doc, "OSAGO", selectedAvto.getInsur() + " от " + DateHelper.DateToString(selectedAvto.getDate_insur(), "dd.MM.yyyy"));


        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return doc;
    }

    public XWPFDocument printAvto(AvtoModel selectedAvto, String mark, String reg, String div, String dep, String predep, String ser
            , String preser, String dis, String group, String typebody, String tech, String fin_b, String fin_f, String insCompany, List<String> lastZakrep) {
        XWPFDocument doc = null;
        try {
            String resourcePath = "/template_avt.docx";

            Path templatePath = Paths.get(ZayvkaWord.class.getClassLoader().getResource(resourcePath).toURI());
            doc = new XWPFDocument(Files.newInputStream(templatePath));
            String[] result = AtxUtils.getAvtoMarkModelNumber(selectedAvto.getId());

            doc = replaceTextFor(doc, "AVTO", mark + " " + selectedAvto.getModel() + ", " + selectedAvto.getNumb_1());
            doc = replaceTextForTable(doc, "REG", reg);
            doc = replaceTextForTable(doc, "DIV", div);
            doc = replaceTextForTable(doc, "DEP", dep);
            doc = replaceTextForTable(doc, "PREDE", predep);
            doc = replaceTextForTable(doc, "SER", ser);
            doc = replaceTextForTable(doc, "PRESE", preser);
            doc = replaceTextForTable(doc, "DIS", dis);

            doc = replaceTextForTable(doc, "MARK", mark);
            doc = replaceTextForTable(doc, "MODEL", selectedAvto.getModel());
            doc = replaceTextForTable(doc, "GOSZ", selectedAvto.getNumb_1());
            doc = replaceTextForTable(doc, "GOSS", selectedAvto.getNumb_2());
            doc = replaceTextForTable(doc, "YEAR", selectedAvto.getM_year());
            doc = replaceTextForTable(doc, "COLOR", selectedAvto.getColor());
            doc = replaceTextForTable(doc, "VIN", selectedAvto.getVin());
            doc = replaceTextForTable(doc, "DV", selectedAvto.getN_engine());
            doc = replaceTextForTable(doc, "KUZ", selectedAvto.getN_body());
            doc = replaceTextForTable(doc, "SHASSI", selectedAvto.getN_chassis());
            doc = replaceTextForTable(doc, "PREDGOSZ", selectedAvto.getOld_number());
            doc = replaceTextForTable(doc, "POWER", selectedAvto.getPower_energy());
            doc = replaceTextForTable(doc, "PTS", selectedAvto.getN_pts());
            doc = replaceTextForTable(doc, "DATEP", DateHelper.DateToString(selectedAvto.getDate_texpasport(), "dd.MM.yyyy"));
            doc = replaceTextForTable(doc, "TYPE", typebody);
            doc = replaceTextForTable(doc, "TECH", tech);
            doc = replaceTextForTable(doc, "GROUP", group);
            doc = replaceTextForTable(doc, "DATEU", DateHelper.DateToString(selectedAvto.getIn_account(), "dd.MM.yyyy"));
            doc = replaceTextForTable(doc, "PRIKAZ", selectedAvto.getIn_pr());

            doc = replaceTextForTable(doc, "FINP", fin_b);
            doc = replaceTextForTable(doc, "FINS", fin_f);
            doc = replaceTextForTable(doc, "INVB", selectedAvto.getInv_b());
            doc = replaceTextForTable(doc, "INVT", selectedAvto.getInv_t());
            doc = replaceTextForTable(doc, "RADIO", selectedAvto.getRadio());
            doc = replaceTextForTable(doc, "SGU", selectedAvto.getSgu());
            doc = replaceTextForTable(doc, "PLACE", selectedAvto.getPlase_sto_address());
            if(selectedAvto.getPlase_sto_date()!=null)
                doc = replaceTextForTable(doc, "NRDATE", selectedAvto.getPlase_sto_prikaz()+ " от "+DateHelper.DateToString(selectedAvto.getPlase_sto_date(), "dd.MM.yyyy"));
            else
                doc = replaceTextForTable(doc, "NRDATE", " ");
            if(selectedAvto.getDate_insur()!=null)
                doc = replaceTextForTable(doc, "INSURE", selectedAvto.getInsur()+ " от "+DateHelper.DateToString(selectedAvto.getDate_insur(), "dd.MM.yyyy"));
            else
                doc = replaceTextForTable(doc, "INSURE", " ");
            doc = replaceTextForTable(doc, "OSAGO", insCompany);

            doc = replaceTextForTableListString(doc, "ZAKREP", lastZakrep);




        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return doc;
    }

    private XWPFDocument replaceTextFor(XWPFDocument doc, String findText, String replaceText) {
        doc.getParagraphs().forEach(p -> {
            p.getRuns().forEach(run -> {
                String text = run.text();
                if (text.contains(findText)) {
                    run.setText(text.replace(findText, (replaceText == null) ? "" : replaceText), 0);
                }
            });
        });

        return doc;
    }

    private XWPFDocument replaceTextForTable(XWPFDocument doc, String findText, String replaceText) {
        doc.getTables().forEach(t -> {
            t.getRows().forEach(r -> {
                r.getTableCells().forEach(c -> {
                    c.getParagraphs().forEach(p -> {
                        p.getRuns().forEach(run -> {
                            String text = run.text();
                            //logger.log(Level.INFO, text);
                            if (text.contains(findText)) {
                                run.setText(text.replace(findText, (replaceText == null) ? "" : replaceText), 0);
                            }
                        });
                    });
                });
            });
        });

        return doc;
    }
    private XWPFDocument replaceTextForTableListString(XWPFDocument doc, String findText, java.util.List<String> replaceList)
        {
            doc.getTables().forEach(t -> {
                t.getRows().forEach(r -> {
                    r.getTableCells().forEach(c -> {
                        c.getParagraphs().forEach(p -> {
                            p.getRuns().forEach(run -> {
                                String text = run.text();
                                if (text.contains(findText)) {
                                    run.setText(text.replace(findText, ""), 0);
                                    for (String difr : replaceList) {
                                        run.setText(difr);
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
    private XWPFDocument replaceTextForTableList(XWPFDocument doc, String findText, java.util.List<RemontZayDifrModel> replaceList)
        {
            doc.getTables().forEach(t -> {
                t.getRows().forEach(r -> {
                    r.getTableCells().forEach(c -> {
                        c.getParagraphs().forEach(p -> {
                            p.getRuns().forEach(run -> {
                                String text = run.text();
                                if (text.contains(findText)) {
                                    run.setText(text.replace(findText, ""), 0);
                                    for (RemontZayDifrModel difr : replaceList) {
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
}
