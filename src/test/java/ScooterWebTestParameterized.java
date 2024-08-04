package test.java;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.scooter.praktikum.MainPage;
import ru.scooter.praktikum.OrderPage;
import ru.scooter.praktikum.OrderWindow;

import static org.junit.Assert.assertTrue;
import static ru.scooter.praktikum.MainPage.CENTRAL_ORDER_BUTTON;
import static ru.scooter.praktikum.MainPage.HEAD_ORDER_BUTTON;


@RunWith(Parameterized.class)
public class ScooterWebTestParameterized {
    private WebDriver driver;
    private final String NAME;
    private final String LAST_NAME;
    private final String STREET;
    private final String TELEPHONE;
    private final String COMMENT;

    private final By XPATH_ORDER_BUTTON;

    public ScooterWebTestParameterized(String name, String lastName, String street, String telephone, String comment, By xpathOrderButton){
        this.NAME = name;
        this.LAST_NAME = lastName;
        this.STREET = street;
        this.TELEPHONE = telephone;
        this.COMMENT = comment;
        this.XPATH_ORDER_BUTTON = xpathOrderButton;
    }

    @Parameterized.Parameters
    public static Object[][] testData(){
        return new Object[][] { {"Петр", "Сергеевич", "Авиамоторная", "89635478955", "Поскорее", HEAD_ORDER_BUTTON},
                {"Андрей", "Викторович", "Смоленская", "89996358976", "Хочу самокат", CENTRAL_ORDER_BUTTON},
                {"Евгений", "Михайлович", "Лесная", "89264856798", "000", HEAD_ORDER_BUTTON}
        };
    }

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
    public void checkOrderPathWithParam(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButton(XPATH_ORDER_BUTTON);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.inputFirstOrderPageAndGo(NAME, LAST_NAME, STREET, TELEPHONE);
        orderPage.inputSecondOrderPage(COMMENT);
        orderPage.clickCompleteButton();
        orderPage.clickCompleteButton();

        OrderWindow OrderWindow = new OrderWindow(driver);

        assertTrue(OrderWindow.headerGetAfterOrder().contains("Заказ оформлен"));
    }

    @After
    public void cleanUp() {
        driver.quit();
    }

}
