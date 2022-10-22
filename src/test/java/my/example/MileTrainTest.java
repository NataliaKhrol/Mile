package my.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class MileTrainTest {

    By COUNTRY_ENTRY = By.cssSelector("[name=ss]");
    By SUBMIT_BUTTON = By.cssSelector("[type=submit]");
    By HOTEL_NAME = By.cssSelector("[data-testid=title]");

    @Test
    public void searchForItem() {
        // 1. Открыть браузер
        // 2. Зайти на сайт
        ChromeOptions options = new ChromeOptions();
        // options.setHeadless(true);
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://booking.com");
//private By TITLE_BUTTON = By.cssSelector("//div[contains(text(), 'IntercityHotel Dubai Jaddaf Waterfront')]");


        driver.findElement(COUNTRY_ENTRY).sendKeys("Dubai");
        driver.findElement(SUBMIT_BUTTON).click();


       List<WebElement> elements = driver.findElements(By.cssSelector("[data-testid=title]"));
       for (int i=0; i < elements.size(); i++) {
           System.out.println(elements.get(i).getAttribute("data-testid"));
        //   assertTrue(element.getText().contains("The First Collection at Jumeirah Village"));
       }
     //   System.out.println(List.toString(bb));
     //   boolean isPres = driver.findElements(By.cssSelector("[data-testid=title]")).contains("The First Collection at Jumeirah Village");

     //   assertTrue(isPres, "No such a hotel");

    }
}

