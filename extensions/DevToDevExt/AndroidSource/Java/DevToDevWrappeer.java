package ${YYAndroidPackageName};

import android.util.Log;
import android.content.Context;
import android.os.Looper;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.String;
import java.lang.Number;
import java.util.HashMap;

import ${YYAndroidPackageName}.R;
import com.yoyogames.runner.RunnerJNILib;

import com.devtodev.core.DevToDev;
import com.devtodev.core.utils.log.LogLevel;
import com.devtodev.core.data.metrics.aggregated.progression.params.LocationEventParams;
//import com.devtodev.core.*;


public class DevToDevWrappeer
{
	public static final String APP_ID = "1a19fc77-77b9-0e93-b999-42aab62398bf";
	public static final String SECRET_KEY = "r9ZHw8tPv7mWIsA6fGDOcCS4khxd5QoY";
	
	public double staticInit()
	{
		// GameMaker wrong thread workadround
		Looper.prepare();
		
		// debug
		Log.i("yoyo", "DevToDev_staticInit");
		

		DevToDev.init(RunnerJNILib.GetApplicationContext(), APP_ID, SECRET_KEY);		
		DevToDev.setLogLevel(LogLevel.Verbose);
		
		DevToDev.startSession();

		return 0.0;
	}
	
	
	public double staticFinal()
	{
		Log.i("yoyo", "DevToDev_staticFinal");
		DevToDev.endSession();
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
	
	
	public double progressionEvent(
		String locationName,
		double spentTime,
		double spentDot,
		double spentBomb,
		double spentPaint,
		double spentTurns,
		double spentMoreTurns,
		double spentOpenBoxes,
		double spentEP,
		double earnedCoins)
	{
		LocationEventParams params = new LocationEventParams();
		
		params.setDifficulty(1);
		//params.setSource("Vilage step 02");
		DevToDev.startProgressionEvent(locationName, params);
		
		params.setSuccess(true);
		params.setDuration((int)spentTime);
		
		HashMap<String, Number> spent = new HashMap<String, Number>();
		spent.put("Dot", (int)spentDot);
		spent.put("Bomb", (int)spentBomb);
		spent.put("Paint", (int)spentPaint);
		spent.put("Turns", (int)spentTurns);
		spent.put("MoreTurns", (int)spentMoreTurns);
		spent.put("OpenBoxes", (int)spentOpenBoxes);
		spent.put("EP", (int)spentEP);
		params.setSpent(spent);
		
		HashMap<String, Number> earned = new HashMap<String, Number>();
		earned.put("Coins", (int)earnedCoins);
		params.setEarned(earned);

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
		DevToDev.realPayment(paymentId, inAppPrice, inAppName, inAppCurrencyISOCode);	
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
}