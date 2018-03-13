package log4j;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


import page.HomePage;
import core.Common;


public class HiworldTest extends Common{
	private static Logger Log = Logger.getLogger(Log4j.class.getName());
	private static WebDriverWait wait;
	
	// contructor franchising
	public HiworldTest(WebDriver driver)
	{
		dr = driver;
		//wait = new WebDriverWait(driver, 5); 
	}
	
	public static void main(String[] args) {
		HiworldTest homePage = new HiworldTest(dr);
       
		DOMConfigurator.configure("log4j.xml");
		
		homePage.initBrowser("http://hiworld.com.vn/");
        Log.info("Open browser");
        
 		//List<String> menuList = homePage.getMenuList();
 		
 		List<WebElement> list = dr.findElements(By.xpath(".//*[@id='bs-example-navbar-collapse-1']/ul/li/a"));
 		
		List<String> menuList = new ArrayList<String>();
		
		//dr.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Log.info("Duy:"+list.size());
		
		
		Log.info("--------");
		for(int i=0; i<list.size(); i++)
		{
			
			Log.info(list.get(i).getText());
			menuList.add(list.get(i).getText());
		}
	
		Log.info("--------");
		
		/*for(int i=1; i<=list.size(); i++)
		{
			String xpath = ".//*[@id='hidden-sub']//li[" + i + "]/a/span[@class='text']";
			String menuName = dr.findElement(By.xpath(xpath)).getText();
			//Log.info(menuName);
			menuList.add(menuName);
		}*/
 		Log.info(menuList.get(0));
 		Log.info(menuList);
 		
 		List<String> expectedMenuList = new ArrayList<String>(){{
 			add("HOME");
 			add("WHO WE ARE");
 			add("WHAT WE BELIEVE");
 			add("WHAT WE DO");
 			add("BECOME OUR MEMBERS");
 			add("OUR PARTNERS");
 			add("OUR ACTIVITIES");
 			add("CONTACT");
 			
 		}};
 		
 		if(menuList.equals(expectedMenuList)){
 			Log.info("true");
 		}else{
 			Log.info("false");
 		}
 		
 		homePage.closeBrowser();
	}
}
