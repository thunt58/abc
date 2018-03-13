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

public class TestCase extends Common{
	
	//initialize browser
	@BeforeMethod
	public void init()
	{
		initBrowser();
	}
	
	//TC015: Check product list that display correctly when select BURGER
	public void verifyBugerList()
	{
		
	}
	
	// closes browser
	@AfterMethod
	public void afterTest()
	{
		dr.close();
	}
}
