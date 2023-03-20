package kr.ac.tukorea.sgp.s2018182024.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int BUTTON_IDS[] = new int[] {
            R.id.card_00,R.id.card_01,R.id.card_02,R.id.card_03,
            R.id.card_10,R.id.card_11,R.id.card_12,R.id.card_13,
            R.id.card_20,R.id.card_21,R.id.card_22,R.id.card_23,
            R.id.card_30,R.id.card_31,R.id.card_32,R.id.card_33,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 버튼 id, index 를 담는 해시맵
    private static HashMap<Integer, Integer> idMap;
    // static 블럭은 클래스 생성 시 한번 만 실행됨
    static{
        idMap = new HashMap<>();
        for(int i = 0; i < BUTTON_IDS.length; ++i){
            idMap.put(BUTTON_IDS[i], i);
        }
    }
    private int getCardIndex(View view){
        Integer index = idMap.get(view.getId());
        if(null == index){
            Log.e(TAG, "Not Defined Card Index!");
            return -1;
        }
        return index;
    }

    public void onBtnCard(View view) {
        int id = getCardIndex(view);
        Log.d(TAG, "Index : " + id);
    }
}