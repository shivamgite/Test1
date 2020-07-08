package simpleDevelopment.development2;

import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class Test1 {

		@Test
		public void method()throws Exception {
			String url = null;
			
			String driverPath = "src/test/resources/browserDrivers/";
			driverPath = driverPath+"Chrome"+File.separator+"chromedriver";
			System.setProperty("webdriver.chrome.driver", "/home/dell/Desktop/ShivaJars/chromedriver_linux64/chromedriver");
			
			
			ChromeOptions option = new ChromeOptions();
			option.addArguments("headless");
			option.addArguments("enable-automation"); 
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(ChromeOptions.CAPABILITY, option);
			WebDriver driver = new ChromeDriver(cap);
			
			driver.manage().window().maximize();
			driver.get("http://www.newtours.demoaut.com/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			List<WebElement> allLinks = driver.findElements(By.tagName("a"));
			Thread.sleep(5000);
			System.out.println("Total links are " + allLinks.size());
			
			for(WebElement eachLink : allLinks) {
						
				try {
					url = 	eachLink.getAttribute("href");
					HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
					conn.setConnectTimeout(2000);
					conn.connect();
					if(conn.getResponseCode()==200) 
						System.out.println(conn.getResponseMessage()+" :  "+"valid link");
					else 
						System.out.println(url + "\n"+conn.getResponseMessage()+" :  "+"invalid link");
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}catch(Exception e){
					e.printStackTrace();
				}finally {
					//driver.quit();
				}
			}
			driver.close();
			driver.quit();
		}


	}


