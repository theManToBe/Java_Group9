import java.io.*;
import java.util.Scanner;
/*
静态命令类StaticCommand
该类的属性主要为仓库初始化时产生的文件：
1.工作区路径
2.仓库路径
3.暂存区文件
4.HEAD文件
============================================
该类主要用于提供对本地仓库和工作区的一系列静态操作：
1.init()初试化本地仓库
2.add()添加文件到暂存区
*/
public class StaticCommand {
    static final String workingDir = System.getProperty("user.dir")+"/";  // 工作区路径是一个静态常量
    static final String path = workingDir + ".git/";  // 本地仓库路径同样是一个静态常量
    protected File index = new File(path + "index"); // 暂存区文件存放路径对于一个初始化后的仓库来说也是固定的。
    protected File head = new File(path + "HEAD");; // HEAD文件存放路径
    public StaticCommand(){

    }

    protected String absolute(String s){ // 得到仓库目录中的某个文件
        return path + s;
    }
    protected String absoluteObjects(String s){ // 得到生成的某个KV文件
        return path + "objects/" + s;
    }
    public String getPath(){ // 返回当前仓库路径
        return path;
    }
    public String getWorkingDir(){ // 返回当前工作区路径
        return workingDir;
    }

//  init初始化一个本地仓库
    public static void init(){
        File f = new File(path);  // path为workingdir/.git/
        if(f.exists()) System.out.println("本地git仓库已存在");
        File objects = new File(f,"objects"); // 初始化.git/objects/
        File refs = new File(f,"refs"); // 初始化.git/refs/
        File heads = new File(refs, "heads"); // 初始化.git/heads/
        Branch main = new Branch("main",""); // 初始化.git/refs/main
        Head head = new Head("main"); // 初始化.git/HEAD
        if(f.mkdir()&&objects.mkdir()&&refs.mkdir()&&heads.mkdir()){ // 文件夹对象用mkdir()方法创建文件夹
            System.out.println("本地git仓库初始化成功");
        }
        try{
            main.write(); // 文件对象用write()方法生成实际文件
            head.write();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    //  add方法将文件放入暂存区
    public static void add(String fileName) throws Exception { 
        File f = new File(workingDir + fileName); // 在工作区找到要添加的文件
        if(!f.exists()) { // 未找到就给提示并结束
            System.out.println("工作区中不存在此文件");
            return;
        }
        String append; // 如果找到该文件，最后需要文件内容写入index
        if(f.isDirectory()){ // 如果是文件夹，生成tree对象，用tostring方法即可知tree对象的类型信息，key值和对应的文件名
            Tree tree = new Tree(f); // 
            append = tree.toString(); 
            tree.write();
        }
        else{
            Blob blob = new Blob(f); // 如果是文件，生成blob对象，需要另外得到类型信息和文件名信息
            append = blob.toString();
            blob.write();
        }
         // 
        PrintWriter pw = new PrintWriter(new FileWriter(path+"index", true));
        pw.println(append); // 
        pw.close();
        System.out.println("文件已添加至暂存区");
    }



}
