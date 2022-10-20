package my.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MileSiteTest {
    private final By SEARCH_BUTTON = By.cssSelector("[class=\"header-search\"]");
    private final By SEARCH_CONFIRM = By.cssSelector("[type=\"submit\"]");
    private final By SEARCH_RESULT = By.xpath("//h1[contains(text(),'Результаты по запросу: замок')]");
    private final By CATALOGUE_BUTTON = By.xpath("//span[contains(text(),'каталог')]");
    private final By ITEM1_SEARCH = By.xpath("//a[contains(text(),'Отделочные материалы')]");
    private final By ITEM1_CONFIRM = By.xpath("//h2[contains(text(),'Напольные покрытия')]");
    private final By ITEM1_CHOICE = By.xpath("//h2[contains(text(),'Искусственная трава')]");
    private final By ITEM1_PICKUP = By.cssSelector("[title=\"Искусственная трава Ricco ПП 12 DECO 30 м x 1,33 м\"]");
    private final By ADD_CART = By.xpath("//span[contains(text(),'В корзину')]");
    private final By NOTIFICATION_MESSAGE = By.xpath("//div[contains(text(),'Товар добавлен в корзину')]");
    private final By ITEM2_SEARCH = By.xpath("//a[contains(text(),'Спорт и активный отдых')]");
    private final By ITEM2_CONFIRM = By.xpath("//h1[contains(text(),'Спорт и активный отдых')]");
    private final By ITEM3_SEARCH = By.xpath("//a[contains(text(),'Скобяные изделия')]");
    private final By ITEM3_CONFIRM = By.xpath("//h2[contains(text(),'Почтовые ящики и декоративные элементы для зданий')]");
    private final By ITEM3_CHOICE = By.xpath("//a[contains(text(),'Крепление флюгера Каменный Замок КФКЗ 001')]");
    private final By BUY_CLICK = By.xpath("//span[contains(text(),'Купить в 1 клик')]");
    private final By NAME_ENTRY = By.xpath("(//input[@name=\"NAME\"])[3]");
    private final By PHONE_ENTRY = By.xpath("(//input[@name=\"PHONE\"])[5]");
    private final By BUY_BUTTON = By.xpath("//button[contains(text(),'Купить')]");
    private final By CONFIRM_MESSAGE = By.className("quick-order-wrap");


    @Test
    public void searchForItem() {
        // 1. Открыть браузер
        // 2. Зайти на сайт
        ChromeOptions options = new ChromeOptions();
        // options.setHeadless(true);
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.get("https://mile.by");

        browser.findElement(SEARCH_BUTTON).sendKeys("замок");
        browser.findElement(SEARCH_CONFIRM).click();//div[contains(text(),'7.6')]
        String result = browser.findElement(SEARCH_RESULT).getText();
        // System.out.println(result);
        assertEquals(result, "Результаты по запросу: замок", "The search wasn't performed");
        browser.quit();
    }

    @Test
    public void chooseItem() {

        ChromeOptions options = new ChromeOptions();
        // options.setHeadless(true);
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.get("https://mile.by");

        browser.findElement(CATALOGUE_BUTTON).click();
        browser.findElement(ITEM1_SEARCH).click();
        browser.findElement(ITEM1_CONFIRM).click();
        browser.findElement(ITEM1_CHOICE).click();
        browser.findElement(ITEM1_PICKUP).click();
        browser.findElement(ADD_CART).click();
        boolean isOpened = browser.findElement(NOTIFICATION_MESSAGE).isDisplayed();
        assertTrue(isOpened, "The item failed to be added to the Cart");
        browser.quit();
    }

    @Test
    public void switchToPage() {

        ChromeOptions options = new ChromeOptions();
        // options.setHeadless(true);
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://mile.by");

        driver.findElement(CATALOGUE_BUTTON).click();
        driver.findElement(ITEM2_SEARCH).click();
        String newPage = driver.findElement(ITEM2_CONFIRM).getText();
        assertEquals(newPage, "Спорт и активный отдых", "New page failed to open");
        driver.quit();
    }

    @Test
    public void buyItem() {

        ChromeOptions options = new ChromeOptions();
        // options.setHeadless(true);
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://mile.by");

        driver.findElement(CATALOGUE_BUTTON).click();
        driver.findElement(ITEM3_SEARCH).click();
        driver.findElement(ITEM3_CONFIRM).click();
        driver.findElement(ITEM3_CHOICE).click();
        driver.findElement(BUY_CLICK).click();
        driver.findElement(NAME_ENTRY).sendKeys("Ann");
        driver.findElement(PHONE_ENTRY).sendKeys("442200000");
        driver.findElement(BUY_BUTTON).click();
        boolean isBought = driver.findElement(CONFIRM_MESSAGE).isDisplayed();
        assertTrue(isBought, "The item failed to be bought");
        driver.quit();
    }
}
