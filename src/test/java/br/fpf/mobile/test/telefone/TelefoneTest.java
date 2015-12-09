package br.fpf.mobile.test.telefone;

import static org.junit.Assert.assertEquals;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.fpf.test.utils.AndroidDriverCreator;

/*
 * Classe de teste
 * Criado por: Vanilton Pinheiro em 11/06/2015  
 */

public class TelefoneTest extends AndroidDriverCreator {

	private String numeroCelular = "991289565";
	static AppiumDriverLocalService service;

	@BeforeClass
	public static void configureServer() {
		service = AppiumDriverLocalService.buildService(
				new AppiumServiceBuilder().withArgument(GeneralServerFlag.SESSION_OVERRIDE));	
		service.start();
	}
	
	@Before
	public void configuracoesIniciais() throws Exception {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		//Setando o pacote da aplicação para as capacidades
		cap.setCapability(MobileCapabilityType.APP_PACKAGE, "com.android.dialer");
		//Setando a Activity da aplicação do telefone
		cap.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.android.dialer.DialtactsActivity");
		//Definindo o servidor appium ativo que será utilizado
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	}

	 /* OBS: Os elementos cuja ocorre iteração irão mudar de acordo com a versão do android 
	  * e modelo de device*/
	@Test
	public void RealizarLigacao() throws InterruptedException {
		
		driver.findElementById("com.android.dialer:id/floating_action_button").click();
		WebDriverWait  wait = new WebDriverWait(driver, 10);
		//Aguardo o elemento para abrir o teclado numero ser exibido
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.dialer:id/dialpad_floating_action_button")));
		for(int i=0; i<numeroCelular.length(); i++){
			//Clicando no elemento com context desc
			driver.findElementByAccessibilityId(String.valueOf(numeroCelular.charAt(i))).click();	
		}
		//Clicar no botão para realizar a chamada
		driver.findElementById("com.android.dialer:id/dialpad_floating_action_button").click();
		//Aguardo a exibição do botão de encerrar chamada
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.android.dialer:id/floating_end_call_action_button")));
		//Verifico se o numero discado está correto
		assertEquals("991289565", driver.findElementById("com.android.dialer:id/phoneNumber").getText());
		//Clico no botão para ativar o viva voz
		driver.findElementById("com.android.dialer:id/audioButton").click();
		//Espera de 20 segundos
		Thread.sleep(20000);
		//Encerra a chamada
		driver.findElementById("com.android.dialer:id/floating_end_call_action_button").click();
		
	}
	
	@After
	public void tearDown() throws Exception {
		driver.closeApp();
	}

}
