package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();          
    }
    
    @Given("^command new user is selected$")
    public void register_new_user_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();          
    }
    
    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is successfully created$")
    public void user_succesfully_created_with_credentials(String username, String password) throws Throwable{
        this.register_new_user_selected();
        this.registerWith(username, password, password);
    }
    
    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void user_tried_to_be_created_with_credentials(String username, String password) throws Throwable{
        this.register_new_user_selected();
        this.registerWith(username, password, password);
    }
    /*
    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    */
    @When("a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered")
    public void valid_username_and_password_and_password_confirmation_are_entered(String username, String password) throws Throwable{
        registerWith(username, password, password);
    }
    @When("a valid username \"([^\"]*)\" and valid password \"([^\"]*)\" and not matching password confirmation \"([^\"]*)\" are entered")
    public void valid_username_and_password_and_password_confirmation_are_entered(String username, String password, String passwordConfirmation) throws Throwable{
        registerWith(username, password, passwordConfirmation);
    }
    
    
    
    @When("invalid username \"([^\"]*)\" and correct password \"([^\"]*)\" and matching password confirmation are entered")
    public void invalid_username_and_valid_password_and_password_confirmation_are_entered(String username, String password) throws Throwable{
        registerWith(username, password, password);
    }
    
    @When("valid username \"([^\"]*)\" and short password \"([^\"]*)\" and matching password confirmation are entered")
    public void valid_username_and_short_password_and_password_confirmation_are_entered(String username, String password) throws Throwable{
        registerWith(username, password, password);
    }
    
    @When("nonexistent username \"([^\"]*)\" with a nonexistent password \"([^\"]*)\" are given")
    public void nonexistent_username_and_password_are_given(String username, String password) throws Throwable{
        logInWith(username, password);
    }
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @Then("^a new user is created$")
    public void new_user_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }
    
    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_in_and_error_message_is_given(String error) throws Throwable {
        WebElement element = driver.findElement(By.id("error"));
        assertTrue(element.getText().contains(error));
    } 
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
    
    private void registerWith(String username, String password, String passwordConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();  
    }     
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
}
