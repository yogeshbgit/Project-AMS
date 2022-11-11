package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyException {

	
	WebDriver driver;
	
	public MyException(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	By explist = By.className("ContentPlaceHolderBody_UserStatus1_tvMyExceptions_0");
	By ualist = By.xpath("//table/tbody/tr/td[4]/a[contains(text(),'UA')]");
	By alllist = By.xpath("//a[@title='ABSENT#']/parent::td/parent::tr//td[1]/span");
	By myexptext = By.xpath("(//div[@class='stats-link']/a)[1]");
	By sadimage = By.id("ContentPlaceHolderBody_UserStatus1_imgMyException");
	
	public List<WebElement> getexplist()
	{
		return driver.findElements(explist);
		 
	}
	public List<WebElement> getualist()
	{
		return driver.findElements(ualist);
		 
	}
	public List<WebElement> getalllist()
	{
		return driver.findElements(alllist);
		 
	}
	public String getmyexptext()
	{
		return driver.findElement(myexptext).getText();
	}
	
	public WebElement getsadimage()
	{
		return driver.findElement(sadimage);
	}
}
