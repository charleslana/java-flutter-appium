package com.example.javaflutterappium.base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

public class AppiumSetup {
    public RemoteWebDriver driver = null;

    public RemoteWebDriver initializeDriver() {
        try {
//            Properties prop = loadProperties(getResourcePath("appium_config.properties"));
            String configFile = System.getProperty("appium.config.file", "src/test/resources/appium_config.properties");
            System.out.println("Arquivo de configuração usado: " + configFile);
            Properties prop = loadProperties(configFile);
            String getAppPath = getResourcePath(prop.getProperty("app"));
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("appium:deviceName", prop.getProperty("deviceName"));
            capabilities.setCapability("appium:platformName", prop.getProperty("platformName"));
            capabilities.setCapability("appium:noReset", Boolean.parseBoolean(prop.getProperty("noReset")));
            capabilities.setCapability("appium:skipUnlock", Boolean.parseBoolean(prop.getProperty("skipUnlock")));
            capabilities.setCapability("appium:app", getAppPath);
            capabilities.setCapability("appium:appPackage", prop.getProperty("appPackage"));
            capabilities.setCapability("appium:appActivity", prop.getProperty("mainActivity"));
            capabilities.setCapability("appium:automationName", prop.getProperty("automationName"));
            driver = new AndroidDriver((new URI(prop.getProperty("remoteUrl"))).toURL(), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        } catch (MalformedURLException | URISyntaxException e) {
            System.err.println("Erro ao inicializar o driver Appium: " + e.getMessage());
            e.printStackTrace();
        }
        return driver;
    }

    private static String getResourcePath(String fileName) {
        try {
            return new File(ClassLoader.getSystemResource(fileName).toURI()).getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }

    private Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo de propriedades: " + e.getMessage());
            e.printStackTrace();
        }
        return properties;
    }
}
