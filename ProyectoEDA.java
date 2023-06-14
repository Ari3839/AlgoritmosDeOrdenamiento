
package proyectoeda;

import java.util.Scanner;

/**
 * Clase que contiene al método principal del proyecto.
 * @author Jiménez,Lázaro y Mendoza
 */
public class ProyectoEDA {
/**
 * Despliega un menú con las opciones de crear un archivo con n número de claves, u ordenar un archivo con alguno de los métodos de ordenamiento externo.
 * El menú se seguita desplegando hasta que el usuario seleccione la opción de salir.
 * @param args Argumentos de la linea de comandos
 */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Utilerias util=new Utilerias();
        Polifase p=new Polifase();
        Radix radix=new Radix();
        MezclaEquilibrada m=new MezclaEquilibrada();
        int op;
        String nombre;
        do{
            System.out.println("\nEscoge una opcion:");
            System.out.println("1. Polifase");
            System.out.println("2. Mezcla equilibrada");
            System.out.println("3. Metodo por distribucion (Radix)");
            System.out.println("4. Crear un archivo con claves aleatorias de tamanio n");
            System.out.println("5. Salir");
            op=Integer.parseInt(sc.nextLine());
            switch(op){
                case 1:
                    int a=menu();
                    System.out.println("Ordenemiento: "+a);
                    nombre=util.nombre();
                    int nClaves=util.nClaves();
                    p.empezar(nombre,a,nClaves);
                    break;
                case 2:
                    a=menu();
                    System.out.println("Ordenemiento: "+a);
                    nombre=util.nombre();
                    m.mezclaEquilibrada(nombre, a);
                    break;
                case 3:
                    a=menu();
                    System.out.println("Ordenemiento: "+a);
                    nombre=util.nombre();
                    nClaves=util.nClaves();
                    radix.radixExterno(nombre, nClaves,a);
                    break;
                case 4:
                    util.generarClaves();
                    break;
                case 5:
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Escoge una opcion valida");
                    break;
            }
        }while(op!=5);
    }
    /**
     * Método que despliega un sub-menú en caso de seleccionar alguna opción de ordenamiento, para elegir el orden de ordenamiento (ascendente o descendente).
     * El menú se seguirá desplegando hasta que el usuario introduzca una opción válida.
     * @return Tipo de ordenamiento
     */
    public static int menu(){
        Scanner sc=new Scanner(System.in);
        int op;
        do{
            System.out.println("Escoge una opcion");
            System.out.println("1. Ordenar ascendentemente");
            System.out.println("2. Ordenar descendentemente");
            op=Integer.parseInt(sc.nextLine());
            switch(op){
                case 1:
                    return 1;
                case 2:
                    return 2;
                default:
                    System.out.println("Escoge una opcion valida");
                    return 0;
            }
        }while(op!=1||op!=2);
        
    }
    
    
}
