// src/test/java/tests/InsiderCareersTest.java
package tests;

import org.testng.annotations.Test;
import pages.*;
import utilities.BaseTest;

public class InsiderCareersTest extends BaseTest {

    @Test
    public void testInsiderCareersQAJobs() {
        try {
            // Step 1: Visit insider.com and check home page
            HomePage homePage = new HomePage(driver);
            homePage.navigateToHomePage();

            // Step 2: Navigate to Careers and verify sections
            homePage.navigateToCareersPage();
            CareersPage careersPage = new CareersPage(driver);
            careersPage.verifyPageSections();

            // Step 3: Go to QA jobs page, filter and check job list
            careersPage.navigateToQAJobs();
            careersPage.clickSeeAllQAJobs();

            QAJobsPage qaJobsPage = new QAJobsPage(driver);

            // Wait for the Department filter to be set automatically
            qaJobsPage.waitForDepartmentFilterToBeQualityAssurance();

            // Just set the location filter
            qaJobsPage.filterJobsByLocation("Istanbul, Turkiye");
            qaJobsPage.verifyJobListings();

            // Step 4: Verify job details
            qaJobsPage.verifyJobDetails();

            // Step 5: Click View Role and verify redirect
            qaJobsPage.clickViewRoleAndVerifyRedirect();

        } catch (Exception e) {
            System.out.println("Test failed with error: " + e.getMessage());
            throw e;
        }
    }
}