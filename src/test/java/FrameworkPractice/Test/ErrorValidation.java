package FrameworkPractice.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import FrameworkPractice.TestComponents.BaseTest;
import FrameworkPractice.TestComponents.Retry;
import FrameworkPractice.pageobjects.CartPage;
import FrameworkPractice.pageobjects.CheckOutPage;
import FrameworkPractice.pageobjects.ConfirmationPage;
import FrameworkPractice.pageobjects.LandingPage;
import FrameworkPractice.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidation extends BaseTest{
	
	
    @Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("naresh12345@gmail.com", "Naresh@345");
		Assert.assertEquals("Incorrect email password",landingPage.getErrorValidations());
	
	}
    
    
    @Test
    public void searchProductValidation() throws InterruptedException {
    	String productName = "Zara coat 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("naresh12345@gmail.com", "Naresh@12345");	
		List<WebElement> products= productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductCartPage("Zara coat 33");
		Assert.assertFalse(match);
    }
}
