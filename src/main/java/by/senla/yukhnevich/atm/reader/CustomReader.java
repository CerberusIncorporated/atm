package by.senla.yukhnevich.atm.reader;

import by.senla.yukhnevich.atm.exception.CustomAtmException;

import java.util.List;

/**
 * Interface that describes what reader should have
 */
public interface CustomReader {
    List<String> findCard(String path) throws CustomAtmException;
}
