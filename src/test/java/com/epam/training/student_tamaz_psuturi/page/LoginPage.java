package com.epam.training.student_tamaz_psuturi.page;

import com.epam.training.student_tamaz_psuturi.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends AbstractPage {
	
	private final Logger logger = LogManager.getRootLogger();
	private final String LOGIN_PAGE_URL = "https://www.saucedemo.com/";
	
	@FindBy(xpath = "//input[contains(@class, 'input_error') and @type='text']")
	private WebElement inputUsername;
	
	@FindBy(xpath = "//input[contains(@class, 'input_error') and @type='password']")
	private WebElement inputPassword;
	
	@FindBy(xpath = "//input[@class='submit-button btn_action']")
	private WebElement buttonLogin;
	
	@FindBy(xpath = "//h3[@data-test='error']")
	private WebElement errorMessage;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	@Override
	public LoginPage openPage() {
		driver.navigate().to(LOGIN_PAGE_URL);
		logger.info("Login page opened");
		return this;
	}
	
	public LoginPage loginWithValidData(User user) {
		inputUsername.sendKeys(user.getUsername());
		inputPassword.sendKeys(user.getPassword());
		buttonLogin.click();
		logger.info("Login performed");
		return this;
	}
	
	public MainPage login(User user) {
		loginWithValidData(user);
		return new MainPage(driver);
	}
	
	public String userNameString() {
		return inputUsername.getText();
	}
	
	public String errorMessageString() {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
//		wait.until(ExpectedConditions.visibilityOf(errorMessage));
		return errorMessage.getText();
	}
}
