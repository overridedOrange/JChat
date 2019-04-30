package com.intgames.JChat.core;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.intgames.JChat.GUI.MainGUI;

public class Client  {

	private Socket sock;
	
	public MainGUI mg;
	
	public Client() {
		
		mg = new MainGUI(this);
		
	}
	
	public void setnetwork(String ip, int port) {
		// TODO Auto-generated method stub
			
			try {
				sock = new Socket(ip, port);
			} catch (UnknownHostException e) {
				mg.error("UnknownHostException","ȣ��Ʈ�� �� �� �����ϴ�!\n" + e.getMessage());
			
			} catch (IOException e) {
				mg.error("IOException","�������� ����� ���� ������ �����ϴ� ���� ������ �߻��߽��ϴ�!\n" + e.getMessage());
			}
			
//			try {
//				br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
//			} catch (IOException e) {
//				mg.error("InputStreamReader ���� �� ����","�������� �����͸� ���� �غ� �ϴ� ���� ������ �߻��߽��ϴ�!");
//			}
//			
//			try {
//				bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
//			} catch (IOException e) {
//				mg.error("getOutputStream ���� �� ����","������ �����͸� ���� �غ� �ϴ� ���� ������ �߻��߽��ϴ�!");
//			}
//			
			
			
			/*new Thread(() ->  {
				
				
				String msg = null;
				
				while(true) {
					
					try {
						while((msg = br.readLine()) != null) {
							
							MainGUI.clientgot(null,msg);
							
						}
					} catch (IOException e) {
						mg.error("������ ���� ����","�������� �����͸� �޴� ���� ������ �߻��߽��ϴ�!");
					}
					
					if (Launcher.clientstate.equals("ClientStopSend"))
						break;
					
				}
				
			}).start();*/

	}
}
