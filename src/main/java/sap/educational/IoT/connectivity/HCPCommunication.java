package sap.educational.IoT.Connectivity;

import java.net.URISyntaxException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.fusesource.mqtt.client.MQTT;

import sap.educational.IoT.Services.MacUtils;

public class HCPCommunication {
	private static final String HCP_ACCOUNT_ID = "i328417";
	private static final String HCP_LANDSCAPE_HOST = "trial.hanatrial.ondemand.com";
	private static final String BROKER_URL = new String(
			"wss://iotmms" + HCP_ACCOUNT_ID + HCP_LANDSCAPE_HOST + "/com.sap.iotservices.mms/v1/api/ws/mqtt");
	private static final String TOPIC_NAME = "TOPIC_NAME";
	private static final String USERNAME = "469f6fe1-ec5a-4110-84e3-31147001f392";
	private static final char[] PASSWORD = "c75c2a1a26fd423723bc2f8c654091".toCharArray();
	private MqttClient client;
	private Publisher publisher = new Publisher();

	public HCPCommunication() {
		String clientId = MacUtils.getMacAddress() + "-pub";
		try {
			client = new MqttClient(BROKER_URL, clientId);
			System.out.println(BROKER_URL);
		} catch (MqttException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void startService() {

		try {
			MqttConnectOptions options = new MqttConnectOptions();
			options.setCleanSession(false);
			options.setUserName(USERNAME);
			options.setPassword(PASSWORD);
			options.setWill(client.getTopic(TOPIC_NAME), "Services has stopped".getBytes(), 0, false);
			options.setKeepAliveInterval(30);
			options.setConnectionTimeout(300);
			System.out.println(client.isConnected());
			client.connect(options);
			while (true) {
				try {
					publisher.publishTemperature(client);
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} catch (MqttException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
