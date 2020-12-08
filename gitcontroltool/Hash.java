package gitcontroltool;
import java.io.*;
import java.security.MessageDigest; // 导入消息摘要，提供信息摘要SHA1算法


public class Hash { 
// 将sha值的计算封装为一个类，该类中有成员属性sha1（初始sha值）和outFile（最终转为16进制的字符串）
// 提供基于文件内容（blob）和文件名或字符串（针对tree）的sha值计算和读取方法、提供字符数组转字符串的方法

    private byte[] sha1;
    public Hash(String inFile,boolean isFile) throws Exception{ // 构造该类的对象时，会根据是否是文件来初始化相应的对象私有成员属性
        if(isFile){
            FileInputStream input = new FileInputStream(inFile);
            this.sha1 = SHA1content(input); // 利用文件内容计算hash值
        }
        else{
            this.sha1 = SHA1name(inFile); // 用文件名计算hash值
        }
    }

	public static byte[] SHA1content(InputStream is) throws Exception { // @1.对一个具体文件，通过文件内容计算sha值的方法，即blob对象的key值
		byte[] buffer = new byte[1024]; // 采用一个字节数组做缓冲器
		MessageDigest complete = MessageDigest.getInstance("SHA-1"); // 指定SHA1算法
		int numRead = 0;
		do {
			numRead = is.read(buffer); // 从输入流中读buffer.length个字节给buffer，并且返回实际读取的字节数给整型变量numRead
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1); // numRead = -1代表文件读取完毕。需要关闭输入流，并且返回消息摘要字节数组。
		is.close();
		return complete.digest();
	}
	
    public static byte[] SHA1name(String s) throws Exception{ // @2.对一个文件或文件夹名计算sha值的方法
        MessageDigest complete = MessageDigest.getInstance("SHA-1");
        int numRead = 0;
        complete.update(s.getBytes());
        return complete.digest();
    }
	
	public String convertToHexString(byte data[]) { // @3.由于MessageDigest对象的digest()方法返回的是字符数组，要得到十六进制的sha值还需要转为字符串
								//（tree的value包括了文件名（字符串）、子文件夹名（字符串）、子文件夹的treekey（字符串）、子文件的blobkey（字符串）
		StringBuffer strBuffer = new StringBuffer(); // 用StringBuffer去建立可变字符串对象
		for (int i = 0; i < data.length; i++) {
			strBuffer.append(Integer.toHexString(0xff & data[i])); // 用十六进制数oxff与某个字节值做按位与运算，
										// 只保留了32位的最后8位，保证负数转换成十六进制不会出错
		}
		return strBuffer.toString();
	}
	public String getSha() { //
		return convertToHexString(sha1);
	}
}
