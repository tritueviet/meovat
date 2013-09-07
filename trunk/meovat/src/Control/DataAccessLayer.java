/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import models.Article;
import models.Category;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Vector;
import org.kxml.Xml;
import org.kxml.parser.ParseEvent;
import org.kxml.parser.XmlParser;

/**
 *
 * @author Khoa
 */
public class DataAccessLayer {

    private String FilePath;

    public DataAccessLayer(String filePath) {
        this.FilePath = filePath;
    }

    public Vector getVectorCategories() {
        try {
            XmlParser parser = new XmlParser(getReaderStream(FilePath));

            // starting parser xml to Vector of Categories
            Vector itemList = new Vector();
            String categoryName = "";
            String categoryPath = "";
            String imagePath = "";
            boolean xmlParsingDone = false;

            while (!xmlParsingDone) {
                ParseEvent event = parser.read();
                ParseEvent pe;

                int choice = event.getType();
                switch (choice) {
                    case Xml.START_TAG:
                        // Get category name
                        String tagName = event.getName();
                        if ("CategoryName".equals(tagName)) {
                            pe = parser.read();
                            categoryName = pe.getText();
                        }
                        // Get category path
                        if ("CategoryPath".equals(tagName)) {
                            pe = parser.read();
                            categoryPath = pe.getText();
                        }

                        // Get category image path
                        if ("ImagePath".equals(tagName)) {
                            pe = parser.read();
                            imagePath = pe.getText();

                            //After get category name & path, we will create a new instance and add it into Vector
                            Category cat = new Category(categoryName, categoryPath, imagePath);
                            itemList.addElement(cat);
                        }
                        break;

                    case Xml.END_TAG:
                        break;
                    case Xml.END_DOCUMENT:
                        xmlParsingDone = true;
                        break;
                    case Xml.TEXT:
                        break;
                    case Xml.WHITESPACE:
                        break;
                    default:
                }
            }
            Runtime.getRuntime().freeMemory();
            Runtime.getRuntime().gc();

            if (!itemList.isEmpty()) {
                return itemList;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //parser= null;


        return null;
    }

    // Note: return ONLY title + id, NOT content
    public Vector getTitlesByPath(String subPath) {
        try {
            XmlParser parser = new XmlParser(getReaderStream(subPath));

            // starting parser xml to Vector of Categories
            Vector itemList = new Vector();
            String title = "";
            String id = "";
            boolean xmlParsingDone = false;

            while (!xmlParsingDone) {
                ParseEvent event = parser.read();
                ParseEvent pe;

                int choice = event.getType();
                switch (choice) {
                    case Xml.START_TAG:
                        // Get category name
                        String tagName = event.getName();
                        if ("ID".equals(tagName)) {
                            pe = parser.read();
                            id = pe.getText();
                        }
                        // Get category path
                        if ("Title".equals(tagName)) {
                            pe = parser.read();
                            title = pe.getText();

                            //After get category name & path, we will create a new instance and add it into Vector
                            Article a = new Article(title, id);
                            a.setTitlePath(subPath);

                            itemList.addElement(a);
                        }
                        break;

                    case Xml.END_TAG:
                        break;
                    case Xml.END_DOCUMENT:
                        xmlParsingDone = true;
                        break;
                    case Xml.TEXT:
                        break;
                    case Xml.WHITESPACE:
                        break;
                    default:
                }
            }
            Runtime.getRuntime().freeMemory();
            Runtime.getRuntime().gc();
            if (!itemList.isEmpty()) {
                return itemList;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        Runtime.getRuntime().freeMemory();
//        Runtime.getRuntime().gc();
        return null;
    }

    public Article getArticleById(String subPath, int id) {
        try {
            XmlParser parser = new XmlParser(getReaderStream(subPath));

            // starting parser xml to Vector of Categories
            Vector itemList = new Vector();
            String title = "";
            String content = "";
            boolean xmlParsingDone = false;
            boolean checkPoint = false;

            while (!xmlParsingDone) {
                ParseEvent event = parser.read();
                ParseEvent pe;

                int choice = event.getType();
                switch (choice) {
                    case Xml.START_TAG:
                        // Get category name
                        String tagName = event.getName();
                        if (!checkPoint && "ID".equals(tagName)) {
                            pe = parser.read();

                            // if checkPoint = false => we need to find the artichle which has the same id with parameter AND DON'T NEED TO READ TITLE OR CONTENT (FIND ID FIRST)
                            if (Integer.parseInt(pe.getText()) == id) {
                                checkPoint = true;
                            }
                        }

                        // WHEN CHECKPOINT = TRUE
                        if (checkPoint) {
                            if ("Title".equals(tagName)) {
                                pe = parser.read();
                                title = pe.getText();
                            }

                            if ("Content".equals(tagName)) {
                                pe = parser.read();
                                content = pe.getText();

                                return (new Article(title, content, id + ""));
                            }
                        }

                        break;

                    case Xml.END_TAG:
                        break;
                    case Xml.END_DOCUMENT:
                        xmlParsingDone = true;
                        break;
                    case Xml.TEXT:
                        break;
                    case Xml.WHITESPACE:
                        break;
                    default:
                }
            }
            Runtime.getRuntime().freeMemory();
            Runtime.getRuntime().gc();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        Runtime.getRuntime().freeMemory();
//        Runtime.getRuntime().gc();
        return null;
    }

    public Vector searchByKeyword(String keyword) {
        Vector resultList = new Vector();

        // Scan all data
        //      Get all files path
        Vector cats = getVectorCategories();
        for (int i = 0; i < cats.size(); i++) {
            String tempPath = ((Category) cats.elementAt(i)).getCategoryPath();

            try {
                XmlParser parser = new XmlParser(getReaderStream(tempPath));

                String id = "";
                String title = "";
                boolean xmlParsingDone = false;

                while (!xmlParsingDone) {
                    ParseEvent event = parser.read();
                    ParseEvent pe;

                    int choice = event.getType();
                    switch (choice) {
                        case Xml.START_TAG:
                            // Get category name
                            String tagName = event.getName();
                            if ("ID".equals(tagName)) {
                                pe = parser.read();
                                id = pe.getText();
                            }
                            // Get category path
                            if ("Title".equals(tagName)) {
                                pe = parser.read();
                                title = pe.getText();

                                // check if keyword inside this title
                                if (checkConatin(title, keyword)) {
                                    Article a = new Article(title, id);
                                    a.setTitlePath(tempPath);

                                    resultList.addElement(a);
                                }

                            }
                            break;

                        case Xml.END_TAG:
                            break;
                        case Xml.END_DOCUMENT:
                            xmlParsingDone = true;
                            break;
                        case Xml.TEXT:
                            break;
                        case Xml.WHITESPACE:
                            break;
                        default:
                    }
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        Runtime.getRuntime().freeMemory();
            Runtime.getRuntime().gc();
//        if (!resultList.isEmpty()) {
//            return resultList;
//        }
        
        return resultList;
    }

    /* 
     * Helper method
     */
    // Get reader UTF-8 stream
    private Reader getReaderStream(String filePath) {
        InputStream is;
        try {
            is = getClass().getResourceAsStream(filePath);

            return (new InputStreamReader(is, "UTF-8"));
        } catch (Exception e) {
            System.err.println("Error Khoa: " + e.toString());
        }

        return null;
    }

    private String removeVnese(String vnString) {
        String SPECIAL_CHARACTERS = "àÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬđĐèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆìÌỉỈĩĨíÍịỊòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰýÝỳỲỹỸỷỶyỴ";
        String REPLACEMENTS = "aAaAaAaAaAaAaAaAaAaAaAaAaAaAaAaAaAdDeEeEeEeEeEeEeEeEeEeEeEiIiIiIiIiIoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOuUuUuUuUuUuUuUuUuUuUuUyYyYyYyYyY";

        String newString = vnString.trim().toLowerCase();
        for (int k = 0; k < SPECIAL_CHARACTERS.length(); k++) {
            newString = newString.replace(SPECIAL_CHARACTERS.charAt(k), REPLACEMENTS.charAt(k));

        }
        Runtime.getRuntime().freeMemory();
            Runtime.getRuntime().gc();
        return newString;
    }

    private boolean checkConatin(String text, String keyword) {
        if ((removeVnese(text)).indexOf(keyword) >= 0) {
            return true;
        }
        if (text.indexOf(keyword) >= 0) {
            return true;
        }
        
        return false;
    }
}
