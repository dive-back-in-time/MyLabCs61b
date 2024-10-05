class Solution {
    public static int maxProfit(int[] prices) {
        //第一天买入，如果第二天股票价格低就会卖出
        //如果价格提升了，就继续持股
        int minBuy = prices[0];
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] > prices[i + 1]) {
                profit += prices[i] - minBuy;
                minBuy = prices[i + 1];
            } else if (i == prices[prices.length - 2]) {
                profit += prices[i + 1] - minBuy;
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {1,2,3,4,5};
        System.out.println(maxProfit(prices));
    }
}
