import java.util.ArrayList;
import java.util.List;

public class FrequencyOfAddress {
	/**
	 * 
	 * @param ips : liste d'adresse ip
	 * @return une liste d'objet IP avec l'adresse et la fr�quence associ�e
	 */
	public static List<IP> findFrequencies(List<String> ips){
		List<IP> result = new ArrayList<IP>();
		String ip;
		boolean notFound=true;
		for(int i=0;i<ips.size();i++){
			ip = ips.get(i);
			for(int j=0;j<result.size();j++){
				if(ip.equals(result.get(j).getAddress())){
					IP newFrequency=result.get(j);
					newFrequency.setFrequency(newFrequency.getFrequency()+1);
					result.set(j, newFrequency);
					notFound=false;
					break;
				}
			}
			if(notFound){
				IP newIp=new IP(ip);
				result.add(newIp);
			}
			notFound=true;
		}
		return result;
	}
}