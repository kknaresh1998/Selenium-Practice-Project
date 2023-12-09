package FrameworkPractice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkPractice.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	WebDriver driver;
	

	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//button[@type='button'])[2]")
	WebElement checkOutPage;
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	private List<WebElement> cartItems;
	
	public boolean verifyProductCartPage(String productName) {
		
		boolean match =cartItems.stream().anyMatch(cartitem -> cartitem.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOutPage() {
		checkOutPage.click();
		return new CheckOutPage(driver);
	}
	
	

}
