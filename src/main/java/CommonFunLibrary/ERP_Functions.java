package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ERP_Functions 
{
	public static WebDriver driver;
	//launch browser and url
	public static String launchApp(String url) 
	{
		String res="";
		System.setProperty("webdriver.chrome.driver","D:\\rkmohan\\Rkmohan_Sln_FW/ERP_Maven/CommonDrivers/chromedriver.exe");
		driver= new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		if (driver.findElement(By.id("btnsubmit")).isDisplayed()) 
		{
			res = "Application Launch Success";
		}
		else
		{
			res= "Application Launch Fail";
		}
		return res;
	}
	//login method
	public static String verifyLogin(String username, String password) throws Throwable
	{
		String res="";
		WebElement objuser = driver.findElement(By.name("username"));
		objuser.clear();
		Thread.sleep(1000);
		objuser.sendKeys(username);
		WebElement objpass = driver.findElement(By.name("password"));
		objpass.clear();
		Thread.sleep(1000);
		objpass.sendKeys(password);
		driver.findElement(By.id("btnsubmit")).click();
		Thread.sleep(1000);
		if (driver.findElement(By.id("logout")).isDisplayed()) 
		{
			res ="Login Success";
		}
		else
		{
			res = "Login Fail";
		}
		return res;
	}
	public static void verifyLogout()
	{
		driver.close();
	}

	//method for supplier creation
	public static String verifySupplier(String sname,String address,String city,String country,String cperson,String pnumber,String email,String mnumber,String notes) throws Throwable 
	{
		String res = "";
		driver.findElement(By.linkText("Suppliers")).click();
		Thread.sleep(2000);
		//click on add button
		driver.findElement(By.xpath("(//span[@class ='glyphicon glyphicon-plus ewIcon'])[1]")).click();
		Thread.sleep(2000);
		String exp_data = driver.findElement(By.name("x_Supplier_Number")).getAttribute("value");
		driver.findElement(By.name("x_Supplier_Name")).sendKeys(sname);
		driver.findElement(By.name("x_Address")).sendKeys(address);
		driver.findElement(By.name("x_City")).sendKeys(city);
		driver.findElement(By.name("x_Country")).sendKeys(country);
		driver.findElement(By.name("x_Contact_Person")).sendKeys(cperson);
		driver.findElement(By.name("x_Phone_Number")).sendKeys(pnumber);
		driver.findElement(By.name("x__Email")).sendKeys(email);
		driver.findElement(By.name("x_Mobile_Number")).sendKeys(mnumber);
		driver.findElement(By.name("x_Notes")).sendKeys(notes);
		driver.findElement(By.name("btnAction")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='OK!']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='ajs-button btn btn-primary']")).click();
		Thread.sleep(2000);
		if (!driver.findElement(By.id("psearch")).isDisplayed())
			driver.findElement(By.xpath("(//span[@class='glyphicon glyphicon-search ewIcon'])[1]")).click();
		driver.findElement(By.name("psearch")).clear();
		driver.findElement(By.name("psearch")).sendKeys(exp_data);
		Thread.sleep(2000);
		driver.findElement(By.id("btnsubmit")).click();
		Thread.sleep(2000);
		//get supplier number from table
		String Act_data = driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
		Thread.sleep(2000);
		System.out.println("Exp: search data:  "+exp_data +"       "+"Act:search Result:  "+Act_data);
		if (Act_data.equals(exp_data))
		{
			res = "Pass";
		} 
		else
		{
			res = "Fail";
		}
		return res;
	}
}
