package com.example.antojitos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder{
        // variables a imprimir en la vista de los datos de my_row
        private TextView id_menu,cantidad,precio,id_estatus;
        ImageView foto;

        //vinculacion la llamada de cada controlador para sus datos
        public ViewHolder(View itemView){
            super(itemView);
            id_menu= (TextView) itemView.findViewById(R.id.textView7);
            cantidad = (TextView) itemView.findViewById(R.id.textView8);
            precio = (TextView) itemView.findViewById(R.id.textView9);
            id_estatus = (TextView) itemView.findViewById(R.id.textView10);
        }
    }

    public List<DatosPreo> DAatosLista;

    public RecyclerViewAdaptor(List<DatosPreo> DAatosLista ){
        this.DAatosLista = DAatosLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // hacwer uso de un layout dentro de otro layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row,parent,false);
        ViewHolder viewHolder = new ViewHolder( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // carga el contenido para cada item
        holder.id_menu.setText(DAatosLista.get(position).getId_user());
        holder.cantidad.setText(DAatosLista.get(position).getId_menu());
        holder.precio.setText(DAatosLista.get(position).getCantidad());
        holder.id_estatus.setText(DAatosLista.get(position).getPrecio());
        holder.foto.setImageResource(DAatosLista.get(position).getImgfoto());
    }

    @Override
    public int getItemCount() {
        return DAatosLista .size();
    }
/*
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView a,b,c,x;
        ImageView piccture;
        public ViewHolder(View itemView){
            super(itemView);
            a = (TextView) itemView.findViewById(R.id.textView7);
            b = (TextView) itemView.findViewById(R.id.textView8);
            c = (TextView) itemView.findViewById(R.id.textView9);
            x = (TextView) itemView.findViewById(R.id.textView10);


        }
    }
*/

/*
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ped,parent,false);
        ViewHolder viewHolder = new ViewHolder( view );

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        holder.a.setText(DAatosLista.get(position).getId_user());
        holder.b.setText(DAatosLista.get(position).getId_menu());
        holder.c.setText(DAatosLista.get(position).getCantidad());
        holder.x.setText(DAatosLista.get(position).getPrecio());
    }


    @Override
    public int getItemCount(){
        return DAatosLista .size();
    }
*/



}
