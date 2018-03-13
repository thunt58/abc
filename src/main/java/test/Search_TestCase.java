package test;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.AboutUsPage;
import page.BratusPage;
import page.ContactPage;
import page.FranchisingPage;
import page.HomePage;
import page.MenuPage;
import page.NewsPage;
import page.RecruitmentPage;
import page.SearchPopupPage;
import page.StorePage;
import core.Common;

public class Search_TestCase extends Common{
	
	//initialize browser
	@BeforeMethod
	public void init()
	{
		initBrowser();
	}
	
	//TC031: Check search no returned data in case type enter
	@Test
	public void search_TypeEnter()
	{
		HomePage homePage2 = new HomePage(dr);
		homePage2.EN_Switch();
		
		SearchPopupPage searchPopupPage2 = homePage2.clickIconSearchMenu();
		searchPopupPage2.inputKeySearch("fhtn");
		
		// go to result search page by click icon
		MenuPage menuPage = searchPopupPage2.typeEnter();
		
		//get actualNotification
		String noti1 = menuPage.getMessage("//*[@id='float-nav']/preceding-sibling::div/div/div[1]");
		String noti2 = menuPage.getMessage("//*[@id='float-nav']/preceding-sibling::div/div/div[2]");
		String noti3 = menuPage.getMessage("//*[@id='float-nav']/preceding-sibling::div/div/div[3]");
		
		// set expectedNotification
		String expectedNoti1 = "Sorry, this information";
		String expectedNoti2 = "CAN NOT BE FOUND";
		String expectedNoti3 = "Please try a different search";
		
		if(noti1.equals(expectedNoti1) && noti2.equals(expectedNoti2) && noti3.equals(expectedNoti3)){
			assert(true);
		}else{
			assert(false);
		}
	}
	
	//TC032: Check search successfully in case click icon search
	@Test
	public void searchHasData()
	{
		HomePage homePage1 = new HomePage(dr);
		homePage1.EN_Switch();
		
		SearchPopupPage searchPopupPage = homePage1.clickIconSearchMenu();
		String strSearchProduct = "shrimp";
		searchPopupPage.inputKeySearch(strSearchProduct);
		
		//tao bien menuPage
		MenuPage menuPage = searchPopupPage.clickIconSearch2();
		
		//get product from product list 
		
		List<MenuPage> productList = menuPage.getProduct();
		
		int m=0;
		for(int i=0; i<productList.size();i++){
			String product = productList.get(i).getProductName();
			if(product.toUpperCase().contains(strSearchProduct.toUpperCase())){
				m = m+1;
			}
		}
		int size = productList.size();
		if(m==size){
			assert(true);
		}else{
			assert(false);
		}
					
	}
	
	//TC033: Check search no returned data in case click icon	
	@Test
	public void searchNoData()
	{
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
		
		SearchPopupPage searchPopupPage1 = homePage.clickIconSearchMenu();
		searchPopupPage1.inputKeySearch("qertyu");
		
		// go to result search page
		MenuPage menuPage = searchPopupPage1.clickIconSearch2();
		
		//get actualNotification
		String noti1 = menuPage.getMessage(".//*[@id='float-nav']/preceding-sibling::div/div/div[1]");
		String noti2 = menuPage.getMessage(".//*[@id='float-nav']/preceding-sibling::div/div/div[2]");
		String noti3 = menuPage.getMessage(".//*[@id='float-nav']/preceding-sibling::div/div/div[3]");
		
		// set expectedNotification
		
		String expectedNoti1 = "Sorry, this information";
		String expectedNoti2 = "CAN NOT BE FOUND";
		String expectedNoti3 = "Please try a different search";
		
		if(noti1.equals(expectedNoti1) && noti2.equals(expectedNoti2) && noti3.equals(expectedNoti3)){
			assert(true);
		}else{
			assert(false);
		}
	
	}
	
	// closes browser
	@AfterMethod
	public void afterTest()
	{
		dr.close();
	}
}

