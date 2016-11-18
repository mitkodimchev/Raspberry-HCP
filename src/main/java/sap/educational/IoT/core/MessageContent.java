package sap.educational.IoT.core;

import java.util.Date;

public class MessageContent {
	private float temp;
	private String timestamp;

	public MessageContent(float tempValue, String timestamp) {
		this.temp = tempValue;
		this.timestamp = timestamp;
	}
}
