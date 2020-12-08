package gitcontroltool;
import java.io.*;
// 单元测试类测试最终效果
// 即给定一个文件内容，向存储中添加对应的key-value；给定key，查找得到对应的value值


public class Test { 

    public static void testValid(String filename){ // testvalid函数测试是否可以生成一个以hash（value）即key值为文件名的新文件
// 传入一个具体的origin文件名称，生成一个hash（origin‘s value）做名字的新文件，文件内容同origin。
        File file = new File(filename);
        try{
        	Blob blob = new Blob(file.getAbsolutePath());
        	blob.createBlob();
			System.out.println(blob);
        }
        catch (Exception ex){
            ex.printStackTrace(); // 在命令行打印异常信息在程序中出错的位置及原因
        }
    }

    public static void getValue(String path, String encoding) throws IOException { 
    	// 根据testValid()方法生成的key值为文件名的文件，现在用getValue方法去得到对应的value
        String content = "";
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
        String line = null;
        while ((line = reader.readLine()) != null) {
            content += line + "\n";
        }
        reader.close();
        System.out.println(content);
    }
    
    public static void main(String[] args) throws IOException {
        String filename = "ddd.txt";
        testValid(filename);
        String testfile = "2aae6c35c94fcfb415dbe95f408b9ce91ee846ed";
        getValue(testfile,"utf-8");
    }
}
