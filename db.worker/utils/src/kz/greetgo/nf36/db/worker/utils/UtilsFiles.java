package kz.greetgo.nf36.db.worker.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

public class UtilsFiles {
  public static String fileToStr(File file) {
    try {
      return streamToStr(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public static String streamToStr(InputStream inStream) {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    if (copyStreamsAndCloseIn(inStream, outStream) == 0) return "";
    return outStream.toString(UTF_8);
  }

  public static long copyStreamsAndCloseIn(InputStream inStream, OutputStream outStream) {
    return copyStreamsAndCloseIn(inStream, outStream, 4 * 1024);
  }

  public static long copyStreamsAndCloseIn(InputStream inStream, OutputStream outStream, int bufferSize) {
    byte[] buffer = new byte[bufferSize];
    long copiedBytes = 0;
    try {
      while (true) {
        int count = inStream.read(buffer);
        if (count < 0) {
          inStream.close();
          return copiedBytes;
        }
        outStream.write(buffer, 0, count);
        copiedBytes += count;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
