package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
	WebDriver driver;
	WebDriverWait wait;
	By newUserLink=By.xpath("//*[contains(text(),'new Rediffmail ID')]");
	
	public Login(WebDriver driver) {
		this.driver=driver;
		//wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	
	public void clickNewUserLink() {
		driver.findElement(newUserLink).click();
	}
}
