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
	
	//My Exception
	By explist = By.className("ContentPlaceHolderBody_UserStatus1_tvMyExceptions_0");
	By ualist = By.xpath("//table/tbody/tr/td[4]/a[contains(text(),'UA')]");
	By alllist = By.xpath("//a[@title='ABSENT#']/parent::td/parent::tr//td[1]/span");
	By myexptext = By.xpath("(//div[@class='stats-link']/a)[1]");
	By sadimage = By.id("ContentPlaceHolderBody_UserStatus1_imgMyException");
	
	//Pending Leave
	By pendingtext = By.xpath("(//div[@class='stats-link']/a)[2]");
	By approachtext = By.xpath("//div[@class='mrt-5 stats-link']/a");
	By imagetxt = By.id("ContentPlaceHolderBody_UserStatus1_imgLeaveHoliday");
	
	//My Pending Action
	By pendingacttext = By.className("newcusthead");
	By actionimagetxt = By.id("ContentPlaceHolderBody_UserStatus1_imgPendingAction");
	
	
	
	
	
	
	
	//My Exception methods
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

	//Pending Leave methods
	public String getpendingtext()
	{
		return driver.findElement(pendingtext).getText();
	}
	
	public String getapproachtext()
	{
		return driver.findElement(approachtext).getText();
	}
	
	public String getpendingimage()
	{
		String image=driver.findElement(imagetxt).getAttribute("class");
		String text  = image.split(" ")[0];
		return text;
	}
	
	
	//MyPending Action method
	public String getpendingacttext()
	{
		return driver.findElement(pendingacttext).getText();
	}
	
	public String getpendingactionimage()
	{
		String image=driver.findElement(actionimagetxt).getAttribute("class");
		String text  = image.split(" ")[0];
		return text;
	}
}
