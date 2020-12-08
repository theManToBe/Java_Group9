package gitcontroltool;
import java.io.*;
import java.security.MessageDigest; // ������ϢժҪ���ṩ��ϢժҪSHA1�㷨

public class Hash { 
// ��shaֵ�ļ����װΪһ���࣬�������г�Ա����sha1����ʼshaֵ����outFile������תΪ16���Ƶ��ַ�����
// �ṩ�����ļ����ݣ�blob�����ļ������ַ��������tree����shaֵ����Ͷ�ȡ�������ṩ�ַ�����ת�ַ����ķ���

    private byte[] sha1;
    public Hash(String inFile,boolean isFile) throws Exception{ // �������Ķ���ʱ��������Ƿ����ļ�����ʼ����Ӧ�Ķ���˽�г�Ա����
        if(isFile){
            FileInputStream input = new FileInputStream(inFile);
            this.sha1 = SHA1content(input); // �����ļ����ݼ���hashֵ
        }
        else{
            this.sha1 = SHA1name(inFile); // ���ļ�������hashֵ
        }
    }

	public static byte[] SHA1content(InputStream is) throws Exception { // @1.��һ�������ļ���ͨ���ļ����ݼ���shaֵ�ķ�������blob�����keyֵ
		byte[] buffer = new byte[1024]; // ����һ���ֽ�������������
		MessageDigest complete = MessageDigest.getInstance("SHA-1"); // ָ��SHA1�㷨
		int numRead = 0;
		do {
			numRead = is.read(buffer); // ���������ж�buffer.length���ֽڸ�buffer�����ҷ���ʵ�ʶ�ȡ���ֽ��������ͱ���numRead
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1); // numRead = -1�����ļ���ȡ��ϡ���Ҫ�ر������������ҷ�����ϢժҪ�ֽ����顣
		is.close();
		return complete.digest();
	}
	
    public static byte[] SHA1name(String s) throws Exception{ // @2.��һ���ļ����ļ���������shaֵ�ķ���
        MessageDigest complete = MessageDigest.getInstance("SHA-1");
        int numRead = 0;
        complete.update(s.getBytes());
        return complete.digest();
    }
	
	public String convertToHexString(byte data[]) { // @3.����MessageDigest�����digest()�������ص����ַ����飬Ҫ�õ�ʮ�����Ƶ�shaֵ����ҪתΪ�ַ���
								//��tree��value�������ļ������ַ����������ļ��������ַ����������ļ��е�treekey���ַ����������ļ���blobkey���ַ�����
		StringBuffer strBuffer = new StringBuffer(); // ��StringBufferȥ�����ɱ��ַ�������
		for (int i = 0; i < data.length; i++) {
			strBuffer.append(Integer.toHexString(0xff & data[i])); // ��ʮ��������oxff��ĳ���ֽ�ֵ����λ�����㣬
										// ֻ������32λ�����8λ����֤����ת����ʮ�����Ʋ������
		}
		return strBuffer.toString();
	}
	public String getSha() { //
		return convertToHexString(sha1);
	}
}