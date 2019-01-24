package io.swagger.api.models;

import java.util.ArrayList;

public class Aplicacion {


    private String nombre;
    private ArrayList<Variable> lVariables;
    private ArrayList<Arquitectura> lArquitecturas;

    public Aplicacion() {

    }

    public Aplicacion(String nombre) {
        this.nombre = nombre;
        this.lVariables = new ArrayList<>();
        this.lArquitecturas = new ArrayList<>();
    }

    public void setlVariables(ArrayList<Variable> lVariables) {
        this.lVariables = lVariables;
    }

    public ArrayList<Variable> getlVariables() {
        return lVariables;
    }

    public void addArquitectura(Arquitectura a){
        lArquitecturas.add(a);
    }

    public ArrayList<Arquitectura> getlArquitecturas() {
        return lArquitecturas;
    }

    public void calcularAplicacion(){
        for(int i=0; i<lArquitecturas.size();i++){
            lArquitecturas.get(i).calcularArquitectura();
        }
    }

}
