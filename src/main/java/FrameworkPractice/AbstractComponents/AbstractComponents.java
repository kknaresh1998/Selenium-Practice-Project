package FrameworkPractice.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameworkPractice.pageobjects.CartPage;
import FrameworkPractice.pageobjects.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		
		this.driver= driver;
	}
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[3]")
	WebElement cartPage;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderPage;
	

	public void waitForProductAppear(By FindBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	 
    public void waitForWebelementVisible(WebElement FindBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
    public void waitForProductDisappear(By FindBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(FindBy));
	}
    
    public void waitForElementsToBeClickable(By FindBy) {
		
  		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  		wait.until(ExpectedConditions.elementToBeClickable(FindBy));
  	}
    
    
	
	public CartPage goToCartPage() {
		
		cartPage.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage ;
	}
	
    public OrderPage goToOrderPage() {
		
		orderPage.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage ;
	}
	
	


}
