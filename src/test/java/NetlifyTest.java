import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetlifyTest extends DriverInit {
    @Test
    public void newTabTest() throws InterruptedException {
        driver.get(baseURL+"tab");

        driver.findElement(By.id("newTabBtn")).click();

        Thread.sleep(2000);

        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());

        driver.switchTo().window(browserTabs.get(1));

        try {
            String emailAddress = driver.findElement(By.xpath("//tbody/tr[1]/td[3]")).getText();
            Assert.assertEquals(emailAddress, "mo@email.com");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread.sleep(2000);

        driver.close();

        driver.switchTo().window(browserTabs.get(0));

        Assert.assertFalse(driver.findElements(By.id("newTabBtn")).isEmpty());

        Thread.sleep(2000);
    }

    @Test
    public void testLoader() {
        driver.get(baseURL+"loader");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));

        String sampleText = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/p[1]")).getText();

        Assert.assertEquals(sampleText, "Some text in my newly loaded page..");
    }

    @Test
    public void testAlerts() {
        driver.get(baseURL+"alerts");

        driver.findElement(By.id("alert-btn")).click();

        Alert alert = driver.switchTo().alert();

        String alert1Text = alert.getText();

        Assert.assertEquals(alert1Text, "Hello! I am an alert box!!");
    }
}
