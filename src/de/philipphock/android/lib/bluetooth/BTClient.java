package de.philipphock.android.lib.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class BTClient implements Runnable {
	private Thread recvThread;
	private volatile boolean listening = true;
	private BluetoothSocket clientSocket;
	private final BluetoothDevice device;
	private final UUID uuid;
	private InputStream clientSocketInStream;
	private OutputStream clientSocketOutStream;

	private boolean isConnected = false;
	private BTClient.BTClientCallback clientcallback;

	private boolean insecure=false;
	
	public BTClient(BluetoothDevice device, String uuid, BTClientCallback clientCallback,boolean makeInsecureConnection) throws IOException {
		this.clientcallback = clientCallback;
		this.device = device;
		this.uuid = UUID.fromString(uuid);
		insecure=makeInsecureConnection;
		
	}

	// executes itself in another thread and listens
	public void startListeningForIncomingBytes() {
		recvThread = new Thread(this);
		recvThread.start();
	}

	/**
	 * 
	 * @return the name of the remote endpoint. An empty String on error.
	 * 
	 */
	public String getRemoteDeviceName(){
		if (clientSocket == null){
			return "";
		}
		if (clientSocket.getRemoteDevice() == null){
			return "";
		}
		return clientSocket.getRemoteDevice().getName();
	}
	
	public void run() {
		byte[] buffer = new byte[1024]; // buffer store for the stream
		BluetoothAdapter.getDefaultAdapter().cancelDiscovery();

		try {// cannot throw here of course (the method who called start will
				// already be somewhere else because of the thread
				// Connect the device through the socket. This will block
				// until it succeeds or throws an exception.

			if (insecure){
				clientSocket = device.createInsecureRfcommSocketToServiceRecord(uuid);
			}else{
				clientSocket = device.createRfcommSocketToServiceRecord(uuid);	
			}
			

			
			clientSocket.connect();
			
			clientSocketInStream = clientSocket.getInputStream();
			clientSocketOutStream = clientSocket.getOutputStream();
			
		} catch (IOException connectException) {
			// Unable to connect; close the socket and get out.
			connectException.printStackTrace();
			try {
				clientSocket.close();
				clientcallback.onConnection(false);
				isConnected=false;
			} catch (IOException closeException) {
				closeException.printStackTrace();
			}
			return;
		}
		clientcallback.onConnection(true);
		isConnected=true;
		// manage the connection
		int bytes = 0;
		while (listening) {
			try {
				// Read from the InputStream
				bytes = clientSocketInStream.read(buffer);
				if (bytes == -1) {
					// connection remotely closed
					listening = false;
					clientcallback.onConnection(false);
					isConnected=false;
					break;
				}
				
				clientcallback.onRecv(buffer,bytes);
			} catch (IOException e) {
				e.printStackTrace();
				clientcallback.onConnection(false);
				isConnected=false;
				break;
			}
		}
	}

	/**
	 * Will cancel an in-progress connection, and close the socket.
	 * 
	 * @throws IOException
	 */
	public void cancel() throws IOException {
		listening = false;
		clientSocket.close();

	}

	public boolean isConnected() {
		return isConnected;
	}
	
	public void send(byte[] b){
		try {
			clientSocketOutStream.write(b);
			clientSocketOutStream.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	
	public interface BTClientCallback{
		public void onConnection(boolean isConnected);
		public void onError(Exception e);
		public void onRecv(byte[] data,int length);
		
	}

	
	
}