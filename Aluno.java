package br.com.crud.modelos;

import java.util.Date;

public class Aluno extends Pessoa {

	private Double notaFinal;
		
	public Aluno(String nome, String telefone, String nascimento, Date cadastro, Date alteracao, Double notaFinal) {
		super(nome, telefone, nascimento, cadastro, alteracao);
		this.notaFinal = notaFinal;
	}

	public Double getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(Double notaFinal) {
		this.notaFinal = notaFinal;
	}	
	
}
