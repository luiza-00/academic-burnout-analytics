package collections;

	import java.util.Scanner;

	public class Metodos {

	    public static int lerInteiro(Scanner resposta) {
	        while (!resposta.hasNextInt()) {
	            System.out.println("Número inválido, digite corretamente.");
	            resposta.next(); 
	        }
	        
	        int numero = resposta.nextInt();
	        resposta.nextLine(); 
	        return numero;
	    }

	    public static double lerDouble(Scanner resposta) {
	        while (!resposta.hasNextDouble()) {
	            System.out.println("Número inválido, digite corretamente.");
	            resposta.next(); 
	            
	        double numero = resposta.nextDouble();
	        resposta.nextLine();
	        return numero;
	    }
			return 0;
	    }
	}
