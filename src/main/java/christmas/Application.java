package christmas;

import christmas.controller.ProgramController;
import christmas.io.reader.ConsoleReader;
import christmas.io.writer.ConsoleWriter;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ProgramController programController = getProgramController();
        programController.run();
    }

    private static ProgramController getProgramController() {
        ConsoleWriter writer = new ConsoleWriter();
        ConsoleReader reader = new ConsoleReader();
        InputView inputView = new InputView(writer, reader);
        OutputView outputView = new OutputView(writer);

        return new ProgramController(inputView, outputView);
    }
}
