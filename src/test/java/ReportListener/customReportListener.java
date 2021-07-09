package ReportListener;

import Steps.ApiSteps;
import Steps.BaseSteps;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.reporter.ExtentLoggerReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import io.cucumber.plugin.event.TestSourceRead;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.TestStepStarted;
import io.cucumber.plugin.event.HookTestStep;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class customReportListener extends ApiSteps implements EventListener {
    private ExtentSparkReporter spark;
    private ExtentReports extent;

    Map<String, ExtentTest> feature = new HashMap<String, ExtentTest>();
    ExtentTest scenario;
    ExtentTest step;

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        // TODO Auto-generated method stub
        /*
         * :: is method reference , so this::collecTag means collectTags method in
         * 'this' instance. Here we says runStarted method accepts or listens to
         * TestRunStarted event type
         */
        publisher.registerHandlerFor(TestRunStarted.class, this::runStarted);
        publisher.registerHandlerFor(TestRunFinished.class, this::runFinished);
        publisher.registerHandlerFor(TestSourceRead.class, this::featureRead);
        publisher.registerHandlerFor(TestCaseStarted.class, this::ScenarioStarted);
        publisher.registerHandlerFor(TestStepStarted.class, this::stepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::stepFinished);

    }
    /*
     * Here we set argument type as TestRunStarted if you set anything else then the
     * corresponding register shows error as it doesn't have a listner method that
     * accepts the type specified in TestRunStarted.class
     */
    // Here we create the reporter
    private void runStarted(TestRunStarted event) {
        spark = new ExtentSparkReporter("./ExtentReportResults.html");
        extent = new ExtentReports();
        spark.config().setTheme(Theme.DARK);
        // Create extent report instance with spark reporter
        extent.attachReporter(spark);
    }
    // TestRunFinished event is triggered when all feature file executions are completed
    private void runFinished(TestRunFinished event) {
        extent.flush();
    }
    // This event is triggered when feature file is read here we create the feature node
    private void featureRead(TestSourceRead event) {
        String featureSource = event.getUri().toString();
        String featureName = featureSource.split(".*/")[1];
        if (feature.get(featureSource) == null) {
            feature.putIfAbsent(featureSource, extent.createTest(featureName));

        }
    }
    // This event is triggered when Test Case is started here we create the scenario node
    private void ScenarioStarted(TestCaseStarted event) {
        String featureName = event.getTestCase().getUri().toString();
        scenario = feature.get(featureName).createNode(event.getTestCase().getName());

    }
    // step started event here we creates the test node
    private void stepStarted(TestStepStarted event) {
        String stepName = " ";
        String keyword = "Triggered the hook :";

        // We checks whether the event is from a hook or step
        if (event.getTestStep() instanceof PickleStepTestStep) {
            // TestStepStarted event implements PickleStepTestStep interface
            // WHich have additional methods to interact with the event object
            // So we have to cast TestCase object to get those methods
            PickleStepTestStep steps = (PickleStepTestStep) event.getTestStep();
            stepName = steps.getStep().getText();
            keyword = steps.getStep().getKeyword();

        } else {
            // Same with HoojTestStep
            HookTestStep hoo = (HookTestStep) event.getTestStep();
            stepName = hoo.getHookType().name();

        }
        step = scenario.createNode(Given.class, keyword + " " + stepName);

    }
    // This is triggered when TestStep is finished
    private void stepFinished(TestStepFinished event) {
        //lo tengo en la basePage porque lo tengo que instanciar aca!
        Response response = RestAssured.get("https://chercher.tech/sample/api/product/read/?id=4675");
        ResponseBody body = response.getBody();
        String responseBody = body.asPrettyString();
        String jsonResponse = prettyJsonString(responseBody);
        int respCode = response.getStatusCode();
        Integer tiempoRta = Math.toIntExact(response.getTimeIn(TimeUnit.MILLISECONDS));

        if (event.getResult().getStatus().toString() == "PASSED") {
            step.log(Status.PASS, "Código de respuesta: " + respCode);
            step.log(Status.PASS, "Tiempo: " + tiempoRta + "ms");
            step.log(Status.PASS, "<pre>" + jsonResponse + "</pre>" );

        } else if (event.getResult().getStatus().toString() == "SKIPPED") {
            step.log(Status.SKIP, "This step was skipped ");

        } else {
            step.log(Status.FAIL, "Código de respuesta: " + respCode);
            step.log(Status.FAIL, "Tiempo: " + tiempoRta + "ms");
            step.log(Status.FAIL, "<pre>" + jsonResponse + "</pre>" );
        }

    }
    public String prettyJsonString(String anAwfulJson){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(anAwfulJson);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;

    }

}


