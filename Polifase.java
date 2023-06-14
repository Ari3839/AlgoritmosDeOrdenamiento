
package proyectoeda;

import java.io.*;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Clase para implementar el ordenamiento externo por el método de Polifase.
 * @author Jiménez,Lázaro y Mendoza
 */
public class Polifase {
    File f,f1,f2,f3;
    LinkedList<double []> bloques1= new LinkedList<>();
    Ordenamiento ord=new Ordenamiento();
    /**
     * En este método se calcula el tamaño de los bloques en los que será dividido el archivo original. También se llama a los métodos que implementan las dos fases de polifase.
     * @param archivo Nombre del archivo a ordenar.
     * @param a Representa el orden de ordenamiento, se ordena ascendentemente si es 1, y descendentemente en caso contrario.
     * @param numC Número de claves que contiene el archivo a ordenar.
     */
    public void empezar(String archivo, int a, int numC){
        boolean fin=false;
        int c=numC/128;
        if(c==0)
            c=1;
        do{
            if(fin==false)
                fin=fase1(archivo,c,a);
            if(fin==false)
                fin=fase2(archivo,c,a);
            c*=2;
        }while(c<numC);
    }  
    /**
     * Realiza la primera parte del algoritmo de ordenamiento por Polifase.
     * @param archivo Nombre del archivo a ordenar.
     * @param c Tamaño de los bloques en los que será dividido el archivo a ordenar.
     * @param a Representa el orden de ordenamiento, se ordena ascendentemente si es 1, y descendentemente en caso contrario.
     * @return Una variable auxiliar para terminar el algoritmo
     */
    public boolean fase1(String archivo,int c, int a){
        int cont=0;
        leerAr(archivo,c);
        leerF3(c);
        ordenarBloques(c,a);
        for(int i=0;i<bloques1.size();i++){
            if(i%2==0){
                guardarF1(bloques1.get(i),c);
            }
            else{
                cont=guardarF2(bloques1.get(i),c);
            }
        }
        System.out.println("\n");
        imprimirArchivo("File1.txt");
        imprimirArchivo("File2.txt");
        bloques1.clear();
        if(cont==0){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Realiza la segunda parte del algoritmo de ordenamiento por Polifase.
     * En esta fase se combinan los bloques generados y guardados en los archivos auxiliares generados en la fase 1.
     * @param archivo Nombre del archivo a ordenar.
     * @param c Tamaño de los bloques en los que será dividido el archivo a ordenar.
     * @param a Representa el orden de ordenamiento, se ordena ascendentemente si es 1, y descendentemente en caso contrario.
     * @return Una variable auxiliar para terminar el algoritmo
     */
    public boolean fase2(String archivo, int c, int a){
        int cont=0;
    	leerF1(c);
        leerF2(c);
        ordenarBloques(c,a);
        for(int i=0;i<bloques1.size();i++){
            if(i%2==0){
                guardarAr(archivo,bloques1.get(i),c);
            }
            else{
                cont=guardarF3(bloques1.get(i),c);
            }
        }
        System.out.println("\n");
        imprimirArchivo(archivo);
        imprimirArchivo("File3.txt");
        bloques1.clear();
        if(cont==0){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Lee las claves contenidas en el archivo auxiliar 1.
     * @param c Tamaño de los bloques en los que será dividido el archivo a ordenar.
     */
    public void leerF1(int c){
        f1=new File("File1.txt");
        StringTokenizer s;
        double numero;
        if(f1.exists()){
            try{
                Scanner entrada = new Scanner(f1);
                while (entrada.hasNext()) {
                    String cadena = entrada.nextLine();
                    s= new StringTokenizer(cadena," ,");
                    while(s.hasMoreTokens()){
                        double [] bloq=new double[c*2];
                        for(int i=0;i<c;i++){
                            try{
                                numero= Double.parseDouble(s.nextToken());
                                bloq[i]=numero; 
                            } catch(NoSuchElementException e){}
                        }
                        bloques1.add(bloq);
                    }
                }
                entrada.close();
                f1.delete();
            } catch(IOException ex) {}
        }
    }
    /**
     * Lee las claves contenidas en el archivo auxiliar 2.
     * @param c Tamaño de los bloques en los que será dividido el archivo a ordenar.
     */
    public void leerF2(int c){
    	f2=new File("File2.txt");
        StringTokenizer s;
        double numero;
        if(f2.exists()){
            try {
                Scanner entrada = new Scanner(f2);
                while (entrada.hasNext()) {
                    String cadena = entrada.nextLine();
                    s= new StringTokenizer(cadena," ,");
                    while(s.hasMoreTokens()){
                        for(double[] bloques1 : bloques1){
                            for(int i=c;i<c*2;i++){
                                try{
                                    numero= Double.parseDouble(s.nextToken());
                                    if(numero!=0){
                                        bloques1[i]=numero;
                                    }
                                } catch(NoSuchElementException e){}
                            } 
                        }
                    }
                }
                entrada.close();
                f2.delete();
            } catch(IOException ex) {}
        }
    }
    /**
     * Lee las claves contenidas en el archivo original.
     * @param archivo Nombre del archivo original ingresado por el usuario.
     * @param c Tamaño de los bloques en los que será dividido el archivo a ordenar.
     */
    public void leerAr(String archivo,int c){
        f=new File(archivo);
        StringTokenizer s;
        double numero;
        if(f.exists()){
            try {
                Scanner entrada = new Scanner(f);
                while (entrada.hasNext()) {
                    String cadena = entrada.nextLine();
                    s= new StringTokenizer(cadena," ,");
                    while(s.hasMoreTokens()){
                        double [] bloq=new double[c*2];
                        for(int i=0;i<c;i++){
                            try{
                                numero= Double.parseDouble(s.nextToken());
                                bloq[i]=numero;
                            } catch(NoSuchElementException e){}
                        }
                        bloques1.add(bloq);
                    }
                }
                entrada.close();
                f.delete();
            } catch(IOException ex) {}
        }
    }
    /**
     * Lee las claves contenidas en el archivo auxiliar 3.
     * @param c Tamaño de los bloques en los que será dividido el archivo a ordenar.
     */
    public void leerF3(int c){
        f3=new File("File3.txt");
        StringTokenizer s;
        double numero;
        if(f3.exists()){
            try {
                Scanner entrada = new Scanner(f3);
                while (entrada.hasNext()) {
                    String cadena = entrada.nextLine();
                    s= new StringTokenizer(cadena," ,");
                    while(s.hasMoreTokens()){
                        for(double[] bloques1 : bloques1){
                            for(int i=c;i<c*2;i++){
                                try{
                                    numero= Double.parseDouble(s.nextToken());
                                    if(numero!=0){
                                        bloques1[i]=numero;
                                    }
                                } catch(NoSuchElementException e){}
                            } 
                        }
                    }
                }
                entrada.close();
                f3.delete();
            } catch(IOException ex) {}
        }
    }
    /**
     * Guarda el contenido de los bloques en el archivo auxiliar 1.
     * @param b Bloque que se desea guardar
     * @param c Tamaño de los bloques en los que será dividido el archivo a ordenar.
     */
    public void guardarF1(double[] b,int c){
    	f1=new File("File1.txt");
        try{
            FileWriter output= new FileWriter(f1.getAbsoluteFile(),true);
            BufferedWriter ob = new BufferedWriter(output);
            PrintWriter w = new PrintWriter(ob);
            for (int j = 0; j<b.length; j++) {
                Double aux=b[j];
                if(aux!=0){
                    w.append(aux+",");
                }
            }
            w.close();
            ob.close();
            output.close();
        }catch(IOException ex){
        	System.out.println("Error al modificar File1");
        }
    }    
    /**
     * Guarda el contenido de los bloques en el archivo auxiliar 2.
     * @param b Bloque que se desea guardar
     * @param c Tamaño de los bloques en los que será dividido el archivo a ordenar.
     * @return Numero de claves guardadas en el archivo
     */
    public int guardarF2(double[] b,int c){
        int cont=0;
        f2=new File("File2.txt");
        try{
            FileWriter output= new FileWriter(f2.getAbsoluteFile(),true);
            BufferedWriter ob = new BufferedWriter(output);
            PrintWriter w = new PrintWriter(ob);
            for (int j = 0; j<b.length; j++) {
            	Double aux=b[j];
                if(aux!=0){
                    w.append(aux+",");
                    cont++;
                }
            }
            w.close();
            ob.close();
            output.close();
        }catch(IOException ex){
        	System.out.println("Error al modificar File2");
        }
        return cont;
    }
    /**
     * Guarda el contenido de las listas de bloques en el archivo original. 
     * @param archivo Nombre del archivo a ordenar
     * @param b Bloque que se desea guardar
     * @param c Tamaño de los bloques en los que será dividido el archivo a ordenar.
     */
    public void guardarAr(String archivo,double[] b,int c){
        f=new File(archivo);
        try{
            FileWriter output= new FileWriter(f.getAbsoluteFile(),true);
            BufferedWriter ob = new BufferedWriter(output);
            PrintWriter w = new PrintWriter(ob);
            for (int j = 0; j<b.length; j++) {
            	Double aux=b[j];
                if(aux!=0){
                    w.append(aux+",");
                }
            }
            w.close();
            ob.close();
            output.close();
        }catch(IOException ex){
        	System.out.println("Error al modificar archivo original");
        }
    }
    /**
     * Guarda el contenido de los bloques en el archivo auxiliar 3.
     * @param b Bloque que se desea guardar
     * @param c Tamaño de los bloques en los que será dividido el archivo a ordenar.
     * @return Numero de claves guardadas en el archivo
     */
    public int guardarF3(double[] b, int c){
        int cont=0;
        f3=new File("File3.txt");
        try{
            FileWriter output= new FileWriter(f3.getAbsoluteFile(),true);
            BufferedWriter ob = new BufferedWriter(output);
            PrintWriter w = new PrintWriter(ob);
            for (int j = 0; j<b.length; j++) {
            	Double aux=b[j];
                if(aux!=0){
                    w.append(aux+",");
                    cont++;
                }
            }
            w.close();
            ob.close();
            output.close();
        }catch(IOException ex){
        	System.out.println("Error al modificar File3");
        }
        return cont;
    }
    /**
     * Realiza el ordenamiento de los bloques generados con ayuda del algoritmo de ordenamiento interno que se encuentra en la clase Utilerias. 
     * @param c Tamaño de los bloques en los que será dividido el archivo a ordenar.
     * @param a Representa el orden de ordenamiento, se ordena ascendentemente si es 1, y descendentemente en caso contrario.
     */
    public void ordenarBloques(int c, int a){
        if(a==1) {
        	for (double[] bloq1 : bloques1) {
                ord.sort(bloq1, 0, bloques1.get(bloques1.size()-1).length-1);
            }
        }else {
        	for (double[] bloq1 : bloques1) {
                ord.sort2(bloq1, 0, bloques1.get(bloques1.size()-1).length-1);
            }
        }  
    }
    /**
     * Imprime en pantalla el contenido del archivo recivido como parámetro 
     * @param archivo Nombre del archivo que se desea imprimir.
     */
    public void imprimirArchivo(String archivo){
        System.out.println("\nArchivo "+archivo);
        File f=new File(archivo);
        StringTokenizer s;
        double numero;
        try{
            Scanner entrada = new Scanner(f);
            while (entrada.hasNext()) {
            	String cadena = entrada.nextLine();
                s= new StringTokenizer(cadena, ",");
                while(s.hasMoreTokens()){
                    try{
                        numero= Double.parseDouble(s.nextToken());
                        if(numero!=0){
                            System.out.print(numero+",");
                        }
                    } catch(NoSuchElementException e){
                    }   
                }
            }
            entrada.close();
            } catch(IOException ex) {}   
    }
}