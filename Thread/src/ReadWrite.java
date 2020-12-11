import java.io.*;
import static java.lang.Math.toIntExact;
import java.nio.*;
import java.nio.channels.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadWrite implements Runnable
{
    private FileChannel channel;
    private FileChannel channel1;
    private FileChannel channel2;
    private long startLocation;
    private int size;
    int sequenceNumber;

    public ReadWrite(long startLocation, int size, FileChannel channel, FileChannel channel1, FileChannel channel2, int sequenceNumber)
    {
        this.startLocation = startLocation;
        this.size = size;
        this.channel = channel;
        this.channel1 = channel1;
        this.channel2 = channel2;
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println("Reading the channel: " + startLocation + ":" + size);

            //allocate memory
            ByteBuffer buff = ByteBuffer.allocate(2);

            int i = 1;
            while(channel.read(buff) != -1) {
                buff.flip();
                if (i % 2 == 0)
                    channel1.write(buff);
                else
                    channel2.write(buff);
                buff.clear();
                i++;
            }

            //chunk to String

            System.out.println("Done Reading the channel: " + startLocation + ":" + size);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception
    {
        final long timeStart = System.currentTimeMillis();
        FileInputStream fileInputStream = new FileInputStream("A.txt");
        FileOutputStream fileOutputStream1 = new FileOutputStream("E.txt");
        FileOutputStream fileOutputStream2 = new FileOutputStream("F.txt");
        FileChannel channel = fileInputStream.getChannel();
        FileChannel channel1 = fileOutputStream1.getChannel();
        FileChannel channel2 = fileOutputStream2.getChannel();
        long remaining_size = channel.size(); //get the total number of bytes in the file
        long chunk_size = remaining_size / 1; //file_size/threads

        //thread pool
        ExecutorService executor = Executors.newFixedThreadPool(1);

        long start_loc = 0;//file pointer
        int i = 0; //loop counter
        while (remaining_size >= chunk_size)
        {
            //launches a new thread
            executor.execute(new ReadWrite(start_loc, toIntExact(chunk_size), channel, channel1, channel2, i));
            remaining_size = remaining_size - chunk_size;
            start_loc = start_loc + chunk_size;
            i++;
        }

        //Tear Down
        executor.shutdown();

        //Wait for all threads to finish
        while (!executor.isTerminated())
        {
            //wait for infinity time
        }
        System.out.println("Finished all threads");
        final long timeEnd = System.currentTimeMillis();
        System.out.println("Thời gian đọc file A: " + (timeEnd - timeStart) + "ms");
        fileInputStream.close();
        fileOutputStream1.close();
        fileOutputStream2.close();
    }

}
