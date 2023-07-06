package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//span[@tabindex='-1' and text()='Zaakceptuj wszystkie']")
    private List<WebElement> cookieAcceptButton;

    @FindBy(name = "_username")
    private WebElement userNameInput;

    @FindBy(name = "_password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@type='submit']")
    private List<WebElement> loginButton;

    @FindBy(xpath = "//b[text()='Niepoprawny login lub hasło :-(']")
    private WebElement wrongLoginAlert;

    public void acceptCookies() {
        cookieAcceptButton.get(1).click();
    }

    public void loginUser(String username, String password) {
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(username);
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.get(1).click();
    }
}