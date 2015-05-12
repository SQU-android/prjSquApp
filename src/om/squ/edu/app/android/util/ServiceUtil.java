/**
 * 
 */
package om.squ.edu.app.android.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
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
	
	/* For TextView*/
	
	private	Context	context;
	private	String	strText;
	private	String	strColor;
	


	public ServiceUtil(Resources	resources, String mode) {
		this.resources 	= 	resources;
		this.mode		=	mode;	
		this.server		=	getServer();
	}

	/* For TextView*/
	public ServiceUtil(Context context, String strText, String strColor )
	{
		this.context	=	context;
		this.strText	=	strText;
		this.strColor	=	strColor;
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
	
	/* For TextView*/
	 public  TextView getTextView()
	   {
		   TextView textView				= 	new TextView(context);
		   textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		   textView.setPadding(5, 5, 5, 5);
		   textView.setTextColor(Color.parseColor(strColor));
		   textView.setText(strText);
		   return textView;
	   }
	
}
