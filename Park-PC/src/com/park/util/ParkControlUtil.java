package com.park.util;

import com.park.data.ParkData;
import com.park.tools.ThreadManage;

/**
 * 车位控制类
 * @author ansore
 *
 */
public class ParkControlUtil {
	public static boolean lockSpace(int id) {
		if(ThreadManage.ClientThread.size()!=0) {
			ThreadManage.ClientThread.get(ParkData.HardWare).SenderMessages("control relay "+id+" 1");
			return true;
		}
		return false;
	} 
	
	public static boolean unlockSpace(int id) {
		if(ThreadManage.ClientThread.size()!=0) {
			ThreadManage.ClientThread.get(ParkData.HardWare).SenderMessages("control relay "+id+" 0");
			return true;
		}
		return false;
	}
	
	public static boolean onLED(int id) {
		if(ThreadManage.ClientThread.size()!=0) {
			ThreadManage.ClientThread.get(ParkData.HardWare).SenderMessages("control led "+id+" 1");
			return true;
		}
		return false;
	}
	
	public static boolean offLED(int id) {
		if(ThreadManage.ClientThread.size()!=0) {
			ThreadManage.ClientThread.get(ParkData.HardWare).SenderMessages("control led "+id+" 0");
			return true;
		}
		return false;
	}
}
