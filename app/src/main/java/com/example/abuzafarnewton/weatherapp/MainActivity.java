package com.example.abuzafarnewton.weatherapp;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button button1,button2;
    ImageView imageView;
    AutoCompleteTextView autoCompleteTextView;
    TextView textView2,textView3,textView4,textView5;
    String[] cityNames={"Dhaka","Khulna","Rajshahi","Barisal","Chittagong","Sylhet","Rangpur","California","Athens"};
    Integer[] cityId={1337179,1336135,1185128,1336137,1205733,1477362,1185188,5332921,8131587};
    ArrayAdapter adapter;
    Boolean isTrue;
    String selectedCity,Icon,CityAndCountry,WeatherStatus,MainTemp;
    Integer arrayIndex;
    JSONObject jsonObject,job,obj2;


    //JSON Variables api_key=eeabbb9594ead89907bf75fc219e0084//"http://api.openweathermap.org/data/2.5/weather?q=Dhaka&appid=eeabbb9594ead89907bf75fc219e0084";
    String part1="http://api.openweathermap.org/data/2.5/weather?q=";
    String part3="&appid=eeabbb9594ead89907bf75fc219e0084";
    String APIUrl,city_id,iconURL;
    Double temp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Components for java  <start>
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        imageView=(ImageView) findViewById(R.id.imageView);
        autoCompleteTextView=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        textView2=(TextView) findViewById(R.id.textView2);
        textView3=(TextView) findViewById(R.id.textView3);
        textView4=(TextView) findViewById(R.id.textView4);
        textView5=(TextView) findViewById(R.id.textView5);
        //<end>



        //AutoComplete Adapter Creation for autoCompleteTextView<start>
        adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,cityNames);
        autoCompleteTextView.setAdapter(adapter);
        //<end>


        //Get City Name from List and Avoid error

    }






    public void btn_find(View view) {
        isTrue=Boolean.FALSE;  //Flag to check wheather this city is in the list or not
        selectedCity=autoCompleteTextView.getText().toString(); //Get input/city name
        for(int i=0;i<cityNames.length;i++){  //Checking entered cityname in the list
            if(selectedCity.matches(cityNames[i])){//if we found the city , it returns its index
                isTrue=Boolean.TRUE;
                arrayIndex=i;
                break;
            }
        }
        if(isTrue){

            APIUrl=part1+cityNames[arrayIndex]+part3;

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, APIUrl, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {


                        JSONArray jsonArray=response.getJSONArray("weather");
                        jsonObject=jsonArray.getJSONObject(0);
                        Icon=jsonObject.getString("icon");
                        WeatherStatus=jsonObject.getString("main");
                        job=response.getJSONObject("main");
                        Double temp=job.getDouble("temp");
                        temp1=temp-273.15;
                        temp1*=100;
                        int a=temp1.intValue();
                        temp1=a/100.00;
                        MainTemp=temp1.toString();
                        obj2=response.getJSONObject("sys");
                        String country=obj2.getString("country");
                        CityAndCountry=response.getString("name")+","+country;


                        textView2.setText(CityAndCountry);
                        String te=MainTemp.toString()+"°C";//MainTemp
                        textView3.setText(te);
                        String te1=te+"/"+te;
                        textView4.setText(te1);
                        textView5.setText(WeatherStatus);

                        iconURL="http://openweathermap.org/img/w/"+Icon+".png";
                        Picasso.with(MainActivity.this)
                                .load(iconURL)
                                .resize(50, 50)
                                .centerCrop()
                                .into(imageView);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            AppController.getInstance().addToRequestQueue(request);
        }





        //API call and getting DATA






        else Toast.makeText(this,"No such city in our list :(  ",Toast.LENGTH_LONG).show();
    }

    public void forecast(View view) {

        //CityAndCountry
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        String url1="http://api.openweathermap.org/data/2.5/forecast?q="+CityAndCountry+"&appid=eeabbb9594ead89907bf75fc219e0084";
        intent.putExtra("targetURL",url1);
        startActivity(intent);


    }
}
