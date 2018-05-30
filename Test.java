import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		// Chemin d'acc�s du fichier
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
					System.out.println("IP : "+ips.get(i)+" - Fr�quence : "+result1.get(i).getFrequency());
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
				System.out.println("IP : 101.81.133.jja - Fr�quence : "+result2.get("101.81.133.jja"));
				System.out.println("IP : 165.124.130.jdj - Fr�quence : "+result2.get("165.124.130.jdj"));
				System.out.println("IP : 68.180.230.ede - Fr�quence : "+result2.get("68.180.230.ede"));
				System.out.println(endTime-startTime+"ms\n"+(memory2-memory1)/1024+"ko");
				break;

			case 3://Avec le filtre de bloom
				
				System.out.println("Choisissez la taille du filtre (0 pour d�faut)");
				int tailleBloomChoisi = scanner.nextInt(), tailleBloom=2622794, nbHash=23;
				if(tailleBloomChoisi!=0)
					tailleBloom=tailleBloomChoisi;
				System.out.println("Choisissez le nombre de fonction de hashage (0 pour d�faut)");
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
				// On affiche une partie des r�sultats pour v�rifier
				System.out.println("IP : 101.81.133.jja - Fr�quence : "+b.countIp("101.81.133.jja")+" (fr�quence exact : 161133)");
				System.out.println("IP : 165.124.130.jdj - Fr�quence : "+b.countIp("165.124.130.jdj")+" (fr�quence exact : 354526)");
				System.out.println("IP : 68.180.230.ede - Fr�quence : "+b.countIp("68.180.230.ede")+" (fr�quence exact : 109301)");
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
