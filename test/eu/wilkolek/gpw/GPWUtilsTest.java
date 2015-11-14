package eu.wilkolek.gpw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Slishu
 */
public class GPWUtilsTest {

    public GPWUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(expected = GPWSourceException.class)
    public void dataNotDownloaded() throws GPWSourceException {
        GPWUtils util = GPWUtils.getInstance();
        util.setStringSource(null);
        util.getData();
    }

    @Test
    public void mainTest() throws GPWSourceException {
        GPWUtils util = GPWUtils.getInstance();
        util.downloadData();
        List<GPWRecord> records = util.getData();

        assertNotNull("Record list is null", records);
        assertTrue("More than 0 records", records.size() > 0);
        System.out.println("Records count: " + records.size());
    }

    @Test
    public void contentTest() throws GPWSourceException {
        GPWUtils util = GPWUtils.getInstance();
        util.downloadData();
        List<GPWRecord> records = util.getData();

        for (GPWRecord record : records) {
            testRecord(record);
        }
    }

    
    @Test
    public void getSampleDataTest() throws GPWSourceException {
        List<GPWRecord> sampleData = GPWUtils.getInstance().getSampleData();
        assertNotNull(sampleData);
        assertEquals(sampleData.size(), 433);
    }
    
    @Test
    public void setStringSourceAndroidTest() throws GPWSourceException, IOException {
        File f = new File("resources/sampleResponse.txt");
        FileInputStream fis = new FileInputStream(f);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
        String internetData = "";
        String line;
        while ((line = br.readLine()) != null) {
            internetData += line;
        }
        
        GPWUtils.getInstance().setStringSource(internetData);
        List<GPWRecord> records = GPWUtils.getInstance().getData();
        assertNotNull(records);
        assertTrue(records.size() > 0);
    }

    @Test
    public void getUriTest() {
        URI uri = GPWUtils.getInstance().getURI();
    }

    private void testRecord(GPWRecord record) {
        assertNotNull("Record is null", record);

        assertNotNull("Valor is null " + getInfo(record), record.getValor());
        assertNotNull("Shortcut is null " + getInfo(record), record.getShortcut());
//        assertNotNull("Rate is null " + getInfo(record), record.getRate()); 
//        assertNotNull("Change is null " + getInfo(record), record.getChange());

    }

    private String getInfo(GPWRecord record) {
        return new String("{ " + "shortcut: " + record.getShortcut() + ", " + "valor: " + record.getValor() + " }");
    }

}
