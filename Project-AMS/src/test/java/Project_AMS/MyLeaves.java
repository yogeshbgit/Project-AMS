package Project_AMS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyLeaves {
	
	WebDriver driver;
	List<String> workdatetext = new ArrayList<>();
	List<String> statustext= new ArrayList<>();
	List<String> leavetyptext= new ArrayList<>();
	List<String> workdatetext1= new ArrayList<>();
	List<String> statustext1= new ArrayList<>();
	List<String> leavetyptext1= new ArrayList<>();
	
	@Test
	public void MyLeave() throws InterruptedException
	{
		LoginPage lp = new LoginPage();
		driver = lp.ValidateLogin();
		driver.findElement(By.id("TreeMenu1_MenuTreeViewt5")).click();
		Thread.sleep(2000);
		// list from leave plan
		List<WebElement> wkdate = driver.findElements(By.xpath("//div[@id='ContentPlaceHolderBody_TabContainer1_TabPanel3']/table/tbody/tr[17]//child::tr/following-sibling::tr[2]/td[1]"));
		List<WebElement> status = driver.findElements(By.xpath("//div[@id='ContentPlaceHolderBody_TabContainer1_TabPanel3']/table/tbody/tr[17]//child::tr/following-sibling::tr[2]/td[2]"));
		List<WebElement> leavetyp= driver.findElements(By.xpath("//div[@id='ContentPlaceHolderBody_TabContainer1_TabPanel3']/table/tbody/tr[17]//child::tr/following-sibling::tr[2]/td[3]"));
		//list from my leave
		Thread.sleep(1500);
		getalllist1(wkdate,status,leavetyp);
		driver.findElement(By.id("__tab_ContentPlaceHolderBody_TabContainer1_TabPanel1")).click();
		List<WebElement> wkdate1= driver.findElements(By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridAbsences']/tbody/tr/following-sibling::tr[1]/td[5]"));
		List<WebElement> status1= driver.findElements(By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridAbsences']/tbody/tr/following-sibling::tr[1]/td[6]"));
		List<WebElement> leavetyp1 = driver.findElements(By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridAbsences']/tbody/tr/following-sibling::tr[1]/td[2]"));
		getalllist2(wkdate1,status1,leavetyp1);
		Collections.reverse(workdatetext1);
		Collections.reverse(statustext1);
		Collections.reverse(leavetyptext1);
		System.out.println("1 : "+workdatetext+", "+statustext+", "+leavetyptext);
		System.out.println("2 : "+workdatetext1+", "+statustext1+", "+leavetyptext1);
		Assert.assertEquals(workdatetext, workdatetext1,"working date verification failed..");
		Assert.assertEquals(statustext, statustext1,"Status verification failed..");
		Assert.assertEquals(leavetyptext, leavetyptext1,"leave type verification failed..");
	
	}
	
	public void  getalllist1(List<WebElement> wkdate,List<WebElement> status,List<WebElement> leavetyp)
	{
//		System.out.println("workdate size : "+wkdate.size());
//		System.out.println("status size : "+status.size());
//		System.out.println("leavetype size : "+leavetyp.size());
		for(WebElement dt : wkdate)
		{
			workdatetext.add(dt.getText());
		}
		for(WebElement st : status)
		{
			statustext.add(st.getText());
		}
		for(WebElement lt : leavetyp)
		{
			leavetyptext.add(lt.getText());
		}
		

		

	}
	public void  getalllist2(List<WebElement> wkdate,List<WebElement> status,List<WebElement> leavetyp)
	{
//		System.out.println("workdate size : "+wkdate.size());
//		System.out.println("status size : "+status.size());
//		System.out.println("leavetype size : "+leavetyp.size());
		for(WebElement dt : wkdate)
		{
			workdatetext1.add(dt.getText());
		}
		for(WebElement st : status)
		{
			statustext1.add(st.getText());
		}
		for(WebElement lt : leavetyp)
		{
			leavetyptext1.add(lt.getText());
		}
		

		

	}

}
