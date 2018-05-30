import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		// Chemin d'accès du fichier
		String path = "C:\\Users\\adrie\\Documents\\A2\\Algo\\log20170630\\log20170630.csv";
		
		boolean exit = false;
		Scanner scanner = new Scanner(System.in);
		while(!exit) {
			System.out.println("Choix :\n1 : pour effectuer un comptage exact\n2 : pour effectuer un comptage exact en utilisant une hashmap\n3 : pour utiliser le filtre de bloom\n4 : pour quitter");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: //Comptage exact
				System.out.println("\nAvec un comptage exact");
				long startTime = System.currentTimeMillis();
				long memory1 = Runtime.getRuntime().totalMemory();
				List<String> ips = OpenFile.openFile(path);
				List<IP> result1 = FrequencyOfAddress.findFrequencies(ips);
				long memory2 = Runtime.getRuntime().totalMemory();
				long endTime = System.currentTimeMillis();
				for(int i=0;i<3;i++){	
					System.out.println("IP : "+ips.get(i)+" - Fréquence : "+result1.get(i).getFrequency());
				}
				System.out.println(endTime-startTime+"ms\n"+(memory2-memory1)/1024+"ko");
				break;
				
			case 2://Comptage exact avec hashmap
				System.out.println("\nAvec un comptage exact en utilisant une hashmap");
				startTime = System.currentTimeMillis();
				memory1 = Runtime.getRuntime().totalMemory();
				Map<String,Integer> result2 = FrequencyOfAddressHashMap.findFrequencies(path);
				endTime = System.currentTimeMillis();
				memory2 = Runtime.getRuntime().totalMemory();
				System.out.println("IP : 101.81.133.jja - Fréquence : "+result2.get("101.81.133.jja"));
				System.out.println("IP : 165.124.130.jdj - Fréquence : "+result2.get("165.124.130.jdj"));
				System.out.println("IP : 68.180.230.ede - Fréquence : "+result2.get("68.180.230.ede"));
				System.out.println(endTime-startTime+"ms\n"+(memory2-memory1)/1024+"ko");
				break;

			case 3://Avec le filtre de bloom
				
				System.out.println("Choisissez la taille du filtre (0 pour défaut)");
				int tailleBloomChoisi = scanner.nextInt(), tailleBloom=2622794, nbHash=23;
				if(tailleBloomChoisi!=0)
					tailleBloom=tailleBloomChoisi;
				System.out.println("Choisissez le nombre de fonction de hashage (0 pour défaut)");
				int nbHashChoisi = scanner.nextInt();
				if(nbHashChoisi!=0)
					nbHash=nbHashChoisi;
				System.out.println("\nAvec filtre de bloom de taille "+tailleBloom+" avec "+nbHash+" fonctions de hashage");
				startTime = System.currentTimeMillis();
				memory1 = Runtime.getRuntime().totalMemory();
				Bloom b = new Bloom(tailleBloom,nbHash);
				b.initBloomWithFile(path);
				memory2 = Runtime.getRuntime().totalMemory();
				endTime = System.currentTimeMillis();
				// On affiche une partie des résultats pour vérifier
				System.out.println("IP : 101.81.133.jja - Fréquence : "+b.countIp("101.81.133.jja")+" (fréquence exact : 161133)");
				System.out.println("IP : 165.124.130.jdj - Fréquence : "+b.countIp("165.124.130.jdj")+" (fréquence exact : 354526)");
				System.out.println("IP : 68.180.230.ede - Fréquence : "+b.countIp("68.180.230.ede")+" (fréquence exact : 109301)");
				System.out.println(endTime-startTime+"ms\n"+(memory2-memory1)/1024+"ko");
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
