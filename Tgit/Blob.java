

import java.io.*;




public class Blob extends KeyValue {
    public Blob(File file) throws Exception { // Blob对象的构造方法(根据传入的文件生成KV文件)
        genKey(file); // 根据源文件生成hashcode
        this.type = "blob"; // 标记为blob对象
        this.path += "objects/"; // 生成的文件存入.git/objects/下

    } 

    @Override
    public void write() throws Exception { // 用源文件生产KV文件
        writeFile();
    }

    public String getType(){ // 得到对应类型信息
        return type;
    }

    public String getKey(){ // 得到对应Hashcode
        return key;
    }

    @Override
    public String toString() { // 为的是写入commit或者tree对象的KV文件中
        return "100644 blob " + key + " " + this.file.getName();
    }
}
