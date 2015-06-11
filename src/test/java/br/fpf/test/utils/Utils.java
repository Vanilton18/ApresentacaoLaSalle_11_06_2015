package br.fpf.test.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Utils extends AndroidDriverCreator{

	public static String TimeAtual(){
		java.util.Date data = new java.util.Date();
		SimpleDateFormat data_formatada = new SimpleDateFormat("dd_MM_yyyy_HHmmss");
		return data_formatada.format(data);
		
	}
	
	public static void TiraPrint() throws IOException{
		File screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("log/Screenshot_"+TimeAtual()+".png"));	
	}
	
}
