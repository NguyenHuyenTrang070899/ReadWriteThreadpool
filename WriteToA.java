import java.io.*;
import java.util.List;

public class WriteToA {
	public void writeFileA (File file) throws IOException {
		long start = System.currentTimeMillis();
		BufferredWriter bw = new BufferedWriter(new FileWriter(file), 8 * (int) Math.pow(1024, 2));
		int size = 100000000;
		for (int i = 1; i <= size; i++) {
			bw.write(Integer.toString(i) + "\n");
		}
		bw.close();
		long end = System.currentTimeMillis();
		System.out.println("Time to write file A: " + ((end - start)/1000) + "s");
	}
}
