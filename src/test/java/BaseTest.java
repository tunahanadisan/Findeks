import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import helper.ElementHelper;
import helper.StoreHelper;
import model.ElementInfo;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BaseTest {

    static WebDriver driver;
    static Actions action;
    static ArrayList<String> newTab;
    static String oldTab;




    @BeforeScenario
    public void seneryooncesi() throws InterruptedException {
        System.out.println("-----------Seneryo Baslangici------------");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.get("https://www.teb.com.tr/");
        oldTab = driver.getWindowHandle();
        driver.manage().window().maximize();
        System.out.println("Driver calisti");
    }

    @AfterScenario
    public void seneryosonrasi() throws InterruptedException {
        driver.quit();
        System.out.print("------Seneryo Sonu--------");
    }

    public WebElement findElement(String key) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;

    }

    public List<WebElement> findElements(String key){
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        return driver.findElements(infoParam);
    }

    public void hoverElement(String by) {
        action.moveToElement(findElement(by)).build().perform();
    }

    public void clickElement(String by) {
        findElement(by).click();
    }
    public static void setNewTab(ArrayList<String> newTab) {
        BaseTest.newTab = newTab;
    }

    public static ArrayList<String> getNewTab() {
        return newTab;
    }

    public static String getOldTab() {
        return oldTab;
    }

    public void assertElementText(String key, String expectedText){
        String actualText = findElement(key).getText();
        Assert.assertEquals(expectedText, convertTurkishChar(actualText));
    }
    public static String convertTurkishChar(String string) {
        string = string.replace("ç", "c");
        string = string.replace("ö", "o");
        string = string.replace("ş", "s");
        string = string.replace("ğ", "g");
        string = string.replace("ü", "u");
        string = string.replace("ı", "i");
        string = string.replace("Ç", "C");
        string = string.replace("Ö", "O");
        string = string.replace("Ş", "S");
        string = string.replace("Ğ", "G");
        string = string.replace("Ü", "U");
        string = string.replace("İ", "I");
        return string;
    }

}
