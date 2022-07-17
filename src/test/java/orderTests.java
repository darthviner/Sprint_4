import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.MainPage;
import pageObject.OrderPage;
import pageObject.TrackPage;


@RunWith(Parameterized.class)
public class orderTests {


    private WebDriver driver;
    private MainPage mainPage;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String metroStation;
    private String date;
    private String rentDuration;
    private String colour;
    private String comment;


    public orderTests(String name, String surname, String address, String phoneNumber, String metroStation, String date, String rentDuration, String colour, String comment, WebDriver driver){

        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.metroStation = metroStation;
        this.date = date;
        this.rentDuration = rentDuration;
        this.colour = colour;
        this.comment = comment;
        this.driver = driver;

    }


    @Parameterized.Parameters // Пометь метод аннотацией для параметров
    public static Object[][] getData() {
        System.setProperty("webdriver.chrome.driver","C:\\WebDriver\\bin2\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver","C:\\WebDriver\\FireFox\\geckodriver.exe");

        return new Object[][]
                {
                        {"Данил","Володиньш","Москва","82283591488","Черкизовская","13.07.2022","двое суток","чёрный жемчуг","Комментарий", new FirefoxDriver()}
                        ,{"Сергей","Шарапов","Владивосток","89992223311","Китай-город","14.07.2022","сутки","серая безысходность","Комментарий", new ChromeDriver()}
                };
    }

    @Before
    public void startup(){
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

        mainPage.orderButtonClick();

        OrderPage orderPage = new OrderPage(driver);

        new WebDriverWait(driver,10)
                .until(ExpectedConditions.visibilityOf(driver.findElement(orderPage.orderContentDib)));
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.elementToBeClickable(orderPage.input("Имя")));


        orderPage.setTextToInput("Имя", name);
        orderPage.setTextToInput("Фамилия", surname);
        orderPage.setTextToInput("Адрес", address);
        orderPage.setTextToInput("Телефон", phoneNumber);
        orderPage.setSelectedSearchValue("Станция метро", metroStation);

        orderPage.buttonClick("Далее");

        new WebDriverWait(this.driver,5)
                .until(ExpectedConditions.visibilityOf(driver.findElement(orderPage.input("Когда привезти самокат"))));
        orderPage.setTextToInput("Когда привезти самокат",date);
        driver.findElement(orderPage.input("Когда привезти самокат")).sendKeys(Keys.ENTER);
        orderPage.setDropdownValue("Срок аренды",rentDuration);

        orderPage.checkboxClick(colour);
        orderPage.setTextToInput("Комментарий",comment);

        WebElement acceptOrderButton = orderPage.getButtonByIndex(1,"Заказать");
        acceptOrderButton.click();

        new WebDriverWait(this.driver,5)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(orderPage.orderConfirmForm)));

        new WebDriverWait(this.driver,5)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(orderPage.button("Да")))).click();

        new WebDriverWait(this.driver,5)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(orderPage.button("Посмотреть статус")))).click();

        TrackPage trackPage = new TrackPage(driver);

        new WebDriverWait(this.driver,6)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(trackPage.button("Отменить заказ"))));

        String actualName = trackPage.getTrackValue("Имя");
        String actualSurname = trackPage.getTrackValue("Фамилия");
        String actualAddress = trackPage.getTrackValue("Адрес");
        String actualMetroStation = trackPage.getTrackValue("Станция метро");
        String actualPhoneNumber = trackPage.getTrackValue("Телефон");
        String actualRentDuration = trackPage.getTrackValue("Срок аренды");
        String actualColour = trackPage.getTrackValue("Цвет");
        String actualComment = trackPage.getTrackValue("Комментарий");

        Assert.assertEquals("Проверка имени",name,actualName);
        Assert.assertEquals("Проверка фамилии",surname,actualSurname);
        Assert.assertEquals("Проверка адреса",address,actualAddress);
        Assert.assertEquals("Проверка станции метро", metroStation,actualMetroStation);
        Assert.assertEquals("Проверка телефона",phoneNumber,actualPhoneNumber);
        Assert.assertEquals("Проверка длительности аренды",rentDuration,actualRentDuration);
        Assert.assertEquals("Проверка цвета",colour,actualColour);
        Assert.assertEquals("Проверка комментария",comment,actualComment);

    }

    @After
    public void teardown(){
        driver.quit();

    }


}
