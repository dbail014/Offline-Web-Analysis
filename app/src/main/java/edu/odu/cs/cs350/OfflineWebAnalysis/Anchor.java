package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.net.URI;

/**
 * The Anchor class extends the Resource class. This class allows an application to store information for a single anchor resources for analysis and output.
 * 
 * @author James Wright
 * 
 */
public class Anchor extends Resource {

    /*
     * Default Constructor
     */
    public Anchor() {
        super();
    }

    /*
     * Non-default constructor
     */
    public Anchor(URI _URIpath, Classification _classification) {
        super(_URIpath, _classification);
    }

    /**
     * Create a copy of this Anchor.
     *
     * @param src anchor to duplicate
     */
    public Anchor(Anchor src) {
        super(src.URIpath, src.classification);
    }

    /*
     * Clone--i.e., copy--this Anchor.
     */
    @Override
    public Resource clone() {
        Anchor src = new Anchor();
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
        Anchor rhsResource = (Anchor) rhs;

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
     * *Print* one Anchor.
     */
    @Override
    public String toString()
    {
        return "{" + this.URIpath.toString() + ", \n" + this.classification.toString() + "}\n";
    }
}