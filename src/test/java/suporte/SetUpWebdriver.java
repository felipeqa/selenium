package suporte;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SetUpWebdriver {

    public static final String USERNAME = "xxxxxxx";
    public static final String AUTOMATE_KEY = "xxxxxxxxxxxxxxxxx";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    private static String URLLOCAL = System.getProperty("url");

    public static WebDriver createDriver() {
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

        driver.get(getUrlLocal());

        return driver;
    }

    public static WebDriver createBrowserStack() {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "71.0 beta");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1920x1200");

        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL(URL), caps);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(getUrlLocal());
        } catch (MalformedURLException e) {
            System.out.println("Erro com a URL " + e.getMessage());
        }

        return driver;

    }

    public static WebDriver createBrowserRemote() {

        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions());
            driver.manage().window().setSize(new Dimension(1920, 1080));
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get(getUrlLocal()    );
        } catch (MalformedURLException e) {
            System.out.println("Erro com a URL " + e.getMessage());
        }

        return driver;
    }

    private static String getUrlLocal(){
        return  (URLLOCAL == null)?  "http://www.juliodelima.com.br/taskit" : URLLOCAL;
    }
}