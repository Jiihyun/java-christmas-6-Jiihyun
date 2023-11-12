package christmas.controller;

import christmas.domain.CustomerOrder;
import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountInfos;
import christmas.domain.discount.strategy.*;
import christmas.domain.dto.output.OrderItemsResponse;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

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

        CustomerOrder customerOrder = new CustomerOrder(visitDay, orderItems, createDiscountStrategies());
        outputView.printAmountBeforeDiscount(customerOrder);

        DiscountInfos discountInfos = customerOrder.applyDiscount();
        outputView.printFreeGift(discountInfos);
        outputView.printBenefitDetails(discountInfos);
        outputView.printBenefitAmount(discountInfos);
        outputView.printExpectedAmount(customerOrder, discountInfos);
        outputView.printBadge(discountInfos);
    }

    private List<DiscountStrategy> createDiscountStrategies() {
        return List.of(
                new ChristmasDDay(),
                new Weekday(),
                new Weekend(),
                new Special(),
                new FreeGift()
        );
    }
}
