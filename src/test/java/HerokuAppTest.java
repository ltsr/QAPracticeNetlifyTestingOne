import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class HerokuAppTest extends DriverInit {
    @BeforeClass
    private void changeURL() {
        baseURL = "https://the-internet.herokuapp.com/";
        driver.get(baseURL);
    }

    @Test
    public void addRemoveTest() throws InterruptedException {
        driver.get(baseURL+"add_remove_elements/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/button[1]")));

        for (int i = 0; i < 5; i++) {
            driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/button[1]")).click();
        }

        WebElement divButtons = driver.findElement(By.xpath("/html/body/div[2]/div/div/div"));

        List<WebElement> buttons = divButtons.findElements(By.className("added-manually"));


        for (WebElement w : buttons) {
            w.click();
            Thread.sleep(1000);
        }



    }
}
