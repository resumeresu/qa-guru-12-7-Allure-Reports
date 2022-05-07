package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import steps.GitHubSteps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class GitHubTest {

    static String searchText = "eroshenkoam/allure-example";
    static String issueNumber = "76";
    static String issueTitle = "С Новым Годом (2022)";

    @Test
    public void issueTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-input").click();
        $(".header-search-input").setValue(searchText).pressEnter();
        $(byLinkText(searchText)).click();
        $("#issues-tab").click();

        $(byId("issue_" + issueNumber + "_link")).shouldHave(text(issueTitle));
    }

    @Test
    public void issueTestWithLambdas() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open GitHub", () -> open("https://github.com"));
        step("Search by given text: " + searchText, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(searchText).pressEnter();
        });
        step("Click the result", () -> $(byLinkText(searchText)).click());
        step("Open the Issues tab", () -> $("#issues-tab").click());
        step("Check the issue #" + issueNumber + " has title: " + searchText, () -> {
            $(byId("issue_" + issueNumber + "_link")).shouldHave(text(issueTitle));
        });
    }

    @Test
    public void issueTestWithAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        GitHubSteps steps = new GitHubSteps();

        steps.openMainPage()
                .searchByText(searchText)
                .clickResult(searchText)
                .openIssuesTab()
                .checkIssueNameHasText(issueNumber, issueTitle);

    }
}
