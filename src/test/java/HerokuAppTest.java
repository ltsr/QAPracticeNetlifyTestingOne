import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
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
        driver.get(baseURL + "add_remove_elements/");

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

    @Test
    public void loadedElementTest() {
        driver.get(baseURL + "dynamic_loading");

        driver.findElement(By.xpath("/html/body/div[2]/div/div/a[2]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start")));

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

        String actualText = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/h4")).getText();

        Assert.assertEquals(actualText, "Hello World!");
    }

    @Test
    public void basicAuthTest() {
        driver.get(baseURL + "basic_auth");

        try {
            ((HasAuthentication) driver).register(UsernameAndPassword.of("admin", "admin"));
            driver.get("https://the-internet.herokuapp.com/digest_auth");

            // Verify if page is loaded successfully
            String Title = driver.findElement(By.tagName("h3")).getText();
            String text = driver.findElement(By.tagName("p")).getText();
            System.out.println(Title);
            System.out.println(text);

            Assert.assertEquals(text, "Congratulations! You must have the proper credentials.");


        } catch (Exception e) {
            System.out.println("An unexpected error: " + e.getMessage());
        }
    }

    @Test
    public void sliderTest() throws InterruptedException {
        driver.get(baseURL+"horizontal_slider");

        Thread.sleep(2000);


        for (int i = 0; i < 6; i++) {
            driver.findElement(By.xpath("/html/body/div[2]/div/div/div/input")).sendKeys(Keys.ARROW_RIGHT);
        }
        double expectedValueDouble = i * 0.5;


        String sliderValueText = driver.findElement(By.xpath("//*[@id=\"range\"]")).getText();

        double sliderValueDouble = Double.parseDouble(sliderValueText);

        Thread.sleep(2000);

        System.out.println("Slider value: " + sliderValueDouble);
        System.out.printf("Expected value: " + expectedValueDouble);

        Assert.assertEquals(sliderValueDouble, expectedValueDouble);
    }
}
