import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		File file = new File("A.txt");
		
		//Write file A
		WriteToA w = new WriteToA();
		w.writeFileA(file);
		//Write file B, C
		ThreadCreate t = new ThreadCreate();
		t.startThread(file);
	}
}
