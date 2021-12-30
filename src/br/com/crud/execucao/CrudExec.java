package br.com.crud.execucao;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.crud.modelos.Aluno;
import br.com.crud.modelos.Pessoa;

public class CrudExec {

	
	// ---------------ATRIBUTOS ESTÁTICOS:---------------
	
	// Variável para receber dados:
	private static Scanner entrada = new Scanner(System.in);
	
	// Controle de registro:
	private static int id = 0;
	
	// Array com seis informações para pessoa/aluno.
	// Limitei para 20 linhas de registro, já que é só um programa na memória;
	private static String[][] listaTotal = new String[20][6];
	
	// Variável de menu:
	private static int opcao;
	
	public static void main(String[] args) {
		
		System.out.println("Bem-vindo ao sistema de cadastro!");		
		
		try {
			menu();
		
			if (opcao < 0 || opcao == 0 || opcao > 5) { // (fazer opcao <= 0 não funcionou)
				System.out.println("Opção inválida! Tente de novo:");
				menu();
			}
			
		} catch (InputMismatchException e) {
			System.out.println("\nAlerta de erros possíveis:"
					+ "\n1) O menu inicial só aceita números inteiros;"
					+ "\n2) Se estiver usando windows, o decimal da nota só aceitará vírgula!"
					+ "\nReinicie o programa.");
		}
	}
	
	// ---------------MENU DA APLICAÇÃO:---------------
	
	public static void menu() {
		System.out.println("---- Menu de Opções ----");
		System.out.println("1. Criar novo Usuário");
		System.out.println("2. Listar usuários cadastrados");
		System.out.println("3. Atualizar Usuário");
		System.out.println("4. Deletar Usuário");
		System.out.println("5. Encerrar Sistema");
		
		
		System.out.print("Insira o número da opção desejada: ");
		
		opcao = entrada.nextInt();
		
		switch (opcao) {
			case 1: {
				criarUsuario();
				break;
			}
			case 2: {
				listarUsuarios();
				break;
			}
			case 3: {
				System.out.print("Informe o número do usuário na lista para atualizar: ");
				int num = entrada.nextInt();
				atualizarUsuario(num);
				break;
			}
			case 4: {
				System.out.print("Informe o número do usuário na lista para deletar: ");
				int num = entrada.nextInt();
				deletarUsuario(num);
				break;
			}
			case 5: {
				encerrar();
				break;
			}
			// Motivo para não usar o Default:
			// Precisava chamar o menu novamente (o que não era possível dentro do próprio método)
		}
	}
	
	// ---------------MÉTODOS DA APLICAÇÃO:---------------
	
	public static void criarUsuario() {
		id++; // id novo criado;
		
		int ind = id - 1; //  - 1 para a array aproveitar a posição 0;
		
		System.out.print("\nInsira o Nome: ");
		String nome = entrada.next();
		
		System.out.print("Insira o Telefone: ");
		String tel = entrada.next();
		
		System.out.print("Insira data de nascimento (dd/mm/yyyy): ");
		String nasc = entrada.next();
		
		System.out.print("Deseja inserir nota? Digite sim para inserir\n(ou qualquer outra resposta se não quiser): ");
		String nota = entrada.next();
		Double notaFinal = null; // Inicializar o tipo fora do if;
		
		if (nota.equalsIgnoreCase("Sim")) {
		
			System.out.print("Insira nota Final de 0 a 10: ");
			notaFinal = entrada.nextDouble(); // Deixa de ser null aqui;
			
		}
		
		Date cad = new Date();
		
		Date alt = new Date();
		
		String tipo = "";
		if (notaFinal != null && notaFinal >= 0.0 && notaFinal <= 10.0) {
			tipo = "Aluno";
			Aluno usuario = new Aluno(nome, tel, nasc, cad, alt, notaFinal);
			listaTotal[ind][0] = usuario.getNome();
			listaTotal[ind][1] = usuario.getTelefone();
			listaTotal[ind][2] = usuario.getNascimento();
			listaTotal[ind][3] = usuario.getCadastro().toString();
			listaTotal[ind][4] = usuario.getAlteracao().toString();
			listaTotal[ind][5] = usuario.getNotaFinal().toString();
		} else {
			tipo = "Pessoa (sem nota)";
			Pessoa usuario = new Pessoa(nome, tel, nasc, cad, alt);
			listaTotal[ind][0] = usuario.getNome();
			listaTotal[ind][1] = usuario.getTelefone();
			listaTotal[ind][2] = usuario.getNascimento();
			listaTotal[ind][3] = usuario.getCadastro().toString();
			listaTotal[ind][4] = usuario.getAlteracao().toString();
			listaTotal[ind][5] = null;
		}
			
		System.out.println("\nUsuário tipo " + tipo + " criado:"
				+ "\nId: " + id
				+ "\nNome: " + nome);
		
		menu();
	}

	
	public static void listarUsuarios() {
		
		if (id == 0) {
			System.out.println("\nNão há usuários para listar."
					+ " Você pode criá-los usando a opção 1.\n");
		} else {
			System.out.println("\nLista de Usuários.\nAlunos com nota"
					+ ", Pessoas com nota null:\n");
		
		for (int i = 0; i < id; i++) {
			
			System.out.println("Número na lista: " + (i+1));
			for (int j = 0; j < 6; j++)
				switch(j) {
					case 0: {
						System.out.print("Nome: " + listaTotal[i][j] + "; ");
						break;
					}
					case 1: {
						System.out.print("Telefone: " + listaTotal[i][j] + "; ");
						break;
					}
					case 2: {
						System.out.println("Data de Nascimento: " + listaTotal[i][j] + "; ");
						break;
					}
					case 3: {
						System.out.println("Cadastrado em: " + listaTotal[i][j] + "; ");
						break;
					}
					case 4: {
						System.out.println("Última Atualização: " + listaTotal[i][j] + "; ");
						break;
					}
					case 5: {
						System.out.println("Nota: " + listaTotal[i][j] + ";\n");
						break;
					}
				}
			}
		}
		
		menu();
	}

	
	public static void atualizarUsuario(int num) {
		
		if (num > 0 && num <= id) {
			System.out.print("\nInsira o Nome: ");
			String nome = entrada.next();
			
			System.out.print("Insira o Telefone: ");
			String tel = entrada.next();
			
			System.out.print("Insira data de nascimento (dd/mm/yyyy): ");
			String nasc = entrada.next();
			
			// Date cad = new Date(); Retirado da atualização.
			Date alt = new Date(); // Alteração atualiza a data.
			
			int ind = num - 1;
			
			if (listaTotal[ind][5] != null) {
				System.out.print("Insira nota Final de 0 a 10: ");
				Double notaFinal = entrada.nextDouble();
				
				listaTotal[ind][0] = nome;
				listaTotal[ind][1] = tel;
				listaTotal[ind][2] = nasc;
				listaTotal[ind][4] = alt.toString();
				listaTotal[ind][5] = notaFinal.toString();
			
			} else {
				
				listaTotal[ind][0] = nome;
				listaTotal[ind][1] = tel;
				listaTotal[ind][2] = nasc;
				listaTotal[ind][4] = alt.toString();
				// listaTotal[id - 1][5] = null; já é null para Pessoa criada!
				// Não houve orientação para a atualização mudar o tipo.
			}
			
			System.out.println("\nUsuário atualizado.");
			menu();
			
		} else {
			System.out.println("\nUsuário não existe! Verifique a lista do Menu.");
			menu();
		}
				
	}

	
	public static void deletarUsuario(int num) {
		
		if (num > 0 && num <= id) {
			
			String[][] novaArray = new String[20][6];
			for (int i = 0; i < num; i++) {
				
				for (int j = 0; j < 6; j++) { // Pegar de nome até nota final de cada id (ANTES da posição);
					
					novaArray[i][j] = listaTotal[i][j];
				}
			}
			
			for (int i = num + 1; i <= id; i++) {
				
				for (int j = 0; j < 6; j++) { // Pegar de nome até nota final de cada id (DEPOIS da posição);
					
					novaArray[i][j] = listaTotal[i][j];
				}
			}
			
			// A linha da Array representada por 'num' não foi inclusa acima na criação da nova Array:
			listaTotal = novaArray;
			
			// Ficamos com um id a menos ao deletar um usuário (para não listar nulls)
			id--;
			
			System.out.println("\nUsuário deletado.");		
			
			menu();
			
		} else {
			System.out.println("\nUsuário não existe! Verifique a lista do Menu.");
			menu();
		}
	}

	
	public static void encerrar() {
		System.out.println("\nPrograma Encerrado.");
		entrada.close();
	}
	
}
