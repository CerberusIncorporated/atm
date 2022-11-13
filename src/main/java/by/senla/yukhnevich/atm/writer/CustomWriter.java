package by.senla.yukhnevich.atm.writer;

import by.senla.yukhnevich.atm.entity.Card;
import by.senla.yukhnevich.atm.exception.CustomAtmException;

import java.io.IOException;

/**
 *  Interface that describes what writer should have
 */
public interface CustomWriter {

    /**
     * Save changes in file
     * @param card - user card
     * @param oldMoney - the old value of money
     * @throws CustomAtmException
     * @throws IOException
     */
    void save(Card card, int oldMoney) throws CustomAtmException, IOException;

    /**
     * Makes the card block
     * @param card - user card
     * @throws CustomAtmException
     */
    void saveBlock(Card card) throws CustomAtmException;
}
