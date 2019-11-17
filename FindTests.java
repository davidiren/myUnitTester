import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Has all logic and runs the tests, will find tests using reflection
 * and will then run those tests
 */
public class FindTests {
    private Method setup;
    private Method teardown;
    private ArrayList<Method> tests = new ArrayList<>();
    private ArrayList<String> results = new ArrayList<>();

    /**
     * Constructor
     */
    public FindTests() {

    }

    /**
     * Will find test methods and setup/teardown methods.
     * @param s - String with a name of a class
     * @return - A list with all text generated
     * @throws NoClassDefFoundError - If a class is misspelled e.g. lowercase
     */
    public ArrayList<String> findTests(String s)throws NoClassDefFoundError{

        try{
            Class<?> x = Class.forName( s );
            if(x.isInterface()){
                results.add("Class is an interface");
                return results;
            }
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
            /*No test methods found*/
            if(getTests().size() < 1){
                results.add("Not a testclass!");
                return results;
            }
            runTests(y);

        }catch(ClassNotFoundException e){
            System.out.println("There is no such class!");
            results.add("There is no such class!");
        } catch (IllegalAccessException | InstantiationException e) {
            //e.printStackTrace();
            results.add("Failed doe to: "+e.getCause().toString());
        }


        return results;
    }

    /**
     * Runs all tests found in the class, will run setup/teardown before test
     * if those methods exist in the class
     * @param o - Reflected class to invoke methods from
     */
    private void runTests(Object o) {
        int i=0, j=0, h=0;
        for (Method m:getTests()) {
            try {
                if (!getSetup().equals(null)) {
                    getSetup().invoke(o);
                }
                if(m.invoke(o).equals(true)){
                    results.add(m.getName()+" Success!");
                    i++; //no. tests cleared
                }else{
                    results.add(m.getName()+" FAIL");
                    j++; //no. tests failed
                }
                if (!getTeardown().equals(null)) {
                    getTeardown().invoke(o);
                }
            }catch (InvocationTargetException | IllegalAccessException e){
                //System.out.println(m.getName()+" Failed due to an exception");
                results.add(m.getName()+" Failed due to an exception");
                h++; //no. tests failed due to exeptions
            }
        }
        results.add("\n");
        results.add(i +" Tests Succeded");
        results.add(j +" Tests Failed");
        results.add(h +" Tests failed due to an exception");
    }

    /**
     * SETTERS AND GETTERS
     */
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
