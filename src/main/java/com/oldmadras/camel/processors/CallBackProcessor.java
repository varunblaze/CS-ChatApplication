package com.oldmadras.camel.processors;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.chatscript.ChatScriptMessage;


public class CallBackProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		final ChatScriptMessage body = exchange.getIn().getBody(ChatScriptMessage.class);
		exchange.getOut().setBody(body);
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 202);
	}

}
