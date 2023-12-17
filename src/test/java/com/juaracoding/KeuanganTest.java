package com.juaracoding;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class KeuanganTest {
    private AndroidDriver driver;

    @BeforeClass
    public void startHere() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("deviceName", "Pixel 2 API 30");
        dc.setCapability("udid", "emulator-5554");
        dc.setCapability("platformName", "android");
        dc.setCapability("platformVersion", "11");
        dc.setCapability("appPackage", "com.chad.financialrecord");
        dc.setCapability("appActivity", "com.rookie.catatankeuangan.feature.splash.SplashActivity");
        dc.setCapability("noReset", "true");
        URL url = new URL("https://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, dc);
    }

    @AfterClass
    public void finish(){
        driver.quit();
    }

    @Test
    public void pemasukanTest() {
        //tombol tambah
        MobileElement btnAdd = (MobileElement) driver.findElementById("com.chad.financialrecord:id/fabMenu");
        //form pemasukan-pengeluaran
        MobileElement fillDate = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvDate");
        MobileElement chooseDate = (MobileElement) driver.findElementById("1 Desember 2023");
        MobileElement okDate = (MobileElement) driver.findElementById("android:id/button1");

        MobileElement fillKategori = (MobileElement) driver.findElementById("com.chad.financialrecord:id/spCategory");
        Select slcKategori = new Select(fillKategori);

        MobileElement fillJumlah = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etAmount");
        MobileElement fillKeterangan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etNote");
        MobileElement btnSave = (MobileElement) driver.findElementById("com.chad.financialrecord:id/btSave");

        //pemasukan
        MobileElement btnPemasukan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/btnIncome");

        //hasil pemasukan
        MobileElement hasilPemasukan = (MobileElement) driver.findElementByXPath("(//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvIncome\"])[1]");

        //action pemasukan
        btnAdd.click();
        btnPemasukan.click();
        fillDate.click();
        chooseDate.click();
        okDate.click();

        fillKategori.click(); //cek lagi cara menu dropdown gimana
        slcKategori.selectByIndex(11); //choose tabungan
        fillJumlah.sendKeys("50.000");
        fillKeterangan.sendKeys("nabung hari ini");
        btnSave.click();

        System.out.println(hasilPemasukan.getText());

        Assert.assertEquals(hasilPemasukan.getText(), "50.000");
    }

    @Test
    public void pengeluaranTest(){
        //tombol tambah
        MobileElement btnAdd = (MobileElement) driver.findElementById("com.chad.financialrecord:id/fabMenu");
        //form pemasukan-pengeluaran
        MobileElement fillDate = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvDate");
        MobileElement chooseDate = (MobileElement) driver.findElementById("1 Desember 2023");
        MobileElement okDate = (MobileElement) driver.findElementById("android:id/button1");

        MobileElement fillKategori = (MobileElement) driver.findElementById("com.chad.financialrecord:id/spCategory");
        Select slcKategori = new Select(fillKategori);

        MobileElement fillJumlah = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etAmount");
        MobileElement fillKeterangan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etNote");
        MobileElement btnSave = (MobileElement) driver.findElementById("com.chad.financialrecord:id/btSave");

        //hasil pengeluaran
        MobileElement hasilPengeluaran = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvExpense\" and @text='20.000']");

        //action pengeluaran
        btnAdd.click();
        fillDate.click();
        chooseDate.click();
        okDate.click();

        fillKategori.click();
        slcKategori.selectByIndex(18); //choose pakaian
        fillJumlah.sendKeys("20.000");
        fillKeterangan.sendKeys("beli baju hamster");
        btnSave.click();

        System.out.println(hasilPengeluaran.getText());
        Assert.assertEquals(hasilPengeluaran.getText(), "20.000");
    }

    @Test
    public void checkSaldo(){
        //saldo
        MobileElement saldo = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvBalance");
        //saldo
        System.out.println(saldo.getText());
        Assert.assertEquals(saldo.getText(), "30.000");
    }
}
