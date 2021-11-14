package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import util.driver.DriverFactory;

import static base.Enums.PageTitle.LOGIN_PAGE_TITLE;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	private static final String fileSeparator = File.separator;
	private static final String rootDirectory = System.getProperty("user.dir");
	private static WebDriver driver;
	private ExtentReports extent;

	private static String takeScreenshot(WebDriver driver, String screenshotName) {
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = rootDirectory + fileSeparator + "screenshots" + fileSeparator + screenshotName + " - "
				+ timestamp + ".png";
		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destination;
	}

	@BeforeTest
	public void extentReportSetup() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(rootDirectory + fileSeparator + "reports"
				+ fileSeparator + "html-report" + fileSeparator + "execution-report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("Test Execution Report");
		htmlReporter.config().setTheme(Theme.DARK);

		extent.setSystemInfo("Application Name", "Precisley Assesment");
		extent.setSystemInfo("Test Developer", "Prakash Gunasekaran");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Operating System", "Windows 10 - 64 Bit");
	}

	@BeforeMethod
	public void initializeDriver() {
		driver = DriverFactory.getDriver(BrowserType.CHROME);
		driver.manage().window().maximize();
		driver.get("https://www.infogix.com/");
		assertEquals(driver.getTitle(), LOGIN_PAGE_TITLE.asString());
	}

	@AfterMethod
	public void generateReportDataAndCloseAllDrivers(ITestResult result) {
		ExtentTest test = extent.createTest(result.getName());
		switch (result.getStatus()) {
		case ITestResult.FAILURE:
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable().getMessage(), ExtentColor.RED));
			try {
				test.fail("Screenshot at the failed moment is below "
						+ test.addScreenCaptureFromPath(takeScreenshot(driver, result.getName())));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case ITestResult.SKIP:
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.GREY));
			break;
		case ITestResult.SUCCESS:
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
			break;
		}
		if (driver != null) {
			driver.quit();
		}
	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}

	protected PageProvider pages() {
		return new PageProvider(driver);
	}

	public void waitForVisibilityElement(WebElement webElement) {
		try {
			new WebDriverWait(driver, 800).until(ExpectedConditions.visibilityOf(webElement));
		} catch (Exception e) {
			throw e;
		}

	}

	public void clickElement(WebElement webElement) {
		if (webElement.isDisplayed())
			webElement.click();
	}

	public void enterString(WebElement xpath, String inputString) {
		if (xpath.isDisplayed()) {
			xpath.sendKeys(inputString);
		}
	}

	public void selectFromValue(WebElement xpath, String value) {
		Select selectFromDropdown = new Select(xpath);
		selectFromDropdown.selectByValue(value);
	}

}
