package tasks;

import chat.ConversationController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.stage.FileChooser;

public class MessageFileSaver {
	private File savedFile;
	private int clientID;
        private FileWriter out;
        
        public MessageFileSaver(int clientID) throws IOException{
            String path = "/resources/messages/"+clientID;
            out = new FileWriter(path,true);
            this.clientID = clientID;
        }
        
    public void writeToFile(String message) throws IOException{
        out.write(message);
    }
      
	
}