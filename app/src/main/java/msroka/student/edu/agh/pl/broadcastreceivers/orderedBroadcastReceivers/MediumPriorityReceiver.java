package msroka.student.edu.agh.pl.broadcastreceivers.orderedBroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by maciek on 2015-12-02.
 */
public class MediumPriorityReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "MEDIUM", Toast.LENGTH_SHORT).show();
    }
}
