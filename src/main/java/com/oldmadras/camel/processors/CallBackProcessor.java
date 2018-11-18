package com.oldmadras.camel.processors;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CallBackProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		final String body = exchange.getIn().getBody(String.class);
		exchange.getOut().setBody(body);
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 202);
	}

}