package by.senla.yukhnevich.atm.entity;

/**
 * Class ATM with param <b>atmAmount</b>
 */
public class Atm {
    /**
     * param with the value of the amount of money in the ATM
     */
    private static long atmAmount = 20000;

    public long getAtmAmount() {
        return atmAmount;
    }

    public static void setAtmAmount(long atmAmount) {
        Atm.atmAmount = atmAmount;
    }

}
