package com.example.catalogueapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MessagesIntentService extends IntentService {
    private static final String EXTRA_PARAM1 = "com.example.catalogueapp.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.catalogueapp.extra.PARAM2";
    public MessagesIntentService() {
        super("MessagesIntentService");
    }
    static Context cont;
    public static void startAction(Context context, int seconds, String mood) {
        Intent intent = new Intent(context, MessagesIntentService.class);
        intent.putExtra(EXTRA_PARAM1, seconds);
        intent.putExtra(EXTRA_PARAM2, mood);
        context.startService(intent);

        cont = context;
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final int param1 = intent.getIntExtra(EXTRA_PARAM1,0);
            final String param2 = intent.getStringExtra(EXTRA_PARAM2);
            Log.d("RECEIVED" , "P:" + param1 + " P:" + param2);
            int i = 0;
            //this.stopSelf();
            try{
                while(i++ <  10) {
                   Log.d("Message","MESSAGE FROM INSIDE");

                    Thread.sleep(param1 * 1000);
                }
            }catch(InterruptedException ie){
                Log.d("EXCEPTION" , "DONE EXCEPTION");
                Thread.currentThread().interrupt();
            }
            Log.d("RUN AFTER " , "HEY 5 seconds!");
            //Toast.makeText(MessagesIntentService.cont,"Message FInalized",Toast.LENGTH_SHORT).show();
            stopSelf();
        }
    }
}
