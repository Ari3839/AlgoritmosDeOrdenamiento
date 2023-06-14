
package proyectoeda;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 * Clase para implementar el método de ordenamiento externo de Mezcla Equilibrada
 * @author Jiménez,Lázaro y Mendoza
 */
public class MezclaEquilibrada {
	File fa1 = new File("Faux1.txt");
	File fa2 = new File("Faux2.txt");
	File fa3 = new File("Faux3.txt");
	int cont = 0;
	Ordenamiento odr = new Ordenamiento();	
	/**
	 * En este método se lee el archivo generado con las claves por lienea.
	 * @param archivo nombre del archivo generado que leera.
	 * @return Elementos nombre de un objeto String en donde se gurdan los elementos leidos.
	 */
	public String lectura(String archivo){
		String elementos = "";
		
		try(BufferedReader read = new BufferedReader(new FileReader(archivo))){
			String strLine;
			while((strLine = read.readLine())!=null) {
				elementos = elementos+strLine;
			}
		}catch(IOException e) {
			System.out.println("Error en la lectura...");
		}
		return elementos;
	}
	/**
	 * Este método hara la escritura de Strings en el archivo que sele pase al método.
	 * @param arch Nombre del archivo que se pasa al método. 
	 * @param cadena Nombre del String que recibe el método por parámetro. 
	 */
	public void escritura(String arch, String cadena) {
		
		try(PrintWriter pw = new PrintWriter(new FileWriter(arch))){
			if(cadena.endsWith(",")) {
				cadena = cadena.substring(0,cadena.length()-1);
			}
			pw.print(cadena);
		}catch(Exception ex) {
			System.out.println("Error...");
		}
	}
	/**
	 * Este método realiza la particion inicial del archivo original para guardar diferentes bloques en dos archuivos auxiliares. 
	 * @param archivoF nombre del archivo origianl que recibe el método.
	 * @param c Reprensenta cual ordenamiento se seguira, si es 1 sera un ordenamiento ascendente, en caso contrario se tratara de un ordemaniento descendente. 
	 */
	public void particionInicial(String archivoF, int c) {
		
		boolean bloque = true;
		double aux = 0;
		String aux2 = "";
		String aux3 = "";
		
		String numeros = lectura(archivoF);
	
		if(!numeros.isEmpty()) {
			StringTokenizer num = new StringTokenizer(numeros,",");
			aux = Double.parseDouble(num.nextToken());
			aux2 = aux2.concat(aux+",");
			double elemento = 0;
			while(num.hasMoreTokens()) {
				elemento = Double.parseDouble(num.nextToken());
				if(c==1) {
					if(elemento >= aux){
						aux = elemento;
						if(bloque) {
							aux2 = aux2.concat(elemento+",");
						}
						else {
							aux3 = aux3.concat(elemento+",");
						}
					}else {
						aux = elemento;
						if(bloque) {
							aux3 = aux3.concat(elemento+",");
							bloque = false;
						}
						else {
							aux2 = aux2.concat(elemento+",");
							bloque = true;
						}
					}
				}else {
					if(elemento <= aux){
						aux = elemento;
						if(bloque) {
							aux2 = aux2.concat(elemento+",");
						}
						else {
							aux3 = aux3.concat(elemento+",");
						}
					}else {
						aux = elemento;
						if(bloque) {
							aux3 = aux3.concat(elemento+",");
							bloque = false;
						}
						else {
							aux2 = aux2.concat(elemento+",");
							bloque = true;
						}
					}
				}
			}
		escritura("Faux2.txt",aux2);
		escritura("Faux3.txt",aux3);
		
		System.out.println("\nParticion Inical: ");
		System.out.println("Faux2:");
		System.out.println(aux2);
		System.out.println("Faux3:");
		System.out.println(aux3);
		}else {
			System.out.println("El archivo inicial esta vacio...");
		}
	}	
	/**
	 * Este método trasnforma Strings a numeros de tipo double. 
	 * @param cadena nombre del String que recibe el método. 
	 * @param separador nombre del sufijo que se utiliza para separar las cadenas. 
	 * @return numeros Nombre del ArrayList que contiene las cadenas trasnformadas que regresa el método.
	 */
	public ArrayList<Double> transformaString(String cadena,String separador){
		String[] cadenas = cadena.split(separador);
		ArrayList<Double> numeros = new ArrayList<>();
	       
		for (String cadena1 : cadenas) {
			numeros.add(Double.parseDouble(cadena1));
		}
		return numeros;
	}
	/**
	 * Este método transforma numeros duuble a un String.
	 * @param num Nombre del ArrayList que recibe el método.
	 * @return cadenaFinal Nombre del String con los números trasformados que regresa el método.   
	 */
	public String transformaDouble(ArrayList<Double> num){
		String cadenaFinal ="";
		for (int i = 0; i<num.size();i++) {
			cadenaFinal = cadenaFinal.concat(num.get(i)+",");
		}
	        
	        return cadenaFinal;
	}
	/**
	 * En este método se combinan los archivos Faux2 y Faux3, los ordena y se guarda el resultado en el archivo original y en Faux3  
	 * @param archivo Nombre del archivo que se quiere ordenar.
	 * @param c Representa el tipo de ordenamiento que se seguira, si es 1 se seguira un ordenamiento ascendente, en caso contrario se sigue un ordenamiento descendente.
	 */
	public void combinaYordena(String archivo, int c){
		ArrayList<Double> f = transformaString(lectura("Faux2.txt"),",");
		ArrayList<Double> f1 = transformaString(lectura("Faux3.txt"),",");
	
		Double aux = 0.0;
		Double aux2 = 0.0; 
		boolean bloque = true;
		boolean bloque2 = false;
		

		ArrayList<Double> auxF = new ArrayList<>();
		ArrayList<Double> auxF1 = new ArrayList<>();
	
		String file = "";
		String file1 = "";
	        
		aux = f.remove(0);
		auxF.add(aux);
		cont++;
		int i = 1;
		while(!f.isEmpty() || !f1.isEmpty()){
			bloque = true;
			while(bloque && !f.isEmpty()){
				if (f.get(0)>=aux) {
					aux = f.get(0);
					if(bloque2){
						auxF1.add(f.remove(0));
					}else{
						auxF.add(f.remove(0));
					}
	                    
				}else{
					aux = f.get(0);
					bloque = false;
				}
			}
			bloque = true;
			if( i == 1){
				aux2 = f1.remove(0);
				auxF.add(aux2);
				i++;
			}
			while(bloque && !f1.isEmpty()){
				if (f1.get(0)>=aux2) {
					aux2 = f1.get(0);
					if (bloque2) {
						auxF1.add(f1.remove(0));
					}else{
						auxF.add(f1.remove(0));                     
					}
	                    
				}else{
					aux2 = f1.get(0);
					bloque = false;  
				}
			}
			if(c==1) {
				if (bloque2) {
					auxF1 = odr.sort(auxF1,0,auxF1.size()-1);
					file1 = file1 + transformaDouble(auxF1);
					auxF1 = new ArrayList<>();
					bloque2 = false;
				}else{       
					auxF = odr.sort(auxF,0,auxF.size()-1);
					file = file + transformaDouble(auxF);
					auxF = new ArrayList<>();
					bloque2 = true;
				}
			}else {
				if (bloque2) {
					auxF1 = odr.sort2(auxF1,0,auxF1.size()-1);
					file1 = file1 + transformaDouble(auxF1);
					auxF1 = new ArrayList<>();
					bloque2 = false;
				}else{       
					auxF = odr.sort2(auxF,0,auxF.size()-1);
					file = file + transformaDouble(auxF);
					auxF = new ArrayList<>();
					bloque2 = true;
				}
			}
		}
	               
		escritura(archivo, file);
		escritura("Faux1.txt", file1);
		
		System.out.println("\nIteracion "+cont);
		System.out.println("Archivo original:");
		System.out.println(file);
		System.out.println("Archivo auxiliar Faux1:");
		System.out.println(file1);
	}
	/**
	 * En este método se combinan el archivo original y el archivo Faux3 y los ordena.
	 * @param archivo Nombre del archivo que se quiere ordenar.
	 * @param c Representa el tipo de ordenamiento que se seguira, si es 1 se seguira un ordenamiento ascendente, en caso contrario se sigue un ordenamiento descendente.
	 */
	public void combinaYordena2(String archivo, int c){
	
		ArrayList<Double> f = transformaString(lectura(archivo),",");
		ArrayList<Double> f1 = transformaString(lectura("Faux1.txt"),",");
	
		Double aux = 0.0;
		Double aux2 = 0.0; 
		boolean bloque = true;
		boolean bloque2 = false;

		ArrayList<Double> auxF = new ArrayList<>();
		ArrayList<Double> auxF1 = new ArrayList<>();
	
		String file = "";
		String file1 = "";
	        
		aux = f.remove(0);
		auxF.add(aux);
	        
		int i = 1;
		while(!f.isEmpty() || !f1.isEmpty()){
			bloque = true;
			while(bloque && !f.isEmpty()){
				if (f.get(0)>=aux) {
					aux = f.get(0);
					if(bloque2){
						auxF1.add(f.remove(0));
					}else{
						auxF.add(f.remove(0));
					}
	                    
				}else{
					aux = f.get(0);
					bloque = false;
				}
			}
			bloque = true;
			if( i == 1){
				aux2 = f1.remove(0);
				auxF.add(aux2);
				i++;
			}
			while(bloque && !f1.isEmpty()){
				if (f1.get(0)>=aux2) {
					aux2 = f1.get(0);
					if (bloque2) {
						auxF1.add(f1.remove(0));
					}else{
						auxF.add(f1.remove(0));                     
					}
	                    
				}else{
					aux2 = f1.get(0);
					bloque = false;  
				}
			}
			if(c==1) {
				if (bloque2) {
					auxF1 = odr.sort(auxF1,0,auxF1.size()-1);
					file1 = file1 + transformaDouble(auxF1);
					auxF1 = new ArrayList<>();
					bloque2 = false;
				}else{   
					auxF = odr.sort(auxF,0,auxF.size()-1);
					file = file + transformaDouble(auxF);
					auxF = new ArrayList<>();
					bloque2 = true;
				}	           
			}else {
				if (bloque2) {
					auxF1 = odr.sort2(auxF1,0,auxF1.size()-1);
					file1 = file1 + transformaDouble(auxF1);
					auxF1 = new ArrayList<>();
					bloque2 = false;
				}else{   
					auxF = odr.sort2(auxF,0,auxF.size()-1);
					file = file + transformaDouble(auxF);
					auxF = new ArrayList<>();
					bloque2 = true;
				}	           
			}
		}          
		escritura("Faux2.txt", file);
		escritura("Faux3.txt", file1);
	        
		System.out.println("Archivo auxiliar Faux2:");
		System.out.println(file);
		System.out.println("Archivo auxiliar Faux3:");
		System.out.println(file1);
	}
	/**
	 * Método en donde se realizara todo los pasos anteriores en orden para realizar el ordenamiento por Mezcla Equilibrada.
	 * @param f Nombre del archivo que se quiere ordenar. 
	 * @param c Representa el tipo de ordenamiento que se seguira, si es 1 se seguira un ordenamiento ascendente, en caso contrario se sigue un ordenamiento descendente.
	 */
	public void mezclaEquilibrada(String f,int c){
		boolean cambioFile = false;
		String[] files = new String[2];
		particionInicial(f,c); //hace la particion inicial
		combinaYordena(f,c); //combina los archivos f2 y f3 los ordena y los mete en f y f1
		
		//Leemos los archivos para saber si estan vacios
		files[0] = lectura("Faux1.txt"); 
		files[1] = lectura("Faux3.txt");
	        
		//combina y ordena hasta que alguno de los archivo f1 o f3 este vacio
		while(!files[0].isEmpty() && !files[1].isEmpty()){
			if (cambioFile) {
				combinaYordena(f,c);
				cambioFile = false;
			}else{    
				combinaYordena2(f,c);
				cambioFile = true;
			}
			files[0] = lectura("Faux1.txt");
			files[1] = lectura("Faux3.txt");
		}
	}
}