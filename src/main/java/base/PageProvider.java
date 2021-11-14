package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.FourStepsToBuildingGovernanceTeamPage;
import pages.PreciselyGetInTouchPage;
import pages.PreciselyHomePage;

public class PageProvider {

	private final WebDriver driver;

	public PageProvider(WebDriver driver) {
		this.driver = driver;
	}

	public PreciselyHomePage getPreciselyHomePage() {
		return PageFactory.initElements(driver, PreciselyHomePage.class);
	}

	public PreciselyGetInTouchPage getContactPage() {
		return PageFactory.initElements(driver, PreciselyGetInTouchPage.class);
	}

	public FourStepsToBuildingGovernanceTeamPage getFourStepsGovernanceTeamPage() {
		return PageFactory.initElements(driver, FourStepsToBuildingGovernanceTeamPage.class);
	}

}