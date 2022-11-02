package pages;

import io.qameta.allure.Step;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    By ITEM1_SEARCH = By.xpath("//a[contains(text(),'Отделочные материалы')]");
    By ITEM1_CONFIRM = By.xpath("//a[contains(text(),'Напольные покрытия')]");
    By ITEM1_CHOICE = By.xpath("//h2[contains(text(),'Искусственная трава')]");
    By ITEM1_PICKUP = By.cssSelector("[title=\"Искусственная трава Ricco ПП 12 DECO 30 м x 1,33 м\"]");
    By ADD_CART = By.xpath("//span[contains(text(),'В корзину')]");
    By NOTIFICATION_MESSAGE = By.cssSelector("[class=\"popup-item add-cart-form-popup\"]");
    By CHOOSE_ITEM = By.cssSelector("[title=\"Электрическая плита Zanussi ZCV9540H1W\"]");
    By QUANTITY_SCROLL = By.cssSelector("[class=\"dec plus\"]");
    By ITEM_ADDED = By.xpath("//div[contains(text(),'Товар добавлен в корзину')]");
    By CART_SWITCH = By.xpath("//a[contains(text(),'Перейти в корзину')]");
    By TOTAL_COST = By.cssSelector("[class=\"total-price\"]");
    By BUY_CLICK = By.xpath("//div[contains(text(),'Купить быстро')]");
    By CONFIRM_MESSAGE = By.cssSelector("[class=\"quick-order-wrap\"]");

@Step("Choose the required items")
    public CartPage chooseItem() {
        driver.findElement(CATALOGUE_BUTTON).click();
        driver.findElement(ITEM1_SEARCH).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ITEM1_CONFIRM)).click();
        driver.findElement(ITEM1_CHOICE).click();
        driver.findElement(ITEM1_PICKUP).click();
        return this;
    }
@Step("Add items to the shopping cart")
    public CartPage addToCart() {
        driver.findElement(ADD_CART).click();
        return this;
    }
@Step("Confirm that items were added to the cart")
    public boolean checkOpen() {
        boolean isOpened = driver.findElement(NOTIFICATION_MESSAGE).isDisplayed();
        return isOpened;
    }
@Step("Open the specific page")
    public CartPage open() {
        driver.get(BASE_URL + "/vendors/zanussi/");
        return this;
    }
@Step("Increase the number of goods")
    public CartPage addQuantity() {
        driver.findElement(CHOOSE_ITEM).click();
        driver.findElement(QUANTITY_SCROLL).click();
        return this;
    }
@Step("Switch to the shopping cart")
    public CartPage goToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ITEM_ADDED));
        driver.findElement(CART_SWITCH).click();
        return this;
    }
@Step("Calculation of Total cost")
    public String checkTotal() {
        String totalCost = driver.findElement(TOTAL_COST).getText();
        return totalCost;
    }
@Step("Confirmation of purchase")
    public CartPage buy() {
        driver.findElement(BUY_CLICK).click();
        return this;
    }
@Step("Notification")
    public boolean checkPurchase() {
        boolean isBought = driver.findElement(CONFIRM_MESSAGE).isDisplayed();
        return isBought;
    }
}
