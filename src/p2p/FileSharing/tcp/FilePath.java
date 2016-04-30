/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p.FileSharing.tcp;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Travis
 */
public class FilePath {
    
    final String SHAREDFOLDER = "SharedFolder";
    
    Path currentPath;
    String pathString;
    
    public FilePath() {};
    
    public String getCurrentPath() {
        currentPath = Paths.get("");
        pathString = currentPath.toAbsolutePath().toString();
        
        return pathString;
    }
    
    public void makeSharedDirectory() {
        File dir = new File(SHAREDFOLDER);
        dir.mkdir();
    }
    
    public String getSharedFolderPath() {
        FilePath filePath = new FilePath();
        String path = filePath.getCurrentPath();
        
        return path + File.separator + filePath.SHAREDFOLDER + File.separator ;
    }
}
