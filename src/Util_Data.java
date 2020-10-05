/*
    初始化文件的读取操作


 */


import java.io.*;
import java.util.Map;
import java.util.Properties;

public class Util_Data {
    static char[]        inBuffer ;                          //待检验字符串
    static String[]      keyWord;                            //关键字表
    static String[]      delimiter;                          //分界符表
    static String[]      arithmeticOperation;                //算术运算符表
    static String[]      logicalOperator;                    //逻辑运算符表
    Map<Integer,String>  identifier;                         //标识符表
    Map<Integer,String>  constant;                           //常数表

    public static void main(String[] args) {
        Util_Data.readFile();
    }
    /**
     * 读取关键字表、分界符表、算术运算符表、逻辑运算符表
     *
     */
    static void readFile(){
        String filePath;                //需分析代码文件的路径

        Properties property = new Properties();
        try {
            //加载资源文件内容
            property.load(new FileInputStream("src/setting.properties"));
            keyWord = property.getProperty("keyWord").split(" ");
            delimiter = property.getProperty("delimiter").split(" ");
            arithmeticOperation = property.getProperty("arithmeticOperation").split(" ");
            logicalOperator = property.getProperty("logicalOperator").split(" ");
            filePath = property.getProperty("filePath");
            //读取代码文件内容
            BufferedReader instr = new BufferedReader(new FileReader(filePath));
            StringBuilder Buffer = new StringBuilder();
            String s;
            while ((s=instr.readLine())!=null){//逐行读取文件内容，不读取换行符和末尾的空格
                Buffer.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
            }
            inBuffer =(new String(Buffer)).toCharArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void printWord(Word word){
        String str;
        String tmp;
        switch(word.wType){
            case -2://换行符
                return;
            case -1://单词出错
                str = "Error";
                break;
            case 1:
                str = "关键字";
                break;
            case 2:
                str = "分界符";
                break;
            case 3:
                str = "算术运算符";
                break;
            case 4:
                str = "关系运算符";
                break;
            case 5:
                str = "无符号数";
                break;
            case 6:
                str = "标识符";
                break;
            default:
                str = "Error";
        }
        if(str =="Error"){
            tmp=str;
        }else {
            tmp = "(" + word.wType + "," + word.wSelf + ")";
        }
        String s = "(" + word.wRow + "," + word.wCol + ")";
        System.out.printf("%-10s%-10s%-10s%-10s", word.wSelf, tmp, str, s);

    }
    static void printIdentifier(){
        System.out.println("+++++++++++++  标识符表  ++++++++++++");
    }
    static void printConstant(){
        System.out.println(" +++++++++++++  常数表  ++++++++++++");
    }
}

