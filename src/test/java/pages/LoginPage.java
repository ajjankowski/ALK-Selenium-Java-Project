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
    private WebElement cookieAcceptButton;

    @FindBy(name = "_username")
    private WebElement userNameInput;

    @FindBy(name = "_password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@type='submit']")
    private List<WebElement> loginButton;

    public void acceptCookies() {
        cookieAcceptButton.click();
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
