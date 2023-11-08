package com.ll.standard.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil {
    public static String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            reader.close();

            System.out.println("파일 읽기 성공");
        }
        catch (IOException e) {
            System.out.println("파일 읽기 오류");
        }

        return sb.toString().trim();
    }

    public static void writeFile(String fileName, String fileContent) {
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            writer.print(fileContent);

            writer.close();

            System.out.println("파일 쓰기 성공");
        }
        catch (IOException e) {
            System.out.println("파일 쓰기 오류");
        }
    }
}
