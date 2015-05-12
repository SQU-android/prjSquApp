/**
 * 
 */
package om.squ.edu.app.android.payment.service;

import om.squ.edu.app.android.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Bhabesh
 *
 */
public class PaymentFragmentDate extends ListFragment
{
	private String currYear;
	private ListSelectionListener mListener = null;
	
	public PaymentFragmentDate () {}

	
	@Override
	public void setArguments(Bundle args) {
		// TODO Auto-generated method stub
		super.setArguments(args);
		this.currYear = args.getString("paramYear");
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try
		{
			mListener = (ListSelectionListener) activity;
		}
		catch(Exception ex)
		{
			Log.e("Error : ", ex.getMessage());
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
				View 		view					=	inflater.inflate(R.layout.fragment_payment_main_date, container, false);
				Activity activity	=	getActivity();
				TextView	textViewPaymentYear		=	(TextView)activity.findViewById(R.id.textPaymentYear);
				Log.e("Current Year (2) ", currYear);
				
				//return view;
				
				return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    // do something with the data
		getListView().setItemChecked(position, true);
		mListener.onListSelection(position);
	  }
	
	@Override
	public void onActivityCreated(Bundle savedState)
	{
		super.onActivityCreated(savedState);
		String[] mTitleArray = getResources().getStringArray(R.array.Titles);
		
		
		
		
		setListAdapter(new ArrayAdapter<String>(getActivity(),R.layout.fragment_payment_main_date,mTitleArray));
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
	}
	
	@Override
	public void onResume() {
		super.onResume();	
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	public void onStop() {
		super.onStop();
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	
	
	public interface ListSelectionListener {
		public void onListSelection(int index);
	}
	
}
