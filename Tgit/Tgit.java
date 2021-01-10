import java.io.IOException;

/*
主函数入口
1.实现命令行交互
2.参数个数正确性校验方法
*/
public class Tgit {

    public static void main(String... args) {
        if (args.length == 0) {
            System.out.print("Please enter a command.");
            System.exit(0);
        }

        try {
            switch (args[0]) {
            case "init":
                init(args);
                break;
            case "add":
                add(args);
                break;
            case "commit":
                commit(args);
                break;
            case "reset":
                reset(args);
                break;
			case "branch":
				branch(args);
				break;
			case "log":
				log(args);	
				break;				
            default:
                System.out.print("No command with that name exists.");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        System.exit(0);
    }  


//	声明init方法
    private static void init(String[] args) {
        operandsCheck(args, 1, 0);
        StaticCommand.init();
    }

//	声明add方法
    private static void add(String[] args) throws Exception {  
        operandsCheck(args, 1, 1);
        StaticCommand.add(args[1]);
    }

//	声明commit方法
    private static void commit(String[] args) throws Exception {  
        operandsCheck(args, 4, 0);
        new History().commit(args[1],args[2],args[3]);
    }

//  声明两种reset的方法
    private static void reset(String[] args) throws Exception {  
        operandsCheck(args, 2, 0);
            switch (args[1]) {
            case "--soft":
                new History().resetsoft();
                break;
            case "--mixed":
                new History().resetmixed();
                break;
			}
    }

//	声明查看commit日志的方法
    private static void log(String[] args) throws Exception { 
        operandsCheck(args, 1, 0);
        new History().log();
    }

//	声明所有分支管理的方法
    private static void branch(String[] args) throws Exception { 
        operandsCheck(args, 0, 1);
            switch (args[1]) {
            case "null":
                new History().branch();
                break;
            case "--createbranch":
                new History().createbranch(args[2]);
                break;
            case "--checkout":
                new History().checkoutbranch(args[2]);
                break;
			}		

    }

//	命令参数个数正确性判断方法
    private static void operandsCheck(String[] args, int expected, int type) { 
        if ((type > 0 && args.length <= expected)
            || (type == 0 && args.length != expected)
            || (type < 0 && args.length >= expected)) {
            System.out.print("Incorrect operands.");
            System.exit(0);
        }
    }
}

