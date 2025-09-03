package Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Driver {
	public static WebDriver driver;
	public WebDriver setup(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
			System.out.println("dxhuhdsu");
			
		}
		else {
			System.setProperty("webdriver.edge.driver", "C:\\Users\\2425590\\Downloads\\edgedriver_win64\\msedgedriver.exe");
			driver=new EdgeDriver();
		}
		return driver;
		
	}
}
