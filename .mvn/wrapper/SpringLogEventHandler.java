package com.nila.arya.events;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.nila.arya.Application;

public class SpringLogEventHandler implements RequestHandler<SNSEvent, Object> {

	private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
	static {
		try {
			handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(Application.class);
		} catch (ContainerInitializationException e) {
			// if we fail here. We re-throw the exception to force another cold start
			e.printStackTrace();
			throw new RuntimeException("Could not initialize Spring Boot application", e);
		}
	}

	public Object handleRequest(SNSEvent request, Context context) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		context.getLogger().log("Invocation started: " + timeStamp);

		context.getLogger().log("Message : " + request.getRecords().get(0).getSNS().getMessage());

		context.getLogger().log("Message Attributes : " + request.getRecords().get(0).getSNS().getMessageAttributes());

		timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		context.getLogger().log("Invocation completed: " + timeStamp);
		return null;
	}
}
