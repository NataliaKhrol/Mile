package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.BasePage;
import pages.CartPage;
import pages.MainPage;

public class BaseTest {
    WebDriver driver;
    BasePage basePage;
    MainPage mainPage;
    CartPage cartPage;

    @Parameters({"browser"})
    @BeforeMethod(description = "Opening the browser")
    // 1. Открыть браузер
    public void setup(@Optional("chrome") String browser, ITestContext testContext) {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
              options.setHeadless(true);
            driver = new ChromeDriver(options);
        }
        testContext.setAttribute("driver", driver);
        basePage = new BasePage(driver);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterMethod(alwaysRun = true, description = "Closing the browser")
    public void close() {
        if (driver != null) {
           driver.quit();
        }
    }
}
