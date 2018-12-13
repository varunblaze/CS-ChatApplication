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
	  public static  String SOURCE_NUMBER="+919962528868";
	@Value("${TWILIO_TARGET_NUMBER}")
	  public static  String TARGET_NUMBER="+14155238886";
	  private static boolean init=false;
	
	private static void initapi()
	{
		  Twilio.init("ACd253e825b3a8af79c2df9fff889f4fa2", "e3b9c5c5353f15b2e49063fefc598795");
		  
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
