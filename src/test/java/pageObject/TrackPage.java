package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.sound.midi.Track;

public class TrackPage {
    private WebDriver driver;

    public TrackPage(WebDriver driver){
        this.driver = driver;
    }
    public By trackTitle(String title){
        return By.xpath("//div[contains(@class,'Track_Title') and contains(text(),'"+title+"')]");
    }
    public By trackValue(String title){
        return By.xpath("//div[contains(@class,'Track_Title') and contains(text(),'"+title+"')]//..//div[2]");
    }
    public By button(String buttonName){
        return By.xpath("//button[text()='"+buttonName+"']");
    }
    public String getTrackValue(String title){
        return driver.findElement(trackValue(title)).getText();
    }
}
