
import java.io.*;
/*
设计一个无参构造方法，方便子类调用这个构造方法
================================
一个标准的Key-Value文件的共享属性：
1.类型(type)
2.key值(hashcode)
3.文件的内容
4.Key-Value对应的File源文件对象
5.这些Key-Value所在的根目录
================================
一个标准的Key-Value文件的共享方法：
1.文件名(key值)的生成方法(根据文件内容生成，根据字符串生成)
2.根据工作区源文件生成KV文件方法(根据文件或者文件夹生成)
*/

public abstract class KeyValue { // 一个标准的Key-Value文件应该包括类型
    protected String type; // KV文件类型
    protected String key; // KV文件hashcode
    protected File file; // KV文件对应的File对象
    protected StringBuilder content = new StringBuilder();  // 对应文件内容
    protected String path = System.getProperty("user.dir") + "/.git/"; // 当前版本仓库
    protected  KeyValue(){ // 无参构造方法方便子类调用

    }
    public void genKey(File file) throws Exception{ // 利用工具类的SHA1类计算文件的hashcode
        Hashcode s = new Hashcode(file); 
        this.key = s.getSha1();
        this.file = file;
    }

    public void genKey(String content) throws Exception{ // 计算字符串内容的hashcode
        Hashcode s = new Hashcode(content);
        this.key = s.getSha1();
    }


    public void writeFile() throws Exception{ // 根据源文件创建KV文件，主要对Blob
        File source = this.file;
        File des = new File(this.path + this.key);
        Copy.copyfile(source,des);
    };

    public void write() throws Exception{ // 根据源文件夹内容创建KV文件，主要对Tree
        File f = new File(path);
        if(!f.exists())f.mkdirs();
        PrintWriter p = new PrintWriter(this.path + this.key);
        p.print(this.content);
        p.close();
    }

    public String getContent() { // 获取文件内容，以字符串形式给出
        return content.toString();
    }
}
