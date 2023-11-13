package christmas.controller;

import christmas.domain.CustomerOrder;
import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountInfos;
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
        //TODO: 다듬기
        Day visitDay = inputView.readVisitDay();
        OrderItems orderItems = inputView.readMenuItems();

        outputView.printBenefitPreviewMsg(visitDay);

        OrderItemsResponse orderItemsResponse = orderItems.toOrderItemsResponse();
        outputView.printOrderedMenu(orderItemsResponse);

        CustomerOrder customerOrder = new CustomerOrder(visitDay, orderItems);
        outputView.printAmountBeforeDiscount(customerOrder);

        customerOrder.discount();
        DiscountInfos discountInfos = customerOrder.getDiscountInfos();

        outputView.printFreeGift(discountInfos);
        outputView.printBenefitDetails(discountInfos);
        outputView.printBenefitAmount(discountInfos);
        outputView.printExpectedAmount(customerOrder, discountInfos);
        outputView.printBadge(discountInfos);
    }
}
