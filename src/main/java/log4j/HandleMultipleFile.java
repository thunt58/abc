package log4j;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HandleMultipleFile {
	public static void main(String[] args) throws InterruptedException, IOException{
		WebDriver dr = new FirefoxDriver();
		
		// maximize window
		dr.manage().window().maximize();
		
		dr.get("http://demo.automationtesting.in/Register.html");
		
		dr.findElement(By.id("imagesrc")).click();

		Thread.sleep(5000);
		
		 int exitCode = 1;
		  try {
		   Process process = Runtime.getRuntime().exec("C:\\Users\\Thu Nguyen\\Desktop\\UploadFile\\fileUpload.exe"+" "+"E:\\CV\\Nguyen-Thi-Thu-CV.docx");
		   exitCode = process.waitFor();
		   System.out.println("**** INFO **** EXIT CODE : " + exitCode);
		  } catch (Exception e) {
			  System.out.println(e.getMessage());
		   //System.out.println("**** ERROR **** Calling [" + args[0] + "] " + e.getMessage());
		  }
	}
}
