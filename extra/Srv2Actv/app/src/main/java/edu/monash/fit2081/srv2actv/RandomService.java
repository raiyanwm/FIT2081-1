package edu.monash.fit2081.srv2actv;

import android.app.IntentService;
import android.content.Intent;

import java.util.Random;

public class RandomService extends IntentService {

    public RandomService() {
        super("RandomService");
    }

    // the amount of milliseconds the loop has to delay
    int loopDelay=2000;


    // this callback method will be invoked when the service gets started
    @Override
    protected void onHandleIntent(Intent intent) {

        // let send a broadcast to anybody is currently listening
        // to the filter  broadcastFilter="monash.fit2081.randomService"
        Intent msgIntent = new Intent();

        msgIntent.setAction(MainActivity.broadcastFilter);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            // put our payload inside the intent
            msgIntent.putExtra("randomValueKey", random.nextInt(100));
            // send it !!!!
            sendBroadcast(msgIntent);

            // this piece of code delays (sleeps) the execution of the current thread by 2 seconds
            try {
                Thread.sleep(loopDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}



