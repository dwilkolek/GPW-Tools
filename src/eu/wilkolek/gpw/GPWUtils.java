package eu.wilkolek.gpw;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Slishu
 */
public class GPWUtils {

    private URL url;
    private String internetData;

    private GPWUtils() {
        try {
            url = new URL("http://www.gpw.pl/ajaxindex.php?action=GPWQuotations&start=showTable&tab=all&lang=PL&full=1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    private static GPWUtils instance;

    public static GPWUtils getInstance() {

        if (instance == null) {
            instance = new GPWUtils();
        }
        return instance;

    }

    private URL getUrl() {
        return this.url;
    }

    public void downloadData() throws GPWSourceException {
        try {
            final URLConnection urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            urlConnection.connect();
            final OutputStream outputStream = urlConnection.getOutputStream();

            final InputStream inputStream = urlConnection.getInputStream();
            setStringSource(getStringFromInputStream(inputStream));
        } catch (IOException ex) {
            Logger.getLogger(GPWUtils.class.getName()).log(Level.SEVERE, null, ex);
            throw new GPWSourceException("IO Exception", ex);
        }
    }

    public void setStringSource(String stringData) throws GPWSourceException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;

            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(stringData)));

            Node nl = document.getElementsByTagName("html").item(0).getChildNodes().item(0);
            CharacterData cd = (CharacterData) nl;
            internetData = cd.getData();
            if (internetData != null) {
                internetData = internetData.replace("&nbsp;", "");
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GPWUtils.class.getName()).log(Level.SEVERE, null, ex);
            throw new GPWSourceException("Parse Exception", ex);
        } catch (SAXException ex) {
            Logger.getLogger(GPWUtils.class.getName()).log(Level.SEVERE, null, ex);
            throw new GPWSourceException("SAX Exception", ex);
        } catch (IOException ex) {
            Logger.getLogger(GPWUtils.class.getName()).log(Level.SEVERE, null, ex);
            throw new GPWSourceException("IO Exception", ex);
        }
    }

    public List<GPWRecord> getData() throws GPWSourceException {

        LinkedList<GPWRecord> result = new LinkedList<GPWRecord>();

        NodeList nodeList = this.getNodeList();
        int length = nodeList.getLength();
        for (int i = 0; i < length; i++) {
            GPWRecord record = this.createRecord(nodeList.item(i));
            if (record != null) {
                result.add(record);
//                System.out.println(record.toString());
            }
        }

        return result;
    }

    private NodeList getNodeList() throws GPWSourceException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;

            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(this.internetData)));
            NodeList rows = document.getElementsByTagName("tr");

            return rows;

        } catch (NullPointerException e) {
            throw new GPWSourceException("Data is not downloaded", e);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GPWUtils.class.getName()).log(Level.SEVERE, null, ex);
            throw new GPWSourceException("Parse Exception", ex);
        } catch (SAXException ex) {
            Logger.getLogger(GPWUtils.class.getName()).log(Level.SEVERE, null, ex);
            throw new GPWSourceException("SAX Exception", ex);
        } catch (IOException ex) {
            Logger.getLogger(GPWUtils.class.getName()).log(Level.SEVERE, null, ex);
            throw new GPWSourceException("IO Exception", ex);
        }
    }

    private GPWRecord createRecord(Node item) {
        if ("td" == item.getChildNodes().item(0).getNodeName() && item.getChildNodes().item(3) != null) {
            return new GPWRecord(item);
        }
        return null;
    }

    private String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}
