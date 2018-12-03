package com.xico.pontointeligente.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;


//O annotation @Entity torna a classe empresa em uma entidade de BCO de dados.
@Entity

//O @Table define o nome da tabela no banco de dados para a entidade
@Table(name="empresa")

//A serialização significa salvar o estado atual dos objetos em arquivos em formato binário para o seu computador, 
//sendo assim esse estado poderá ser recuperado posteriormente recriando o objeto em memória assim como ele estava 
//no momento da sua serialização.

public class Empresa implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4364780392884408833L;
	private Long id;
	private String razaoSocial;
	private String cnpj;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private List<Funcionario> funcionarios;
	
	
	public Empresa() {
	}
	//O annotation @Id informa que o campo será chave primaria da tabela.
	@Id
	//O annotation @GeneratedValue informa como a chave primária será incrementada, no modo AUTO a chave é incrementada a cada nova inserção
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long Id) {
		this.id=id;
	}

	//O annotation @Column define um nome para o campo na tabela bem como se pode ser nulo ou não
	@Column(name="razao_social",nullable=false)
	public String getRazaoSocial() {
		return razaoSocial;
	}	

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial=razaoSocial;
	}

	@Column(name="cnpj", nullable=false)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj=cnpj;
	}
	
	@Column(name="data_criacao",nullable=false)
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao=dataCriacao;
	}
	
	@Column(name="data_atualizacao", nullable=false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	
	public void setDataAtualizacao(Date dataAtualizacao ) {
		this.dataAtualizacao=dataAtualizacao;
	}
	
	//Annotatio responsavél por especificar o relacionamento de um para muitos.
	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Funcionario> getFuncionarios(){
		return funcionarios;
	}
	
	//O annotation @PrePersist permite executar uma ação no objeto antes dele ser inserido.
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao= atual;
		dataAtualizacao= atual;
	}
	
	//O annotation @PreUpdate permite executar uma ação no objeto antes do update ser realizado.
	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}
	
	//O annotation @Override permite reescrever o método que foi herdado, onde o comportamento da classe pai é diferente do seu comportamento na classe filha
	//Exemplo o metodo to String abaixo que faz parte da classe object se ele não for reescrito terá o comportamento da classe object, se for reescrito terá
	//o comportamento da classe filha nesse caso a classe empresa.
	//A sobrescrita de um método  ocorre quando uma classe filha implementa um método que já existe numa classe mãe, alterando o comportamento existente.
	@Override
	public String toString(){
		return "Empresa[id="+id+", "
				+ "razaoSocial="+razaoSocial+", "
						+ "cnpj="+cnpj+", "
								+ "dataCriacao="+dataCriacao+", "
										+ "dataAtualizacao="+dataAtualizacao+"]";
		
	}
	
	
	
	
	
	
	
	
}
