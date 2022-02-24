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

import internal.GlobalVariable

public class Navigations {

	@Keyword
	def openDriveTimeSite() {
		WebUI.openBrowser(GlobalVariable.baseURL)
		WebUI.waitForPageLoad(1)
		WebUI.maximizeWindow()
	}

	@Keyword
	def navigateToSearchCarsPage() {
		WebUI.click(findTestObject('Navigation Menu/a_Search Cars'))
		WebUI.waitForPageLoad(3)
	}


	@Keyword
	def navigateToFindDealershipPage() {
		WebUI.click(findTestObject('Navigation Menu/a_Find a Dealership'))
		WebUI.waitForPageLoad(1)
	}

	@Keyword
	def navigateToGetApprovedPage() {
		WebUI.click(findTestObject('Navigation Menu/a_Get Approved'))
		WebUI.waitForPageLoad(1)
	}
}
