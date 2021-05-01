package com.elevenzon.sqlite;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.elevenzon.sqlite.model.staff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class staffMain extends AppCompatActivity {
    Button staff,addshift,shiftparday,shiftparweek,shiftparperiod;
    final Calendar myCalendar = Calendar.getInstance();
    private JSONObject aaaaaaaaaa;
    ArrayList<staff> arrayList=new ArrayList<>();
    final Calendar myCalendar2 = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_main);

        staff = (Button) findViewById(R.id.staff);

        addshift = (Button) findViewById(R.id.addshift);

        shiftparday = (Button) findViewById(R.id.shiftparday);

        shiftparweek = (Button) findViewById(R.id.shiftparweek);

        shiftparperiod = (Button) findViewById(R.id.shiftparperiod);

        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(staffMain.this, AllStaffMain.class);
                staffMain.this.startActivity(myIntent);

            }
        });
        addshift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogaddshiftshow();
            }
        });



        shiftparday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog() ;
            }
        });

        shiftparweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogshow2();
            }
        });
        shiftparperiod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogshow3();
            }
        });
    }

    public void dialogaddshiftshow(){

        final EditText edittext;
        final Switch s;
        Button submit;
        final ListView listview ;
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        dialog.setContentView(R.layout.dialogaddshift);
        params.copyFrom(dialog.getWindow().getAttributes());
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();




        edittext = (EditText)dialog.findViewById(R.id.editTextDateshift);
        s=(Switch) dialog.findViewById(R.id.switch1);
        listview = (ListView) dialog.findViewById(R.id.listview);
        submit = (Button) dialog.findViewById(R.id.submitshift);




        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        final List<com.elevenzon.sqlite.model.staff> list = new ArrayList<>();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();
                list.add((com.elevenzon.sqlite.model.staff)listview.getItemAtPosition(position)) ;


            }
        });



        String updateProductUrl = server.URL_GET_STAFF;

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

                                staff c =  new staff(aaaaaaaaaa.getLong("id"),aaaaaaaaaa.getString("nom"),aaaaaaaaaa.getDouble("salaire"));

                                arrayList.add(c);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        //creating adapter object and setting it to recyclerview

                        ArrayAdapter<staff> arrayAdapter
                                = new ArrayAdapter<staff>(getApplicationContext(), android.R.layout.simple_list_item_checked , arrayList);

                        listview.setAdapter(arrayAdapter);

                        for(int i=0;i< arrayList.size(); i++ )  {
                            listview.setItemChecked(i,false);
                        }


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



        s.setTextOff("shift 2 ");
        s.setTextOn("Shift 1");
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.isChecked())
                    s.setText("sbeh");
                else
                    s.setText("la3chya");
            }
        });

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
                new DatePickerDialog(staffMain.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                if (s.isChecked()) {
                    //  title.setError("Please Enter Title");
                    final SparseBooleanArray sp = listview.getCheckedItemPositions();
                    String updateProductUrl = server.URL_ADD_SHIFT1;
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, updateProductUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    dialog.cancel();

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
                            params.put("date", edittext.getText().toString());
                            for (int i = 0; i < sp.size(); i++) {
                                if (sp.valueAt(i) == true) {
                                    staff user = (staff) listview.getItemAtPosition(i);
                                    params.put("perso"+(i+1),String.valueOf( user.getId()));
                                }
                            }

                            for (int i = 0; i <8-sp.size() ; i++) {
                                params.put("perso"+(sp.size()+i+1),String.valueOf(-1));

                            }



                            return params;
                        }
                    };
                    ;
                    Volley.newRequestQueue(getApplicationContext()).add(stringRequest);


                } else {

                    final SparseBooleanArray sp = listview.getCheckedItemPositions();





                    String updateProductUrl = server.URL_ADD_SHIFT2;
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, updateProductUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    dialog.cancel();

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
                            params.put("date", edittext.getText().toString());
                            for (int i = 0; i < sp.size(); i++) {
                                if (sp.valueAt(i) == true) {
                                    staff user = (staff) listview.getItemAtPosition(i);
                                    // Or:
                                    // String s = ((CheckedTextView) listView.getChildAt(i)).getText().toString();
                                    //
                                    params.put("perso"+(i+1),String.valueOf( user.getId()));
                                }
                            }

                            for (int i = 0; i <8-sp.size() ; i++) {
                                params.put("perso"+(sp.size()+i+1),String.valueOf(-1));

                            }



                            return params;
                        }
                    };
                    ;
                    Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

                }
            }  });
    }


    public void showDialog() {
        final  EditText edittext;
        Button submit;
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        dialog.setContentView(R.layout.datepicker);
        params.copyFrom(dialog.getWindow().getAttributes());
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();




        edittext = (EditText)dialog.findViewById(R.id.editpardate);

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
                new DatePickerDialog(staffMain.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        submit = (Button) dialog.findViewById(R.id.submitpardate);
        submit.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                if (edittext.getText().toString().isEmpty()) {
                    //  title.setError("Please Enter Title");

                    Toast.makeText(getBaseContext(),"date fergha",Toast.LENGTH_LONG).show();
                }else {
                    Intent myIntent = new Intent(staffMain.this, shiftperdaymain.class);
                    myIntent.putExtra("key", edittext.getText().toString());

                    staffMain.this.startActivity(myIntent);
                }
            } });
    }
    public void dialogshow2(){
        final  EditText edittext ,edittext2;
        Button submit;
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        dialog.setContentView(R.layout.dialogparperiod);
        params.copyFrom(dialog.getWindow().getAttributes());
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();




        edittext = (EditText)dialog.findViewById(R.id.date1);
        edittext2 = (EditText)dialog.findViewById(R.id.date2);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                edittext.setText(sdf.format(myCalendar.getTime()));
            }

        };

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                edittext2.setText(sdf.format(myCalendar2.getTime()));
            }

        };
        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(staffMain.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edittext2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(staffMain.this, date2, myCalendar2
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        submit = (Button) dialog.findViewById(R.id.submitpardateperiode);
        submit.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                if (edittext.getText().toString().isEmpty() || edittext2.getText().toString().isEmpty()) {
                    //  title.setError("Please Enter Title");

                    Toast.makeText(getBaseContext(),"date fergha",Toast.LENGTH_LONG);
                }else {
                    Intent myIntent = new Intent(staffMain.this, shiftparweek.class);
                    myIntent.putExtra("key", edittext.getText().toString()); //Optional parameters
                    myIntent.putExtra("key2", edittext2.getText().toString()); //Optional parameters

                    staffMain.this.startActivity(myIntent);
                }
            } });

    }

    public void dialogshow3(){
        final  EditText edittext ,edittext2;
        Button submit;
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        dialog.setContentView(R.layout.dialogparperiod);
        params.copyFrom(dialog.getWindow().getAttributes());
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();




        edittext = (EditText)dialog.findViewById(R.id.date1);
        edittext2 = (EditText)dialog.findViewById(R.id.date2);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                edittext.setText(sdf.format(myCalendar.getTime()));
            }

        };

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                edittext2.setText(sdf.format(myCalendar2.getTime()));
            }

        };
        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(staffMain.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edittext2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(staffMain.this, date2, myCalendar2
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        submit = (Button) dialog.findViewById(R.id.submitpardateperiode);
        submit.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                if (edittext.getText().toString().isEmpty() || edittext2.getText().toString().isEmpty()) {
                    //  title.setError("Please Enter Title");

                    Toast.makeText(getBaseContext(),"date fergha",Toast.LENGTH_LONG);
                }else {
                    Intent myIntent = new Intent(staffMain.this, ShiftparPeriodMain.class);
                    myIntent.putExtra("key", edittext.getText().toString()); //Optional parameters
                    myIntent.putExtra("key2", edittext2.getText().toString()); //Optional parameters

                    staffMain.this.startActivity(myIntent);
                }
            } });

    }

}