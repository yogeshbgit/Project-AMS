package PageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MyExceptionObject {
	
public WebDriver driver;
public Select s ;
	public MyExceptionObject(WebDriver driver)
	{
		this.driver=driver;
	}
	By clickOnMyExceptions= By.xpath("//td[@class='groovybutton TreeMenu1_MenuTreeView_2 TreeMenu1_MenuTreeView_5']/a[@id='TreeMenu1_MenuTreeViewt2']");
	By clickOnMyExceptionListOpt=By.id("__tab_ContentPlaceHolderBody_TabContainer1_TabPanel1");
	By workingDateList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave']/tbody/tr/td[1]/span");
	By workingDate=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_LBLWorkingDate_0");
	//By type=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_DDLType_0");
	//type in list
	By type=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave']/tbody/tr/td[2]/select");
	//By category=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_DDLCategories_0");
	//category list
	By category=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave']/tbody/tr/td[3]/select");
	//By reason=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_DDLReasonType_0");
	//reason list
	By reason=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave']/tbody/tr/td[4]/select");
	By duration=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_LBLDuration_0");
	//By remark=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_TXTRemarks_0");
	//remark list
	By remark=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave']/tbody/tr/td[6]/input");
	//By markBtn=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_ButUpdate_0");
	// mark button list
	By markBtn=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave']/tbody/tr/td[7]/input");
	By absentCount=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_lblAbsent");
	
	
	public void getMyExceptionList() {
		driver.findElement(clickOnMyExceptions).click();
	}
	public String getClickOnMyExceptionListOpt() {
		return driver.findElement(clickOnMyExceptionListOpt).getText();
	}
	public String getAbsentCount() {
		return driver.findElement(absentCount).getText();
	}
	public List<String> getWorkingDateList() {
		List<WebElement> workingdatelist = driver.findElements(workingDateList);
		List<String> workingDateList = new ArrayList<>();
		for(WebElement workdate:workingdatelist)
		{
			workingDateList.add(workdate.getText());
		}
		return workingDateList;
	}
	
	public WebElement getWorkingDate() {
		 return driver.findElement(workingDate);
	}
	
	public void getTypeList(String typ) {
		List<WebElement> typelist = driver.findElements(type);
		
		for(WebElement ty : typelist)
		{
				s=new Select(ty);
				s.selectByVisibleText(typ);
		}
	}
	public void getCategorylist(String cat) {
		
List<WebElement> categorylist = driver.findElements(category);
		
		for(WebElement category : categorylist)
		{
				s=new Select(category);
				s.selectByVisibleText(cat);
		}
		
	}	
	public void getReasonlist(String  Reason) {
		
List<WebElement> reasonlistt = driver.findElements(reason);
		
		for(WebElement reason1 : reasonlistt)
		{
				s=new Select(reason1);
				s.selectByVisibleText(Reason);
		}
		
	}
	public void getRemarklist(String rmrk) {
List<WebElement> remarklist = driver.findElements(remark);
		
		for(WebElement remark1 : remarklist)
		{
				remark1.sendKeys(rmrk);
		}
		
	}
	public void getAllMarkBtn() {
List<WebElement> markbuttons = driver.findElements(markBtn);
	
		for(WebElement mark : markbuttons)
		{
				mark.click();
		}
		
	}
	

}
