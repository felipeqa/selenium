package suporte;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SetUpWebdriver {

    public static WebDriver createDriver(){
        // para linux e mac
//        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // para windows
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\felipe\\drivers\\chromedriver.exe");

//        driver.manage().window().setSize(new Dimension(1280, 800));

        // xpath como pegar o próximo elemento tag a
        //span[text()="+55909093333"]/following-sibling::a

        // xpath como pegar e elemnto anterior tag i
        //span[text()="+55909093333"]/preceding-sibling::i
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//      configurar o tamanho da página
        driver.manage().window().maximize();

        driver.get("http://www.juliodelima.com.br/taskit");

        return driver;
    }
}