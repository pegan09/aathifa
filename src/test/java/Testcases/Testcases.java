package Testcases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver.Driver;
import Pages.Login;
import Pages.NewUser;


public class Testcases {
	WebDriver driver;
	WebDriverWait wait;
	Login login;
	NewUser newuser;
	public static String countryselected;
	static Properties prop=new Properties();
	
	
	@BeforeClass
	public void setup() throws IOException {
		FileInputStream fis=new FileInputStream(".//src//main//resources//config.properties");
		prop.load(fis);
		Driver setup=new Driver();
		driver=setup.setup(prop.getProperty("browser"));
		driver.get(prop.getProperty("baseUrl"));
		driver.manage().window().maximize();
		login=new Login(driver);
		newuser =new NewUser(driver);
		wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		
	}
	
	@Test
	public void testCase1() throws InterruptedException, IOException {
	    login.clickNewUserLink();
	    newuser.setName(prop.getProperty("name"));
	    newuser.setMail(prop.getProperty("mailid"));
	    newuser.setAvailability();
	    newuser.setRadio();
	    newuser.setPassword(prop.getProperty("password"));
	    newuser.setCheckbox();
	    newuser.setDate(prop.getProperty("date"));
	    newuser.setMonth(prop.getProperty("month"));
	    newuser.setYear(prop.getProperty("year"));
	    newuser.displayCountries();

	    String expectedCountry = prop.getProperty("country");
	    String actualCountry = newuser.validateCountry(expectedCountry);

	    try {
	        Assert.assertEquals(actualCountry, expectedCountry);
	        writeExcel(expectedCountry, actualCountry, "Pass");
	    } catch (AssertionError e) {
	        writeExcel(expectedCountry, actualCountry, "Fail");
	        throw e; 
	    }
	}
    
	public void writeExcel(String expectedCountry, String actualCountry, String result) throws IOException {
	    FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\case.xlsx");
	    XSSFWorkbook workbook = new XSSFWorkbook();
	    XSSFSheet sheet = workbook.createSheet("test case");

	    // Header row
	    XSSFRow header = sheet.createRow(0);
	    header.createCell(0).setCellValue("TS_no");
	    header.createCell(1).setCellValue("Expected result");
	    header.createCell(2).setCellValue("Actual result");
	    header.createCell(3).setCellValue("Pass/Fail");

	    // Data row
	    XSSFRow row = sheet.createRow(1);
	    row.createCell(0).setCellValue("TC_01");
	    row.createCell(1).setCellValue(expectedCountry);
	    row.createCell(2).setCellValue(actualCountry);
	    row.createCell(3).setCellValue(result);

	    workbook.write(fos);
	    workbook.close();
	    fos.close();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		
	}
}
