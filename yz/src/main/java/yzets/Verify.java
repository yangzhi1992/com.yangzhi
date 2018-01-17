package yzets;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Verify {
	public static void main(String[] args) throws IOException {
		/*System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");  
	    WebDriver driver = new ChromeDriver();  
	    driver.get("http://weixin.sogou.com/antispider/?from=%2fweixin%3Ftype%3d2%26query%3dz+%26ie%3dutf8%26s_from%3dinput%26_sug_%3dy%26_sug_type_%3d");
	    WebElement ele = driver.findElement(By.id("seccodeImage"));
	    // Get entire page screenshot
	    java.io.File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    BufferedImage  fullImg = ImageIO.read(screenshot);
	    // Get the location of element on the page
	    Point point = ele.getLocation();
	    // Get width and height of the element
	    int eleWidth = ele.getSize().getWidth();
	    int eleHeight = ele.getSize().getHeight();
	    // Crop the entire page screenshot to get only element screenshot
	    BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
	        eleWidth, eleHeight);
	    ImageIO.write(eleScreenshot, "png", screenshot);
	    // Copy the element screenshot to disk
	    File screenshotLocation = new File("D:/test.png");
	    FileUtils.copyFile(screenshot, screenshotLocation);
	    WebElement classelement = driver.findElement(By.className("p2"));
	    String errorText=classelement.getText();
	    System.out.println("验证码"+classelement.getText());*/
	    
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");  
	    WebDriver driver = new ChromeDriver();  
		// 让浏览器访问 Baidu
		driver.get("http://www.baidu.com");
		// 用下面代码也可以实现
		// driver.navigate().to("http://www.baidu.com");
		// 获取 网页的 title
		System.out.println("1 Page title is: " + driver.getTitle());
		// 通过 id 找到 input 的 DOM
		WebElement element = driver.findElement(By.id("kw"));
		// 输入关键字
		element.sendKeys("zTree");
		// 提交 input 所在的  form
		element.submit();
		// 通过判断 title 内容等待搜索页面加载完毕，间隔10秒
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		        return d.getTitle().toLowerCase().endsWith("ztree");
		    }
		});
		// 显示搜索结果页面的 title
		System.out.println("2 Page title is: " + driver.getTitle());
		//关闭浏览器
		driver.quit();
	}
}
