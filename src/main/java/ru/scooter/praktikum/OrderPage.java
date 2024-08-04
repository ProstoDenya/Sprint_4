package ru.scooter.praktikum;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderPage {
    private final WebDriver driver;

    private final static By INPUT_ORDER_NAME = By.xpath(".//input[@placeholder='* Имя']");
    private final static By INPUT_ORDER_LAST_NAME = By.xpath(".//input[@placeholder='* Фамилия']");
    private final static By INPUT_ORDER_ADDRESS = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final static By INPUT_ORDER_METRO = By.xpath(".//input[@placeholder='* Станция метро']");
    private final static By INPUT_ORDER_TEL = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    private final static By CENTRAL_ORDER_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); //Кнопка "Заказать" в центре страницы

    private final static By INPUT_ORDER_DATE = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final static By INPUT_ORDER_COUNT_DATE = By.xpath(".//div[@class='Dropdown-placeholder']");
    private final static By SELECT_ORDER_COUNT_DATE = By.xpath(".//div[@class='Dropdown-menu']/div[2]");
    private final static By INPUT_ORDER_COLOUR = By.id("black");
    private final static By INPUT_COMMENT = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    private final static By COMPLETE_ORDER_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); // кнопка "Заказать" на странице "Про аренду"
    private final static By COMPLETE_ORDER_BUTTON_YES = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); //кнопка "Да" на поп-апе "Хотите оформить заказ?"

    private final static By FINAL_ORDER_STATUS = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']"); // информация о заказе на поп-апе "Заказ оформлен"
    private final static By FINAL_ORDER_STATUS_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть статус']"); // кнопка "Посмотреть статус"

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void inputName(String text){
        driver.findElement(INPUT_ORDER_NAME).sendKeys(text);
    }
    public void inputLastName(String text){
        driver.findElement(INPUT_ORDER_LAST_NAME).sendKeys(text);
    }
    public void inputOrderAddress(String text){
        driver.findElement(INPUT_ORDER_ADDRESS).sendKeys(text);
    }
    public void inputOrderMetro(int n){
        driver.findElement(INPUT_ORDER_METRO).click();
        for(int i = 0; i < n; i++) {
            driver.findElement(INPUT_ORDER_METRO).sendKeys(Keys.DOWN);
        }
        driver.findElement(INPUT_ORDER_METRO).sendKeys(Keys.ENTER);
    }
    public void inputOrderTel(String text){
        driver.findElement(INPUT_ORDER_TEL).sendKeys(text);
    }
    public void clickCentralButton(){
        driver.findElement(CENTRAL_ORDER_BUTTON).click();
    }

    public void inputFirstOrderPageAndGo(String name, String lastName, String address, String telephone){
        inputName(name);
        inputLastName(lastName);
        inputOrderAddress(address);
        inputOrderMetro(1);
        inputOrderTel(telephone);
        clickCentralButton();
    }

    public void inputOrderDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = LocalDate.now().plusDays(3).format(formatter); // прибавляем три дня к текущей дате
        driver.findElement(INPUT_ORDER_DATE).sendKeys(date);
        driver.findElement(INPUT_ORDER_DATE).sendKeys(Keys.ENTER);
    }
    public void inputOrderCountDate(){
        driver.findElement(INPUT_ORDER_COUNT_DATE).click();
        driver.findElement(SELECT_ORDER_COUNT_DATE).click();
    }
    public void inputOrderColour(){
        driver.findElement(INPUT_ORDER_COLOUR).click();
    }

    public void inputComment(String text){
        driver.findElement(INPUT_COMMENT).sendKeys(text);
    }
    public void clickCompleteButton(){
        driver.findElement(COMPLETE_ORDER_BUTTON).click();
    }
    public void clickCompleteButtonYes(){
        driver.findElement(COMPLETE_ORDER_BUTTON_YES).click();
    }
    public void inputSecondOrderPage(String comment){
        inputOrderDate();
        inputOrderCountDate();
        inputOrderColour();
        inputComment(comment);
        clickCompleteButton();
        clickCompleteButtonYes();
    }

    public String checkFinalPage(){
        driver.findElement(FINAL_ORDER_STATUS_BUTTON).isEnabled();
        return driver.findElement(FINAL_ORDER_STATUS).getText();
    }

}