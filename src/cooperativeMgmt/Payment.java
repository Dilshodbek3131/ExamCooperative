package cooperativeMgmt;

import java.util.Stack;

public class Payment {
    private static   int amount;

    public Payment(int amount){
        this.amount = amount;
    }

    public static int getAmount() {
        return amount;
    }
}
