package com.bilgeadam.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the araba database table.
 * 
 */
@Entity
@NamedQuery(name="Araba.findAll", query="SELECT a FROM Araba a")
public class Araba implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String kapi;

	private String marka;

	private String model;

	private int pencere;

	public Araba() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKapi() {
		return this.kapi;
	}

	public void setKapi(String kapi) {
		this.kapi = kapi;
	}

	public String getMarka() {
		return this.marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPencere() {
		return this.pencere;
	}

	public void setPencere(int pencere) {
		this.pencere = pencere;
	}

}