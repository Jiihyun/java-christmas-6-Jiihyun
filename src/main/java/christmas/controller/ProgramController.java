package christmas.controller;

import christmas.domain.CustomerOrderInfo;
import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.dto.output.OrderItemsResponse;
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
        Day visitDay = inputView.readVisitDay();
        OrderItems orderItems = inputView.readMenuItems();

        outputView.printBenefitPreviewMsg(visitDay);

        OrderItemsResponse orderItemsResponse = orderItems.toOrderItemsResponse();
        outputView.printOrderedMenu(orderItemsResponse);

        CustomerOrderInfo customerOrderInfo = new CustomerOrderInfo(visitDay, orderItems);
        customerOrderInfo.applyDiscount();
        outputView.printBeforeAndAfterPriceWithDetail(customerOrderInfo);
    }
}
