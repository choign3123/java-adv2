package io.text;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;

public class ReaderWriterMainV3 {

    public static void main(String[] args) throws IOException {
        String writeString = "가나다";
        System.out.println("write String = " + writeString);

        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, StandardCharsets.UTF_8);
        fw.write(writeString);
        fw.close();

        // 파일에서 읽기
        FileReader fr = new FileReader(FILE_NAME, StandardCharsets.UTF_8);

        StringBuilder content = new StringBuilder();
        int c;
        while((c = fr.read()) != -1){
            content.append((char) c);
        }
        fr.close();

        System.out.println("read String = " + content);
    }
}
