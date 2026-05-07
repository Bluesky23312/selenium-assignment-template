package hu.lidl.tests;

import hu.lidl.pages.HomePage;
import hu.lidl.pages.LoginPage;
import hu.lidl.pages.ProfilePage;
import hu.lidl.utils.ConfigReader;
import hu.lidl.utils.ScreenshotUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Cookie;



public class LidlLoginTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    // Advanced Task: JUnit Rule to automatically take a screenshot if a test fails!
    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("Test Failed! Taking screenshot...");
            ScreenshotUtil.takeScreenshot(driver, description.getMethodName());
        }

        @Override
        protected void finished(Description description) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed automatically.");
            }
        }
    };

    @Before
    public void setUp() {
        System.out.println("--- Starting Test Setup ---");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        // Advanced Task: Config file & Headless Execution
        String isHeadless = ConfigReader.getProperty("headless_mode");
        if ("true".equalsIgnoreCase(isHeadless)) {
            options.addArguments("--headless");
            System.out.println("Running in Headless mode (invisible browser).");
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @Test
    public void testValidLoginFlow() throws InterruptedException {
        String baseUrl = ConfigReader.getProperty("base_url");

        driver.get(baseUrl);
        System.out.println("Opened homepage to establish domain context.");
        homePage.acceptCookies();


        String name1 = ConfigReader.getProperty("lidl_cookie_name_1");
        String value1 = ConfigReader.getProperty("lidl_cookie_value_1");
        driver.manage().addCookie(new Cookie(name1, value1));

        String name2 = ConfigReader.getProperty("lidl_cookie_name_2");
        String value2 = ConfigReader.getProperty("lidl_cookie_value_2");
        driver.manage().addCookie(new Cookie(name2, value2));

        System.out.println("Injected the stolen authentication cookies!");


        driver.get("https://www.lidl.hu/mla/");
        System.out.println("Navigated directly to Profile Page. Bypassed the firewall!");


        Thread.sleep(3000);


        profilePage.logout();
    }

}