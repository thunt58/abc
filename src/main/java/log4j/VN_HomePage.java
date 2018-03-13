package page;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.Common;

// ke thua class Common
public class VN_HomePage extends Common {
	public By MENU_SEARCH;
	public VN_HomePage(WebDriver driver) {
		dr = driver;
		this.xlFilePath = "E:\\Project\\Lotteria_Day4\\repository\\locator.xlsx";
		this.sheetName = "VN_HomePage";
		initialLocator();
	}

	// Click vao logo Lotteria
	public void clickLogoHome() {
		// Xac dinh locator cua logoHomepage bang Xpath va nhan click
		dr.findElement(By.xpath(".//*[@class='item05 logo']")).click();
	}

	public String getURL() {
		String url = ""; // URL = rong
		url = dr.getCurrentUrl(); // Gan URL hien tai vao URL rong
		return url; // Tra ve gia tri cho noi goi ham
	}
	// Click vao icon search trong HomePage (Page object):
	public VN_SearchPopupPage clickMenuSearch() { 
		// Xac dinh locator cua icon search:
		//		dr.findElement(By.xpath("//a[@class='pop-search']")).click(); 
//		HashMap<String, By> hashMap = new HashMap<String, By>();
//		Common com = new Common();
//		String xlFilePath ="E:\\Project\\Lotteria_Day4\\repository\\locator.xlsx";
//		String sheetName = "VN_HomePage";
//		hashMap = com.initialLocator( xlFilePath, sheetName);
//		By MENU_SEARCH = hashMap.get("MENU_SEARCH");
		MENU_SEARCH = getLorcatorValue("MENU_SEARCH").click();
		dr.findElement(MENU_SEARCH);
		return new VN_SearchPopupPage(dr);
	}
	public void clickMenuOrder() {
		dr.findElement(By.xpath("//*[@class='item03 hide-mobile']")).click();
	}
}
