package page;

import org.openqa.selenium.WebDriver;

import core.Common;

public class ContactPage extends Common {

	//contructor ContactPage
	public ContactPage(WebDriver driver)
	{
		dr = driver;
	}
	
	// get URL of Contact Page
	public String getURL()
	{
		String url="";
		url = dr.getCurrentUrl();
		return url;
	}
	
}
