package com.glorysys.restapiVolley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebService {
    private Context context;


    public WebService(Context context) {
        this.context = context;
    }



    public void getData(IgetData igetData){
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,
                "https://gorest.co.in/public/v1/posts", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("data");

                                JSONObject jsonObject=jsonArray.getJSONObject(3);
                               String title= jsonObject.getString("title");
                               String body= jsonObject.getString("body");
                               igetData.onResponse(title,body);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    igetData.onResponse("error","body error");
            }
        });
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }

    public interface IgetData{
        void onResponse(String title,String body);
    }
}
