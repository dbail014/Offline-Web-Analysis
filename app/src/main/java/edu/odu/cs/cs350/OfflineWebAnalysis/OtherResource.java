package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.net.URI;

/**
 * The Stylesheet class extends the Resource class. This class allows an application to store information for a single stylesheet resources for analysis and output.
 * 
 * @author James Wright
 * 
 */
public class OtherResource extends Resource {
    private TargetType targetType;
    private long fileSize;

    /*
     * Default Constructor
     */
    public OtherResource()
    {
        super();
        this.targetType = TargetType.ARCHIVE;
        this.fileSize = 0;
    }

    /*
     * Non-default constructor
     */
    public OtherResource(URI _URIpath, Classification _classification, TargetType _targetType, long _fileSize)
    {
        super(_URIpath, _classification);
        this.targetType = _targetType;
    }

    /**
     * Create a copy of this OtherResource.
     *
     * @param src OtherResource to duplicate
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
     * Retrieve File Size
     * 
     * @return current File Size
     */
    public long getFileSize() {
        return this.fileSize;
    }

    /*
     * Update File Size
     * 
     * @param _fileSize
     */
    public void setFileSize(long _fileSize) {
        this.fileSize = _fileSize;
    }

    /*
     * Clone--i.e., copy--this OtherResource.
     */
    @Override
    public Resource clone() {
        OtherResource src = new OtherResource();
        src.URIpath = this.URIpath;
        src.classification = this.classification;
        src.targetType = this.targetType;
        src.fileSize = this.fileSize;
        return src;
    }

    /*
     * Check for logical equivalence
     * 
     * @param rhs object for which a comparison is desired
     */
    @Override
    public boolean equals(Object rhs) {
        OtherResource rhsResource = (OtherResource) rhs;

        return this.classification.equals(rhsResource.classification)
                && this.URIpath.equals(rhsResource.URIpath)
                && (this.targetType == rhsResource.targetType)
                && (this.fileSize == rhsResource.fileSize);
    }

    /*
     * Generate a hash code
     */
    @Override
    public int hashCode() {
        return (this.URIpath != null ? this.URIpath.hashCode() : 0)
                + this.classification.hashCode()
                + this.targetType.hashCode()
                + Long.hashCode(this.fileSize);
    }

    /*
     * *Print* one Image.
     */
    @Override
    public String toString() {
        return "{" + this.URIpath.toString() + ", \n" + this.targetType.toString() + ", \n" + this.fileSize + "}\n";
    }
}