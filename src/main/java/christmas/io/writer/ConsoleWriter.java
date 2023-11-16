package christmas.io.writer;

public class ConsoleWriter implements Writer {
    @Override
    public void writef(String format, Object... args) {
        System.out.printf(format, args);
    }

    @Override
    public void writeln(String message) {
        System.out.println(message);
    }
}
