import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("ファイルのパスを入力してください");

		String path = br.readLine();
		
		br.close();
		
		if(path.length() == 0) {
			System.out.println("ファイルのパスが入力されていません");
			System.exit(-1);
		}
		
		File file = new File(path);
		
		if(!file.exists()) {
			System.out.println("ファイルが存在しません");
			System.exit(-1);
		}
		
		List<String> lines = new ArrayList<>();
		List<String> result = new ArrayList<>();

		br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
		String str;
		while((str = br.readLine()) != null) {
			lines.add(str);
		}
		br.close();
		
		for(int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if(line.matches("[0-9]{1,}")){
				result.add(line);
			} else {
				result.add(line.replaceAll("[A-Za-z!\":#\\$%&'\\[\\]\\(\\)=~\\|\\-\\^`\\{\\}\\*\\+<>\\?_@;,\\.\\/'\\\\ ,]",""));
			}
		}

		for(String data : lines){
			System.out.println(data);
		}
		
		for(String data : result){
			if(data.length() != 0) {
				System.out.println(data);
			}
		}

		file = new File(System.getProperty("user.home") + "/Desktop/replaced.txt");
		if(file.exists()) {
			file.delete();
		}
		
		file.createNewFile();
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		for(String data : result){
			if(data.length() != 0) {
				pw.println(data);
			}
		}
		pw.flush();
		pw.close();
	}

}
