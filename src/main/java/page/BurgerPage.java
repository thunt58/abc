package page;

import org.openqa.selenium.WebDriver;

import core.Common;
public class BurgerPage extends Common {
	// constructor Franchising
	public BurgerPage(WebDriver driver)
	{
		dr = driver;
	}
	
	// get url of Franchising Page
	public String getURL()
	{
		String url = "";
		url = dr.getCurrentUrl();
		return url;
	}
	
}

