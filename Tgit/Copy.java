

import java.io.*;
import java.util.Scanner;

/*
Copy类负责文件的复制，提供两种静态方法：
1）文件的复制转移
2）文件夹的复制转移
*/

public class Copy {
    public static void copyfile(File source, File des) throws Exception{ // 给定源文件和目标文件
        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(source));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(des))){
            int r, numRead = 0;  // 通过两个缓冲流完成源文件的读取，和目标文件的输出
            while((r=in.read())!=-1){
                out.write((byte)r);
                numRead++;
            }
        }
    }

    public static void copydir(File tree, File des) throws Exception{
        try(Scanner in = new Scanner(tree)){
            while(in.hasNext()){ // 对应一个tree对象的文件实例，解析tree文件的每一行，每一行用空格分隔得到每行的3维字符串数组
                String line = in.nextLine();
                String[] lineSplited = line.split(" ");
                String type = lineSplited[0]; // 第一个字符串为类型信息
                String hashcode = lineSplited[1]; // 第二个字符串为key信息
                String fileName = lineSplited[2]; // 第三个字符串为文件名信息
                if(type.equals("blob")){ // 如果解析到类型为blob对象，则调用copyfile方法
                    Copy.copyfile(new File(tree.getParent(),hashcode),new File(des,fileName));
                }
                else if(type.equals("tree")){ // 如果解析到是tree对象，则递归调用copydir方法
                    File newDes = new File(des,fileName);
                    copydir(new File(tree.getParent(), hashcode),newDes);
                }
            }
        }
    }
}
