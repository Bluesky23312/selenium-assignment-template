package hu.lidl.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import java.io.File;

public class ScreenshotUtil {

    // Method to capture and save a screenshot
    public static void takeScreenshot(WebDriver driver, String testName) {
        try {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File("./build/screenshots/" + testName + ".png");
            FileHandler.copy(source, destination);
            System.out.println("Screenshot saved to: " + destination.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
}