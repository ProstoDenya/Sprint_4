package test.java;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.scooter.praktikum.MainPage;
import ru.scooter.praktikum.OrderPage;


public class ScooterWebTest{

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/main/java/resources/webdriver/chrome/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickCookieButton();
    }

    @Test
    public void checkOrderPathHead(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickHeadOrderButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.inputFirstOrderPageAndGo("Мария","Багрова", "Автозаводская", "79855680808" );
        orderPage.inputSecondOrderPage("комментарий");
    }

    @Test
    public void checkOrderPathCenter(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCentralOrderButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.inputFirstOrderPageAndGo("Роман","Иванов", "Молодежная", "79857543896" );
        orderPage.inputSecondOrderPage("комментарий");
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
