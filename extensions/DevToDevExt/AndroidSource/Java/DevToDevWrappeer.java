package ${YYAndroidPackageName};

import android.util.Log;
import android.content.Context;
import android.annotation.TargetApi;
import android.app.Application;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.String;
import java.lang.Number;
import java.util.HashMap;
import java.util.Map;

import ${YYAndroidPackageName}.R;
import com.yoyogames.runner.RunnerJNILib;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Dialog;
import android.view.MotionEvent;
import org.json.JSONObject;
import org.json.JSONException;

import com.devtodev.core.DevToDev;
import com.devtodev.core.utils.log.LogLevel;
import com.devtodev.core.data.metrics.aggregated.progression.params.LocationEventParams;
import com.devtodev.core.data.consts.Gender;
import com.devtodev.cheat.DevToDevCheat;
import com.devtodev.cheat.listener.OnVerifyListener;
import com.devtodev.cheat.consts.VerifyStatus;
//import com.devtodev.core.*;


public class DevToDevWrappeer implements IExtensionBase, OnVerifyListener
{
	public static final String APP_ID = "4026afff-7522-03d8-868c-f62c41723d46";
	public static final String SECRET_KEY = "HP6cxMTsqXDoOJAQCSL38hmrbkwiFyNz";
	
	String _iap_paymentId;
	double _iap_inAppPrice;
	String _iap_inAppName;
	String _iap_inAppCurrencyISOCode;
	
	Map<String, String> _tokenMap;
	
	public double staticInit()
	{
		// GameMaker wrong thread workadround
		Looper.prepare();
		
		// debug
		Log.i("yoyo", "DevToDev_staticInit");
		
		DevToDev.setLogLevel(LogLevel.Assert);
		Context appContext = RunnerJNILib.GetApplicationContext();
		DevToDev.init(appContext, APP_ID, SECRET_KEY);		
				
		DevToDev.startSession();

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH)
		{
			((Application)appContext).registerActivityLifecycleCallbacks(new CustomLifecycleCallback());
		}
	
		_tokenMap = new HashMap<String, String>();
		
        return 0.0;
    }
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public class CustomLifecycleCallback implements Application.ActivityLifecycleCallbacks
	{   
		@Override	
		public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}
		
		@Override
		public void onActivityDestroyed(Activity activity) {}
		
		@Override
		public void onActivityPaused(Activity activity) {}
		
		@Override
		public void onActivityResumed(Activity activity) {}
		
		@Override
		public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}
		
		@Override
		public void onActivityStarted(Activity activity) {}

        @Override
        public void onActivityStopped(Activity activity)
		{
			Log.i("yoyo", "DevToDev.endSession");
            DevToDev.endSession();
        }
    }
	
	
	public double staticFinal()
	{
		Log.i("yoyo", "DevToDev_staticFinal");
		//DevToDev.endSession();
		return 0.0;
	}


	public double customEvent(String arg0)
	{
		DevToDev.customEvent(arg0);
		//Log.i("yoyo", arg0);
		
		// FIXME
		//DevToDev.sendBufferedEvents();
		
		return 0.0;
	}


	public double levelUp(double level)
	{
		DevToDev.levelUp((int)level);
		return 0.0;
	}
	
	public double tutorialCompleted(double tutorialStep)
	{
		DevToDev.tutorialCompleted((int)tutorialStep);

		return 0.0;
	}
	
	
	public double progressionEvent(
		String locationName,
		double spentTime,
		double spentDot,
		double spentBomb,
		double spentPaint,
		double spentTurns,
		double spentMoreTurns,
		double spentOpenBoxes,
		double success,
		double difficulty,
		double playHour,
		double coinsSpentProgress,
		double coinsSpentFinal,
		double coinsBalance)
	{
		LocationEventParams params = new LocationEventParams();
		
		params.setDifficulty((int)difficulty);
		//params.setSource("Vilage step 02");
		DevToDev.startProgressionEvent(locationName, params);
		
		params.setSuccess(success > 0.5);
		params.setDuration((int)spentTime);
		
		HashMap<String, Number> spent = new HashMap<String, Number>();
		spent.put("Dot", (int)spentDot);
		spent.put("Bomb", (int)spentBomb);
		spent.put("Paint", (int)spentPaint);
		spent.put("Turns", (int)spentTurns);
		spent.put("MoreTurns", (int)spentMoreTurns);
		spent.put("OpenBoxes", (int)spentOpenBoxes);
		spent.put("playHour", (int)playHour);

		spent.put("Coins trial", (int)coinsSpentProgress);
		spent.put("Coins balance", (int)coinsBalance);
		
		if (success > 0.5)
		{
			spent.put("Coins spent final", (int)coinsSpentFinal);
		}
		
		
		params.setSpent(spent);
		
		/*
		HashMap<String, Number> earned = new HashMap<String, Number>();
		earned.put("Coins", (int)earnedCoins);
		params.setEarned(earned);
		*/

		DevToDev.endProgressionEvent(locationName, params);
		return 0.0;
	}
	
	public double inAppPurchase(
		String purchaseId,
		String purchaseType,
		double purchaseAmount,
		double purchasePrice,
		String purchaseCurrency)
	{
		Log.i("yoyo", "inAppPurchase");
		DevToDev.inAppPurchase(purchaseId, purchaseType, (int)purchaseAmount, (int)purchasePrice, purchaseCurrency);
		return 0.0;
	}
	
	public double realPayment(
		String paymentId,
		double inAppPrice,
		String inAppName,
		String inAppCurrencyISOCode)
	{
		DevToDev.realPayment(paymentId, (float)inAppPrice, inAppName, inAppCurrencyISOCode);	
		return 0.0;
	}
	
	public double verifyPayment(
		String token,
		double inAppPrice,
		String inAppName,
		String inAppCurrencyISOCode,
		String receipt,
		String signature,
		String publicKey)
	{
		_iap_paymentId = token;
		_iap_inAppPrice = inAppPrice;
		_iap_inAppName = inAppName;
		_iap_inAppCurrencyISOCode = inAppCurrencyISOCode;
	
		String wwSign = getSignatureWithToken(token);
		DevToDevCheat.verifyPayment(token, wwSign, publicKey, this);
		return 0.0;
	}
	
	public void onVerify(VerifyStatus status)
	{
		if (status == VerifyStatus.Valid)
		{
			realPayment(_iap_paymentId, _iap_inAppPrice, _iap_inAppName, _iap_inAppCurrencyISOCode);
		}
	}

	public double setUserInfo(
		String gender,
		String name,
		double ageRange,
		double id,
		String locale,
		String email,
		double moneySpent)
	{
		if (gender.equals("male"))
		{
			DevToDev.getActivePlayer().setGender(Gender.Male);	
		}
		else
		{
			DevToDev.getActivePlayer().setGender(Gender.Female);
		}

		DevToDev.getActivePlayer().setUserData("name", name);
		DevToDev.getActivePlayer().setUserData("ageRange", (Number)ageRange);
		DevToDev.getActivePlayer().setUserData("id", (Number)id);
		DevToDev.getActivePlayer().setUserData("locale", locale);

		DevToDev.getActivePlayer().setEmail(email);
		
		DevToDev.getActivePlayer().setUserData("moneySpent", (Number)moneySpent);

		return 0.0;
	}
	
	// ----------------------------------------------------------------------------
	
	
	public double AddTwoNumbers(double arg0, double arg1)
	{
		double value = arg0 + arg1;
		Log.i("yoyo", arg0 + "+" + arg1 + " = " + value);
		return value;
	}


	public String BuildAString(String arg0, String arg1)
	{
		String myString = arg0 + " " + arg1;
		Log.i("yoyo", myString);
		return myString;
	}

	
	public String HowManyObjects(double arg0, double arg1, String arg2)
	{
		double value = arg0 + arg1;
		Log.i("yoyo", arg0 + "+" + arg1 + " = " + value);
		String myString = String.valueOf(value) + " " + arg2;
		Log.i("yoyo", myString);
		return myString;
	}

	
	private static final int EVENT_OTHER_SOCIAL = 70;
	public void ReturnAsync(double arg0, double arg1)
	{
		int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
		RunnerJNILib.DsMapAddString( dsMapIndex, "type", "finished" );
		RunnerJNILib.DsMapAddDouble( dsMapIndex, "argument0", arg0);
		RunnerJNILib.DsMapAddDouble( dsMapIndex, "argument1", arg1);
		RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
	}
	
	public String getSignatureWithToken(String arg0)
	{
		if (_tokenMap.containsKey(arg0))
		{
			return _tokenMap.get(arg0);
		}
		
		if (_tokenMap.size() > 0)
		{
			return new String("token not found");
		}
		
		return new String("Signature map is empty :( FAIL");
	}
	
	// implements IExtensionBase -----------------------------------------------

	public void onStart(){};
	public void onRestart(){};
	public void onStop(){};
	public void onDestroy(){};
	public void onPause(){};
	public void onResume(){};
	public void onConfigurationChanged(Configuration newConfig){};
	
	public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {};
	
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		Log.i("yoyo_ww", "Got activity request code: " + requestCode);
		Log.i("yoyo_ww", "Got activity result: " + resultCode);
		
		if (requestCode == 1001)
		{
			Log.i("yoyo_ww", "Getting extras");
			
			int responseCode = data.getIntExtra("RESPONSE_CODE", 0);
			Log.i("yoyo_ww", "Got responseCode: " + responseCode);
			
			String purchaseData = data.getStringExtra("INAPP_PURCHASE_DATA");
			Log.i("yoyo_ww", "Got purchaseData: " + purchaseData);
			
			String dataSignature = data.getStringExtra("INAPP_DATA_SIGNATURE");//this is the signature which you want
			Log.i("yoyo_ww", "Got dataSignature: " + dataSignature);

			if (resultCode == Activity.RESULT_OK)
			{
				try
				{
					Log.i("yoyo_ww", "Getting JSON");
					
					JSONObject jo = new JSONObject(purchaseData);
					String sku = jo.getString("productId");
					// alert("You have bought the " + sku + ". Excellent choice, adventurer!");
					Log.i("yoyo_ww", "Got productId: " + sku);
					
					String token = jo.getString("purchaseToken");
					Log.i("yoyo_ww", "Got token: " + token);
					
					Log.i("yoyo_ww", "Puttin data to hashMap");
					_tokenMap.put(token, dataSignature);
					Log.i("yoyo_ww", "SUCCESS");
					
					// DEBUG
					//alert("INAPP_DATA_SIGNATURE = [" + dataSignature + "]");
				}
				catch (JSONException e)
				{
					Log.i("yoyo_ww", "Getting JSON EXCEPTION");
					
					//alert("Failed to parse purchase data.");
					e.printStackTrace();
				}
			}
		}
	};
	
	public boolean onKeyLongPress(int keyCode, KeyEvent event)
	{
		return false;
	}
	public void onWindowFocusChanged(boolean hasFocus)
	{
	}
	
	public boolean onCreateOptionsMenu( Menu menu )
	{
		return false;
	}
	public boolean onOptionsItemSelected( MenuItem item )
	{
		return false;
	}
	
	public boolean onKeyDown( int keyCode, KeyEvent event )
	{
		return false;
	}
	public boolean onKeyUp( int keyCode, KeyEvent event )
	{
		return false;
	}
	
	public Dialog onCreateDialog(int id)
	{
		return null;
	}
	
	public boolean onTouchEvent(final MotionEvent event)	{ return false;};
	public boolean onGenericMotionEvent(MotionEvent event)	{ return false;};
	
	public boolean dispatchGenericMotionEvent(MotionEvent event)	{ return false;};
	public boolean dispatchKeyEvent(KeyEvent event)				{ return false;};
}