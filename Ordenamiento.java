
package proyectoeda;

import java.util.ArrayList;
/**
 * Clase para implementar el ordenamiento interno por Quick Sort.
 * @author Jiménez,Lázaro y Mendoza
 */
public class Ordenamiento {
	/**
	 * En este método se ordenara el arreglo. 
	 * @param arr Nombre del arreglo que recibe el método.
	 * @param low Nombre del indice de la izquierda que es donde inicia el rango del arreglo. 
	 * @param high Nombre del indice de la derecha que es en donde termina el rango del arreglo.
	 * @return i+1 Nombre del indice de la izquierda al cual se le suma uno. 
	 */
    int partition(double arr[],int low, int high){
    	double pivot=arr[high];
        int i=(low-1);
        for(int j=low; j<high;j++){
        	if(arr[j]<=pivot){
        		i++;
                double temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
         }
         double temp=arr[i+1];
         arr[i+1]=arr[high];
         arr[high]=temp;
         return i+1;
    }
    /**
     * En este método se generan arreglos de diferentes tamaños. 
     * @param arr Nombre del arreglo que se quiere ordenar.
     * @param low Nombre del indice de la izquierda en donde incia el rango del arreglo. 
     * @param high Nombre del indice de la derecha en donde termina el rango del arreglo.
     */
    void sort(double arr[], int low, int high){
    	if(low<high){
            int pi=partition(arr,low,high);
            sort(arr,low,pi-1);
            sort(arr,pi+1,high);
        }
    }
    /**
     * En este método se ordena el arreglo. 
     * @param arr Nombre del arreglo que se quiere ordenar. 
     * @param low Nombre del indice de la izquierda en donde incia el rango del arreglo. 
     * @param high Nombre del indice de la derecha en donde termina el rango del arreglo.
     * @return i+1 Nombre del indice de la izquierda al cual se le suma uno. 
     */
    int partition2(double arr[],int low, int high){
    	double pivot=arr[high];
        int i=(low-1);
        for(int j=low; j<high;j++){
            if(arr[j]>=pivot){
                i++;
                double temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }
        double temp=arr[i+1];
        arr[i+1]=arr[high];
        arr[high]=temp;
        return i+1;
    }
    /**
     * Este método genera arreglos de diferentes tamaños. 
     * @param arr Nombre del arreglo que se quiere ordenar.
     * @param low Nombre del indice de la izquierda en donde incia el rango del arreglo. 
     * @param high Nombre del indice de la derecha en donde termina el rango del arreglo.
     */
    void sort2(double arr[], int low, int high){
    	if(low<high){
    		int pi=partition2(arr,low,high);
    		sort2(arr,low,pi-1);
    		sort2(arr,pi+1,high);
    	}
    }
    /**
     * En este método se ordena el ArrayList. 
     * @param arregloDesordenado nombre del ArrayList que se quiere ordenar. 
     * @param low Nombre del indice de la izquierda en donde incia el rango del arreglo. 
     * @param high Nombre del indice de la derecha en donde termina el rango del arreglo.
     * @return i+1  Nombre del indice de la izquierda al cual se le suma uno. 
     */
    public int partition(ArrayList<Double> arregloDesordenado, int low, int high){
		double pivot=arregloDesordenado.get(high);
		int i=(low-1);
		for(int j=low; j<high;j++){
			if(arregloDesordenado.get(j)<=pivot){
				i++;
				double temp=arregloDesordenado.get(i);
				arregloDesordenado.set(i, arregloDesordenado.get(j));
				arregloDesordenado.set(j, temp);
			}
		}
		double temp=arregloDesordenado.get(i+1);
		arregloDesordenado.set(i+1, arregloDesordenado.get(high));
		arregloDesordenado.set(high, temp);
		return i+1;
	}
	/**
	 * En este método se generan ArrayList de diferentes tamaños. 
	 * @param arreglo Nombre del ArrayList que se quiere ordenar. 
	 * @param low Nombre del indice de la izquierda en donde incia el rango del arreglo. 
	 * @param high Nombre del indice de la derecha en donde termina el rango del arreglo.
	 * @return Regresa el ArrayList ordenado de forma ascendente. 
	 */
	public ArrayList<Double> sort(ArrayList<Double> arreglo, int low, int high){
		if(low<high){
			int pi=partition(arreglo,low,high);
			sort(arreglo,low,pi-1);
			sort(arreglo,pi+1,high);
		}
		return arreglo;
	}
	/**
	 * En este Método se ordena el ArrayList.
	 * @param arregloDesordenado Nombre del ArrayList que se quiere ordenar. 
	 * @param low Nombre del indice de la izquierda en donde incia el rango del arreglo. 
	 * @param high Nombre del indice de la derecha en donde termina el rango del arreglo.
	 * @return i+1 Nombre del indice de la izquierda al cual se le suma uno. 
	 */
	public int partition2(ArrayList<Double> arregloDesordenado, int low, int high){
		double pivot=arregloDesordenado.get(high);
		int i=(low-1);
		for(int j=low; j<high;j++){
			if(arregloDesordenado.get(j)>=pivot){
				i++;
				double temp=arregloDesordenado.get(i);
				arregloDesordenado.set(i, arregloDesordenado.get(j));
				arregloDesordenado.set(j, temp);
			}
		}
		double temp=arregloDesordenado.get(i+1);
		arregloDesordenado.set(i+1, arregloDesordenado.get(high));
		arregloDesordenado.set(high, temp);
		return i+1;
	}
	/**
	 * En este método se generan ArrayList de diferentes tamaños. 
	 * @param arreglo Nombre del ArrayList que se quiere ordenar. 
	 * @param low Nombre del indice de la izquierda en donde incia el rango del arreglo. 
	 * @param high Nombre del indice de la derecha en donde termina el rango del arreglo.
	 * @return Regresa el ArrayList ordenado de forma descendente.
	 */
	public ArrayList<Double> sort2(ArrayList<Double> arreglo, int low, int high){
		if(low<high){
			int pi=partition2(arreglo,low,high);
			sort2(arreglo,low,pi-1);
			sort2(arreglo,pi+1,high);
		}
		return arreglo;
	}
}
