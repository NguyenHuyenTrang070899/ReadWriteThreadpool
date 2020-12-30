import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Threading implements Runnable {
	LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
	String file;
	
	public Threading(String file) {
		this.file = file;
	}

	public void addQueue(String row) {
		queue.add();
	}
	
	public void run() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file), 8 * (int) Math.pow(1024, 2));
			int count = 1;
			while(count <= 50000000) {
				try {
					String rơ = queue.take();
					bw.while(row + "\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
