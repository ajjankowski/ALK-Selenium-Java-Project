package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.BaseTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RegistrationPage extends BaseTest {

    public RegistrationPage(WebDriver driver) {
        BaseTest.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@tabindex='-1' and text()='Zaakceptuj wszystkie']")
    private List<WebElement> cookieAcceptButton;

    public void acceptCookies() {
        cookieAcceptButton.get(1).click();
    }

    public void assertUrl(String expectedUrl, String actualUrl) {
        assertEquals(expectedUrl, actualUrl);
    }
}