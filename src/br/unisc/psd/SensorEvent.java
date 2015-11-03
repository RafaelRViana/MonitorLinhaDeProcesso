package br.unisc.psd;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author rafael.viana
 */
public class SensorEvent {

	private final String SENSOR_A = "0x0A";
	private final String SENSOR_B = "0x0B";
	private final String SENSOR_C = "0x0C";
	private final String SENSOR_RESET = "0x0F";
	
	private String sensor;
	
	private String date;
	
	public SensorEvent(String sensor) {
            this.sensor = sensor;
            //System.out.print(sensor);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");   
            this.date = formato.format(new Date());
	}
	
	public String getSensor() {
		return sensor;
	}
	
	public String getDate() {
		return date;
	}
	
        public boolean isSensorA() {
            return SENSOR_A.toLowerCase().equals(sensor.toLowerCase());
        }
        
        public boolean isSensorB() {
            return SENSOR_B.toLowerCase().equals(sensor.toLowerCase());
        }
        
        public boolean isSensorC() {
            return SENSOR_C.toLowerCase().equals(sensor.toLowerCase());
        }
        
        public boolean isSensorReset() {
            return SENSOR_RESET.toLowerCase().equals(sensor.toLowerCase());
        }
        
	public String getSensorName() {	
		if(isSensorA()) {
			return "SENSOR A";
		} else if(isSensorB()) {
			return "SENSOR B";
		} else if(isSensorC()) {
			return "SENSOR C";
		} else if(isSensorReset()) {
			return "SENSOR RESET";
		}
		
		return "ENTRADA NAO RECONHECIDA";
	}
	
}