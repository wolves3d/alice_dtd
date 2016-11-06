package ${YYAndroidPackageName};

import android.util.Log;
import android.content.Context;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.String;

import ${YYAndroidPackageName}.R;
import com.yoyogames.runner.RunnerJNILib;

import com.devtodev.core.DevToDev;
import com.devtodev.core.utils.log.LogLevel;
//import com.devtodev.core.*;


public class DevToDevWrappeer
{
	public static final String APP_ID = "1a19fc77-77b9-0e93-b999-42aab62398bf";
	public static final String SECRET_KEY = "r9ZHw8tPv7mWIsA6fGDOcCS4khxd5QoY";
	
	public double staticInit()
	{
		// debug
		Log.i("yoyo", "DevToDev_staticInit");
		DevToDev.setLogLevel(LogLevel.Verbose);

		DevToDev.init(RunnerJNILib.GetApplicationContext(), APP_ID, SECRET_KEY);		
		DevToDev.startSession();

		return 0.0;
	}
	
	
	public double staticFinal()
	{
		Log.i("yoyo", "DevToDev_staticFinal");
		endSession();
		return 0.0;
	}


	public double customEvent(String arg0)
	{
		DevToDev.customEvent(arg0);
		//Log.i("yoyo", arg0);
		
		// FIXME
		//endSession();
		//DevToDev.sendBufferedEvents();
		
		return 0.0;
	}

	

	public double levelUp(double level)
	{
		DevToDev.levelUp(level);
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