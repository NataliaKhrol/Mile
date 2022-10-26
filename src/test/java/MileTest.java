import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MileTest extends BaseTest{

    public static final String BASE_URL = "https://mile.by";
    private final By SEARCH_BUTTON = By.cssSelector("[class=\"header-search\"]");
    private final By SEARCH_CONFIRM = By.cssSelector("[type=\"submit\"]");
    private final By SEARCH_RESULT = By.xpath("//h1[contains(text(),'Результаты по запросу: замок')]");
    private final By CATALOGUE_BUTTON = By.cssSelector("[class=\"new-but new-catalog\"]");
    private final By ITEM1_SEARCH = By.xpath("//a[contains(text(),'Отделочные материалы')]");
    private final By ITEM1_CONFIRM = By.xpath("//a[contains(text(),'Напольные покрытия')]");
    private final By ITEM1_CHOICE = By.xpath("//h2[contains(text(),'Искусственная трава')]");
    private final By ITEM1_PICKUP = By.cssSelector("[title=\"Искусственная трава Ricco ПП 12 DECO 30 м x 1,33 м\"]");
    private final By ADD_CART = By.xpath("//span[contains(text(),'В корзину')]");
    private final By NOTIFICATION_MESSAGE = By.xpath("//div[contains(text(),'Товар добавлен в корзину')]");
    private final By ITEM2_SEARCH = By.xpath("//a[contains(text(),'Спорт и активный отдых')]");
    private final By ITEM2_CONFIRM = By.xpath("//a[contains(text(),'Самокаты, велосипеды, скейтборды')]");
    private final By ITEM3_SEARCH = By.xpath("(//a[contains(text(),'Декор')])[3]");
    private final By ITEM3_CHOICE = By.xpath("//a[contains(text(),'Шторы')]");
    private final By ITEM3_CONFIRM = By.xpath("//a[contains(text(),'Жалюзи горизонтальные СГЖМ-300/13 48/160')]");
    private final By BUY_CLICK = By.xpath("//span[contains(text(),'Купить в 1 клик')]");
    private final By CONFIRM_MESSAGE = By.xpath("//span[contains(text(),'обработку персональных данных')]");

    @Test
    public void searchForItem() {

        driver.get(BASE_URL);
        driver.findElement(SEARCH_BUTTON).sendKeys("замок");
        driver.findElement(SEARCH_CONFIRM).click();//div[contains(text(),'7.6')]
        String result = driver.findElement(SEARCH_RESULT).getText();
        assertEquals(result, "Результаты по запросу: замок", "The search wasn't performed");
    }

    @Test
    public void chooseItem() {

        driver.get(BASE_URL);
        driver.findElement(CATALOGUE_BUTTON).click();
        driver.findElement(ITEM1_SEARCH).click();
        driver.findElement(ITEM1_CONFIRM).click();
        driver.findElement(ITEM1_CHOICE).click();
        driver.findElement(ITEM1_PICKUP).click();
        driver.findElement(ADD_CART).click();
        boolean isOpened = driver.findElement(NOTIFICATION_MESSAGE).isDisplayed();
        assertTrue(isOpened, "The item failed to be added to the Cart");
    }

   @Test
    public void checkTotal() {

        driver.get(BASE_URL + "/vendors/zanussi/");
        driver.findElement(By.cssSelector("[title=\"Электрическая плита Zanussi ZCV9540H1W\"]")).click();
        driver.findElement(By.cssSelector("[class=\"dec plus\"]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'В корзину')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Товар добавлен в корзину')]")));
        driver.findElement(By.xpath("//a[contains(text(),'Перейти в корзину')]")).click();
        String totalCost = driver.findElement(By.cssSelector("[class=\"total-price\"]")).getText();
        assertEquals(totalCost, "2198 руб.", "Total calculated incorrectly");
    }

    @Test
    public void switchToPage() {

        driver.get(BASE_URL);
        driver.findElement(CATALOGUE_BUTTON).click();
        driver.findElement(ITEM2_SEARCH).click();
        String newPage = driver.findElement(ITEM2_CONFIRM).getText();
        assertEquals(newPage, "Самокаты, велосипеды, скейтборды", "New page failed to open");
    }

    @Test
    public void buyItem() {

        driver.get(BASE_URL);
        driver.findElement(CATALOGUE_BUTTON).click();
        driver.findElement(ITEM3_SEARCH).click();
        driver.findElement(ITEM3_CHOICE).click();
        driver.findElement(ITEM3_CONFIRM).click();
        driver.findElement(BUY_CLICK).click();
        boolean isBought = driver.findElement(CONFIRM_MESSAGE).isDisplayed();
        assertTrue(isBought, "The item failed to be bought");
    }
}