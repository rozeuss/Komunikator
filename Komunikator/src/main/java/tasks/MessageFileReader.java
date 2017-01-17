/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;

/**
 *
 * @author ADMIN
 */
public class MessageFileReader {
	FileChooser fileChooser;
	File savedFile;
	private int clientID;
        PrintWriter out;
        
        public MessageFileReader(int clientID){
            String path = "/resources/messages/"+clientID;
            try {
                out = new PrintWriter(path);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MessageFileSaver.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.clientID = clientID;
        }
        
    public void readFromFile(){
        
    }   
}
