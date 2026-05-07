package hu.lidl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.JavascriptExecutor;

public class HomePage extends BasePage {

    private final By acceptCookiesBtn = By.xpath("//button[contains(@class, 'cookie-alert-extended-button') or contains(@id, 'onetrust-accept-btn')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Action1: Access to the website
    public void navigateTo() {
        driver.get("https://www.lidl.hu/");
    }

    // Action2: Click Cookie window
    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn)).click();
            System.out.println("Click Cookie window successfully！");
        } catch (Exception e) {
            System.out.println("No Cookie window, or locating is unsuccessful");
        }
    }

    // Advanced XPath: Finding the login button in the header navigation
    private final By loginNavBtn = By.xpath("//span[contains(@class, 'm-icon--user')]");

    // Action 3: Click the login icon to navigate to the login page
    public void clickLoginIcon() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginNavBtn)).click();
            System.out.println("Clicked on the Login icon in the navigation bar successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click the Login icon. Element might be hidden or locator needs update.");
        }
    }

    // Action 4: Verify the page title (Basic task)
    public void verifyPageTitle() {
        String title = driver.getTitle();
        System.out.println("The page title is: " + title);
        if (title.contains("Lidl")) {
            System.out.println("Title verification passed.");
        }
    }

    // Action 5: Scroll to the bottom and back to top using JavaScript (Advanced task)
    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        System.out.println("Scrolled to the bottom of the page using JavaScriptExecutor.");

        try { Thread.sleep(1000); } catch (Exception e) {} // 停顿1秒让你看清

        js.executeScript("window.scrollTo(0, 0)");
        System.out.println("Scrolled back to the top.");
    }
}