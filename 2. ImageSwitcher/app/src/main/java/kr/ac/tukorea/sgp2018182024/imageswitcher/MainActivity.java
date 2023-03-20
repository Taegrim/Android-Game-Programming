package kr.ac.tukorea.sgp2018182024.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private int page = 1;

    private ImageView imageView;
    private TextView pageTextView;
    private ImageButton nextButton, prevButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        pageTextView = findViewById(R.id.pageTextView);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);

        pageTextView.setText(1 + " / " + RES_IDS.length);
    }

    public void onBtnPrev(View view) {
        Log.d(TAG, "Prev Button Click");
        setPage(page - 1);
    }

    public void onBtnNext(View view) {
        Log.d(TAG, "Next Button Click");
        setPage(page + 1);
    }

    // 고정된 값을 버튼이 눌릴 때마다 생성 하는 것보다 class 변수로 생성함
    // lifecycle 이 class 와 같으므로 static 으로 설정, 상수 이므로 final 키워드 사용
    private static final int[] RES_IDS = new int[]{
            R.mipmap.cat_1,
            R.mipmap.cat_2,
            R.mipmap.cat_3,
            R.mipmap.cat_4,
            R.mipmap.cat_5,
            R.mipmap.cat_6,
    };

    private void setPage(int page){
        if(page < 1 || page > RES_IDS.length)
            return;

        int resId = RES_IDS[page - 1];
        imageView.setImageResource(resId);
        pageTextView.setText(page + " / " + RES_IDS.length);

        prevButton.setEnabled(page > 1);
        nextButton.setEnabled(page < RES_IDS.length);

        this.page = page;
    }
}