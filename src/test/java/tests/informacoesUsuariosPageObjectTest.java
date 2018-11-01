package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Beta;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.SetUpWebdriver;

import static junit.framework.TestCase.assertEquals;
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoesUsuariosPageObjectTest.csv")
public class informacoesUsuariosPageObjectTest {

    private WebDriver driver;

    @Before
    public void setUp(){
        driver = SetUpWebdriver.createDriver();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name = "tipoContato")String tipoContato,
                                                             @Param(name = "contato")String contato,
                                                             @Param(name = "msg")String msgEsperada){
       String textoToast =  new LoginPage(driver)
                .clicarSignIn()
                .fazerLogin("felipeqa", "123456")
                .clicarMe()
                .clicarAbaMoreDataAboutYou()
                .clicarBotaoAddMoreDataAboutYou()
                .adicionarContato(tipoContato, contato)
                .capturarTextoToast();

       assertEquals("Mensagem deve ser igual", msgEsperada, textoToast);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
