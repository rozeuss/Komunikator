package database;
public class CredentialsSender {
    
    private CredentialsSender(){}
    private static CredentialsSender credentialsSenderHolder;
    public void sendCredentials(String username, String hashedPassword) 
    {
         
    }
    public static CredentialsSender getInstance(){
        if(credentialsSenderHolder == null)
            credentialsSenderHolder = new CredentialsSender();
            
        return credentialsSenderHolder;
    }
}
