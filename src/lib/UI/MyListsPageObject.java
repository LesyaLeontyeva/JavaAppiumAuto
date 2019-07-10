package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.util.List;

public class MyListsPageObject extends MainPageObject {
    public static final String
            FOLDER_BY_NAME_TPL = "//*[@resource-id='org.wikipedia:id/item_container']//*[contains(@text,'{FOLDER_NAME}')]",
            ARTICLE_BY_TITLE_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{TITLE}')]",
            ARTICLE_TITLE = "//*[@resource-id = 'org.wikipedia:id/page_list_item_title']",
            ARTICLE_TITLE_ON_PAGE = "org.wikipedia:id/view_page_title_text";

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder by name " + name_of_folder,
                10
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(By.xpath(article_xpath),
                "Cannot find saved article by title " + article_xpath,
                15);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath),
                "Saved article still present with title" + article_title,
                15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public int getElementsCountInList() {
        return this.getAmountOfElements(By.xpath(ARTICLE_TITLE));
    }

    public String getArticleNameInList() {

        String name_of_article_in_list = this.waitForElementAndGetAttribute(
                By.xpath(ARTICLE_TITLE),
                "text",
                "We don't find attribute of article in list",
                15
        );
        return name_of_article_in_list;
    }

    public void openArticleByIdInList() {
        this.waitForElementAndClick(
                By.xpath(ARTICLE_TITLE),
                "Cannot find article",
                15
        );
    }

    public String getArticleTitleOnPage() {
        String name_of_article_on_page = this.waitForElementAndGetAttribute(
                By.id(ARTICLE_TITLE_ON_PAGE),
                "text",
                "We don't find attribute of article on page",
                15
        );
        return name_of_article_on_page;
    }
}