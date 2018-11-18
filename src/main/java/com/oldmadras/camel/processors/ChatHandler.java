package com.oldmadras.camel.processors;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.google.common.base.Optional;

public class ChatHandler implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		final String body = exchange.getIn().getBody(String.class);
		String value="false";
		if (Optional.fromNullable(body).isPresent())
		{
		if (body.contains("init=true"))
		{
			value="true";
			exchange.getIn().setBody(":reset");

		}
		
		}
		exchange.getOut().setHeader("init", value);
		exchange.getIn().setHeader("originalbody", body);
		exchange.getOut().setHeader("init", value);
		exchange.getOut().setHeader("originalbody", body);

	}

}