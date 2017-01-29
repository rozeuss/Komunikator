package validators;

import java.util.regex.*;

public class EmailValidator {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    	    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static Matcher matcher;
    private boolean valid = false;


    public EmailValidator(String email) {
    	if(email == null) valid = false;
    	else{
            matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            if (matcher.matches())
                valid = true;
            else valid = false;
    	}
    }

    public boolean isValid() {
        return valid;
    }

}
