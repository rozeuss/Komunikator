/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;

/**
 *
 * @author ADMIN
 */
public class FileAppender {
    
    private File file;
    private String filePath;
    BufferedWriter bufferedWriter;
    FileWriter fileWriter;
             
    public void setFile(String filepath) throws IOException
    {
        filePath = filepath;
        file = new File(filePath);
        if(!file.exists())
            file.createNewFile();
    }
    
    public void writeToFile(String content) throws IOException
    {
        fileWriter     = new FileWriter(file.getAbsoluteFile(),true);
        bufferedWriter = new BufferedWriter(fileWriter);   
        
        bufferedWriter.write(content);
        bufferedWriter.newLine();
        bufferedWriter.close();
    
    }
    
    
}
