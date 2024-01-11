/*
 Spyros Kanakopoylos 3212018071 
   Thodoris Theodorellis 3212018061
 */

package project;

public class SystemInfo {

    public static String javaVersion() {
        return System.getProperty("java.version");
    }

    public static String javafxVersion() {
        return System.getProperty("javafx.version");
    }

}