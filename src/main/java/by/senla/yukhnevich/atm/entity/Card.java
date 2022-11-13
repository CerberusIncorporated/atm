package by.senla.yukhnevich.atm.entity;

/**
 * Class Card with params
 */
public class Card {

    private int isBlock;
    private String cardNumber;
    private int pinCode;
    private int balance;

    /**
     * @param isBlock    - block field. If isBlock = 0 - Card blocked. If isBlock = 1 - Card not blocked.
     * @param cardNumber - card number should be ХХХХ-ХХХХ-ХХХХ-ХХХХ
     * @param pinCode    - pin should be XXXX
     * @param balance    - card balance
     */
    public Card(int isBlock, String cardNumber, int pinCode, int balance) {
        this.isBlock = isBlock;
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getIsBlock() {
        return isBlock;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(" ").append(isBlock);
        sb.append(cardNumber);
        sb.append(" ").append(pinCode);
        sb.append(" ").append(balance);
        return sb.toString();
    }
}
