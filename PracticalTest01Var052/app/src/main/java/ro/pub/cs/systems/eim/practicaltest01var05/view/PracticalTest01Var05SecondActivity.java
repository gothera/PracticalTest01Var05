package ro.pub.cs.systems.eim.practicaltest01var05.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ro.pub.cs.systems.eim.general.Constants;
import ro.pub.cs.systems.eim.practicaltest01var05.R;

public class PracticalTest01Var05SecondActivity extends AppCompatActivity {

    private EditText totalEdit;
    private Button verifyButton, cancelButton;

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.verifyButton:
                    setResult(1);
                    finish();
                    break;
                case R.id.cancelButton:
                    setResult(0);
                    finish();
                    break;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_second);
        totalEdit = (EditText) findViewById(R.id.totalPushes);
        verifyButton = (Button) findViewById(R.id.verifyButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        ButtonListener liste = new ButtonListener();
        verifyButton.setOnClickListener(liste);
        cancelButton.setOnClickListener(liste);
        Intent intent = getIntent();
        if (intent != null) {
            int totalPushes  = Integer.valueOf(intent.getStringExtra(Constants.TOTAL_PUSHES));
            totalEdit.setText(String.valueOf(totalPushes));
        }

    }
}