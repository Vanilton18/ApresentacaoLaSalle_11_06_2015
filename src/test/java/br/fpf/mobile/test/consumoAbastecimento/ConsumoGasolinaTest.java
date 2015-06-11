package br.fpf.mobile.test.consumoAbastecimento;

import static org.junit.Assert.assertEquals;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.io.File;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import br.fpf.test.utils.AndroidDriverCreator;
import br.fpf.test.utils.Utils;

/*
 * Classe de teste
 * Criado por: Vanilton Pinheiro em 11/06/2015  
 */

public class ConsumoGasolinaTest extends AndroidDriverCreator {

	File app = new File("apk/ConsumoGasolina.apk");
	
	@Before
	public void configuracoesIniciais() throws Exception {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		cap.setCapability(MobileCapabilityType.APP, app);
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	}

	@Test
	public void CadastroDeAbastecimento() throws InterruptedException {
		assertEquals("Desempenho Mensal", driver.findElement(By.id("android:id/action_bar_title")).getText());
		driver.findElement(By.id("br.com.consumogasolina:id/action_opcoes")).click();
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Adicionar Abastecimento\")").click();
		driver.findElement(By.id("br.com.consumogasolina:id/editText_km_abastecimento")).sendKeys("22950");
		driver.findElement(By.id("br.com.consumogasolina:id/editText_quantidade_litros")).sendKeys("34.14");
		driver.findElement(By.id("br.com.consumogasolina:id/editText_valor")).sendKeys("106.86");
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Cadastrar\")").click();
		assertEquals("0.0 KM/Litro(s)/Mês", driver.findElement(By.id("br.com.consumogasolina:id/editText_km_litro")).getText());
		assertEquals("R$ 106.86", driver.findElement(By.id("br.com.consumogasolina:id/editText_valor_abastecimento")).getText());
		assertEquals("1 Vez(es)/Mês", driver.findElement(By.id("br.com.consumogasolina:id/editText_quantidade_abastecimento")).getText());
		assertEquals("34.14 Litro(s)/Mês", driver.findElement(By.id("br.com.consumogasolina:id/editText_quantidade_litros")).getText());
		assertEquals("0 KM/Mês", driver.findElement(By.id("br.com.consumogasolina:id/editText_quantidade_km")).getText());
		
	}
	
	@After
	public void tearDown() throws Exception {
		Utils.TiraPrint();
		driver.closeApp();
	}

}
