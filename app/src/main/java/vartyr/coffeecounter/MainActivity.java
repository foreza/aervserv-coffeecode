package vartyr.coffeecounter;

// Defaults imported by Android Studio
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;    // App compatability for minimum / target API versions
import android.os.Bundle;                           //
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aerserv.sdk.*;
import com.aerserv.sdk.utils.UrlBuilder;


import java.util.Arrays;
import java.util.List;


// Main Activity is the entry point into our application.
public class MainActivity extends AppCompatActivity  {

    private AerServBanner banner;

    private String LOG_TAG;
    private String DEFAULT_PLC;
    private String APP_ID;
    private List<String> keywords;
    private int COFFEE_COUNT;

    // Set up a listener to listen to incoming events
    protected AerServEventListener listener = new AerServEventListener(){
        @Override
        public void onAerServEvent(final AerServEvent event, final List<Object> args){
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String msg = "";
                    switch (event) {
                        case AD_FAILED:
                            if (args.size() > 1) {
                                Integer adFailedCode =
                                        (Integer) args.get(AerServEventListener.AD_FAILED_CODE);
                                String adFailedReason =
                                        (String) args.get(AerServEventListener.AD_FAILED_REASON);
                                msg = "Ad failed with code=" + adFailedCode + ", reason=" + adFailedReason;
                            } else {
                                msg = "Ad Failed with message: " + args.get(0).toString();
                            }
                            break;

                    }
//                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    Log.d(LOG_TAG, msg);
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        LOG_TAG = globalVariable.getLogTag();
        DEFAULT_PLC = globalVariable.getDefaultPlc(0);
        APP_ID = globalVariable.getAppId();
        keywords = globalVariable.getKeywords();
        COFFEE_COUNT = globalVariable.getCOFFEE_COUNT();


        // Call the init function only once and toggle it to false after it has been called.
        // Use this to print the debug state of the application and any other useful pieces of information
        if (!globalVariable.getHasInit()){

            // Call the init function only once and toggle it to false after it has been called.

            AerServSdk.init(this, globalVariable.getAppId() );
            globalVariable.setInit();

            // Log the SDK version
            TextView version = (TextView) findViewById(R.id.sdkVersion);
            version.setText("v" + UrlBuilder.VERSION);

            // Any sort of init log messages should be printed here
            Log.d(LOG_TAG, "Running init with site app ID: " + APP_ID);
            Log.d(LOG_TAG, "Currently running SDK version: " + UrlBuilder.VERSION);

        }

        // Load this banner on the page.
         loadBanner();

    }


    public void loadBanner() {
        final AerServConfig config = new AerServConfig(this, DEFAULT_PLC)
                .setEventListener(listener)
//                .setRefreshInterval(60)
                .setKeywords(keywords);
//                .setDebug(true)
//                .setVerbose(true);
        banner = (AerServBanner) findViewById(R.id.banner);
        banner.configure(config).show();
    }



    public void updateCoffeeCountInView(int counter) {

        String message = Integer.toString(counter, 0) + " Beans!";

        Log.v("Updating counter: ", message);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.coffeeCounterView_Main);
        textView.setText(message);

    }

    // When we click 'increment coffee count, it should increment the value' and start a new activity to illustrate that, along with a back button.
    public void incrementCoffeeCount(View view) {

        Intent intent = new Intent(this, CoffeeIncrementedActivity.class);

        intent.putExtra("COFFEE_COUNT", COFFEE_COUNT);
        intent.putExtra("PLC_ID", DEFAULT_PLC);

        // Start the activity.
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        Log.d(LOG_TAG, " On activity result");

        super.onActivityResult(requestCode, resultCode, data);

        // If the request code is 1, the CoffeeIncrementedActivity is providing a result.
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                int amt = data.getIntExtra("INCREMENT_AMT", 0);
                Log.d(LOG_TAG, "Attempt to increment amount by" + Integer.toString(amt));
                globalVariable.setCOFFEE_COUNT(globalVariable.getCOFFEE_COUNT() + amt);
                Log.d(LOG_TAG, " UPDATED TOTAL =" + globalVariable.getCOFFEE_COUNT());
                updateCoffeeCountInView(globalVariable.getCOFFEE_COUNT());
            }
        }
    }


    // TODO: Do any sort of cleanup methods here

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(banner != null){
            banner.kill();
        }
    }



}