package textualmold9830.cli.commands;

public interface Command {
    default void execute(String[] args) {
        if (args.length > 0) {
            run(args);
        }
        else {
            run();
        }
    }
    default void run(String[] args){}
    default void run(){}
    String name();
}
