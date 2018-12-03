package com.xico.pontointeligente.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xico.pontointeligente.api.enums.TipoEnum;

@Entity
@Table(name="lancamento")
public class Lancamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8887239953046042775L;

	private Long id;
	private Date data;
	private String descricao;
	private String localizacao;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private TipoEnum tipo;
	private Funcionario funcionario;
	
	public Lancamento() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	//@Temporal annotatio utilizado para informar que o atribuito contém um valor do tipo data.
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data", nullable=false)
	public Date getData() {
		return  data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	@Column(name="descricao", nullable=false)
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name="localizacao", nullable=false)
	public String getLocalizacao() {
		return localizacao;
	}
	
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
	@Column(name="data_criacao", nullable=false)
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Date dataCriacacao) {
		this.dataCriacao = dataCriacacao;
	}
	
	@Column(name="data_atualizacao", nullable=false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo", nullable=false)
	public TipoEnum getTipo() {
		return tipo;
	}
	
	public void setTipo (TipoEnum tip) {
		this.tipo=tipo;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario feuncionario) {
		this.funcionario = funcionario;
	}
	
	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}
	
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao=atual;
		dataAtualizacao = atual;
	}
	
	@Override
	public String toString() {
		return "Lancamento[id="+id+", "
				+ "data="+data+", "
				+ "descricao="+descricao+", "
						+ "localizacao="+localizacao+", "
								+ "dataCriacao="+dataCriacao+", "
										+ "dataAtualizacao="+dataAtualizacao+", "
												+ "tipo="+tipo+", "
														+ "funcionario="+funcionario+"]";
	}	
}
