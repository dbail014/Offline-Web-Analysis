package edu.odu.cs.cs350.OfflineWebAnalysis;

public abstract class Resource implements Cloneable {

    // data members
    protected String URIpath;
    protected Classification classification;
    protected int localPageNumber;

    // TODO
    // Default Constructor
    public Resource() {
        this.URIpath = "";
        this.classification = Classification.EXTERNAL;
        this.localPageNumber = 0;
    }

    /*
     * @Description
     * 
     * @param _name desired name
     */
    public Resource(String _URIpath, Classification _classification, int _localPageNumber) {
        this.URIpath = _URIpath;
        this.classification = _classification;
        this.localPageNumber = _localPageNumber;
    }

    /*
     * Retrieve URI Path
     * 
     * @return current URI Path
     */
    public String getURIPath() {
        return this.URIpath;
    }

    /*
     * Update URI Path
     * 
     * @param _URIpath replacement URIpath
     */
    public void setURIPath(String _URIpath) {
        this.URIpath = _URIpath;
    }

    /*
     * Retrieve classificaiton
     * 
     * @return current classification
     */
    public Classification getClassification() {
        return this.classification;
    }

    /*
     * Update classification
     * 
     * @param _classification replacement classification
     */
    public void setClassification(Classification _classification) {
        this.classification = _classification;
    }

    /*
     * Retrieve URI Path
     * 
     * @return current URI Path
     */
    public int getLocalPageNumber() {
        return this.localPageNumber;
    }

    /*
     * Update URI Path
     * 
     * @param _URIpath replacement URIpath
     */
    public void setLocalPageNumber(int _localPageNumber) {
        this.localPageNumber = _localPageNumber;
    }

    /*
     * Check for logical equivalence.
     * 
     * @param rhs object for which a comparison is desired
     */
    @Override
    public abstract boolean equals(Object rhs);

    /*
     * Generate a hash code
     */
    @Override
    public abstract int hashCode();

    /*
     * Duplicate this item
     */
    @Override
    public abstract Resource clone();

    /*
     * *Print* a Resource
     */
    @Override
    public abstract String toString();
}