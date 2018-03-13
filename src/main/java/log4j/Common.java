package test01;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import jxl.Sheet;
import jxl.Workbook;



public class Common {
	// Bien Global
	public WebDriver dr;

	// mo trinh duyet
	public void initBrower() {
		dr = new FirefoxDriver();
		// Nhap URL vao trinh duyet vua mo va di den dia chi dich
		dr.get("http://www.lotteria.vn");
	}
	public HashMap<String, By> hashMap;
	public String  xlFilePath;
	public String  sheetName;
	public static String[][] getTableObject(String xlFilePath, String sheetName) {
		String[][] tabArray = null;
		int ci, cj;
		try {
		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
		Sheet sheet = workbook.getSheet(sheetName);
		tabArray = new String[sheet.getRows() - 1][3];
		ci = 0;

		for (int i = 1; i < sheet.getRows(); i++, ci++) {
		cj = 0;
		for (int j = 0; j < 3; j++, cj++) {
		tabArray[ci][cj] = sheet.getCell(j, i).getContents();
		}
		}
		} catch (Exception e) {
		e.printStackTrace();
		}

		return (tabArray);
		}

	public HashMap<String, By> initialLocator (){
		HashMap<String, By> hashMap = null;
		hashMap = new HashMap<String, By>();
		//Doc data tu excel
		String [][] locator = getTableObject (xlFilePath, sheetName);
		for(int i=0; i<locator.length; i++)
		{
			hashMap.put(locator[i][0],getLocator(locator[i][i+1],locator[i][i+2]));
		}
	}
	public By getLocatorValue (String lorcatorName)(){
		return this.hashMap.get(lorcatorName);
	}
	/**
	 * Tra ve locator
	 * @param type 
	 * @param value: gia tri cua locator tuong ung
	 * @return: tra ve gia tri cua bien result
	 */
	public By getLocator (String type, String value){ 
		By result = null;
		switch (type){
		case "id":
			result = By.id(value);
			break;
		case "xpath":
			result = By.xpath(value);
			break;
		case "name":
			result = By.name(value);
			break;
		}
		return result;
	}
}
