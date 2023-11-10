package christmas.io.writer;

public class ConsoleWriter implements Writer{
    @Override
    public void write(String message) {
        System.out.print();
    }

    @Override
    public void writeln(String message) {
        System.out.println("message = " + message);
    }
}
