/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import javafx.scene.control.Tab;

/**
 *
 * @author ADMIN
 */
public class Conversation extends Tab{
    
    private String clientName;

    public Conversation(String name) {
        super();
        clientName = name;
    }

    public void setClientID(String clientID) {
        this.clientName = clientID;
    }

    public String getClientID() {
        return clientName;
    }
    
}
