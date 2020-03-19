package it.polito.tdp.libretto.model;

import java.time.LocalDate;

/**
 * Classe Voto, contiene le informazioni su un esame superato.
 * @author Asus
 *
 */
public class Voto {
	
	private String nomeCorso; // "Tecniche di Programmazione"
	private int voto; // 28
	private LocalDate data; // 15/06/2020
	
	/**
	 * Costruisce un nuovo Voto.
	 * @param nomeCorso nome del corso superato
	 * @param voto valore del voto acquisito
	 * @param data data di superamento dell'esame
	 */
	public Voto(String nomeCorso, int voto, LocalDate data) {
		this.nomeCorso = nomeCorso;
		this.voto = voto;
		this.data = data;
	}

	public String getNomeCorso() {
		return nomeCorso;
	}

	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return nomeCorso + ": " + voto + " (" + data + ");";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeCorso == null) ? 0 : nomeCorso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		if (nomeCorso == null) {
			if (other.nomeCorso != null)
				return false;
		} else if (!nomeCorso.equals(other.nomeCorso))
			return false;
		return true;
	}
	
	
	
}
