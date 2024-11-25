package textualmold9830.cli.commands;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Set;

public class DumpCommand implements Command {
    public static boolean isCapturing = false;
    public PrintStream original;

    public DumpCommand() {
        original = System.out;
    }

    public static ByteArrayOutputStream baos = new ByteArrayOutputStream(1024*1024);
    public static PrintStream newOut = new PrintStream(baos);
    @Override
    public String name() {
        return "dump";
    }

    @Override
    public void execute(String[] args) {
        if (!isCapturing){
            System.out.println("Setting System.out to an internal print stream, rerun to see output");
            System.setOut(newOut);
            isCapturing = true;
            return;
        } else {
            if (args.length > 0){
                switch (args[0]){
                    case "stacktrace":{
                        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
                        PrintStream originalErr = System.err;
                        System.setErr(original);
                        for (Thread thread : threadSet) {
                            StackTraceElement[] ste = thread.getStackTrace();
                            Arrays.asList(ste).forEach(System.out::println);

                        }
                        System.setErr(originalErr);
                    }break;
                    case "output": {
                        original.println(baos.toString());
                        break;
                    }
                    case "undo":{
                        System.setOut(original);
                        isCapturing = false;
                        break;
                    }
                    case "clear":{
                        baos = new ByteArrayOutputStream(1024*1024);
                        newOut = new PrintStream(baos);
                        System.setOut(newOut);
                        break;
                    }
                }
            } else {
                System.out.println("usage: dump stacktrace");
                System.out.println("usage: dump output");
                System.out.println("usage: dump undo");
                System.out.println("usage: dump clear");
                System.out.println("usage: dump");
            }
        }
        if (args.length > 0 && args[0] != null){
            if (args[0].equals("test")){
                original.println("original");
                newOut.println("newOut");
                System.out.println("system");
            }
        }
    }

}
