package christmas.controller;

import christmas.domain.CustomerOrder;
import christmas.domain.dto.output.CustomerOrderResponse;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ProgramController {
    private final InputView inputView;
    private final OutputView outputView;

    public ProgramController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            CustomerOrder customerOrder = inputView.readCustomerOrder();
            customerOrder.discount();
            CustomerOrderResponse customerOrderResponse = CustomerOrderResponse.of(customerOrder);
            outputView.printCustomerOrder(customerOrderResponse);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
