/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lepiou;

/**
 *
 * @author lepioo
 */
public class FileNameInfo {
    
    private String path;
    private String location;
    private String baseName;
    private String completeName;
    private String extension;

    public String getPath() {
        return path;
    }
    
    public String getLocation() {
        return location;
    }

    public String getBaseName() {
        return baseName;
    }

    public String getCompleteName() {
        return completeName;
    }

    public String getExtension() {
        return extension;
    }
    
    public static FileNameInfo getFileInfo(String pPath){
        FileNameInfo inf=new FileNameInfo();
        inf.path=pPath;
            String path = pPath;
            if (path.lastIndexOf("/") > -1) {
                path = path.substring(0, path.lastIndexOf("/") + 1);
                inf.completeName = pPath.substring(pPath.lastIndexOf("/") + 1, pPath.length());
            } else {
                path = path.substring(0, path.lastIndexOf("\\") + 1);
                inf.completeName = pPath.substring(pPath.lastIndexOf("\\") + 1, pPath.length());
            }
            inf.location = pPath.substring(0,pPath.lastIndexOf(inf.completeName));
            inf.extension = inf.completeName.substring(inf.completeName.lastIndexOf(".") + 1, inf.completeName.length());
            inf.baseName = inf.completeName.substring(0, inf.completeName.lastIndexOf("."));
        return inf;
    }
    
    public String toString(){
        return("FileNameInfo:[path:"+path+", location:"+location+", completeName:"+completeName+", baseName:"+baseName+", extension:"+extension+"]");
    }
}
