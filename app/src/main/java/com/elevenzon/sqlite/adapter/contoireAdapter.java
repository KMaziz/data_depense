package com.elevenzon.sqlite.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.elevenzon.sqlite.R;
import com.elevenzon.sqlite.model.contoire;
import com.elevenzon.sqlite.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class contoireAdapter extends RecyclerView.Adapter<contoireAdapter.viewHolder> {

        Context context;
        Activity activity;
        ArrayList<contoire> arrayList;
final Calendar myCalendar = Calendar.getInstance();

public contoireAdapter(Context context,Activity activity, ArrayList<contoire> arrayList) {
        this.context = context;
        this.activity  = activity ;
        this.arrayList = arrayList;
        }

@Override
public contoireAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes_list, viewGroup, false);
        return new contoireAdapter.viewHolder(view);
        }

@SuppressLint("RecyclerView")
@Override
public void onBindViewHolder(final contoireAdapter.viewHolder holder, final   int position) {
        holder.title.setText(arrayList.get(position).getTitle());
        holder.dd.setText(arrayList.get(position).getDate());
        holder.description.setText(String.valueOf(arrayList.get(position).getPrix()));

        holder.delete.setOnClickListener(new View.OnClickListener() {;
@Override
public void onClick(View v) {
        //deleting note

    new AlertDialog.Builder(activity)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("delete")
            .setMessage("Are you sure you want to delete this ?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String updateProductUrl = server.URL_DELETE_contoire;
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, updateProductUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    arrayList.remove(position);
                                    notifyDataSetChanged();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();

                                }
                            })
                    {
                        @Override
                        protected Map<String, String> getParams(){
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("id",String.valueOf(arrayList.get(position).getID()));
                            return params;
                        }
                    };
                    ;
                    Volley.newRequestQueue(context).add(stringRequest);



                }

            })
            .setNegativeButton("No", null)
            .show();





        }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {;
@Override
public void onClick(View v) {
        //display edit dialog
        showDialog(position);
        }
        });
        }

@Override
public int getItemCount() {
        return arrayList.size();
        }

public class viewHolder extends RecyclerView.ViewHolder {
    TextView title, description , dd;
    ImageView delete, edit;
    public viewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        delete = (ImageView) itemView.findViewById(R.id.delete);
        edit = (ImageView) itemView.findViewById(R.id.edit);
        dd=(TextView) itemView.findViewById(R.id.textViewdate);
    }
}

    public void showDialog(final int pos) {
        final EditText title, des,edittext;
        Button submit;
        final Dialog dialog = new Dialog(activity);
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
                new DatePickerDialog(context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        title = (EditText) dialog.findViewById(R.id.title);
        des = (EditText) dialog.findViewById(R.id.description);
        submit = (Button) dialog.findViewById(R.id.submit);

        title.setText(arrayList.get(pos).getTitle());
        des.setText(String.valueOf(arrayList.get(pos).getPrix()));
        edittext.setText(arrayList.get(pos).getDate());
        submit.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                if (title.getText().toString().isEmpty()) {
                    //  title.setError("Please Enter Title");

                    Toast.makeText(context,"titre fergha",Toast.LENGTH_LONG).show();
                }else if(des.getText().toString().isEmpty()) {
                    //  des.setError("Please Enter price");

                    Toast.makeText(context,"prix fergha",Toast.LENGTH_LONG).show();
                }
                else if(edittext.getText().toString().isEmpty()){
                    Toast.makeText(context,"date fergha",Toast.LENGTH_LONG).show();
                }else {


                    String updateProductUrl = server.URL_UPDATE_contoire;
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, updateProductUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    arrayList.get(pos).setTitle(title.getText().toString());
                                    arrayList.get(pos).setPrix(Double.valueOf(des.getText().toString()));
                                    arrayList.get(pos).setDate(edittext.getText().toString());
                                    dialog.cancel();
                                    //notify list
                                    notifyDataSetChanged();

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();

                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("id", String.valueOf(arrayList.get(pos).getID()));
                            params.put("title", title.getText().toString());
                            params.put("prix", des.getText().toString());
                            params.put("date", edittext.getText().toString());


                            return params;
                        }
                    };
                    ;
                    Volley.newRequestQueue(context).add(stringRequest);

                }

            }
        });
    }

}
