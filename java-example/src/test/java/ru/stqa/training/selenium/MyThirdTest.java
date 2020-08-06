package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.*;

public class MyThirdTest extends TestBase {
    @Test
    public void verifyLeftMenuItems() {
        List<WebElement> menus = driver.findElements(By.id("app-"));
        int menusSize = menus.size();
        for (int i = 1; i <= menusSize; i++) {
            driver.findElement(xpath(("//ul[@id='box-apps-menu']//li[" + i + "]"))).click();
            if (areElementsPresent(driver, By.xpath("//li[@class='selected']//ul[@class='docs']//li"))) {
                List<WebElement> submenus = driver.findElements(By.xpath("//li[@class='selected']//ul[@class='docs']//li"));
                int submenusSize = submenus.size();
                for (int j = 1; j <= submenusSize; j++) {
                    driver.findElement(xpath(("//li[@class='selected']//ul[@class='docs']//li[" + j + "]"))).click();
                    Assert.assertEquals(isElementPresent(driver, tagName("h1")), true);
                }
            } else {
                Assert.assertEquals(isElementPresent(driver, tagName("h1")), true);
            }
        }
    }
}