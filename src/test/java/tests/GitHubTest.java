package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GitHubTest {

    @Test
    public void issueTest() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        String searchText = "eroshenkoam/allure-example";
        String issueNumber = "76";
        String issueTitle = "С Новым Годом (2022)";

        open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").setValue(searchText).pressEnter();
        $(byLinkText(searchText)).click();
        $("#issues-tab").click();
        $(byId("issue_" + issueNumber + "_link")).shouldHave(text(issueTitle));

    }

}
