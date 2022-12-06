package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcesser {
    private BufferedReader reader;

    public FileProcesser(String fileName) {
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            System.err.println("Provided file name was invalid");
        }
    }

    public String getNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeReader() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
