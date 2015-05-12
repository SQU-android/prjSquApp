/**
 * 
 */
package om.squ.edu.app.android.payment.service.rest;

import om.squ.edu.app.android.R;
import om.squ.edu.app.android.payment.bo.Payment;
import om.squ.edu.app.android.payment.bo.PaymentSummary;
import om.squ.edu.app.android.util.HttpUtils;
import om.squ.edu.app.android.util.ServiceUtil;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.widget.TextView;


/**
 * @author Bhabesh
 *
 */
public class PaymentDetailRestService extends AsyncTask<String, Void, Payment>
{
	private GridLayout	gridLayout;
	private Context		context;
	private	Resources	resources;
	
	public PaymentDetailRestService( GridLayout gridLayout, Context context, Resources 	resources)
	{
		this.gridLayout	=	gridLayout;
		this.context	=	context;
		this.resources	=	resources;
	}

	@Override
	protected Payment doInBackground(String... params) {
		
		String urlRest	=	params[0];
		
		RestTemplate 	restTemplate	=	new RestTemplate();
		
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(HttpUtils.getNewHttpClient()));				//By pass self signed certificate 
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		Payment payment	=	restTemplate.getForObject(urlRest,Payment.class);
		return payment;
	}
	
	   @Override
	   protected void onPostExecute(Payment payment)
	   {
		   //Log.e("Payment Detail : ", payment.toString());
		   
		   gridLayout.setColumnCount(3);
		   gridLayout.setRowCount(payment.getLstPaymentSummary().size());
		   
		   gridLayout.addView(new ServiceUtil(context, resources.getString(R.string.payment_dt_txt), "#000000").getTextView());
		   gridLayout.addView(new ServiceUtil(context, resources.getString(R.string.payment_amt_txt), "#000000").getTextView());
		   gridLayout.addView(new ServiceUtil(context, resources.getString(R.string.payment_desc_txt), "#000000").getTextView());
		   
		   for(PaymentSummary paymentSummary: payment.getLstPaymentSummary())
		   {
			   gridLayout.addView(new ServiceUtil(context, paymentSummary.getPaymentDt(), "#000000").getTextView());
			   TextView	tvAmount = new ServiceUtil(context, String.valueOf(paymentSummary.getPayAmount()), "#FF3333").getTextView();
			   tvAmount.setGravity(Gravity.RIGHT);
			   gridLayout.addView(tvAmount);
			   gridLayout.addView(new ServiceUtil(context, paymentSummary.getPaymentDesc(), "#3333FF").getTextView());
		   }
		   
		   
		   
	   }

}
