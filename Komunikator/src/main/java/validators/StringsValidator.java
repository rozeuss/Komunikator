package validators;


public class StringsValidator {
	public boolean isAlpha(String name) {
		if(!name.isEmpty()){
		if(Character.isUpperCase(name.codePointAt(0))) return name.matches("[a-zA-Z]+");
		else return false;
		}
		else return false;
	}
	
//	public boolean isValidUsername(String name){
//		return false;
//	}
	
	
	public boolean isValidLogin(String pass) {
	    String n = ".*[0-9].*";
	    String a = ".*[A-Z].*";
	    return pass.matches(n) && pass.matches(a);
	}
	
}
