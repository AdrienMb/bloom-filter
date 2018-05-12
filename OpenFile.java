import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Scanner;

public class OpenFile {
	public static ArrayList<String> openFile(String path){
		ArrayList<String> ips = new ArrayList<String>();
		try(Scanner scan = new Scanner(FileSystems.getDefault().getPath(path))){
			String line = scan.nextLine();
			while(scan.hasNextLine()) {
				line = scan.nextLine();
				ips.add(line.split(";")[0]);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ips;
	}
}
