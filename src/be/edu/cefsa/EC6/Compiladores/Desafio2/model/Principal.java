package be.edu.cefsa.EC6.Compiladores.Desafio2.model;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Principal {

	public static final String arquivoLeitura = "prog.txt";
	public static final String arquivoSaida = "prog-check.txt";
	public static final String charsEsq = "([{<";
	public static final String charsDir = ")]}>";

	public static void main(String[] args) {

		// System.out.println(validaLinha("<<()[<>]>>"));
		try {
			// leitura arquivo
			FileReader fr = new FileReader(arquivoLeitura,StandardCharsets.UTF_8); 
			BufferedReader dadosLidos = new BufferedReader(fr); 
			String linha = "";
			
			// escrita arquivo
			FileWriter fw = new FileWriter(arquivoSaida,StandardCharsets.UTF_8);
			BufferedWriter escreveArquivo = new BufferedWriter(fw);
			
			while ((linha = dadosLidos.readLine()) != null) 
			{
				if (validaLinha(linha))
					escreveArquivo.write(linha+" - OK\n");
				else
					escreveArquivo.write(linha+" - Inválido\n");			
				
			}
			fr.close();
			escreveArquivo.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		
	}

	public static void leituraArquivo() {

	}

	public static boolean validaLinha(String linha) {
		Stack<Character> pilha = new Stack<Character>();

		for (int i = 0; i < linha.length(); i++) {
			if (charsEsq.indexOf(linha.charAt(i)) >= 0) {
				pilha.add(linha.charAt(i));
			} else {
				if (charsDir.indexOf(linha.charAt(i)) >= 0) {
					if (pilha.size() > 0) {
						if (comparaPar(pilha.peek(), linha.charAt(i))) {
							pilha.pop();
						} else
							return false;
					} else
						return false;
				} else
					return false;
			}
		}

		return pilha.size() == 0;
	}

	public static boolean comparaPar(char esq, char dir) {
		switch (esq) {
		case '(':
			if (dir == ')')
				return true;
			break;
		case '[':
			if (dir == ']')
				return true;
			break;
		case '{':
			if (dir == '}')
				return true;
			break;
		case '<':
			if (dir == '>')
				return true;
			break;
		default:
			return false;
		}
		return false;
	}
}
