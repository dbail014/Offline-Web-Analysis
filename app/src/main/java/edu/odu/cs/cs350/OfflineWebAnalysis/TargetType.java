package edu.odu.cs.cs350.OfflineWebAnalysis;

/**
 * Enum for classifying type of a resource as archive, video, audio, or other
 * 
 * @author James Wright
 */
public enum TargetType {
    ARCHIVE,
    VIDEO,
    AUDIO,
    OTHER;

    /*
     * *Print* TargetType
     */
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
                break;
            case OTHER:
                targetTypeString = "other";
        }
        return targetTypeString;
    }
}