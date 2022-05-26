package com.example.project10_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnResult;
    Button btnFinish;
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
                //startActivity(intent);
                //콜백함수를 등록해서 호출을 해야한다. 별다른 메소드가 아니라 launch메소드를 사용한다.
                mStartForResult.launch(intent); //데이터를 받기 위해서
            }
        });


    }
    //데이터 리턴을 처리하기 위해서 선언
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                Intent intent = result.getData();
                Log.i("TAG", "RESULT_OK : "+ RESULT_OK);

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dialog);

                TextView dialogtv = dialog.findViewById(R.id.dialogtv);
                ImageView dialogiv = dialog.findViewById(R.id.dialogiv);

                btnFinish = dialog.findViewById(R.id.btnFinish);
                btnFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialogtv.setText(intent.getStringExtra("ImageName"));
                dialogiv.setImageResource(intent.getIntExtra("ImageID", 0));
                dialog.show();
            }
        }
    });

}

