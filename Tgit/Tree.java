
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;

/*
根据文件夹生成的KV对象，文件夹下可能有文件或者新的文件夹
*/


public class Tree extends KeyValue {
    public Tree(File file) throws Exception { // Tree对象的构造方法，列出根目录下的文件信息
        for(File f:file.listFiles()){ // 如果是
            String append;
            if(f.isFile()){ // 如果是文件，需要做两个操作，一是在objects/下生成blob的KV文件，二是在该Tree对象内容中吸入这条信息
                Blob blob = new Blob(f);
                blob.write();
                content.append("100644 blob " + blob.getKey() + " " + f.getName() + "\n");
            }
            else if(f.isDirectory()){ // 如果是文件夹，则只需要写入信息的操作，同时会递归这个新的文件夹
                content.append("100644 tree " + new Tree(f).getKey() + " " + f.getName() + "\n");
            }
        }
        genKey(content.toString()); // tree对象的hashcode需要在遍历完文件夹内容后生成
        this.path += "objects/"; 
        this.file = file; // file属性指向对应源文件路径
    }

    public Tree(StringBuilder index) throws Exception{ // 该构造方法用于commit之前根据暂存区文件的内容生成commit的根目录的tree对象
        this.content = index;
        System.out.println("content is " + this.content.toString());
        genKey(content.toString());
        this.path += "objects/";
    }



    public String getKey() { // 获取tree对象的hashcode
        return this.key;
    }

    public String getContent(){ // 获取tree对象的内容
        return this.content.toString();
    }

    @Override
    public String toString() { // 打印tree对象信息
        return "100644 tree " + this.key + " " + this.file.getName();
    }
}
