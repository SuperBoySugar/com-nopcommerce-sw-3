package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Utility extends BaseTest {

    /**
     * This method will click on element
     */
    public void clickOnElement(By by){
        driver.findElement(by).click();
    }
    /**
     * This method will send text to element
     */
    public void sendTextElement(By by, String text){
        driver.findElement(by).sendKeys(text);
    }


    /**
     * This method will get text from element
     */
    public String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }
    /**
     * This method will get the list of elements
     */
//    public WebElement findElementFromWebPage(By by){
//        return driver.findElement(by);
//    }
    public List<WebElement> findElementsFromWebPage(By by){
        return driver.findElements(by);
    }

    /**
     * This method will select text from Dropdown
     */
    public void selectByVisibleTextFromDropDown(By by, String text){
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        // Select by visible text
        select.selectByVisibleText(text);
    }

    /**
     * This Method will verify text using Assert
     */
    public void verifyText(String expectedMessage, By by, String displayMessage) {
        String actualMessage = getTextFromElement(by);
        Assert.assertEquals(displayMessage, expectedMessage, actualMessage);
    }

    /**
     * This Methd will Sort product by Name A - Z
     */
    By productTitle = By.cssSelector(".products .product-item-info .product-item-name");
    public void verifyProductAreInAscendingOrder() {
        List<WebElement> originalList = driver.findElements(productTitle);
        List<String> originalProductNameList = new ArrayList<>();
        for (WebElement product : originalList) {
            originalProductNameList.add(product.getText());
        }
        Collections.sort(originalProductNameList);
        System.out.println(originalProductNameList);

        List<WebElement> afterSortingList = driver.findElements(productTitle);
        List<String> afterSortingProductName = new ArrayList<>();
        for (WebElement product : afterSortingList) {
            afterSortingProductName.add(product.getText());
        }
        System.out.println(afterSortingProductName);
        Assert.assertEquals("Product not sorted", originalProductNameList, afterSortingProductName);
    }

    //************************* Sort Products by Name Z To A ***************************//
    By productList = By.cssSelector(".product-thumb h4 a");

    public void verifyProductAreInDescendingOrder()  {
//        Thread.sleep(2000);
        List<WebElement> originalList = driver.findElements(productList);
        List<String> originalProductNameList = new ArrayList<>();
        for (WebElement product : originalList) {
            originalProductNameList.add(product.getText());
        }

        originalProductNameList.sort(String.CASE_INSENSITIVE_ORDER.reversed());
        System.out.println(originalProductNameList);

        List<WebElement> afterSortingList = driver.findElements(productList);
        List<String> afterSortingProductName = new ArrayList<>();
        for (WebElement product1 : afterSortingList) {
            afterSortingProductName.add(product1.getText());
        }
        System.out.println(afterSortingProductName);
        Assert.assertEquals("Product not sorted", originalProductNameList, afterSortingProductName);
    }

    //********************* Sort Products by Price High - Low ****************************//
    By productPrice = By.xpath("//div[@class='row']//div[@class='product-thumb']//span[@class='price-tax']");

    public void verifyProductsAreSortedByHighToLow() {

        List<WebElement> originalList = driver.findElements(productPrice);
        List<Double> beforeFilterPriceList = new ArrayList<>();
        for (WebElement price : originalList) {
            beforeFilterPriceList.add(Double.valueOf(price.getText().replace("Ex Tax:$", "")));
        }
        System.out.println("Before Sorting: " + beforeFilterPriceList);
        Collections.sort(beforeFilterPriceList, Collections.reverseOrder());

        List<WebElement> afterSortingList = driver.findElements(productPrice);
        List<Double> afterSortingPriceList = new ArrayList<>();
        for (WebElement price : afterSortingList) {
            afterSortingPriceList.add(Double.valueOf(price.getText().replace("Ex Tax:$", "")));
        }
        System.out.println("After Sorting: " + afterSortingPriceList);
        Assert.assertEquals(beforeFilterPriceList, afterSortingPriceList);
    }
    //*************************** Action Methods ***************************************//


    // This method will mouse hover on element
    public void mouseHoverToElement(By by) throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement mouseHoover = driver.findElement(by);
//        Thread.sleep(3000);
//        waitUnitVisibilityOfElementLocated(by,2000);
        actions.moveToElement(mouseHoover).build().perform();
    }

    /**
     * This method will use to hover mouse on element
     */
    public void doMouseHoverNoClick(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();
    }

    /**
     * This method will mouse hover and Click on element
     */
    public void doMouseHoverAndClick(By by) {
        Actions hover = new Actions(driver);
        WebElement a = driver.findElement(by);
        hover.moveToElement(a).click().build().perform();
    }

    /**
     * This method will Mouse hover on First element then Second element but will not click
     */
    public void doMouseHoverOnFirstThenSecondElement(By by1, By by2) {
        Actions hover = new Actions(driver);
        WebElement destination1 = driver.findElement(by1);
        WebElement destination2 = driver.findElement(by2);
        hover.moveToElement(destination1).moveToElement(destination2).build().perform();
    }

    /**
     * This Method will hover mouse on one element, then on second element
     * and click the second element
     */
    public void doMouseHoverOnFirstThenSecondAndClick(By by1, By by2) {
        Actions hover = new Actions(driver);
        WebElement destination1 = driver.findElement(by1);
        WebElement destination2 = driver.findElement(by2);
        hover.moveToElement(destination1).moveToElement(destination2).click().build().perform();
    }

    public void selectMenu(By by, String menu) {
        List<WebElement> topMenuNames = driver.findElements(by);
        for (WebElement names : topMenuNames) {
            if (names.getText().equalsIgnoreCase(menu)) {
                names.click();
                break;
            }
        }
    }

    // Generate Random email
    public String randomEmailGenerator(){
        String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder email = new StringBuilder();
        Random randomEmail= new Random();
        while (email.length()<10) {
            int index = (int) (randomEmail.nextFloat() * chars.length());
            email.append(chars.charAt(index));
        }
        String saltStr = (email.toString()+"@gmail.com");
        return saltStr;
    }

}
