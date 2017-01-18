package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgeValidator {

	public static final Pattern VALID_AGE_REGEX = Pattern.compile("^[1-9]*[1-9][0-9]*$", Pattern.CASE_INSENSITIVE);
	private static Matcher matcher;
	private boolean valid = false;

	public AgeValidator(String age) {
		matcher = VALID_AGE_REGEX.matcher(age);
		if (matcher.matches())
			valid = true;
		else
			valid = false;
	}

	public boolean isValid() {
		return valid;
	}

}
