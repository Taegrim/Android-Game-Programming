package kr.ac.tukorea.sgp.s2018182024.memorygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView flipsTextView;
    private int flips;
    private int openCardCount;

    private static final int[] BUTTON_IDS = new int[] {
            R.id.card_00,R.id.card_01,R.id.card_02,R.id.card_03,
            R.id.card_10,R.id.card_11,R.id.card_12,R.id.card_13,
            R.id.card_20,R.id.card_21,R.id.card_22,R.id.card_23,
            R.id.card_30,R.id.card_31,R.id.card_32,R.id.card_33,
    };

    private static final int[] RESOURCE_IDS = new int[]{
            R.mipmap.card_2c,R.mipmap.card_3d,R.mipmap.card_4h,R.mipmap.card_5s,
            R.mipmap.card_as,R.mipmap.card_qh,R.mipmap.card_jc,R.mipmap.card_kd,
            R.mipmap.card_2c,R.mipmap.card_3d,R.mipmap.card_4h,R.mipmap.card_5s,
            R.mipmap.card_as,R.mipmap.card_qh,R.mipmap.card_jc,R.mipmap.card_kd,
    };

    private void startGame(){
        setFlips(0);
        openCardCount = 0;
        for(int i = 0; i < BUTTON_IDS.length; ++i){
            ImageButton btn = findViewById(BUTTON_IDS[i]);
            btn.setVisibility(View.VISIBLE);
            btn.setImageResource(R.mipmap.card_blue_back);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flipsTextView = findViewById(R.id.flipsTextView);

        startGame();
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
    private int getCardIndex(int id){
        Integer index = idMap.get(id);
        if(null == index){
            Log.e(TAG, "Not Defined Card Index!");
            return -1;
        }
        return index;
    }

    private ImageButton prevImageButton;

    private void setFlips(int flips){
        this.flips = flips;
        flipsTextView.setText("Flips : " + flips);
    }

    public void onBtnCard(View view) {
        int index = getCardIndex(view.getId());
        Log.d(TAG, "Index : " + index);

        ImageButton btn = (ImageButton) view;
        if(btn == prevImageButton){
            return;
        }

        setFlips(this.flips + 1);
        int resId = RESOURCE_IDS[index];
        btn.setImageResource(resId);

        if(null != prevImageButton){
            int prevIndex = getCardIndex(prevImageButton.getId());
            if(resId == RESOURCE_IDS[prevIndex]){
                btn.setVisibility(View.INVISIBLE);
                prevImageButton.setVisibility(View.INVISIBLE);
                prevImageButton = null;
                openCardCount += 2;
                if(openCardCount == BUTTON_IDS.length)
                    askRetry();
                return;
            }
            else {
                prevImageButton.setImageResource(R.mipmap.card_blue_back);
            }
        }
        prevImageButton = btn;
    }

    public void onBtnRestart(View view) {
        askRetry();
    }

    private void askRetry(){
//      빌더 패턴을 이용한 AlertDialog 생성
        new AlertDialog.Builder(this)
                .setTitle("Restart")
                .setMessage("Do tou want to restart the game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "Restart");
                        startGame();
                    }
                })
                .setNegativeButton("No", null)
                .create()
                .show();
    }
}