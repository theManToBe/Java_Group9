
/*
Branch文件不同于其他Blob，Tree，Commit类型的KV文件，其key值不是hashcode而是给定的字符串，内容是commits的hashcode
*/
public class Branch extends KeyValue{
    public Branch(String branch,String commit){ //
        this.path += "refs/heads/";  // branch文件的路径在.git/refs/heads/文件夹下
        this.key = branch; // branch文件的名字就是传入的字符串
        this.content = new StringBuilder(commit); // branch文件中的内容就是commits的hashcode
    }
}
