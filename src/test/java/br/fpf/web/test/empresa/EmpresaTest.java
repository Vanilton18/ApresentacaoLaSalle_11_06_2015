package br.fpf.web.test.empresa;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmpresaTest {

	WebDriver driver;

	@Before
	public void configuracoesIniciais() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://notebook:8081/empresax");
		driver.manage().window().maximize();
	}

	@Test
	public void CadastrodeCliente() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		assertEquals("EMPRESA VANILTON | Home", driver.getTitle());
		driver.findElement(By.id("clientes")).click();
		driver.findElement(By.id("bt_novo_cliente")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nome")));
		driver.findElement(By.id("nome")).sendKeys("TesteLaSalle");
		driver.findElement(By.id("email")).sendKeys("teste@lasalle.com");
		driver.findElement(By.id("endereco")).sendKeys("Av. Dom Pedro");
		driver.findElement(By.id("historico")).sendKeys("Sem Historico");
		driver.findElement(By.id("bt_salvar_cliente")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();

	}

}
