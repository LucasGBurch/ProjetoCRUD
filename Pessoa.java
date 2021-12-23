package br.com.crud.modelos;

import java.util.Date;

public class Pessoa {
	
	private String nome;
	private String telefone;
	private String nascimento;
	private Date cadastro, alteracao;
	
	
	public Pessoa(String nome, String telefone, String nascimento, Date cadastro, Date alteracao) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.nascimento = nascimento;
		this.cadastro = cadastro;
		this.alteracao = alteracao;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getNascimento() {
		return nascimento;
	}
	
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	
	public Date getCadastro() {
		return cadastro;
	}
	
	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}
	
	public Date getAlteracao() {
		return alteracao;
	}
	
	public void setAlteracao(Date alteracao) {
		this.alteracao = alteracao;
	}
		
}
