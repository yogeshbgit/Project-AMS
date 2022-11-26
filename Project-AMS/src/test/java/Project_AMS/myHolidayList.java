package Project_AMS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObject.myHolidayListObject;
import Utils.DateConversion;

public class myHolidayList {
	WebDriver driver;
	List<String> allholidaylist;
	myHolidayListObject ml;
	List<String> selectedlist;
	String alertMessage;
	
	@BeforeTest
	public void initilize() throws InterruptedException
	{
		LoginPage lp = new LoginPage();
		driver=lp.ValidateLogin();
	}
	
	@Test(priority=0)
	public void validateHolidaysWithCurrentDate() throws InterruptedException
	{
		
		 ml = new myHolidayListObject(driver);
		ml.getclickonHolidaylist();
		allholidaylist =  ml.allholidaylist();
		System.out.println("All holiday list size : "+allholidaylist.size());
		dateValidation(allholidaylist);
		//System.out.println("All holiday list : "+allholidaylist);		
	}
	
	
	//select holidays and submite, validate valid holiday count and submitted text 
	@Test(priority=1)
	public void validateSelectedHolidays() throws InterruptedException {
		List<WebElement> lists = ml.allholiday;
		int beforelist = lists.size();
		//System.out.println("before list size (7) : "+beforelist);
		//System.out.println("submit list before submit (0) : "+ml.getsubmitedList().size());
		selectedlist = new ArrayList<>();
		int n=1;
		for(int i=0;i<lists.size();i++)
		{
			if(n<3)
			{
			lists.get(i).click();
				selectedlist.add(lists.get(i).getText().split(" [....]")[0]);
				//System.out.println("selected list : "+lists.get(i).getText());
				n++;
			}
			
		}
		//System.out.println("selected size(2) : "+selectedlist.size());
		ml.clicksubmitbutton();
		Thread.sleep(2000);
		alertMessage= ml.getAlertMessage();
		Assert.assertEquals(alertMessage,"Holiday applied","Alert message verification failed after submit");
		
		
	}
	
	@Test(priority=2)
	public void validateSelectedHolidayCount() {
		if(selectedlist.size()>2) {
			Assert.assertEquals(alertMessage, "Already Credited or you can select maximum 2","You have selected more holiday list");
		}

	}
		
	
	//verify submitted with selected list
@Test(dependsOnMethods= {"validateSelectedHolidayCount"})
	public void validateSubmittedHolidayList() {
		List<String> submitedList=ml.getsubmitedList();
		if(submitedList.size()>2)
		{
			System.out.println("Alert messege : Submitted list limit exceeded");
		}
		else
		{
			//System.out.println("submitted list within limit");
		}
		if(selectedlist.size()==submitedList.size()) {
			Assert.assertEquals(selectedlist,submitedList,"List not matched");
			
		}
		else {
			System.out.println("Selected and submitted count not matched");
		}

		System.out.println("Selected list : "+selectedlist);
		System.out.println("submitted list : "+submitedList);
	
	}

	@Test(priority=4)
	public void validateCancelledHolidayList() throws InterruptedException {

		for(int i=0;i<ml.getCancelButton().size();i=0)
		{
			ml.getCancelButton().get(i).click();
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			Thread.sleep(5000);
			//System.out.println("alert message after delete : "+alertMessage);
			Assert.assertEquals(alertMessage,"Holiday deleted","Alert message verification failed after delete");
		}
	}
	

	
	
	
	public void dateValidation(List<String> allholidaylist)
	{
		for(String list : allholidaylist )
		{
			//list = 30-Nov-2022
			String text=list.split("- ")[1];
			DateConversion dc = new DateConversion();
			dc.dateFormatConversion(text);
			int date = dc.date;
			int month = dc.month;
			int year = dc.year;
			
			//System.out.println("application date  : "+date+",month: "+month+",year: "+year);
			DateFormat dateFormat = new SimpleDateFormat("dd");
			DateFormat yearFormat = new SimpleDateFormat("yyyy");
			DateFormat monthFormat = new SimpleDateFormat("MM");
			Date date1 = new Date();
			int systemDate = Integer.parseInt(dateFormat.format(date1));
			int systemYear = Integer.parseInt(yearFormat.format(date1));
			int systemMonth = Integer.parseInt(monthFormat.format(date1));
			//System.out.println("system date : "+systemDate+",month: "+systemMonth+",year: "+systemYear);
			if(month>systemMonth)
			{
				Assert.assertTrue(date>0,"Date verification failed");
			}
			else
			{
			Assert.assertTrue(systemDate<=date,"Date verification failed");
			}
			Assert.assertTrue(systemMonth<=month,"Month verification failed");
			Assert.assertTrue(systemYear<=year,"Year verification failed");
			
					
		}
	}
	
	
	@AfterTest(enabled=false)
	public void teardown()
	{
		driver.close();
	}

	
	
}


//==========================to be add ==validateholiday count with homeage status and sad images