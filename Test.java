import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// On ouvre notre fichier et place les ips dans une liste
		String path = "C:\\Users\\adrie\\Documents\\A2\\Algo\\log20170630\\log20170630.csv";
		//System.out.println("Nombre d'ips : "+ips.size());
		boolean exit = false;
		Scanner scanner = new Scanner(System.in);
		while(!exit) {
			System.out.println("\nChoix :");
			System.out.println("1 : pour effectuer un comptage exact");
			System.out.println("2 : pour effectuer un comptage exact en utilisant une hashmap");
			System.out.println("3 : pour utiliser le filtre de bloom");
			System.out.println("4 : pour quitter");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				//On effectue un comptage exact
				System.out.println("\nAvec un comptage exact");
				long startTime = System.currentTimeMillis();
				List<String> ips = OpenFile.openFile(path);
				List<IP> result1 = FrequencyOfAddress.findFrequencies(ips);
				long endTime = System.currentTimeMillis();
				// On affiche une partie des résultats pour vérifier
				for(int i=0;i<600;i+=100){	
					System.out.println("IP : "+ips.get(i)+" - Fréquence : "+result1.get(i).getFrequency());
				}
				System.out.println(endTime-startTime+"ms");
				break;
				
			case 2:
				//On effectue un comptage exact
				System.out.println("\nAvec un comptage exact en utilisant une hashmap");
				startTime = System.currentTimeMillis();
				Map<String,Integer> result2 = FrequencyOfAddressHashMap.findFrequencies(path);
				endTime = System.currentTimeMillis();
				// On affiche une partie des résultats pour vérifier
				for(int i=0;i<600;i+=100){	
					//System.out.println("IP : "+ips.get(i)+" - Fréquence : "+result2.get(ips.get(i)));
				}
				System.out.println(endTime-startTime+"ms");
				break;

			case 3:
				//Avec le filtre de bloom cette fois
				int tailleBloom=330684515;
				int nbHash=10;
				System.out.println("\nAvec filtre de bloom de taille "+tailleBloom+" avec "+nbHash+" fonctions de hashage");
				startTime = System.currentTimeMillis();
				Bloom b = new Bloom(tailleBloom,nbHash);
				b.initBloomWithFile(path);
				endTime = System.currentTimeMillis();
				// On affiche une partie des résultats pour vérifier
				for(int i=0;i<600;i+=100){	
					//System.out.println("IP : "+ips.get(i)+" - Fréquence : "+b.countIp(ips.get(i)));
				}
				System.out.println(endTime-startTime+"ms");
				break;
			case 4:
				exit=true;
				break;
			default:
				exit=true;
				break;
			}
		}




	}

}
