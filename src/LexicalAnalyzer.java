public class LexicalAnalyzer {
    static int row = 1;
    static int col = 1;
    static int i = 0;
    static String token;
    static char ch;
    static Word analyzer(){
        ch = Util_Data.inBuffer[i];
        token="";
        while(ch == ' '){
            i++;
            ch = Util_Data.inBuffer[i];
        }
        if( ch == '\n'){
            row++;
            i++;
            ch = Util_Data.inBuffer[i];
            col = 1;
        }
        if(isLetter()){
            while(isLetter()||isDigit(ch)){
                token += ch;
                ch = Util_Data.inBuffer[++i];
            }
//            //回退一个字符
//            retract();
            if(isKey()){
                //关键字
                return new Word(row,col,1,token);
            }
            else{
                //标识
                return new Word(row,col,6,token);
            }
        }
        else if(isDigit(ch)||(ch == '.'))
        {
            while(isDigit(ch)||(ch == '.'&&isDigit(Util_Data.inBuffer[++i])))
            {
                if(ch == '.') i--;
                token += ch;
                ch = Util_Data.inBuffer[++i];
            }
//            //回退一个字符
//            retract();
            //属于无符号常数
            return new Word(row,col,5,token);
        }
        else if(isDelimiter()) {
            token += ch;
            i++;
            //分界符
            return new Word(row,col,2,token);
        }
        else if(isArithmeticOperation(ch)){
            int times=0;
            while (isArithmeticOperation(Util_Data.inBuffer[i])){
                token += ch;
                i++;
                times++;

            }
         //   retract();
            if(times>1)
                return new Word(row,col,-1,token);
            else
                return new Word(row,col,3,token);
        }else if(ch == '<' || ch == '>' || ch == '='){

            token+=ch;
            ch=Util_Data.inBuffer[++i];
            token+=Util_Data.inBuffer[++i];
            if(!islogicalOperator()){
                token=token.substring(0,token.length()-1);
            }
            return new Word(row,col,4,token);
        }
        else
        {
            i++;
            token+=ch;
            return new Word(row,col,-1,token);
        }

    }

   static void retract(){
       i--;
       ch=' ';
    }
    //判断是否是关键字
    static boolean isKey()
    {
        for(int i = 0;i < Util_Data.keyWord.length;i++)
        {
            if(Util_Data.keyWord[i].equals(token))
                return true;
        }
        return false;
    }
    //判断是否是字母
    static boolean isLetter()
    {
        if((ch >= 'a' && ch <= 'z')||(ch >= 'A' && ch <= 'Z'))
            return true;
        else
            return false;
    }
    //判断是否是数字
    static boolean isDigit(char ch)
    {
        if(ch >= '0' && ch <= '9')
            return true;
        else
            return false;
    }
    //判断是否为分界符
    static boolean isDelimiter(){
        switch(ch) {
            //分界符
            case ',':
            case '(':
            case ')':
            case '[':
            case ']':
            case ';':
            case '{':
            case '}':
                return true;
            default:
                return false;
        }
    }
    //判断是否为算数运算符
    static boolean isArithmeticOperation(char ch) {
        String temp =""+ch;
        for (String ach : Util_Data.arithmeticOperation){
            if(ach.contains(temp))
                return true;
        }
        return false;
    }
    //判断是否为逻辑运算符
    static boolean islogicalOperator(){
        String temp =""+token;
        for (String ach : Util_Data.logicalOperator){
            if(ach.contains(temp))
                return true;
        }
        return false;
    }



}
