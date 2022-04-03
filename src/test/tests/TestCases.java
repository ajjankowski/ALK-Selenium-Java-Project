package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegistrationPage;
import utils.ExcelReader;
import utils.ScreenshotTaker;

import java.io.IOException;

// TODO:
//       TC1: Invalid user registration to check the validation of the registration form
//       TC2: Invalid user login to validate the login form
//       TC3: Correct user registration for the given data
//       TC4: Attempting to register an existing user
//       TC5: Correct user log-in for the given data

public class TestCases extends BaseTest {

    @Test
    public void testInvalidUserRegistration() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        String link = "https://joemonster.org/rejestracja";
        driver.get(link);
        registrationPage.acceptCookies();
        registrationPage.assertUrl(link, driver.getCurrentUrl());

        //dodaj wait jak trzeba
        //dodaj isDisplayed do checkboxów
        //dodaj if do zanaczeń
    }

    @Test
    public void testInvalidUserLoginDDT() throws IOException {
        String testName = "InvalidUserLogin";
        ExtentTest test = extentReports.createTest(testName);
        driver.get("https://joemonster.org/logowanie");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acceptCookies();

        ExcelReader excel = new ExcelReader(
                "src/test/resources/wrongLoginData.xlsx", "Sheet1");
        int numberOfRows = excel.getRowCount();
        for (int i = 0; i < numberOfRows; i++) {
            String login = excel.getCellData(i, 0);
            String password = excel.getCellData(i, 1);
            loginPage.loginWrongCredentials(login, password);
            WebDriverWait wait = new WebDriverWait(driver, 5);
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//b[text()='Niepoprawny login lub hasło :-(']")));
                screenshotTaker.takeScreenshot(i, testName, driver);
                test.log(Status.PASS, "Assertion " + i + " passed",
                        new ScreenshotTaker().screenshotForReport(i, testName, driver));
            } catch (Exception e) {
                test.log(Status.FAIL, "Assertion " + i + " failed",
                        new ScreenshotTaker().screenshotForReport(i, testName, driver));
            }
        }
    }

    @Test
    public void testCorrectUserRegistration() {
        //ustaw wait na buttony
        driver.get("https://joemonster.org/rejestracja");
    }

    @Test
    public void testAttemptToRegisterExistingUser() {
        driver.get("https://joemonster.org/rejestracja");
    }

    @Test
    public void testCorrectUserLogin() {
        driver.get("https://joemonster.org/logowanie");
    }
}