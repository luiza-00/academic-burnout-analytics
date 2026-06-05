package collections;

	import java.util.Scanner;

	public class Metodos {
public static int lerInteiro(Scanner resposta) {
	        while (true) {
	            try {
	                // Lê a linha inteira como texto e limpa espaços vazios nas pontas
	                String linha = resposta.nextLine().trim();
	                return Integer.parseInt(linha); // Converte o texto em número inteiro
	            } catch (NumberFormatException e) {
	                System.out.print("Digite um número inteiro válido: ");
	            }
	        }
	    }

	    public static double lerDouble(Scanner resposta) {
	        while (true) {
	            try {
	                String linha = resposta.nextLine().trim();
	                return Double.parseDouble(linha); // Converte o texto em número decimal
	            } catch (NumberFormatException e) {
	                System.out.print("Digite um número inteiro válido: ");
	            }
	        }
		}
	}
