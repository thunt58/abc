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

public class Menu_TestCase extends Common{
	
	//initialize browser
	@BeforeMethod
	public void init()
	{
		initBrowser();
	}
	
	//TC014: Check menu list when mouse down in menu item on menu bar
	@Test
	public void verifyMenuList()
	{
		// Launch the browser
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
		
		// Mouse down 
		String xpathMenu = ".//*[@class='item02 has-sub hide-mobile']/a";
		homePage.mouseDown(xpathMenu);
		
		List<String> menuList = homePage.getMenuList();
		
		List<String> expectedMenuList = new ArrayList<String>(){{
			add("BURGER");
			add("COMBO");
			add("VALUE");
			add("CHICKEN");
			add("RICE");
			add("DESSERT");
			add("DRINK");
			
		}};
		
		if(menuList.equals(expectedMenuList)){
			assert(true);
		}else{
			assert(false);
		}
		
	}
	
	//TC015: Verify product list that displayed correctly when select BURGER
	public void verifyBURGERList()
	{
		//Launch the browser
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
		
		//Mouse down
		String xpathMenu = ".//*[@class='item02 has-sub hide-mobile']/a";
		homePage.mouseDown(xpathMenu);
		
		// click on the burger item and go to burger page
		MenuPage menuPage = homePage.clickItemInMenu();
		
		
	}
	
	
	// closes browser
	@AfterMethod
	public void afterTest()
	{
		dr.close();
	}
}

