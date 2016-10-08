package com.park.tools;

import java.util.HashMap;

import com.park.coclient.ConClientThread;
import com.park.conserver.ConServerThread;

public class ThreadManage {
	
	public static HashMap<String, ConServerThread> ServerThread = new HashMap<>();

	public static HashMap<String, ConClientThread> ClientThread = new HashMap<>();
}
