package br.unisc.psd;


import java.io.IOException;
import java.io.InputStream;
import java.util.TooManyListenersException;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.math.BigInteger;
import java.util.List;

/**
 * @author rafael.viana
 */
public class SerialPortReader implements Runnable, SerialPortEventListener {
	
    private static final int BAUDRATE = 1200;
    private static final int TIMEOUT = 2000;
    private static final int MAX_BYTES = 1; 
	
    private InputStream inputStream;
    private SerialPort serialPort;
    private Thread readThread;
    
    private MonitorLinhaDeProcesso monitor;

    public SerialPortReader(CommPortIdentifier portId, MonitorLinhaDeProcesso monitor) {
        
        this.monitor = monitor;
        
        try {
            serialPort = (SerialPort) portId.open("LeitorSerial", TIMEOUT);
        } catch (PortInUseException e) {
        	throw new SerialPortException("Porta já está em uso");
        }
        
        try {
            inputStream = serialPort.getInputStream();
        } catch (IOException e) {
        	throw new SerialPortException("Erro ao abrir input stream da porta serial");
        }
        
        try {
            serialPort.addEventListener(this);
        } catch (TooManyListenersException e) {
        	throw new SerialPortException("Já existem muitos listeners registrados nesta porta serial");
        }
        
        serialPort.notifyOnDataAvailable(true);
        
        try {
            serialPort.setSerialPortParams(BAUDRATE,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {
        	throw new SerialPortException("Operação de comunicação não suportada");
        }
        
        readThread = new Thread(this);
        readThread.start();
    }

    public void run() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
        	throw new SerialPortException("Erro ao interromper Thread");
        }
    }

    public void serialEvent(SerialPortEvent event) {
        switch(event.getEventType()) {
	        case SerialPortEvent.BI:
	        case SerialPortEvent.OE:
	        case SerialPortEvent.FE:
	        case SerialPortEvent.PE:
	        case SerialPortEvent.CD:
	        case SerialPortEvent.CTS:
	        case SerialPortEvent.DSR:
	        case SerialPortEvent.RI:
	        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
	                   //System.out.println("caiu aqui...");
                    break;
	            
	        case SerialPortEvent.DATA_AVAILABLE:
	            byte[] readBuffer = new byte[MAX_BYTES];
                    int numBytes = 0;
                    
	            try {
	                while (inputStream.available() > 0) {
	                   numBytes  = inputStream.read(readBuffer);
                        }
                        String command = SerialCommand.setCommand(org.apache.commons.codec.binary.Hex.encodeHexString(readBuffer).toString().toUpperCase());
                        if(command != null){
                            monitor.registerEvent(command);
                        }
	            } catch (IOException e) {
	            	throw new SerialPortException("Erro ao ler buffer entrada");
	            }
	            break;
        }
    }
    
    private String byteArrayToHex(byte[] a) {
       StringBuilder sb = new StringBuilder();
       for(int i = 0; i < 2; i++)
       {
           byte b = a[i];
           sb.append(String.format("%02x", b&0xff));
       }
       return sb.toString();
    }
    
    public void close() {
        serialPort.close();
    }
    
}