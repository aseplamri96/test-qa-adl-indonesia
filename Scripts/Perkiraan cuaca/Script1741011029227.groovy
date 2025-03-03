import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

// Definisikan API Key
String apiKey = 'ae692cc22ce374262feb1d0cbe27068f'

// 1. Dapatkan Ramalan Cuaca 5 Hari di Jakarta Selatan
def responseWeather = WS.sendRequest(findTestObject('Ramalan cuaca 5 hari di Jakarta Selatan'))

// Cek Response Status
WS.verifyResponseStatusCode(responseWeather, 200)

println('Response Weather: ' + responseWeather.getResponseBodyContent())

// 2. Dapatkan Polusi Udara Terkini di Jakarta Selatan
def responseAirPollution = WS.sendRequest(findTestObject('Polusi udara terkini di Jakarta Selatan'))

// Cek Response Status
WS.verifyResponseStatusCode(responseAirPollution, 200)

println('Response Air Pollution: ' + responseAirPollution.getResponseBodyContent())

// Verifikasi beberapa data penting
def jsonWeather = WS.getElementPropertyValue(responseWeather, 'list[0].main.temp')

println(('Suhu saat ini: ' + jsonWeather) + '°C')

def jsonPollution = WS.getElementPropertyValue(responseAirPollution, 'list[0].components.pm10')

println(('Tingkat polusi PM10: ' + jsonPollution) + ' µg/m³')

// Tambahkan Assertion
assert jsonWeather != null : 'Gagal mendapatkan suhu cuaca'

assert jsonPollution != null : 'Gagal mendapatkan data polusi'

