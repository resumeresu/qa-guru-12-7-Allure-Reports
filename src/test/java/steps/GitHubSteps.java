package steps;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GitHubSteps {

    @Step("Open GitHub")
    public GitHubSteps openMainPage() {
        open("https://github.com");
        return this;
    }

    @Step("Search by given text: {text}")
    public GitHubSteps searchByText(String text) {
        $(".header-search-input").click();
        $(".header-search-input").setValue(text).pressEnter();
        return this;
    }

    @Step("Click the result by link text: {text}")
    public GitHubSteps clickResult(String text) {
        $(byLinkText(text)).click();
        $("#issues-tab").click();
        return this;
    }

    @Step("Open the Issues tab")
    public GitHubSteps openIssuesTab() {
        $("#issues-tab").click();
        return this;
    }

    @Step("Check the issue name #{number} has title: {title}")
    public GitHubSteps checkIssueNameHasText(String number, String title) {
        $(byId("issue_" + number + "_link")).shouldHave(text(title));
        return this;
    }
}
