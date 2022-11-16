package Project_AMS;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage {

	
	WebDriver driver;
	@Test
	public WebDriver ValidateLogin() throws InterruptedException
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

return driver;
			
	}
}
