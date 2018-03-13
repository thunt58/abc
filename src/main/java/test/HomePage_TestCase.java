package test;

import java.io.IOException;
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
import page.NewsPage;
import page.RecruitmentPage;
import page.StorePage;
import core.Common;

public class HomePage_TestCase extends Common{
	
	//initialize browser
	@BeforeMethod
	public void init()
	{
		initBrowser();
	}
	
	//TC001: Check default Home Page which displayed
	@Test
	public void checkHomePage()
	{	
		HomePage homePage = new HomePage(dr);
		String actualResult = homePage.getURL();
		String expectedResult = "http://www.lotteria.vn/";
		if(actualResult.equals(expectedResult)){
			assert(true);
		}else{
			assert(false);
		}		
	}
	
	//TC002: Check Home Page displayed when user click to logo Lotteria
		@Test
	public void checkHomeIcon() throws IOException
	{	
		HomePage homePage = new HomePage(dr);
		// Step1. Click optional item on menu bar
		String m = "MENU_MENU";
		homePage.clickElement(m);
		// Step 2. Click logo Lotteria on center screen
		homePage.clickLogoHome();
		String actualResult = homePage.getURL();
		String expectedResult = "http://www.lotteria.vn/";
		if(actualResult.equals(expectedResult)){
			assert(true);
		}else{
			assert(false);
		}		
	}
	
	//TC004: Verify items include [Menu,Order,Franchising,Find store] which displayed right on footer
	@Test
	public void verifyItemFooter()
	{
		// Step 1: Access to web
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
		
		// Step 2: Scroll down to see footer
		homePage.scrollDown();
		
		// Items are displayed on footer
		// Verify Menu Items with main menu
		
		String iconSearchXpath = "//*[@id='footer']//input[@type='submit']";
		if(homePage.verifyMenuOfFooter() == true ){	
			if(homePage.verifyOrderInfo() == true){
				if(homePage.verifyFranInfo() == true){
					if(homePage.verifyFindStore() == true && homePage.verifyElementPresent(iconSearchXpath)){
						assert(true);
					}
				}else{
					assert(false);
				}
			}else{
				assert(false);
			}
		}else{
			assert(false);
		}
		
	}
	
	//TC005: Verify page is displayed when click item of Menu
	//verify BURGER Menu
	
	@Test
	public void checkItemMenuLink()
	{
		
	}
	
	//TC006: Check Franchising of menu footer
	@Test
	public void checkFranchisingLink()
	{
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
		
		//click Franchising Description Link
		FranchisingPage franchisingPage = homePage.clickFranchisingLink();
		
		// get url Franchising Page
		String franchisingURL = franchisingPage.getURL();
		
		// set url Franchising Page
		String expectedFranchising = "http://www.lotteria.vn/en/franchising/";
		
		// Check screen 
		if(franchisingURL.equals(expectedFranchising)){
			assert(true);
		}else{
			assert(false);
		}
	}
	
	//TC007: Check Store Page when click text box in Find Store item
	@Test
	public void checkFindStoreBtn()
	{
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
		
		// Step 2: Scroll down to see footer
		homePage.scrollDown();
		
		// click Find Store Link
		StorePage storePage = homePage.clickFindStoreLink();
		
		// set url Store Page
		String expectedStoreURL = "http://www.lotteria.vn/en/store/";
		
		// get url Store Page
		String storeURL = storePage.getURL();
		
		// compare actual result to expected result
		if(expectedStoreURL.equals(storeURL)){
			assert(true);
		}else{
			assert(false);
		}
		
	}
	
	//TC008: Check footer which not change when go to pages
	@Test
	public void verifyConstantFooter()
	{
		// Step 1: Launch browser
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
		
		// Step 2: Scroll down to see footer
		homePage.scrollDown();
		
		if(homePage.verifyMenuItems() == true){
			if(homePage.verifyMenuItems() == true){
				if(homePage.verifyPresentIcon() == true){
					assert(true);
				}
			}else{
				assert(false);
			}
		}else{
			assert(false);
		}
	}
	
	//TC009: Check Bratus Page is displayed when click link "Site by Bratus"
	@Test
	public void verifyBratusPage()
	{
		// Launch the browser
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
		
		// Scroll down to see footer
		homePage.scrollDown();
		
		// Step1: Click "Site by Bratus" link
		BratusPage bratusPage = homePage.clickSiteByBratus();
		
		// set css value before mouse hover
		String colorBefore = "rgba(141, 140, 138, 1)";
		
		// set css value after mouse hover
		String colorAfter = "rgba(144, 135, 134, 1)";
		
		// set bratusPage url value
		String bratusPageURL = "http://bratus.co/";
		
		// get bratusPage url value
		String expectedBratusPage = bratusPage.getURL();
		
		// get color value when after mouse hover
		String colorAfterMouse = homePage.afterMouseHoverValue("//*[@id='footer']//a[@href='http://www.bratus.co/']");
		
		// compare css value that are changing and check url when click link 
		//if(colorAfter.equals(colorAfterMouse)){
			if(bratusPageURL.equals(expectedBratusPage)){
				assert(true);
			}		
		else{
			assert(false);
		}
		
	}
	
	//TC010: Check About us is displayed when click link "About us"
	@Test
	public void verifyAboutUsPage()
	{
		// Launch the browser
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
			
		String actualURL = "http://www.lotteria.vn/en/about-us/";
		// click about us link of footer
		AboutUsPage aboutUsPage = homePage.clickAboutLink();
		String expectedURL = aboutUsPage.getURL();
		
		if(homePage.compareValue(actualURL, expectedURL)){
			assert(true);
		}else{
			assert(false);
		}
	}
	
	//TC011: Check News is displayed when click link "News"
	@Test
	public void verifyNewsPage()
	{
		// Launch the browser
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
		
		// click news link
		NewsPage newsPage = homePage.clickNewsLink();
		
		String actualURL = "http://www.lotteria.vn/en/news/";
		String expectedURL = newsPage.getURL();
		
		if(homePage.compareValue(actualURL, expectedURL)){
			assert(true);
		}else{
			assert(false);
		}
	}
	
	//TC012: Check Recuiments is displayed when click link "Recuiments"
	@Test
	public void verifyRecuiments()
	{
		//Launch the browser
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
		
		// click Recuiments
		RecruitmentPage recruitment = homePage.clickRecuimentsLink();
		String actualURL = "http://www.lotteria.vn/en/recruitment/";
		String expectedURL = recruitment.getURL();
		
		if(homePage.compareValue(actualURL, expectedURL)){
			assert(true);
		}else{
			assert(false);
		}
		
	}
	
	//TC013: Check Contact is displayed when click link "Contact"
	@Test
	public void verifyContact()
	{
		// Launch the browser
		HomePage homePage = new HomePage(dr);
		homePage.EN_Switch();
		
		// Click contact link
		ContactPage contactPage = homePage.clickContactLink();
		
		// get url of contact page
		String expectedURL = "http://www.lotteria.vn/en/contact/";
		String actualURL = contactPage.getURL();
		
		// compare url
		if(expectedURL.equals(actualURL)){
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
