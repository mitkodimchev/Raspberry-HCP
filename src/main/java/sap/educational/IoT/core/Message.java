package sap.educational.IoT.core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Message {
	private String mode;
	private String messageType;
	private ArrayList<MessageContent> messages;

	public Message(String mode, String messageType) {
		this.mode = mode;
		this.messageType = messageType;
		this.messages = new ArrayList<MessageContent>();
	}

	public boolean addMessageContent(float tempValue) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date date = new Date();
		String timestamp = dateFormat.format(date);
		MessageContent messageContent = new MessageContent(tempValue, timestamp);
		this.messages.add(messageContent);
		return true;
	}

}
