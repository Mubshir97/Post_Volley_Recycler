package com.example.postrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ModelData> modelDataArrayList=new ArrayList<>();
Button btn;
RecyclerView recyclerView;
AdapterShow adapter;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        btn=(Button) findViewById(R.id.btn);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
       // recyclerView.setAdapter(new AdapterShow(getApplicationContext(),modelDataArrayList));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
    }
    public void postData(){
try {
        String url="http://restapi.adequateshop.com/api/authaccount/registration";

             jsonObject=new JSONObject();
            jsonObject.put("name","hadfish ");
            jsonObject.put("email","jasd3@gmail.com");
            jsonObject.put("password","hero1250");
            Log.d("Tag","reqqqq"+jsonObject);

            final String stringBody = jsonObject.toString();

            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Log.d("Tag","===>"+response);
                         jsonObject=new JSONObject(response);
                           String data_values=jsonObject.getString("data");
                           JSONObject jsonObject1=new JSONObject(data_values);
                            ModelData modelData = new ModelData();

                            modelData.setId(jsonObject1.getString("Id"));
                            modelData.setName(jsonObject1.getString("Name"));
                        modelData.setEmail(jsonObject1.getString("Email"));
                            modelData.setToken(jsonObject1.getString("Token"));

                            modelDataArrayList.add(modelData);

                        adapter=new AdapterShow(getApplicationContext(),modelDataArrayList);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "error"+error, Toast.LENGTH_SHORT).show();

                }
            }){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {

                    try {
                        return stringBody == null ? null : stringBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException e) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",stringBody,"utf-8");
                        return null;
                    }
                }


                };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(stringRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}