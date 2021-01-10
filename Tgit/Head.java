
/*
HEAD文件不同于其他Blob，Tree，Commit类型的KV文件，其key值就是名字"HEAD"，内容是当前指向的分支的路径(相对.git/文件夹)
*/
public class Head extends KeyValue{
    public Head(String branch){
        this.key = "HEAD";
        this.content = new StringBuilder("refs/heads/" + branch);
    }
}
