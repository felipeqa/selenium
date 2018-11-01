package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.SetUpWebdriver;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoesUsuarioTestData.csv")
public class InformacoesUsuarioTest {

    private WebDriver driver;

    @Rule
    public TestName nomeDoTeste = new TestName();

    @Before
    public void setUp(){
        driver = SetUpWebdriver.createDriver();
        driver.findElement(By.linkText("Sign in")).click();

        WebElement signInBox = driver.findElement(By.id("signinbox"));
        signInBox.findElement(By.name("login")).sendKeys("felipeqa");
        signInBox.findElement(By.name("password")).sendKeys("123456");

        driver.findElement(By.linkText("SIGN IN")).click();
        driver.findElement(By.className("me")).click();
        driver.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name = "tipoContato")String tipoContato,
                                                             @Param(name = "contato")String contato,
                                                             @Param(name = "msg")String msgEsperada){


        driver.findElement(By.xpath("//div[@id=\"moredata\"]//button[@data-target=\"addmoredata\"]")).click();

        //Criando um escopo para um comobox
        WebElement popupAddMoreData = driver.findElement(By.id("addmoredata"));
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipoContato);
        //#####################################

        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        WebElement msgPOP = driver.findElement(By.id("toast-container"));
        String msgText = msgPOP.getText();

        assertEquals("Mensagem de validação", msgEsperada, msgText);
    }

    @Test
    public void removerUmContatoDeUmUsuario(){

        driver.findElement(By.xpath("//span[text()=\"+5523232323\"]/following-sibling::a")).click();

        //fechar janela javascript
        driver.switchTo().alert().accept();

        WebElement msgPOP = driver.findElement(By.id("toast-container"));
        String msgText = msgPOP.getText();

        assertEquals("Mensagem de validação", "Rest in peace, dear phone!", msgText);

        String arquivoPath = "image/" + Generator.dataHoraParaArquivo() + nomeDoTeste.getMethodName() + ".png";
        Screenshot.tirarScreenshot(driver, arquivoPath);

        // espera explicita
        WebDriverWait esperar = new WebDriverWait(driver,10 );
        esperar.until(ExpectedConditions.stalenessOf(msgPOP));

        driver.findElement(By.linkText("Logout")).click();

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
