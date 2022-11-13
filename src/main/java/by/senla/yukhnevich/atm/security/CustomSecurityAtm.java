package by.senla.yukhnevich.atm.security;

import by.senla.yukhnevich.atm.entity.Card;

/**
 * Class for checking the PIN code and blocking
 */
public class CustomSecurityAtm {
    /**
     * Check pin
     *
     * @param cardPin      - the PIN code of the card is transmitted from the file
     * @param inputUserPin - the pin code entered by the user
     * @return - true if pin code equal
     */
    public boolean checkPin(int cardPin, int inputUserPin) {
        return cardPin == inputUserPin;
    }

    /**
     * Check if card blocked
     *
     * @param card - if 0 - card is blocked
     * @return - true is card blocked
     */
    public boolean checkBlock(Card card) {
        return card.getIsBlock() == 0;
    }
}
