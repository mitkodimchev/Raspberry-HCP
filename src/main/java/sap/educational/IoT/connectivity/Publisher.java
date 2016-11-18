package sap.educational.IoT.Connectivity;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import sap.educational.IoT.Services.MessageCreator;

public class Publisher {
	
	private static final String TOPIC_NAME = "TOPIC_NAME";
	private MessageCreator messageCreator = new MessageCreator();
	
	public void publishTemperature(MqttClient client) {
        MqttTopic temperatureTopic = client.getTopic(TOPIC_NAME);
        String temperatureJson = messageCreator. createJson();
        try {    
        	MqttMessage message = new MqttMessage();
        	message.setPayload(temperatureJson.getBytes());
			temperatureTopic.publish(message);
			System.out.println("Published data. Topic: " + temperatureTopic.getName() + "  Message: " + temperatureJson);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
