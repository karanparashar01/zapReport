package com.zap.vcpro;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;
import java.io.File;

public class ZAPsecurityTest {
    @Value("${zap_proxy_address}")
    private String ZAP_PROXY_ADDRESS;
    @Value("${zap_api_key}")
    private String ZAP_API_key;
    @Value("${zap_proxy_port}")
    private int ZAP_PROXY_PORT;
    private WebDriver driver;
    private ClientApi api;

    @BeforeMethod
    public void setup() {
        String proxyServerUrl = "http://localhost:8080";
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyServerUrl);
        proxy.setSslProxy(proxyServerUrl);

        ChromeOptions co = new ChromeOptions();
        co.setAcceptInsecureCerts(true);
        co.setProxy(proxy);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(co);
        api = new ClientApi("localhost", 8080, "70jufpbptirp5a4at12ve72ei0");

    }

    @Test
    public void ZAPsecurityTest1() {
        driver.get("https://vcpro.dbent.co.in/app/index.html#/login");
        //Assert.assertTrue(driver.getTitle().contains("CustomerConsole"));
    }
    @AfterMethod
    public void tearDown() throws Exception{
        if (api != null) {
            String title = "POC ZAP Selenium - Abstracta";
            String template = "traditional-html";
            String description = "This is a ZAP test report";
            String reportfilename = "abstracta-web-security-report.html";
            String targetFolder = System.getProperty("user.dir");
            try {
                ApiResponse res = api.reports.generate(title, template, null,
                        description, null, null, null,null, null, reportfilename,null,
                        targetFolder,null);
                System.out.println("ZAP report generated here: " + res.toString());
            } catch (ClientApiException ex) {
                throw new Exception(ex);
                
            }
        }
    }

}