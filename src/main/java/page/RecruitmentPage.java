package page;

import org.openqa.selenium.WebDriver;

import core.Common;

public class RecruitmentPage extends Common {
	// contructor recuiments page
	public RecruitmentPage(WebDriver driver)
	{
		dr = driver;
	}
	
	// get url of page
	public String getURL()
	{
		String url = "";
		url = dr.getCurrentUrl();
		return url;
	}
}
