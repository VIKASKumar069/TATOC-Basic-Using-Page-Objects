package firsttestngpackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TatocBasicTest {
	
	WebDriver driver;// = new ChromeDriver();
	String url = "http://10.0.1.86/tatoc";	
	
	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\DeadpooL\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		List<WebElement> res = driver.findElements(By.tagName("a"));
		res.get(0).click();
	}

	@Test(priority=0)
    public void gridGateRed() {
    	Greenbox box = new Greenbox(driver);
    	box.clickRedbox();
    	Assert.assertTrue(box.getObstacleName().contains("Error"));
    }
    
    @Test(priority=1)
    public void gridGateGreen() {
    	driver.get("http://10.0.1.86/tatoc/basic/grid/gate");
    	Greenbox box = new Greenbox(driver);
    	box.clickGreenbox();
    	Assert.assertTrue(box.getObstacleName().contains("Frame Dungeon"));
    }
    
    @Test(priority=2)
    public void differentBoxes() {
    FrameDungeon obj = new FrameDungeon(driver);
    obj.diffrentColor(); 
	Assert.assertTrue(obj.getObstacleName().contains("Error"));
    }
    
    @Test(priority=3)
    public void sameBoxes() {
    	driver.get("http://10.0.1.86/tatoc/basic/frame/dungeon");
    FrameDungeon obj = new FrameDungeon(driver);

    obj.sameColor();
	Assert.assertTrue(obj.getObstacleName().contains("Drag Around"));
    }
    
    @Test(priority=4)
    public void noDrag() {
    	DragAround obj = new DragAround(driver);
		obj.noDrag();
		Assert.assertFalse(obj.getObstacleName().contains("Popup Windows"));
    	
    } 
    
	@Test(priority=5)
	public void dragCorrect() {
		driver.get("http://10.0.1.86/tatoc/basic/drag#");
		DragAround obj = new DragAround(driver);
		obj.correctDrag();
		Assert.assertTrue(obj.getObstacleName().contains("Popup Windows"));
	}
	
	@Test(priority=6)
	public void popUpNotOpen() {
		popupWindows obj = new popupWindows(driver);
		obj.proceed();
		Assert.assertFalse(obj.getObstacleName().contains("Cookie Handling"));
	}
	
	@Test(priority=7)
	public void emptySubmitPopUp() {
		driver.get("http://10.0.1.86/tatoc/basic/windows");
		popupWindows obj = new popupWindows(driver);
		obj.emptySubmitPopUp();
		Assert.assertFalse(obj.getObstacleName().contains("Cookie Handling"));
	}
    
	@Test(priority=8)
	public void textSubmitPopUp() {
		driver.get("http://10.0.1.86/tatoc/basic/windows");
		popupWindows obj = new popupWindows(driver);
		obj.textSubmitPopUp();
		Assert.assertTrue(obj.getObstacleName().contains("Cookie Handling"));
	}
	
	@Test(priority=9)
	public void noTokenGenerateNotAddCookie() {
		CookieHandling obj = new CookieHandling(driver);
		obj.proceed();
		Assert.assertFalse(obj.getMessage().contains("End"));
	}
	
	@Test(priority=10)
	public void tokenGenerateNotAddCookie() {
		driver.get("http://10.0.1.86/tatoc/basic/cookie#");
		CookieHandling obj = new CookieHandling(driver);
		obj.generateToken();
		obj.proceed();
		Assert.assertFalse(obj.getMessage().contains("End"));
	}
	
	@Test(priority=11)
	public void tokenGenerateAddWrongCookie() {
		driver.get("http://10.0.1.86/tatoc/basic/cookie#");
		CookieHandling obj = new CookieHandling(driver);
		obj.addWrongCookie();
		obj.proceed();
		Assert.assertFalse(obj.getMessage().contains("End"));
	}
	
	@Test(priority=12)
	public void TokenGenerateAddCorrectCookie() {
		driver.get("http://10.0.1.86/tatoc/basic/cookie#");
		CookieHandling obj = new CookieHandling(driver);
		obj.addCorrectCookie();
		obj.proceed();
		Assert.assertTrue(obj.getMessage().contains("End"));
	}
	
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}
	
}