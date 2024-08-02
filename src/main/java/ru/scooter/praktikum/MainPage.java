package ru.scooter.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;

    public static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    public final static By HEAD_ORDER_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g']"); // Кнопка "Заказать"  сверху страницы
    public final static By CENTRAL_ORDER_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); //Кнопка "Заказать" в центре страницы
    private final static By STATUS_ORDER_BUTTON = By.xpath(".//button[@class='Header_Link__1TAG7']"); // кнопка "Статус заказа"
    private final static By COOKIE_BUTTON = By.id("rcc-confirm-button"); // кнопка куки "да все привыкли"

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void open() {
        driver.get(PAGE_URL);
    }
    public void clickHeadOrderButton(){
        driver.findElement(HEAD_ORDER_BUTTON).click();
    }

    public void clickCentralOrderButton(){
        driver.findElement(CENTRAL_ORDER_BUTTON).click();
    }

    public void clickOrderButton(By xpathOrderButton) {
        driver.findElement(xpathOrderButton).click();
    }

    public void clickStatusOrderButton(){
        driver.findElement(STATUS_ORDER_BUTTON).click();
    }

    public void clickCookieButton(){
        driver.findElement(COOKIE_BUTTON).click();
    }

    public void clickElement (By element){
        driver.findElement(element).click();
    }

    public void clickQuestion(String question){
        String questionsList = String.format(".//div[@class='accordion']//*[text()='%s']", question);
        driver.findElement(By.xpath(questionsList)).click();
    }

    public void verifyAnswer(String answer){
        String answersList = String.format(".//div[@class='accordion']//*[text()='%s']", answer);
        String answerText = driver.findElement(By.xpath(answersList)).getText();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(answersList), answerText));
    }

    public void scrollDown(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(CENTRAL_ORDER_BUTTON));
    }
}