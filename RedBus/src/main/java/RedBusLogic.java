import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusLogic {

	public static void main(String[] args) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		String url = "https://www.redbus.in/";
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(url);
		// Thread.sleep(5000);
		String dateIcon = "//div[@class='labelCalendarContainer']";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateIcon)));
		String allSpanItems = "//span[contains(@class,'DayTiles__CalendarDaysSpan')]";
		// String holidayCount="//div[@class='holiday_count']";
		String monthWithHoliday = "//div[contains(@class,'DayNavigator__') and contains(@style,'font-size')]";
		String navigateForward = "//div[contains(@class,'DayNavigator__CalendarHeader')]//div[contains(@class,'DayNavigator__IconBlock')][last()]";
		driver.findElement(By.xpath(dateIcon)).click();
		String inputData = "Nov 2024";
		validateData vd = new validateData();
		boolean status = vd.validateInputData(inputData);
		if (status) {
			String weekendColor = "rgba(216, 78, 85, 1)";
			String currentWeekendColor = "rgba(255, 255, 255, 1)";
			String monthAndHolidayCountUI = driver.findElement(By.xpath(monthWithHoliday)).getText();
			System.err.println(monthAndHolidayCountUI);
			while (!monthAndHolidayCountUI.contains(inputData)) {

				driver.findElement(By.xpath(navigateForward)).click();
				monthAndHolidayCountUI = driver.findElement(By.xpath(monthWithHoliday)).getText();
				System.err.println(monthAndHolidayCountUI);
			}
			List<WebElement> allSpanEle = driver.findElements(By.xpath(allSpanItems));
			for (WebElement ele : allSpanEle) {
				String cssProp = ele.getCssValue("color");
				if (cssProp.equals(weekendColor) || cssProp.equals(currentWeekendColor)) {

					String uiText = ele.getText();
					System.out.println(uiText);
				}

			}
		} else {
			System.err.println("Invalid Data");
		}
		driver.quit();
	}

}
