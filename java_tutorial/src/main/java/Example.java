import SubDirectory.FunctionalInterfaceExample;
import SubDirectory.ReDDoSExample;
import SubDirectory.StreamExample;
import SubDirectory.TimeExample;

public class Example {
    private static FunctionalInterfaceExample functionalInterfaceExample;
    private static StreamExample streamExample;
    private static TimeExample timeExample;
    private static ReDDoSExample reDDoSExample;

    static {
        functionalInterfaceExample = new FunctionalInterfaceExample();
        streamExample = new StreamExample();
        timeExample = new TimeExample();
        reDDoSExample = new ReDDoSExample();
    }

    public static void main(String[] args) {
//        functionalInterfaceExample.doAll();
//        streamExample.doAll();
//        timeExample.doAll();
        reDDoSExample.doAll();
    }
}
