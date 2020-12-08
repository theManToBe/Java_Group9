/* ����һ����⣺��Ҫ�����blob����
@1. ����value����洢����Ӷ�Ӧ��key-value
	step1 �ڹ������½�һ��file���󣬲�д���ļ����ݣ�����̨���롢������д��һ�����е��ļ���
	step2 Ϊ���file����������Ϊ���ļ���hashֵ��������

@2. ����key�����ҵõ���Ӧ��valueֵ
	�����ȡ���ļ���hashֵ���Ϳ��Եõ��ļ����ݣ���ʱ���ļ���hashֵ������key���ļ����ݾ���value��
*/
package gitcontroltool;
import java.io.*;
// ��װBlob�࣬�������԰���blob��������֣�һ��������������������ȡ������ļ����ݣ�
public class Blob {
    private String name; // blob���������
    FileInputStream input; // ����������
    public Blob(String filename) throws Exception { // blob����Ĺ��췽����ԭ���ļ����ݲ��䣬����һ���ļ���Ϊhash��origin's value����blob����
    	Hash s = new Hash(filename,true); // step1.����һ�����еľ����ļ��������ɶ�Ӧ��hashֵ
        this.name = s.getSha(); // step2.Ϊ��ǰblob������hashֵ������
        this.input = new FileInputStream(filename); // step3.���������������ɵ�ǰ����ľ����ļ����ɵ�file����ʵ������һ��ֻ���ڹ��췽��������ʵ��
    } 

    public void createBlob() throws IOException { // д�봫���ļ������ݣ�д�����blob��������ֱ��档
        FileOutputStream output = new FileOutputStream(this.name); // �ļ������ڻ��Զ�����
        byte[] buffer = new byte[1024]; // �����ַ���������������������д�ٶȡ�
        int numRead = 0;
        do {
            numRead = input.read(buffer);
            if(numRead > 0){
                output.write(buffer);
            }
        }while(numRead!=-1);
        input.close();
        output.close();
    }
    
    public String getkey() { // д�봫���ļ������ݣ�д�����blob��������ֱ��档
    	String key = this.name;
    	return key;
    }

    @Override
    public String toString() { // ��дobject��tostring������
        return "blob " + name;
    }
}
