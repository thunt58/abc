package log4j;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import page.FranchisingPage;
import page.HomePage;
import page.MenuPage;
import page.SearchPopupPage;
import page.StorePage;
import core.Common;


public class Store extends Common{
	private static Logger Log = Logger.getLogger(Log4j.class.getName());
	// contructor franchising
	public Store(WebDriver driver)
	{
		dr = driver;
	}
	
	public static void main(String[] args) {
		Store s = new Store(dr);
		DOMConfigurator.configure("log4j.xml");
		
		s.initBrowser();
        Log.info("Open browser");
        
        HomePage homePage = new HomePage(dr);
        homePage.EN_Switch();
        
        StorePage store = homePage.clickStoreInMenu();
        Log.info(store.getURL());
        
       
        store.selectDropDown("tinhthanhID","Hà Nội");
        store.ajax();
        store.selectDropDown("quan","Long Biên");
        
        List<String> list = store.getListDistrict();
        //Log.info(list);
        
        WebElement myTable = dr.findElement(By.xpath(".//*[@id='middle']//table[@class='list-store desk']"));
		
		// To locate rows of table
		List<WebElement> rows_table = myTable.findElements(By.tagName("tr"));
		
		// To calculate no of rows in table
		int rows_count = rows_table.size();
	
		
		List<List<String>> outLists = new ArrayList<List<String>>();
		
		//List<String> rowValue = new ArrayList<String>(); 
		
		// Loop will execute till the last row of table
//		for(int row=1; row<rows_count; row++)
//		{
//			// To locate colums(cells) of that specific row
//			int index = row+1;
//			String xpath = ".//*[@id='middle']//table[@class='list-store desk']/tbody/tr[" + index + "]/td";
//			
//			List<WebElement> colums_row = dr.findElements(By.xpath(xpath));
//			
//			// To locate no of colums(cells) in that specific row
//			int colums_count = colums_row.size();
//				List<String> rowValue = new ArrayList<String>();
//				
//				for(int column=1; column<=colums_count; column++ ){
//					String xpathCell = ".//*[@id='middle']//tbody/tr[" + index + "]/td[" + column + "]";
//					String celltext = dr.findElement(By.xpath(xpathCell)).getText();
//
//					rowValue.add(celltext);
//				}
//				
//				outLists.add(rowValue);	
//		}
//		
//		
//		List<String> myList1 = new ArrayList<String>() {{
//		    add("01");
//		    add("Lotteria Ca Mau" + "\n" + "Nguyen Kim Trade Center, 1-2 Hung Vuong, Ward 7, Ca Mau City");
//		    add("6:30 AM – 11:30 PM");
//		    add("0780.357.0527");
//		}};
//		List<String> myList2 = new ArrayList<String>() {{
//		    add("02");
//		    add("Lotteria Tran Hung Dao" + "\n" + "29 Tran Hung Dao Str, Ward 5, Ca Mau City");
//		    add("7:00 AM – 10:30 PM");
//		    add("07803.562.582");
//		}};
//       
//        List<List<String>> storeList = new ArrayList<List<String>>();
//        
//        storeList.add(myList1);
//        storeList.add(myList2);
        
	//	List<List<String>> actualStoreList = store.getDataTable();
//		Log.info(outLists.size());
//		Log.info(outLists);
//		Log.info(storeList);
		
		
        // get store list result
        List<List<String>> actualStoreList = store.getDataTable();
        
        // get address column from dynamic table result
        List<String> actualAdress = store.getAddress();
        Log.info(actualAdress);
        
        String district = "Long Bien";
        //String disSearch = district.toUpperCase();
        // get expected data row 
        
        int x = store.getRows();
        int rowResult = x - 1;
        Log.info(rowResult);
        //compare 
        int count = 0;
        for(int i=0; i< actualAdress.size();i++)
        {
        	if(actualAdress.get(i).contains(district)){
        		count++;
        	}
        }
        if(count == rowResult){
        	Log.info("true");
        }else{
        	Log.info("false");
        }
//		for(int i=0;i<outLists.size();i++)
//		{
//			for(int j=0;j<outLists.get(i).size();j++)
//			{
//				Log.info(outLists.get(i).get(j));
//			}
//		}

		//Log.info("size của actual "+actualStoreList.size());
//		for(int i=0;i<actualStoreList.size();i++)
//		{
//			for(int j=0;j<actualStoreList.get(i).size();j++)
//			{
//				Log.info(actualStoreList.get(i).get(j));
//			}
//		}
		//List<List<String>> actualStoreList = store.getDataTable();
//		Log.info("size của expected "+storeList.size());
//		
//		if(storeList.size() == outLists.size()){
//			if(storeList.equals(outLists)){
//	        	 Log.info("true");
//	        }
//	        else{
//	        	 Log.info("false");
//	        }
//		}else{
//			 Log.info("false");
//		}
//        
	}
		
}
