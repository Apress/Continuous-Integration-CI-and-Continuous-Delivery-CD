package com.mycorp.myapp.mylambda;

import com.mycorp.myapp.mylambda.MyHandler;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

class InvokeTest {
  private static final Logger logger = LoggerFactory.getLogger(InvokeTest.class);

  @Test
  void invokeTest() {
    logger.info("invokeTest");
    String input = "{}";
    Context context = new TestContext();
    MyHandler handler = new MyHandler();
    String result = handler.handleRequest(input, context);
    assertTrue(result.contains("200 OK"));
  }

}