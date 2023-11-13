package christmas.domain.constants;

import christmas.domain.discount.DiscountInfo;
import christmas.domain.discount.DiscountInfos;
import christmas.domain.discount.constants.DiscountCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadgeTest {

    @Test
    @DisplayName("총혜택금액이 5000원 미만이면 없음을 반환한다")
    void getNone() {
        //given
        DiscountInfos discountInfos = new DiscountInfos(List.of(
                new DiscountInfo(DiscountCategory.SPECIAL, 1000)));
        //when
        Badge result = Badge.getBadge(discountInfos);
        //then
        assertEquals(Badge.NONE, result);
    }
    
    @Test
    @DisplayName("총혜택금액이 5000원 이상이면 별 뱃지를 반환한다")
    void getStarBadge() {
        //given
        DiscountInfos discountInfos = new DiscountInfos(List.of(
                new DiscountInfo(DiscountCategory.WEEKEND, 6069),
                new DiscountInfo(DiscountCategory.CHRISTMAS_D_DAY, 1000)));
        //when
        Badge result = Badge.getBadge(discountInfos);
        //then
        assertEquals(Badge.STAR, result);
    }

    @Test
    @DisplayName("총혜택금액이 10000원 이상이면 트리 뱃지를 반환한다")
    void getTreeBadge() {
        //given
        DiscountInfos discountInfos = new DiscountInfos(List.of(
                new DiscountInfo(DiscountCategory.CHRISTMAS_D_DAY, 3400),
                new DiscountInfo(DiscountCategory.WEEKDAY, 6069),
                new DiscountInfo(DiscountCategory.SPECIAL, 1000)));
        //when
        Badge result = Badge.getBadge(discountInfos);
        //then
        assertEquals(Badge.TREE, result);
    }

    @Test
    @DisplayName("총혜택금액이 20000원 이상이면 산타 뱃지를 반환한다")
    void getSantaBadge() {
        //given
        DiscountInfos discountInfos = new DiscountInfos(List.of(
                new DiscountInfo(DiscountCategory.FREE_GIFT, 25000),
                new DiscountInfo(DiscountCategory.CHRISTMAS_D_DAY, 2200),
                new DiscountInfo(DiscountCategory.WEEKDAY, 6069)));
        //when
        Badge result = Badge.getBadge(discountInfos);
        //then
        assertEquals(Badge.SANTA, result);
    }
}