import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class FindTests {

    private Method setup;
    private Method teardown;
    private ArrayList<Method> tests = new ArrayList<>();
    private ArrayList<String> results = new ArrayList<>();

    public FindTests() {

    }

    public ArrayList<String> findTests(String s){

        try{
            Class<?> x = Class.forName( s );
            Object y = x.newInstance();
            Method[] ma = x.getMethods();
            for (Method met:ma) {
                //System.out.println(met.getName());

                if(met.getName().contains("test")){
                    //System.out.println(met.getName());
                    getTests().add(met);
                }else if(met.getName().contains("setUp")){
                    setSetup(met);
                }else if(met.getName().contains("tearDown")){
                    setTeardown(met);
                }

            }
            runTests(y);

        }catch(ClassNotFoundException e){
            System.out.println("There is no such class!");
            results.add("There is no such class!");
        } catch (IllegalAccessException | InstantiationException e) {
            //e.printStackTrace();
            results.add(e.getCause().toString());
        }

        return results;
    }


    private void runTests(Object o) {
        for (Method m:getTests()) {
            try {
                if (!getSetup().equals(null)) {
                    getSetup().invoke(o);
                }
                if(m.invoke(o).equals(true)){
                    System.out.println(m.getName()+" Success!");
                    results.add(m.getName()+" Success!");
                }else{
                    System.out.println(m.getName()+" FAIL");
                    results.add(m.getName()+" FAIL");
                }
                if (!getTeardown().equals(null)) {
                    getTeardown().invoke(o);
                }
            }catch (InvocationTargetException | IllegalAccessException e){
                System.out.println(m.getName()+" Failed due to: "+
                        e.getCause());
                results.add(m.getName()+" Failed due to: "+ e.getCause());
            }
        }
    }

    public Method getSetup() {
        return setup;
    }

    public void setSetup(Method setup) {
        this.setup = setup;
    }

    public Method getTeardown() {
        return teardown;
    }

    public void setTeardown(Method teardown) {
        this.teardown = teardown;
    }

    public ArrayList<Method> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Method> tests) {
        this.tests = tests;
    }
}
