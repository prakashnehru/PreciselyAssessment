package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import base.Utils;

public class PreciselyGetInTouchPage extends Utils {

	protected final WebDriver driver;

	@FindBy(xpath = "//ul[contains(@id,'search')]/following::li[contains(@class,'contact')]/following::a[text() = 'Get in touch']")
	public WebElement getInTouchMenuButton;

	@FindBy(id = "interested-in")
	public WebElement howCanWeHelpYouDropdownField;

//	@FindBy(xpath = "//iframe[@class ='lazyload']")
//	public WebElement iFrame;

	@FindBy(xpath = "//input[@name ='FirstName']")
	public WebElement firstNameInputField;

	@FindBy(xpath = "//input[@name ='LastName']")
	public WebElement lastNameInputField;

	@FindBy(xpath = "//input[@name ='Job_Title1']")
	public WebElement jobTitleInputField;

	@FindBy(xpath = "//input[@name ='Company']")
	public WebElement companyNameInputField;

	@FindBy(xpath = "//input[@name ='EmailAddress']")
	public WebElement companyEmailAddressInputField;

	@FindBy(xpath = "//input[@name ='BusPhone']")
	public WebElement companyPhoneNumberInputField;

	@FindBy(id = "country-select")
	public WebElement countryDropdownField;

	@FindBy(xpath = "//div[@id='province-select-canada']/select[@name ='C_State_Prov']")
	public WebElement provinceDropdownField;

	@FindBy(xpath = ".//textarea[@name='Comments']")
	public WebElement commentsInputField;

	@FindBy(xpath = ".//button[@type='submit' and contains(text(),'Get in touch')]")
	public WebElement submitButton;

	@FindBy(xpath = "//*[contains(text(),'Thank you for contacting us.')]")
	public WebElement thankYouMessageTextField;

	public PreciselyGetInTouchPage(WebDriver driver) {
		this.driver = driver;
	}

//	public void switchtoIFrame() {
//		waitForVisibilityElement(iFrame);
//		if (iFrame.isDisplayed())
//			driver.switchTo().frame(iFrame);
//	}

	public void enterAndSubmitContactDetails(String interestedIn, String firstName, String lastName, String jobTitle,
			String companyName, String companyEmailId, String companyPhoneNumber, String country, String province,
			String comments) {
		selectFromValue(howCanWeHelpYouDropdownField, interestedIn);
		enterString(firstNameInputField, firstName);
		enterString(lastNameInputField, lastName);
		enterString(jobTitleInputField, jobTitle);
		enterString(companyNameInputField, companyName);
		enterString(companyEmailAddressInputField, companyEmailId);
		enterString(companyPhoneNumberInputField, companyPhoneNumber);
		selectFromValue(countryDropdownField, country);
		selectFromValue(provinceDropdownField, province);
		enterString(commentsInputField, comments);
		clickElement(submitButton);
	}

	public void validateThankYouScreen(String expectedString) {
		waitForVisibilityElement(thankYouMessageTextField);
		Assert.assertTrue(thankYouMessageTextField.getText().contains("Thank you for contacting us."));
	}

}
