package kr.ac.tukorea.sgp.s2018182024.morecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private CheckBox goodProgrammerCheckbox;
    private TextView outputTextView;
    private TextView emojiTextView;
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goodProgrammerCheckbox = findViewById(R.id.goodProgrammerCheckbox);
        outputTextView = findViewById(R.id.outputTextView);
        emojiTextView = findViewById(R.id.emojiTextView);
        nameEditText = findViewById(R.id.nameEditText);
    }

    public void onBtnDoIt(View view) {
        Log.d(TAG, "Do It!");
        int stringResId = goodProgrammerCheckbox.isChecked() ? R.string.pay_for_good_fmt : R.string.pay_for_not_good_fmt;
        String name = nameEditText.getText().toString();
        if(0 == name.trim().length()){
            name = "NoName";
        }
        String text = getString(stringResId, name);
        outputTextView.setText(text);
    }

    public void onCheckBoxGoodProgrammer(View view) {
        int emojiResId = goodProgrammerCheckbox.isChecked() ? R.string.pay_good_emoji : R.string.pay_not_good_emoji;
        emojiTextView.setText(getString(emojiResId));
    }
}