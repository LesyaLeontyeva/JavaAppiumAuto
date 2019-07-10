package tests;

import lib.CoreTestCase;
import lib.UI.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.CreateListAddArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
    }
    @Test
    public void testSaveTwoArticlesToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.CreateListAddArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("JavaScript");
        SearchPageObject.clickByArticleWithSubstring("Programming language");


        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToCreatedList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);



        int count = MyListPageObject.getElementsCountInList();
        assertTrue(
                "We found too few results!",
                count == 1
        );

        String name_of_article_in_list = MyListPageObject.getArticleNameInList();


        MyListPageObject.openArticleByIdInList();

        String name_of_article_on_page = MyListPageObject.getArticleTitleOnPage();

        assertEquals(
                "Titles of articles are not the same",
                name_of_article_in_list,
                name_of_article_on_page
        );


    }

}
