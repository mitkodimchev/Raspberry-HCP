package sap.educational.IoT.Services;

import java.io.IOException;

import com.google.gson.Gson;

import sap.educational.IoT.Connectivity.HCPCommunication;
import sap.educational.IoT.core.Message;
import sap.educational.IoT.embedded.TemperatureMeasure;

public class MessageCreator {
	private String deviceId = "469f6fe1-ec5a-4110-84e3-31147001f392";

	private TemperatureMeasure temperature = new TemperatureMeasure();
	public String createJson() {
		Message message = new Message("async", "9575b4fa8b5f78800feb");
		try {
			if(false)
				message.addMessageContent(temperature.getCPUTempeture());
			message.addMessageContent(22);
			Gson gson = new Gson();
			StringBuilder jsonString = new StringBuilder("PUBLISH iot/data/"+ deviceId +" ");
			String jsonMessage = gson.toJson(message);
			jsonString.append(jsonMessage);
			return jsonString.toString();
		} catch (NumberFormatException | IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(new MessageCreator().createJson());
		new HCPCommunication().startService();
	}

}
