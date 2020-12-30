import java.io.*;



public abstract class KeyValueObject {
    protected String type;//文件类型
    protected String key; //文件的key
    protected File file;

    protected KeyValueObject() {
    }


    protected void generateKey(File file) throws Exception {
        Hash s = new Hash(file); //创建hash类
        this.key = s.getSHA1(); //使用hash类方法，可以得到16进制字符串key值
        this.file = file;
    }

    //生成key的函数，参数为hash值字符串（tree的value）
    protected void generateKey(String value) throws Exception  {
        Hash s = new Hash(value);
        this.key = s.getSHA1();
    }


    public String getKey(){ //数据域封装
        return this.key;
    }

    protected String getType(){
        return this.type;
    }

    //创建一份名字为其key值的文件
    protected void copyFile() throws IOException{
        try(
                FileInputStream filein = new FileInputStream("this.file");
                BufferedInputStream input = new BufferedInputStream(filein);
                FileOutputStream fileout = new FileOutputStream(this.key);
                BufferedOutputStream output = new BufferedOutputStream(fileout);
        ){
            int nums = 0;
            while((nums = input.read())!= -1){
                output.write((byte)nums);
            }
        }
    }
}