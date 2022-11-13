package by.senla.yukhnevich.atm;

import by.senla.yukhnevich.atm.exception.CustomAtmException;
import by.senla.yukhnevich.atm.menu.impl.CustomMenuImplement;

/**
 * Class need for start atm
 */
public class AtmLauncher {
    public static void main(String[] args) throws CustomAtmException {
        CustomMenuImplement customMenuImplement = new CustomMenuImplement();
        customMenuImplement.start();
    }
}
