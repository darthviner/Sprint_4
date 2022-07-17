package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrderPage {

    private WebDriver driver;

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public final By orderContentDib = By.className("Order_Content__bmtHS");
    public final By orderConfirmForm = By.className("Order_Modal__YZ-d3");

    public By input(String inputName){
        return By.xpath("//input[contains(@placeholder,'"+inputName+"')]");
    }

    public By selectedSearchInput(String inputName){
        return By.xpath("//input[@class='select-search__input' and contains(@placeholder,'"+inputName+"')]");
    }
    public By selectedSearchItem(String itemValue){
        return By.xpath("//div[contains(text(),'"+itemValue+"')]//..//..//button");
    }
    public By dropdownInput(String inputName){
        return By.xpath("//div[contains(text(),'"+inputName+"')]//..//..");
    }

    public By dropdownValue(String value){
        return By.xpath("//div[contains(text(),'"+value+"') and @class='Dropdown-option']");
    }

    public By button(String buttonName){
        return By.xpath("//button[text()='"+buttonName+"']");
    }
    public List<WebElement> getButtons(String buttonName){
        return driver.findElements(By.xpath("//button[text()='"+buttonName+"']"));
    }
    public WebElement getButtonByIndex(int index, String value){
        List<WebElement> elements = getButtons(value);
        if (index>elements.size()){
            return elements.get(elements.size()-1);
        }
        return elements.get(index);
    }
    public By checkBoxInput(String checkboxName){
        return By.xpath("//text()[contains(.,'"+checkboxName+"')]//..//input");
    }

    public  void buttonClick(String buttonName){
        driver.findElement(button(buttonName)).click();
    }
    public void checkboxClick(String checkboxName){
        driver.findElement(checkBoxInput(checkboxName)).click();
    }
    public void dropdownInputClick(String inputName){
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.
                        elementToBeClickable(
                                driver.findElement(dropdownInput(inputName))))
                .click();
    }
    public void selectedSearchInputClick(String inputName){
        driver.findElement(selectedSearchInput(inputName));
    }

    public void setSelectedSearchText(String inputName, String value){
        driver.findElement(selectedSearchInput(inputName)).sendKeys(value);
    }

    public void setSelectedSearchValue(String inputName, String value){
        selectedSearchInputClick(inputName);
        setSelectedSearchText(inputName,value);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(driver.findElement(selectedSearchItem(value)))).click();
    }

    public void setDropdownValue(String inputName, String value){
        dropdownInputClick(inputName);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(driver.findElement(dropdownValue(value)))).click();
    }

    public void setTextToInput(String inputName, String text){
        driver.findElement(input(inputName)).sendKeys(text);
    }


}
