package driveTime

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import internal.GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import java.text.*;
import junit.framework.Assert

public class SearchCarsPage {

	@Keyword
	def filterCarsByMakeAndModel(make, model) {
		WebUI.click(findTestObject('Search Cars Page/Filters/filter_Make And Model'))
		WebUI.takeFullPageScreenshot()
		WebUI.click(make)
		WebUI.takeFullPageScreenshot()
		WebUI.click(findTestObject('Search Cars Page/Filters/Make And Model/filter_Select Models'))
		WebUI.takeFullPageScreenshot()
		WebUI.click(model)
		WebUI.takeFullPageScreenshot()
		WebUI.click(findTestObject('Search Cars Page/Filters/Make And Model/Model/button_Save'))
		WebUI.waitForPageLoad(2)
	}

	@Keyword
	def verifyFilteringCarsByMakeAndModel(make, model) {
		WebDriver driver = DriverFactory.getWebDriver()
		ArrayList<WebElement> carTitles = driver.findElements(By.cssSelector('.car-title'))
		for (int i = 0; i < 2; i++) {
			String title = carTitles.get(i).getText()
			Assert.assertTrue(title.contains(make))
			Assert.assertTrue(title.contains(model))
		}
	}

	@Keyword
	def searchCars(cityOrStateOrZipCode) {
		WebUI.setText(findTestObject('Search Cars Page/input_Search By City, State or ZIP'), cityOrStateOrZipCode)
		WebUI.takeFullPageScreenshot()
		WebUI.click(findTestObject('Search Cars Page/svg_Search'))
		WebUI.waitForPageLoad(2)
	}

	@Keyword
	def verifySearchingCarsByCity(city) {
		WebDriver driver = DriverFactory.getWebDriver()
		ArrayList<WebElement> cityNames = driver.findElements(By.cssSelector('.dealership-name.text-overflow-ellipsis'))
		for (int i = 0; i < 3; i++) {
			String cityName = cityNames.get(i).getText()
			WebUI.verifyEqual(cityName, city)
		}
	}

	@Keyword
	def verifySearchingCarsByState(stateAbbreviation) {
		WebDriver driver = DriverFactory.getWebDriver()
		for (int i = 0; i < 3; i++) {
			WebElement[] cars = driver.findElements(By.cssSelector('.content-wrapper'))
			cars[i].click()
			WebUI.waitForPageLoad(2)
			WebUI.verifyElementText(findTestObject('Object Repository/Search Cars Page/Car Page/span_State'), stateAbbreviation)
			WebUI.takeFullPageScreenshot()
			WebUI.back()
		}
	}

	@Keyword
	def verifySearchingCarsByZipCode(zipCode) {
		WebDriver driver = DriverFactory.getWebDriver()
		for (int i = 0; i < 3; i++) {
			WebElement[] cars = driver.findElements(By.cssSelector('.content-wrapper'))
			cars[i].click()
			WebUI.waitForPageLoad(2)
			WebUI.verifyElementText(findTestObject('Object Repository/Search Cars Page/Car Page/span_ZIP Code'), zipCode)
			WebUI.takeFullPageScreenshot()
			WebUI.back()
		}
	}

	@Keyword
	def verifySortingByMilesHighToLow() {
		WebDriver driver = DriverFactory.getWebDriver()
		ArrayList<WebElement> miles = driver.findElements(By.cssSelector('.odometer-reading'))
		NumberFormat format = NumberFormat.getIntegerInstance(Locale.US);
		for(int i = 1; i < miles.size(); i++) {
			int currentMiles = (int) format.parse(miles.get(i).getText());
			int previousMiles = (int) format.parse(miles.get(i -1 ).getText())
			assert currentMiles.compareTo(previousMiles) <= 0
		}
	}

	@Keyword
	def verifySortingByMilesLowToHigh() {
		WebDriver driver = DriverFactory.getWebDriver()
		ArrayList<WebElement> miles = driver.findElements(By.cssSelector('.odometer-reading'))
		NumberFormat format = NumberFormat.getIntegerInstance(Locale.US);
		for(int i = 1; i < miles.size(); i++) {
			int currentMiles = (int) format.parse(miles.get(i).getText());
			int previousMiles = (int) format.parse(miles.get(i -1 ).getText())
			assert currentMiles.compareTo(previousMiles) >= 0
		}
	}
}
