package PageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class myHolidayListObject {
	WebDriver driver;
	public myHolidayListObject(WebDriver driver)
	{
		this.driver = driver;
	}
	By clickonHolidaylist = By.id("TreeMenu1_MenuTreeViewt1");
	By list = By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td/label");
	By yellowHolidaylist = By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td/span/label");
	
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
}
