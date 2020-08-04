public class OffByN implements CharacterComparator{
    int N;
    @Override
    public boolean equalChars(char x, char y){
        int diff = x - y;
        if (diff == N || diff == -N){
            return true;
        }else return false;
    }
    OffByN(int n){
        N = n;
    }
}