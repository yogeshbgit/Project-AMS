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
import org.testng.asserts.SoftAssert;

import PageObject.myHolidayListObject;
import Utils.DateConversion;

public class myHolidayList {
	WebDriver driver;
	List<String> allholidaylist;
	myHolidayListObject ml;
	List<String> selectedlist;
	String alertMessage;
	SoftAssert sa = new SoftAssert();
	
	@BeforeTest
	public void initilize() throws InterruptedException
	{
		LoginPage lp = new LoginPage();
		driver=lp.ValidateLogin();
		
	}
	
	@Test(priority=1)
	public void validateHolidaysWithCurrentDate() throws InterruptedException
	{
		
		 ml = new myHolidayListObject(driver);
		ml.getclickonHolidaylist();
		allholidaylist =  ml.allholidaylist();
		System.out.println("All holiday list size : "+allholidaylist.size());
		dateValidation(allholidaylist);
		//System.out.println("All holiday list : "+allholidaylist);		
	}
	
	@Test(priority=2)
	public void validatehighlightholiday()
	{
	List<WebElement> lists=	ml.getyellowholidaylist();
	for(WebElement list:lists)
	{
		String text=list.getText().split(" -")[0].split("[.] ")[1];
		//System.out.println("yellow holiday text:"+text);
		if(text.contains("Saturday") || text.contains("Sunday"))
		{
			//System.out.println("Highlight yellow holiday are saturday or sunday");
			sa.assertTrue(true,"Highlight yellow holiday are saturday or sunday");
		}
		else
		{
			sa.assertTrue(false,"Highlight yellow holiday not saturday or sunday");
		}
	}
		
	
	}
	
	
	
	//select holidays and submite, validate valid holiday count and submitted text 
	@Test(priority=3)
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
	}
	
	@Test(priority=4)
	public void validateSelectedHolidayCount() {
		if(selectedlist.size()>2) {
			alertMessage= ml.getAlertMessage();
			sa.assertEquals(alertMessage, "Already Credited or you can select maximum 2","You have selected more holiday list");
		}
		else
		{
			alertMessage= ml.getAlertMessage();
			sa.assertEquals(alertMessage,"Holiday applied","Alert message verification failed after submit");
		}
		
	}
		
	
	//verify submitted with selected list
@Test(priority=5)
	public void validateSubmittedHolidayList() {
		List<String> submitedList=ml.getsubmitedList();
		if(submitedList.size()>2)
		{
			
			//System.out.println("Alert messege : Submitted list limit exceeded");
			sa.assertTrue(false,"Alert messege : Submitted list limit exceeded");
		}
		else
		{
			//System.out.println("submitted list within limit");
		}
		if(selectedlist.size()==submitedList.size()) {
			sa.assertEquals(selectedlist,submitedList,"List not matched");
			
		}
		else {
			sa.assertTrue(false,"Selected and submitted count not matched");
			//System.out.println("Selected and submitted count not matched");
		}

		System.out.println("Selected list : "+selectedlist);
		System.out.println("submitted list : "+submitedList);
		
	}

	@Test(priority=6)
	public void validateCancelledHolidayList() throws InterruptedException {

		for(int i=0;i<ml.getCancelButton().size();i=0)
		{
			ml.getCancelButton().get(i).click();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
			alertMessage= ml.getAlertMessage();
			//System.out.println("alert message after delete : "+alertMessage);
			sa.assertEquals(alertMessage,"Holiday deleted","Alert message verification failed after delete");
			
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
				sa.assertTrue(date>0,"Date verification failed");
			}
			else
			{
			sa.assertTrue(systemDate<=date,"Date verification failed");
			}
			sa.assertTrue(systemMonth<=month,"Month verification failed");
			sa.assertTrue(systemYear<=year,"Year verification failed");
			
			
					
		}
	}
	
	
	
	@AfterTest(enabled=true)
	public void teardown()
	{
		sa.assertAll();
		driver.close();
	}

	
	
}


//==========================to be add ==validateholiday count with homeage status and sad images