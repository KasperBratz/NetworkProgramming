import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.regex.*;

public class main {
//http://cs.lth.se/eda095/foerelaesningar/?no_cache=1
	public static void main(String[] args) {
		URL url = null;
		ArrayList<URL> list = new ArrayList<>();
		String pattern = "<a href=\"[\\S]*?\\.pdf";
		
		Pattern p = Pattern.compile(pattern);
		Scanner scan = new Scanner(System.in);
		System.out.println("address: ");
		String s = scan.nextLine();
		try {
			url = new URL(s);
			InputStream is = url.openStream();
			BufferedReader bReader =
					new BufferedReader(new InputStreamReader(is));
			String line;
		
			//find pdfs from lines
			while ((line = bReader.readLine()) != null) {
				Matcher m = p.matcher(line);
				while(m.find()){
					URL u = new URL(url,m.group(0).substring(9));
					list.add(u);
					System.out.println(u.toString());
				} 
			}
			
			//Download matched files
			int i =0;
			for(URL u: list){
				try{
					InputStream in = u.openStream();
					Files.copy(in, Paths.get("File" + i + ".pdf"), StandardCopyOption.REPLACE_EXISTING);
					i++;
					in.close();
				}catch (IOException e) {}
				
			}
			
		} catch (IOException e) {
			System.out.println("Something went wrong :<!");
		}
		
		
			
			
		

	}

}
