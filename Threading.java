import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Threading implements Runnable {
	LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
	String file;
	Boolean stop = false;
	
	public Threading(String file) {
		this.file = file;
	}

	public void addQueue(String row) {
		queue.add();
	}
	
	public void Stop() {
		stop = true;
	}
	
	public void run() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			while(!stop) {
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