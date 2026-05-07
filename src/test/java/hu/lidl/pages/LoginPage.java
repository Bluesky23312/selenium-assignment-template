package hu.lidl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    // Advanced XPath for High Score: Using logical OR and dynamic attributes
    private final By emailInput = By.xpath("//input[@type='email' or contains(@name, 'Email')]");
    private final By passwordInput = By.xpath("//input[@type='password' or contains(@name, 'Password')]");
    private final By submitBtn = By.xpath("//button[@type='submit' or contains(@class, 'login-btn')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Action: Perform the login operation
    public void login(String email, String password) {
        System.out.println("Waiting for the login form to load...");

        // Wait for the email field to appear, then type the email
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        System.out.println("Entered email.");

        // Type the password
        driver.findElement(passwordInput).sendKeys(password);
        System.out.println("Entered password.");

        // Click the submit button
        driver.findElement(submitBtn).click();
        System.out.println("Clicked the login submit button.");
    }
}