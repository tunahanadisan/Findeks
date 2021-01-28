import com.thoughtworks.gauge.Step;
import org.junit.Assert;

import java.util.ArrayList;

public class Anasayfa extends BaseTest {


    @Step("<saniye> saniye bekle")
    public void waitElement(int key) throws InterruptedException {
        Thread.sleep(key*1000);
        System.out.println(key+" saniye beklendi");
    }
    @Step("<key> elementine tikla")
    public void clickLogin(String key) {
        clickElement(key);
    }

    @Step("<key> elementinin üzerine gel")
    public void hover(String key) {
        hoverElement(key);
    }

    @Step("<key> elementi var mi")
    public void checkElement(String key) {
        try {
            findElement(key);
        } catch (Exception e) {
            Assert.fail("Element bulunamadı.");
        }
    }
    @Step("Yeni pencereye gec")
    public void switchToNewTab() {
        setNewTab(new ArrayList<String>(driver.getWindowHandles()));
        getNewTab().remove(getOldTab());
        driver.switchTo().window(getNewTab().get(0));
    }
    @Step("Gecilen sekmeyi kapat")
    public void switchToOldTab() {
        driver.close();
        driver.switchTo().window(getOldTab());
    }
    @Step("<key> elementinde yazan isim ile <text>'i karsilastir")
    public void checkName(String key, String text) {

        assertElementText(key,convertTurkishChar(text));
    }
}
