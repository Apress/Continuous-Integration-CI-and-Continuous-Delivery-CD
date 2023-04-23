package com.mycorp.myapp.mylambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class TestContext implements Context{

  public TestContext() {}
  public String getAwsRequestId(){
    return new String("234b12f4-xmpe-5gca-8456-160426754f99");
  }
  public String getLogGroupName(){
    return new String("/aws/lambda/myLambda");
  }
  public String getLogStreamName(){
    return new String("2022/10/16/[$LATEST]005377da2a6f4c3990bda5d5872acc91");
  }
  public String getFunctionName(){
    return new String("myLambda");
  }
  public String getFunctionVersion(){
    return new String("$LATEST");
  }
  public String getInvokedFunctionArn(){
    return new String("arn:aws:lambda:us-east-1:497562947267:function:mmyLambda");
  }
  public CognitoIdentity getIdentity(){
    return null;
  }
  public ClientContext getClientContext(){
    return null;
  }
  public int getRemainingTimeInMillis(){
    return 300000;
  }
  public int getMemoryLimitInMB(){
    return 512;
  }
  public LambdaLogger getLogger(){
    return new TestLogger();
  }

}