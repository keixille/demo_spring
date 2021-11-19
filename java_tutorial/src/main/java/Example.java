import SubDirectory.FunctionalInterfaceExample;
import SubDirectory.StreamExample;
import SubDirectory.TimeExample;

public class Example {
    private static FunctionalInterfaceExample functionalInterfaceExample;
    private static StreamExample streamExample;
    private static TimeExample timeExample;

    static {
        functionalInterfaceExample = new FunctionalInterfaceExample();
        streamExample = new StreamExample();
        timeExample = new TimeExample();
    }

    public static void main(String[] args) {
        functionalInterfaceExample.doAll();
        streamExample.doAll();
        timeExample.doAll();
    }
}
