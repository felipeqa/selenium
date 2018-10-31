package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {

    private WebDriver driver = new ChromeDriver();

    @Before
    public void setUp(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//      configurar o tamanho da página
        driver.manage().window().maximize();
        driver.get("http://www.juliodelima.com.br/taskit");

    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(){
        // para linux e mac
//        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // para windows
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\felipe\\drivers\\chromedriver.exe");


//        driver.manage().window().setSize(new Dimension(1280, 800));

        driver.findElement(By.linkText("Sign in")).click();

        WebElement signInBox = driver.findElement(By.id("signinbox"));
        signInBox.findElement(By.name("login")).sendKeys("felipeqa");
        signInBox.findElement(By.name("password")).sendKeys("123456");

        driver.findElement(By.linkText("SIGN IN")).click();

        driver.findElement(By.className("me")).click();
        driver.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
        driver.findElement(By.xpath("//div[@id=\"moredata\"]//button[@data-target=\"addmoredata\"]")).click();

        //Criando um escopo para um comobox
        WebElement popupAddMoreData = driver.findElement(By.id("addmoredata"));
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");
        //#####################################

        popupAddMoreData.findElement(By.name("contact")).sendKeys("+55909093333");
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        WebElement msgPOP = driver.findElement(By.id("toast-container"));
        String msgText = msgPOP.getText();

        assertEquals("Mensagem de validação", "Your contact has been added!", msgText);



    }

    @After
    public void tearDown(){
//        driver.quit();
    }
}
