package by.senla.yukhnevich.atm.reader.impl;

import by.senla.yukhnevich.atm.exception.CustomAtmException;
import by.senla.yukhnevich.atm.reader.CustomReader;
import by.senla.yukhnevich.atm.util.FileNameValidation;
import by.senla.yukhnevich.atm.util.RegexValidation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implements CustomReader. Class read and parse file that is specified in the variable PATH.
 *
 * @see CustomReader
 */
public class CustomReaderImplement implements CustomReader {
    private static final FileNameValidation fileNameValidator = new FileNameValidation();
    private static final String PATH = "src\\main\\resources\\data\\cards.txt";
    private final RegexValidation regexValidation = new RegexValidation();

    /**
     * The method searches for the card number in the file.
     * If it is found it fills the array with credit card data.
     *
     * @param number - the number of the card to be found
     * @return - array with card data or null
     * @throws CustomAtmException
     * @see by.senla.yukhnevich.atm.entity.Card
     */
    public List<String> findCard(String number) throws CustomAtmException {
        if (!fileNameValidator.validateFile(PATH)) {
            System.err.println("Reading from the file impossible");
            throw new CustomAtmException("Reading from the file impossible");
        }
        if (!regexValidation.validateRegex(number)) {
            return null;
        }
        byte[] content;
        try (FileInputStream fis = new FileInputStream((PATH));) {
            content = new byte[fis.available()];
            fis.read(content);
        } catch (IOException e) {
            System.err.println("Reading from the file impossible");
            throw new CustomAtmException(e);
        }
        ArrayList<String> list = new ArrayList<>();
        String[] lines = new String(content).split(System.lineSeparator());
        for (String line : lines) {
            String[] numbers = line.split(" ");
            int j = 1;
            for (String cardNumber : numbers) {
                if (cardNumber.equals(number)) {
                    list.add(numbers[0]);
                    list.add(cardNumber);
                    list.add(numbers[j]);
                    list.add(numbers[j + 1]);
                    return list;
                }
                j++;
            }
        }
        return null;
    }
}
