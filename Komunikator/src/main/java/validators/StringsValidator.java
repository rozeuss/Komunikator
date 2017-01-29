package validators;


public class StringsValidator {
	public boolean isAlpha(String name) {
		if(!name.isEmpty()){
		if(Character.isUpperCase(name.codePointAt(0))) return name.matches("[a-zA-Z]+");
		else return false;
		}
		else return false;
	}
	
	public boolean isValidUsername(String pass) {
	    String a = "[a-zA-Z]+[0-9]*";
	    return pass.matches(a);
	}
	
	public boolean isValidPass(String pass) {
	    String n = ".*[0-9].*";
	    String a = ".*[a-z].*";
	    return pass.matches(n) && pass.matches(a);
	}
	
}
