public class Word {
    int    wRow;
    int    wCol;
    int    wType;
    String wSelf;

    public Word(int wRow, int wCol, int wType, String wSelf) {
        this.wRow = wRow;
        this.wCol = wCol;
        this.wType = wType;
        this.wSelf = wSelf;
        LexicalAnalyzer.col++;
    }
}
