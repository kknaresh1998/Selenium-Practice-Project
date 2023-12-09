package FrameworkPractice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import FrameworkPractice.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement findCountry;
	
	By countries = By.cssSelector(".ta-item");
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	By placeOrderWait= By.xpath("//*[text()='Place Order ']");
	
	@FindBy(xpath="//*[text()='Place Order ']")
	WebElement placeOrderClick;
	
	By confirmationMessage = By.cssSelector(".hero-primary");
	
	@FindBy(css=".hero-primary")
	WebElement confirmText;
	
	public void selectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(findCountry, countryName).build().perform();
		
		waitForProductAppear(countries);
		
		selectCountry.click();
		
	}
	
	public ConfirmationPage placeOrder() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,350)", "");
		js.executeScript("arguments[0].click();",placeOrderClick);
		
		//waitForProductAppear(placeOrderWait);
		
		//placeOrderClick.click();
		
		return new ConfirmationPage(driver);


	}


	
	
	
	

	
	
	

}
