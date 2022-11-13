package by.senla.yukhnevich.atm.util;

import by.senla.yukhnevich.atm.exception.CustomAtmException;

import java.io.File;

/**
 * Checks the file name so that no errors occur
 */
public class FileNameValidation {
    /**
     * Checks the file to work with it
     *
     * @param path - path to file for checking
     * @return - true if everything fine
     * @throws CustomAtmException
     */
    public boolean validateFile(String path) {
        if (path == null) {
            System.err.println("The path equal to null");
            return false;
        }
        if (path.isBlank()) {
            System.err.println("The name of the path is empty");
            return false;
        }
        File file = new File(path);
        return file.exists() && file.length() > 0;
    }
}
