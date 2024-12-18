package io.buffered;

import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

public class CreateFileV2 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bufferIdx = 0;

        for(int i=0; i<FILE_SIZE; i++){
            buffer[bufferIdx] = 65;
            bufferIdx++;

            // 버퍼가 가득 차면 쓰고, 버퍼를 비운다.
            if(bufferIdx == BUFFER_SIZE){
                fos.write(buffer);
                bufferIdx = 0;
            }
        }

        // 끝 부분에 오면 버퍼가 가득차지 않고 남아있을 수 있다. 버퍼에 남은 부분 쓰기
        if(0 < bufferIdx){
            fos.write(buffer, 0, bufferIdx);
        }

        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File name: " + FILE_NAME);
        System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
