package api.avaliadin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.DiscriminatorType;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("I")
public class Item {
	@Id
	@Column(name="idItem")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="titulo")
	private String titulo;
	
	@Column(name="ano")
	private String ano;
	
	@Column(name="pais")
	private String pais;
	
	@Column(name="dtCad")
	private Date dtCad;
	
	@Column(name="estado")
	private boolean estado;
	
	@Column(insertable=false, updatable=false)
    public String tipo;
	
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Date getDtCad() {
		return dtCad;
	}

	public void setDtCad(Date dtCad) {
		this.dtCad = dtCad;
	}
	
	
}
