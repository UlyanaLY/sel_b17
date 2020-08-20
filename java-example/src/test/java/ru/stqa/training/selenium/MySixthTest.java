package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class MySixthTest extends TestBase {
    @Test
    public void verifyProductPage() {
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
            Assert.assertEquals(productNameHome, productName);

            //б) на главной странице и на странице товара совпадают цены (обычная и акционная)
            //Проверить обычную цену
            Assert.assertEquals(regularPriceHome, regularPrice);
            //Проверить акционную цену
            Assert.assertEquals(campaignPriceHome, campaignPrice);
            //в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
            Assert.assertEquals(regularPriceColorHome, "rgba(119, 119, 119, 1)");
            Assert.assertEquals(regularPriceColor, "rgba(102, 102, 102, 1)");
            //г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
            Assert.assertEquals(campaignPriceColorHome, "rgba(204, 0, 0, 1)");
            Assert.assertEquals(campaignPriceColor, "rgba(204, 0, 0, 1)");
            //д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
            Assert.assertTrue((Double.parseDouble((regularPriceSizeHome))) < (Double.parseDouble((campaignPriceSizeHome))));
            Assert.assertTrue((Double.parseDouble((regularPriceSize))) < (Double.parseDouble((campaignPriceSize))));
        }
    }
}