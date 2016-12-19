/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Maciej
 */
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
