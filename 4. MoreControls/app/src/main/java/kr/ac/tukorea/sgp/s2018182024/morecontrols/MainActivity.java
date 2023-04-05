package kr.ac.tukorea.sgp.s2018182024.morecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import kr.ac.tukorea.sgp.s2018182024.morecontrols.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nameEditText.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.v(TAG, "beforeTextChanged(i=" + i + ",i1=" + i1 + ",i2=" + "i2");
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.v(TAG, "onTextChanged(seq=" + charSequence + ",i=" + i + ",i1=" + i1 + ",i2=" + "i2");
            binding.outputTextView.setText(charSequence);
            if(binding.immediateSwitch.isChecked()){
                doIt();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            Log.v(TAG, "afterTextChanged(" + editable.toString() + ")");
        }
    };

    public void onBtnDoIt(View view) {
        Log.d(TAG, "Do It!");
        doIt();
    }

    private void doIt(){
        int stringResId = binding.goodProgrammerCheckbox.isChecked() ? R.string.pay_for_good_fmt : R.string.pay_for_not_good_fmt;
        String name = binding.nameEditText.getText().toString();
        if(0 == name.trim().length()){
            name = "NoName";
        }
        String text = getString(stringResId, name);
        binding.outputTextView.setText(text);
    }

    public void onCheckBoxGoodProgrammer(View view) {
        int emojiResId = binding.goodProgrammerCheckbox.isChecked() ? R.string.pay_good_emoji : R.string.pay_not_good_emoji;
        binding.emojiTextView.setText(getString(emojiResId));
    }

    public void onBtnNewActivity(View view) {
        Intent intent = new Intent(this, AnotherActivity.class);
        startActivity(intent);
    }
}