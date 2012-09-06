package converter.service;

import static org.junit.Assert.*;

import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import converter.domain.VerizonDLDiscPhoneData;

import reader.ExcellFileReader;

public class PhoneServiceTest {

	private static PhoneService service = null;
	private static String fileName = "C:\\DL_phones.xls";
	private Set<Set<VerizonDLDiscPhoneData>> dataHolder = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("application-context.xml");      	
      	service = (PhoneService)appCtx.getBean("phoneService");
	}

	@Before
	public void setUp() throws Exception {
        ExcellFileReader reader = new ExcellFileReader();        
        POIFSFileSystem poiFile = reader.getPOIFSFileSystem(fileName);
        HSSFSheet hssfSheet = reader.getHSSFSheet(poiFile);         
        dataHolder = reader.readDataFromWorksheet(hssfSheet);
	}

	//@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveAllRecords() {
	 
		assertNotNull(dataHolder);
        try{
       	service.saveAllRecords(dataHolder);
      	 }
	     catch(Exception e){
		    e.printStackTrace();
		    System.out.println("Error occured while creating connection, due to : " + e.getMessage());
	     }	  	     
	}

	@After
	public void tearDown() throws Exception {
	}
}
