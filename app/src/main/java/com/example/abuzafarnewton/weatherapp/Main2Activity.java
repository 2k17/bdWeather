package com.example.abuzafarnewton.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3;
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11,textView12;
    private  String url;
    JSONObject jsonObject,job,obj2, main1;
    String Obj1_date,Obj2_date,Obj3_date,Obj1_icon,Obj2_icon,Obj3_icon,Obj1_minmax,Obj2_minmax,Obj3_minmax,Obj1_status,Obj2_status,Obj3_status,Obj1_maint3mp,Obj2_maint3mp,Obj3_maint3mp;
    Double Obj1_maintemp,Obj2_maintemp,Obj3_maintemp,x,y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        url=getIntent().getExtras().getString("targetURL");

        imageView1=(ImageView) findViewById(R.id.imageView1);
        imageView2=(ImageView) findViewById(R.id.imageView2);
        imageView3=(ImageView) findViewById(R.id.imageView3);

        textView1=(TextView) findViewById(R.id.textView1);
        textView2=(TextView) findViewById(R.id.textView2);
        textView3=(TextView) findViewById(R.id.textView3);
        textView4=(TextView) findViewById(R.id.textView4);
        textView5=(TextView) findViewById(R.id.textView5);
        textView6=(TextView) findViewById(R.id.textView6);
        textView7=(TextView) findViewById(R.id.textView7);
        textView8=(TextView) findViewById(R.id.textView8);
        textView9=(TextView) findViewById(R.id.textView9);
        textView10=(TextView) findViewById(R.id.textView10);
        textView11=(TextView) findViewById(R.id.textView11);
        textView12=(TextView) findViewById(R.id.textView12);




        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {



                    JSONArray jsonArray=new JSONArray();
                    jsonArray=response.getJSONArray("list");
                    job=jsonArray.getJSONObject(0);

                    JSONArray weather=new JSONArray();
                    weather=job.getJSONArray("weather");
                    obj2=weather.getJSONObject(0);
                    Obj1_icon=obj2.getString("icon");
                    Obj1_status=obj2.getString("main");



                    main1=job.getJSONObject("main");
                    Obj1_maintemp=main1.getDouble("temp")-273.15;
                    Integer z=Obj1_maintemp.intValue();
                    Obj1_maint3mp=z.toString()+"°C";

                    x=main1.getDouble("temp_min")-273.15;
                    int xx=x.intValue();
                    y=main1.getDouble("temp_max")-273.15;
                    int yy=y.intValue();
                    Obj1_minmax=xx+"°C/"+yy+"°C";
                    Obj1_date=job.getString("dt_txt");



                    obj2= jsonArray.getJSONObject(8);
                    weather=obj2.getJSONArray("weather");
                    job=weather.getJSONObject(0);
                    Obj2_icon=job.getString("icon");
                    Obj2_status=job.getString("main");

                    main1=obj2.getJSONObject("main");
                    Obj2_maintemp=main1.getDouble("temp")-273.15;
                    z=Obj2_maintemp.intValue();
                    Obj2_maint3mp=z.toString()+"°C";


                    x=main1.getDouble("temp_min")-273.15;
                    xx=x.intValue();
                    y=main1.getDouble("temp_max")-273.15;
                    yy=y.intValue();
                    Obj2_minmax=xx+"°C/"+yy+"°C";
                    Obj2_date=obj2.getString("dt_txt");





                    jsonObject=jsonArray.getJSONObject(17);
                    weather=jsonObject.getJSONArray("weather");
                    obj2=weather.getJSONObject(0);
                    Obj3_icon=obj2.getString("icon");
                    Obj3_status=obj2.getString("main");



                    main1=jsonObject.getJSONObject("main");
                    Obj3_maintemp=main1.getDouble("temp")-273.15;
                    z=Obj3_maintemp.intValue();
                    Obj3_maint3mp=z.toString()+"°C";


                    x=main1.getDouble("temp_min")-273.15;
                    xx=x.intValue();
                    y=main1.getDouble("temp_max")-273.15;
                    yy=y.intValue();
                    Obj3_minmax=xx+"°C/"+yy+"°C";
                    Obj3_date=jsonObject.getString("dt_txt");



                    String ic1="http://openweathermap.org/img/w/"+Obj1_icon+".png";
                    Picasso.with(Main2Activity.this)
                            .load(ic1)
                            .resize(50, 50)
                            .centerCrop()
                            .into(imageView1);

                    textView1.setText(Obj1_status);
                    textView2.setText(Obj1_maint3mp);
                    textView3.setText(Obj1_minmax);
                    textView4.setText(Obj1_date);


                    String ic2="http://openweathermap.org/img/w/"+Obj2_icon+".png";
                    Picasso.with(Main2Activity.this)
                            .load(ic2)
                            .resize(50, 50)
                            .centerCrop()
                            .into(imageView2);

                    textView5.setText(Obj2_status);
                    textView6.setText(Obj2_maint3mp);
                    textView7.setText(Obj2_minmax);
                    textView8.setText(Obj2_date);



                    String ic3="http://openweathermap.org/img/w/"+Obj3_icon+".png";
                    Picasso.with(Main2Activity.this)
                            .load(ic3)
                            .resize(50, 50)
                            .centerCrop()
                            .into(imageView3);

                    textView9.setText(Obj3_status);
                    textView10.setText(Obj3_maint3mp);
                    textView11.setText(Obj3_minmax);
                    textView12.setText(Obj3_date);



                } catch (JSONException e) {
                    Toast.makeText(Main2Activity.this, e.getMessage(), Toast.LENGTH_LONG).show();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NoConnectionError){
                    Toast.makeText(Main2Activity.this,"Please turn on WIFI / Data Connection :( ",Toast.LENGTH_SHORT).show();
                }
            }
        });


        AppController.getInstance().addToRequestQueue(request);
    }







}


