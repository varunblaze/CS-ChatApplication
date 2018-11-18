package com.oldmadras.annachikadai.integrations;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
public class TwilioUtils {
	@Value("${TWILIO_ACCOUNT_SID}")
	public static  String ACCOUNT_SID;
	@Value("${TWILIO_AUTH_TOKEN}")
	  public static  String AUTH_TOKEN;
	@Value("${TWILIO_SOURCE_NUMBER}")
	  public static  String SOURCE_NUMBER="xxxxxx";
	@Value("${TWILIO_TARGET_NUMBER}")
	  public static  String TARGET_NUMBER="+xxxxx";
	  private static boolean init=false;
	
	private static void initapi()
	{
		  Twilio.init("xxxxx", "xx");
		  
	}
	  
	  public static void sendmessage(String message)
	  {
		  if (!init)
		  {
			  initapi();
			  init=true;
		  }
		    
		  Message message1 = Message.creator(
	                new com.twilio.type.PhoneNumber("whatsapp:" + SOURCE_NUMBER ),
	                new com.twilio.type.PhoneNumber("whatsapp:" +TARGET_NUMBER),
	                message)
	            .create();
	  }

}
