package com.oldmadras.camel.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.component.chatscript.ChatScriptMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oldmadras.annachikadai.integrations.TwilioCallBackMessage;

@RestController 
public class CamelController {
	
	@Autowired
	CamelContext camelContext;
	
	@Autowired
	ProducerTemplate producerTemplate;

	@PostMapping(value = "/callback", consumes="application/x-www-form-urlencoded; charset=utf-8", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<?> callback(final HttpServletRequest request, @ModelAttribute TwilioCallBackMessage requestBody) {
		System.out.println("In Call back " + requestBody.getBody());
		ChatScriptMessage m= new ChatScriptMessage(requestBody.getFrom(),"",requestBody.getBody());
		//-------------------------------------------------------
		//----------------------------------------------------requestExchrequestExchangerequestExchangerequestExchangerequestExchangeangerequestExchange
		final Exchange requestExchange = ExchangeBuilder.anExchange(camelContext).withBody(m).build();
		final Exchange responseExchange = producerTemplate.send("direct:chat", requestExchange);
		final ChatScriptMessage responseBody = responseExchange.getOut().getBody(ChatScriptMessage.class);
		final int responseCode = responseExchange.getOut().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class).intValue();
		return ResponseEntity.status(responseCode).body(responseBody);
	}
	
	@PostMapping(value = "/requestExchangerequestExchangechat", produces = "application/json", consumes = "application/json")
	public @ResponseBody String chat(final HttpServletRequest request, @RequestBody ChatScriptMessage requestBody) {
		
		final Exchange requestExchange = ExchangeBuilder.anExchange(camelContext).withBody(requestBody).build();
		final Exchange responseExchange = producerTemplate.send("direct:chat", requestExchange);
		final ChatScriptMessage responseBody = responseExchange.getOut().getBody(ChatScriptMessage.class);
		return responseBody.getReply();
	}
	@PostMapping(value = "/reset", produces = "application/json", consumes = "application/json")
	public @ResponseBody String resetchat(final HttpServletRequest request, @RequestBody ChatScriptMessage requestBody) {
		
		final Exchange requestExchange = ExchangeBuilder.anExchange(camelContext).withBody(requestBody).build();
		final Exchange responseExchange = producerTemplate.send("direct:reset", requestExchange);
		final ChatScriptMessage responseBody = responseExchange.getOut().getBody(ChatScriptMessage.class);
		return responseBody.getReply();
	}

}
