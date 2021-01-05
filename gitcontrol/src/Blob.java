import java.io.*;

public class Blob extends KeyValueObject {
    public Blob(File file) throws Exception {
        generateKey(file);//构造时生成file的key
        this.type = "blob";//表明文件类型


    }


    public String getType(){
        return type;
    }

    public String getKey(){
        return key;
    }

    @Override
    public String toString() {
        return "100644 blob " + key;
    }
}
