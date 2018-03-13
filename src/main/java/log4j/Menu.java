package log4j;

import java.io.IOException;
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
import core.Common;
import core.Utils;


public class Menu extends Common{
	private static Logger Log = Logger.getLogger(Log4j.class.getName());
	private static WebDriverWait wait;
	
	// contructor franchising
	public Menu(WebDriver driver)
	{
		dr = driver;
		wait = new WebDriverWait(driver, 10); 
	}
	
	
	public static void main(String[] args) throws IOException {
		
		DOMConfigurator.configure("log4j.xml");
		
        HomePage homePage = new HomePage(dr);
        homePage.initBrowser();
        Log.info("Open browser");
        homePage.EN_Switch();
		
		// Step1. Click optional item on menu bar

		dr.findElement(By.xpath(".//*[@id='top']//ul/li[3]/a[@href='javascript:;']")).click();
		
		dr.findElement(By.xpath("//*[@id='top']//li[6]/a[@href='http://www.lotteria.vn']")).click();
		
		String actualResult = homePage.getURL();
		String expectedResult = "http://www.lotteria.vn/";
		if(actualResult.equals(expectedResult)){
			assert(true);
		}else{
			assert(false);
		}		
 		
	}
}
