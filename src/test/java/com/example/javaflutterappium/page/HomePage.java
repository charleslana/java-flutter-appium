package com.example.javaflutterappium.page;

import io.github.ashwith.flutter.FlutterElement;
import io.github.ashwith.flutter.FlutterFinder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage {
    private final RemoteWebDriver driver;
    private final FlutterFinder finder;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String bottomNavHome = "bottom_nav_home";
    private final String bottomNavSearch = "bottom_nav_search";
    private final String botaoAbrirDialog = "Abrir Dialog";
    private final String botaoFechar = "Fechar";
    private final String textoPesquisarProduto = "Produto novo";
    private final String botaoPesquisarProduto = "Pesquisar produto";

    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        this.finder = new FlutterFinder(driver);
    }

    public void realizarTeste() {
        clicarMenuBuscar();
        clicarMenuHome();
        abrirDialogEFecharDialog();
        clicarMenuBuscar();
        pesquisarProduto();
        clicarMenuHome();
        sleep(2000L);
    }

    private void pesquisarProduto() {
        finder.byType("TextFormField").sendKeys(textoPesquisarProduto);
        finder.byText(botaoPesquisarProduto).click();
        String snackBarText = String.format("Pesquisa realizada com o produto: %s!", textoPesquisarProduto);
        FlutterElement snackBarElement = finder.byText(snackBarText);
        driver.executeScript("flutter:waitFor", snackBarElement, 5000);
    }

    private void abrirDialogEFecharDialog() {
        finder.byText(botaoAbrirDialog).click();
        finder.byText(botaoFechar).click();
    }

    private void clicarMenuBuscar() {
        logger.info("Clicando no menu de buscar");
        finder.byValueKey(bottomNavSearch).click();
    }

    private void clicarMenuHome() {
        logger.info("Clicando no menu da home");
        WebElement menuHomeEncontrado = finder.byValueKey(bottomNavHome);
        menuHomeEncontrado.click();
    }

    private void sleep(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
