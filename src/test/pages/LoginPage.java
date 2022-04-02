package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.BaseTest;

import java.util.List;

public class LoginPage extends BaseTest {

    public LoginPage(WebDriver driver) {
        BaseTest.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@tabindex='-1' and text()='Zaakceptuj wszystkie']")
    private List<WebElement> cookieAcceptButton;

    @FindBy(name = "_username")
    private WebElement userNameInput;

    @FindBy(name = "_password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Zaloguj się']")
    private WebElement loginButton;

    @FindBy(xpath = "//b[text()='Niepoprawny login lub hasło :-(']")
    private WebElement wrongLoginAlert;

    public void acceptCookies() {
        cookieAcceptButton.get(1).click();
    }

    public void loginWrongCredentials(String username, String password) {
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(username);
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}