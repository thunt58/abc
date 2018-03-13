package page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import core.Common;
import core.ReadLocatorFile;
import core.Utils;


public class HomePage extends Common {
	
	// contructor HomePage
	public HomePage(WebDriver driver){
		dr = driver;
	}
	
	// the method return element web driver
	public Utils getElement(String elementName) throws IOException{
		
		//Map<String, Utils> m = new HashMap<String, Utils>();
		
		ReadLocatorFile objExcelFile = new ReadLocatorFile();
		
	    //Prepare the path of excel file
	    String filePath = "E:\\Selenium\\test01\\repository";
	   
	    String[][] xpathData = objExcelFile.readExcel(filePath,"Lotteria_locator.xlsx","HomePage");
		
		Map<String, Utils> m = objExcelFile.getLocatorList(xpathData);
		
		Utils element = null;
		for(Map.Entry<String, Utils> entry:m.entrySet()){
			String key = entry.getKey(); 
			element = entry.getValue();
			
			if(key.equals(elementName)){
				return element;
			}else{
				continue;
			}
	    } 
		
		return element;
	}
		
	/* 
	 * User interactions 
	 */
	/* Function to perform mouse actions */
	
	// The method will perform a mouse-down action on an element
	public void mouseDown(String xpath){
		Actions action = new Actions(dr);
		WebElement element = dr.findElement(By.xpath(xpath));
		action.moveToElement(element);
		action.perform();
	}
	
	//Function to perform a click element
	public void clickElement(String nameElement) throws IOException{
		Utils element = getElement(nameElement);
		String value = element.getValue();
		String type = element.getType();
		By xpathAction = getXpathElement(value,type);
		dr.findElement(xpathAction).click();
	}
	
	// Click logo home
	public void clickLogoHome()throws IOException{
		Utils element = getElement("LOGO_HOMEPAGE");
		String value = element.getValue();
		String type = element.getType();
		By logo_homepage = getXpathElement(value,type);
		dr.findElement(logo_homepage).click();
		
		//dr.findElement(By.xpath(".//*[@class='item05 logo']")).click();
	}
	
	// Click home icon
	public void clickHomeIcon(){
		dr.findElement(By.xpath(".//*[@class='item02 home hide-mobile']")).click();
	}
	
	// Click menu
	public void clickMenu(){
		dr.findElement(By.xpath("//*[@class='item02 has-sub hide-mobile']")).click();
	}
	
	// Scroll down
	public void scrollDown(){
		JavascriptExecutor jse = (JavascriptExecutor)dr;
		jse.executeScript("window.scrollBy(0,250)", "");
	}
	
	// get url of HomePage
	public String getURL(){
		String url = "";
		url = dr.getCurrentUrl();
		return url;
	}
	
	// convert EN Page
	public void EN_Switch(){
		dr.findElement(By.xpath(".//*[@class='item10 lang']")).click();
	}
	/*
	 * Menu Page
	 */
	// go to Menu Page when click burger in menu bar
	public MenuPage clickItemInMenu(){
		dr.findElement(By.xpath("//*[@id='hidden-sub']//li[1]/a/span[3]")).click();
		return new MenuPage(dr);
	}
	
	
	/*
	 * Menu
	 */
	
	// get main menu list
	public List<String> getMenuList(){
		List<WebElement> list = dr.findElements(By.xpath(".//*[@id='hidden-sub']//li"));
		List<String> menuList = new ArrayList<String>();
		
		for(int i=1; i<=list.size(); i++)
		{
			String xpath = ".//*[@id='hidden-sub']//li[" + i +"]";
			String menuName = dr.findElement(By.xpath(xpath)).getText();
			menuList.add(menuName);
		}
		return menuList;
	}
	
	
	/* go to Store Page in Menu */
	public StorePage clickStoreInMenu(){
		dr.findElement(By.xpath(".//*[@id='top']//a[@href='http://www.lotteria.vn/en/store/']")).click();
		return new StorePage(dr);
	}
	
	/* 
	 * Search Function
	 */
	
	// go to SearchPopupPage 
	public SearchPopupPage clickIconSearchMenu(){
		dr.findElement(By.xpath("//a[@class='pop-search']")).click();
		return new SearchPopupPage(dr);
	}
	
	/*
	 *Footer
	 */
	
	// get list item in menu footer 
	public List<String> listItemMenu(){
		List<WebElement> list = dr.findElements(By.xpath("//*[@id='footer']/div[1]//li"));
		List<String> listItemMenu = new ArrayList<String>();
		for(int i=1;i<=list.size();i++){
			String xpath = "//*[@id='footer']/div[1]//li[" + i + "]/a";
			String itemMenuName = dr.findElement(By.xpath(xpath)).getText();
			listItemMenu.add(itemMenuName);
		}
		return listItemMenu;
	}
	
	// get item information footer
	public String getInfo(String xpath){
		String orderInfo = dr.findElement(By.xpath(xpath)).getText();
		return orderInfo;
	}
	
	// get placeholder value 
	public String getPlaceholderText(String xpath){
		String orderInfo = dr.findElement(By.xpath(xpath)).getAttribute("placeholder");
		return orderInfo;
	}
	
	// verify menu with main menu which displayed
	public boolean verifyMenuOfFooter(){
		// set expected menu value
		List<String> expectedListMenu = new ArrayList<String>() {{
		    add("BURGER");
		    add("Combo");
		    add("Chicken");
		    add("Dessert");
		    add("Rice");
		    add("Value");
		}};
		
		// get menuList from web
		List<String> menuList = listItemMenu();
		int count=0;
		// compare 2 array
		
//		for(int i=0;i<expectedListMenu.size();i++)
//		{
//			String expectedItemName = expectedListMenu.get(i);
//			//String actualItemName = menuList.get(i);
//			if(Arrays.asList(menuList).contains(expectedItemName)){
//				count++;
//			}
//		}
//		if(count == expectedListMenu.size()){
//			return true;
//		}else{
//			return false;
//		}
		
		// compare 2 array
		if( expectedListMenu!= null && menuList != null && (expectedListMenu.size() == menuList.size())){
			expectedListMenu.removeAll(menuList);
            if(expectedListMenu.isEmpty()){
            	return true;
            }else{
            	return false;
            }
        }
		return false;
	}
	
	// verify order information
	public boolean verifyOrderInfo(){
		String shipCode = getInfo("//*[@class='ship']/strong");
		String freeDelivery = getInfo("//*[@class='ship']/p");
		
		String expectedShipCode = "1800-8099";
		String expectedFree = "Free Delivery";
		
		if(shipCode.equals(expectedShipCode) && freeDelivery.equals(expectedFree)){
			return true;
		}else{
			return false;
		}
		
	}
	
	// verify franchising information
	public boolean verifyFranInfo(){
		String actual = "DETAILED INFORMATION" + '\n' + "ABOUT BUYING LOTTERIA" + '\n' + "FRANCHISE.";
		String franInfo = getInfo("//*[@class='member']");
		if(actual.equals(franInfo)){
			return true;
		}else{
			return false;
		}
	}
	
	// verify find store information
	public boolean verifyFindStore(){
		String expected_Placeholder = "Where are you ?";
		String actualPlaceholder = getPlaceholderText("//*[@class='search']/input[@type='text']");
		if(expected_Placeholder.equals(actualPlaceholder)){
			return true;
		}else{
			return false;
		}
	}
	
	// verify element present 
	public boolean verifyElementPresent(String xpath){
		if(dr.findElement(By.xpath(xpath))!= null){
			return true;
		}else{
			return false;
		}
	}
	
	// go to franchising page
	public FranchisingPage clickFranchisingLink(){
		dr.findElement(By.xpath("//*[@class='member']/a")).click();
		return new FranchisingPage(dr);
	}
	
	// go to store page
	public StorePage clickFindStoreLink(){
		dr.findElement(By.xpath(".//*[@class='search']/input[@type='text']")).click();
		return new StorePage(dr);
	}
	
	/*
	 * subFooter
	 */
	
	// verify copy right information
	public boolean verifyCopyRightInfo(){
		// set copy right info 
		String actualCopyRight1 = "©2014 Lotteria All Rights Reserved";
		String actualCopyRight2 = "Site by Bratus";
		
		// get copy right info
		String expectedCopyRight1 = getInfo("//*[@id='footer']//div[@class='copyright']");
		String expectedCopyRight2 = getInfo("//*[@id='footer']//a[@href='http://www.bratus.co/']");
		
		if(actualCopyRight1.equals(expectedCopyRight1) && actualCopyRight2.equals(expectedCopyRight2))
		{
			return true;
		}else{
			return false;
		}
	}
	
	// verify present icon of footer
	public boolean verifyPresentIcon(){
		boolean tw = verifyElementPresent("//*[@id='footer']//a[@class='icon tw']");
		boolean pi = verifyElementPresent("//*[@id='footer']//a[@class='icon pi']");
		boolean fb = verifyElementPresent("//*[@id='footer']//a[@class='icon fb']");
		boolean gp = verifyElementPresent("//*[@id='footer']//a[@class='icon gp']");
		boolean yt = verifyElementPresent("//*[@id='footer']//a[@class='icon yt']");
		
		if(tw == true && pi == true && fb == true && gp == true && yt == true){
			return true;
		}
		return false;
	}
	
	// get list menu items of sub Menu
	public List<String> getListCopyFooter(){
		List<WebElement> list = dr.findElements(By.xpath("//*[@id='footer']/div[2]//li"));
		List<String> obtainedList = new ArrayList<String>();
		
		for(int i=1; i<=list.size(); i++){
			String xpath = "//*[@id='footer']/div[2]//li[" + i + "]/a";
			String copyName = dr.findElement(By.xpath(xpath)).getText();
			obtainedList.add(copyName);
		}
		return obtainedList;
	}
	
	// verify menu items which be displayed
	public boolean verifyMenuItems(){
		// set menu items array
		List<String> listItemsName = getListCopyFooter();
		// set expected menu value
		List<String> expectedListMenu = new ArrayList<String>() {{
		    add("About us");
		    add("News");
		    add("Recruitment");
		    add("Contact");
		}};
		if(listItemsName.equals(expectedListMenu)){
			return true;
		}else{
			return false;
		}
		
	}
	
	//check the css value that are changing when we perform mouse hover on the element

	// get color of element before mouse hover
	public String beforeMouseHoverValue(String xpath){
		WebElement element = dr.findElement(By.xpath(xpath));
		String colorValue = element.getCssValue("color");
		return colorValue;
	}
	
	// perform mouse hover on the element
	// get color of element after mouse hover
	public String afterMouseHoverValue(String xpath){
		Actions action = new Actions(dr);
		WebElement element = dr.findElement(By.xpath(xpath));
		action.moveToElement(element).perform();
		String colorValue = element.getCssValue("color");
		return colorValue;
	}
	
	// compare 2 value
	public boolean compareValue(String a, String b){
		if(a.equals(b)){
			return true;
		}else{
			return false;
		}
	}
	
	// go to Bratus page
	public BratusPage clickSiteByBratus(){
		dr.findElement(By.xpath("//*[@id='footer']//a[@href='http://www.bratus.co/']")).click();
		return new BratusPage(dr);
	}
	
	// go to About us page
	public AboutUsPage clickAboutLink(){
		dr.findElement(By.xpath("//*[@id='footer']//a[@href='http://www.lotteria.vn/en/about-us/']")).click();
		return new AboutUsPage(dr);
	}
	
	// go to News page
	public NewsPage clickNewsLink(){
		dr.findElement(By.xpath("//*[@id='footer']//a[@href='http://www.lotteria.vn/en/news/']")).click();
		return new NewsPage(dr);
	}
	
	// go to Recuiments Page
	public RecruitmentPage clickRecuimentsLink(){
		dr.findElement(By.xpath("//*[@id='footer']//a[@href='http://www.lotteria.vn/en/recruitment/']")).click();
		return new RecruitmentPage(dr);
	}
	
	// go to Contact Page
	public ContactPage clickContactLink(){
		dr.findElement(By.xpath("//*[@id='footer']//a[@href='http://www.lotteria.vn/en/contact/']")).click();
		return new ContactPage(dr);
	}
	/*
	 * end subFooter
	 */
	
	
	
}
