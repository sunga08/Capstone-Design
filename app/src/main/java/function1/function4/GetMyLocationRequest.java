package function1.function4;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class GetMyLocationRequest extends StringRequest {

    public GetMyLocationRequest(String url, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }//생성자

    //서버 url 설정하기
    final static private String URL = "http://bi1724.dothome.co.kr/GetMyLocation.php"; //반려견 정보 php파일
    private Map<String,String> map;
    public GetMyLocationRequest(String userID, String myLatitude, String myLongitude,Response.Listener<String> listner){
        super(Request.Method.POST,URL,listner,null);
        map = new HashMap<>();
        map.put("userID",userID);
        map.put("myLatitude",myLatitude);
        map.put("myLongitude",myLongitude);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
