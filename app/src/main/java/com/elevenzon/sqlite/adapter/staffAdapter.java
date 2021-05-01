package com.elevenzon.sqlite.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
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
import com.elevenzon.sqlite.model.staff;
import com.elevenzon.sqlite.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class staffAdapter extends RecyclerView.Adapter<staffAdapter.viewHolder> {

    Context context;
    Activity activity;
    ArrayList<staff> arrayList;

    public staffAdapter(Context context,Activity activity, ArrayList<staff> arrayList) {
        this.context = context;
        this.activity  = activity ;
        this.arrayList = arrayList;
    }

    @Override
    public staffAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.staff_list, viewGroup, false);
        return new staffAdapter.viewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(final staffAdapter.viewHolder holder, final   int position) {
        holder.title.setText(arrayList.get(position).getNom());
        holder.description.setText(String.valueOf(arrayList.get(position).getSalaire()));

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
                                String updateProductUrl = server.URL_DELETE_STAFF;
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
                                        params.put("id",String.valueOf(arrayList.get(position).getId()));
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
        TextView title, description;
        ImageView delete, edit;
        public viewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titlestaff);
            description = (TextView) itemView.findViewById(R.id.descriptionstaff);
            delete = (ImageView) itemView.findViewById(R.id.deletestaff);
            edit = (ImageView) itemView.findViewById(R.id.editstaff);
        }
    }

    public void showDialog(final int pos) {
        final EditText title, des;
        Button submit;
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        dialog.setContentView(R.layout.dialogstaff);
        params.copyFrom(dialog.getWindow().getAttributes());
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();




        title = (EditText) dialog.findViewById(R.id.nom);
        des = (EditText) dialog.findViewById(R.id.salaireparjour);
        submit = (Button) dialog.findViewById(R.id.submitstaff);

        title.setText(arrayList.get(pos).getNom());
        des.setText(String.valueOf(arrayList.get(pos).getSalaire()));
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
                else {


                    String updateProductUrl = server.URL_UPDATE_STAFF;
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, updateProductUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    arrayList.get(pos).setNom(title.getText().toString());
                                    arrayList.get(pos).setSalaire(Double.valueOf(des.getText().toString()));
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
                            params.put("id", String.valueOf(arrayList.get(pos).getId()));
                            params.put("nom", title.getText().toString());
                            params.put("salaire", des.getText().toString());



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