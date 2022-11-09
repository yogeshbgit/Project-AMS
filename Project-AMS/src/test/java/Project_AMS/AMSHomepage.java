package Project_AMS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AMSHomepage {
	
	public WebDriver driver;
	@Test
	public void validateHomePage() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
			driver.get("https://ams-in.capita.co.in/Mypage/Home.aspx");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.name("loginfmt")).sendKeys("P50096390@capitaindia.onmicrosoft.com");
			driver.findElement(By.xpath("//input[@id='idSIButton9']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys("Admin$2145");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='idBtn_Back']")).click();
			String myexception = driver.findElement(By.xpath("(//div[@class='stats-link']/a)[1]")).getText();
			Assert.assertEquals(myexception, "My Exceptions (9)","validation");
		List<WebElement> lists=	driver.findElements(By.className("ContentPlaceHolderBody_UserStatus1_tvMyExceptions_0"));
		List<WebElement> alllist = driver.findElements(By.xpath("//a[@title='ABSENT#']/parent::td/parent::tr//td[1]/span"));
		List<WebElement> ualist = driver.findElements(By.xpath("//a[@title='ABSENT#']"));
		validateDatenText(lists, alllist, ualist);
	}
	
	
	public void validateDatenText(List<WebElement> lists,List<WebElement> alllist,List<WebElement> ualist)
	{
		List<String> UAlist1 = new ArrayList<>();
		List<String> UAlist2 = new ArrayList<>();
		List<Integer>data1 = new ArrayList<>() ;
		List<Integer>data2 = new ArrayList<>() ;
		for(int i=0;i<=lists.size()-2;i++)
		{
			String Ualist = lists.get(i).getText().split("2022 ")[1].trim();
			UAlist1.add(Ualist);
			String text = lists.get(i).getText().split(" ")[0];
			List<Integer>d1 =  dateFormatConversion(text);
			data1.addAll(d1);
		}
		for(int j=alllist.size()-1;j>5;j--)
		{
			String text1 = alllist.get(j).getText().split(", ")[1];
			List<Integer>d2 = dateFormatConversion1(text1);
			data2.addAll(d2);
		}
		
		
		for(int j=ualist.size()-1;j>5;j-- )
		{
			UAlist2.add(ualist.get(j).getText());
		}
		Assert.assertEquals(data1, data2);
		Assert.assertEquals(UAlist1, UAlist2);
		System.out.println("both text and date matched");
		
	}
	
	
	public List<Integer> dateFormatConversion(String  schedule)
	{

		List<Integer> alldata = new ArrayList<>();
	int date = Integer.parseInt(schedule.split("-")[0]);
	int year = Integer.parseInt(schedule.split("-")[2]);
	String Month = schedule.split("-")[1];

	HashMap<String,Integer> hm = new HashMap<String,Integer>();
	hm.put("Jan", 1);
	hm.put("Feb", 2);
	hm.put("Mar", 3);
	hm.put("Apr", 4);
	hm.put("May", 5);
	hm.put("Jun", 6);
	hm.put("Jul", 7);
	hm.put("Aug", 8);
	hm.put("Sep", 9);
	hm.put("Oct", 10);
	hm.put("Nov", 11);
	hm.put("Dec", 12);

	int month = hm.get(Month);
	alldata.add(date);
	alldata.add(month);
	alldata.add(year);
	
	return alldata;
	//System.out.println(" 01 int : "+date +" "+month+" "+year );
	
	}
	
	public List<Integer> dateFormatConversion1(String  schedule)
	{
		List<Integer> alldata = new ArrayList<>();
	int date = Integer.parseInt(schedule.split(" ")[0]);
	int year = Integer.parseInt(schedule.split(" ")[2]);
	String Month = schedule.split(" ")[1];

	HashMap<String,Integer> hm = new HashMap<String,Integer>();
	hm.put("Jan", 1);
	hm.put("Feb", 2);
	hm.put("Mar", 3);
	hm.put("Apr", 4);
	hm.put("May", 5);
	hm.put("Jun", 6);
	hm.put("Jul", 7);
	hm.put("Aug", 8);
	hm.put("Sep", 9);
	hm.put("Oct", 10);
	hm.put("Nov", 11);
	hm.put("Dec", 12);

	int month = hm.get(Month);
	alldata.add(date);
	alldata.add(month);
	alldata.add(year);
	
	//System.out.println("int : "+date +" "+month+" "+year );
	return alldata;
	}

}
