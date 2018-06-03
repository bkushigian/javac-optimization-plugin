public class TestClass {

    public int leftConstantFold(int a){
        return 1 + 2 + a;
    }

    public int rightConstantFold(int a){
        return a + 1 + 2;
    }
}
