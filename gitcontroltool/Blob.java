/* 任务一的理解：主要是针对blob对象
@1. 给定value，向存储中添加对应的key-value
	step1 在工作区新建一个file对象，并写入文件内容（控制台输入、或者是写入一个已有的文件）
	step2 为这个file对象重命名为《文件的hash值》并保存

@2. 给定key，查找得到对应的value值
	如果读取《文件的hash值》就可以得到文件内容，此时《文件的hash值》就是key，文件内容就是value。
*/
package gitcontroltool;
import java.io.*;
// 封装Blob类，类中属性包含blob对象的名字，一个输入流变量（用来读取具体的文件内容）
public class Blob {
    private String name; // blob对象的名字
    FileInputStream input; // 输入流变量
    public Blob(String filename) throws Exception { // blob对象的构造方法，原有文件内容不变，生成一个文件名为hash（origin's value）的blob对象
    	Hash s = new Hash(filename,true); // step1.传入一个已有的具体文件，先生成对应的hash值
        this.name = s.getSha(); // step2.为当前blob对象赋予hash值做名字
        this.input = new FileInputStream(filename); // step3.输入流变量引用由当前传入的具体文件生成的file对象实例，这一步只能在构造方法函数内实现
    } 

    public void createBlob() throws IOException { // 写入传入文件的内容，写入后用blob对象的名字保存。
        FileOutputStream output = new FileOutputStream(this.name); // 文件不存在会自动创建
        byte[] buffer = new byte[1024]; // 利用字符数组做缓冲器，提升读写速度。
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
    
    public String getkey() { // 写入传入文件的内容，写入后用blob对象的名字保存。
    	String key = this.name;
    	return key;
    }

	
    @Override
    public String toString() { // 重写object的tostring方法。
        return "blob " + name;
    }
}
