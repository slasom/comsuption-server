package io.swagger.api.services;


import io.swagger.api.models.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AplicacionServices {



    public String calculate(String app) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = (JSONObject) parser.parse(app);
        JSONObject jsonObject = (JSONObject) obj;

        //Lista Arquitecturas
        JSONArray lAr;
        //Objeto Arquitecturas
        JSONObject arquitectura;

        //Lista Casos de Usos
        JSONArray casoDeUsos;
        //Objeto Caso de Uso
        JSONObject cu;
        //Lista lVar Usadas
        JSONArray lVarUsadasCU;


        //Lista Primitivas
        JSONArray lPrimitivas;
        //Objeto Primitiva
        JSONObject primitiva;
        //Lista lVar Externas Primitivas
        JSONArray lVarUsadaPri;


        //Operaciones
        JSONArray lOperaciones;
        JSONObject operacion;
        JSONArray lVarUsadasOp;

        ArrayList<Variable> lVariables = new ArrayList<>();
        ArrayList<String> arquitecturas = new ArrayList<>();


        try {
            //Nombre APP
            Aplicacion APP = new Aplicacion((String) jsonObject.get("nombre"));

            //Lista de Arquitecturas para mostrar
            JSONArray arq = (JSONArray) jsonObject.get("arquitecturas");
            for (int i = 0; i < arq.size(); i++) {
                arquitecturas.add((String) arq.get(i));
            }


            //Parsear Lista Variables
            JSONArray lVar = (JSONArray) jsonObject.get("lVariables");

            for (int i = 0; i < lVar.size(); i++) {
                JSONObject variable = (JSONObject) lVar.get(i);

                ArrayList<Float> valoresVar = new ArrayList<>();
                JSONArray valores = (JSONArray) variable.get("valores");
                for (int j = 0; j < valores.size(); j++) {
                    //Parseo para leer los numeros decimales
                    Double b = Double.parseDouble(valores.get(j).toString());
                    float n = BigDecimal.valueOf(b).floatValue();
                    valoresVar.add(n);
                }
                lVariables.add(new Variable((String) variable.get("nombreVar"), valoresVar));
            }

            APP.setlVariables(lVariables);

            //Lista Arquitecturas
            lAr = (JSONArray) jsonObject.get("lArquitecturas");


            for (int i = 0; i < lAr.size(); i++) {
                arquitectura = (JSONObject) lAr.get(i);
                if (arquitecturas.contains(arquitectura.get("nombre"))) {

                    //Nombre Arquitectura
                    Arquitectura a = new Arquitectura((String) arquitectura.get("nombre"));
                    a.addVariables(lVariables);

                    casoDeUsos = (JSONArray) arquitectura.get("casoDeUsos");
                    for (int j = 0; j < casoDeUsos.size(); j++) {
                        cu = (JSONObject) casoDeUsos.get(j);

                        //NombreCU
                        CasoDeUso c = new CasoDeUso((String) cu.get("nombreCU"));
                        c.setlVariables(lVariables);

                        //lVarUsadasCU
                        lVarUsadasCU = (JSONArray) cu.get("lVarUsadas");
                        for (int k = 0; k < lVarUsadasCU.size(); k++) {
                            String varUsadaCU = (String) lVarUsadasCU.get(k);
                            if(!c.addVariable(varUsadaCU))
                                return "La variable "+varUsadaCU+" no esta en la lista general de variables.";
                        }

                        //Primitivas
                        lPrimitivas = (JSONArray) cu.get("lPrimitivas");
                        for (int l = 0; l < lPrimitivas.size(); l++) {
                            primitiva = (JSONObject) lPrimitivas.get(l);

                            if(!comprobarTipo((String) primitiva.get("tipo"))){
                                return "El metodo "+ primitiva.get("tipo")+ " no existe.";
                            }
                            Primitiva p = new Primitiva((String) primitiva.get("tipo"));
                            p.setlVariables(lVariables);

                            lVarUsadaPri = (JSONArray) primitiva.get("lVarUsadas");
                            for (int m = 0; m < lVarUsadaPri.size(); m++) {
                                String varUsada = (String) lVarUsadaPri.get(m);
                                if(!p.addVariable(varUsada))
                                    return "La variable "+varUsada+" no esta en la lista general de variables.";
                            }

                            lOperaciones = (JSONArray) primitiva.get("lOperaciones");
                            for(int m=0; m<lOperaciones.size();m++){

                                operacion = (JSONObject) lOperaciones.get(m);

                                Double sizeB = Double.parseDouble(operacion.get("size").toString());
                                float size = BigDecimal.valueOf(sizeB).floatValue();

                                Operacion o = new Operacion(size);
                                o.setlVariables(lVariables);

                                lVarUsadasOp = (JSONArray) operacion.get("lVarUsadas");
                                for (int q = 0; q < lVarUsadasOp.size(); q++) {
                                    String varUsada = (String) lVarUsadasOp.get(q);
                                    if(!o.addVariable(varUsada))
                                        return "La variable "+varUsada+" no esta en la lista general de variables.";
                                }

                                p.addOperacion(o);
                            }
                            c.addPrimitiva(p);
                        }
                        a.addCU(c);
                    }
                    APP.addArquitectura(a);
                }
            }

            if (APP.getlArquitecturas().size() > 0) {
                APP.calcularAplicacion();
                return generarResultado(APP);
            }
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();

        }

        return "";
    }

    private boolean comprobarTipo(String tipo) {

        switch (tipo) {
            case "post":
                return true;

            case "get":
                return true;

            case "store":
                return true;

            case "receivePush":
                return true;

            case "getGPS":
                return true;

        }

        return false;
    }

    public String generarResultado(Aplicacion app) {
        String result="";
        for (int i = 0; i < app.getlArquitecturas().size(); i++) {
            String r = "";
            r = app.getlArquitecturas().get(i).mostrarMatrizBateria();
            r = r + app.getlArquitecturas().get(i).mostrarMatrizTrafico();

            for (int j = 0; j < app.getlArquitecturas().get(i).getCasoDeUsos().size(); j++) {
                r = r + app.getlArquitecturas().get(i).getCasoDeUsos().get(j).mostrarMatrizBateria();
                r = r + app.getlArquitecturas().get(i).getCasoDeUsos().get(j).mostrarMatrizTrafico();
            }
            result+=r+"\n";

        }
        System.out.println(result);
        return result;
    }

    public String mostrarMatriz(List<List<Float>> matriz, ArrayList<Variable> lVariables){
        String s="";

        for(int i=0; i< lVariables.size();i++){

            if(i==lVariables.size()-1)
                s+=lVariables.get(i).getNombreVar()+",Resultado\n";
            else
                s+=lVariables.get(i).getNombreVar()+",";
        }


        for (int j = 0; j < matriz.get(0).size(); j++) {
            for (int i = 0; i < matriz.size(); i++) {
                if(i== matriz.size()-1)
                    s=s+matriz.get(i).get(j);
                else
                    s=s+matriz.get(i).get(j) + ",";
            }

            s=s+'\n';
        }

        return s;
    }

    public int calcularTamanioVariables(ArrayList<Variable> lVariables){
        int tam = 1;
        for (int i = 0; i < lVariables.size(); i++) {
            tam *= lVariables.get(i).getTamValores();
        }

        return tam;
    }

}
