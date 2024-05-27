package com.example.antojitos;

public class DatosPreo {


    public Integer id_user;
    public Integer id_menu;
    public Integer id_guisado;
    public Integer cantidad;
    public String precio;
    public Integer id_estatus;
    private int imgfoto;

    public DatosPreo(int anInt){

    }

    public DatosPreo(int iduser, int idmenu, int idguisado, int cantida, String pres, int idestatu, int imgfoto){
        this.id_user = iduser;
        this.id_menu = idmenu;
        this.id_guisado = idguisado;
        this.cantidad = cantida;
        this.precio = pres;
        this.id_estatus = idestatu;
        this.imgfoto = imgfoto;
    }

    public int getImgfoto() {
        return imgfoto;
    }

    public void setImgfoto(int imgfoto) {
        this.imgfoto = imgfoto;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_menu() {
        return id_menu;
    }

    public void setId_menu(Integer id_menu) {
        this.id_menu = id_menu;
    }

    public Integer getId_guisado() {
        return id_guisado;
    }

    public void setId_guisado(Integer id_guisado) {
        this.id_guisado = id_guisado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Integer getId_estatus() {
        return id_estatus;
    }

    public void setId_estatus(Integer id_estatus) {
        this.id_estatus = id_estatus;
    }
}
