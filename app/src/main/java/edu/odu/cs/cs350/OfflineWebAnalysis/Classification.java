package edu.odu.cs.cs350.OfflineWebAnalysis;

/**
 * Enum for classification of resource as internal (same as intra-site), intra-page, or external
 * 
 * @author James Wright
 */
enum Classification {
    INTERNAL,
    INTRAPAGE,
    EXTERNAL;

    /*
     * *Print* classification.
     */
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