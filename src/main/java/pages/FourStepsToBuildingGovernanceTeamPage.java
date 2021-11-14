package pages;

import static base.Enums.PageTitle.GOVERNANCETEAMPAGE_TITLE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.Utils;

public class FourStepsToBuildingGovernanceTeamPage extends Utils {

	protected final WebDriver driver;

	@FindBy(xpath = "//*[contains(text(),'Is governance necessary to achieve ')]/a[contains(text(), 'regulatory compliance')]")
	public WebElement regulatoryComplianceLinkText;

	public FourStepsToBuildingGovernanceTeamPage(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyRegulatoryComplianceLinkText(String hrefString) {
		assertTrue(hrefString.contains(regulatoryComplianceLinkText.getAttribute("href")));
	}

	public FourStepsToBuildingGovernanceTeamPage checkGovernanceTeamPageTitle() {
		assertEquals(driver.getTitle(), GOVERNANCETEAMPAGE_TITLE.asString());
		return this;
	}
	
	public By createXpathForSearchGovernText(String searchString) {
    	return By.xpath("//div[@id='nav-all']//a[contains(text(), '"+ searchString + "')]");
    } 

    public void clickSearchString(String searchString)  {
    	WebElement webElement = driver.findElement(createXpathForSearchGovernText(searchString));    	
    	clickElement(webElement);
    }

}
