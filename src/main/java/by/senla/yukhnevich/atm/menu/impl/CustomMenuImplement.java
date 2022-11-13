package by.senla.yukhnevich.atm.menu.impl;

import by.senla.yukhnevich.atm.entity.Card;
import by.senla.yukhnevich.atm.exception.CustomAtmException;
import by.senla.yukhnevich.atm.menu.CustomMenu;
import by.senla.yukhnevich.atm.reader.impl.CustomReaderImplement;
import by.senla.yukhnevich.atm.security.CustomSecurityAtm;
import by.senla.yukhnevich.atm.service.impl.CustomAtmServiceImplement;
import by.senla.yukhnevich.atm.writer.impl.CustomWriterImplement;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @see CustomMenu
 * Class implements a contract with CustomMenu
 */
public class CustomMenuImplement implements CustomMenu {

    private final CustomReaderImplement customReaderImplement = new CustomReaderImplement();
    private final CustomAtmServiceImplement serviceImplement = new CustomAtmServiceImplement();
    private final CustomSecurityAtm securityAtm = new CustomSecurityAtm();
    private final CustomWriterImplement writerImplement = new CustomWriterImplement();
    /**
     * Method check is the card number, PIN code correct and is the card blocked.
     * If everything is fine then call controlPanel.
     *
     * @throws CustomAtmException
     */
    @Override
    public void start() throws CustomAtmException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type card number: ХХХХ-ХХХХ-ХХХХ-ХХХХ");
            List<String> cardInfo = customReaderImplement.findCard(scanner.nextLine());
            if (cardInfo != null) {
                System.out.println("Card is exist.");
                Card card = new Card(Integer.parseInt(cardInfo.get(0)), cardInfo.get(1), Integer.parseInt(cardInfo.get(2)), Integer.parseInt(cardInfo.get(3)));
                if (securityAtm.checkBlock(card)) {
                    System.err.println("Your card is block! Contact support service!");
                    break;
                }
                System.out.println("Type pin: XXXX");
                try {
                    int i;
                    for (i = 0; i < 3; i++) {
                        String inputUser = scanner.nextLine();
                        if (inputUser != null && inputUser.matches("[0-9]+")) {
                            if (securityAtm.checkPin(card.getPinCode(), Integer.parseInt(inputUser))) {
                                System.out.println("Welcome!");
                                controlPanel(card);
                                break;
                            }
                        } else {
                            System.err.println("Pin is wrong! Try again: ");
                        }
                    }
                    if (i == 3) {
                        writerImplement.saveBlock(card);
                        System.err.println("Too many attempts. The card is blocked!");
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Pin is wrong! Try again.");
                }
                System.out.println("End session");
                break;
            } else {
                System.err.println("Card number is wrong or not exist");
            }
        }
    }

    /**
     * Method the method simulates a banking panel
     * and depending on what the user chooses passes execution to CustomAtmServiceImplement
     *
     * @param card - it is needed for changing and reading depending on the user's choice
     * @throws CustomAtmException
     * @see CustomAtmServiceImplement
     */
    @Override
    public void controlPanel(Card card) throws CustomAtmException {
        System.out.println("Choose operation: ");
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("1 - Check balance");
            System.out.println("2 - Withdraw");
            System.out.println("3 - Replenishment");
            System.out.println("0 - End session");
            String operation = scanner.nextLine();
            String amount;
            try {
                switch (operation) {
                    case ("1"):
                        System.out.println("Your balance: " + serviceImplement.checkBalance(card));
                        break;
                    case ("2"):
                        System.out.println("Type amount to withdraw: ");
                        amount = scanner.nextLine();
                        serviceImplement.withdraw(card, Integer.parseInt(amount));
                        break;
                    case ("3"):
                        System.out.println("Type amount to replenishment: ");
                        amount = scanner.nextLine();
                        serviceImplement.replenishment(card, Integer.parseInt(amount));
                        break;
                    case ("0"):
                        System.out.println("Exit...");
                        flag = false;
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Type integer numbers!");
            }
        }
    }
}
