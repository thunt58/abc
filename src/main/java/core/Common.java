package core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common 
{
	// bien global
	public static WebDriver dr;
	public static WebDriverWait wait;
	private final static String URL = "http://www.lotteria.vn/";
	
	//Launch the Lotteria
    public void initBrowser(){
	//String pathConf = "F:\\Downloads\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe";
	//System.setProperty("webdriver.chrome.driver", pathConf);
	    // mo browser
        dr = new FirefoxDriver();
        //mo url
        dr.get(URL);
        dr.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    
    //the method return type locator
    public By getXpathElement(String value, String type){
    	By result = null;
    	switch(type) {
        case "id" : 
        	result = By.id(value);
        	break;
       case "class name" :
    	   result = By.className(value);
    	   break;
		case "name" :
			result = By.name(value);
        	break;
		case "tagName" :
			result = By.tagName(value);
        	break; 
		case "linkText" :
			result = By.linkText(value);
        	break; 
		case "partialLinkText" :
			result = By.partialLinkText(value);
        	break; 
		case "cssSelector" :
			result = By.cssSelector(value);
        	break; 
		case "xpath" :
			result = By.xpath(value);
			break;
     }
    	return result;
    }
    
    
    public void closeBrowser()
    {
    	dr.close();
    }
    
}
