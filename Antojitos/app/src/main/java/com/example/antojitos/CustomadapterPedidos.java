package com.example.antojitos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomadapterPedidos extends RecyclerView.Adapter<Customadapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList id_menu,id_guisado,cantidad,precio,id_estatus;
    //private ArrayList id_user,id_menu,id_guisado,cantidad,precio,id_estatus;


    //int position;

    CustomadapterPedidos(Context context, ArrayList id_menu, ArrayList id_guisado, ArrayList cantidad, ArrayList precio, ArrayList id_estatus) {
        //CustomadapterPedidos(Context context, ArrayList id_user, ArrayList id_menu, ArrayList id_guisado, ArrayList cantidad, ArrayList precio, ArrayList id_estatus) {
        this.context = context;
        //this.id_user = id_user;
        this.id_menu = id_menu;
        this.id_guisado = id_guisado;
        this.cantidad = cantidad;
        this.precio = precio;
        this.id_estatus = id_estatus;
    }

    @NonNull
    @Override
    public Customadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_ped,parent,false);
        return new Customadapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Customadapter.MyViewHolder holder, int position) {
        //capturamos la posicion
        //this.position = position;
        /*
        id_menu.add(jsonObject.getString("folio_ord"));
        .add(jsonObject.getString("id_estatus"));
        cantidad.add(jsonObject.getString("fechax"));
        precio.add(jsonObject.getString("cantidadx"));
        id_estatus.add(jsonObject.getString("preciovv"));
        */
        holder.textView33.setText(String.valueOf( "¡PEDIDO EN CURSO!" + id_guisado.get(position) ));
        holder.textView44.setText(String.valueOf( "NO.PEDIDO: 2123000" + id_menu.get(position) + " FECHA: " + cantidad.get(position)));
        holder.textView55.setText(String.valueOf("No. DE PIEZAS: " +precio.get(position)));
        holder.textView66.setText(String.valueOf("TOTAL A PAGAR: $" +id_estatus.get(position)));
        holder.foto.setImageResource(R.drawable.logoll);

        // le damos poder al linearLayout = mainLinearl
        // de que al dar click se active algo
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, details.class);
                intent.putExtra("folio",String.valueOf(id_menu.get(position)));
                intent.putExtra("fecha",String.valueOf(cantidad.get(position)));
                intent.putExtra("precio",String.valueOf(id_estatus.get(position)));

                intent.putExtra("estatus",String.valueOf(id_guisado.get(position)));
                intent.putExtra("cantidad",String.valueOf(precio.get(position)));
                intent.putExtra("precio",String.valueOf(id_estatus.get(position)));



                context.startActivity(intent);
                //activity.startActivityForResult(intent,1);


            }
        });
    }


    public int getItemCount() {
        return id_guisado.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView33,textView44, textView55, textView66;
        ImageView foto;

        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView33 = itemView.findViewById(R.id.textView7);
            textView44 = itemView.findViewById(R.id.textView8);
            textView55 = itemView.findViewById(R.id.textView9);
            textView66 = itemView.findViewById(R.id.textView10);

            foto = itemView.findViewById(R.id.imageView2);

            //llamammos al id que le dimos a todoo el linearlayaout
            linearLayout = itemView.findViewById(R.id.mainLinearl);

        }
    }
}
