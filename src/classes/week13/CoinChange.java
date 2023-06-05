package classes.week13;

import java.util.ArrayList;

public class CoinChange {
    static ArrayList<Integer> coins = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        int amount = 5;
        int[] coinTypes = {1, 2, 3};
        findCoinChangeOptions(coinTypes, amount);
        //  System.out.println(result);
        for(int i=0;i<result.size();i++) {
        System.out.println(i+") "+result.get(i));
        }
    }

    public static void findCoinChangeOptions(int[] coinTypes, int amount) {
        if (amount == 0) {
            result.add(new ArrayList<>(coins));
            return;
        }
        if (amount < 0) {
            return;
        }
        for (int coin : coinTypes) {
            coins.add(coin);
            findCoinChangeOptions(coinTypes, amount - coin);
            coins.remove(coins.size() - 1);
        }
    }
}