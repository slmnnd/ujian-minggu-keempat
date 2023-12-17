package com.juaracoding;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("deviceName", "Pixel 2 API 30");
        dc.setCapability("udid", "emulator-5554");
        dc.setCapability("platformName", "android");
        dc.setCapability("platformVersion", "11");
        dc.setCapability("appPackage", "com.chad.financialrecord");
        dc.setCapability("appActivity", "com.rookie.catatankeuangan.feature.splash.SplashActivity");
        dc.setCapability("noReset", "true");
        URL url = new URL("https://127.0.0.1:4723/wd/hub");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        AndroidDriver driver =  new AndroidDriver(url, dc);
        //tombol tambah
        MobileElement btnAdd = (MobileElement) driver.findElementById("com.chad.financialrecord:id/fabMenu");

        //pemasukan
        MobileElement btnPemasukan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/btnIncome");

        //form pemasukan-pengeluaran
        MobileElement fillDate = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvDate");
        MobileElement chooseDate = (MobileElement) driver.findElementById("1 Desember 2023");
        MobileElement okDate = (MobileElement) driver.findElementById("android:id/button1");

        MobileElement fillKategori = (MobileElement) driver.findElementById("com.chad.financialrecord:id/spCategory");
        Select slcKategori = new Select(fillKategori);

        MobileElement fillJumlah = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etAmount");
        MobileElement fillKeterangan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etNote");
        MobileElement btnSave = (MobileElement) driver.findElementById("com.chad.financialrecord:id/btSave");


        //hasil pemasukan
        MobileElement hasilPemasukan = (MobileElement) driver.findElementByXPath("(//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvIncome\"])[1]");

        //hasil pengeluaran
        MobileElement hasilPengeluaran = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.chad.financialrecord:id/tvExpense\" and @text='20.000']");

        //saldo
        MobileElement saldo = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvBalance");

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

        //saldo
        System.out.println(saldo.getText());



    }
}