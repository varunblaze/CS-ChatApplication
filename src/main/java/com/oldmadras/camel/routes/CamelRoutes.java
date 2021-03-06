package com.oldmadras.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.oldmadras.camel.processors.*;
@Component
public class CamelRoutes extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:firstRoute")
			.log("Camel sssbody: ${body}");
		from ("direct:callback")
		 .to("chatscript://localhost:1024/Annachi")
	      .log("Chat reply: ${body}")
		  .process(new WhatsappProcessor());
	from ("direct:chat")
      .to("chatscript://localhost:1024/Harry")
      .log("Chat reply: ${body}")
      .process(new CallBackProcessor());
//	  .process(new WhatsappProcessor());
	 
	from ("direct:reset")
     .to("chatscript://localhost:1024/Annachi")
 //    .process(new CallBackProcessor());
     ;//  .process(new WhatsappProcessor());
	  
    
	 }

}
