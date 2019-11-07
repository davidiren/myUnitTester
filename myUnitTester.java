import javax.swing.*;

public class myUnitTester {
    public static void main(String[] args) {
        //FindTests ft = new FindTests();
        //ft.findTests(args[0]);
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                Body b = new Body("");
                b.show();
            }
        });
    }
}
