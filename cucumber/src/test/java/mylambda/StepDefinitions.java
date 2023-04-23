package mylambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.HashMap;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import java.nio.charset.StandardCharsets;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.model.ServiceException;
import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {
    private String result;

    @Given("myLambda is running")
    public void mylambda_is_running() {
        // Let's assume it is running
    }

    @When("I send a valid request")
    public void i_send_a_valid_request() {
        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName("myLambda")
                .withPayload("{}");
        InvokeResult invokeResult = null;

        try {
            // This test should run in the pipeline in a AWSShellScript@1
            // The AWSShellScript@1 provides the environment variables loaded by EnvironmentVariableCredentialsProvider
            AWSLambda awsLambda = AWSLambdaClientBuilder.standard()
                    .withCredentials(new EnvironmentVariableCredentialsProvider())
                    .withRegion(Regions.US_EAST_1).build();

            invokeResult = awsLambda.invoke(invokeRequest);
            result = new String(invokeResult.getPayload().array(), StandardCharsets.UTF_8);

        } catch (ServiceException e) {
            System.out.println(e);
        }
    }

    @Then("I should get status {string}")
    public void i_should_get_status(String expectedResult) {
        assertEquals(expectedResult, result);
    }
}