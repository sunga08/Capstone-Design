package function1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myregisterlogin.R;
import java.util.ArrayList;
//꺾은선 그래프
//MPAndroidChart import
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.charts.LineChart;

import main.MainActivity;

public class MyStartWalkMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_start_walk_main);

        //메인 화면에서 넘어온 아이디 정보 받기
        Intent intent = getIntent();
        final String userID= intent.getStringExtra("userID");

        //산책시작 버튼
        Button btn_startwalk = findViewById(R.id.btn_startwalk);
        btn_startwalk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MyStartWalkMain.this, MyStartWalk.class);
                //MainActovity: 산책시작화면으로 회원의 아이디 정보 넘기기
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });
        //만족도 입력 버튼
        Button btn_input = findViewById(R.id.btn_input);


        //선 그래프
        LineChart lineChart;
        ArrayList<Entry> entry_chart = new ArrayList<>();

        lineChart = (LineChart) findViewById(R.id.chart);//layout의 id

//chartData하나에 linedataset이 set1, set2로 두개의 라인을 가진 그래프

        ArrayList<Entry> entry1 = new ArrayList<>();
        ArrayList<Entry> entry2 = new ArrayList<>();

        //그래프에 들어갈 좌표값 입력
        entry1.add(new Entry(1, 2));
        entry2.add(new Entry(1, 4));
        entry1.add(new Entry(2, 5));
        entry2.add(new Entry(2, 2));
        entry1.add(new Entry(3, 5));
        entry2.add(new Entry(3, 9));
        //entry add는 알아서 반복문을 넣든 각자 코드에 맞게 응용하시면 됨.

        LineData chartData = new LineData();


        LineDataSet set1 = new LineDataSet(entry1, "만족도");

        lineChart.setBackgroundColor(Color.LTGRAY); // 그래프 배경 색 설정
        set1.setColor(Color.BLUE); // 차트의 선 색 설정
        set1.setCircleColor(Color.BLUE); // 차트의 points 점 색 설정
        //set1.setDrawFilled(true); // 차트 아래 fill(채우기) 설정
        //set1.setFillColor(Color.BLACK); // 차트 아래 채우기 색 설정
        chartData.addDataSet(set1);

        LineDataSet set2 = new LineDataSet(entry2, "산책 시간");
        set1.setColor(Color.GREEN); // 차트의 선 색 설정
        set1.setCircleColor(Color.GREEN); // 차트의 points 점 색 설정
        chartData.addDataSet(set2);
        lineChart.setData(chartData);

        lineChart.invalidate(); //Invalidate()로 차트 갱신

    }
}
