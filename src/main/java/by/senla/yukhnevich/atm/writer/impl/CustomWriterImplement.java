package by.senla.yukhnevich.atm.writer.impl;

import by.senla.yukhnevich.atm.entity.Card;
import by.senla.yukhnevich.atm.exception.CustomAtmException;
import by.senla.yukhnevich.atm.util.FileNameValidation;
import by.senla.yukhnevich.atm.writer.CustomWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implements CustomWriter. Class save changes in file
 *
 * @see CustomWriter
 */
public class CustomWriterImplement implements CustomWriter {
    private static final FileNameValidation fileNameValidator = new FileNameValidation();
    /**
     * The path to the file to be changed
     */
    private static final String PATH = "src\\main\\resources\\data\\cards.txt";

    /**
     * Saves changes from CustomAtmServiceImplement in file
     *
     * @param card     - user card
     * @param oldMoney - the old value of money
     * @throws CustomAtmException
     * @see by.senla.yukhnevich.atm.service.impl.CustomAtmServiceImplement
     */
    @Override
    public void save(Card card, int oldMoney) throws CustomAtmException {
        if (!fileNameValidator.validateFile(PATH)) {
            System.err.println("Reading from the file impossible");
            throw new CustomAtmException("Reading from the file impossible");
        }
        List<String> newLines = new ArrayList<>();
        try {
            for (String line : Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8)) {
                if (line.contains(card.getCardNumber())) {
                    newLines.add(line.replace(String.valueOf(oldMoney), Integer.toString(card.getBalance())));
                } else {
                    newLines.add(line);
                }
            }
            Files.write(Paths.get(PATH), newLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Reading from the file impossible");
            throw new CustomAtmException("Reading from the file impossible");
        }

    }

    /**
     * Changes value (1 to 0) in file for block card
     *
     * @param card - user card
     * @throws CustomAtmException
     * @see Card
     */
    @Override
    public void saveBlock(Card card) throws CustomAtmException {
        if (!fileNameValidator.validateFile(PATH)) {
            System.err.println("Reading from the file impossible");
            throw new CustomAtmException("Reading from the file impossible");
        }
        List<String> newLines = new ArrayList<>();
        try {
            for (String line : Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8)) {
                if (line.contains(card.getCardNumber())) {
                    newLines.add(line.replaceFirst("1", "0"));
                } else {
                    newLines.add(line);
                }
            }
            Files.write(Paths.get(PATH), newLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Reading from the file impossible");
            throw new CustomAtmException("Reading from the file impossible");
        }
    }

}
