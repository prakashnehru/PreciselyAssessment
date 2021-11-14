package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Utils;
import pages.FourStepsToBuildingGovernanceTeamPage;
import pages.PreciselyGetInTouchPage;
import pages.PreciselyHomePage;

public class PreciselyTest extends Utils {

	private PreciselyHomePage preciselyHomePage;
	private PreciselyGetInTouchPage preciselyGetInTouchPage;
	private FourStepsToBuildingGovernanceTeamPage fourStepsToBuildingGovernanceTeamPage;

	private String interestedIn = "Other";
	private String firstName = "Prakash";
	private String lastName = "Prakash";
	private String jobTitle = "Senior Automation Position";
	private String companyName = "Precisely HR";
	private String companyEmailId = "test@test.com";
	private String companyPhoneNumber = "1234567890";
	private String country = "Canada";
	private String province = "British Columbia";
	private String comments = "This is a coding challenge for Test Automation position. Please disregard this message";
	private String thankYouMessage = "Thank you for contacting us.";
	private String searchText = "govern";
	private String governanceTeamText = "Four Steps to Building a Successful Data Governance Team";
	private String regulatoryComplianceWebLink = "https://www.precisely.com/blog/data-security/top-regulatory-compliance-frameworks";

	@BeforeMethod
	public void before() {
		preciselyHomePage = pages().getPreciselyHomePage();
		preciselyGetInTouchPage = pages().getContactPage();
		fourStepsToBuildingGovernanceTeamPage = pages().getFourStepsGovernanceTeamPage();
	}

	@Test
	public void verifyEnterCorrectDetailsInGetInTouchPage_successfullyMessageIsShown() {

		preciselyHomePage.clickGetInTouchMenuButton();

		preciselyGetInTouchPage.enterAndSubmitContactDetails(interestedIn, firstName, lastName, jobTitle, companyName,
				companyEmailId, companyPhoneNumber, country, province, comments);
		preciselyGetInTouchPage.validateThankYouScreen(thankYouMessage);
	}

	@Test
	public void verifyRegulatoryComplianceWebLinkisPresentedInFourStepsToBuildingGovernanceTeamPage_successfulLinkIsShown() {

		preciselyHomePage.clickSearchIconButton();
		preciselyHomePage.enterSearchText(searchText);

		fourStepsToBuildingGovernanceTeamPage.clickSearchString(governanceTeamText);
		fourStepsToBuildingGovernanceTeamPage.checkGovernanceTeamPageTitle();
		fourStepsToBuildingGovernanceTeamPage.verifyRegulatoryComplianceLinkText(regulatoryComplianceWebLink);
	}

}