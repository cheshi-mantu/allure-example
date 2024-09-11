package io.qameta.allure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.qameta.allure.Allure.parameter;

@Layer("rest")
@Owner("baev")
@Feature("Issues")
public class IssuesRestTest {

    private static final String OWNER = "allure-framework";
    private static final String REPO = "allure2";

    private final RestSteps steps = new RestSteps();

    @Story("Create new issue")
    @Microservice("Billing")
    @JiraIssues({@JiraIssue("AE-1")})
    @Tags({@Tag("api"), @Tag("smoke")})
    @DisplayName("Create issue via api")
    @ParameterizedTest(name = "({argumentsWithNames})")
    @AllureId("12378")
    @ValueSource(strings = {"First Note", "Second Note"})
    public void shouldCreateUserNote(@Param(value = "Title") String title) {
        steps.createIssueWithTitle(OWNER, REPO, title);
        steps.shouldSeeIssueWithTitle(OWNER, REPO, title);
    }

    @Story("Close existing issue")
    @Microservice("Repository")
    @Tags({@Tag("api"), @Tag("regress")})
    @JiraIssues({@JiraIssue("AE-2")})
    @DisplayName("Close issue via api")
    @ParameterizedTest(name = "({argumentsWithNames})")
    @AllureId("269231")
    @ValueSource(strings = {"First Note", "Second Note"})
    public void shouldDeleteUserNote(@Param(value = "Title") String title) {
        steps.createIssueWithTitle(OWNER, REPO, title);
        steps.closeIssueWithTitle(OWNER, REPO, title);
    }


}
