package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage {


    public LoginFormPage(WebDriver driver) {
        super(driver);
    }

    public LoginFormPage digitarLogin(String login){
        driver.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
        return this;
    }

    public LoginFormPage digitarPassword(String password){
        driver.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);
        return this;
    }

    public SecretaPage clicarSignIn(){
        driver.findElement(By.linkText("SIGN IN")).click();
        return new SecretaPage(driver);
    }

    public SecretaPage fazerLogin(String login, String senha){
        digitarLogin(login);
        digitarPassword(senha);
        return clicarSignIn();

        // OR
        //return digitarLogin(login).digitarPassword(senha).clicarSignIn();

    }
}
