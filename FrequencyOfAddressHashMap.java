import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FrequencyOfAddressHashMap {
	/**
	 * 
	 * @param path : chemin d'accès du fichier de données
	 * @return une hasmap avec comme clef une adresse ip et comme valeurs son nombre d'occurence
	 */
	public static Map<String,Integer> findFrequencies(String path){
		Map<String,Integer> result = new HashMap<String,Integer>();
		String ip;
		try(Scanner scan = new Scanner(FileSystems.getDefault().getPath(path))){
			String line = scan.nextLine();
			while(scan.hasNextLine()) {
				line = scan.nextLine();
				ip =line.split(",")[0];
				if (result.containsKey(ip)) {
					int count = result.get(ip);
					count++;
					result.put(ip,count);
				} else {
					result.put(ip,1);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
		
	}
}
