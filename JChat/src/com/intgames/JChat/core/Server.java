package com.intgames.JChat.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.intgames.JChat.GUI.MainGUI;
import com.intgames.JChat.GUI.ServerLogGUI;
import com.intgames.JChat.resources.Message;
import com.intgames.JChat.resources.MessageOutputStream;
import com.intgames.JChat.runnables.ServerAccepterThread;

public class Server {

	
	/**
	 * @author Eugene Hong
	 * 
	 * 
	 * 
	 */
	
	
	private String servername;
	private ServerSocket server;
	private ServerAccepterThread sa;
	private List<MessageOutputStream> bw = new LinkedList<>();
	private ServerLogGUI log;
	
	public MainGUI mg;
	
	public Server(String servername, String path) {
		this.servername = servername;
		this.log = new ServerLogGUI(this.servername, this, path);
		mg = new MainGUI(this.log); 
		/* 
	 	Since different server instance uses different ServerLogGUI instance,
	 	give ServerLogGUI instance of server to 
		*/
		 
	}

	public void setnetwork(int port) {
		
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			mg.error("ServerSocket ���� ����!", "ServerSocket ������ �����߽��ϴ�!\n" + e.getMessage());
		}
		
		this.sa = new ServerAccepterThread(server, this);
		this.sa.start();
		
	}
	

	public void putObjectOutputStream(MessageOutputStream oo) {
		// TODO Auto-generated method stub
		this.bw.add(oo);
		
	}
	
	public void sendEveryone(Message msg, double ping) {
		
		log.println(msg, ping);
		
		Iterator<MessageOutputStream> it = bw.iterator();
		
		while(it.hasNext()) {
			
			try {
				MessageOutputStream oo = it.next();
				oo.writeMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				mg.error("���� ���� ����", "Ŭ���̾�Ʈ���� �޽����� ������ �� �����ϴ�.\n" + e.getMessage());
			}
			
		}
		
		
	}
	
	public void shutdown(int status) {
		
		log.serverstatus(status);
		
		this.sa.kill();
		
		Iterator<MessageOutputStream> it = bw.iterator();
		
		while(it.hasNext()) {
			
			try {
				MessageOutputStream br = it.next();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				mg.error("������ ���� �� �����ϴ�!",e.getMessage());
			}
			
		}
		
	}
	
}





