package Project_AMS;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class calenderhandle {

WebDriver driver;
@Test
public void calender() throws InterruptedException
{
	LoginPage lp = new LoginPage();
	driver = lp.ValidateLogin();
	Thread.sleep(2000);
	driver.findElement(By.id("TreeMenu1_MenuTreeViewt5")).click();
	driver.findElement(By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTFromLeaveDate")).click();
	WebElement gettext = driver.findElement(By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTFromLeaveDate_CalendarExtender_title"));
	String text =driver.findElement(By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTFromLeaveDate_CalendarExtender_title")).getText();
	System.out.println("calender : "+text);
	WebElement nextclick=driver.findElement(By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTFromLeaveDate_CalendarExtender_nextArrow"));
	String exp = "March, 2024";
	while(!text.equals(exp))
	{
		nextclick.click();
		text=gettext.getText();
	}
	


}
}
