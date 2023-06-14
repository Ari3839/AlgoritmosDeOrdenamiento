
package proyectoeda;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
/**
 * Esta clase contiene todos los métodos que comparten los ordenamientos
 * @author Jiménez,Lázaro y Mendoza
 */
public class Utilerias {
    /**
     * Realiza la lectura del nombre del archivo.
     * @return Regrese el nombre ingresado por el usuario.
     */
    public String nombre(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Ingresa el nombre del archivo a ordenar");
        String archivo=sc.nextLine();
        return archivo;
    }
    /**
     * Realiza la lectura del número de claves que contiene el archivo.
     * @return Regresa el número introducido por el usuario.
     */
    public int nClaves() {
    	Scanner sc=new Scanner(System.in);
        System.out.println("Ingresa el numero de claves que contiene el archivo");
        return Integer.parseInt(sc.nextLine());
        
    }
    /**
     * Este crea un archivo bajo el nombre que ingrese el usuario, poseteriormente pregunta el número de claves que se desean almacenar y genera esa cantidad de números aleatorios entre 0 y 9999 con dos decimales. 
     * Cada que se calcula un número aleatorio este es guardado en el archivo creado al inicio del método. 
     */
    public void generarClaves(){
        int n;
        Random rnd=new Random();
        Scanner sc=new Scanner(System.in);
        System.out.println("Ingresa el nombre del archivo que contendra las claves");
        String archivo=sc.nextLine();
        System.out.println("Cuantas claves quieres generar?");
        n=Integer.parseInt(sc.nextLine());
        try{
            FileWriter output= new FileWriter(archivo);
            BufferedWriter ob = new BufferedWriter(output);
            PrintWriter w = new PrintWriter(ob); 
            for(int i=0;i<n;i++){
            	double aux=Double.parseDouble( new DecimalFormat("#.00").format(rnd.nextDouble()*9999));
                System.out.println("["+(i+1)+"]: "+aux);
            	w.append(aux+",");
            }
            w.close();
            ob.close();
            output.close();
            System.out.println("Se genero exitosamente tu archivo");
        } catch(IOException ex){
            System.out.println("Error");
        }
    }
    
}

