
import java.io.*;
import java.util.Scanner;

/*
commit对象的构造方法：
给定author、commiter、comment注释、暂存区对象信息构造commit对象，同时生成precommit信息
================================
一个标准的commit文件的扩展属性(相对Key-Value文件)：
1.commit对应的根目录Tree对象
2.precommit信息
3.author信息
4.commiter信息
5.comment注释
6.commit对应的branch
================================
Commit对象文件的扩展方法：
由于commit对象中有precommit属性，所以设置了genprecommit方法来得到该属性
*/

public class Commit extends KeyValue{
    private Tree tree;
    private String precommit;
    private String author;
    private String committer;
    private String comment;
    private String branch;

    public Commit(String author, String committer, String comment, File index) throws Exception{
        genPrecommit();   // 给当前commit内容添加precommit信息
        Scanner in = new Scanner(index);    // 扫描暂存区的对象hashcode准备提交
        StringBuilder Contentofindex = new StringBuilder();
        while(in.hasNext()){ // 暂存区可能有多个文件，需要用打包到一个根目录tree对象，然后提交（因为需要知道对象的名字）
            Contentofindex.append(in.nextLine() + "\n");
        }
        in.close();
        this.tree = new Tree(Contentofindex); // 根据暂存区内容生成commit的根目录tree对象
        this.tree.write();  // 将这个tree对象生成的文件放入objects/文件夹下
        this.author = author; // 生成author信息
        this.committer = committer; // 生成commiter信息
        this.comment = comment; // 生成comment信息
        this.content = new StringBuilder("tree " + this.tree.getKey() + "\nprecommit " 
                                        + this.precommit + "\nauthor " + this.author + "\ncommitter " 
                                        + this.committer + "\n\n" + this.comment);
        // 在commit对象的对应位置存放这些信息
        genKey(content.toString()); // 生成commit文件的hashcode
        System.out.println("当前Commit的key值为 "+ this.key);
        PrintWriter printWriter = new PrintWriter(this.branch);
        System.out.println("当前Commit已经写入" + this.branch); // 
        printWriter.print(this.key); // 把当前Commit的key写入对应的branch文件
        printWriter.close();
        this.path += "objects/"; // 将commit对象对应的KV文件放入objects/文件夹下
    }

    private void genPrecommit() throws Exception{ // precommit就是当前HEAD中指向的分支对应的最新commit
        Scanner scanner = new Scanner(new File(this.path + "HEAD")); 
        this.branch =  this.path + scanner.next();  // 找到当前指向的Branch
        scanner.close();
        scanner = new Scanner(new File(branch));  // 找到当前Branch指向的commitkey
        if(scanner.hasNext()) {
            this.precommit = scanner.next();  // 当前branch如果有commitkey就将此commitkey做precommit
        }
        else this.precommit = ""; // 如果是本地仓库的第一次提交则不会存在precommit
        scanner.close(); 
    }

}
