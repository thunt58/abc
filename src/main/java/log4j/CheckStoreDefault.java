
package log4j;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.FranchisingPage;
import page.HomePage;
import page.MenuPage;
import page.SearchPopupPage;
import page.StorePage;
import core.Common;


public class CheckStoreDefault extends Common{
	private static Logger Log = Logger.getLogger(Log4j.class.getName());
	private static WebDriverWait wait;
	
	// contructor franchising
	public CheckStoreDefault(WebDriver driver)
	{
		dr = driver;
		wait = new WebDriverWait(driver, 10); 
	}
	
	
	public static void main(String[] args) {
		
		DOMConfigurator.configure("log4j.xml");
		
        HomePage homePage = new HomePage(dr);
        homePage.initBrowser();
        Log.info("Open browser");
        homePage.EN_Switch();
        
        StorePage store = homePage.clickStoreInMenu();
		
        store.selectDropDown("tinhthanhID","TP.HCM");
        store.ajax();
        
        int expectedTotal = 84;
        // get total store of TP.HCM
        int actualTotal = store.getRows();
        Log.info(actualTotal);
        
        //compare 
        if(expectedTotal == actualTotal){
        	assert(true);
        }else{
        	assert(false);
        }
		
        
	}
}
