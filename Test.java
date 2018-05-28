import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// On ouvre notre fichier et place les ips dans une liste
		List<String> ips = OpenFile.openFile("C:/Users/adrie/Documents/workspace-Algo/Traces-IP-Projet.csv");
		System.out.println("Nombre d'ips : "+ips.size());
		boolean exit = false;
		Scanner scanner = new Scanner(System.in);
		while(!exit) {
			System.out.println("\nChoix :");
			System.out.println("1 : pour effectuer un comptage exact");
			System.out.println("2 : pour utiliser le filtre de bloom");
			System.out.println("3 : pour quitter");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				//On effectue un comptage exact
				System.out.println("\nAvec un comptage exact");
				long startTime = System.currentTimeMillis();
				Map<String,Integer> result = FrequencyOfAddress.findFrequencies(ips);
				long endTime = System.currentTimeMillis();
				// On affiche une partie des résultats pour vérifier
				for(int i=0;i<600;i+=100){	
					System.out.println("IP : "+ips.get(i)+" - Fréquence : "+result.get(ips.get(i)));
				}
				System.out.println(endTime-startTime+"ms");
				break;

			case 2:
				//Avec le filtre de bloom cette fois
				int tailleBloom=3354771;
				int nbHash=23;
				System.out.println("\nAvec filtre de bloom de taille "+tailleBloom+" avec "+nbHash+" fonctions de hashage");
				startTime = System.currentTimeMillis();
				Bloom b = new Bloom(tailleBloom,nbHash);
				b.initBloom(ips);
				endTime = System.currentTimeMillis();
				// On affiche une partie des résultats pour vérifier
				for(int i=0;i<600;i+=100){	
					System.out.println("IP : "+ips.get(i)+" - Fréquence : "+b.countIp(ips.get(i)));
				}
				System.out.println(endTime-startTime+"ms");
				break;
			case 3:
				exit=true;
				break;
			default:
				exit=true;
				break;
			}
		}




	}

}
