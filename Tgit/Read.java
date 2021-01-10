
import java.io.File;
import java.util.Scanner;

public class Read {

    public static String readBranch(File head){ // 从HEAD文件中读取当前分支
        try{
            Scanner in = new Scanner(head); 
            String branch = in.next();
            in.close(); // 读取HEAD文件的第一行就是当前branch的路径
            return branch;
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static String readCommit(File branch){ // 读取branch文件中的commitkey
        try{
            Scanner in = new Scanner(branch);
            String commit = in.nextLine();
            in.close();
            return commit;
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static String readPrecommit(File commit){ // 读取commit文件中precommit
        try{
            Scanner in = new Scanner(commit);
            String line = in.nextLine();
            String aimline = in.nextLine();
            String[] aim = aimline.split("\\ "); // precommit的key位于commit内容用空格分隔后的第二行的第二个元素
            String precommit = aim[1];
            in.close();
            if(precommit.length()!=40){ // 如果是第一次在该分支提交，则返回空值
                return "";
            }
            else{
                return precommit;
            }
        }
        catch (Exception e){
            return "";
        }
    }

    public static String readCommitTree(File commit) throws Exception{ // 从commit得到对应的根目录tree对象
        try(Scanner in = new Scanner(commit)){
            while(in.hasNext()){
                String line = in.nextLine();
                String[] aim = line.split(" ");  // 根目录tree的key位于commit内容用空格分隔后的第一行的第二个元素
                String hashcode = aim[1];
                return hashcode;
            }
        }
        return null;
    }
}
