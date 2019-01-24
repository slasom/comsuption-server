package io.swagger.api.models;

import com.consumo.app.appconsumo.services.AplicacionServices;

import java.util.ArrayList;
import java.util.List;

public class Primitiva {

    private ArrayList<Variable> lVariables;
    ArrayList<Integer> lVarUsadas;

    ArrayList<Operacion> lOperaciones;

    List<List<Float>> matrizBateria = new ArrayList<List<Float>>();
    List<List<Float>> matrizTrafico = new ArrayList<List<Float>>();

    List<List<Float>> matrizInicial = new ArrayList<List<Float>>();

    private float resultBateria;
    private float resultTrafico;
    private String tipo;

    private AplicacionServices aplicacionServices;


    public Primitiva(String tipo) {
        resultBateria = 0;
        resultTrafico = 0;
        lVariables = new ArrayList<>();
        lVarUsadas = new ArrayList<>();
        lOperaciones = new ArrayList<>();
        aplicacionServices = new AplicacionServices();
        this.tipo = tipo;


    }

    public Primitiva() {
        resultBateria = 0;
        resultTrafico = 0;
        lVariables = new ArrayList<>();
        lVarUsadas = new ArrayList<>();
        lOperaciones = new ArrayList<>();
        aplicacionServices = new AplicacionServices();
        this.tipo = "";


    }


    //Calcular los n valores de una operacion
    public void calcularPrimitiva() {

        //Calcular numero de filas
        int tamX = lVariables.size() + 1;
        int tamY = aplicacionServices.calcularTamanioVariables(lVariables);

        //Generar numero de filas de la matriz
        for (int i = 0; i < tamX; i++) {
            matrizBateria.add(new ArrayList<Float>());
            matrizTrafico.add(new ArrayList<Float>());
            matrizInicial.add(new ArrayList<Float>());
        }

        for (int j = 0; j < tamY; j++) {
            matrizInicial.get(tamX - 1).add(0f);
        }

        insercionVariablesMatriz();

        calcularOperaciones();


        //Calculo de resultados con Var Externas
        if (!lVarUsadas.isEmpty()) {
            float resB;
            float resT;
            for (int i = 0; i < tamY; i++) {
                resB = 1;
                resT = 1;

                for (int j = 0; j < lVarUsadas.size(); j++) {
                    resB *= matrizBateria.get(lVarUsadas.get(j)).get(i);
                    resT *= matrizTrafico.get(lVarUsadas.get(j)).get(i);
                }

                matrizBateria.get(tamX - 1).set(i, resB * matrizBateria.get(tamX - 1).get(i));
                matrizTrafico.get(tamX - 1).set(i, resT * matrizTrafico.get(tamX - 1).get(i));

            }
        }
    }

    private void calcularOperaciones() {

        int sizeX = matrizInicial.size();
        int sizeY = matrizInicial.get(0).size();
        float result = 0;

        for (int i = 0; i < lOperaciones.size(); i++) {

            for (int k = 0; k < sizeY; k++) {
                //Calcular la matriz inicial de la operacion antes de obtener los resultados
                lOperaciones.get(i).calcularOperacion();
                float resultNow = lOperaciones.get(i).getMatrizInicial().get(sizeX - 1).get(k);

                result = matrizInicial.get(sizeX - 1).get(k);

                matrizInicial.get(sizeX - 1).set(k, result + resultNow);
            }
        }

        for (int j = 0; j < sizeY; j++) {

            calcularMetodo(matrizInicial.get(sizeX - 1).get(j));

            matrizBateria.get(sizeX - 1).add(resultBateria);
            matrizTrafico.get(sizeX - 1).add(resultTrafico);
        }

    }

    private String calcularMetodo(float size) {
        switch (this.tipo) {
            case "post":
                post(size);
                break;
            case "get":
                get(size);
                break;
            case "store":
                store(size);
                break;
            case "receivePush":
                receivePush();
                break;
            case "getGPS":
                getGPS();
                break;
        }
        return null;
    }

    public void mostrarMatrizBateria() {
        String s = "Primitiva_" + tipo + "_Bateria";
        s += aplicacionServices.mostrarMatriz(matrizBateria, lVariables);
        System.out.println(s);
    }

    public void mostrarMatrizTrafico() {
        String s = "Primitiva_" + tipo + "_Trafico";
        s += aplicacionServices.mostrarMatriz(matrizTrafico, lVariables);
        System.out.println(s);
    }

    private void insercionVariablesMatriz() {

        int tamY = aplicacionServices.calcularTamanioVariables(lVariables);
        ;

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
                        matrizBateria.get(indice).add(lVariables.get(i).getValores().get(j));
                        matrizTrafico.get(indice).add(lVariables.get(i).getValores().get(j));
                        matrizInicial.get(indice).add(lVariables.get(i).getValores().get(j));
                    }
                }
            }
            indice++;
        }
    }


    public boolean addVariable(String nameVar) {
        boolean ins = false;
        for (int i = 0; i < lVariables.size(); i++) {
            if (lVariables.get(i).getNombreVar().equals(nameVar)) {
                lVarUsadas.add(i);
                ins = true;
            }
        }
        return ins;
    }

    public void addOperacion(Operacion o) {
        lOperaciones.add(o);
    }


    //Calculo Base de una operacion primitiva.
    public void store(float size) {
        resultBateria = (0.00002f * size) + 0.5137f;
        resultTrafico = 0;
    }

    public void post(float size) {
        resultBateria = (0.0002f * size) + 17.135f;
        resultTrafico = (1.0581f * size) + 1031f;
    }

    public void get(float size) {
        resultBateria = (0.00005f * size) + 18.539f;
        resultTrafico = (1.0479f * size) + 764.93f;
    }

    public void receivePush() {
        resultBateria = 18.36f;
        resultTrafico = 407f;
    }

    public void getGPS() {
        resultBateria = 7.2f;
        resultTrafico = 0;
    }


    public void setlVariables(ArrayList<Variable> lVariables) {
        this.lVariables = lVariables;
    }

    public List<List<Float>> getMatrizBateria() {
        return matrizBateria;
    }

    public List<List<Float>> getMatrizTrafico() {
        return matrizTrafico;
    }

}
