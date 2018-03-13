package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.Common;

public class SearchPopupPage extends Common {
	// contructor SearchPopupPage
	public SearchPopupPage(WebDriver driver)
	{
		dr = driver;
	}
	
	// input search text
	public void inputKeySearch(String strSearchText)
	{
		dr.findElement(By.name("str")).sendKeys(strSearchText);
	}
	
	// click icon search of popup
	public MenuPage clickIconSearch2()
	{
		dr.findElement(By.xpath(".//*[@id='search']//input[@type='submit']")).click();
		return new MenuPage(dr);
	}
	
	// type enter when perform to search
	public MenuPage typeEnter()
	{
		dr.findElement(By.name("str")).sendKeys(Keys.ENTER);
		return new MenuPage(dr);
	}
	
	// select radio button
	public void selectRadio(String xpath)
	{
		WebElement radioBtn = dr.findElement(By.xpath(xpath));
		radioBtn.click();
	}
	
} 
