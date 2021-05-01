package com.elevenzon.sqlite;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.elevenzon.sqlite.adapter.contoireAdapter;

import com.elevenzon.sqlite.model.contoire;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ContoireMain extends AppCompatActivity {
        ArrayList<contoire> arrayList=new ArrayList<>();
        RecyclerView recyclerView;
        FloatingActionButton actionButton;


        final Calendar myCalendar = Calendar.getInstance();
        private JSONObject aaaaaaaaaa;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_contoire_main);


            recyclerView = (RecyclerView) findViewById(R.id.recycler_viewcontoire);
            actionButton = (FloatingActionButton) findViewById(R.id.addcontoire);
            displayNotes();

            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog();
                }
            });
        }
        //display notes list
        public void displayNotes() {

            String updateProductUrl = server.URL_GET_contoire;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, updateProductUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            arrayList.clear();
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

                                    contoire c =  new contoire(aaaaaaaaaa.getLong("id"),aaaaaaaaaa.getString("title"),aaaaaaaaaa.getDouble("prix"),
                                            aaaaaaaaaa.getString("date"));

                                    arrayList.add(c);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }

                            //creating adapter object and setting it to recyclerview

                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            contoireAdapter adapter = new contoireAdapter(getApplicationContext(),ContoireMain.this, arrayList);
                            recyclerView.setAdapter(adapter);

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

        //display dialog
        public void showDialog() {
            final EditText title, des,edittext;
            Button submit;
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            dialog.setContentView(R.layout.dialog);
            params.copyFrom(dialog.getWindow().getAttributes());
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.gravity = Gravity.CENTER;
            dialog.getWindow().setAttributes(params);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();




            edittext = (EditText)dialog.findViewById(R.id.editTextDate);

            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {


                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String myFormat = "MM/dd/yy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                    edittext.setText(sdf.format(myCalendar.getTime()));
                }

            };

            edittext.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    new DatePickerDialog(ContoireMain.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });


            title = (EditText) dialog.findViewById(R.id.title);
            des = (EditText) dialog.findViewById(R.id.description);
            submit = (Button) dialog.findViewById(R.id.submit);
            submit.setOnClickListener(new View.OnClickListener() {;
                @Override
                public void onClick(View v) {
                    if (title.getText().toString().isEmpty()) {
                        //  title.setError("Please Enter Title");

                        Toast.makeText(getApplicationContext(),"titre fergha",Toast.LENGTH_LONG).show();
                    }else if(des.getText().toString().isEmpty()) {
                        //  des.setError("Please Enter price");

                        Toast.makeText(getApplicationContext(),"prix fergha",Toast.LENGTH_LONG).show();
                    }
                    else if(edittext.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(),"date fergha",Toast.LENGTH_LONG).show();
                    }else {
                        String updateProductUrl = server.URL_ADD_contoire;
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, updateProductUrl,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        dialog.cancel();
                                        displayNotes();
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
                                params.put("title",title.getText().toString());
                                params.put("prix",des.getText().toString());
                                params.put("date",edittext.getText().toString());
                                return params;
                            }
                        };
                        ;
                        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);




                    }
                } });
        }
    }