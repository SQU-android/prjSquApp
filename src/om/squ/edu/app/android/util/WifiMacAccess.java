/**
 * 
 */
package om.squ.edu.app.android.util;


import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * @author Bhabesh
 *
 */
public class WifiMacAccess 
{
	String 		macAddress;
	Context		mContext;
	public WifiMacAccess(Context mContext)
	{
		this.mContext	=	mContext;
	}
	
	public String getMac()
	{
		WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

		if(wifiManager.isWifiEnabled()) {
		    // WIFI ALREADY ENABLED. GRAB THE MAC ADDRESS HERE
		    WifiInfo info = wifiManager.getConnectionInfo();
		    macAddress = info.getMacAddress();
		} else {
		    // ENABLE THE WIFI FIRST
		    wifiManager.setWifiEnabled(true);

		    // WIFI IS NOW ENABLED. GRAB THE MAC ADDRESS HERE
		    WifiInfo info = wifiManager.getConnectionInfo();
		    macAddress = info.getMacAddress();
		    //wifiManager.setWifiEnabled(false);
		}
		
		macAddress	=	macAddress.replace(":", "");
		
		return macAddress;
	}
	
}
