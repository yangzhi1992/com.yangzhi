package java8.java03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Java3_3 {
    public static void main(String[] args) throws IOException {
        String string = processFile();
        String string2 = processFile2((BufferedReader::readLine));
        String string3 = processFile2((BufferedReader br) -> br.readLine() + br.readLine());
    }

    public static String processFile() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }

    public static String processFile2(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }
}
