package reader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import converter.domain.VerizonDLDiscPhoneData;
import converter.service.PhoneService;

	
public class ExcellFileReader {
		
	static PhoneService service = null;
	    
    public POIFSFileSystem getPOIFSFileSystem(String fileName) {
    	
    	POIFSFileSystem myFileSystem = null;
        try {
            FileInputStream myInput = new FileInputStream(fileName);
            myFileSystem = new POIFSFileSystem(myInput);
        }
        catch(FileNotFoundException fnfe){
        	fnfe.printStackTrace();
        }
        catch(IOException ioe){
        	ioe.printStackTrace();
        }
        return myFileSystem;    
    }        
    
    public HSSFSheet getHSSFSheet(POIFSFileSystem myFileSystem){
    	HSSFSheet mySheet = null;
    	try{
    		HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
    	    mySheet = myWorkBook.getSheetAt(0);
    	}
    	catch(IOException ioe){
        	ioe.printStackTrace();
        }
    	return mySheet;
    }

    public static Set<Set<VerizonDLDiscPhoneData>> readDataFromWorksheet(HSSFSheet mySheet) {
           Set<Set<VerizonDLDiscPhoneData>> cellHolder = new HashSet<Set<VerizonDLDiscPhoneData>>();
           VerizonDLDiscPhoneData phone = null;
           try {
               Iterator rowIter = mySheet.rowIterator();
               while (rowIter.hasNext()) {
            	      phone = new VerizonDLDiscPhoneData();
                      HSSFRow myRow = (HSSFRow) rowIter.next();
                      Iterator cellIter = myRow.cellIterator();
                      Set<VerizonDLDiscPhoneData> cellStoreSet = new HashSet<VerizonDLDiscPhoneData>();
                      while (cellIter.hasNext()) {                    	     
                             HSSFCell myCell = (HSSFCell) cellIter.next();
                             myCell.setCellType(Cell.CELL_TYPE_STRING);
                             if(myCell.getCellNum() ==0) 
                            	phone.setUserName(myCell.getStringCellValue());
                             if(myCell.getCellNum() ==1) 
                               	phone.setAtn(myCell.getStringCellValue());  	
                            
                             cellStoreSet.add(phone);                            
                      }
                      cellHolder.add(cellStoreSet);
              }
         } catch (Exception e) {
                 e.printStackTrace();
         }
         return cellHolder;
   }

    protected static void printDataToConsole(Set<Set<VerizonDLDiscPhoneData>> dataHolder) {
       	for(Set cellStoreSet : dataHolder){
       		String stringCellValue = null;
            //for(int j = 0; j < cellStoreList.size(); j++) {
           	for(Iterator it = cellStoreSet.iterator(); it.hasNext();){
           		VerizonDLDiscPhoneData phone = (VerizonDLDiscPhoneData) it.next();
                stringCellValue = phone.toString();                   
            }   
            System.out.println(stringCellValue + "\t");
       }
    }
    
    public static void main(String[] args) {
     	 long startTime = System.currentTimeMillis();
        String fileName = "C:\\DL_phones.xls";
        ExcellFileReader reader = new ExcellFileReader();
        
        POIFSFileSystem poiFile = reader.getPOIFSFileSystem(fileName);
        HSSFSheet hssfSheet = reader.getHSSFSheet(poiFile);         
        Set<Set<VerizonDLDiscPhoneData>> dataHolder = reader.readDataFromWorksheet(hssfSheet);
                 
   	 reader.initForTest();
        try{
       	service.saveAllRecords(dataHolder);
      	 }
	     catch(Exception e){
		    e.printStackTrace();
		    System.out.println("Error occured while creating connection, due to : " + e.getMessage());
	     }	  
        long totalTime = System.currentTimeMillis() - startTime;
	     System.out.println("Total time used = " + totalTime);
        //printDataToConsole(dataHolder);
        //printCellDataToConsole(dataHolder);                  
   }
   
   private void initForTest() {
     	ApplicationContext appCtx = new ClassPathXmlApplicationContext("application-context.xml");      	
     	service = (PhoneService)appCtx.getBean("phoneService");
   }  
}
	
