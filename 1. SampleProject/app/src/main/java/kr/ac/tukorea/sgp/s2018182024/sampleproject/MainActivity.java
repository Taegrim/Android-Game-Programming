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
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = findViewById(R.id.snumTextView);
                tv.setText("변경된 문자열");
            }
        });

        btn = findViewById(R.id.anotherBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = findViewById(R.id.snumTextView);
                tv.setText("다른 문자열");
            }
        });
    }
}