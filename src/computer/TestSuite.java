package computer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utilities.Utility;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBeticalOrder() {
        // Click on Computer Menu
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']/child::li[1]"));
        // Click on Desktop
        clickOnElement(By.xpath("//div[@class='block block-category-navigation']/descendant::a[2]"));
        // Select sort by Name Z to A
        selectByVisibleTextFromDropDown(By.id("products-orderby"), "Name: Z to A");
        // verify the product in Descending order
        verifyProductAreInDescendingOrder();
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessfully() throws InterruptedException {
        // Click on Computer Menu
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']/child::li[1]"));
        // Click on Desktop
        clickOnElement(By.xpath("//div[@class='block block-category-navigation']/descendant::a[2]"));
        // Select sort by position Name A to Z
        selectByVisibleTextFromDropDown(By.id("products-orderby"), "Name: A to Z");
        // Exception
        Thread.sleep(1000);
        // click on Add to Cart
        clickOnElement(By.cssSelector("div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > div:nth-child(2) > button:nth-child(1)"));
        // verify the text
        verifyText("Build your own computer", By.xpath("//h1[text()='Build your own computer']"), "Build your own computer text is not displayed");
        // Select "2.2 GHZ Intel Pentium Dual" option
        selectByVisibleTextFromDropDown(By.id("product_attribute_1"), "2.2 GHz Intel Pentium Dual-Core E2200");
        // Select "8GB" option
        selectByVisibleTextFromDropDown(By.id("product_attribute_2"), "8GB [+$60.00]");
        // click on HDD radio button
        clickOnElement(By.id("product_attribute_3_7"));
        // Select OS radio 'vista Premium'
        clickOnElement(By.id("product_attribute_4_9"));
        // check two boxes
        clickOnElement(By.id("product_attribute_5_12"));
//        verifyText("$1,475,00",By.id("price-value-1"),"$1,475,00");
        clickOnElement(By.id("add-to-cart-button-1"));
        // Verify the Message 'The Product has been added'
        verifyText("The product has been added to your shopping cart", By.xpath("//p[text()='The product has been added to your ']"), "Product added to cart message is not displayed");
        // click on close
        clickOnElement(By.xpath("//span[@title='Close']"));
        // Mouse huvoer on Shopping Cart
        doMouseHoverNoClick(By.xpath("//span[text()='Shopping cart']"));
        // click on Go to Cart
        clickOnElement(By.xpath("//button[text()='Go to cart']"));
        // verify the text
        verifyText("Shopping cart", By.xpath("//h1[text()='Shopping cart']"), "Shopping cart text is not visible");
        // Change quantity to 2
        sendTextElement(By.xpath("//input[@class='qty-input']"), Keys.BACK_SPACE + "2");
        // click on Updatecart button
        clickOnElement(By.id("updatecart"));
        // verify the price
        verifyText("$2,950.00", By.xpath("//span[@class='value-summary']//strong"), "Total price is not displayed as expected");
        // click on terms of service text
        clickOnElement(By.id("termsofservice"));
        // click on Checkout
        clickOnElement(By.id("checkout"));
        // verify the text
        verifyText("Welcome, Please Sign In!", By.xpath("//div[@class='page-title']//h1"), "Welcome message is not displayed as expected");
        // click on Check as Guest
        clickOnElement(By.xpath("//button[text()='Checkout as Guest']"));
        // Enter Username field
        sendTextElement(By.id("BillingNewAddress_FirstName"), "Henry");
        // Enter Lastname field
        sendTextElement(By.id("BillingNewAddress_LastName"), "smith");
        // Enter email field
        sendTextElement(By.id("BillingNewAddress_Email"), randomEmailGenerator());
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
        // click on shipping option
        clickOnElement(By.id("shippingoption_1"));
        // click on shipping option
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        // click on Payments method
        clickOnElement(By.id("paymentmethod_1"));
        // click on options
        clickOnElement(By.xpath("//button[@name='save' and @class='button-1 payment-method-next-step-button']"));
        // Select Master Card
        selectByVisibleTextFromDropDown(By.id("CreditCardType"), "Master card");
        // Enter Cardholder name
        sendTextElement(By.id("CardholderName"), "Rishi Sunak");
        // Enter Card number
        sendTextElement(By.id("CardNumber"), "5356654814185420");
        // Select dropdown option
        selectByVisibleTextFromDropDown(By.id("ExpireMonth"), "09");
        // Select dropdown option
        selectByVisibleTextFromDropDown(By.id("ExpireYear"), "2026");
        // Select dropdown option
        sendTextElement(By.id("CardCode"), "123");
        // click on Payment
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        // verify the text
        verifyText("Credit Card", By.xpath("//li[@class='payment-method']//span[@class='value']"), "Payment method is displayed incorrectly");
        // verify the text
        verifyText("Next Day Air", By.xpath("//li[@class='shipping-method']//span[@class='value']"), "Shipping Method is displayed incorrectly");
        // verify the text
        verifyText("$2,950.00", By.xpath("//span[@class='value-summary']//strong"), "Total Amount is displayed incorrectly");
        //Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        // Verify Thank you message
        verifyText("Thank you", By.xpath("//h1[contains(text(),'Thank you')]"), "Thank You Message is incorrectly displayed");
        //Verify the message “Your order has been successfully processed!”
        verifyText("Your order has been successfully processed!", By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"), "Order has not been processed successfully");
        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        //Verify the text “Welcome to our store”
        verifyText("Welcome to our store", By.xpath("//h2[contains(text(),'Welcome to our store')]"), "Welcome to our store has been incorrectly displayed");
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
