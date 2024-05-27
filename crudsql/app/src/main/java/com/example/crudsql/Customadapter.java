package com.example.crudsql;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Customadapter extends RecyclerView.Adapter<Customadapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList a_id, a_nom, a_nume;
    //int position;


    Customadapter( Activity activity, Context context, ArrayList a_id, ArrayList a_nom, ArrayList a_nume){
        this.activity = activity;
        this.context = context;
        this.a_id = a_id;
        this.a_nom = a_nom;
        this.a_nume = a_nume;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //capturamos la posicion
        //this.position = position;

        holder.textView33.setText(String.valueOf(a_id.get(position)));
        holder.textView44.setText(String.valueOf(a_nom.get(position)));
        holder.textView55.setText(String.valueOf(a_nume.get(position)));

        // le damos poder al linearLayout = mainLinearl
        // de que al dar click se active algo
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, update.class);
                intent.putExtra("id",String.valueOf(a_id.get(position)));
                intent.putExtra("nmbre",String.valueOf(a_nom.get(position)));
                intent.putExtra("nump",String.valueOf(a_nume.get(position)));

                //context.startActivity(intent);
                activity.startActivityForResult(intent,1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return a_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView33,textView44, textView55;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView33 = itemView.findViewById(R.id.textView3);
            textView44 = itemView.findViewById(R.id.textView4);
            textView55 = itemView.findViewById(R.id.textView5);
            //llamammos al id que le dimos a todoo el linearlayaout
            linearLayout = itemView.findViewById(R.id.mainLinearl);

        }
    }
}
