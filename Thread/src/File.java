import java.io.*;

public class File {
    public void readFileA() {
        final long timeStart = System.currentTimeMillis();
        try {
            InputStream inputStream = new FileInputStream("A.txt");;
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            OutputStream outputStream1 = new FileOutputStream("B.txt");
            BufferedOutputStream bufferedOutputStream1 = new BufferedOutputStream(outputStream1);

            OutputStream outputStream2 = new FileOutputStream("C.txt");
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(outputStream2);

            for (int i = 1; i <= 100000000; i++) {
                int a = bufferedInputStream.read();
                bufferedInputStream.read(); // đọc kí tự xuống dòng
                if (i % 2 == 0) {
                    bufferedOutputStream1.write(a);
                    bufferedOutputStream1.write(13); // ghi kí tự xuống dòng
                } else {
                    bufferedOutputStream2.write(a);
                    bufferedOutputStream2.write(13);
                }
            }
            final long timeEnd = System.currentTimeMillis();
            System.out.println("Thời gian đọc file A và ghi vào file B, C: " + ((timeEnd - timeStart)/1000) + "s");
            bufferedInputStream.close();
            bufferedOutputStream1.close();
            bufferedOutputStream2.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFileA() {
        final long timeStart = System.currentTimeMillis();
        try {
            OutputStream outputStream = new FileOutputStream("A.txt");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            for (int i = 1; i <= 100000000; i++) {
                if (i % 2 == 0) {
                    bufferedOutputStream.write(50);
                    bufferedOutputStream.write(13); // ghi kí tự xuống dòng
                } else {
                    bufferedOutputStream.write(49);
                    bufferedOutputStream.write(13);
                }

            }
            final long timeEnd = System.currentTimeMillis();
            System.out.println("Thời gian ghi file A: " + ((timeEnd - timeStart)/1000) + "s");

            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            outputStream.close();
        } catch (IOException e) {

        } finally {

        }
    }
    public static void main(String args[]) {
        File f = new File();
        f.writeFileA();
        f.readFileA();
    }
}