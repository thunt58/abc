package page;



import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.Common;

/**
 * @author Thu Nguyen
 *
 */
public class MenuPage extends Common{
	// contructor MenuPage
	
	// declare attribute
	String image;
	String productName;
	String price;
	
	
	public MenuPage(String image, String productName, String price) {
		super();
		this.image = image;
		this.productName = productName;
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPrice() {
		return price;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public MenuPage(WebDriver driver)
	{
		dr = driver;
	}
		
	// get message in case searchNoData
	public String getMessage(String xpath)
	{
		String text = dr.findElement(By.xpath(xpath)).getText();
		return text;
	}
	
	//get name of product in menu bar
//	public  List<String> getCategory()
//	{
//		// lay danh sach cac phan tu web chua ten san pham
//		List<WebElement> list = dr.findElements(By.xpath("//*[@id='middle']//li"));
//		//tao mang luu tru ten san pham
//		 List<String>  listCategory = new ArrayList<String>();
//		for(int i=1; i<= list.size();i++){
//			String xpath = "//*[@id='middle']//li" + "[" + i + "]/h2/a";
//			String categoryName = dr.findElement(By.xpath(xpath)).getText();
//			listCategory.add(categoryName);
//		}
//		
//		return listCategory;
//	}
	
	// get attribute of product which be founded using ArrayList
	public List<MenuPage> getProduct()
	{
		// lay danh sach cac phan tu web chua ten san pham
		List<WebElement> list = dr.findElements(By.xpath("//*[@id='middle']//li"));
		// create array to store attribut of product
		List<MenuPage>  listProduct = new ArrayList<MenuPage>();
		
		for(int i=1; i<= list.size();i++){
			
			String xpathName = "//*[@id='middle']//li" + "[" + i + "]/h2/a";
			String productName = dr.findElement(By.xpath(xpathName)).getText();
			String xpathImg = "//*[@id='middle']//li" + "[" + i + "]/a/img";
			String image = dr.findElement(By.xpath(xpathImg)).getAttribute("src");
			String priceXpath = "//*[@id='middle']//li" + "[" + i + "]/p/span";
			String price =  dr.findElement(By.xpath(priceXpath)).getText();
			
			MenuPage menuPage = new MenuPage(image, productName, price);
			
			listProduct.add(menuPage);
		}
		
		return listProduct;
	}
	
	// click item on menu bar
	public void clickItem(String xpath){
		dr.findElement(By.xpath(xpath));
	}
	
	// get name of product which be founded using mang 1 chieu
//	public String[] getProductList(){
//		List<WebElement> list = dr.findElements(By.xpath("//*[@id='middle']//li"));
//		String[] listProduct = new String[list.size()];
//		for(WebElement e:list){
//			int index = list.indexOf(e)+1;
//			String xpath = "//*[@id='middle']//li" + "[" + index + "]/h2/a";
//			String productName = dr.findElement(By.xpath(xpath)).getText();
//			listProduct[index-1] = productName;
//		}
//		return listProduct;
//	}
	
	
	
}
