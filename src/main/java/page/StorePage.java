package page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import core.Common;

public class StorePage extends Common{
	//contructor Store Page
	WebDriverWait wait;
	public StorePage(WebDriver driver)
	{
		dr = driver;
	}
	
	// get url of store page
	public String getURL()
	{
		String url ="";
		url = dr.getCurrentUrl();
		return url;
	}
	 
	// select option dropdown in Province/City
	public WebElement selectDropDown(String id, String value)
	{
		Select province = new Select(dr.findElement(By.id(id)));
		province.selectByVisibleText(value);
		return null;
	}
	
	// test ajax call
//	public void test_AjaxCall()
//	{
//		WebElement element = selectDropDown("TP HCM");
//		wait.until(ExpectedConditions.visibilityOf(selectDropDown("TP HCM")));	
//	}
	
	// get district list of store in dropdown
	public List<String> getListDistrict()
	{
		WebElement elem = dr.findElement(By.id("quan"));
		
		Select selectList = new Select(elem);
		List<WebElement> options = selectList.getOptions();
			
		//List<String> listDistrict = new ArrayList<String>();
		List<String> listDistrict = options.stream().map(WebElement::getText).collect(Collectors.toList());
		
		return listDistrict;
	}
	
	
	/*Result Table : store list table*/
		
	// get No.of rows
	public int getRows()
	{
		WebElement myTable = dr.findElement(By.xpath(".//*[@id='middle']//table[@class='list-store desk']"));
		
		// To locate rows of table
		List<WebElement> rows_table = myTable.findElements(By.tagName("tr"));
	
		return rows_table.size();
	}
	
	// get data of dynamic table
	public List<List<String>> getDataTable()
	{
		// To locate table
		WebElement myTable = dr.findElement(By.xpath(".//*[@id='middle']//table[@class='list-store desk']"));
		
		// To locate rows of table
		List<WebElement> rows_table = myTable.findElements(By.tagName("tr"));
		
		// To calculate no of rows in table
		int rows_count = rows_table.size();
		
		// declare arrayList 2 demension
		List<List<String>> outLists = new ArrayList<List<String>>();
		
		// Loop will execute till the last row of table
//		for(int row=1; row<rows_count; row++)
//		{
//			// To locate colums(cells) of that specific row
//			List<WebElement> colums_row = rows_table.get(row).findElements(By.tagName("td"));
//			
//			// To locate no of colums(cells) in that specific row
//			int colums_count = colums_row.size();
//			for(int column=0; column<colums_count; column++ )
//			{
//				String celtext = colums_row.get(column).getText();
//				rowValue.add(celtext);
//			}
//			outLists.add(rowValue);
//		}
		
		for(int row=1; row<rows_count; row++)
		{
			// To locate colums(cells) of that specific row
			int index = row+1;
			String xpath = ".//*[@id='middle']//table[@class='list-store desk']/tbody/tr[" + index + "]/td";
			
			List<WebElement> colums_row = dr.findElements(By.xpath(xpath));
			
			// To locate no of colums(cells) in that specific row
			int colums_count = colums_row.size();
				// declare arrayList 1 demension
				List<String> rowValue = new ArrayList<String>();
				
				for(int column=1; column<=colums_count; column++ ){
					String xpathCell = ".//*[@id='middle']//tbody/tr[" + index + "]/td[" + column + "]";
					String celltext = dr.findElement(By.xpath(xpathCell)).getText();

					rowValue.add(celltext);
				}
				
				outLists.add(rowValue);	
		}
		
		return outLists;
	}
	
	// get address list of store
	public List<String> getAddress()
	{
		List<String> listAddress = new ArrayList<String>();
		
		List<List<String>> outLists = getDataTable();
		
		for(int i=0; i<outLists.size();i++)
        {
        	for(int j=0;j<outLists.get(i).size();j++)
        	{
        		listAddress.add(outLists.get(i).get(1));
        		break;
        	}
        }
		return listAddress;
	}
	
	public void ajax()
	{
		/* Wait for the ajax controls to appear. */
		WebDriverWait wait;
		// difference of dr.findElement(By.xpath($xpath)
		By container = By.cssSelector(".select-wrap");
		wait = new WebDriverWait(dr, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(container));
		
		// Get value before performing an ajax call
		
	}
}

