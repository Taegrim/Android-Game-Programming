package kr.ac.tukorea.sgp.s2018182024.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.changeStringBtn);
        btn.setOnClickListener(changeStringBtnOnClickHandler);

        btn = findViewById(R.id.anotherBtn);
        btn.setOnClickListener(anotherBtnOnClickHandler);
    }

    // app 이 실행될 때 해당 멤버가 new 되고, 이 멤버는 onClick 이라는 함수를 가지고 있음
    private View.OnClickListener changeStringBtnOnClickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView tv = findViewById(R.id.snumTextView);
            tv.setText("새로운 문자열");
        }
    };

    private View.OnClickListener anotherBtnOnClickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView tv = findViewById(R.id.snumTextView);
            tv.setText("다른 문자열");
        }
    };
}