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

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEvent;
//import com.adjust.sdk.LogLevel;


public class AdjustExt implements IExtensionBase
{	
	public void Init()
	{
		Log.i("yoyo", "Adjust Init");
		
		Context appContext = RunnerJNILib.GetApplicationContext();

		Log.i("yoyo", "Adjust VERBOSE log level");
		
		String appToken = "dfzdd7h4rlkw";
        String environment = AdjustConfig.ENVIRONMENT_PRODUCTION;
        AdjustConfig config = new AdjustConfig(appContext, appToken, environment);
		
		Log.i("yoyo", "Adjust VERBOSE log level 2");
		
		config.setLogLevel(com.adjust.sdk.LogLevel.VERBOSE);
		
		Log.i("yoyo", "Adjust VERBOSE log level 3");
		
        Adjust.onCreate(config);
		
		Log.i("yoyo", "Adjust VERBOSE log level 5");
		
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH)
		{
			Log.i("yoyo", "Adjust VERBOSE log level 6");
			//((Application)appContext).registerActivityLifecycleCallbacks(new CustomLifecycleCallback());
			Log.i("yoyo", "Adjust VERBOSE log level 7");
		}
	}
		
	public double staticInit()
	{
		// debug
		Log.i("yoyo", "Adjust staticInit");
		
		// GameMaker wrong thread workadround
		//Looper.prepare();
		
		// debug
		//Log.i("yoyo", "DevToDev_staticInit 2");
						
        return 0.0;
    }
	
	/*
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public class CustomLifecycleCallback implements Application.ActivityLifecycleCallbacks
	{   
		@Override	
		public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}
		
		@Override
		public void onActivityDestroyed(Activity activity) {}
		
		@Override
		public void onActivityPaused(Activity activity)
		{
			Adjust.onPause();
		}
		
		@Override
		public void onActivityResumed(Activity activity)
		{
			Adjust.onResume();
		}
		
		@Override
		public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}
		
		@Override
		public void onActivityStarted(Activity activity) {}

        @Override
        public void onActivityStopped(Activity activity)
		{
			Log.i("yoyo", "DevToDev.endSession");
            //DevToDev.endSession();
        }
    }
	*/
	
	
	public double staticFinal()
	{
		Log.i("yoyo", "DevToDev_staticFinal");
		return 0.0;
	}
	
	
	public double adjustEvent(String arg0)
	{
		AdjustEvent event = new AdjustEvent(arg0);
		Adjust.trackEvent(event);
		
		return 0.0;
	}
	

	public double adjustIAP(String arg0, double sum, String currency)
	{
		AdjustEvent event = new AdjustEvent(arg0);
		event.setRevenue(sum, currency);
		
		Adjust.trackEvent(event);
		
		return 0.0;
	}
	
	// implements IExtensionBase -----------------------------------------------

	@Override
	public void onStart()
	{
		Log.i("yoyo", "Adjust onStart");
	};
	
	@Override
	public void onRestart(){};
	
	@Override
	public void onStop()
	{
		Log.i("yoyo", "Adjust onStop");
	};
	
	@Override
	public void onDestroy(){};
	
	@Override
	public void onPause()
	{
		Log.i("yoyo", "Adjust onPause begin");
		Adjust.onPause();
		Log.i("yoyo", "Adjust onPause end");
	};
	
	@Override
	public void onResume()
	{
		Log.i("yoyo", "Adjust onResume begin");
		Adjust.onResume();
		Log.i("yoyo", "Adjust onResume end");
	};
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){}; 
	
	@Override
	public void onConfigurationChanged(Configuration newConfig){};
	
	public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {};
	
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