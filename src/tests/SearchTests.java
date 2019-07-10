package tests;

import lib.CoreTestCase;
import lib.UI.MainPageObject;
import lib.UI.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.ServiceConfigurationError;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

    }

    @Test

    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();

    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "kkjslkdfjl";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
    // HOMEWORK

    @Test
    public void testSearchText() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String article_title = SearchPageObject.defineElementText();

        assertEquals(
                "We see unexpected text!",
                "Search…",
                article_title

        );

    }

    @Test
    public void testSearchWordInResults() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Apple");
        SearchPageObject.waitForSearchResult("Apple");

    }

    @Test
    public void testCancelSearchHomeWork() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Apple");
        SearchPageObject.waitForSearchResult("Apple");
        int count = SearchPageObject.getAmountOfElements();
        assertEquals(
                "We see unexpected number of articles searching by 'Apple', presented number is " + count,
                6,
                count
        );
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForEmptyMessage();
    }

    @Test
    public void testOpenArticleAndSearchTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        SearchPageObject.waitForElementMustNotPresent();


//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                "Cannot find 'Search Wikipedia' input",
//                5
//
//        );
//
//        MainPageObject.waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text,'Search…')]"),
//                "Java",
//                "Cannot find search input",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
//                "Cannot find java search result",
//                5
//        );
//
//        MainPageObject.assertElementPresent(
//                By.id("org.wikipedia:id/view_page_title_text"),
//                "Cannot find article title"
//        );
    }
}
