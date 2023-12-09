package FrameworkPractice.Test;

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

import FrameworkPractice.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName = "Zara coat 3";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		//naresh123452gmail.com // Naresh@12345
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("naresh12345@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Naresh@12345");
		driver.findElement(By.id("login")).click();
		Thread.sleep(5000);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod= products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
		.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();	
	
		System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))).getText());
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click();
		
		List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		boolean match =cartItems.stream().anyMatch(cartitem -> cartitem.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-item"))));
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		
		WebElement placeOrder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Place Order ']")));
		placeOrder.click();
		
		System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("#toast-container")))).getText());

		String confirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	

	}

}
