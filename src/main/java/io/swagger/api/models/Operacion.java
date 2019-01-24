package io.swagger.api.models;

import com.consumo.app.appconsumo.services.AplicacionServices;

import java.util.ArrayList;
import java.util.List;

public class Operacion {

    private ArrayList<Variable> lVariables;
    ArrayList<Integer> lVarUsadas;


    List<List<Float>> matrizInicial = new ArrayList<List<Float>>();


    private AplicacionServices aplicacionServices;

    private float resultado;
    private float resultTrafico;
    private float size;
    private int tamY;
    private int tamX;

    public Operacion(float size) {
        lVariables = new ArrayList<>();
        lVarUsadas = new ArrayList<>();
        aplicacionServices = new AplicacionServices();
        this.size = size;
    }

    public void setlVariables(ArrayList<Variable> lVariables) {
        this.lVariables = lVariables;
    }

    //Calcular los n valores de una operacion
    public int calcularTamanioVariables() {
        int tam = 1;
        for (int i = 0; i < lVariables.size(); i++) {
            tam *= lVariables.get(i).getTamValores();
        }
        return tam;
    }

    public int calcularTamX() {
        return lVariables.size();
    }

    public boolean addVariable(String nameVar) {
        boolean ins=false;
        for (int i = 0; i < lVariables.size(); i++) {
            if (lVariables.get(i).getNombreVar().equals(nameVar)) {
                lVarUsadas.add(i);
                ins=true;
            }
        }
        return ins;
    }

    private void insercionVariablesMatriz() {

        tamY = calcularTamanioVariables();
        tamX = calcularTamX() + 1;

        //Generar numero de filas de la matriz
        for (int i = 0; i < tamX; i++) {
            matrizInicial.add(new ArrayList<Float>());
        }

        //Relleno de las matrices con valores de las variables
        boolean insercionB = false;
        int indice = 0;
        int div = tamY;
        for (int i = 0; i < lVariables.size(); i++) {
            //Insercion de manera que combinen todas las posiblidades
            int tam = lVariables.get(i).getValores().size();
            div = div / tam;
            for (int l = 0; l < tamY / (div * tam); l++) {
                for (int j = 0; j < lVariables.get(i).getValores().size(); j++) {
                    for (int k = 0; k < div; k++) {
                        //Add Valor
                        matrizInicial.get(indice).add(lVariables.get(i).getValores().get(j));
                    }
                }
            }
            indice++;
        }
    }

    public void calcularOperacion() {
        insercionVariablesMatriz();

        for (int i = 0; i < tamY; i++) {
            resultado = size;

            for (int j = 0; j < lVarUsadas.size(); j++) {
                resultado *= matrizInicial.get(lVarUsadas.get(j)).get(i);

            }
            matrizInicial.get(tamX - 1).add(resultado);
        }
    }

    public List<List<Float>> getMatrizInicial() {
        return matrizInicial;
    }
}
