import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyOfAddress {

	public static Map<String,Integer> findFrequencies(List<String> ips){
		Map<String,Integer> result = new HashMap<String,Integer>();
		String ip;
		boolean notFound=true;
		for(int i=0;i<ips.size();i++){
			ip = ips.get(i);
			if (result.containsKey(ip)) {
				int count = result.get(ip);
				count++;
				result.put(ip,count);
			} else {
				result.put(ip,1);
			}
		}
		return result;
	}
}
