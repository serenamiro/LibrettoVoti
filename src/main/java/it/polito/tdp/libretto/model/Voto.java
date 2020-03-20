package it.polito.tdp.libretto.model;

import java.time.LocalDate;

/**
 * Classe Voto, contiene le informazioni su un esame superato.
 * @author Asus
 *
 */
public class Voto implements Comparable<Voto>{
	
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
	
	/**
	 * Copy constructor di {@link Voto}: crea un nuovo {@link Voto},
	 *  copiando il contenuto del parametro {@code v}.
	 * @param v il voto da copiare
	 */
	public Voto(Voto v) {
		// non serve accedere i campi con il getter per accedere 
		// agli attributi di un oggetto dello stesso tipo
		this.nomeCorso = v.nomeCorso;
		this.voto = v.voto;
		this.data = v.data;

		// stringhe e interi sono immutabili, per cui con queste istruzioni
		// creo delle copie delle informazioni che mi servono. Se così non fosse,
		// per creare delle copie e non condividere i riferimenti, si potrebbe
		// usare i copy constructor:
		// this.nomeCorso = new String(v.nomeCorso);
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
	
	/**
	 * Crea una copia (clone) dell'oggetto presente (this), come nuovo oggetto
	 */
	public Voto clone() {
		Voto v = new Voto(this.nomeCorso, this.voto, this.data);
		return v;
	}

	@Override
	public int compareTo(Voto o) {
		/*
		 * < 0 se this < other
		 * == 0 se this == other
		 * > 0 se this > other
		 */
		return this.nomeCorso.compareTo(o.nomeCorso);
	}
	
}
