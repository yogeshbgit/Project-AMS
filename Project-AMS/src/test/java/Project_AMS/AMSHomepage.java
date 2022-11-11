package Project_AMS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import PageObject.MyException;
import Utils.DateConversion;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AMSHomepage {
	
	public WebDriver driver;
	MyException me;
	@Test
	public void validateMyException() throws InterruptedException
	{
		LoginPage lp = new LoginPage();
		driver = lp.ValidateLogin();
		me = new MyException(driver);
		List<WebElement> lists=	me.getexplist();
		List<WebElement> alllist = me.getalllist();
		List<WebElement> ualist = me.getualist();
		String myexception = me.getmyexptext();
		Assert.assertEquals(myexception, "My Exceptions ("+ualist.size()+")","validation");
		WebElement image=	me.getsadimage();
			if(lists.size()!=0)
			{	
				String  imagetext = image.getAttribute("class").split(" ")[0];
				//System.out.println(imagetext);
				Assert.assertEquals(imagetext, "sad");
				System.out.println("Sad Image Matched");
			}
		validateDatenText(lists, alllist, ualist);

	}
	 
	
	public void validateDatenText(List<WebElement> lists,List<WebElement> alllist,List<WebElement> ualist)
	{
		List<String> UAlist1 = new ArrayList<>();
		List<String> UAlist2 = new ArrayList<>();
		List<Integer>data1 = new ArrayList<>() ;
		List<Integer>data2 = new ArrayList<>() ;
		DateConversion datecon = new DateConversion();
		for(int i=0;i<=lists.size()-2;i++)
		{
			String Ualist = lists.get(i).getText().split("2022 ")[1].trim();
			UAlist1.add(Ualist);
			String text = lists.get(i).getText().split(" ")[0];
			List<Integer>d1 = datecon.dateFormatConversion(text);
			data1.addAll(d1);
		}
		int k=1;
		for(int j=alllist.size()-1;j>0;j--) 
		{
			String text1 = alllist.get(j).getText().split(", ")[1];
			//System.out.println("data 2 text : "+text1);
			if(k<4)
			{
				List<Integer>d2 = datecon.dateFormatConversion1(text1);
				data2.addAll(d2);
				k++;
			}
			else
				break;
		}
		
		int l=1;
		for(int j=ualist.size()-1;j>0;j--)
		{
			if(l<4)
			{
				UAlist2.add(ualist.get(j).getText());
				l++;
			}
			else
				break;
		}

		System.out.println("data 1 :"+data1);
		System.out.println("data 2 :"+data2);
		Assert.assertEquals(data1, data2);
		System.out.println("ua list 1 : "+UAlist1);
		System.out.println("ua list 2 : "+UAlist2);
		Assert.assertEquals(UAlist1, UAlist2);
		System.out.println("both text and date matched");
		
	}
	
	@Test
	public  void validatePendingLeave()
	{

		String pendtext =me.getpendingtext();		
		String apptext = me.getapproachtext();
		Assert.assertEquals(pendtext, "Pending Leave/Holiday request by LM");
		Assert.assertEquals(apptext, "Approaching Leave / Holiday:");
		String imgtext = me.getpendingimage();
		Assert.assertEquals(imgtext, "sad");
		
	}
	
	@Test
	public void  validateMyPendingAction()
	{
		String imgtext = me.getpendingactionimage();
		Assert.assertEquals(imgtext, "happy");
		String pendingacttext = me.getpendingacttext();
		Assert.assertEquals(pendingacttext, "My Pending Action");

	}
	
	@AfterTest(enabled=true)
	public void teardown()
	{
		driver.close();
	}

}
