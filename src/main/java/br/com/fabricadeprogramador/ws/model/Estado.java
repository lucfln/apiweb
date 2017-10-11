package br.com.fabricadeprogramador.ws.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;

@Entity
public class Estado {
	@Id
	@GeneratedValue
	private Integer iduf;
	private String uf;
	
	public Integer getIduf() {
		return iduf;
	}
	public void setId(Integer id) {
		this.iduf = id;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
	
	
	

}
