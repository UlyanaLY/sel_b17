package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyFourthTest extends TestBase {

    @Test
    public void verifyStickers() {
        driver.navigate().to("http://localhost/litecart/en/");
        List<WebElement> items = driver.findElements(By.cssSelector(".product"));

        for (WebElement item : items) {
            Assert.assertTrue(hasOnlyOneElement(item, By.cssSelector(".sticker")));
        }
    }

    boolean hasOnlyOneElement(WebElement el, By locator) {
        return el.findElements(locator).size() == 1;
    }
}