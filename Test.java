import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// On ouvre notre fichier et place les ips dans une liste
		List<String> ips = OpenFile.openFile("C:/Users/adrie/Documents/workspace-Algo/Traces-IP-Projet.csv");
		System.out.println("Nombre d'ips : "+ips.size());
		
		System.out.println("\nAvec un comptage exact");
		
		//On effectue un comptage exact
		List<IP> result = FrequencyOfAddress.findFrequencies(ips);
		// On affiche une partie des r�sultats pour v�rifier
		for(int i=0;i<5;i++){	
			System.out.println("IP : "+result.get(i).getAddress()+" - Fr�quence : "+result.get(i).getFrequency());
		}
		
		System.out.println("\nAvec filtre de bloom");
		
		//Avec le filtre de bloom cette fois
		Bloom b = new Bloom(3354771,23);
		b.initBloom(ips);
		// On affiche une partie des r�sultats pour v�rifier
		for(int i=0;i<5;i++){	
			System.out.println("IP : "+result.get(i).getAddress()+" - Fr�quence : "+b.countIp(result.get(i).getAddress()));
		}
	}

}
