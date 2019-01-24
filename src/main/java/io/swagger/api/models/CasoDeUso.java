package io.swagger.api.models;

import com.consumo.app.appconsumo.services.AplicacionServices;

import java.util.ArrayList;
import java.util.List;

public class CasoDeUso {

    private String nombreCU;
    private ArrayList<Primitiva> lPrimitivas;
    private ArrayList<Variable> lVariables;

    private List<List<Float>> matrizBateriaCU;
    private List<List<Float>> matrizTraficoCU;

    private ArrayList<Integer> lVarUsadas;

    private AplicacionServices aplicacionServices;


    public CasoDeUso(String nombre) {
        nombreCU = nombre;
        lPrimitivas = new ArrayList<>();
        lVariables = new ArrayList<>();

        matrizBateriaCU = new ArrayList<List<Float>>();
        matrizTraficoCU = new ArrayList<List<Float>>();

        lVarUsadas = new ArrayList<>();

        aplicacionServices = new AplicacionServices();
    }


    private void generarMatrizResultado() {

        //Generar numero de filas de la matriz
        int tamX = lVariables.size() + 1;
        int tamY = aplicacionServices.calcularTamanioVariables(lVariables);

        for (int i = 0; i < tamX; i++) {
            matrizBateriaCU.add(new ArrayList<Float>());
            matrizTraficoCU.add(new ArrayList<Float>());
        }

        //Insertar valores de variables en la matriz
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
                        matrizBateriaCU.get(indice).add(lVariables.get(i).getValores().get(j));
                        matrizTraficoCU.get(indice).add(lVariables.get(i).getValores().get(j));
                    }
                }
            }
            indice++;

        }
        //Inicializacion de la ultima fila (fila de resultados)
        if (matrizBateriaCU.get(0).size() != 0) {
            for (int i = 0; i < matrizBateriaCU.get(0).size(); i++) {
                matrizBateriaCU.get(matrizBateriaCU.size() - 1).add(0f);
                matrizTraficoCU.get(matrizTraficoCU.size() - 1).add(0f);
            }
        } else {
            matrizBateriaCU.get(0).add(0f);
            matrizTraficoCU.get(0).add(0f);
        }

    }

    public void calcularCU() {

        generarMatrizResultado();
        calcularPrimitivas();

        obtenerResultadoBateria();
        obtenerResultadoTrafico();

        calcularResultado();

    }

    private void calcularPrimitivas() {
        for (int i = 0; i < lPrimitivas.size(); i++) {
            lPrimitivas.get(i).calcularPrimitiva();
        }
    }

    //Suma los resultados de las Primitivas que tiene incluidas con respecto al consumo de datos
    private void obtenerResultadoTrafico() {
        float result;

        int sizeX = matrizTraficoCU.size();
        int sizeY = matrizTraficoCU.get(0).size();

        for (int j = 0; j < lPrimitivas.size(); j++) {

            for (int k = 0; k < sizeY; k++) {
                float resultNow = lPrimitivas.get(j).getMatrizTrafico().get(sizeX - 1).get(k);
                result = matrizTraficoCU.get(sizeX - 1).get(k);

                matrizTraficoCU.get(sizeX - 1).set(k, result + resultNow);
            }
        }
    }

    //Suma los resultados de las Primitivas que tiene incluidas con respecto al consumo de bateria
    private void obtenerResultadoBateria() {
        float result;
        //Calcular todos los valores

        int sizeX = matrizBateriaCU.size();
        int sizeY = matrizBateriaCU.get(0).size();


        for (int j = 0; j < lPrimitivas.size(); j++) {

            for (int k = 0; k < sizeY; k++) {
                float resultNow = lPrimitivas.get(j).getMatrizBateria().get(sizeX - 1).get(k);
                result = matrizBateriaCU.get(sizeX - 1).get(k);

                matrizBateriaCU.get(sizeX - 1).set(k, result + resultNow);
            }
        }
    }

    private void calcularResultado() {
        float resB, resT = 1;
        int tamY = matrizBateriaCU.get(0).size();
        int tamX = matrizBateriaCU.size();

        for (int i = 0; i < tamY; i++) {
            resB = matrizBateriaCU.get(tamX - 1).get(i);
            resT = matrizTraficoCU.get(tamX - 1).get(i);

            for (int j = 0; j < lVarUsadas.size(); j++) {
                resB = resB * matrizBateriaCU.get(lVarUsadas.get(j)).get(i);
                resT = resT * matrizTraficoCU.get(lVarUsadas.get(j)).get(i);

            }

            matrizBateriaCU.get(tamX - 1).set(i, resB);
            matrizTraficoCU.get(tamX - 1).set(i, resT);
        }
    }

    public String mostrarMatrizBateria() {
        String s;
        s = "CU_" + nombreCU + "_Bateria" + '\n';
        s += aplicacionServices.mostrarMatriz(matrizBateriaCU, lVariables);
        return s;
    }

    public String mostrarMatrizTrafico() {
        String s;
        s = "CU_" + nombreCU + "_TraficoDatos" + '\n';
        s += aplicacionServices.mostrarMatriz(matrizTraficoCU, lVariables);
        return s;
    }

    public List<List<Float>> getMatrizBateriaCU() {
        return matrizBateriaCU;
    }

    public List<List<Float>> getMatrizTraficoCU() {
        return matrizTraficoCU;
    }

    public void addPrimitiva(Primitiva p) {
        lPrimitivas.add(p);
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


    public void setlVariables(ArrayList<Variable> lVariables) {
        this.lVariables = lVariables;
    }


}
