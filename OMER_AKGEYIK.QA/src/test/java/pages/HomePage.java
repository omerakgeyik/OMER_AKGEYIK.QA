package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Company')]")
    private WebElement companyMenu;

    @FindBy(xpath = "//a[contains(text(),'Careers')]")
    private WebElement careersOption;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        driver.get("https://useinsider.com/");
        Assert.assertEquals(driver.getTitle(), "#1 Leader in Individualized, Cross-Channel CX — Insider");
    }

    public void navigateToCareersPage() {
        companyMenu.click();
        careersOption.click();
    }
}