package om.squ.edu.app.android;

import om.squ.edu.app.android.basic.MacService;
import om.squ.edu.app.android.payment.service.PaymentService;
import om.squ.edu.app.android.util.Constants;
import om.squ.edu.app.android.util.WifiMacAccess;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	 // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREFER_NAME = "squAppEmpPrefer";
    private	SharedPreferences	prefUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String 		macAddress		=	null;
		String 		userId			=	null;
		Resources	resources		=	getResources();
		
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
			
			try
			{
				WifiMacAccess macAccess	=	new WifiMacAccess(this);
				if(resources.getString(R.string.mode_deploy).equals(Constants.CONST_MODE_DEV))
				{
					/* TODO : Mac address of user - for testing in virtual*/
					macAddress	=	"3423BA4C0127";
				}
				else
				{
					macAddress = macAccess.getMac();
				}
				
				TextView txtViewMac = (TextView)findViewById(R.id.txtViewMacAdd);
				//txtViewMac.setText(macAddress); 
				
				
				prefUser = getApplicationContext().getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
				
				
				
				TextView textViewName 	= 	(TextView)findViewById(R.id.txtName);
				TextView textViewId		=	(TextView)findViewById(R.id.txtId);
				TextView textLoadingMac	=	(TextView)findViewById(R.id.txtLoadingMac);

				MacService macRestService = new MacService(macAddress,textViewName, textViewId, textLoadingMac, prefUser, resources);
				userId = prefUser.getString("userId", null);
				
			}
			catch(Exception ex)
			{
				System.out.println("error : "+ex.getMessage());
				Toast.makeText(getApplicationContext(), "Error : Check SQU Wifi Connection. Details : "+ex.getMessage(), Toast.LENGTH_LONG).show();
			}
			

			
			//Log.e(" User 4 :",(String)textViewId.getText());
			
			
			
			
			
			

			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 
	 */
	public void clickPayment(View view)
	{
		//ImageButton	imgBttnPayment = (ImageButton)findViewById(R.id.imgBttnPayment);
		Intent intentPayment = new Intent(getApplicationContext(),PaymentService.class);
		startActivity(intentPayment);
	}
	
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	

}
