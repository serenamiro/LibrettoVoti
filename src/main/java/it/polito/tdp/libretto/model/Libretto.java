package it.polito.tdp.libretto.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe Libretto, memorizza e gestisce i voti superati.
 * @author Asus
 *
 */
public class Libretto {
	
	private List<Voto> voti = new ArrayList<>();
		
	public Libretto() {
		super();
	}
	
	/**
	 * Copy constructor
	 * "Shallow" (copia superficiale)
	 * @param lib
	 */
	public Libretto(Libretto lib) {
		super();
		this.voti.addAll(lib.voti);
	}

	/**
	 * Aggiunge un voto al libretto
	 * @param v voto da aggiungere
	 * @return {@code true} se ha inserito il voto, {@code false} se 
	 * non l'ha inserito perchè era in conflitto oppure era duplicato.
	 */
	public boolean add(Voto v) {
		// avrei potuto passare invece che il voto i dati che compongono il voto
		// e creare il voto all'interno di questo metodo, ma non è consigliabile
		// dal momento che in futuro potrei vole modificare la classe Libretto
		// o la classe Voto, per cui meglio passare solo oggetti.
		
		if(this.isConflitto(v) || this.isDuplicato(v)) {
			// non inserire il voto
			return false; // segnala al chiamante che l'operazione non ha avuto successo
		} else {
			// inserisci il voto perchè non è nè in conflitto nè duplicato
			this.voti.add(v); // OPERAZIONE DI DELEGA
			return true;
		}
	}
	
	public String toString() {
		String s = "";
		for (Voto v : this.voti) {
			s += v.toString() + "\n";
		}
		return s;
	}
	
	
	/**
	 * Dato un libretto, restituisce una stringa nella quale vi sono solamente
	 * i voti pari al valore specificato
	 * @param voto valore specificato
	 * @return stringa formattata per visualizzare il sotto-libretto
	 */
	public String stampaVotiUguali(int voto) {
		String s = "";
		for (Voto v : this.voti) {
			if(v.getVoto() == voto)
				s += v.toString() + "\n";
		}
		return s;
	}
	
	/**
	 * Genera un nuovo libretto, a partire da quello esistente, che 
	 * conterrà esclusivamente i voti con votazione pari a quella specificata
	 * @param voto votazione specificata
	 * @return Libreto "ridotto"
	 */
	public Libretto estraiVotiUguali(int voto) {
		Libretto nuovo = new Libretto();
		for (Voto v : this.voti) {
			if (v.getVoto() == voto)
				nuovo.add(v);
		}
		return nuovo;
	}

	/**
	 * Dato il nome di un corso, ricerca se quell'esame esiste nel libretto,
	 * ed in caso affermativo restituisce l'oggetto {@link Voto} corrispondente.
	 * Se l'esame non è presente nel libretto, il metodo restituisce {@code null}.
	 * @param nomeCorso nome dell'esame da cercare
	 * @return {@link Voto} corrispondente, oppure {@code null}
	 */
	public Voto cercaNomeCorso(String nomeCorso) {
		/* for (Voto v: this.voti) {
			if (nomeCorso.equals(v.getNomeCorso())) {
				return v;
			}
		}
		return null;
		*/
		int pos = this.voti.indexOf(new Voto(nomeCorso, 0, null));
		if(pos != -1) {
			return this.voti.get(pos);
		} else {
			return null;
		}
	}
	
	
	/**
	 * Ritorna {@code true} se il corso specificato da {@code v} esiste
	 * nel libretto, con la stessa valutazione. Se non esiste, o se la 
	 * valutazione è diversa, ritorna {@code false}.
	 * @param v il {@link Voto} da ricercare 
	 * @return l'esistenza di un duplicato
	 */
	public boolean isDuplicato(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getNomeCorso());
		if(esiste == null) {
			// corso non esistente -> non è duplicato
			return false;
		} 
		/*
		if(esiste.getVoto() == v.getVoto()) {
			return true;
		} else {
			return false;
		}
		*/
		return (esiste.getVoto() == v.getVoto());
	}
	
	/**
	 * determina se esiste un voto con lo stesso nome corso ma 
	 * valutazione diversa.
	 * @param v
	 * @return
	 */
	public boolean isConflitto(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getNomeCorso());
		if(esiste == null) {
			// corso non esistente -> non è duplicato
			return false;
		}
		return (esiste.getVoto() != v.getVoto());
	}
	
	
	/**
	 * Restituisce un nuovo libretto migliorando i voti del libretto attuale
	 * @return
	 */
	public Libretto creaLibrettoMigliorato() {
		Libretto nuovo = new Libretto();
		for(Voto v : this.voti) {
			Voto v2 = v.clone(); // usando il metodo clone()
			// alternativamente:
			// Voto v2 = new Voto(v); usando il copy constructor
			
			if(v2.getVoto() >= 24) {
				v2.setVoto(v2.getVoto() +2);
				if(v2.getVoto()>30) {
					v2.setVoto(30);
				}
			} else if (v2.getVoto() >= 18) {
				v2.setVoto(v2.getVoto()+1);
			}
			nuovo.add(v2);
		}
		return nuovo;
	}
	
	
	/**
	 * riordina i voti presenti nel libretto corrente alfabeticamente per corso
	 */
	public void ordinaPerCorso() {
		Collections.sort(this.voti);
	}
	
	public void ordinaPerVoto() {
		Collections.sort(this.voti, new ConfrontaVotiPerValutazione());
	}
	
	/**
	 * Elimina dal libretto tutti i voti <24
	 */
	public void cancellaVotiScarsi() {
		// Non posso modificare il contenuto di una lista mentre sto iterando
		List<Voto> daRimuovere = new ArrayList<>();
		for(Voto v : this.voti) {
			if(v.getVoto()<24) {
				daRimuovere.add(v);
			}
		}
		/*
		for(Voto v : daRimuovere) {
			this.voti.remove(v);
		}
		*/
		this.voti.removeAll(daRimuovere);
	}
	
}
