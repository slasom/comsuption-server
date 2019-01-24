package io.swagger.api.models;

import com.consumo.app.appconsumo.services.AplicacionServices;

import java.util.ArrayList;
import java.util.List;

public class Arquitectura {
    private String nombre;
    private ArrayList<CasoDeUso> casoDeUsos;
    private ArrayList<Variable> lVariables;
    private AplicacionServices aplicacionServices;

    List<List<Float>> matrizBateriaAR;
    List<List<Float>> matrizTraficoAR;


    public Arquitectura() {
    }

    public Arquitectura(String nombre) {
        this.nombre = nombre;
        this.casoDeUsos = new ArrayList<>();
        this.lVariables = new ArrayList<>();
        this.aplicacionServices = new AplicacionServices();
        this.matrizBateriaAR = new ArrayList<>();
        this.matrizTraficoAR = new ArrayList<>();
    }



    public void addCU(CasoDeUso cu) {
        casoDeUsos.add(cu);
    }


    public List<List<Float>> getMatrizBateriaAR() {
        return matrizBateriaAR;
    }

    public List<List<Float>> getMatrizTraficoAR() {
        return matrizTraficoAR;
    }

    public ArrayList<CasoDeUso> getCasoDeUsos() {
        return casoDeUsos;
    }

    private void calcularResultadoBateria() {
        float result;
        //Calcular todos los valores

        int sizeX = matrizBateriaAR.size();
        int sizeY = matrizBateriaAR.get(0).size();

        for (int i = 0; i < casoDeUsos.size(); i++) {
            for (int k = 0; k < sizeY; k++) {

                float resultNow = casoDeUsos.get(i).getMatrizBateriaCU().get(sizeX - 1).get(k);
                result = matrizBateriaAR.get(sizeX - 1).get(k);

                matrizBateriaAR.get(sizeX - 1).set(k, result + resultNow);
            }
        }
    }

    private void calcularResultadoTrafico() {
        float result;
        //Calcular todos los valores

        int sizeX = matrizTraficoAR.size();
        int sizeY = matrizTraficoAR.get(0).size();

        for (int i = 0; i < casoDeUsos.size(); i++) {
            for (int j = 0; j < sizeY; j++) {
                    float resultNow = casoDeUsos.get(i).getMatrizTraficoCU().get(sizeX - 1).get(j);
                    result = matrizTraficoAR.get(sizeX - 1).get(j);

                    matrizTraficoAR.get(sizeX - 1).set(j, result + resultNow);
            }
        }
    }



    private void generarMatricesResultado() {
        //Generar numero de filas de la matriz

        int tamX = lVariables.size() + 1;
        int tamY = aplicacionServices.calcularTamanioVariables(lVariables);

        for (int i = 0; i < tamX; i++) {
            matrizBateriaAR.add(new ArrayList<Float>());
            matrizTraficoAR.add(new ArrayList<Float>());
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
                        matrizBateriaAR.get(indice).add(lVariables.get(i).getValores().get(j));
                        matrizTraficoAR.get(indice).add(lVariables.get(i).getValores().get(j));
                    }
                }
            }
            indice++;

        }

        //Inicializar valores de resultados a 0
        if(matrizBateriaAR.get(0).size()!=0) {
            for (int i = 0; i < matrizBateriaAR.get(0).size(); i++) {
                matrizBateriaAR.get(matrizBateriaAR.size() - 1).add(0f);
                matrizTraficoAR.get(matrizTraficoAR.size() - 1).add(0f);
            }
        }else{
            matrizBateriaAR.get(0).add(0f);
            matrizTraficoAR.get(0).add(0f);
        }
    }

    public String mostrarMatrizBateria() {
        String s;
        s="Arquitectura_"+nombre+"_Bateria"+'\n';
        s+=aplicacionServices.mostrarMatriz(matrizBateriaAR,lVariables);

        return s;
    }

    public String mostrarMatrizTrafico() {
        String s;
        s="Arquitectura_"+nombre+"_TraficoDatos"+'\n';
        s+=aplicacionServices.mostrarMatriz(matrizTraficoAR,lVariables);


        return s;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addVariables(ArrayList<Variable> variables) {
        this.lVariables = variables;
    }

    public void calcularArquitectura(){
        generarMatricesResultado();

        calcularCUs();

        calcularResultadoBateria();

        calcularResultadoTrafico();
    }

    private void calcularCUs(){
        for (int i=0; i<casoDeUsos.size();i++){
            casoDeUsos.get(i).calcularCU();
        }
    }

}
