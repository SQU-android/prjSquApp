package om.squ.edu.app.android;

import om.squ.edu.app.android.basic.MacService;
import om.squ.edu.app.android.payment.PaymentService;
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
				macAddress = macAccess.getMac();
				
				TextView txtViewMac = (TextView)findViewById(R.id.txtViewMacAdd);
				txtViewMac.setText(macAddress); 
				
			}
			catch(Exception ex)
			{
				System.out.println("error : "+ex.getMessage());
			}
			
			prefUser = getApplicationContext().getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
			
			
			
			TextView textViewName 	= 	(TextView)findViewById(R.id.txtName);
			TextView textViewId		=	(TextView)findViewById(R.id.txtId);

			MacService macRestService = new MacService(macAddress,textViewName, textViewId, prefUser, resources);
			userId = prefUser.getString("userId", null);
			
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
