package com.example.project10_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("명화선호도조사");

        //투표결과리스트
        int voteCount[] = new int[9];
        for (int i = 0; i < 6; i++) {
            voteCount[i] = 0;
        }

        Integer imageId[] = {R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9};
        String imgName[] = {"독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
                "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들",
                "해변에서"};

        ImageView image[] = new ImageView[9];
        for (int i = 0; i < imageId.length; i++) {
            int index = i;  //i를 왜 인덱스에다 저장 하는지 모르겠다;;;
            image[index] = findViewById(imageId[index]);
            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    voteCount[index]++;
                    Toast.makeText(MainActivity.this, imgName[index] + ": " + voteCount[index] + "표를 선택", Toast.LENGTH_SHORT).show();

                }
            });
        }


        btnResult = findViewById(R.id.btnResult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("VoteCount", voteCount); //배열 명을 넘기는 것은 배열의 0번째 를 넘기는 것과 같다.
                intent.putExtra("ImageName", imgName);
                startActivity(intent);

            }
        });
    }
}

