package test;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import page.HomePage;
import page.StorePage;
import core.Common;

public class Store_TestCase extends Common{
	
	//initialize browser
	@BeforeMethod
	public void init()
	{
		initBrowser();
	}
	
	//TC034: Check list of store that displayed default
	@Test
	public void checkStoreListDefault()
	{
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
		
		StorePage store = homePage.clickStoreInMenu();
		
        store.selectDropDown("tinhthanhID","TP.HCM");
        store.ajax();
        
        int expectedTotal = 84;
        // get total store of TP.HCM
        int actualTotal = store.getRows() - 1;
        
        //compare 
        if(expectedTotal == actualTotal){
        	assert(true);
        }else{
        	assert(false);
        }
	}
	
	//TC035: Check list of store after selecting 1 province/city
	@Test
	public void checkStoreList()
	{
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
		
		StorePage store = homePage.clickStoreInMenu();
		
        store.selectDropDown("tinhthanhID","Cà Mau");
        store.ajax();
        
        //set
		List<String> myList1 = new ArrayList<String>() {{
		    add("01");
		    add("Lotteria Ca Mau" + "\n" + "Nguyen Kim Trade Center, 1-2 Hung Vuong, Ward 7, Ca Mau City");
		    add("6:30 AM – 11:30 PM");
		    add("0780.357.0527");
		}};
		List<String> myList2 = new ArrayList<String>() {{
		    add("02");
		    add("Lotteria Tran Hung Dao" + "\n" + "29 Tran Hung Dao Str, Ward 5, Ca Mau City");
		    add("7:00 AM – 10:30 PM");
		    add("07803.562.582");
		}};
       
        List<List<String>> storeList = new ArrayList<List<String>>();
        
        storeList.add(myList1);
        storeList.add(myList2);
        
        // get store list result
        List<List<String>> actualStoreList = store.getDataTable();
        
        //compare array 2 demention
        if(storeList.size() == actualStoreList.size()){
			if(storeList.equals(actualStoreList)){
	        	 assert(true);
	        }
	        else{
	        	 assert(false);
	        }
		}else{
			 assert(false);
		}
        // get size of store list
       
	}
	
	//TC036: Verify list of store that display when select 1 Province/City
	@Test
	public void checkStoreList_District()
	{
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
		
		StorePage store = homePage.clickStoreInMenu();
		
        store.selectDropDown("tinhthanhID","Hà Nội");
        store.ajax();
        store.selectDropDown("quan","Cầu Giấy");
       
        // Get the list of district corresponding to the Province/City
        List<String> listOfDistrict = store.getListDistrict();
		
        // get store list result
        List<List<String>> actualStoreList = store.getDataTable();
        
        // get address column from dynamic table result
        List<String> actualAdress = store.getAddress();
        
        
        String district = "Cau Giay";
        //String disSearch = district.toUpperCase();
        // get expected data row 
        
        int x = store.getRows();
        int rowResult = x - 1;
        //compare 
        int count = 0;
        for(int i=0; i< actualAdress.size();i++)
        {
        	if(actualAdress.get(i).contains(district)){
        		count++;
        	}
        }
        if(count == rowResult){
        	assert(true);
        }else{
        	assert(false);
        }
       
	}

	@Test
	public void test_ajaxCalls()
	{
		
	}
	
	// closes browser
	@AfterMethod
	public void afterTest()
	{
		dr.close();
	}
}

