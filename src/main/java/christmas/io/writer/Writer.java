package christmas.io.writer;

public interface Writer {
    void writef(String format, Object... args);

    void writeln(String message);
}
