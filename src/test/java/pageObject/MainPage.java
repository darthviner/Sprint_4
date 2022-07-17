package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.file.WatchEvent;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;

    }
    public By qeustionAccordeonButton(int index){

        return By.id("accordion__heading-"+index);
    }

    public void qeustionAccordeonButtonClick(int index){
        driver.findElement(qeustionAccordeonButton(index)).click();
    }
    public final By acceptCookieButton = By.id("rcc-confirm-button");
    public final By orderButton = By.className("Button_Button__ra12g");

    public  void setAcceptCookieButtonClick(){
        driver.findElement(acceptCookieButton).click();
    }



    public By answerPanel(int index){

        return By.id("accordion__panel-"+index);
    }

    public String getAnswerPanelText(int index){
        return driver.findElement(answerPanel(index)).getText();
    }

    public void orderButtonClick(){
        driver.findElement(orderButton).click();
    }

    public void clickQuestion(){

    }
}
