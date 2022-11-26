package Project_AMS;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObject.MyExceptionHistoryObect;
import PageObject.MyExceptionObject;

public class MyExceptionHistory {
	public WebDriver driver;
	public MyExceptionObject myException; 
	public MyExceptionHistoryObect myExHist;
	public String workingDate;
	public List<String> workingDateList;
	public String absentCount;
	public boolean flag;
	@BeforeTest
	public void initilize() throws InterruptedException
	{
		LoginPage lp = new LoginPage();
		driver = lp.ValidateLogin();	
	}
	@Test(priority=0)
	public void validateException() throws InterruptedException 
	{
		
		myException=new MyExceptionObject(driver);
		myException.getMyExceptionList();
		absentCount = myException.getAbsentCount();
		if(Integer.parseInt(absentCount)>0) {
			System.out.println("absent count : "+absentCount);
			WebElement workingDate1=myException.getWorkingDate();
			workingDate=workingDate1.getText();
			workingDateList = myException.getWorkingDateList();
			//System.out.println("inside validate exception :"+workingDate);
			myException.getTypeList("Work From Home(WFH)");
			Thread.sleep(4000);
			/*
			wait = new WebDriverWait (driver, Duration.ofSeconds(30));
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridLeave_DDLCategories_0")));
	   		*/
//			Synchronization sync=new Synchronization(driver);
//			sync.visibilityOfElement();
					
			myException.getCategorylist("--NA--");
			myException.getReasonlist("--NA--");
			myException.getRemarklist("Updated by selenium");
			myException.getAllMarkBtn();
			//=================to be add==wait till element to be disappears from page======================================
				
		//	sync.invisibilityOfEleLocated();
				flag=true;
			}
			else {
				flag=false;
			}
			
	}
	
	@Test(priority=1)
	public void validateExceptionHistoryStatus() throws InterruptedException
	{
		Thread.sleep(4000);
		myExHist = new MyExceptionHistoryObect(driver);
		myExHist.getClickOnMyExcepHist();
		List<String> histworkingdate = myExHist.gethistoryworkingdate();
		List<String> histStatusList=myExHist.getStatuslist();
		
		for(int i=0;i<workingDateList.size();i++)
		{
			for(int j=0;j<histworkingdate.size();j++)
			{
				if(workingDateList.get(i).equals(histworkingdate.get(j)))
				{
					
					
					for(int k=j;k<histStatusList.size();k++)
					{
						if(histStatusList.get(k).contains("Waiting for approval"))
						{
							System.out.println("history date : "+histworkingdate.get(j)+", Working Date : "+workingDateList.get(i));
							System.out.println("history status matched***** : "+histStatusList.get(k));
							for(int l=k;l<myExHist.getDeleteButton().size();l++)
							{
								Thread.sleep(5000);
								myExHist.getDeleteButton().get(l).click();
								Thread.sleep(5000);
								driver.switchTo().alert().accept();
								Thread.sleep(4000);
								System.out.println("delete msg : "+myExHist.getDeleteMessage());
								Assert.assertEquals(myExHist.getDeleteMessage(), "Deleted..","delete message verification failed..");
								break;
							}
						
							break;
						}
						
						 
					}
					break;
				}
				
			}
			
		}
		
	
	}

}
