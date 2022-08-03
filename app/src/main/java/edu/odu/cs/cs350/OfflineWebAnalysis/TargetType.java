package edu.odu.cs.cs350.OfflineWebAnalysis;

/**
 * Description...
 * 
 * 
 * @author James Wright
 * 
 */
public enum TargetType {
    ARCHIVE,
    VIDEO,
    AUDIO;

    public String toString() {
        String targetTypeString = "";
        switch(this) {
            case ARCHIVE:
                targetTypeString = "archive";
                break;
            case VIDEO:
                targetTypeString = "video";
                break;
            case AUDIO: 
                targetTypeString = "audio";
        }
        return targetTypeString;
    }
}