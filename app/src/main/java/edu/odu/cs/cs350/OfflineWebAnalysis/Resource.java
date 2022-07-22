package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.net.URI;

public abstract class Resource implements Cloneable {

    // data members
    protected URI URIpath;
    protected Classification classification;

    /*
     * Default constructor
     */
    public Resource() {
        // no URI here because no empty/default version
        this.classification = Classification.EXTERNAL;
    }

    /*
     * Non-default constructor
     */
    public Resource(URI _URIpath, Classification _classification) {
        this.URIpath = _URIpath;
        this.classification = _classification;
    }

    /*
     * Retrieve URI Path
     * 
     * @return current URI Path
     */
    public URI getURIPath() {
        return this.URIpath;
    }

    /*
     * Update URI Path
     * 
     * @param _URIpath replacement URIpath
     */
    public void setURIPath(URI _URIpath) {
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
