
public class Main {
    public static void main(String[] args) {
            Util_Data.readFile();
            System.out.println(Util_Data.inBuffer.length);
            while (LexicalAnalyzer.i<Util_Data.inBuffer.length){
                Word word = LexicalAnalyzer.analyzer();
                Util_Data.printWord(word);
            }
    }
}