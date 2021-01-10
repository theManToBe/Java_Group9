import java.io.File;
import java.io.PrintWriter;

public class unitTest { // 测试文件名是否合法，标准KV文件的名字是40位长度的字符串
    public static void testName(String filename){
        File file = new File(filename);
        try{
            if(file.isDirectory()) {
                Tree tree = new Tree(file);
                System.out.println(tree);
                assert tree.getKey().length() == 40;
            }
            else{
                Blob blob = new Blob(file);
                System.out.println(blob);
                assert blob.getKey().length() == 40;
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void testContent(String filename) throws Exception{ 
    // 测试文件内容是否合法，Tree对象和Blob对象有自己相应的文件内容
        File file = new File(filename);
        if(file.isDirectory()){ // 
            System.out.println(new Tree(file).getContent());
        }
        else{
            System.out.println(new Blob(file));
        }
    }    }

    public static void testName(File file) throws Exception{
        if(file.isDirectory()){ // 测试文件名是否合法
            Tree tree = new Tree(file);
            System.out.println(tree);
            assert tree.getKey().length() == 40;
        }
        else{
            Blob blob = new Blob(file);
            System.out.println(blob);
            assert blob.getKey().length() == 40;
        }
    }

    public static void testTree(File file){
        // 测试tree对象是否能成功生成
        try{
            if(file.isDirectory()){
                System.out.println(new Tree(file).getContent());
            }
            else{
                System.out.println(new Blob(file));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void testBranch(String branch) throws Exception{
        // 
        Branch b = new Branch(branch,"");
        b.write(); // 测试是否能正常生成一个branch
    }

    public static void testHead(String branch) throws Exception{
        Head head = new Head(branch);
        head.write(); // 测试是否能正常生成一个HEAD文件
    }

    public static void testCommit(String path) throws Exception{
        Commit commit1 = new Commit("team9","team9","first commit");
        System.out.println(commit1.getContent());
        commit1.write();

        PrintWriter printWriter = new PrintWriter(path+"dwd.java"); 
        printWriter.print("somewords");
        printWriter.close();
        Commit commit2 = new Commit("team9","team9","commit again");
        System.out.println(commit2.getContent());
        commit2.write();

    }

    public static File genFile(){
        File test1 = new File("root");
        File blob1 = new File(test1,"blob1");
        File tree1 = new File(test1,"tree1");
        File blob2 = new File(tree1,"blob2");
        return test1;
    }

    public static void testWorkingDir(){
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
    }

    public static void main(String[] args){
        try {
            String path = "./test";
            testBranch("master");
            testHead("master");
            testCommit(path);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        testWorkingDir();



    }

