package FrameworkPractice.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameworkPractice.TestComponents.BaseTest;
import FrameworkPractice.pageobjects.CartPage;
import FrameworkPractice.pageobjects.CheckOutPage;
import FrameworkPractice.pageobjects.ConfirmationPage;
import FrameworkPractice.pageobjects.LandingPage;
import FrameworkPractice.pageobjects.OrderPage;
import FrameworkPractice.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "Zara coat 3";
	
    @Test(dataProvider="getData", groups= "purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("emailID"), input.get("password"));
		Thread.sleep(5000);
		
		List<WebElement> products= productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductCartPage(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage =cartPage.goToCheckOutPage();
		checkOutPage.selectCountry("india");
		ConfirmationPage confirmationPage =checkOutPage.placeOrder();
		System.out.println(confirmationPage.getTousterMessagePrint());
		String confirmation = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
    
    
    @Test(dependsOnMethods= {"submitOrder"})
    public void orderProductDisplayed() {
    	
    	ProductCatalogue productCatalogue = landingPage.loginApplication("naresh12345@gmail.com", "Naresh@12345");
    	OrderPage orderPage = productCatalogue.goToOrderPage();
    	orderPage.verifyProductOrderPage(productName);
    	
    }
    
	/*
	 * @DataProvider
	 *  public Object[][] getData() {
	 * 
	 * return new Object[] []
	 * {{"naresh12345@gmail.com","Naresh@12345","Zara coat 3"}
	 * ,{"testnaresh_123@gmail.com","Naresh@12345","ADIDAS ORIGINAL"}}; }
	 */
    
   
    
    @DataProvider
    public Object[][] getData() throws IOException {
		/*
		 * HashMap<String,String> map = new HashMap<String,String>(); map.put("emailID",
		 * "naresh12345@gmail.com"); map.put("password", "Naresh@12345");
		 * map.put("product", "Zara coat 3");
		 * 
		 * HashMap<String,String> map1 = new HashMap<String,String>();
		 * map1.put("emailID", "testnaresh_123@gmail.com"); map1.put("password",
		 * "Naresh@12345"); map1.put("product", "ADIDAS ORIGINAL");
		 */
    	
    	List<HashMap<String,String>> data = getJsondataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\FrameworkPractice\\Data\\PurchaseOrder.json");
   	   return new Object[] [] {{data.get(0)},{data.get(1)}};
   			   
    }



}
