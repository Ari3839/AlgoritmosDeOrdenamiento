package proyectoeda;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Esta clase contiene todos los métodos necesarios para realizar el ordenamiento externo por distribución(Radix)
 * @author Jiménez,Lázaro y Mendoza
 */
public class Radix {
    File f0=new File("f0.txt");
    File f1=new File("f1.txt");
    File f2=new File("f2.txt");
    File f3=new File("f3.txt");
    File f4=new File("f4.txt");
    File f5=new File("f5.txt");
    File f6=new File("f6.txt");
    File f7=new File("f7.txt");
    File f8=new File("f8.txt");
    File f9=new File("f9.txt");
    /**
     * Este método realiza las iteraciones necesarias para ordenar un conjunto de n cifras de hasta cuatro digitos enteros y dos decimales
     * @param archivo Nombre del archivo a ordenar.
     * @param nClaves Número de claves que contiene el archivo a ordenar.
     * @param a Representa el orden de ordenamiento, se ordena ascendentemente si es 1, y descendentemente en caso contrario.
     */
    public void radixExterno(String archivo, int nClaves, int a){
    	for(int i=1;i<7;i++){
            leer(archivo,i,nClaves, a);
            System.out.println("Iteracion:"+i);
            imprimirIteracion(archivo);
	    }
	}
    /**
     * Se realiza la lectura del archivo original. Cada que se lee un número se determina en qué archivo auxiliar será almacenado de acuerdo con el valor del dígito analizado. Al finalizar la lectura y asignacion de archivos, primero se elimina el archivo original, luego se crea de nuevo y dentro se guarda el contenido de los archivos auxiliares, de acuerdo con el orden de ordenamiento. Finalmente los archivo auxiliares también son eliminados.     * @param archivo El nombre del archivo a ordenar.
     * @param archivo Nombre del archivo a ordenar
     * @param i Número de iteración en el que se encuntre el método radixExterno(...). Es utilizado para calcular una constante k que nos servira para obtener el archivo donde deberá ser almacenda la cifra leída.
     * @param nClaves Número de claves que contiene el archivo a ordenar.
     * @param a Representa el orden de ordenamiento, se ordena ascendentemente si es 1, y descendentemente en caso contrario.
     */
    public void leer (String archivo, int i, int nClaves, int a){
    	File f=new File(archivo);
    	StringTokenizer s;
    	try {
            Scanner entrada = new Scanner(f);
            while (entrada.hasNext()) {
                String cadena = entrada.nextLine();
	        s= new StringTokenizer(cadena,",");
	        while(s.hasMoreTokens()){
	            double numero = Double.parseDouble(s.nextToken());
	            int numSD=(int)(numero*100);
	            int k = (int)Math.pow(10,i-1);
                    Integer tmp = numSD/k;
                    Integer mod = tmp %10;
	            asignarArchivo(numero,mod);
	        }
	    }
            entrada.close();
            elimiarArchivo(archivo);
        } catch(IOException ex) {
	    System.out.println("Error en la lectura del archivo original y asignacion en los archivos auxiliares");
	}
        if(a==1){
            guardarArAs(archivo);
        }else{                              
            guardarArDes(archivo);
        }
        elimiarArchivo("f0.txt");
        elimiarArchivo("f1.txt");
        elimiarArchivo("f2.txt");
        elimiarArchivo("f3.txt");
        elimiarArchivo("f4.txt");
        elimiarArchivo("f5.txt");
        elimiarArchivo("f6.txt");
        elimiarArchivo("f7.txt");
        elimiarArchivo("f8.txt");
        elimiarArchivo("f9.txt");              
    }
    /**
     * Guarda en orden ascendente el contenido de los archivos auxiliares en el archivo original. Realiza la lectura y guardado de los archivos auxiliares desde f0 hasta f9. Si algún archivo no existe, no se realiza la lectura del mismo. 
     * @param archivo Nombre del archivo original.
     */
    public void guardarArAs(String archivo) {
        try{
            FileWriter output= new FileWriter(archivo);
            BufferedWriter ob = new BufferedWriter(output);
            PrintWriter w = new PrintWriter(ob);
            if(f0.exists())
                leerArAux("f0.txt", archivo);
            else
                System.out.println("Archivo f0.txt vacio");
            if(f1.exists())
                leerArAux("f1.txt", archivo);
            else
                System.out.println("Archivo f1.txt vacio");
            if(f2.exists())
                leerArAux("f2.txt", archivo);
            else
                System.out.println("Archivo f2.txt vacio");
            if(f3.exists())
                leerArAux("f3.txt", archivo);
            else
                System.out.println("Archivo f3.txt vacio");
            if(f4.exists())
                leerArAux("f4.txt", archivo);
            else
                System.out.println("Archivo f4.txt vacio");
            if(f5.exists())
                leerArAux("f5.txt", archivo);
            else
                System.out.println("Archivo f5.txt vacio");
            if(f6.exists())
                leerArAux("f6.txt", archivo);
            else
                System.out.println("Archivo f6.txt vacio");
            if(f7.exists())
                leerArAux("f7.txt", archivo);
            else
                System.out.println("Archivo f7.txt vacio");
            if(f8.exists())
                leerArAux("f8.txt", archivo);
            else
                System.out.println("Archivo f8.txt vacio");
            if(f9.exists())
                leerArAux("f9.txt", archivo);
            else
                System.out.println("Archivo f9.txt vacio");
            w.close();
            ob.close();
            output.close();
            //System.out.println("Archivo Original correctamente generado");
        } catch(IOException ex){
        	System.out.println("Error al modificar archivo original");
        }
    }
    /**
     * Guarda en orden descendente el contenido de los archivos auxiliares en el archivo original. Realiza la lectura y guardado de los archivos auxiliares desde f9 hasta f0. Si algún archivo no existe, no se realiza la lectura del mismo. 
     * En caso de haber un problema se produce una excepción que imprime un mensaje informando que ocurrio un error.
     * @param archivo Nombre del archivo original.
     */
    public void guardarArDes(String archivo) {
            if(f9.exists())
                leerArAux("f9.txt", archivo);
            else
                System.out.println("Archivo f9.txt vacio");
            if(f8.exists())
                leerArAux("f8.txt", archivo);
            else
                System.out.println("Archivo f8.txt vacio");
            if(f7.exists())
                leerArAux("f7.txt", archivo);
            else
                System.out.println("Archivo f7.txt vacio");
            if(f6.exists())
                leerArAux("f6.txt", archivo);
            else
                System.out.println("Archivo f6.txt vacio");
            if(f5.exists())
                leerArAux("f5.txt", archivo);
            else
                System.out.println("Archivo f5.txt vacio");
            if(f4.exists())
                leerArAux("f4.txt", archivo);
            else
                System.out.println("Archivo f4.txt vacio");
            if(f3.exists())
                leerArAux("f3.txt", archivo);
            else
                System.out.println("Archivo f3.txt vacio");
            if(f2.exists())
                leerArAux("f2.txt", archivo);
            else
                System.out.println("Archivo f2.txt vacio");
            if(f1.exists())
                leerArAux("f1.txt", archivo);
            else
                System.out.println("Archivo f1.txt vacio");
            if(f0.exists())
                leerArAux("f0.txt", archivo);
            else
                System.out.println("Archivo f0.txt vacio");
    }
    /**
     * Realiza la lectura y copiado de la información del primer archivo recibido como parámetro al segundo. Si el segundo archivo recibido como parámetro no existe es creado.
     * En caso de haber un problema en la lectura o copiado de información se produce una excepción que imprime un mensaje informando el problema.
     * @param aux Nombre del archivo auxiliar. Cada archivo auxiliar representa un dígito del 0 al 9. 
     * @param archivo Nombre del archivo a ordenar.
     */
    public void leerArAux(String aux, String archivo){
        System.out.println(aux);
        File f =new File(archivo);
        File auxiliar=new File(aux);
        
    	StringTokenizer s;
    	try {
            Scanner entrada = new Scanner(auxiliar); 
            if(!f.exists()){
                f.createNewFile();
            }
            FileWriter FAux= new FileWriter(f.getAbsoluteFile(),true);
            BufferedWriter ob = new BufferedWriter(FAux);
            PrintWriter w = new PrintWriter(ob); 
            
	        while (entrada.hasNext()){
                    String cadena = entrada.nextLine();
	            s= new StringTokenizer(cadena,",");
	            while(s.hasMoreTokens()){
	            	double numero = Double.parseDouble(s.nextToken()); 
                        w.append(numero+",");
                        System.out.print(numero+", ");
                    }
	        }
                entrada.close();
                System.out.println();
            w.close();
            ob.close();
            FAux.close();
            } catch(IOException ex) {
	    	System.out.println("Error en la lectura y copiado del archivo auxiliar");
	    }
    }
    /**
     * Elimina el archivo recibido como parámetro. En caso de ocurrir un error se produce una excepción que imprime un mensaje informando el error.
     * @param aux Nombre del archivo que se desea eliminar.
     */
    private void elimiarArchivo(String aux) {
        File f = new File(aux);
        try {
            f.delete();
        }catch (Exception e) {
            System.out.println("No se elimino el archivo"+aux);
        }
    }
    /**
     * Realiza la asignación del primer número recibido como parámetro al archivo que le corresponde.
     * @param nCompleto El número a almacenar en alguno de los archivos auxiliares.
     * @param cifra Dígito que corresponde al valor de la cifra significativa analizada en ese momento. 
     */
    private void asignarArchivo(double nCompleto, int cifra) {
        switch(cifra){
    		case 0:
    			try{
                            if(!f0.exists()){
                                f0.createNewFile();
                            }
                            FileWriter FAux0= new FileWriter(f0.getAbsoluteFile(),true);
                            BufferedWriter ob = new BufferedWriter(FAux0);
                            PrintWriter w = new PrintWriter(ob); 
                            w.append(nCompleto+",");
                            w.close();
                            ob.close();
                            FAux0.close();
    			} catch(IOException ex){
                            System.out.println("Error");
    			}
    			break;
    		case 1:
    			try{
                            if(!f1.exists()){
                                f1.createNewFile();
                            }
                            FileWriter FAux1= new FileWriter(f1.getAbsoluteFile(),true);
                            BufferedWriter ob = new BufferedWriter(FAux1);
                            PrintWriter w = new PrintWriter(ob); 
                            w.append(nCompleto+",");
                            w.close();
                            ob.close();
                            FAux1.close();
    			} catch(IOException ex){
                            System.out.println("Error");
    			}
                    break;
    		case 2:
    			try{
                            if(!f2.exists()){
                                f2.createNewFile();
                            }
                            FileWriter FAux2= new FileWriter(f2.getAbsoluteFile(),true);
                            BufferedWriter ob = new BufferedWriter(FAux2);
                            PrintWriter w = new PrintWriter(ob); 	
                            w.append(nCompleto+",");
                            w.close();
                            ob.close();
                            FAux2.close();
    			} catch(IOException ex){
                            System.out.println("Error");
    			}
    			break;
    		case 3:
    			try{
                            if(!f3.exists()){
                                f3.createNewFile();
                            }
                            FileWriter FAux3= new FileWriter(f3.getAbsoluteFile(),true);
                            BufferedWriter ob = new BufferedWriter(FAux3);
                            PrintWriter w = new PrintWriter(ob); 
                            w.append(nCompleto+",");
                            w.close();
                            ob.close();
                            FAux3.close();
    			} catch(IOException ex){
                            System.out.println("Error");
    			}
    			break;
    		case 4:
    			try{
                            if(!f4.exists()){
                                f4.createNewFile();
                            }
                            FileWriter FAux4= new FileWriter(f4.getAbsoluteFile(),true);
                            BufferedWriter ob = new BufferedWriter(FAux4);
                            PrintWriter w = new PrintWriter(ob); 	
                            w.append(nCompleto+",");
                            w.close();
                            ob.close();
                            FAux4.close();
    			} catch(IOException ex){
                            System.out.println("Error");
    			}  
    			break;
    		case 5:
    			try{
                            if(!f5.exists()){
                                f5.createNewFile();
                            }
                            FileWriter FAux5= new FileWriter(f5.getAbsoluteFile(),true);
                            BufferedWriter ob = new BufferedWriter(FAux5);
                            PrintWriter w = new PrintWriter(ob); 
                            w.append(nCompleto+",");
                            w.close();
                            ob.close();
                            FAux5.close();
    			} catch(IOException ex){
                            System.out.println("Error");
    			}
    			break;
    		case 6:
    			try{
                            if(!f6.exists()){
                                f6.createNewFile();
                            }
                            FileWriter FAux6= new FileWriter(f6.getAbsoluteFile(),true);
                            BufferedWriter ob = new BufferedWriter(FAux6);
                            PrintWriter w = new PrintWriter(ob); 
                            w.append(nCompleto+",");
                            w.close();
                            ob.close();
                            FAux6.close();
    			} catch(IOException ex){
                            System.out.println("Error");
    			} 
    			break;
    		case 7:
    			try{
                            if(!f7.exists()){
                                f7.createNewFile();
                            }
                            FileWriter FAux7= new FileWriter(f7.getAbsoluteFile(),true);
                            BufferedWriter ob = new BufferedWriter(FAux7);
                            PrintWriter w = new PrintWriter(ob);
                            w.append(nCompleto+",");
                            w.close();
                            ob.close();
                            FAux7.close();
    			} catch(IOException ex){
                            System.out.println("Error");
    			}
    			break;
    		case 8:
    			try{
                            if(!f8.exists()){
                                f8.createNewFile();
                            }
                            FileWriter FAux8= new FileWriter(f8.getAbsoluteFile(),true);
                            BufferedWriter ob = new BufferedWriter(FAux8);
                            PrintWriter w = new PrintWriter(ob); 
                            w.append(nCompleto+",");
                            w.close();
                            ob.close();
                            FAux8.close();
    			} catch(IOException ex){
                            System.out.println("Error");
    			}
    			break;
    		case 9:
    			try{
                            if(!f9.exists()){
                                f9.createNewFile();
                            }
                            FileWriter FAux9= new FileWriter(f9.getAbsoluteFile(),true);
                            BufferedWriter ob = new BufferedWriter(FAux9);
                            PrintWriter w = new PrintWriter(ob);	
                            w.append(nCompleto+",");
                            w.close();
                            ob.close();
                            FAux9.close();
    			} catch(IOException ex){
                            System.out.println("Error");
    			}
    			break;
    	}
    }
    /**
     * Imprime el estado del archivo recibido como parámetro en el momento en el que se llama a este método.
     * En caso de haber un problema en la lectura de información se produce una excepción que imprime un mensaje informando el error.
     * @param archivo Nombre del archivo que se deea mostrar en pantalla.
     */
    public void imprimirIteracion(String archivo){
        File f=new File(archivo);
        StringTokenizer s;
        double numero;
        try{
            Scanner entrada = new Scanner(f);
            while (entrada.hasNext()) {
            	String cadena = entrada.nextLine();
                s= new StringTokenizer(cadena,",");
                while(s.hasMoreTokens()){
                    numero= Double.parseDouble(s.nextToken());
                    System.out.print(numero+",");
                }
            }
            System.out.println("\n");
            entrada.close();
            } catch(IOException ex) {
            System.out.println("Error al leer archivo");
        } 
    }
    
}
