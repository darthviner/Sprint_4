import com.sun.jdi.PrimitiveValue;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.MainPage;

import java.lang.ref.PhantomReference;
@RunWith(Parameterized.class)
public class questionsPanelTests {


    private WebDriver driver;
    private MainPage mainPage;
    private ChromeOptions options;
    private String expectedResult;
    private int index;

    public questionsPanelTests(int index, String expectedResult){
        System.setProperty("webdriver.chrome.driver","C:\\WebDriver\\bin2\\chromedriver.exe");

        this.index = index;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters // Пометь метод аннотацией для параметров
    public static Object[][] getData() {
        return new Object[][] {
                {0,"Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1,"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2,"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3,"Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4,"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5,"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6,"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7,"Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Before
    public void startup(){
        options = new ChromeOptions(); // Драйвер для браузера Chrome
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void questionsPanelTextCorrectTest(){

        mainPage = new MainPage(driver);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(driver.findElement(mainPage.acceptCookieButton)));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(mainPage.acceptCookieButton));
        mainPage.setAcceptCookieButtonClick();
        mainPage.qeustionAccordeonButtonClick(index);
        String actualResult = mainPage.getAnswerPanelText(index);

        Assert.assertEquals("Проверка ожидаемого текста",expectedResult,actualResult);

    }

    @After
    public void teardown(){
        driver.quit();

    }


}
