package log4j;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx4j.log.Log;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;

import page.HomePage;

public class Currency {
	NumberFormat format = NumberFormat.getCurrencyInstance();
	Double value = (Double) format.parse("$5.45");
 		
}
