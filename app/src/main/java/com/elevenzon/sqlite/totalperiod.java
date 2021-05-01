package com.elevenzon.sqlite;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.elevenzon.sqlite.adapter.NotesAdapter;
import com.elevenzon.sqlite.adapter.chichaAdapter;
import com.elevenzon.sqlite.adapter.contoireAdapter;
import com.elevenzon.sqlite.adapter.stockAdapter;
import com.elevenzon.sqlite.helper.DataBaseHelperStock;
import com.elevenzon.sqlite.helper.DatabaseHelper;
import com.elevenzon.sqlite.helper.DatabaseHelperChicha;
import com.elevenzon.sqlite.model.NoteModel;
import com.elevenzon.sqlite.model.chicha;
import com.elevenzon.sqlite.model.contoire;
import com.elevenzon.sqlite.model.shift1;
import com.elevenzon.sqlite.model.staff;
import com.elevenzon.sqlite.model.stock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class totalperiod extends AppCompatActivity {
    private JSONObject aaaaaaaaaa;
    Button stockbtn,chichabtn,autrebtn,contoirebtn;

    List<chicha> allchicha = new ArrayList<>();
    List<NoteModel> allnote = new ArrayList<>();
    List<stock> allstock = new ArrayList<>();
    List<contoire> allcontoire = new ArrayList<>();

    Calendar c = Calendar.getInstance();

    ArrayList<chicha> chichaList = new ArrayList<>();
    ArrayList<NoteModel> notelist = new ArrayList<>();
    ArrayList<stock> stockList = new ArrayList<>();
    ArrayList<contoire> contoireList = new ArrayList<>();


    double total=0,totalchicha=0,totalautre=0,totalcontoire=0,totalstock=0;
    boolean bool3,bool2,bool1=false;
    TextView textView;


    List<staff> staffArrayList = new ArrayList<>();
    List<shift1> arrayList = new ArrayList<>();
    List<shift1> arrayList1 = new ArrayList<>();

    List<staff> finalliststaff = new ArrayList<>();
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totalperiod);


        Intent intent = getIntent();
        final String value = intent.getStringExtra("key"); //if it's a string you stored.
        final String value2 = intent.getStringExtra("key2");
        textView = (TextView)findViewById(R.id.textViewtotper);

        getallstaff(value,value2);
        textView.setText(" ");

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



        chichabtn=(Button) findViewById(R.id.chichabtnallperiod);
        stockbtn=(Button) findViewById(R.id.stockbtnallperiod);
        autrebtn=(Button) findViewById(R.id.autrebtnallperiod);
        contoirebtn=(Button) findViewById(R.id.contoirebtnallperiod);
        getallstock(d1,d2);
        getallchicha(d1,d2);
        getallautre(d1,d2);
        getallcontoire(d1,d2);


        chichabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(totalperiod.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                dialog.setContentView(R.layout.dialogrv);
                params.copyFrom(dialog.getWindow().getAttributes());
                params.height = WindowManager.LayoutParams.MATCH_PARENT;
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.gravity = Gravity.CENTER;
                dialog.getWindow().setAttributes(params);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                rv= (RecyclerView) dialog.findViewById(R.id.rvdialog);

                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                rv.setItemAnimator(new DefaultItemAnimator());
                chichaAdapter adapter = new chichaAdapter(getApplicationContext(), totalperiod.this, chichaList);
                rv.setAdapter(adapter);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        finish();
                        startActivity(getIntent());
                    }
                });
            }
        });
        autrebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(totalperiod.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                dialog.setContentView(R.layout.dialogrv);
                params.copyFrom(dialog.getWindow().getAttributes());
                params.height = WindowManager.LayoutParams.MATCH_PARENT;
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.gravity = Gravity.CENTER;
                dialog.getWindow().setAttributes(params);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                rv= (RecyclerView) dialog.findViewById(R.id.rvdialog);

                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                rv.setItemAnimator(new DefaultItemAnimator());
                NotesAdapter adapter = new NotesAdapter(getApplicationContext(), totalperiod.this, notelist);
                rv.setAdapter(adapter);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        finish();
                        startActivity(getIntent());
                    }
                });
            }
        });
        stockbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(totalperiod.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                dialog.setContentView(R.layout.dialogrv);
                params.copyFrom(dialog.getWindow().getAttributes());
                params.height = WindowManager.LayoutParams.MATCH_PARENT;
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.gravity = Gravity.CENTER;
                dialog.getWindow().setAttributes(params);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                rv= (RecyclerView) dialog.findViewById(R.id.rvdialog);

                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                rv.setItemAnimator(new DefaultItemAnimator());
                stockAdapter adapter = new stockAdapter(getApplicationContext(), totalperiod.this, stockList);
                rv.setAdapter(adapter);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        finish();
                        startActivity(getIntent());
                    }
                });

            }
        });
        contoirebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(totalperiod.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                dialog.setContentView(R.layout.dialogrv);
                params.copyFrom(dialog.getWindow().getAttributes());
                params.height = WindowManager.LayoutParams.MATCH_PARENT;
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.gravity = Gravity.CENTER;
                dialog.getWindow().setAttributes(params);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                rv= (RecyclerView) dialog.findViewById(R.id.rvdialog);

                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                rv.setItemAnimator(new DefaultItemAnimator());
                contoireAdapter adapter = new contoireAdapter(getApplicationContext(), totalperiod.this, contoireList);
                rv.setAdapter(adapter);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        finish();
                        startActivity(getIntent());
                    }
                });

            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                for (int i = 0; i <finalliststaff.size() ; i++) {
                    total+=finalliststaff.get(i).getSalaire();
                    System.out.println(staffArrayList.size());
                }
                textView.setText("les totale pour la periode de "+value+" a "+value2 +" est : "+total+"dt");

            }
        }, 2500);

    }




    private void getallcontoire(final Date d1, final Date d2) {

        String updateProductUrl = server.URL_GET_contoire;

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, updateProductUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        allcontoire.clear();
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

                                allcontoire.add(c);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }


                        bool2=true;
                        //creating adapter object and setting it to recyclerview
                        SimpleDateFormat sdformat = new SimpleDateFormat("MM/dd/yy");

                        for (int i = 0; i <allcontoire.size() ; i++) {
                            Date dd = null;
                            try {
                                dd =sdformat.parse(allcontoire.get(i).getDate().trim());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            if(dd.compareTo(d1) >= 0 && d2.compareTo(dd)>=0){
                                contoireList.add(allcontoire.get(i));
                                total+=allcontoire.get(i).getPrix();
                                totalcontoire+=allcontoire.get(i).getPrix();


                            }
                        }

                        textView.setText("le totale pour la periode de "+sdformat.format(d1)+" a "+sdformat.format(d2) +" est : "+total+"dt");
                        contoirebtn.setText("contoire "+totalcontoire+"dt");


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


    private void getallautre(final Date d1, final Date d2) {
        String updateProductUrl = server.URL_GET_AUTRE;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, updateProductUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        allnote.clear();
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

                                NoteModel c =  new NoteModel(aaaaaaaaaa.getLong("id"),aaaaaaaaaa.getString("title"),aaaaaaaaaa.getDouble("prix"),
                                        aaaaaaaaaa.getString("date"));

                                allnote.add(c);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        //creating adapter object and setting it to recyclerview
                        SimpleDateFormat sdformat = new SimpleDateFormat("MM/dd/yy");


                        for (int i = 0; i <allnote.size() ; i++) {
                            Date dd = null;
                            try {
                                dd =sdformat.parse(allnote.get(i).getDate().trim());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            if(dd.compareTo(d1) >= 0 && d2.compareTo(dd)>=0){
                                notelist.add(allnote.get(i));
                                total+=allnote.get(i).getPrix();
                                totalautre+=allnote.get(i).getPrix();


                            }
                        }



                        textView.setText("le totale pour la periode de "+sdformat.format(d1)+" a "+sdformat.format(d2) +" est : "+total+"dt");
                        bool3=true;
                        autrebtn.setText("Autre "+totalautre+"dt");

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

    private void getallchicha(final Date d1, final Date d2) {


        String updateProductUrl = server.URL_GET_CHICHA;

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, updateProductUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        allchicha.clear();
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

                                chicha c =  new chicha(aaaaaaaaaa.getLong("id"),aaaaaaaaaa.getString("title"),aaaaaaaaaa.getDouble("prix"),
                                        aaaaaaaaaa.getString("date"));

                                allchicha.add(c);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }


                        bool2=true;
                        //creating adapter object and setting it to recyclerview
                        SimpleDateFormat sdformat = new SimpleDateFormat("MM/dd/yy");

                        for (int i = 0; i <allchicha.size() ; i++) {
                            Date dd = null;
                            try {
                                dd =sdformat.parse(allchicha.get(i).getDate().trim());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            if(dd.compareTo(d1) >= 0 && d2.compareTo(dd)>=0){
                                chichaList.add(allchicha.get(i));
                                total+=allchicha.get(i).getPrix();
                                totalchicha+=allchicha.get(i).getPrix();


                            }
                        }

                        textView.setText("le totale pour la periode de "+sdformat.format(d1)+" a "+sdformat.format(d2) +" est : "+total+"dt");
                        chichabtn.setText("chicha "+totalchicha+"dt");


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

    private void getallstock(final Date d1, final Date d2) {
        String updateProductUrl = server.URL_GET_STOCK;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, updateProductUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        bool1 = true;

                        allstock.clear();
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
                                aaaaaaaaaa = array.getJSONObject(i);
                                // String ss = aaaaaaaaaa.getString();

                                JSONObject jsonObject = new JSONObject(String.valueOf(aaaaaaaaaa));

                                stock c = new stock(aaaaaaaaaa.getLong("id"), aaaaaaaaaa.getString("title"), aaaaaaaaaa.getDouble("prix"),
                                        aaaaaaaaaa.getString("date"));

                                allstock.add(c);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }


                        //creating adapter object and setting it to recyclerview
                        SimpleDateFormat sdformat = new SimpleDateFormat("MM/dd/yy");

                        for (int i = 0; i < allstock.size(); i++) {
                            Date dd = null;
                            try {
                                dd = sdformat.parse(allstock.get(i).getDate().trim());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            if (dd.compareTo(d1) >= 0 && d2.compareTo(dd) >= 0) {

                                stockList.add(allstock.get(i));
                                total += allstock.get(i).getPrix();
                                totalstock+=allstock.get(i).getPrix();
                            }
                        }


                        textView.setText("le totale pour la periode de "+sdformat.format(d1)+" a "+sdformat.format(d2) +" est : "+total+"dt");
                        stockbtn.setText("stock "+ totalstock+"dt");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }


    private void getallstaff(final String value, final String value2) {

        String updateProductUrl = server.URL_GET_STAFF;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, updateProductUrl,
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
                        for (int i = 0; i <arrayList1.size() ; i++) {

                            for (int j = 0; j < staffArrayList.size(); j++) {
                                if (arrayList1.get(i).getPerso1() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                                else if (arrayList1.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                                else if (arrayList1.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                                else if (arrayList1.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                                else if (arrayList1.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                                else if (arrayList1.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                                else if (arrayList1.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                                else  if (arrayList1.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                            }


                        }

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
                        for (int i = 0; i <arrayList.size() ; i++) {

                            for (int j = 0; j < staffArrayList.size(); j++) {
                                if (arrayList.get(i).getPerso1() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                                else if (arrayList.get(i).getPerso2() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                                else if (arrayList.get(i).getPerso3() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                                else if (arrayList.get(i).getPerso4() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                                else if (arrayList.get(i).getPerso5() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                                else if (arrayList.get(i).getPerso6() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                                else if (arrayList.get(i).getPerso7() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                                else if (arrayList.get(i).getPerso8() ==staffArrayList.get(j).getId())
                                    finalliststaff.add(staffArrayList.get(j));
                            }


                        }
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