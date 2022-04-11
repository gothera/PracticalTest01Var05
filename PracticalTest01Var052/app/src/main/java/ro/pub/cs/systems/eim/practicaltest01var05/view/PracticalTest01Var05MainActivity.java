package ro.pub.cs.systems.eim.practicaltest01var05.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ro.pub.cs.systems.eim.general.Constants;
import ro.pub.cs.systems.eim.practicaltest01var05.R;
import ro.pub.cs.systems.eim.service.PracticalTest01Var05Service;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private Button topLeft, topRight, botoomLeft, bottomRight, center, navigateButton;
    private TextView textView;
    private ButtonListener listener;
    private IntentFilter intentFilter = new IntentFilter();

    private class ButtonListener implements View.OnClickListener {
        public int totalPushes = 0;

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.navigateButton) {
                Intent intent = new Intent("ro.pub.cs.systems.eim.practicaltest01.view.intent.action.PracticalTest01Var05SecondActivity");
                intent.putExtra(Constants.TOTAL_PUSHES, String.valueOf(totalPushes));
                startActivityForResult(intent, Constants.ACTIVITY_CODE);
            }
            totalPushes++;
            String current = textView.getText().toString();
            String id = "";
            if (view.getId() == R.id.topRight) {
                id = "Top Right";
            }
            if (view.getId() == R.id.topLeft) {
                id = "Top Left";
            }
            if (view.getId() == R.id.bottomLeft) {
                id = "BottomLeft";
            }
            if (view.getId() == R.id.topLeft) {
                id = "Top Left";
            }
            current = current + id;

            textView.setText(current);

            if(totalPushes > 4) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
                intent.setComponent(new ComponentName("ro.pub.cs.systems.eim.practicaltest01var05", "ro.pub.cs.systems.eim.practicaltest01var05.service.PracticalTest01Var05Service"));
                intent.putExtra(Constants.SERVICE_STRING, textView.getText().toString());
                getApplicationContext().startService(intent);
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);

        topLeft = (Button) findViewById(R.id.topLeft);
        topRight = (Button) findViewById(R.id.topRight);
        bottomRight = (Button) findViewById(R.id.bottomRight);
        botoomLeft = (Button) findViewById(R.id.bottomLeft);
        center = (Button) findViewById(R.id.center);
        navigateButton = (Button) findViewById(R.id.navigateButton);
        textView = (TextView) findViewById(R.id.textShow);
        listener = new ButtonListener();
        topLeft.setOnClickListener(listener);
        topRight.setOnClickListener(listener);
        bottomRight.setOnClickListener(listener);
        botoomLeft.setOnClickListener(listener);
        navigateButton.setOnClickListener(listener);
        intentFilter.addAction(Constants.globalAction);


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("tagda", savedInstanceState.getString(Constants.SAVE_STATE));
        if (savedInstanceState.getString(Constants.SAVE_STATE) != null) {
            Toast.makeText(this, "Number of pushes was " +  savedInstanceState.getString(Constants.SAVE_STATE),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
         savedInstanceState.putInt(Constants.SAVE_STATE, listener.totalPushes);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "Activity result was " + resultCode,Toast.LENGTH_LONG).show();
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("dADADA", intent.getStringExtra(Constants.SERVICE_eXTRA));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}