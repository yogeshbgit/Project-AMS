package Project_AMS;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import PageObject.MyException;
import Utils.DateConversion;

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
		//System.out.println("list size : "+lists.size());
		List<WebElement> alllist = me.getalllist();
		//System.out.println("alllist size : "+alllist.size());
		List<WebElement> ualist = me.getualist();
		//System.out.println("ualist size : "+ualist.size());
		String myexception = me.getmyexptext();
		if(ualist.size()!=0)
		{
			Assert.assertEquals(myexception, "My Exceptions ("+ualist.size()+")","My Exception validation");
		}
		else
		{
			Assert.assertEquals(myexception, "My Exceptions","My Exception validation");
		}
		WebElement image=	me.getsadimage(); 
			if(lists.size()!=0)
			{	
				String  imagetext = image.getAttribute("class").split(" ")[0];
				//System.out.println(imagetext);
				Assert.assertEquals(imagetext, "sad");
				System.out.println("Sad Image Matched");
			}
			else
			{
				String  imagetext = image.getAttribute("class").split(" ")[0];
				//System.out.println(imagetext);
				Assert.assertEquals(imagetext, "happy");
				System.out.println("happy Image Matched");
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
		int n=1;
		for(int i=0;i<=lists.size()-1;i++)
		{
			String Ualist = lists.get(i).getText().split("2022 ")[1].trim();
			UAlist1.add(Ualist);
			String text = lists.get(i).getText().split(" ")[0];
			if(n<4)
			{
				List<Integer>d1 = datecon.dateFormatConversion(text);
				data1.addAll(d1);
				n++; 
			}
		}
		int k=1;
		for(int j=alllist.size()-1;j>=0;j--) 
		{
			String text1 = alllist.get(j).getText().split(", ")[1];
			if(k<4)
			{
				List<Integer>d2 = datecon.dateFormatConversion1(text1);
				data2.addAll(d2);
				k++;
			}
			else
			{
				System.out.println("in else condition");
				break;
			}	
		}
		int l=1;
		for(int j=ualist.size()-1;j>=0;j--)
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
		String text = driver.findElement(By.cssSelector(".newcusthead")).getText();
		System.out.println("pending action text :  "+text);
		String pendingacttext = me.getpendingacttext();
		//Assert.assertEquals(pendingacttext, "My Pending Action");

	}
	
	@AfterTest(enabled=false)
	public void teardown()
	{
		driver.close();
	}
	


}
