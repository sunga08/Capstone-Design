package function1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myregisterlogin.R;

public class MyStartWalk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_start_walk);

        //산책시작메인 화면에서 넘어온 아이디 정보 받기
        Intent intent = getIntent();
        final String userID= intent.getStringExtra("userID");


    }
}
