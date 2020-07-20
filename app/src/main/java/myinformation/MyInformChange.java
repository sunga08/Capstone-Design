package myinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myregisterlogin.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MyInformChange extends AppCompatActivity {

    private RadioButton rbtn_mydogsize1,rbtn_mydogsize2,rbtn_mydogsize3;
    private RadioButton rbtn_mydogspecies3,rbtn_mydogspecies9,rbtn_mydogspecies14;
    private RadioButton rbtn_baddog3,rbtn_baddog9,rbtn_baddog14;

    private EditText et_mydogage;
    private Button btn_apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_inform_change);

        btn_apply = findViewById(R.id.btn_apply);
        et_mydogage= findViewById(R.id.et_mydogage);

        //라디오 버튼 정의
        {
            rbtn_mydogsize1 = findViewById(R.id.rbtn_mydogsize1);
            rbtn_mydogsize2 = findViewById(R.id.rbtn_mydogsize2);
            rbtn_mydogsize3 = findViewById(R.id.rbtn_mydogsize3);

            rbtn_mydogspecies3 = findViewById(R.id.rbtn_mydogspecies3);
            rbtn_mydogspecies9 = findViewById(R.id.rbtn_mydogspecies9);
            rbtn_mydogspecies14 = findViewById(R.id.rbtn_mydogspecies14);

            rbtn_baddog3 = findViewById(R.id.rbtn_baddog3);
            rbtn_baddog9 = findViewById(R.id.rbtn_baddog9);
            rbtn_baddog14 = findViewById(R.id.rbtn_baddog14);
        }

        btn_apply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String myDogAge="";
                String myDogSize = "";
                String myDogSpecies = "";
                String badDog = "";
                //MyInformCheck에서 넘어온 아이디 정보를 인텐트로 받음
                Intent intent = getIntent();
                String userID = intent.getStringExtra("userID");

                //라디오버튼 선택 부분
                {
                    if (rbtn_mydogsize1.isChecked()) {
                        myDogSize = rbtn_mydogsize1.getText().toString();

                    } else if (rbtn_mydogsize2.isChecked()) {
                        myDogSize = rbtn_mydogsize2.getText().toString();
                    } else{
                        myDogSize = rbtn_mydogsize3.getText().toString();
                    }

                    if (rbtn_mydogspecies3.isChecked()) {
                        myDogSpecies = rbtn_mydogspecies3.getText().toString();
                    } else if (rbtn_mydogspecies9.isChecked()) {
                        myDogSpecies = rbtn_mydogspecies9.getText().toString();
                    } else{
                        myDogSpecies = rbtn_mydogspecies14.getText().toString();
                    }

                    if (rbtn_baddog3.isChecked()) {
                        badDog = rbtn_baddog3.getText().toString();
                    } else if (rbtn_baddog9.isChecked()) {
                        badDog = rbtn_baddog9.getText().toString();
                    } else{
                        badDog = rbtn_baddog14.getText().toString();
                    }
                }

                //불리 구문
                Response.Listener<String> responseListner = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        //제이슨 오브젝트 로 서버 전송 으로 반려견 정보 등록함 (일반 String 사용할수 없기때문) = 운반체
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response); //알트+엔터로 오류 처리
                            boolean success = jsonObject.getBoolean("success"); //php문에서 success 값을 가져옴 성공여부 알수 있음
                            if (success)  //Myinformcheck 액티비티로 화면전환, 내정보 확인함
                            {
                                Toast.makeText(getApplicationContext(), "내 반려견 정보 등록 성공", Toast.LENGTH_LONG).show();

                            } else
                            {    //등록 실패
                                Toast.makeText(getApplicationContext(), "내 반려견 정보 등록 실패 ", Toast.LENGTH_LONG).show();
                                return;
                            }

                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                };

                Integer myDogAge_int;

                try {
                    myDogAge_int = Integer.parseInt(et_mydogage.getText().toString());
                }catch(NumberFormatException e){
                    myDogAge_int=0;
                }
                //서버로 볼리를 이용해서 (레지스터 리퀘스트) 요청을 함
                MyInformChangeRequest myInformChangeRequest = new MyInformChangeRequest(userID ,myDogAge_int,myDogSize, myDogSpecies, badDog,responseListner);
                RequestQueue queue = Volley.newRequestQueue(MyInformChange.this);
                queue.add(myInformChangeRequest);

            }
        });


    }

}
