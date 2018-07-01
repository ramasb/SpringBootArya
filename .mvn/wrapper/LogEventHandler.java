package com.nila.arya.events;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;

public class LogEventHandler implements RequestHandler<SNSEvent, Object> {
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