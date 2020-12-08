package gitcontroltool;
import java.io.*;
// ��Ԫ�������������Ч��
// ������һ���ļ����ݣ���洢����Ӷ�Ӧ��key-value������key�����ҵõ���Ӧ��valueֵ

public class Test { 

    public static void testValid(String filename){ // testvalid���������Ƿ��������һ����hash��value����keyֵΪ�ļ��������ļ�
// ����һ�������origin�ļ����ƣ�����һ��hash��origin��s value�������ֵ����ļ����ļ�����ͬorigin��
        File file = new File(filename);
        try{
        	Blob blob = new Blob(file.getAbsolutePath());
        	blob.createBlob();
			System.out.println(blob);
        }
        catch (Exception ex){
            ex.printStackTrace(); // �������д�ӡ�쳣��Ϣ�ڳ����г����λ�ü�ԭ��
        }
    }

    public static void getValue(String path, String encoding) throws IOException { 
    	// ����testValid()�������ɵ�keyֵΪ�ļ������ļ���������getValue����ȥ�õ���Ӧ��value
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