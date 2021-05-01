package com.elevenzon.sqlite;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.elevenzon.sqlite.R;
import com.elevenzon.sqlite.model.shift1;
import com.elevenzon.sqlite.model.staff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ShiftparPeriodMain extends AppCompatActivity {
    private JSONObject aaaaaaaaaa;
    ArrayList<shift1> arrayList=new ArrayList<>();
    ArrayList<staff> staffArrayList=new ArrayList<>();
    HashMap<String,Integer> map = new HashMap<>();
    RecyclerView rv ;

    TextView textView1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiftpar_period_main);
        textView1 = (TextView) findViewById(R.id.shiftparperiodtext);
        Intent intent = getIntent();
        String value = intent.getStringExtra("key"); //if it's a string you stored.
        String value2 = intent.getStringExtra("key2");


        getallstaff(value,value2);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
settext();
            }
        }, 1000);   //5 seconds

    }


    void settext(){
        String d="";
        for (Map.Entry<String,Integer> entry : map.entrySet())
            d+="\n"+ entry.getKey() +" : " + entry.getValue().toString();

        textView1.setText(d);

    }
    private void getallstaff(final String value, final String value2) {

        String updateProductUrl = server.URL_GET_STAFF;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, updateProductUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        staffArrayList.clear();
                        // converting the string to json array object
                        JSONArray array = null;
                        try {
                            array = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {


                            try {
                                aaaaaaaaaa =array.getJSONObject(i);
                                // String ss = aaaaaaaaaa.getString();

                                JSONObject jsonObject = new JSONObject(String.valueOf(aaaaaaaaaa));

                                staff c =  new staff(aaaaaaaaaa.getLong("id"),aaaaaaaaaa.getString("nom"),aaaaaaaaaa.getDouble("salaire"));

                                staffArrayList.add(c);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        Date d1 = null,d2=null;

                        SimpleDateFormat sdformat = new SimpleDateFormat("MM/dd/yy");
                        try {
                            d1 = sdformat.parse(value);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            d2 = sdformat.parse(value2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        while (d2.compareTo(d1) >= 0) {
                            getshift1(sdformat.format(d1));
                            getshift2(sdformat.format(d1));

                            Calendar c = Calendar.getInstance();
                            c.setTime(d1);
                            c.add(Calendar.DATE, 1);
                            d1 = c.getTime();
                        }


                        for (int i = 0; i <arrayList.size() ; i++) {

                            for (int j = 0; j < staffArrayList.size(); j++) {
                                if (arrayList.get(i).getPerso1() ==staffArrayList.get(j).getId())
                                {               if (map.containsKey(staffArrayList.get(j).getNom())) {

                                    map.put(staffArrayList.get(j).getNom(), map.get(staffArrayList.get(j).getNom()) + 1);
                                }
                                else {

                                    map.put(staffArrayList.get(j).getNom(), 1);
                                }   }
                                else if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())

                                {if (map.containsKey(staffArrayList.get(j).getNom())) {

                                    map.put(staffArrayList.get(j).getNom(), map.get(staffArrayList.get(j).getNom()) + 1);
                                }
                                else {

                                    map.put(staffArrayList.get(j).getNom(), 1);
                                }   }
                                else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                {if (map.containsKey(staffArrayList.get(j).getNom())) {

                                    map.put(staffArrayList.get(j).getNom(), map.get(staffArrayList.get(j).getNom()) + 1);
                                }
                                else {
                                    map.put(staffArrayList.get(j).getNom(), 1);
                                }   }
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                {if (map.containsKey(staffArrayList.get(j).getNom())) {

                                    map.put(staffArrayList.get(j).getNom(), map.get(staffArrayList.get(j).getNom()) + 1);
                                }
                                else {

                                    map.put(staffArrayList.get(j).getNom(), 1);
                                }   }
                                else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                {if (map.containsKey(staffArrayList.get(j).getNom())) {

                                    map.put(staffArrayList.get(j).getNom(), map.get(staffArrayList.get(j).getNom()) + 1);
                                }
                                else {

                                    map.put(staffArrayList.get(j).getNom(), 1);
                                }   }
                                else                                if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                {if (map.containsKey(staffArrayList.get(j).getNom())) {

                                    map.put(staffArrayList.get(j).getNom(), map.get(staffArrayList.get(j).getNom()) + 1);
                                }
                                else {

                                    // If char is not present in charCountMap,
                                    // putting this char to charCountMap with 1 as it's value
                                    map.put(staffArrayList.get(j).getNom(), 1);
                                }   }
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                {if (map.containsKey(staffArrayList.get(j).getNom())) {

                                    // If char is present in charCountMap,
                                    // incrementing it's count by 1
                                    map.put(staffArrayList.get(j).getNom(), map.get(staffArrayList.get(j).getNom()) + 1);
                                }
                                else {

                                    // If char is not present in charCountMap,
                                    // putting this char to charCountMap with 1 as it's value
                                    map.put(staffArrayList.get(j).getNom(), 1);
                                }   }
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                {if (map.containsKey(staffArrayList.get(j).getNom())) {

                                    // If char is present in charCountMap,
                                    // incrementing it's count by 1
                                    map.put(staffArrayList.get(j).getNom(), map.get(staffArrayList.get(j).getNom()) + 1);
                                }
                                else {

                                    // If char is not present in charCountMap,
                                    // putting this char to charCountMap with 1 as it's value
                                    map.put(staffArrayList.get(j).getNom(), 1);
                                }   } }}
                        settext();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

                    }
                })
                ;

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);


    }

    private void getshift1(final String value) {


        String updateProductUrl = server.URL_GET_SHIFT1;





        StringRequest stringRequest = new StringRequest(Request.Method.POST, updateProductUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // converting the string to json array object

                        JSONArray array = null;
                        try {
                            array = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {


                            try {
                                aaaaaaaaaa =array.getJSONObject(i);
                                // String ss = aaaaaaaaaa.getString();

                                JSONObject jsonObject = new JSONObject(String.valueOf(aaaaaaaaaa));

                                shift1 c =  new shift1(aaaaaaaaaa.getString("date"),aaaaaaaaaa.getLong("perso1"),aaaaaaaaaa.getLong("perso2"),
                                        aaaaaaaaaa.getLong("perso3"),
                                        aaaaaaaaaa.getLong("perso4"),aaaaaaaaaa.getLong("perso5"),aaaaaaaaaa.getLong("perso6"),aaaaaaaaaa.getLong("perso7"),

                                        aaaaaaaaaa.getLong("perso8"));

                                arrayList.add(c);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                        System.out.println(arrayList.size());

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("date",value.trim());

                return params;
            }
        };
        ;
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }


    private void getshift2(final String value) {


        String updateProductUrl = server.URL_GET_SHIFT2;





        StringRequest stringRequest = new StringRequest(Request.Method.POST, updateProductUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // converting the string to json array object


                        JSONArray array = null;
                        try {
                            array = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {


                            try {
                                aaaaaaaaaa =array.getJSONObject(i);
                                // String ss = aaaaaaaaaa.getString();

                                JSONObject jsonObject = new JSONObject(String.valueOf(aaaaaaaaaa));

                                shift1 c =  new shift1(aaaaaaaaaa.getString("date"),aaaaaaaaaa.getLong("perso1"),aaaaaaaaaa.getLong("perso2"),
                                        aaaaaaaaaa.getLong("perso3"),
                                        aaaaaaaaaa.getLong("perso4"),aaaaaaaaaa.getLong("perso5"),aaaaaaaaaa.getLong("perso6"),aaaaaaaaaa.getLong("perso7"),

                                        aaaaaaaaaa.getLong("perso8"));

                                arrayList.add(c);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                        System.out.println(arrayList.size());

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("date",value.trim());

                return params;
            }
        };
        ;
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }

}