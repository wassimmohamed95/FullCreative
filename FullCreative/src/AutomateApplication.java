import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AutomateApplication {
	
	WebDriver driver;
	Actions action;
	
	@BeforeTest
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver","D:\\Selenium\\chromedriver_88.exe");
		driver = new ChromeDriver();
		driver.get("http://www.htmlcanvasstudio.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void drawLineActivity()
	{
		WebElement drawLine = driver.findElement(By.xpath("//*[@class='button line']"));
		drawLine.click();
		action = new Actions(driver);
		WebElement source = driver.findElement(By.id("imageTemp"));
		int xoffset = 150;
		int yoffset = xoffset/2;
		action.clickAndHold(source).build().perform();
		action.moveToElement(source, -(xoffset), 0).build().perform();
		action.click().build().perform();
		action.moveToElement(source, -(yoffset), 0).build().perform();
		action.clickAndHold().build().perform();
		action.moveToElement(source, -(yoffset), -(yoffset)).build().perform();
		action.click().build().perform();
		action.moveToElement(source, -(yoffset), 0).build().perform();
		action.clickAndHold().build().perform();
		action.moveToElement(source, -(yoffset), yoffset).build().perform();
		action.click().build().perform();
		action.clickAndHold(source).release().build().perform();
	}
	
	@Test(dependsOnMethods="drawLineActivity")
	public void drawRectangleActivity()
	{
		WebElement drawRectangle = driver.findElement(By.xpath("//*[@class='button rectangle']"));
		drawRectangle.click();
		action = new Actions(driver);
		WebElement source = driver.findElement(By.id("imageTemp"));
		int xoffset = 250;
		int yoffset = xoffset/2;
		action.clickAndHold(source).build().perform();
		action.moveToElement(source, xoffset, yoffset).build().perform();
		action.click().build().perform();
	}
	
	@Test(dependsOnMethods="drawRectangleActivity")
	public void eraseLine()
	{
		action = new Actions(driver);
		WebElement source = driver.findElement(By.id("imageTemp"));
		WebElement slider = driver.findElement(By.xpath("//*[@id='slider']/a"));
		slider.click();
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_RIGHT).keyUp(Keys.CONTROL).build().perform();
		WebElement eraseLine = driver.findElement(By.xpath("//*[@class='button eraser']"));
		eraseLine.click();
		int xoffset = 150;
		action.clickAndHold(source).build().perform();
		action.moveToElement(source, -(xoffset), 0).build().perform();
		action.click().build().perform();
	}
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
