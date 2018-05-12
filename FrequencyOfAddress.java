import java.util.ArrayList;

public class FrequencyOfAddress {

	public static ArrayList<IP> findFrequencies(ArrayList<String> ips){
		ArrayList<IP> result = new ArrayList<IP>();
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
