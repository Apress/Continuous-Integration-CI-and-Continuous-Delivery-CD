package com.mycorp.myapp.cdk;

import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Tags;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.events.*;
import software.amazon.awscdk.services.events.targets.*;

public class MyAppStack extends Stack {
    public MyAppStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public MyAppStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

        // Create the lambda
        String myHandlerCode = (String) getNode().tryGetContext("my-handler-path");
        String version = (String) getNode().tryGetContext("version");
        Function function = Function.Builder.create(this, id)
                .runtime(Runtime.JAVA_11)
                .functionName("myLambda")
                .code(Code.fromAsset(myHandlerCode))
                .memorySize(2048)
                .timeout(Duration.minutes(15))
                .handler("com.mycorp.myapp.mylambda.MyHandler::handleRequest")
                .build();

        // Create a schdule to run the lambda every minute
        Rule rule = Rule.Builder.create(this, "Schedule Rule every 5 minutes")
                .schedule(Schedule.cron(CronOptions.builder().minute("0/5").build()))
                .build();
        rule.addTarget(new LambdaFunction(function));

        // Tag this stack
        Tags.of(this).add("version", version);
    }
}
