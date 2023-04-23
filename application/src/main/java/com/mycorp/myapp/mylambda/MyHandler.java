package com.mycorp.myapp.mylambda;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;

// Handler value: example.Handler
public class MyHandler implements RequestHandler<Object, String>{
  Gson gson = new GsonBuilder().setPrettyPrinting().create();

  @Override
  public String handleRequest(Object input, Context context)
  {
    LambdaLogger logger = context.getLogger();
    String response = "200 OK";
    logger.log(gson.toJson("myLambda is healthy"));
    return response;
  }
}
