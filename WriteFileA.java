import java.io.*;
import java.util.List;

public class WriteFileA {
	public static void writeToA (File file) {
		long start = System.currentTimeMillis();
		BufferredWriter bw = new BufferedWriter(new FileWriter(file));
		int size = 100000000;
		for (int i = 1; i <= size; i++) {
			write(Integer.toString(i) + "\n"), bw);
		}
		bw.close();
		long end = System.currentTimeMillis();
		System.out.println("Time to write file A: " + ((end - start)/1000) + "s");
	}
	public static void writeFile (File file, List<String> letters) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			write(letters, bw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void write(List<String> letters, Writer w) throws IOException {
		for (String letter : letters) {
			w.write(letter);
		}
		w.close();
	}
	public static void write(String content, Writer w) throws IOException {
		w.write(content);
	}
}