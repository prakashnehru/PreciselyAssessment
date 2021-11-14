package pages;

import static base.Enums.PageTitle.LOGIN_PAGE_TITLE;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.Utils;

public class PreciselyHomePage extends Utils {

	protected final WebDriver driver;

	@FindBy(id = "search-navigation")
	public WebElement getInTouchMenuButton;

	@FindBy(xpath = "//div[@id='navbarSupportedContent']//span[contains(@class,'search-span cursor')]")
	public WebElement searchIcon;

	@FindBy(xpath = "//input[@name='s']")
	public WebElement searchInputField;

	public PreciselyHomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickGetInTouchMenuButton() {
		waitForVisibilityElement(getInTouchMenuButton);
		clickElement(getInTouchMenuButton);
	}

	public void clickSearchIconButton() {
		waitForVisibilityElement(searchIcon);
		clickElement(searchIcon);
	}

	public PreciselyHomePage checkLoginPageTitle() {
		assertEquals(driver.getTitle(), LOGIN_PAGE_TITLE.asString());
		return this;
	}

	public void enterSearchText(String enterText) {
		enterString(searchInputField, enterText + Keys.ENTER);
	}

}
