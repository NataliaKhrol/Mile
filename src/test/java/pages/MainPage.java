package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    By SEARCH_BUTTON = By.cssSelector("[class=\"header-search\"]");
    By SEARCH_CONFIRM = By.cssSelector("[type=\"submit\"]");
    By SEARCH_RESULT = By.xpath("//h1[contains(text(),'Результаты по запросу: замок')]");
    By ITEM2_SEARCH = By.xpath("//a[contains(text(),'Спорт и активный отдых')]");
    By ITEM2_CONFIRM = By.xpath("//a[contains(text(),'Самокаты, велосипеды, скейтборды')]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

@Step("Search for the necessary item")
    public MainPage search(String searchItem) {
        driver.findElement(SEARCH_BUTTON).sendKeys(searchItem);
        driver.findElement(SEARCH_CONFIRM).click();//div[contains(text(),'7.6')]
        return this;
    }
@Step("Control the searching result")
    public String checkResult() {
        String result = driver.findElement(SEARCH_RESULT).getText();
        return result;
    }
@Step("Switch to another chapters")
    public MainPage switchTo() {
        driver.findElement(CATALOGUE_BUTTON).click();
        driver.findElement(ITEM2_SEARCH).click();
        return this;
    }
@Step("Switch result")
    public String checkSwitchResult() {
        String newPage = driver.findElement(ITEM2_CONFIRM).getText();
        return newPage;
    }
}
