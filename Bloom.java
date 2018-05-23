import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bloom {
	/**
	 * Taille du tableau de notre filtre
	 */
	public int m;

	/**
	 * Nombre de fonctions de hashage
	 */
	public int k;

	/**
	 * Notre de tableau d'entier 
	 * (et non de bit puisqu'il s'agit d'un "Counting bloom filter")
	 */
	public int[] bloom;

	/**
	 * @param m : Taille du tableau de notre filtre.
	 * @param k : Nombre de fonctions de hashage.
	 */
	public Bloom(int m, int k) {
		this.m=m;
		this.k=k;
		this.bloom= new int[m];
	}

	/**
	 * @param ip : L'adresse ip � hasher.
	 * @return Les k empreintes de notre ip. 
	 */
	public int[] hashk(String ip) {
		int[] result = new int[k];
		for(int i=0;i<k;i++) {
			result[i]=ip.hashCode()+i;
		}
		return result;
	}

	/**
	 * @param ip : L'adresse ip de l'�l�ment � ajouter.
	 */
	public void addIp(String ip) {
		int[] hashes = hashk(ip);
		for(int i=0; i<k; i++) {
			int position = Math.abs(hashes[i]%m);
			this.bloom[position]++;
		}
	}

	/**
	 * @param ip : L'adresse ip de l'�l�ment � supprimer.
	 */
	public void deleteIp(String ip) {
		if(checkIp(ip)) {
			int[] hashes = hashk(ip);
			for(int i=0; i<k; i++) {
				int position = Math.abs(hashes[i]%m);
				this.bloom[position]--;
			}
		}
	}

	/**
	 * @param ip : L'adresse ip de l'�l�ment � v�rifier.
	 */
	public boolean checkIp(String ip) {
		int[] hashes = hashk(ip);
		for(int i=0; i<k; i++){
			int position = Math.abs(hashes[i]%m);
			if(bloom[position]==0)
				return false;
		}
		return true;
	}

	/**
	 * @param ip : L'adresse ip de l'�l�ment � compter.
	 */
	public int countIp(String ip) {
		int[] hashes = hashk(ip);
		List<Integer> count = new ArrayList<Integer>();
		for(int i=0; i<k; i++){
			int position = Math.abs(hashes[i]%m);
			count.add(bloom[position]);
		}
		if(count.size()>0) {
			return Collections.min(count);
		}
		else {
			return 0;
		}
	}

	/**
	 * 
	 * @param ips : List des ips � ajouter pour initialiser le filtre.
	 */
	public void initBloom(List<String> ips) {
		for(String ip : ips) {
			addIp(ip);
		}
	}
}