/**
 * Author:      David Irén
 * CS-user:     id17din
 * Mail:        id17din@cs.umu.se
 *
 * Date:        18-11-2019
 */

import se.umu.cs.unittest.TestClass;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * Has all logic and runs the tests, will find tests using reflection
 * and will then run those tests, generating results along the way.
 * If something does not work it will instead save what went wrong
 */
public class GenerateResults {
    private Method setup;
    private Method teardown;
    private ArrayList<Method> tests = new ArrayList<>();
    private ArrayList<String> results = new ArrayList<>();
    private int success=0, fail=0, exceptionFail =0;
    private TestClass tc;

    /**
     * Constructor
     */
    public GenerateResults() {

    }

    /**
     * Will find test methods and setup/teardown methods.
     * @param s - String with a name of a class
     * @return - A list with all text generated
     * @throws NoClassDefFoundError - If a class is misspelled
     */
    public ArrayList<String> findTests(String s)
            throws NoClassDefFoundError{

        try{
            File dir = new File(".");
            URLClassLoader classLoader = URLClassLoader.newInstance
                    (new URL[] { dir.toURI().toURL() });
            Class<?> x = Class.forName(s, true, classLoader);
            if(x.isInterface()){
                results.add("Class is an interface");
                return results;
            }
            //Check if class implements TestClass
            if(!(Class.forName("se.umu.cs.unittest.TestClass")
                    .isAssignableFrom(x))){
                results.add("Class does not implement TestClass!");
                return results;
            }

            Object o = x.getDeclaredConstructor().newInstance();

            // Find all test methods and setup/teardown
            Method[] ma = x.getMethods();
            for (Method met:ma) {

                if(met.getName().contains("test")){
                    getTests().add(met);
                }else if(met.getName().contains("setUp")){
                    setSetup(met);
                }else if(met.getName().contains("tearDown")){
                    setTeardown(met);
                }

            }
            /*If no test methods were found*/
            if(getTests().size() < 1){
                results.add("Not a testclass!");
                return results;
            }
            runTests(o);

        }catch(ClassNotFoundException e){
            results.add("There is no such class!");
        } catch (IllegalAccessException | InstantiationException e) {
            results.add("Failed due to: "+e.getCause().toString());
        } catch (MalformedURLException e) {
            results.add("Failed due to: "+e.getCause().toString());
        } catch (NoSuchMethodException e) {
            results.add("Class does not have a constructor!");
        } catch (InvocationTargetException e) {
            results.add("Test method failed due to: "+e.getCause().toString());

        }


        return results;
    }

    /**
     * Runs all tests found in the class, will run setup/teardown
     * before test if those methods exist in the class
     * @param o - Reflected class to invoke methods from
     */
    private void runTests(Object o) {

        for (Method m:getTests()) {
            try {
                if(m.getReturnType() != boolean.class){
                    continue; //test does not return a boolean, don't run test
                }
                if (!getSetup().equals(null)) {
                    getSetup().invoke(o);
                }
                if(m.invoke(o).equals(true)){
                    results.add(m.getName()+" Success!");
                    success++; //no. tests cleared
                }else{
                    results.add(m.getName()+" FAIL");
                    fail++; //no. tests failed
                }
                if (!getTeardown().equals(null)) {
                    getTeardown().invoke(o);
                }
            }catch (InvocationTargetException | IllegalAccessException e){
                results.add(m.getName()+" Failed due to an exception");
                exceptionFail++; //no. tests failed due to exeptions
            }
        }
        results.add("\n"+success +" Tests Succeded");
        results.add(fail +" Tests Failed");
        results.add(exceptionFail +" Tests failed due to an exception");
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

    public ArrayList<String> getResults() {
        return results;
    }

    public int getSuccess() {
        return success;
    }

    public int getFail() {
        return fail;
    }

    public int getExceptionFail() {
        return exceptionFail;
    }

    public void setTests(ArrayList<Method> tests) {
        this.tests = tests;
    }
}
