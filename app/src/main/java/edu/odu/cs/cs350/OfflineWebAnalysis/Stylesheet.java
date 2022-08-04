package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.net.URI;

/**
 * The Stylesheet class extends the Resource class. This class allows an application to store information for a single stylesheet resources for analysis and output.
 * 
 * @author James Wright
 * 
 */
public class Stylesheet extends Resource {
    /*
     * Default Constructor
     */
    public Stylesheet() {
        super();
    }

    /*
     * Non-default constructor
     */
    public Stylesheet(URI _URIpath, Classification _classification) {
        super(_URIpath, _classification);
    }

    /**
     * Create a copy of this Stylesheet.
     *
     * @param src stylesheet duplicate
     */
    public Stylesheet(Stylesheet src) {
        super(src.URIpath, src.classification);
    }

    /*
     * Clone--i.e., copy--this Stylesheet.
     */
    @Override
    public Resource clone() {
        Stylesheet src = new Stylesheet();
        src.URIpath = this.URIpath;
        src.classification = this.classification;
        return src;
    }

    /*
     * Check for logical equivalence
     * 
     * @param rhs object for which a comparison is desired
     */
    @Override
    public boolean equals(Object rhs) {
        Stylesheet rhsResource = (Stylesheet) rhs;

        return this.classification.equals(rhsResource.classification)
                && this.URIpath.equals(rhsResource.URIpath);
    }

    /*
     * Generate a hash code
     */
    @Override
    public int hashCode() {
        return (this.URIpath != null ? this.URIpath.hashCode() : 0)
                + this.classification.hashCode();
    }

    /*
     * *Print* one Stylesheet.
     */
    @Override
    public String toString() {
        return "{" + this.URIpath.toString() + ", \n" + this.classification.toString() + "}\n";
    }
}
