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
public class OtherResource extends Resource {
    private TargetType targetType;

    /*
     * Default Constructor
     */
    public OtherResource()
    {
        super();
        this.targetType = TargetType.ARCHIVE;
    }

    /*
     * Non-default constructor
     */
    public OtherResource(URI _URIpath, Classification _classification, TargetType _targetType)
    {
        super(_URIpath, _classification);
        this.targetType = _targetType;
    }

    /**
     * Create a copy of this Image.
     *
     * @param src consumable item to duplicate
     */
    public OtherResource(OtherResource src)
    {
        super(src.URIpath, src.classification);
        this.targetType = src.targetType;
    }

    /*
     * Retrieve target type
     * 
     * @return current target type
     */
    public TargetType getTargetType () {
        return this.targetType;
    }

    public void setTargetType(TargetType _targetType) {
        this.targetType = _targetType;
    }

    /*
     * Clone--i.e., copy--this Item.
     */
    @Override
    public Resource clone() {
        OtherResource src = new OtherResource();
        src.URIpath = this.URIpath;
        src.classification = this.classification;
        src.targetType = this.targetType;
        return src;
    }

    /*
     * Check for logical equivalence
     * 
     * @param rhs object for which a comparison is desired
     */
    @Override
    public boolean equals(Object rhs) {
        if (!(rhs instanceof Image)) {
            return false;
        }

        OtherResource rhsResource = (OtherResource) rhs;

        return this.classification.equals(rhsResource.classification)
                && this.URIpath.equals(rhsResource.URIpath)
                && (this.targetType == rhsResource.targetType);
    }

    /*
     * Generate a hash code
     */
    @Override
    public int hashCode() {
        return (this.URIpath != null ? this.URIpath.hashCode() : 0)
                + this.classification.hashCode()
                + this.targetType.hashCode();
    }

    /*
     * *Print* one Image.
     */
    @Override
    public String toString() {
        // TODO
        return "";
    }
}
