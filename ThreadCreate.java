import java.io.*;
import java.util.List;

public class ThreadCreate {
	public void startThread(File file) {
		Threading t1 = new Threading("B.txt");
		Threading t1 = new Threading("C.txt");
		Thread td1 = new Thread(t1);
		Thread td2 = new Thread(t2);
		td1.start();
		td2.start();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			long start = System.currentTimeMillis();
			while ((line = br.readLine()) != null) {
				if (Integer.parseInt(line) % 2 == 0) {
					t1.addQueue(line);
				} else {
					t2.addQueue(line);
				}
			}
			br.close();
			long end = System.currentTimeMillis();
			System.out.println("Time to read file A and write file B, C: " + ((end - start)/1000) + "s");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
			
