package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CareersPage {
    private WebDriver driver;

    @FindBy(id = "career-our-location")
    private WebElement locationsBlock;

    @FindBy(id = "career-find-our-calling")
    private WebElement teamsBlock;

    @FindBy(xpath = "(//div[@class='elementor-container elementor-column-gap-default'])[6]")
    private WebElement lifeAtInsiderBlock;


    public CareersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyPageSections() {
        Assert.assertTrue(locationsBlock.isDisplayed(), "Locations block is not displayed");
        Assert.assertTrue(teamsBlock.isDisplayed(), "Teams block is not displayed");
        Assert.assertTrue(lifeAtInsiderBlock.isDisplayed(), "Life at Insider block is not displayed");
    }

    public void navigateToQAJobs() {
        driver.get("https://useinsider.com/careers/quality-assurance/");
    }

    public void clickSeeAllQAJobs() {
        // Implementation for clicking "See all QA jobs"
        WebElement seeAllQAJobs = driver.findElement(org.openqa.selenium.By.xpath("//a[contains(text(),'See all QA jobs')]"));
        seeAllQAJobs.click();
    }
}