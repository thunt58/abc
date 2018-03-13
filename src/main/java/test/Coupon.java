package test;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Coupon {
	private WebDriver driver;
    private String url; 
   
	 @BeforeTest
	 public void setUp() throws Exception {
	  //System.setProperty("webdriver.chrome.driver","C:\\chrome\\chromedriver.exe");
	     driver = new FirefoxDriver();
	     url = "http://live.guru99.com/";
	     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	     
	 }
   
	 @Test 
	   public void TestCase() throws Exception {
	  driver.get(url); 
	     
	     // 2. Click Mobile menu
	     driver.findElement(By.linkText("MOBILE")).click(); 
	  // 3. Click vào sản phẩm
	     driver.findElement(By.id("product-collection-image-2")).click();
	     // 4. thêm sp vào giỏ hàng
	     driver.findElement(By.className("add-to-cart-buttons")).click();
	     driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	     // 5. áp dụng mã giảm giá
	     driver.findElement(By.id("coupon_code")).sendKeys("GURU50");
	     driver.findElement(By.cssSelector("[title=\"Apply\"]")).click();
	     // 6. lấy số tiền giảm giá (nó hiện là -$25.00) nên sẽ phải chuyển nó qua StringBuilder và cắt vị trí 0 tới 2-> sẽ thành 25.00, 
	     //sau đó cắt tiếp từ vị trí 2-5> sẽ được 25
	     String giamgia=driver.findElement(By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tbody/tr[2]/td[2]")).getText();
	     StringBuilder str=new StringBuilder(giamgia);
	     str.delete(0, 2);
	     str.delete(2, 5);
	     // lấy giá 5% để so sánh với giá trên xem đúng không
	     int giagiam=(500*5)/100;
	     //so sánh, nếu đúng thì testcase pass, trái lại thì fail
	     try {
	     //chúng ta cần ép kiểu nó về String để so sánh, vì 1 thằng là int, 1 thằng là StringBuilder, không so sánh được
	      Assert.assertEquals(String.valueOf(str), String.valueOf(giagiam)); 
	       } catch (Exception e) {
	        e.printStackTrace();
	       }
	   
	   }
	  
	 @AfterTest
	 public void tearDown() throws Exception {
	  driver.quit();
	   }

}
