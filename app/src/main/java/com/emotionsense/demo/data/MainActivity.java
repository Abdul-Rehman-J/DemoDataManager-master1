package com.emotionsense.demo.data;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.emotionsense.demo.data.loggers.StoreOnlyUnencryptedFiles;
import com.ubhave.datahandler.ESDataManager;
import com.ubhave.datahandler.except.DataHandlerException;
import com.ubhave.datahandler.loggertypes.AbstractDataLogger;
import com.ubhave.datahandler.transfer.DataUploadCallback;
import com.ubhave.sensormanager.ESSensorManager;
import com.ubhave.sensormanager.data.SensorData;
import com.ubhave.sensormanager.sensors.SensorUtils;

public class MainActivity extends Activity implements DataUploadCallback
{
	private final static String LOG_TAG = "MainActivity";

	private AbstractDataLogger logger;
	private ESSensorManager sensorManager;

	private SubscribeThread[] subscribeThreads;
	private SenseOnceThread[] pullThreads;


	// TODO: add push sensors you want to sense from here
    private final int[] Battery={SensorUtils.SENSOR_TYPE_BATTERY};
    private final int[] ConState={SensorUtils.SENSOR_TYPE_CONNECTION_STATE};
    private final int[] ConStrength={SensorUtils.SENSOR_TYPE_CONNECTION_STRENGTH};
    private final int[] PassiveLocation={SensorUtils.SENSOR_TYPE_PASSIVE_LOCATION};
    private final int[] PhoneState={SensorUtils.SENSOR_TYPE_PHONE_STATE};
    private final int[] pushSensors = { SensorUtils.SENSOR_TYPE_PROXIMITY };
    private final int[] Screen={SensorUtils.SENSOR_TYPE_SCREEN};
    private final int[] Sms={SensorUtils.SENSOR_TYPE_SMS};


    // TODO: add pull sensors you want to sense once from here
     	private final int[] pullSensors = {SensorUtils.SENSOR_TYPE_ACCELEROMETER};
    private final int[] Bluetooth = {SensorUtils.SENSOR_TYPE_BLUETOOTH};
    private final int[] CallReader = {SensorUtils.SENSOR_TYPE_CALL_CONTENT_READER};
    //private final int[] Gyroscope = {SensorUtils.SENSOR_TYPE_GYROSCOPE};
    private final int[] Location = {SensorUtils.SENSOR_TYPE_LOCATION};
    private final int[] Magnetic = {SensorUtils.SENSOR_TYPE_MAGNETIC_FIELD};
    private final int[] MicroPhone = {SensorUtils.SENSOR_TYPE_MICROPHONE};
    private final int[] PhoneRadio = {SensorUtils.SENSOR_TYPE_PHONE_RADIO};
    private final int[] SMSReader = {SensorUtils.SENSOR_TYPE_SMS_CONTENT_READER};
   // private final int[] StepCounter = {SensorUtils.SENSOR_TYPE_STEP_COUNTER};
    private final int[] Wifi = {SensorUtils.SENSOR_TYPE_WIFI};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try
		{
			// TODO: change this line of code to change the type of data logger
			// Note: you shouldn't have more than one logger!
//			logger = AsyncEncryptedDatabase.getInstance();
//			logger = AsyncWiFiOnlyEncryptedDatabase.getInstance();
//			logger = AsyncEncryptedFiles.getInstance();
//			logger = AsyncUnencryptedDatabase.getInstance();
//			logger = AsyncUnencryptedFiles.getInstance();
//			logger = StoreOnlyEncryptedDatabase.getInstance();
//			logger = StoreOnlyEncryptedFiles.getInstance();
//			logger = StoreOnlyUnencryptedDatabase.getInstance();
			logger = StoreOnlyUnencryptedFiles.getInstance();
			sensorManager = ESSensorManager.getSensorManager(this);

			// Example of starting some sensing in onCreate()
			// Collect a single sample from the listed pull sensors
			pullThreads = new SenseOnceThread[pullSensors.length];
			for (int i = 0; i < pullSensors.length; i++)
			{
				pullThreads[i] = new SenseOnceThread(this, sensorManager, logger, pullSensors[i]);
				pullThreads[i].start();
			}
//            pullThreads = new SenseOnceThread[StepCounter.length];
//            for (int i = 0; i < StepCounter.length; i++)
//            {
//                pullThreads[i] = new SenseOnceThread(this, sensorManager, logger, StepCounter[i]);
//                pullThreads[i].start();
//            }
		}
		catch (Exception e)
		{
			Toast.makeText(this, "" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
			Log.d(LOG_TAG, e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void onResume()
	{
		super.onResume();
		
		// Example of starting some sensing in onResume()
		// Collect a single sample from the listed push sensors
		subscribeThreads = new SubscribeThread[pushSensors.length];
		for (int i = 0; i < pushSensors.length; i++)
		{
			subscribeThreads[i] = new SubscribeThread(this, sensorManager, logger, pushSensors[i]);
			subscribeThreads[i].start();
		}
        subscribeThreads = new SubscribeThread[CallReader.length];
        for (int i = 0; i < CallReader.length; i++)
        {
            subscribeThreads[i] = new SubscribeThread(this, sensorManager, logger, CallReader[i]);
            subscribeThreads[i].start();
        }
        subscribeThreads = new SubscribeThread[SMSReader.length];
        for (int i = 0; i < SMSReader.length; i++)
        {
            subscribeThreads[i] = new SubscribeThread(this, sensorManager, logger, SMSReader[i]);
            subscribeThreads[i].start();
        }
        subscribeThreads = new SubscribeThread[Location.length];
        for (int i = 0; i < Location.length; i++)
        {
            subscribeThreads[i] = new SubscribeThread(this, sensorManager, logger, Location[i]);
            subscribeThreads[i].start();
        }
        subscribeThreads = new SubscribeThread[Magnetic.length];
        for (int i = 0; i < Magnetic.length; i++)
        {
            subscribeThreads[i] = new SubscribeThread(this, sensorManager, logger, Magnetic[i]);
            subscribeThreads[i].start();
        }
        subscribeThreads = new SubscribeThread[Sms.length];
        for (int i = 0; i < Sms.length; i++)
        {
            subscribeThreads[i] = new SubscribeThread(this, sensorManager, logger, Sms[i]);
            subscribeThreads[i].start();
        }
        subscribeThreads = new SubscribeThread[Sms.length];
        for (int i = 0; i < Sms.length; i++)
        {
            subscribeThreads[i] = new SubscribeThread(this, sensorManager, logger, Sms[i]);
            subscribeThreads[i].start();
        }
        pullThreads = new SenseOnceThread[Bluetooth.length];
        for (int i = 0; i < Bluetooth.length; i++)
        {
            pullThreads[i] = new SenseOnceThread(this, sensorManager, logger, Bluetooth[i]);
            pullThreads[i].start();
        }
        pullThreads = new SenseOnceThread[CallReader.length];
        for (int i = 0; i < CallReader.length; i++)
        {
            pullThreads[i] = new SenseOnceThread(this, sensorManager, logger, CallReader[i]);
            pullThreads[i].start();
        }
//        pullThreads = new SenseOnceThread[Gyroscope.length];
//        for (int i = 0; i < Gyroscope.length; i++)
//        {
//            pullThreads[i] = new SenseOnceThread(this, sensorManager, logger, Gyroscope[i]);
//            pullThreads[i].start();
//        }
        pullThreads = new SenseOnceThread[Location.length];
        for (int i = 0; i < Location.length; i++)
        {
            pullThreads[i] = new SenseOnceThread(this, sensorManager, logger, Location[i]);
            pullThreads[i].start();
        }
        pullThreads = new SenseOnceThread[Magnetic.length];
        for (int i = 0; i < Magnetic.length; i++)
        {
            pullThreads[i] = new SenseOnceThread(this, sensorManager, logger, Magnetic[i]);
            pullThreads[i].start();
        }
        pullThreads = new SenseOnceThread[MicroPhone.length];
        for (int i = 0; i < MicroPhone.length; i++)
        {
            pullThreads[i] = new SenseOnceThread(this, sensorManager, logger, MicroPhone[i]);
            pullThreads[i].start();
        }
        pullThreads = new SenseOnceThread[PhoneRadio.length];
        for (int i = 0; i < PhoneRadio.length; i++)
        {
            pullThreads[i] = new SenseOnceThread(this, sensorManager, logger, PhoneRadio[i]);
            pullThreads[i].start();
        }
//        pullThreads = new SenseOnceThread[Sms.length];
//        for (int i = 0; i < Sms.length; i++)
//        {
//            pullThreads[i] = new SenseOnceThread(this, sensorManager, logger, Sms[i]);
//            pullThreads[i].start();
//        }
        pullThreads = new SenseOnceThread[Wifi.length];
        for (int i = 0; i < Wifi.length; i++)
        {
            pullThreads[i] = new SenseOnceThread(this, sensorManager, logger, Wifi[i]);
            pullThreads[i].start();
        }
	}

	@Override
	public void onPause()
	{
		super.onPause();
		
		// Don't forget to stop sensing when the app pauses
		for (SubscribeThread thread : subscribeThreads)
		{
			thread.stopSensing();
		}
        	}

//	public void onSearchClicked(final View view)
//	{
//		// Counts the number of sensor events from the last 60 seconds
//        //long startTime = System.currentTimeMillis() - (1000L * 60);
//		try
//		{
//			long startTime = System.currentTimeMillis() - (15000);
//			ESDataManager dataManager = logger.getDataManager();
//
//			for (int pushSensor : pushSensors)
//			{
//				List<SensorData> recentData = dataManager.getRecentSensorData(pushSensor, startTime);
//				Toast.makeText(this, "Recent "+SensorUtils.getSensorName(pushSensor)+": " + recentData.size(), Toast.LENGTH_LONG).show();
//			}
//
//			for (int pushSensor : pullSensors)
//			{
//				List<SensorData> recentData = dataManager.getRecentSensorData(pushSensor, startTime);
//				Toast.makeText(this, "Recent "+SensorUtils.getSensorName(pushSensor)+": " + recentData.size(), Toast.LENGTH_LONG).show();
//			}
//		}
//		catch (Exception e)
//		{
//			Toast.makeText(this, "Error retrieving sensor data", Toast.LENGTH_LONG).show();
//			Log.d(LOG_TAG, e.getLocalizedMessage());
//			e.printStackTrace();
//		}
//	}
//
//	public void onFlushClicked(final View view)
//	{
//		// Tries to POST all of the stored sensor data to the server
//		try
//		{
//			ESDataManager dataManager = logger.getDataManager();
//			dataManager.postAllStoredData(this);
//		}
//		catch (DataHandlerException e)
//		{
//			Toast.makeText(this, "Exception: "+e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//			Log.d(LOG_TAG, ""+e.getLocalizedMessage());
//		}
//	}

	@Override
	public void onDataUploaded()
	{
		runOnUiThread(new Runnable()
		{

			@Override
			public void run()
			{
				// Callback method: the data has been successfully posted
				Toast.makeText(MainActivity.this, "Data transferred.", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	@Override
	public void onDataUploadFailed()
	{
		runOnUiThread(new Runnable()
		{

			@Override
			public void run()
			{
				// Callback method: the data has not been successfully posted
				Toast.makeText(MainActivity.this, "Error transferring data", Toast.LENGTH_LONG).show();
			}
		});
	}
}
