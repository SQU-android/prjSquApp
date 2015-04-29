/**
 * 
 */
package om.squ.edu.app.android.util;

import android.content.res.Resources;
import om.squ.edu.app.android.R;

/**
 * @author Bhabesh
 *
 */
public class ServiceUtil 
{
	private Resources	resources;
	private	String 		mode;
	private String 		server;
	
	


	public ServiceUtil(Resources	resources, String mode) {
		this.resources 	= 	resources;
		this.mode		=	mode;	
		this.server		=	getServer();
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		if(mode.equals(Constants.CONST_MODE_DEV))
		{
			this.server = resources.getString(R.string.server_rest_dev);
		}
		
		if(mode.equals(Constants.CONST_MODE_TEST))
		{
			this.server = resources.getString(R.string.server_rest_test);
		}
		return this.server;
	}
	
	/**
	 * @return the urlMac
	 */
	public String getUrlMac() {
		
		String 	appBasic 		= 	resources.getString(R.string.app_rest_basic);
		String	serviceMacAdd	=	resources.getString(R.string.service_basic_macadd);
		
		String		urlMac			=	server+"/"+appBasic+"/"+serviceMacAdd;
		
		return urlMac;
	}

	/**
	 * 
	 * @return
	 */
	public String getUrlPayment()
	{
		String 	appPayment 		= 	resources.getString(R.string.app_rest_payment);
		String	servicePayment	=	resources.getString(R.string.service_payment_payment);
		String	urlPayment		=	server+"/"+appPayment+"/"+servicePayment;
		
		return urlPayment;
	}
	
}
