package br.com.crud.execucao;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.crud.modelos.Aluno;
import br.com.crud.modelos.Pessoa;

public class CrudExec {

	
	// ---------------ATRIBUTOS EST�TICOS:---------------
	
	// Vari�vel para receber dados:
	private static Scanner entrada = new Scanner(System.in);
	
	// Controle de registro:
	private static int id = 0;
	
	// Array com seis informa��es para pessoa/aluno.
	// Limitei para 20 linhas de registro, j� que � s� um programa na mem�ria;
	private static String[][] listaTotal = new String[20][6];
	
	// Vari�vel de menu:
	private static int opcao;
	
	public static void main(String[] args) {
		
		System.out.println("Bem-vindo ao sistema de cadastro!");		
		
		try {
			menu();
		
			if (opcao < 0 || opcao == 0 || opcao > 5) { // (fazer opcao <= 0 n�o funcionou)
				System.out.println("Op��o inv�lida! Tente de novo:");
				menu();
			}
			
		} catch (InputMismatchException e) {
			System.out.println("\nAlerta de erros poss�veis:"
					+ "\n1) O menu inicial s� aceita n�meros inteiros;"
					+ "\n2) Se estiver usando windows, o decimal da nota s� aceitar� v�rgula!"
					+ "\nReinicie o programa.");
		}
	}
	
	// ---------------MENU DA APLICA��O:---------------
	
	public static void menu() {
		System.out.println("---- Menu de Op��es ----");
		System.out.println("1. Criar novo Usu�rio");
		System.out.println("2. Listar usu�rios cadastrados");
		System.out.println("3. Atualizar Usu�rio");
		System.out.println("4. Deletar Usu�rio");
		System.out.println("5. Encerrar Sistema");
		
		
		System.out.print("Insira o n�mero da op��o desejada: ");
		
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
				System.out.print("Informe o n�mero do usu�rio na lista para atualizar: ");
				int num = entrada.nextInt();
				atualizarUsuario(num);
				break;
			}
			case 4: {
				System.out.print("Informe o n�mero do usu�rio na lista para deletar: ");
				int num = entrada.nextInt();
				deletarUsuario(num);
				break;
			}
			case 5: {
				encerrar();
				break;
			}
			// Motivo para n�o usar o Default:
			// Precisava chamar o menu novamente (o que n�o era poss�vel dentro do pr�prio m�todo)
		}
	}
	
	// ---------------M�TODOS DA APLICA��O:---------------
	
	public static void criarUsuario() {
		id++; // id novo criado;
		
		int ind = id - 1; //  - 1 para a array aproveitar a posi��o 0;
		
		System.out.print("\nInsira o Nome: ");
		String nome = entrada.next();
		
		System.out.print("Insira o Telefone: ");
		String tel = entrada.next();
		
		System.out.print("Insira data de nascimento (dd/mm/yyyy): ");
		String nasc = entrada.next();
		
		System.out.print("Deseja inserir nota? Digite sim para inserir\n(ou qualquer outra resposta se n�o quiser): ");
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
			
		System.out.println("\nUsu�rio tipo " + tipo + " criado:"
				+ "\nId: " + id
				+ "\nNome: " + nome);
		
		menu();
	}

	
	public static void listarUsuarios() {
		
		if (id == 0) {
			System.out.println("\nN�o h� usu�rios para listar."
					+ " Voc� pode cri�-los usando a op��o 1.\n");
		} else {
			System.out.println("\nLista de Usu�rios.\nAlunos com nota"
					+ ", Pessoas com nota null:\n");
		
		for (int i = 0; i < id; i++) {
			
			System.out.println("N�mero na lista: " + (i+1));
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
						System.out.println("�ltima Atualiza��o: " + listaTotal[i][j] + "; ");
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
			
			// Date cad = new Date(); Retirado da atualiza��o.
			Date alt = new Date(); // Altera��o atualiza a data.
			
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
				// listaTotal[id - 1][5] = null; j� � null para Pessoa criada!
				// N�o houve orienta��o para a atualiza��o mudar o tipo.
			}
			
			System.out.println("\nUsu�rio atualizado.");
			menu();
			
		} else {
			System.out.println("\nUsu�rio n�o existe! Verifique a lista do Menu.");
			menu();
		}
				
	}

	
	public static void deletarUsuario(int num) {
		
		if (num > 0 && num <= id) {
			
			String[][] novaArray = new String[20][6];
			for (int i = 0; i < num; i++) {
				
				for (int j = 0; j < 6; j++) { // Pegar de nome at� nota final de cada id (ANTES da posi��o);
					
					novaArray[i][j] = listaTotal[i][j];
				}
			}
			
			for (int i = num + 1; i <= id; i++) {
				
				for (int j = 0; j < 6; j++) { // Pegar de nome at� nota final de cada id (DEPOIS da posi��o);
					
					novaArray[i][j] = listaTotal[i][j];
				}
			}
			
			// A linha da Array representada por 'num' n�o foi inclusa acima na cria��o da nova Array:
			listaTotal = novaArray;
			
			// Ficamos com um id a menos ao deletar um usu�rio (para n�o listar nulls)
			id--;
			
			System.out.println("\nUsu�rio deletado.");		
			
			menu();
			
		} else {
			System.out.println("\nUsu�rio n�o existe! Verifique a lista do Menu.");
			menu();
		}
	}

	
	public static void encerrar() {
		System.out.println("\nPrograma Encerrado.");
		entrada.close();
	}
	
}
