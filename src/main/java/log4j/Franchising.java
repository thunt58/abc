package log4j;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
 
import org.openqa.selenium.WebDriver;

import page.FranchisingPage;
import page.HomePage;
import page.MenuPage;
import page.SearchPopupPage;
import core.Common;


public class Franchising extends Common{
	private static Logger Log = Logger.getLogger(Log4j.class.getName());
	// contructor franchising
	public Franchising(WebDriver driver)
	{
		dr = driver;
	}
	
	public static void main(String[] args) {
		Franchising f = new Franchising(dr);
		DOMConfigurator.configure("log4j.xml");
		
		f.initBrowser("http://www.lotteria.vn/");
        Log.info("Open browser");
        
        HomePage homePage = new HomePage(dr);
        homePage.EN_Switch();
		
		//click Franchising Link
		//FranchisingPage franchisingPage = homePage.clickFranchisingLink();
		
		// get url Franchising Page
		//String franchisingURL = franchisingPage.getURL();
		//Log.info(franchisingURL);
		
		//String expectedFranchising = "http://www.lotteria.vn/en/franchising/";
        
        
        
        SearchPopupPage searchPopupPage = homePage.clickIconSearchMenu();
		String strSearchProduct = "shrimp";
		searchPopupPage.inputKeySearch(strSearchProduct);
		//select radio product
		searchPopupPage.selectRadio("//label[@for='sp']");
		
		//tao bien menuPage by click icon search
		//MenuPage menuPage = searchPopupPage.clickIconSearch2();
		
		
		// go to result search page by type enter
		MenuPage menuPage = searchPopupPage.typeEnter();
		
		//get productList from ArrayList
		List<String> expectedProduct = menuPage.getProductName();
		for(int i=0; i<expectedProduct.size();i++){
			Log.info(expectedProduct.get(i));	
		}
	
		int m=0;
		for(int i=0; i<expectedProduct.size();i++){
			String product = expectedProduct.get(i);
			if(product.toUpperCase().contains(strSearchProduct.toUpperCase())){
			m++;	
			}
		}
		int size = expectedProduct.size();
		if(m==size){
			assert(true);
			Log.info("Search has data");
		}else{
			assert(false);
		}
	}
}
