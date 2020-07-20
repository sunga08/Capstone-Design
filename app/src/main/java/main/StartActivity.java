package main;
//어플 시작하면 도움말을 보여줌
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myregisterlogin.R;

import registerlogin.LoginActivity;

public class StartActivity extends AppCompatActivity {
    private Button startbtn;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startbtn= (Button) findViewById(R.id.startbtn);
        startbtn.setBackgroundResource(R.drawable.start01);
        startbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                count++;
                if(count == 1){
                    startbtn.setBackgroundResource(R.drawable.start02);
                }
                else if(count == 2){
                    startbtn.setBackgroundResource(R.drawable.start03);
                }
                else if(count>=3){
                    Intent intent =  new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}
