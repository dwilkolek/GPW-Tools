package eu.wilkolek.gpw;

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

    @Test
    public void mainTest() throws GPWSourceException {
        GPWUtils util = GPWUtils.getInstance();
        util.downloadData();
        List<GPWRecord> records = util.getData();

        assertNotNull("Record list is null", records);
        assertTrue("More than 0 records", records.size() > 0);
        System.out.println("Records count: " + records.size());
    }

    @Test(expected = GPWSourceException.class)
    public void dataNotDownloaded() throws GPWSourceException {
        GPWUtils util = GPWUtils.getInstance();
        util.getData();
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
