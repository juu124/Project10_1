package com.example.project10_1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.project10_1.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {
    Button btnReturn;
    int max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("투표결과");

        Intent intent = getIntent();
        int voteResult[] = intent.getIntArrayExtra("VoteCount");
        String imageName[] = intent.getStringArrayExtra("ImageName");

        TextView tvTop = findViewById(R.id.tvTop);
        ImageView ivTop = findViewById(R.id.ivTop);
        Integer imageId[] = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9};

        max = 0;
        for (int i = 0; i < voteResult.length; i++) {
            if (voteResult[max] < voteResult[i]) {
                max = i;
            }
        }

        tvTop.setText(imageName[max]);
        ivTop.setImageResource(imageId[max]);

        TextView tv[] = new TextView[9];
        RatingBar rbar[] = new RatingBar[9];

        Integer tvId[] = {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9};
        Integer rbarId[] = {R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4, R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9};

        for (int i = 0; i < voteResult.length; i++) {
            tv[i] = findViewById(tvId[i]);
            rbar[i] = findViewById(rbarId[i]);
        }

        for (int i = 0; i < voteResult.length; i++) {
            tv[i].setText(imageName[i]);
            rbar[i].setRating(voteResult[i]);
        }

        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(ResultActivity.this, MainActivity.class);
                outIntent.putExtra("ImageName",imageName[max]);
                outIntent.putExtra("ImageID", imageId[max]);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}