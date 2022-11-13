package by.senla.yukhnevich.atm.service;

import by.senla.yukhnevich.atm.entity.Card;
import by.senla.yukhnevich.atm.exception.CustomAtmException;

import java.io.IOException;

/**
 * Interface that describes what Atm should have
 */
public interface CustomAtmService {
    /**
     * Method for checking balance on the card
     *
     * @param card - user card
     * @return - balance on the card
     */
    int checkBalance(Card card);

    /**
     * For withdraw money
     *
     * @param card   - user card
     * @param amount - amount money for withdraw
     * @return - return withdrawn money
     * @throws IOException
     * @throws CustomAtmException
     */
    int withdraw(Card card, int amount) throws IOException, CustomAtmException;

    /**
     * @param card   - user card
     * @param amount - amount money for replenishment
     * @return - return replenishment money
     * @throws IOException
     * @throws CustomAtmException
     */
    int replenishment(Card card, int amount) throws IOException, CustomAtmException;
}
