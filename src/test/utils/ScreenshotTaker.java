package utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotTaker {

    public String takeScreenshot(int screenNumber, String testName, WebDriver driver) throws IOException {
        File scrFile = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
        String path = "src/test/resources/screenshots/" + testName + "/Screen_"
                + screenNumber + "_" + testName + ".png";
        FileUtils.copyFile(scrFile, new File(path));
        return path;
    }

    public MediaEntityModelProvider screenshotForReport(int screenNumber, String testName, WebDriver driver) throws IOException {
        String path = new ScreenshotTaker().takeScreenshot(screenNumber, testName, driver);
        return MediaEntityBuilder.createScreenCaptureFromPath(path).build();
    }
}