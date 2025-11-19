package com.file.Xor;

import java.io.*;
import java.nio.ByteBuffer;

public class xor{
  
  /*
  by:云龙(WZYang) QQ:2165352584
  交流群 : 497543168
  */
  
  /*
  @传入: String
  inputPath:传入路径
  outputPath:输出路径
  key:加密/解密的密钥
  */
  
  public static void FileXor(String inputPath, String outputPath, String keys){
    try{
      byte[] key = keys.getBytes();
      //将key转换为字节
      InputStream inputStream = new FileInputStream(inputPath);
      OutputStream outputStream = new FileOutputStream(outputPath);
      int read;
      byte[] buffer = new byte[1024];
      while ((read = inputStream.read(buffer)) != -1) {
        for (int i = 0; i < read; i++) {
          //buffer[i] = (byte) (buffer[i] ^ key[i % key.length]);
          // 对每一个字节进行异或运算
          /* 加强算法(如果需要使用此算法请对上行代码用"//"屏蔽)
          使用加强算法可以有效防止被暴力破解,因为异或加密属于非常简单的对称加密容易被破解
          */
          
          int keyLen = key.length;
          int KeyLen_head = key[0];
          int KeyLen_tail = key[keyLen - 1];
          int keyi = key[i % key.length];
          
          int akey = (KeyLen_head ^ KeyLen_tail) ^ (KeyLen_head - KeyLen_tail) ^ keyLen - 1;
          int bkey = akey ^ keyi + 1;
          int endkey = akey ^ bkey ^ i;
          
          buffer[i] = (byte) (buffer[i] ^ endkey);
          
        }
        outputStream.write(buffer, 0, read);
      }
      inputStream.close();
      outputStream.flush();
      outputStream.close();
    }catch(IOException e){
      
    }
  }
}