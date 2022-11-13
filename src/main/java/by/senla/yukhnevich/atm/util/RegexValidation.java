package by.senla.yukhnevich.atm.util;

import java.util.regex.Pattern;

/**
 * Validation for card
 */
public class RegexValidation {
    /**
     * valid ХХХХ-ХХХХ-ХХХХ-ХХХХ
     *
     * @param string - a string to check for a pattern
     * @return - true if pattern ХХХХ-ХХХХ-ХХХХ-ХХХХ correct
     */
    public boolean validateRegex(String string) {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$");
        return pattern.matcher(string).matches();
    }
}
