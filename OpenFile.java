import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OpenFile {
	public static List<String> openFile(String path){
		List<String> ips = new ArrayList<String>();
		try(Scanner scan = new Scanner(FileSystems.getDefault().getPath(path))){
			String line = scan.nextLine();
			while(scan.hasNextLine()) {
				line = scan.nextLine();
				ips.add(line.split(",")[0]);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ips;
	}
}
