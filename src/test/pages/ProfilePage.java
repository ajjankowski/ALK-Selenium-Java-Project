package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ScreenshotTaker;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ProfilePage {
    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//span[@tabindex='-1' and text()='Zaakceptuj wszystkie']")
    private List<WebElement> cookieAcceptButton;

    @FindBy(xpath = "//ul[@class='nav nav-tabs']/li")
    private List<WebElement> panelButtons;

    public void acceptCookies() {
        cookieAcceptButton.get(1).click();
    }

    public String omnieButtonCheck() {
        panelButtons.get(0).click();
        return driver.getCurrentUrl();
    }

    public String nadeslaneButtonCheck() {
        panelButtons.get(1).click();
        return driver.getCurrentUrl();
    }

    public String komentarzeButtonCheck() {
        panelButtons.get(2).click();
        return driver.getCurrentUrl();
    }

    public String szaffaButtonCheck() {
        panelButtons.get(3).click();
        return driver.getCurrentUrl();
    }

    public String faniButtonCheck() {
        panelButtons.get(4).click();
        return driver.getCurrentUrl();
    }

    public String klubyButtonCheck() {
        panelButtons.get(5).click();
        return driver.getCurrentUrl();
    }

    public String okejkiButtonCheck() {
        panelButtons.get(6).click();
        return driver.getCurrentUrl();
    }

    public String blogButtonCheck() {
        panelButtons.get(7).click();
        return driver.getCurrentUrl();
    }

    public String subskrypcjeButtonCheck() {
        panelButtons.get(8).click();
        return driver.getCurrentUrl();
    }

    public void assertUrl(String expectedUrl, String actualUrl, int screenNumber, String testName) throws IOException {
        assertEquals(expectedUrl, actualUrl);
        ScreenshotTaker.screenshotForReport(screenNumber, testName, driver);
    }
}
