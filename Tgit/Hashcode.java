import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/*
Hashcode提供KV文件的key值生成算法
 */

public class Hashcode {
    private byte[] sha1; // sha1最初是一个字节数组
    private String out; // 字节数组最后需要转换为一个40位的字符串


    public Hashcode(File inFile) throws Exception { // SHA1类封装依据文件内容生成的hashcode(字节数组形式)
        FileInputStream input = new FileInputStream(inFile);
        this.sha1 = Sha1(input);
    }

    public Hashcode(String content) throws Exception { // SHA1类封装依据字符串生成的hashcode(字节数组形式)
        this.sha1 = Sha1(content);
    }

    public static byte[] Sha1(InputStream is) throws Exception { // 具体的sha1算法，传入文件，计算消息摘要，返回的是字符数组
        byte[] buffer = new byte[1024]; // 采用缓冲器读取文件
        MessageDigest complete = MessageDigest.getInstance("SHA-1");
        int numRead = 0;
        do {
            numRead = is.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);
        is.close();
        return complete.digest();
    }


    public static byte[] Sha1(String content) throws Exception { // 具体的sha1算法，传入字符串，计算消息摘要，返回的是字符数组
        MessageDigest complete = MessageDigest.getInstance("SHA-1"); 
        complete.update(content.getBytes());
        return complete.digest();
    }


    public String getSha1() { // 由于SHA1类实例对象封装了字节数组形式的hashcode，调用无参tostring方法，得到字符串形式的sha1值
        out = "";
        int n = sha1.length;
        for (int i = 0; i < n; i++) {
            String append = Integer.toString(sha1[i] & 0xFF, 16); // 为防止出现负数符号需要与0xFF
            if (append.length() < 2) {
                out = out + "0" + append;  // 不足2位的16进制数用0补足
            }
            else {
                out += append;
            }
        }
        return out;
    }
}
