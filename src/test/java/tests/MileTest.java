package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.MainPage;
import tests.BaseTest;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MileTest extends BaseTest {

    @Test
    public void searchForItem() {

        new BasePage(driver).open();
        new MainPage(driver).search("замок");
        assertEquals(mainPage.checkResult(), "Результаты по запросу: замок", "The search wasn't performed");
    }

    @Test
    public void switchToPage() {

        basePage.open();
        mainPage.switchTo();
        assertEquals(mainPage.checkSwitchResult(), "Самокаты, велосипеды, скейтборды", "New page failed to open");
    }

    @Test
    public void addToCart() {

        basePage.open();
        cartPage.chooseItem()
                .addToCart();
        assertTrue(cartPage.checkOpen(), "The item failed to be added to the Cart");
    }

    @Test
    public void checkTotal() {

        cartPage.open()
                .addQuantity()
                .addToCart()
                .goToCart();
        assertEquals(cartPage.checkTotal(), "2198 руб.", "Total calculated incorrectly");
    }

    @Test
    public void buyItem() {

        cartPage.open()
                .addQuantity()
                .addToCart()
                .goToCart()
                .buy();
        assertTrue(cartPage.checkPurchase(), "The item failed to be bought");
    }
}
