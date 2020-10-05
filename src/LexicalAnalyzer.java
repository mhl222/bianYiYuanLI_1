public class LexicalAnalyzer {
    static int row = 1;
    static int col = 0;
    static int i = 0;
    static String token;
    static char ch;
    Word analyzer(){

        String arr = "";
        for(int i = 0;i< chars.length;i++) {
            ch = chars[i];
            arr = "";
            if(ch == ' '||ch == '\t'||ch == '\n'||ch == '\r'){}
            else if(isLetter(ch)){
                while(isLetter(ch)||isDigit(ch)){
                    arr += ch;
                    ch = chars[++i];
                }
                //回退一个字符
                i--;
                if(isKey(arr)){
                    //关键字
                    System.out.println(arr+"\t4"+"\t关键字");
                }
                else{
                    //标识符
                    System.out.println(arr+"\t4"+"\t标识符");
                }
            }
            else if(isDigit(ch)||(ch == '.'))
            {
                while(isDigit(ch)||(ch == '.'&&isDigit(chars[++i])))
                {
                    if(ch == '.') i--;
                    arr = arr + ch;
                    ch = chars[++i];
                }
                //属于无符号常数
                System.out.println(arr+"\t5"+"\t常数");
            }
            else switch(ch){
                    //运算符
                    case '+':System.out.println(ch+"\t2"+"\t运算符");break;
                    case '-':System.out.println(ch+"\t2"+"\t运算符");break;
                    case '*':System.out.println(ch+"\t2"+"\t运算符");break;
                    case '/':System.out.println(ch+"\t2"+"\t运算符");break;
                    //分界符
                    case '(':System.out.println(ch+"\t3"+"\t分界符");break;
                    case ')':System.out.println(ch+"\t3"+"\t分界符");break;
                    case '[':System.out.println(ch+"\t3"+"\t分界符");break;
                    case ']':System.out.println(ch+"\t3"+"\t分界符");break;
                    case ';':System.out.println(ch+"\t3"+"\t分界符");break;
                    case '{':System.out.println(ch+"\t3"+"\t分界符");break;
                    case '}':System.out.println(ch+"\t3"+"\t分界符");break;
                    //运算符
                    case '=':{
                        ch = chars[++i];
                        if(ch == '=')System.out.println("=="+"\t2"+"\t运算符");
                        else {
                            System.out.println("="+"\t2"+"\t运算符");
                            i--;
                        }
                    }break;
                    case ':':{
                        ch = chars[++i];
                        if(ch == '=')System.out.println(":="+"\t2"+"\t运算符");
                        else {
                            System.out.println(":"+"\t2"+"\t运算符");
                            i--;
                        }
                    }break;
                    case '>':{
                        ch = chars[++i];
                        if(ch == '=')System.out.println(">="+"\t2"+"\t运算符");
                        else {
                            System.out.println(">"+"\t2"+"\t运算符");
                            i--;
                        }
                    }break;
                    case '<':{
                        ch = chars[++i];
                        if(ch == '=')System.out.println("<="+"\t2"+"\t运算符");
                        else {
                            System.out.println("<"+"\t2"+"\t运算符");
                            i--;
                        }
                    }break;
                    //无识别
                    default: System.out.println(ch+"\t6"+"\t无识别符");
                }
        }
        return null;
    }
    void getChar(){//读入一个字符
        ch = Util_Data.inBuffer[i];
        i++;
    }
    //判断是否是关键字
    boolean isKey()
    {
        for(int i = 0;i < Util_Data.keyWord.length;i++)
        {
            if(Util_Data.keyWord[i].equals(token))
                return true;
        }
        return false;
    }
    //判断是否是字母
    boolean isLetter()
    {
        if((ch >= 'a' && ch <= 'z')||(ch >= 'A' && ch <= 'Z'))
            return true;
        else
            return false;
    }
    //判断是否是数字
    boolean isDigit()
    {
        if(ch >= '0' && ch <= '9')
            return true;
        else
            return false;
    }

}
