package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.net.URI;

// TODOs
// Update documentation of to be in line with javadoc standards

/**
 * Description...
 * 
 * 
 * @author James Wright
 * 
 */
public class Script extends Resource {
    /*
     * Default Constructor
     */
    public Script()
    {
        super();
    }

    /*
     * Non-default constructor
     */
    public Script(URI _URIpath, Classification _classification)
    {
        super(_URIpath, _classification);
    }

    /**
     * Create a copy of this Image.
     *
     * @param src consumable item to duplicate
     */
    public Script(Script src)
    {
        super(src.URIpath, src.classification);
    }

    /*
     * Clone--i.e., copy--this Item.
     */
    @Override
    public Resource clone() {
        Script src = new Script();
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
        if (!(rhs instanceof Script)) {
            return false;
        }

        Script rhsResource = (Script) rhs;

        return this.classification.equals(rhsResource.classification)
                && this.URIpath.equals(rhsResource.URIpath);
    }

    @Override
    public int hashCode() {
        return (this.URIpath != null ? this.URIpath.hashCode() : 0)
                + this.classification.hashCode();
    }

    /*
     * *Print* one Image.
     */
    @Override
    public String toString() {
        return "{" + this.URIpath.toString() + ", \n" + this.classification.toString() + "}\n";
    }
}
