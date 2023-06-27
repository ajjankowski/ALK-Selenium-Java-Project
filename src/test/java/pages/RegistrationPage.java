package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationPage {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//span[@tabindex='-1' and text()='Zaakceptuj wszystkie']")
    private List<WebElement> cookieAcceptButton;

    @FindBy(id = "user_register_username")
    private WebElement loginInput;

    @FindBy(xpath = "//img[@src='/images/notok.gif']")
    private WebElement loginNotUniqueIndicator;

    @FindBy(id = "user_register_gender_male_0")
    private WebElement radioButtonMale;

    @FindBy(id = "user_register_gender_male_1")
    private WebElement radioButtonFemale;

    @FindBy(id = "user_register_email")
    private WebElement emailInput;

    @FindBy(xpath = "//select[@id='user_register_birthday_day']/option")
    private List<WebElement> birthdayDay;

    @FindBy(xpath = "//select[@id='user_register_birthday_month']/option")
    private List<WebElement> birthdayMonth;

    @FindBy(xpath = "//select[@id='user_register_birthday_year']/option")
    private List<WebElement> birthdayYear;

    @FindBy(id = "user_register_accept_rules")
    private WebElement acceptRules;

    @FindBy(xpath = "//div[@class='recaptcha-checkbox-border']")
    private WebElement captcha;

    @FindBy(id = "user_register_accept_rodo")
    private WebElement acceptRodo;

    @FindBy(id = "user_register_submit")
    private WebElement submitButton;

    public void acceptCookies() {
        cookieAcceptButton.get(1).click();
    }

    public void registerUser(String login, String gender, String email,
                             int day, int month, int year, boolean captcha) {
        loginInput.click();
        loginInput.clear();
        loginInput.sendKeys(login);
        if (gender.equals("male")) {
            if (!radioButtonMale.isSelected()) {
                radioButtonMale.click();
            }
        } else {
            if (!radioButtonFemale.isSelected()) {
                radioButtonFemale.click();
            }
        }
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);
        birthdayDay.get(day).click();
        birthdayMonth.get(month).click();
        birthdayYear.get(year).click();
        acceptRules.click();
        acceptRodo.click();
        if (captcha) {
            clickCaptcha();
        }
        submitButton.click();
    }

    public void clickCaptcha() {
        WebElement iframe = driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
        driver.switchTo().defaultContent();
    }
}