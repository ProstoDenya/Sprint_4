package ru.scooter.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderWindow {
    WebDriver driver;
    private final By POP_UP_HEADER = By.xpath(".//div[text()='Заказ оформлен']");

    public OrderWindow(WebDriver driver) {
        this.driver = driver;
    }

    public String headerGetAfterOrder() {
        new WebDriverWait(driver, 10000).until(driver -> (driver.findElement(POP_UP_HEADER).getText() != null
                && !driver.findElement(POP_UP_HEADER).getText().isEmpty()
        ));
        return driver.findElement(POP_UP_HEADER).getText();
    }
}
