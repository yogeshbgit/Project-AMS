package PageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyExceptionHistoryObect {

	WebDriver driver;
	
	public MyExceptionHistoryObect(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By clickOnMyExcepHist=By.xpath("//div[@id='ContentPlaceHolderBody_TabContainer1_header']/span[2]/span/span/span");
	//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridviewExceptionHistry']/tbody/tr/preceding-sibling::tr[1]/td[1]/span
	By status = By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridviewExceptionHistry']/tbody/tr/preceding-sibling::tr[1]/td[6]/span");
	By historyworkingdate = By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridviewExceptionHistry']/tbody/tr/preceding-sibling::tr[1]/td[1]/span");
	By delete = By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridviewExceptionHistry']/tbody/tr/td[9]");
	By deleteMessage = By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_Label5");
	public void getClickOnMyExcepHist() {
		driver.findElement(clickOnMyExcepHist).click();
	}
	public List<String> getStatuslist()
	{
		List<WebElement> historyStatusList= driver.findElements(status);
		List<String> histStatusList = new ArrayList<>();
		for(WebElement status : historyStatusList)
		{
			histStatusList.add(status.getText());
		}
		
		
		return histStatusList;
	}
	
	public List<String> gethistoryworkingdate()
	{
		List<WebElement> histworkingdate = driver.findElements(historyworkingdate);
		List<String> histWorkingDateList = new ArrayList<>();
		for(WebElement workdate :histworkingdate )
		{
			histWorkingDateList.add(workdate.getText());
		}
		return histWorkingDateList;
	}
	public List<WebElement> getDeleteButton()
	{
		List<WebElement> deletebuttons = driver.findElements(delete);
		return deletebuttons;
	}
	public String getDeleteMessage()
	{
		return driver.findElement(deleteMessage).getText();
	}

}
