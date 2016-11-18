package sap.educational.IoT.embedded;

import java.io.IOException;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.system.SystemInfo;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;

public class TemperatureMeasure {


	//GpioController gpio = GpioFactory.getInstance();

	public float getCPUTempeture() throws NumberFormatException, IOException, InterruptedException {
		return SystemInfo.getCpuTemperature();
	}

	public void readTempFromGPIO() {
		 if (Gpio.wiringPiSetup() == -1) {
		        System.out.println(" ==>> GPIO SETUP FAILED");
		        return;
		 }
		 
		GpioUtil.export(3, GpioUtil.DIRECTION_OUT);

		Gpio.pinMode(3, Gpio.OUTPUT);
		Gpio.digitalWrite(3, Gpio.LOW);
		Gpio.delay(18);

		Gpio.digitalWrite(3, Gpio.HIGH);
		Gpio.pinMode(3, Gpio.INPUT);

		Gpio.digitalRead(3);
	}

}
