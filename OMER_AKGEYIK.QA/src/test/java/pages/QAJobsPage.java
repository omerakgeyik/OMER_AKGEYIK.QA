// src/test/java/pages/QAJobsPage.java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class QAJobsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//span[@id='select2-filter-by-location-container']")
    private WebElement locationFilter;

    @FindBy(xpath = "//span[@id='select2-filter-by-department-container']")
    private WebElement departmentFilter;

    @FindBy(css = ".jobs-list .job-item, .position-list-item, [class*='job-listing']")
    private List<WebElement> jobListings;

    @FindBy(xpath = "//a[contains(text(),'View Role')]")
    private List<WebElement> viewRoleButtons;

    public QAJobsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void waitForDepartmentFilterToBeQualityAssurance() {
        try {
            // We expect the Department filter to change to "Quality Assurance"
            wait.until(ExpectedConditions.attributeContains(
                    By.xpath("//span[@id='select2-filter-by-department-container']"),"title","Quality Assurance"
            ));

        } catch (Exception e) {
            System.out.println("Error while expecting Department filter to be set to 'Quality Assurance':" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void filterJobsByLocation(String location) {
        try {
            // Click on the location filter
            wait.until(ExpectedConditions.elementToBeClickable(locationFilter));
            locationFilter.click();
            // Find the Location option and click on it
            WebElement locationOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(@class, 'select2-results__option') and contains(text(), '" + location + "')]")
            ));
            locationOption.click();
            // Wait for filtering results to load
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight / 4);");
            waitForJobsToLoad();
        } catch (Exception e) {
            System.out.println("Error setting location filter: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void waitForJobsToLoad() {
        try {
            // Wait for job lists to load
            wait.until(ExpectedConditions.visibilityOfAllElements(jobListings));
        } catch (Exception e) {
            System.out.println("Error while loading job lists:" + e.getMessage());
        }
    }

    public void verifyJobListings() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(jobListings));
            Assert.assertTrue(jobListings.size() > 0, "No job listings found after filtering");
        } catch (Exception e) {
            System.out.println("Error verifying job listings: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void verifyJobDetails() {
        try {
            for (WebElement job : jobListings) {
                wait.until(ExpectedConditions.visibilityOf(job));

                String position = findElementText(job, ".position-title, .job-title, [class*='title']");
                String jobDepartment = findElementText(job, ".department, .job-department, [class*='department']");
                String jobLocation = findElementText(job, ".location, .job-location, [class*='location']");

                Assert.assertTrue(position.toLowerCase().contains("quality assurance"),
                        "Position doesn't contain 'Quality Assurance': " + position);

                Assert.assertTrue(jobDepartment.toLowerCase().contains("quality assurance"),
                        "Department doesn't contain 'Quality Assurance': " + jobDepartment);

                Assert.assertTrue(jobLocation.toLowerCase().contains("ıstanbul"),
                        "Location doesn't contain 'Istanbul': " + jobLocation);
            }
        } catch (Exception e) {
            System.out.println("Error verifying job details: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private String findElementText(WebElement parent, String cssSelector) {
        try {
            return parent.findElement(By.cssSelector(cssSelector)).getText();
        } catch (Exception e) {
            return "Not found";
        }
    }

    public void clickViewRoleAndVerifyRedirect() {
        try {
            if (!viewRoleButtons.isEmpty()) {
                String originalWindow = driver.getWindowHandle();
                viewRoleButtons.getFirst().click();

                // Wait for new window/tab
                wait.until(ExpectedConditions.numberOfWindowsToBe(2));

                // Switch to new tab
                for (String windowHandle : driver.getWindowHandles()) {
                    if (!originalWindow.contentEquals(windowHandle)) {
                        driver.switchTo().window(windowHandle);
                        break;
                    }
                }

                // Wait for new page to load
                wait.until(ExpectedConditions.urlContains("lever.co"));
                Assert.assertTrue(driver.getCurrentUrl().contains("lever.co"),
                        "Not redirected to Lever application form. Current URL: " + driver.getCurrentUrl());
            }
        } catch (Exception e) {
            System.out.println("Error clicking View Role: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}