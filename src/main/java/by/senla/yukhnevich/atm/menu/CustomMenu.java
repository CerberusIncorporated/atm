package by.senla.yukhnevich.atm.menu;

import by.senla.yukhnevich.atm.entity.Card;
import by.senla.yukhnevich.atm.exception.CustomAtmException;

/**
 * Interface that describes what an ATM menu should have
 */
public interface CustomMenu {
    void start() throws CustomAtmException;

    void controlPanel(Card card) throws CustomAtmException;
}
