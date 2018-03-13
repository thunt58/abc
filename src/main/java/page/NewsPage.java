package page;

import org.openqa.selenium.WebDriver;

import core.Common;

public class NewsPage extends Common{
	// contructor news page
	public NewsPage(WebDriver driver)
	{
		dr = driver;
	}
	
	// get url of news page of footer
	public String getURL()
	{
		String url = "";
		url = dr.getCurrentUrl();
		return url;
	}
}
