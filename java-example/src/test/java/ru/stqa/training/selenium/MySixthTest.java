package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.xpath;

public class MySixthTest extends TestBase {
    @Test
    public void verifyProductPage() throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/en/");
        List<WebElement> campaigns = driver.findElements(xpath("//*[@id='box-campaigns']//ul//li"));
        int campaignsSize = campaigns.size();

        for (int i = 1; i <= campaignsSize; i++) {
            WebElement productHome = driver.findElement(xpath("//div[@id='box-campaigns']//ul//li[" + i + "]"));
            String productNameHome = productHome.findElement(xpath(".//a[1]/div[2]")).getText();
            String regularPriceHome = productHome.findElement(xpath("//a[1]/div[4]/s")).getText();
            String campaignPriceHome = productHome.findElement(xpath(".//a[1]/div[4]/strong")).getText();
            String regularPriceColorHome = productHome.findElement(xpath(".//a[1]/div[4]/s")).getCssValue("color");
            String campaignPriceColorHome = productHome.findElement(xpath(".//a[1]/div[4]/strong")).getCssValue("color");
            String regularPriceSizeHome = productHome.findElement(xpath(".//a[1]/div[4]/s")).getCssValue("font-size");
            regularPriceSizeHome = regularPriceSizeHome.replaceAll("px", "");
            String campaignPriceSizeHome = productHome.findElement(xpath(".//a[1]/div[4]/strong")).getCssValue("font-size");
            campaignPriceSizeHome = campaignPriceSizeHome.replaceAll("px", "");

            productHome.findElement(xpath(".//a[1]/div[2]")).click();
            sleep(500);

            String productName = driver.findElement(xpath("//h1")).getText();
            String regularPrice = driver.findElement(xpath("//*[@id='box-product']//div[2]/s[@class='regular-price']")).getText();
            String campaignPrice = driver.findElement(xpath("//*[@id='box-product']//div[2]/strong[@class='campaign-price']")).getText();
            String regularPriceColor = driver.findElement(xpath("//*[@id='box-product']//div[2]/s[@class='regular-price']")).getCssValue("color");
            String campaignPriceColor = driver.findElement(xpath("//*[@id='box-product']//div[2]/strong[@class='campaign-price']")).getCssValue("color");
            String regularPriceSize = driver.findElement(xpath("//*[@id='box-product']//div[2]/s[@class='regular-price']")).getCssValue("font-size");
            regularPriceSize = regularPriceSize.replaceAll("px", "");
            String campaignPriceSize = driver.findElement(xpath("//*[@id='box-product']//div[2]/strong[@class='campaign-price']")).getCssValue("font-size");
            campaignPriceSize = campaignPriceSize.replaceAll("px", "");

            //а) на главной странице и на странице товара совпадает текст названия товара
            assertEquals(productNameHome, productName);
            //б) на главной странице и на странице товара совпадают цены (обычная и акционная)
            //Проверить обычную цену
            assertEquals(regularPriceHome, regularPrice);
            //Проверить акционную цену
            assertEquals(campaignPriceHome, campaignPrice);

            //в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
            assertEquals(returnChannels(regularPriceColorHome)[0].trim(), "119");
            assertEquals(returnChannels(regularPriceColorHome)[1].trim(), "119");
            assertEquals(returnChannels(regularPriceColorHome)[2].trim(), "119");

            assertEquals(returnChannels(regularPriceColor)[0].trim(), "102");
            assertEquals(returnChannels(regularPriceColor)[1].trim(), "102");
            assertEquals(returnChannels(regularPriceColor)[2].trim(), "102");

            //г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
            assertEquals(returnChannels(campaignPriceColorHome)[0].trim(), "204");
            assertEquals(returnChannels(campaignPriceColorHome)[1].trim(), "0");
            assertEquals(returnChannels(campaignPriceColorHome)[2].trim(), "0");

            assertEquals(returnChannels(campaignPriceColor)[0].trim(), "204");
            assertEquals(returnChannels(campaignPriceColor)[1].trim(), "0");
            assertEquals(returnChannels(campaignPriceColor)[2].trim(), "0");

            //д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
            assertTrue((Double.parseDouble((regularPriceSizeHome))) < (Double.parseDouble((campaignPriceSizeHome))));
            assertTrue((Double.parseDouble((regularPriceSize))) < (Double.parseDouble((campaignPriceSize))));
        }
    }
}