package com.oldmadras.annachikadai.integrations;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
public class TwilioUtils {
	@Value("${TWILIO_ACCOUNT_SID}")
	public static  String ACCOUNT_SID;
	@Value("${TWILIO_AUTH_TOKEN}")
	  public static  String AUTH_TOKEN;
	@Value("${TWILIO_SOURCE_NUMBER}")
	  public static  String SOURCE_NUMBER="+";
	@Value("${TWILIO_TARGET_NUMBER}")
	  public static  String TARGET_NUMBER="+";
	  private static boolean init=false;
	
	private static void initapi()
	{
		  Twilio.init("", "");
		  
	}
	  
	  public static void sendmessage(String from, String to, String message)
	  {
		  if (!init)
		  {
			  initapi();
			  init=true;
		  }
		    
		  Message message1 = Message.creator(
	                new com.twilio.type.PhoneNumber(Optional.ofNullable(from).orElse("whatsapp:" + SOURCE_NUMBER) ),
	                new com.twilio.type.PhoneNumber("whatsapp:" +Optional.ofNullable(to).orElse(TARGET_NUMBER)),
	                message)
	            .create();
	  }

}
