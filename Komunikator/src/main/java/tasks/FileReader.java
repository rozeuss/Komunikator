/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files; 
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ADMIN
 */
public class FileReader implements Runnable {
    
    private File file;
    private String filePath;
    FileInputStream fileInputStream;
    BufferedReader bufferedReader;
    private int lineCounter;
    public void setFile(String filepath, int lines) throws FileNotFoundException{
        filePath = filepath;
        fileInputStream = new FileInputStream(filePath);
        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
    }
    
    
    @Override
    public void run() {
        String line;
        int counter = 0;
        try {
            while ((line = bufferedReader.readLine()) != null || counter < 50 ) {
                //send the line to text area
                counter++;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex + "\n >>> ERROR WHILE READING A CONVERSATION FILE");
        }
        
    }
    
    
}
