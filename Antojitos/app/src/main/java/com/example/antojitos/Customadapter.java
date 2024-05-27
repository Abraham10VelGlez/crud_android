package com.example.antojitos;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

        public class Customadapter extends RecyclerView.Adapter<Customadapter.MyViewHolder> {

            private Context context;
            Activity activity;
            private ArrayList id_menu,id_guisado,id_guisado2,cantidad,precio,id_estatus,id_user,id;
            //private ArrayList id_user,id_menu,id_guisado,cantidad,precio,id_estatus;


            //int position;

            Customadapter(Context context, ArrayList id_menu, ArrayList id_guisado,ArrayList id_guisado2, ArrayList cantidad, ArrayList precio, ArrayList id_estatus,ArrayList id_user, ArrayList id) {
                //Customadapter(Context context, ArrayList id_user, ArrayList id_menu, ArrayList id_guisado, ArrayList cantidad, ArrayList precio, ArrayList id_estatus) {
                this.context = context;
                this.id_menu = id_menu;
                this.id_guisado = id_guisado;
                this.id_guisado2 = id_guisado2;
                this.cantidad = cantidad;
                this.precio = precio;
                this.id_estatus = id_estatus;
                this.id_user = id_user;
                this.id = id;
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

                holder.textView33.setText(String.valueOf( "¡PEDIDO AGREGADO!"));
                holder.textView44.setText(String.valueOf( "MENU: " + id_menu.get(position) +" GUISADO: " + id_guisado.get(position) + " " + id_guisado2.get(position)));
                holder.textView55.setText(String.valueOf("No. DE PIEZAS: " +cantidad.get(position)));
                holder.textView66.setText(String.valueOf(id_estatus.get(position)));
                holder.foto.setImageResource(R.drawable.logoll);

        /*
        holder.textView33.setText(String.valueOf( "hola" + id_user.get(position)));
        holder.textView44.setText(String.valueOf(cantidad.get(position)));
        holder.textView55.setText(String.valueOf(precio.get(position)));
        holder.foto.setImageResource(R.drawable.logoll);
        * */


        // le damos poder al linearLayout = mainLinearl
        // de que al dar click se active algo
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


/*
                Intent intent = new Intent(context, details.class);
                intent.putExtra("id",String.valueOf(id_user.get(position)));
                intent.putExtra("menu",String.valueOf(id_menu.get(position)));
                intent.putExtra("guisado",String.valueOf(id_guisado.get(position)));

                //context.startActivity(intent);
                activity.startActivityForResult(intent,1);
*/

            }
        });

    }

    @Override
    public int getItemCount() {
        return id_guisado.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
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
