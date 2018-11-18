package com.oldmadras.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.oldmadras.annachikadai.integrations.TwilioUtils;
import com.oldmadras.camel.processors.CallBackProcessor;
import com.oldmadras.camel.processors.ChatHandler;
import com.oldmadras.camel.processors.WhatsappProcessor;

@Component
public class CamelRoutes extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:firstRoute")
			.log("Camel sssbody: ${body}");
		from ("direct:callback")
		  .process(new CallBackProcessor());
		 
	from ("direct:chat")
      .to("ChatScript://localhost:1024/Annachi")
  //    .process(new CallBackProcessor());
      .log("Chat reply: ${body}")
      .process(new WhatsappProcessor());
	 
	from ("direct:reset")
     .to("ChatScript://localhost:1024/Annachi?resetchat=true")
 //    .process(new CallBackProcessor());
     ;//  .process(new WhatsappProcessor());
	  
    
	 }

}
