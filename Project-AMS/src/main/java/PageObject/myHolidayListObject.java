package PageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class myHolidayListObject {
	public WebDriver driver;
	public List<WebElement> allholiday;
	public myHolidayListObject(WebDriver driver)
	{
		this.driver = driver;
	}
	By clickonHolidaylist = By.id("TreeMenu1_MenuTreeViewt1");
	By list = By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td/label");
	By yellowHolidaylist = By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td/span/label");
	//By checkbox = By.xpath("(//input[@type='checkbox'])[1]"); // only checkbox
	//(//input[@type='checkbox']/parent::td/label)[1] 
	By checkbox = By.xpath("(//input[@type='checkbox']/parent::td/label)[1]");	//checkbox text
	By submitbutton = By.id("ContentPlaceHolderBody_IMGBTNSubmit");
	By submitedlist = By.xpath("//table[@id='ContentPlaceHolderBody_GridViewHoliday']/tbody/tr/following-sibling::tr[1]/td[2]");
	By alertMessage = By.id("ContentPlaceHolderBody_LBLMSG");
	By cancelButton = By.xpath("//table[@id='ContentPlaceHolderBody_GridViewHoliday']/tbody/tr/following-sibling::tr/td[7]");
	
	public void getclickonHolidaylist()
	{
	driver.findElement(clickonHolidaylist).click();
	}
	public List<WebElement> getHolidaylist()
	{
		return driver.findElements(list);
	}
	public List<WebElement> getyellowholidaylist()
	{
		return driver.findElements(yellowHolidaylist);
	}
	public List<String> allholidaylist()
	{
		List<WebElement> holidaylist = driver.findElements(list);
		List<WebElement> yelholidaylist = driver.findElements(yellowHolidaylist);
		allholiday = new ArrayList<WebElement>(holidaylist);
		allholiday.addAll(yelholidaylist);
		List<String> hollist = new ArrayList<>();
		List<String> yelhollist = new ArrayList<>();
		for(WebElement list : holidaylist)
		{
			hollist.add(list.getText());
		}
		for(WebElement list : yelholidaylist)
		{
			yelhollist.add(list.getText());
		}
		List<String> allholidaylist = new ArrayList<>();
		allholidaylist.addAll(hollist);
		allholidaylist.addAll(yelhollist);
		return allholidaylist;
		
	}
	public WebElement clickcheckbox()
	{
		return driver.findElement(checkbox);
	}
	
	public void clicksubmitbutton()
	{
		driver.findElement(submitbutton).click();
	}
	
	public List<String> getsubmitedList()
	{
		List<WebElement> lists =driver.findElements(submitedlist);
		List<String> submitedlist = new ArrayList<>();
		for(WebElement list:lists)
		{
			submitedlist.add(list.getText().split(" \\[")[0]);
		}
		
		return submitedlist;
	}
	
	public String getAlertMessage() 
	{
		return driver.findElement(alertMessage).getText();
		
	}
	public List<WebElement> getCancelButton()
	{
		return driver.findElements(cancelButton);
	}
}
