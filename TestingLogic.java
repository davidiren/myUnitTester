import org.junit.Test;

public class TestingLogic {

    @Test
    public void testNoOfSuccess(){
        GenerateResults gr = new GenerateResults(){};
        gr.findTests("Test1");

        assert gr.getSuccess() == 3;
    }

    @Test
    public void testNoOfFail(){
        GenerateResults gr = new GenerateResults(){};
        gr.findTests("Test1");

        assert gr.getFail() == 1;
    }

    @Test
    public void testNoOfExceptionFail(){
        GenerateResults gr = new GenerateResults(){};
        gr.findTests("Test1");

        assert gr.getExceptionFail() == 1;
    }

    @Test
    public void testNoSuchClass(){
        GenerateResults gr = new GenerateResults(){};
        gr.findTests("ThisIsNotAClass");

        assert gr.getResults().size()==1; //Ett fel meddelande
    }

    @Test
    public void shouldBe8Prints(){
        GenerateResults gr = new GenerateResults(){};
        gr.findTests("Test1");

        assert gr.getResults().size()==8;//8 prints,5 test, 3 summary
    }
}
