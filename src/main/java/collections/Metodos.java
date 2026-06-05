package collections;

import java.util.Scanner;

public class Metodos {
	public static int lerInteiro(Scanner resposta) {
		while (true) {
			String linha = resposta.nextLine().trim();
			if (linha.isEmpty()) {
				return 0; 
			}
			try {
				return Integer.parseInt(linha); // Converte o texto em número inteiro
			} catch (NumberFormatException e) {
				System.out.println("Digite um número inteiro válido: ");
			}
		}
	}

	public static double lerDouble(Scanner resposta) {
		while (true) {
			String linha = resposta.nextLine().trim();
			if (linha.isEmpty()) {
				return 0.0;
			}
			try {
				return Double.parseDouble(linha); // Converte o texto em número decimal
			} catch (NumberFormatException e) {
				System.out.println("Digite um número decimal válido: ");
			}
		}
	}
}
