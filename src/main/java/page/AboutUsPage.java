package page;

import org.openqa.selenium.WebDriver;

import core.Common;

public class AboutUsPage extends Common{
	// contructor About us page
	public AboutUsPage(WebDriver driver)
	{
		dr = driver;
	}
	
	// get url of About us page
	public String getURL()
	{
		String url = "";
		url = dr.getCurrentUrl();
		return url;
	}
}
