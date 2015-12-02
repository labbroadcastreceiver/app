package msroka.student.edu.agh.pl.broadcastreceivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import msroka.student.edu.agh.pl.broadcastreceivers.receivers.MyPowerDisconnectedBroadcastReceiver;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBoxWithFinalReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxWithFinalReceiver = (CheckBox) findViewById(R.id.checkBoxWithFinalReceiver);
    }

    IntentFilter intentFilterPowerDisconnected = new IntentFilter(Intent.ACTION_POWER_DISCONNECTED);
    MyPowerDisconnectedBroadcastReceiver myPowerDisconnectedBroadcastReceiver =
            new MyPowerDisconnectedBroadcastReceiver();
    @Override
    protected void onResume() {
        // Zaczynamy nasłuchiwanie
        registerReceiver(myPowerDisconnectedBroadcastReceiver, intentFilterPowerDisconnected);
        super.onResume();
    }
    @Override
    protected void onPause() {
        // Kończymy nasłuchiwanie
        unregisterReceiver(myPowerDisconnectedBroadcastReceiver);
        super.onPause();
    }


    public void sendBroadcastButtonClick(View view) {
        sendCustomBroadcast();
        Toast.makeText(getApplicationContext(), "My broadcast sent", Toast.LENGTH_SHORT).show();
    }


    void sendCustomBroadcast() {
        Intent intent = new Intent();
        intent.setAction("android.lab.WAZNE_POWIADOMIENIE");
        sendBroadcast(intent);
    }

    public void sendOrderedBroadcastButtonClick(View view) {
        sendCustomOrderedBroadcast(checkBoxWithFinalReceiver.isChecked());
        Toast.makeText(getApplicationContext(), "My ordered broadcast sent", Toast.LENGTH_SHORT).show();
    }
    void sendCustomOrderedBroadcast(boolean withFinalReceiver) {
        Intent intent = new Intent();
        intent.setAction("android.lab.WAZNE_PRIORYTETOWANE_POWIADOMIENIE");
        if(withFinalReceiver) {
            sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Toast.makeText(context, "Final receiver", Toast.LENGTH_SHORT).show();
                }

                ;
            }, null, Activity.RESULT_OK, null, null);
        } else {
            sendBroadcast(intent);
        }
    }
}
