package FrameworkPractice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import FrameworkPractice.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	
     WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	By touserMessageForWait= By.cssSelector("#toast-container");
	
	@FindBy(css="#toast-container")
	WebElement touserMessageForText;
	
	public String getConfirmationMessage() {
		
		return confirmationMessage.getText();
	}
	
	public String getTousterMessagePrint() {
		
		waitForProductAppear(touserMessageForWait);
		return touserMessageForText.getText();
	}

}
