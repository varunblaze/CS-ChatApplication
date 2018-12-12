package com.oldmadras.camel.processors;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.chatscript.ChatScriptMessage;

import com.oldmadras.annachikadai.integrations.TwilioUtils;

public class WhatsappProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		final ChatScriptMessage body = exchange.getIn().getBody(ChatScriptMessage.class);
		TwilioUtils.sendmessage(body.getReply());
		exchange.getOut().setBody(body);
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 202);
	}

}