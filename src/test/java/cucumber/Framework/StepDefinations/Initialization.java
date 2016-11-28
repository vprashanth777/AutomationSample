package cucumber.Framework.StepDefinations;



import java.io.FileInputStream;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class Initialization {
	

	public static  WebDriver driver=null;
	public static AndroidDriver ADdriver=null;
	public static WebDriverWait WD=null;
	public static String winHandleBefore ;
	Properties properties;
	String BrowserType;
	
	
	 public Initialization()
	 {
		
		  properties = new Properties();
		 FileInputStream fis;
		try {
			fis = new FileInputStream("Data/TestProperties.xml");
			 properties.loadFromXML(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  
		
		 
			
	 }
	 
	 public String GetPropertValue(String S)
	 {
		return properties.getProperty(S);
		 
	 }
	
	 


	 
	public void GetDriverObject() 
	{
		String BrowserType=properties.getProperty("BrowserType");
		String Environment=properties.getProperty("Environment");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		
		
		if(driver==null)
		{
			if(BrowserType.equalsIgnoreCase("IE")) {
			
				System.setProperty("webdriver.ie.driver", "Drivers/IEDriverServer.exe");
				
				
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				

				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				WebDriver driver = new InternetExplorerDriver(capabilities);

				
				driver.manage().window().maximize();
			}
			
			
			else if(BrowserType.equalsIgnoreCase("CHROME")) {
				System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
				driver=new ChromeDriver();
				driver.manage().window().maximize();
			}
			
			else if(BrowserType.equalsIgnoreCase("FIREFOX")) {
				//System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
				driver=new FirefoxDriver();
				driver.manage().window().maximize();
				}
			else if(BrowserType.equalsIgnoreCase("NATIVE") || BrowserType.equalsIgnoreCase("HYBRID") ) {
				
				
			      
		        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		        capabilities.setCapability("deviceName", "Android Emulator");
		        capabilities.setCapability("app",properties.getProperty("MobAppPath"));
		        
			
			
			}
			
			
			else if(BrowserType.equalsIgnoreCase("MOBIWEB")) {
				
		           capabilities.setCapability(CapabilityType.BROWSER_NAME, "Browser");
		          // capabilities.setCapability("platformVersion", "4.2");
		           capabilities.setCapability("platformName", "Android");
		           capabilities.setCapability("deviceName", "Android Emulator");
		           try {
					driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//capabilities.setCapability(CapabilityType.BROWSER_NAME, "Browser");
		       // capabilities.setCapability("deviceName", "Android Emulator");
		       
			}
			
			else if(BrowserType.equalsIgnoreCase("BROWSERSTACK")) {
			 String USERNAME = "prasanthpillai1";
			 String AUTOMATE_KEY = "pGCB5mwUkBfv7acdEGjB";
			 String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
			 
			 ReadBrowserStack RBS=new ReadBrowserStack(Environment);
			 
					 if(Environment.equalsIgnoreCase("WINDOWS"))
					 {
					 capabilities.setCapability("browser", RBS.Browser);
					 capabilities.setCapability("browser_version", RBS.Browser_version);
					 capabilities.setCapability("os", RBS.OperatingSystem);
					 capabilities.setCapability("os_version", RBS.OperatingSystemversion);
					 capabilities.setCapability("resolution", "1024x768");
					 }
					 
					 else if(Environment.equalsIgnoreCase("Mobile"))
					 {
						 capabilities.setCapability("browserName", RBS.Browser);
						 capabilities.setCapability("platform", RBS.OperatingSystem);
						 capabilities.setCapability("device", RBS.Device);
					 }
			 
			 
			    try {
					driver = new RemoteWebDriver(new URL(URL), capabilities);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			else {
				System.out.println("Please select a valid Browser");
				System.exit(0);
			}
			
		}
		
	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WD=new WebDriverWait(driver, 60);
		
		
	}
	
	
	
	
	
	
	}
