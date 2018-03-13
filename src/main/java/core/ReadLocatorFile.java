package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadLocatorFile {
	
    //method return array 2D
    public String[][] readExcel(String filePath,String fileName,String sheetName) throws IOException
    {
    	//Create an object of File class to open xlsx file
	    File file =  new File(filePath+"\\"+fileName);
	
	    //Create an object of FileInputStream class to read excel file
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook guru99Workbook = null;
	
	    //Find the file extension by splitting file name in substring  and getting only extension name
	    String fileExtensionName = fileName.substring(fileName.indexOf("."));
	
	    //Check condition if the file is xlsx file
	    if(fileExtensionName.equals(".xlsx")){
	
	    //If it is xlsx file then create object of XSSFWorkbook class
	    guru99Workbook = new XSSFWorkbook(inputStream);
	    }
	
	    //Check condition if the file is xls file
	    else if(fileExtensionName.equals(".xls")){
	        //If it is xls file then create object of XSSFWorkbook class
	        guru99Workbook = new HSSFWorkbook(inputStream);
	    }
	
	    //Read sheet inside the workbook by its name
	    Sheet guru99Sheet = guru99Workbook.getSheet(sheetName);
	
	    //Find number of rows in excel file
	    int rowCount = guru99Sheet.getLastRowNum()- guru99Sheet.getFirstRowNum();;
	  
	    //Create a loop over all the rows of excel file to read it
	    //System.out.println(rowCount);
	    int sum = rowCount + 1;
	    //System.out.println(sum);
	  
	    int ci=0;
	    int cj=0;
	    
	    String[][] xpathData = new String[sum][4];
	    for (int i = 0; i < sum; i++,ci++)
	    {
	        Row row = guru99Sheet.getRow(i);
	        cj=0;
	        //System.out.println("hang --- "+i);
	        for(int j=0; j<4; j++,cj++){
	        	//System.out.println("cot-----" + cj);
	        	xpathData[ci][cj] = row.getCell(j).getStringCellValue();
	        	//System.out.println("value:" + xpathData[ci][cj]);
	        }	
	    }
	    return xpathData;
    }
    
    
    // method return data in the form of hash map
    
    public Map<String, Utils> getLocatorList(String[][] xpathData){
    	
    	Map<String, Utils> m = new HashMap<String, Utils>();
	    
		for (int i = 0; i < xpathData.length; i++) {
			
	      //  for(int j=0; j<3; j++){
	        	String key = xpathData[i][0];
	        	String value = xpathData[i][1];
	        	String type = xpathData[i][2];
	        	Utils data = new Utils(value,type);
	        	m.put(key, data);
	        	//System.out.println(xpathData[i][j++]);
	     //   }   
	    } 
    	return m;
    }
    
    
    //Main function is calling readExcel function to read data from excel file
    public static void main(String...strings) throws IOException{
	    //Create an object of ReadGuru99ExcelFile class
    	ReadLocatorFile objExcelFile = new ReadLocatorFile();
	
	    //Prepare the path of excel file
	
	  
	    String filePath = "E:\\Selenium\\test01\\repository";
	   
	    String[][] xpathData = objExcelFile.readExcel(filePath,"Lotteria_locator.xlsx","Common");
	    
	    Map<String, Utils> m = new HashMap<String, Utils>();
	    
		for (int i = 0; i < xpathData.length; i++) {
			
	      //  for(int j=0; j<3; j++){
	        	String key = xpathData[i][0];
	        	String value = xpathData[i][1];
	        	String type = xpathData[i][2];
	        	Utils data = new Utils(value,type);
	        	m.put(key, data);
	        	//System.out.println(xpathData[i][j++]);
	     //   }   
	    } 

		for(Map.Entry<String, Utils> entry:m.entrySet()){    
	        String key=entry.getKey();  
	        Utils b=entry.getValue();  
	        System.out.println(key+" Details:");  
	        System.out.println(b.getValue()+" "+b.getType());   
	    }    
   }

}