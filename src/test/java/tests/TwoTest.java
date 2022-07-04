package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.Base;

public class TwoTest extends Base {

	WebDriver driver;

	@Test
	public void twotest() throws IOException, InterruptedException {

		System.out.println("twotest");
		driver = initializeDriver();
		driver.get("http://www.tutorialsninja.com/demo/");
		Thread.sleep(3000);
		driver.close();
	}
}
