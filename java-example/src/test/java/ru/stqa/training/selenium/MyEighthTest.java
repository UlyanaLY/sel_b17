package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.By.xpath;

public class MyEighthTest extends TestBase {
    @Test
    public void adProduct() throws InterruptedException {
        sleep(500);
        driver.findElement(xpath("//*[@id='app-'][2]/a")).click();
        driver.findElement(xpath("//a[contains(text(),'Add New Product')]")).click();
        sleep(500);

        driver.findElement(xpath("//label[contains(text(),'Enabled')] ")).click();
        driver.findElement(xpath("//input[@name='name[en]'] ")).sendKeys("Duck Policeman");
        driver.findElement(xpath("//input[@name='code'] ")).sendKeys("CR-001");
        driver.findElement(xpath("//*[@id='tab-general']//tr[4]//input[@data-name='Subcategory']")).click();

        Select categories = new Select(driver.findElement(By.name("default_category_id")));
        categories.selectByVisibleText("Subcategory");

        driver.findElement(xpath("//*[@id='tab-general']//input[@name='product_groups[]' and @value='1-3']")).click();
        driver.findElement(xpath("//*[@id='tab-general']//input[@type='number']")).clear();
        driver.findElement(xpath("//*[@id='tab-general']//input[@type='number']")).sendKeys("20");


        Select quantities = new Select(driver.findElement(By.name("quantity_unit_id")));
        quantities.selectByVisibleText("pcs");

        Select statuses = new Select(driver.findElement(By.name("delivery_status_id")));
        statuses.selectByVisibleText("3-5 days");

        WebElement addFile = driver.findElement(By.xpath(".//input[@name='new_images[]']"));
        File f = new File("duck_policman.jpg");
        String url = f.getAbsolutePath();
        addFile.sendKeys(url);

        driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys("28.07.2020");
        driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys("28.12.2020");
        sleep(500);

        driver.findElement(By.xpath("//a[@href='#tab-information']")).click();

        Select manufactures = new Select(driver.findElement(By.name("manufacturer_id")));
        manufactures.selectByVisibleText("ACME Corp.");

        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("duck, policeman");
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("big rubber duck policeman");

        driver.findElement(xpath("//textarea[@name='description[en]']")).sendKeys(Keys.TAB);
        driver.findElement(xpath("//textarea[@name='description[en]']")).sendKeys("Big rubber duck policeman");

        driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys("head_title_duck");
        driver.findElement(By.xpath("//input[@name='meta_description[en]']")).sendKeys("meta_description_duck");
        sleep(500);

        driver.findElement(By.xpath("//a[@href='#tab-prices']")).click();

        driver.findElement(xpath("//input[@name='purchase_price']")).clear();
        driver.findElement(xpath("//input[@name='purchase_price']")).sendKeys("20");

        Select currencyCodes = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        currencyCodes.selectByVisibleText("Euros");

        driver.findElement(xpath("//input[@name='prices[USD]']")).clear();
        driver.findElement(xpath("//input[@name='prices[USD]']")).sendKeys("18");

        driver.findElement(xpath("//input[@name='prices[EUR]']")).clear();
        driver.findElement(xpath("//input[@name='prices[EUR]']")).sendKeys("20");

        driver.findElement(xpath("//button[@name='save']")).click();
        sleep(500);

        WebElement link = driver.findElement(By.linkText("Duck Policeman"));

        Assert.assertTrue(isElementPresent(driver, By.linkText("Duck Policeman")));
    }
}