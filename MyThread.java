public class MyThread extends Thread{

    private String testClass;
    private FindTests ft;

    public MyThread(String s){
        testClass = s;
        ft = new FindTests();
    }

    @Override
    public void run(){
        ft.findTests(testClass);
    }

}
