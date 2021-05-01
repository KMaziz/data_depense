package com.elevenzon.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.elevenzon.sqlite.adapter.staffAdapter;
import com.elevenzon.sqlite.model.shift1;
import com.elevenzon.sqlite.model.staff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class shiftperdaymain extends AppCompatActivity {
    private JSONObject aaaaaaaaaa;
    ArrayList<shift1> arrayList1=new ArrayList<>();
    ArrayList<shift1> arrayList=new ArrayList<>();

    ArrayList<staff> staffArrayList=new ArrayList<>();

TextView one,two,three,four;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiftperdaymain);


        Intent intent = getIntent();
        String value = intent.getStringExtra("key"); //if it's a string you stored.
        one = (TextView) findViewById(R.id.textone);
        two = (TextView) findViewById(R.id.texttwo);
        three = (TextView) findViewById(R.id.textthree);
        four = (TextView) findViewById(R.id.textfour);
        one.setText("the staff of the shift number one : ");
        three.setText("the staff of the shift number two : ");

        getallstaff(value);

    }

    private void getallstaff(final String value) {

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



                        getshift1(value);

                        getshift2(value);


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
                        arrayList1.clear();
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

                                arrayList1.add(c);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
String s = "";
                        for (int i = 0; i <arrayList1.size() ; i++) {

                            for (int j = 0; j < staffArrayList.size(); j++) {
                                if (arrayList1.get(i).getPerso1() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                               else if (arrayList1.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                               else if (arrayList1.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList1.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList1.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList1.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList1.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList1.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        two.setText(s);
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
    arrayList.clear();
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
                        String s = "";
                        for (int i = 0; i <arrayList.size() ; i++) {

                            for (int j = 0; j < staffArrayList.size(); j++) {
                                if (arrayList.get(i).getPerso1() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        four.setText(s);
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