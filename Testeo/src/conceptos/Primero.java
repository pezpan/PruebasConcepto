package conceptos;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Primero {
	
	private static final String myEmail = "email@gmail.com";
	
	public boolean isMyEmail(final String emailToCheck){
		if(emailToCheck != null && emailToCheck == this.myEmail){
			return true;
		}
		return false;
	}
	
	public static List<String> validate(final List<String> args){
		List<String> myArgs = new ArrayList<String>();
		Collections.copy(myArgs, args);
		System.out.println("Empezamos a eliminar elementos");
		int listSize = myArgs.size();
		for(int i=1; i < listSize; i++){
			if(myArgs.get(i).length() > 2){
				myArgs.remove(i);
			}
		}
		return myArgs;
	}
		
	
	interface Foo{}
	class Alpha implements Foo{}
	class Beta extends Alpha{}
	class Delta extends Beta{
		public void correr(){
			try{
				System.out.println("1 OK");
				Beta x = new Beta();
				System.out.println("2 OK");
				Beta b = (Beta)x;
				System.out.println("3 OK");
				Foo r = (Alpha)x;
				System.out.println("4 OK");
				Foo f = (Delta)x;	
				System.out.println("Final OK");
			}catch(ClassCastException ex){
				System.out.println("ClassCastException pillada");
			}
		}
	}
	
	// Pregunta 1. Modificadores validos para campos de una interfaz
	public interface Status{
		public static final int MY_VALUE = 10;
	}
	
	// Pregunta 6. Que opciones compilan
	public class miClase{
		void foo(int...x){
			// foreach(x) System.out.println(z); -> foreach no existe en Java, y z no está definido
			for(int z: x) System.out.println(z); 
			// while(x.hasNext()) System.out.println(x.next()); // -> Los parametros de longitud variable se tratan como un array, y no tienen metodo next ni hasNext
			for(int i = 0; i < x.length; i++) System.out.println(x[i]);
			
		}
	}
	
	// Pregunta 7
	public class Money{
		private String country = "Rusia";
		public String getCountry(){return country;}		
	}
	
	class Yen extends Money{
		public String getCountry(){			
			return super.country;
		}		
	}
	
	class Euro extends Money{
		public String getCountry(String timeZone){
			return super.getCountry();
		}
	}
	
	// Pregunta 9
	class TestA{
		public String cadena = "Soy A";
		public void start(){System.out.println("TestA");}
	}
	class TestB extends TestA{
		public String cadena = "Soy B";
		public void start(){System.out.println("TestB");}
	}
	
	// Pregunta 10
	public class Pass{
		void doStuff(int x){
			System.out.println("doStuff x = " + x++);
		}		
	}
	
	// Pregunta 11
	class Clase1{}
	class Clase2 extends Clase1{}
	class Clase3 extends Clase1{}
	
	// Pregunta 17
	class ClaseAA{
		public int  numero;
		protected ClaseAA(int numero){
			this.numero = numero;
		}
	}
	
	public class ExtendedClaseAA extends ClaseAA{		
		private ExtendedClaseAA(int numero){
			super(numero);			
		}
	}
	
	// Pregunta 18
	public class Blip{
		protected int blipvert(int x){return 0;}
	}
	
	// Ver que lineas compilarían
	class Vert extends Blip{
		// public int blipvert(int x){return 0;} // Si compila, sobreescribe el metodo heredado
		//private int blipvert(int x){return 0;} // No compila, reduce la visibilidad del metodo heredado
		//private int blipvert(long x){return 0;} // No compila
		//protected long blipvert(int x){return 0;} // No compila. El tipo de valor devuelto es incompatible, no es int
		//protected int blipvert(long x){return 0;} // Si compila, es un metodo sobrecargado
		//protected long blipvert(long x){return 0;} // Si compila, es un metodo sobrecargado
		//protected long blipvert(int x, int y){return 0;} // Si compila, es un metodo sobrecargado
	}
	
	// Ejecucion
	public static void main(String args[]) throws Exception{
		Primero primer = new Primero();
		Scanner scanner = new Scanner(System.in);
		int opcion = 0;
		while(opcion < 20){
			System.out.println("Opcion: " + opcion);
			System.out.println("Introduce el numero de prueba: ");
			opcion = scanner.nextInt();
			switch(opcion){
				// Pregunta 2
				case 2:
					// 2. Finally
					// No entra en el finally si se lanza una excepcion desde fuera
					// No saldra ningun texto
					int s = 7 / 0;
					try{
						int j = 4 / 0;
					}catch(Exception ex){
						System.out.println("Dentro de catch: " + ex.getMessage());
					}finally{
						System.out.println("Dentro del finally");
					}
					break;
				// Pregunta 3	
				case 3:
					primer.new Delta().correr();
					break;
				// Pregunta 4. Devuelve true, si es una errata en la pregunta
				case 4:
					System.out.println(primer.isMyEmail("email@gmail.com"));
					break;
				// Pregunta 5. Debe dar una excepcion por copiar a una lista vacia.
				// Copy  copia a una lista que tenga al menos el mismo numero de elementos que la de origen 
				case 5:
					try{
						Primero.validate(new ArrayList<String>(Arrays.asList("cp", "cd", "chmod", "mv")));
					}catch(Exception ex){
						System.out.println("Excepcion en 5: " + ex.getMessage());
					}
					break;
				// Pregunta 6
				case 6:
					primer.new miClase().foo(1,2,3,4,5,6,7,8,9); 
					break;
				// Pregunta 7
				case 7:
					// Funciona porque es una clase interna de Primero. Se puede acceder a los campos privados de una subclase
					// desde cualquier parte de la clase externa, y como la clase que hereda de la otra esta dentro de la clase
					// externa, puede acceder con super pero no directamente.
					String moneda = primer.new Yen().getCountry();
					System.out.println("Yen: " + moneda);
					String otramoneda = primer.new Euro().getCountry();
					System.out.println("Euro: " + otramoneda);					
					break;
				// Pregunta 9
				case 9:
					// Se llama al metodo del objeto B porque es el tipo con el cual se ha creado, aunque se llame
					// con una referencia de tipo A. (Polimorfismo)
					// El tipo B tiene dos variables cadena, la suya y la de la superclase. Cual se llame si depende de la referencia
					// y al ser del tipo A, se llama a la variable de la clase A.					
					System.out.println(((TestA)primer.new TestB()).cadena);					
					((TestA)primer.new TestB()).start();
					break;
				// Pregunta 10
				case 10:
					int x = 5;
					Pass p = primer.new Pass();
					p.doStuff(x);
					System.out.println("Main x = " + x);
					break;
				// Pregunta 11
				case 11:
					Clase1 p0 = primer.new Clase1();
					Clase2 p1 = primer.new Clase2();
					Clase3 p2 = primer.new Clase3();
					Clase1 p3 = primer.new Clase2();
					Clase1 p4 = primer.new Clase3();
					
					p0 = p1; // La clase0 puede hacer referencia a clase1
//					p1 = p2; -> No valida, no se puede convertir Clase 3 en Clase 2
//					p2 = p4; -> No valida, no se puede convertir Clase 1 en Clase 3
//					p2 = (Clase3)p1; -> No valida, no se puede convertir Clase 2 en Clase 3
					p1 = (Clase2)p3; // Los dos son de tipo Clase2
					p2 = (Clase3)p4; // Los dos son de tipo Clase3
					
					System.out.println("Salimos del 11");
					
					break;
				// Pregunta 17
				case 17:
					// Devuelve 420
					ExtendedClaseAA ext = primer.new ExtendedClaseAA(420);
					System.out.println(ext.numero);
					break;
				// Pregunta 19
				case 19:
					
				default:					
					break;
			}
		}
		System.out.println("Fuera de while");
				
	}
	
	
	
}
