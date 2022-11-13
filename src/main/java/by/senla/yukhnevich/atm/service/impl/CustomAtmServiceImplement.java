package by.senla.yukhnevich.atm.service.impl;

import by.senla.yukhnevich.atm.entity.Atm;
import by.senla.yukhnevich.atm.entity.Card;
import by.senla.yukhnevich.atm.exception.CustomAtmException;
import by.senla.yukhnevich.atm.service.CustomAtmService;
import by.senla.yukhnevich.atm.writer.impl.CustomWriterImplement;

/**
 * Class implements CustomAtmService. Class performs operations for an ATM
 *
 * @see CustomAtmService
 */
public class CustomAtmServiceImplement implements CustomAtmService {
    /**
     * Atm need for changing amount of money in the ATM
     *
     * @see Atm
     * CustomWriterImplement need for save changes in file
     * @see CustomWriterImplement
     */
    private Atm atm = new Atm();
    private final CustomWriterImplement customWriterImplement = new CustomWriterImplement();

    /**
     * Checking card balance
     *
     * @param card - user card
     * @return - balance user card
     */
    @Override
    public int checkBalance(Card card) {
        return card.getBalance();
    }

    /**
     * Withdraws money from an ATM and saves the changes to a file.
     *
     * @param card   - user card
     * @param amount - amount money for withdraw
     * @return - return withdrawn money
     * @throws CustomAtmException
     */
    @Override
    public int withdraw(Card card, int amount) throws CustomAtmException {
        int money = 0;
        if(amount <= 0){
            System.err.println("Type number more than zero.");
        }
        else if (card.getBalance() >= amount && atm.getAtmAmount() >= amount) {
            int oldMoney = card.getBalance();
            money = card.getBalance() - amount;
            card.setBalance(money);
            long atmBalance = atm.getAtmAmount() - amount;
            atm.setAtmAmount(atmBalance);
            customWriterImplement.save(card, oldMoney);
            System.out.println("Withdraw complete successful!");
        } else {
            System.out.println("Not enough money on the card or at the ATM.");
        }

        return money;
    }

    /**
     * Replenishment money in ATM and saves the changes to a file.
     *
     * @param card   - user card
     * @param amount - amount money for replenishment
     * @return - return replenishment money
     * @throws CustomAtmException
     */
    @Override
    public int replenishment(Card card, int amount) throws CustomAtmException {
        int money = 0;
        if(amount <= 0){
            System.err.println("Type number more than zero.");
        }
        else if (amount < 1000000) {
            int oldMoney = card.getBalance();
            money = card.getBalance() + amount;
            card.setBalance(money);
            long atmBalance = atm.getAtmAmount() + amount;
            atm.setAtmAmount(atmBalance);
            money = amount;
            customWriterImplement.save(card, oldMoney);
            System.out.println("Replenishment complete successful!");
        } else {
            System.out.println("Replenishment should be no more than 1 000 000");
        }

        return money;
    }
}
