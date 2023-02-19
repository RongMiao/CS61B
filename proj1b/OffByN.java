
public class OffByN implements CharacterComparator{
    private int offset = 0;
    public OffByN(int off) {
        this.offset = off;
    }
    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == offset) {
            return true;
        }
        return false;
    }
}
