package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;

import java.util.concurrent.TimeUnit;
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoesUsuarioTestData.csv")
public class InformacoesUsuarioTest {

    private WebDriver driver = new ChromeDriver();

    @Rule
    public TestName nomeDoTeste = new TestName();

    @Before
    public void setUp(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//      configurar o tamanho da página
        driver.manage().window().maximize();

        driver.get("http://www.juliodelima.com.br/taskit");
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
        // para linux e mac
//        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // para windows
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\felipe\\drivers\\chromedriver.exe");

//        driver.manage().window().setSize(new Dimension(1280, 800));

        // xpath como pegar o próximo elemento tag a
        //span[text()="+55909093333"]/following-sibling::a

        // xpath como pegar e elemnto anterior tag i
        //span[text()="+55909093333"]/preceding-sibling::i

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

        driver.findElement(By.xpath("//span[text()=\"23232323\"]/following-sibling::a")).click();

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
