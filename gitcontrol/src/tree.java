import java.io.*;
public class Tree extends KeyValueObject {

    public Tree(File file) throws Exception {
        this.type = "tree"; //设置type
        for(File f:file.listFiles()){ 遍历文件夹
            if(f.isFile()){ //
                value = value + "\n" + "100644 blob " + new Blob(f).getKey() + " " + f.getName();
            }
            else if(f.isDirectory()){
                value = value + "\n" + "040000 tree " + new Tree(f).getKey() + " " + f.getName();
            }
        }
        generateKey(value); //遍历文件夹后，按照得到的value生成key值
    }

    public String getValue(){
        return this.value;
    }


    @Override
    public String toString(){
        return "040000 tree" + key;
    }

    //重写父类的复制文件方法
    @Override
    public void copyFile() throws IOException{
        PrintWriter thisFile = new PrintWriter(this.key);//PrintWriter向文本输出
        thisFile .print(value);
        thisFile .close();
    }

}