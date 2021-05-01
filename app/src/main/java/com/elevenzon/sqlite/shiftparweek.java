package com.elevenzon.sqlite;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class shiftparweek extends AppCompatActivity {

    TextView day11 ,day12,day21,day22,day31,day32,day41,day42,day51,day52,day61,day62,day71,day72;
    private JSONObject aaaaaaaaaa;
    ArrayList<shift1> arrayList=new ArrayList<>();
    ArrayList<staff> staffArrayList=new ArrayList<>();
    TextView day1,day2,day3,day4,day5,day6,day7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiftparweek);

        Intent intent = getIntent();
        final String value = intent.getStringExtra("key"); //if it's a string you stored.
        String value2 = intent.getStringExtra("key2");



        day1=(TextView) findViewById(R.id.textViewSun) ;

        day2=(TextView) findViewById(R.id.textViewMon) ;

        day3=(TextView) findViewById(R.id.textViewTue) ;

        day4=(TextView) findViewById(R.id.textViewWed) ;

        day5=(TextView) findViewById(R.id.textViewThu) ;

        day6=(TextView) findViewById(R.id.textViewFri) ;

        day7=(TextView) findViewById(R.id.textViewSat) ;


        day11=(TextView)findViewById(R.id.day11);
        day12=(TextView)findViewById(R.id.day12);

        day21=(TextView)findViewById(R.id.day21);
        day22=(TextView)findViewById(R.id.day22);

        day31=(TextView)findViewById(R.id.day31);
        day32=(TextView)findViewById(R.id.day32);


        day41=(TextView)findViewById(R.id.day41);
        day42=(TextView)findViewById(R.id.day42);

        day51=(TextView)findViewById(R.id.day51);
        day52=(TextView)findViewById(R.id.day52);

        day61=(TextView)findViewById(R.id.day61);
        day62=(TextView)findViewById(R.id.day62);

        day71=(TextView)findViewById(R.id.day71);
        day72=(TextView)findViewById(R.id.day72);
        getallstaff(value,value2);

    }
    private void getallstaff(final String value, String value2) {
        Date d1 = null,d2= null;

        final SimpleDateFormat sdformat = new SimpleDateFormat("MM/dd/yy");
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

        String updateProductUrl = server.URL_GET_STAFF;

        final Date finalD = d1;
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
                        day1.setText(finalD.toString());
                        Calendar c = Calendar.getInstance();
                        getshift11(value);
                        getshift21(value);

                        c.setTime(finalD);

                        day1.setText(sdformat.format(c.getTime()));
                        c.add(Calendar.DATE, 1);
                        getshift12(sdformat.format(c.getTime()));
                        getshift22(sdformat.format(c.getTime()));

                        day2.setText( sdformat.format(c.getTime()));
                        c.add(Calendar.DATE, 1);
                        getshift13(sdformat.format(c.getTime()));
                        getshift23(sdformat.format(c.getTime()));

                        day3.setText( sdformat.format(c.getTime()));
                        c.add(Calendar.DATE, 1);
                        getshift14(sdformat.format(c.getTime()));
                        getshift24(sdformat.format(c.getTime()));

                        day4.setText( sdformat.format(c.getTime()));
                        c.add(Calendar.DATE, 1);
                        getshift15(sdformat.format(c.getTime()));
                        getshift25(sdformat.format(c.getTime()));

                        day5.setText( sdformat.format(c.getTime()));
                        c.add(Calendar.DATE, 1);
                        getshift16(sdformat.format(c.getTime()));
                        getshift26(sdformat.format(c.getTime()));

                        day6.setText( sdformat.format(c.getTime()));
                        c.add(Calendar.DATE, 1);
                        getshift17(sdformat.format(c.getTime()));
                        getshift27(sdformat.format(c.getTime()));

                        day7.setText(sdformat.format(c.getTime()));




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

    private void getshift11(final String value) {


        String updateProductUrl = server.URL_GET_SHIFT1;





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
                               else  if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        day11.setText(s);
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
    private void getshift12(final String value) {


        String updateProductUrl = server.URL_GET_SHIFT1;





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
                             else   if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                               else  if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        day21.setText(s);
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
    private void getshift13(final String value) {


        String updateProductUrl = server.URL_GET_SHIFT1;





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
                               else if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        day31.setText(s);
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
    private void getshift14(final String value) {


        String updateProductUrl = server.URL_GET_SHIFT1;





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
                               else if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        day41.setText(s);
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
    private void getshift15(final String value) {


        String updateProductUrl = server.URL_GET_SHIFT1;





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
                               else if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        day51.setText(s);
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
    private void getshift16(final String value) {


        String updateProductUrl = server.URL_GET_SHIFT1;





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
                                else if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        day61.setText(s);
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
    private void getshift17(final String value) {


        String updateProductUrl = server.URL_GET_SHIFT1;





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
                                else if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        day71.setText(s);
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



    private void getshift21(final String value) {


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
                                else if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                               else  if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        day12.setText(s);
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
    private void getshift22(final String value) {


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
                                else if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        day22.setText(s);
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
    private void getshift23(final String value) {


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
                                else if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        day32.setText(s);
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
    private void getshift24(final String value) {


        String updateProductUrl = server.URL_GET_SHIFT1;





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
                                else if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        day41.setText(s);
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
    private void getshift25(final String value) {


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
                               else if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                               else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        day52.setText(s);
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
    private void getshift26(final String value) {


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
                               else if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        day62.setText(s);
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
    private void getshift27(final String value) {


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
                               else if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                               else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                               else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                               else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                               else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                               else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    s+=" \n "+ staffArrayList.get(j).getNom();
                            }


                        }
                        day72.setText(s);
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