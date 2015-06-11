package br.fpf.mobile.test.WhatsApp;

import static org.junit.Assert.assertEquals;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.io.IOException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.fpf.test.utils.AndroidDriverCreator;
import br.fpf.test.utils.Utils;

/*
 * Classe de teste
 * Criado por: Vanilton Pinheiro em 11/06/2015  
 */

public class MensagemTest extends AndroidDriverCreator {

	
	@Before
	public void configuracoesIniciais() throws Exception {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		//Setando o pacote da aplicação para as capacidades
		cap.setCapability(MobileCapabilityType.APP_PACKAGE, "com.whatsapp");
		//Setando a Activity da aplicação do telefone
		cap.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.whatsapp.Main");
		//Definindo o servidor appium ativo que será utilizado
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	}

	 /* OBS: Os elementos cuja ocorre iteração irão mudar de acordo com a versão do android 
	  * e modelo de device*/
		
	@Test
	public void EnviarMensagemWhats() throws InterruptedException, IOException {
		WebDriverWait  wait = new WebDriverWait(driver, 30);
		AndroidElement TituloApp = (AndroidElement) driver.findElementByXPath("//android.widget.TextView[@text='WhatsApp']");
		wait.until(ExpectedConditions.visibilityOf(TituloApp));
		driver.findElementById("com.whatsapp:id/menuitem_search").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.whatsapp:id/search_src_text")));
		driver.findElementById("com.whatsapp:id/search_src_text").click();
		driver.findElementById("com.whatsapp:id/search_src_text").sendKeys("Clineu");
		driver.findElementByAndroidUIAutomator(
				"new UiSelector().text(\"João Clineu\")").click();
		assertEquals("João Clineu",driver.findElementById("com.whatsapp:id/conversation_contact_name").getText());
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.whatsapp:id/entry")));
		driver.findElementById("com.whatsapp:id/entry").sendKeys("Testando Zap Zap");
		driver.findElementById("com.whatsapp:id/send").click();
		assertEquals("",driver.findElementById("com.whatsapp:id/entry").getText());

	}
	
	@After
	public void tearDown() throws Exception {
		Utils.TiraPrint();
		driver.closeApp();
	}

}
