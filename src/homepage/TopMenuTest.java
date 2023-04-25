package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class TopMenuTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    // Method to select category from top menu on homepage
    public void selectMenu(String menu) {
        List<WebElement> topMenuNames = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']//li//a"));
        for (WebElement names : topMenuNames) {
            if (names.getText().equalsIgnoreCase(menu)) {
                names.click();
                break;
            }
        }
    }

    @Test
    public void verifyComputersPageNavigation() {
        // Call the method and click on Computer
        selectMenu("Computers");
        // Verify the item display
        verifyText("Computers", By.partialLinkText("Computers"), "User has not navigated to the Computers Page");
    }

    @Test
    public void verifyElectronicsNavigation() {
        // Call the method and click on Electronics
        selectMenu("Electronics");
        // Verify the item display
        verifyText("Electronics", By.partialLinkText("Electronics"), "User has not navigated to the Electronics Page");
    }

    @Test
    public void verifyApparelPageNavigation() {
        // Call the method and click on Apparel
        selectMenu("Apparel");
        // Verify the item display
        verifyText("Apparel", By.partialLinkText("Apparel"), "User has not navigated to the Apparel Page");
        driver.close();
    }

    @Test
    public void verifyDigitalDownloadsPageNavigation() {
        // Call the method and click on Digital Downloads
        selectMenu("Digtital downloads");
        // Verify the item display
        verifyText("Digital downloads", By.partialLinkText("Digital downloads"), "User has not navigated to the Digital downloads Page");
    }

    @Test
    public void verifyBooksPageNavigation() {
        // Call the method and click on Books
        selectMenu("Books");
        // Verify the item display
        verifyText("Books", By.partialLinkText("Books"), "User has not navigated to the Apparel Page");
    }

    @Test
    public void verifyJewelryPageNavigation() {
        // Call the method and click on Jewelry
        selectMenu("Jewelry");
        // Verify the item display
        verifyText("Jewelry", By.partialLinkText("Jewelry"), "User has not navigated to the Jewelry");
    }

    @Test
    public void verifyGiftCardsPageNavigation() {
        // Call the method and click on Cards
        selectMenu("Gift Cards");
        // Verify the item display
        verifyText("Gift Cards", By.partialLinkText("Gift Cards"), "User has not navigated to the Gift Cards");
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}
