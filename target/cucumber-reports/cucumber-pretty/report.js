$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("login.feature");
formatter.feature({
  "line": 1,
  "name": "HubSpot Login Feature",
  "description": "",
  "id": "hubspot-login-feature",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 6,
  "name": "HubSpot Login Test Scenario",
  "description": "",
  "id": "hubspot-login-feature;hubspot-login-test-scenario",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "User is currently present on the Login Page",
  "keyword": "Given "
});
formatter.step({
  "line": 10,
  "name": "Title of Login Page is HubSpot Login",
  "keyword": "When "
});
formatter.step({
  "comments": [
    {
      "line": 12,
      "value": "#Then User enters \"shambo.choudhuri9@gmail.com\" and \"pHc#m8m6\""
    }
  ],
  "line": 14,
  "name": "User enters username and password",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "User lands on Home Page",
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "User quits the browser",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginStepDefinition.user_is_currently_present_on_the_Login_page()"
});
