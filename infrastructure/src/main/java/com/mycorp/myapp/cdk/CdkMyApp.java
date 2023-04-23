package com.mycorp.myapp.cdk;

import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.Environment;

public final class CdkMyApp {
    public static void main(final String[] args) {

        var app = new App();
        String account = (String) app.getNode().tryGetContext("account");
        String region = (String) app.getNode().tryGetContext("region");
        Environment environment = Environment.builder()
                .account(account)
                .region(region)
                .build();
        final StackProps stackProps = StackProps.builder().env(environment).build();
        new MyAppStack(app, "MyAppStack", stackProps);
        app.synth();
    }
}
