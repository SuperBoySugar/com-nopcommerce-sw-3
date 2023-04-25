package electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utilities.Utility;

public class ElectronicsTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToCellPhonesPageSuccessfully() {
        // Mouse Hover on Electronics
        doMouseHoverNoClick(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));
        // Mouse Hover on Cell Phones and click
        doMouseHoverAndClick(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        // Verify the text Cell Phone
        verifyText("Cell phones", By.xpath("//h1[text()='Cell phones']"), "The text does not display");
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        // Mouse Hover on Electronics Tab
        doMouseHoverNoClick(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));
        // Mouse Hover on Cell phones and click
        doMouseHoverAndClick(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        // Verify the text Cell Phone
        verifyText("Cell phones", By.xpath("//h1[text()='Cell phones']"), "The text does not display");
        // Click on List View Tab
        clickOnElement(By.xpath("//a[contains(text(),'List')]"));
        Thread.sleep(1000);
        clickOnElement(By.cssSelector("div:nth-of-type(3) > .product-item h2 > a"));
        //  Verify the text Nokia Lumia 1020
        verifyText("Nokia Lumia 1020", By.cssSelector("div[class='product-name'] h1"), "Text does not display");
        //  Verify the price $349.00
        verifyText("$349.00", By.id("price-value-20"), "Price is not display");
        //  Change the quantity to 2
         sendTextElement(By.id("product_enteredQuantity_20"), Keys.BACK_SPACE + "2");
        //  Click on Add To Cart Button
        clickOnElement(By.id("add-to-cart-button-20"));
        // Verify the text Message
        verifyText("The product has been added to your shopping cart", By.xpath("//p[@class='content']"), "Message is not displayed");
        //After that close the bar clicking on the cross button
        clickOnElement(By.cssSelector("span[title='Close']"));
        //Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        doMouseHoverAndClick(By.xpath("//span[text()='Shopping cart']"));
        clickOnElement(By.xpath("//button[text()='Go to cart']"));
        // Verify the message Shopping Cart
        verifyText("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping Cart displayed incorrect");
        // Verify the quantity is 2
//         verifyText("2", By.xpath("//input[@class='qty-input']"),"Quantity is not displayed");
        String expectedMessage = "2";
        String actualMessage = driver.findElement(By.xpath("//tbody/tr[1]/td[5]/input[1]")).getAttribute("value");
        Assert.assertEquals("Quantity is not correct", expectedMessage, actualMessage);
        // Verify the Total $698.00
        verifyText("$698.00", By.cssSelector(".product-subtotal"), "Price displayed incorrect");
        // Click on checkbox I agree with the terms of service
        clickOnElement(By.id("termsofservice"));
        // Click on CheckOut
        clickOnElement(By.id("checkout"));
        // Verify the text Welcome, Please Sign in
        verifyText("Welcome, Please Sign In!", By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), "Message is not displayed");
        // Click on Register Button
        clickOnElement(By.xpath("//button[text()='Register']"));
        // Verify the text Register
        verifyText("Register", By.xpath("//h1[text()='Register']"), "Register message is not display correctly");
        // Fill the mandatory fields
        sendTextElement(By.id("FirstName"), "Henry");
        sendTextElement(By.id("LastName"), "smith");
        sendTextElement(By.id("Email"), randomEmailGenerator());
        sendTextElement(By.id("Password"), "Password1");
        sendTextElement(By.id("ConfirmPassword"), "Password1");
        // Click on Register Button
        clickOnElement(By.id("register-button"));
        // Verify the message
        verifyText("Your registration completed", By.xpath("//div[@class='result']"), "Your are not registered");
        // Click on Continue button
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
        Thread.sleep(5000);
        // Verify the text Shopping cart
        verifyText("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart is displayed empty");
        // Click on CheckOut
        Thread.sleep(5000);
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        // Click on Checkout
        clickOnElement(By.xpath("//button[@id='checkout']"));
        // Select country name
        selectByVisibleTextFromDropDown(By.id("BillingNewAddress_CountryId"), "India");
        // Enter city name
        sendTextElement(By.id("BillingNewAddress_City"), "Ahmedabad");
        // Enter Address
        sendTextElement(By.id("BillingNewAddress_Address1"), "123 S G Highway");
        // Enter zip code
        sendTextElement(By.id("BillingNewAddress_ZipPostalCode"), "364001");
        // Enter Telephone number
        sendTextElement(By.id("BillingNewAddress_PhoneNumber"), "0123456789");
        // Click on button
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        // Select Radio button 2nd Day Air ($0.00)
        clickOnElement(By.id("shippingoption_2"));
        // Click on Continue button
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        // Select Redio button Credit Card
        clickOnElement(By.id("paymentmethod_1"));
        // Select Visa from Select credit card dropdown
        clickOnElement(By.xpath("//button[@name='save' and @class='button-1 payment-method-next-step-button']"));
        // Fill all the details
        selectMenu(By.xpath("//select[@id='CreditCardType']"), "0");
        sendTextElement(By.xpath("//input[@id='CardholderName']"), "John Smith");
        sendTextElement(By.xpath("//input[@id='CardNumber']"), "4700 1234 5678 7840");
        sendTextElement(By.xpath("//select[@id='ExpireMonth']"), "10");
        sendTextElement(By.xpath("//select[@id='ExpireYear']"), "2030");
        sendTextElement(By.xpath("//input[@id='CardCode']"), "999");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        /* Verify "Payment Method" is "Credit Card"
        Verify "Shipping Method" is 2nd Day Air
        Verify Total is "$698.00"
         */
        verifyText("Credit Card", By.xpath("//span[contains(text(),'Credit Card')]"), "Payment method is displayed incorrectly");
        verifyText("2nd Day Air", By.cssSelector("li[class='shipping-method'] span[class='value']"), "Shipping Method is displayed incorrectly");
        verifyText("$698.00", By.xpath("//span[contains(text(),'$698.00')]"), "Total Amount is displayed incorrectly");
        // Click on confirm
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        // Verify the Text "Thank You"
        verifyText("Thank you", By.xpath("//h1[contains(text(),'Thank you')]"), "Thank You Message is incorrectly displayed");
        //Verify the message “Your order has been successfully processed!”
        verifyText("Your order has been successfully processed!", By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"), "Order has not been processed successfully");
        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        //Verify the text “Welcome to our store”
        verifyText("Welcome to our store", By.xpath("//h2[contains(text(),'Welcome to our store')]"), "Welcome to our store has been incorrectly displayed");
        //Click on “Logout” link
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));
        //Verifying the URL
        String url = driver.getCurrentUrl();
        Assert.assertEquals("The URL is incorrect", url, "https://demo.nopcommerce.com/");
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}