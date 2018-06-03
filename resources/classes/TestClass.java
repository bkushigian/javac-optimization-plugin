public class TestClass {
    public int i = 0;
    public int j = 0;

    public int leftConstantFold(int a){
        return 1 + 2 + a;
    }

    public int rightConstantFold(int a){
        return a + 1 + 2;
    }

    public int parenConstantFold(int a) {
        return a + (1 + 2);
    }

    public int yearsToSeconds(int years){
        int seconds = years * 60 * 60 * 24 * 365;
        return seconds;
    }

    public int divExample(int a, int b, int c){
        return a / b / c;
    }
}
