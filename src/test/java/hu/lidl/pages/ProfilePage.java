package hu.lidl.pages;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    private final By logoutBtn = By.xpath("//button[contains(., 'Kijelentkezés')]");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        System.out.println("Preparing to log out...");
        try {
            Thread.sleep(2000);

            WebElement logoutElement = wait.until(ExpectedConditions.presenceOfElementLocated(logoutBtn));

            JavascriptExecutor js = (JavascriptExecutor) driver;


            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", logoutElement);
            System.out.println("Scrolled element into view.");
            Thread.sleep(1000);


            js.executeScript("arguments[0].click();", logoutElement);
            System.out.println("Clicked the Logout button using JS Executor!");

        } catch (Exception e) {
            System.out.println("Failed to log out. Even JS click failed!");
            e.printStackTrace();
        }
    }
}