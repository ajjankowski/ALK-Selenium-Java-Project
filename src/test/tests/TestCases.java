package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;
import pages.RegistrationPage;
import utils.ExcelReader;
import utils.ScreenshotTaker;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

// TODO:
//       TC1: Invalid user registration to check the validation of the registration form
//       TC2: Invalid user login to validate the login form
//       TC3: Attempting to register an existing user
//       TC4: Correct user log-in for the given data
//       TC5: Logged-in user has access to profile settings

public class TestCases extends BaseTest {

    @Parameters({"gender", "email", "day", "month", "year"})
    @Test
    public void testInvalidUserRegistration(String gender, String email,
                                            int day, int month, int year) throws IOException {
        String testName = "InvalidUserRegistration";
        ExtentTest test = extentReports.createTest(testName);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        int randomNumber = (int) (Math.random() * 1000);
        String login = "login" + randomNumber;
        String link = "https://joemonster.org/rejestracja";
        driver.get(link);

        registrationPage.acceptCookies();
        assertEquals(link, driver.getCurrentUrl());
        registrationPage.registerUser(
                login, gender, email,
                day, month, year, true);
        assertEquals(link, driver.getCurrentUrl());
        test.log(Status.PASS, "User cannot register with wrong email",
                ScreenshotTaker.screenshotForReport(1, testName, driver));
    }

    @Test
    public void testInvalidUserLogin() throws IOException {
        String testName = "InvalidUserLogin";
        ExtentTest test = extentReports.createTest(testName);
        String link = "https://joemonster.org/logowanie";
        driver.get(link);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acceptCookies();
        assertEquals(link, driver.getCurrentUrl());

        ExcelReader excel = new ExcelReader(
                "src/test/resources/LoginData.xlsx", "invalidData");
        int numberOfRows = excel.getRowCount();
        for (int i = 0; i < numberOfRows; i++) {
            String login = excel.getCellData(i, 0);
            String password = excel.getCellData(i, 1);
            loginPage.loginUser(login, password);
            WebDriverWait wait = new WebDriverWait(driver, 5);
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//b[text()='Niepoprawny login lub hasło :-(']")));
                test.log(Status.PASS, "Assertion " + (i + 1) + " passed",
                        ScreenshotTaker.screenshotForReport(i + 1, testName, driver));
            } catch (Exception e) {
                test.log(Status.FAIL, "Assertion " + (i + 1) + " failed",
                        ScreenshotTaker.screenshotForReport(i + 1, testName, driver));
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testAttemptToRegisterExistingUser() throws IOException {
        String testName = "AttemptToRegisterExistingUser";
        ExtentTest test = extentReports.createTest(testName);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        String link = "https://joemonster.org/rejestracja";
        driver.get(link);
        registrationPage.acceptCookies();
        assertEquals(link, driver.getCurrentUrl());

        ExcelReader excel = new ExcelReader(
                "src/test/resources/LoginData.xlsx", "validData");
        String login = excel.getCellData(0, 0);
        registrationPage.registerUser(
                login, "male", "email@correct.pl",
                11, 4, 20, false);
        assertEquals(link, driver.getCurrentUrl());

        test.log(Status.PASS, "User cannot register with existing login",
                ScreenshotTaker.screenshotForReport(1, testName, driver));
    }

    @Test
    public void testCorrectUserLogin() throws IOException {
        String testName = "CorrectUserLogin";
        ExtentTest test = extentReports.createTest(testName);
        String link = "https://joemonster.org/logowanie";
        driver.get(link);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acceptCookies();
        assertEquals(link, driver.getCurrentUrl());

        ExcelReader excel = new ExcelReader(
                "src/test/resources/LoginData.xlsx", "validData");
        String login = excel.getCellData(0, 0);
        String password = excel.getCellData(0, 1);
        loginPage.loginUser(login, password);
        assertEquals("https://joemonster.org/user.php", driver.getCurrentUrl());
        test.log(Status.PASS, "User correctly logged-in",
                ScreenshotTaker.screenshotForReport(1, testName, driver));
    }

    @DataProvider(name = "loginData")
    public Object[][] createData() {
        return new Object[][] {
                {"seleniumtest", "theboco10"}
        };
    }

    @Test(dataProvider = "loginData")
    public void testAccessToProfileSettings(String login, String password) throws IOException {
        String testName = "AccessToProfileSettings";
        ExtentTest test = extentReports.createTest(testName);
        String link = "https://joemonster.org/logowanie";
        driver.get(link);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.acceptCookies();
        assertEquals(link, driver.getCurrentUrl());

        loginPage.loginUser(login, password);
        assertEquals("https://joemonster.org/user.php", driver.getCurrentUrl());

        profilePage.assertUrl("https://joemonster.org/bojownik/seleniumtest", profilePage.omnieButtonCheck(), 1, testName);
        test.log(Status.PASS, "'O mnie' link correct", ScreenshotTaker.screenshotForReport(1, testName, driver));

//        profilePage.assertUrl("https://joemonster.org/bojownik/seleniumtest/nadeslane", profilePage.nadeslaneButtonCheck(), 2, testName);
//        test.log(Status.PASS, "'Nadesłane' link correct", ScreenshotTaker.screenshotForReport(2, testName, driver));
//
//        profilePage.assertUrl("https://joemonster.org/user.php?op=extend&previous_site=%2Fbojownik%2Fseleniumtest%2Fkomentarze", profilePage.komentarzeButtonCheck(), 3, testName);
//        test.log(Status.PASS, "'Komentarze' link correct", ScreenshotTaker.screenshotForReport(3, testName, driver));
//
//        profilePage.assertUrl("https://joemonster.org/bojownik/seleniumtest", profilePage.szaffaButtonCheck(), 4, testName);
//        test.log(Status.PASS, "'Szaffa' link correct", ScreenshotTaker.screenshotForReport(4, testName, driver));
//
//        profilePage.assertUrl("https://joemonster.org/bojownik/seleniumtest/fani", profilePage.faniButtonCheck(), 5, testName);
//        test.log(Status.PASS, "'Fani' link correct", ScreenshotTaker.screenshotForReport(5, testName, driver));
//
//        profilePage.assertUrl("https://joemonster.org/user.php?op=extend&previous_site=%2Fbojownik%2Fkluby", profilePage.klubyButtonCheck(), 6, testName);
//        test.log(Status.PASS, "'Kluby' link correct", ScreenshotTaker.screenshotForReport(6, testName, driver));
//
//        profilePage.assertUrl("https://joemonster.org/bojownik/seleniumtest/ulubione", profilePage.okejkiButtonCheck(), 7, testName);
//        test.log(Status.PASS, "'Okejki' link correct", ScreenshotTaker.screenshotForReport(7, testName, driver));
//
//        profilePage.assertUrl("https://joemonster.org/user.php?op=extend&previous_site=%2Fblog%2Fseleniumtest%2Fedytuj-blog%2F", profilePage.blogButtonCheck(), 8, testName);
//        test.log(Status.PASS, "'Blog' link correct", ScreenshotTaker.screenshotForReport(8, testName, driver));
//
//        profilePage.assertUrl("https://joemonster.org/bojownik/seleniumtest/subskrypcje", profilePage.subskrypcjeButtonCheck(), 9, testName);
//        test.log(Status.PASS, "'Subskrypcje' link correct", ScreenshotTaker.screenshotForReport(9, testName, driver));
    }
}