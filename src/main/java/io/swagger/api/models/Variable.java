package io.swagger.api.models;

import java.util.ArrayList;

public class Variable {
    private String nombreVar;
    private ArrayList<Float> valores;




    public Variable(String nVar, ArrayList<Float> valores) {
        this.nombreVar = nVar;
        this.valores = valores;
    }

    public String getNombreVar() {
        return nombreVar;
    }

    public void setNombreVar(String nombreVar) {
        this.nombreVar = nombreVar;
    }

    public ArrayList<Float> getValores() {
        return valores;
    }

    public void setValores(ArrayList<Float> valores) {
        this.valores = valores;
    }

    public int getTamValores() {
        return valores.size();
    }
}
