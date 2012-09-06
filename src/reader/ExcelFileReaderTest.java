package reader;

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

import reader.ExcellFileReader;

import converter.domain.VerizonDLDiscPhoneData;
import converter.service.PhoneService;

public class ExcelFileReaderTest {
	static String fileName = "C:\\DL_phones.xls";
	private ExcellFileReader reader = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		reader = new ExcellFileReader();
	}

	@Test
	public void testGetPOIFSFileSystem(){
		POIFSFileSystem poiFile = reader.getPOIFSFileSystem(fileName);
		System.out.println("poiFile =" +poiFile.toString());
		assertNotNull(poiFile);
		
	}
	
	@Test
	public void testGetHSSFSheet(){
		POIFSFileSystem poiFile = reader.getPOIFSFileSystem(fileName);
		HSSFSheet hssfSheet = null;
		try{
		   hssfSheet = reader.getHSSFSheet(poiFile);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("work sheet =" +hssfSheet.toString());
		assertNotNull(hssfSheet);
	}		
	
	
	@Test
	public void testReadDataFromWorksheet() {		
		 POIFSFileSystem poiFile = reader.getPOIFSFileSystem(fileName);
		 HSSFSheet workSheet = reader.getHSSFSheet(poiFile);
		 Set<Set<VerizonDLDiscPhoneData>> dataHolder = reader.readDataFromWorksheet(workSheet);           
		 reader.printDataToConsole(dataHolder);
		 assertNotNull(dataHolder);        
	}

	@Test
	public void testReaderPerformance(){
	  long startTime = System.currentTimeMillis();
	  testReadDataFromWorksheet();
	  long totalTime = System.currentTimeMillis() - startTime;
	  System.out.println("Total time used = " + totalTime);	 
	      
	}
	@After
	public void tearDown() throws Exception {
	}
}
