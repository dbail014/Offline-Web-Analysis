package edu.odu.cs.cs350.OfflineWebAnalysis;

enum Classification {
    INTERNAL,
    INTRAPAGE,
    EXTERNAL;

    public String toString() {
        switch(this) {
            case INTERNAL:
                return "internal";
            case INTRAPAGE:
                return "intrapage";
            case EXTERNAL: 
                return "external";
            default:
                return null;
        }
    }
}