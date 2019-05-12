package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.ExtentReports.ExtentTestManager;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    @Test (description = "Test summary")
    public void invalidLoginTest_InvalidUserNameInvalidPassword (Method method) throws InterruptedException {
        //*************PAGE METHODS WITH JAVA GENERICS********************
        //Open N11 HomePage
        //Initialize elements by using PageFactory
    	ExtentTestManager.startTest(method.getName(),"Invalid Login Scenario with empty username and password.");
        page.GetInstance(HomePage.class).goToN11();

        //Chain of Invocation (Go to Login Page and then LoginToN11)
        page.GetInstance(HomePage.class).
                goToLoginPage().loginToN11("onur@swtestacademy.com", "11223344");

        //*************ASSERTIONS***********************
        Thread.sleep(500); //It is better to use explicit wait here.
        page.GetInstance(LoginPage.class).verifyLoginPassword(("E-posta adresiniz veya şifreniz hatalı"));
    }

    @Test (description = "Test summary1")
    public void invalidLoginTest_EmptyUserEmptyPassword (Method method) throws InterruptedException {
    	ExtentTestManager.startTest(method.getName(),"Invalid Login Scenario with empty username and password.");
        //*************PAGE METHODS WITH JAVA GENERICS********************
        //Open N11 HomePage
        page.GetInstance(HomePage.class).goToN11();

        //Method Chaining (Go to Login Page and then LoginToN11)
        page.GetInstance(HomePage.class).goToLoginPage().loginToN11("","");

        //*************ASSERTIONS***********************
        Thread.sleep(500); //It is better to use explicit wait here.
        page.GetInstance(LoginPage.class).verifyLoginUserName("Lütfen e-posta adresinizi girin.");
        page.GetInstance(LoginPage.class).verifyLoginPassword("Bu alanın doldurulması zorunludur.");
    }

}
