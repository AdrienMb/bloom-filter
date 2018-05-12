import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ArrayList<String> ips = OpenFile.openFile("C:/Users/Adrien/Downloads/Traces-IP-Projet.csv");
		 ArrayList<IP> result = FrequencyOfAddress.findFrequencies(ips);
		 for(int i=0;i<50;i++){		// On affiche une partie des résultats pour vérifier
			 System.out.println("IP : "+result.get(i).getAddress()+" - Fréquence : "+result.get(i).getFrequency());
		 }
	}

}
