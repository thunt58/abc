package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.Common;

public class BratusPage extends Common {
	
	public BratusPage(WebDriver driver)
	{
		dr = driver;
	}
	
	// get url of bratus page
	public String getURL()
	{
		String url ="";
		url = dr.getCurrentUrl();
		return url;
	}
	
}
