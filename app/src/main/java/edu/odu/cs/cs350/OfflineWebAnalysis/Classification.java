package edu.odu.cs.cs350.OfflineWebAnalysis;

/**
 * Description...
 * 
 * 
 * @author James Wright
 * 
 */
enum Classification {
    INTERNAL,
    INTRAPAGE,
    EXTERNAL;

    public String toString() {
        String classificationString = "";
        switch(this) {
            case INTERNAL:
                classificationString = "internal";
                break;
            case INTRAPAGE:
                classificationString = "intrapage";
                break;
            case EXTERNAL: 
                classificationString = "external";
        }
        return classificationString;
    }
}