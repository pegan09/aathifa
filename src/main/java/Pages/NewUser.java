package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewUser {
	WebDriver driver;
	WebDriverWait wait;
	By name=By.xpath("//input[contains(@name,'name')]");
	By mail=By.xpath("//input[contains(@id,'login')]");
	By availability=By.xpath("//*[contains(@value,'availability')]");
	By radio=By.xpath("//*[contains(@id,'radio')]");
	By password=By.id("newpasswd");
	By checkbox=By.className("nomargin");
	By date=By.className("day");
	By month=By.xpath("//*[@class='middle month']");
	By year=By.xpath("//*[@class='year']");
	By country=By.id("country");
	public NewUser(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	
	public void setName(String namefield) {
		driver.findElement(name).sendKeys(namefield);
	}
	public void setMail(String mailid) {
		driver.findElement(mail).sendKeys(mailid);
	}
	public void setAvailability() {
		wait.until(ExpectedConditions.elementToBeClickable(availability));
		driver.findElement(availability).click();
	}
	public void setRadio() {
		wait.until(ExpectedConditions.elementToBeClickable(radio));
		driver.findElement(radio).click();
	}
	public void setPassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}
	public void setCheckbox() {
		driver.findElement(checkbox).click();
	}
	public void setDate(String dayfield) {
		WebElement dates=driver.findElement(date);
		Select s=new Select(dates);
		s.selectByValue(dayfield);
	}
	public void setMonth(String monthfield) {
		WebElement months=driver.findElement(month);
		Select s1=new Select(months);
		s1.selectByValue(monthfield);
	}
	public void setYear(String yearfield) {
		WebElement years=driver.findElement(year);
		Select s2=new Select(years);
		s2.selectByValue(yearfield);
	}
	public void displayCountries() {
		driver.findElement(country).click();
		WebElement countryname=driver.findElement(country);
		Select s4=new Select(countryname);
		List <WebElement> countrylist=s4.getOptions();	
		System.out.println("Printing countries name:");
		driver.findElement(country).sendKeys(Keys.ESCAPE);
		for(WebElement val:countrylist) {
			System.out.println(val.getText());
		}
		System.out.println("Total number of Countries in the field: "+countrylist.size());
	}
	public String validateCountry(String countryfield) {
		WebElement countryname=driver.findElement(country);
		Select s=new Select(countryname);
		s.selectByVisibleText(countryfield);
		String countryselected= s.getFirstSelectedOption().getText();
		System.out.println("Name of country selected: "+countryselected);
				
				if(countryselected.equals(countryfield)) {
					System.out.println("Selected country is "+countryfield);
				}
				else {
					System.out.println("Selected country is not "+countryfield);
				}
				return countryselected;
	}
	
}
