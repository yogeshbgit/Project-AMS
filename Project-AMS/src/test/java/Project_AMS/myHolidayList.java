package Project_AMS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import PageObject.myHolidayListObject;
import Utils.DateConversion;

public class myHolidayList {
	WebDriver driver;
	@Test
	public void validateMyHolidayList() throws InterruptedException
	{
		LoginPage lp = new LoginPage();
		driver=lp.ValidateLogin();
		myHolidayListObject ml = new myHolidayListObject(driver);
		ml.getclickonHolidaylist();
		List<String> allholidaylist =  ml.allholidaylist();
		System.out.println("holiday list : "+allholidaylist.size());
		dateValidation(allholidaylist);
				
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
			
			System.out.println("application date  : "+date+",month: "+month+",year: "+year);
			DateFormat dateFormat = new SimpleDateFormat("dd");
			DateFormat yearFormat = new SimpleDateFormat("yyyy");
			DateFormat monthFormat = new SimpleDateFormat("MM");
			Date date1 = new Date();
			int systemDate = Integer.parseInt(dateFormat.format(date1));
			int systemYear = Integer.parseInt(yearFormat.format(date1));
			int systemMonth = Integer.parseInt(monthFormat.format(date1));
			System.out.println("system date : "+systemDate+",month: "+systemMonth+",year: "+systemYear);
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
	@AfterTest
	public void teardown()
	{
		driver.close();
	}

	
	
	
}
