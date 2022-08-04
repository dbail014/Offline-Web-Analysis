package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.net.URI;

/**
 * The Image class extends the Resource class. This class allows an application to store information for a single image resources for analysis and output.
 * 
 * @author James Wright
 * 
 */
public class Image extends Resource {
    // data members
    private long fileSize;

    /*
     * Default Constructor
     */
    public Image()
    {
        super();
        this.fileSize = 0;
    }

    /*
     * Non-default constructor
     */
    public Image(URI _URIpath, Classification _classification, long _fileSize)
    {
        super(_URIpath, _classification);
        this.fileSize = _fileSize;
    }

    /**
     * Create a copy of this Image.
     *
     * @param src Image to duplicate
     */
    public Image(Image src)
    {
        super(src.URIpath, src.classification);
        this.fileSize = src.fileSize;
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
     * Clone--i.e., copy--this Image.
     */
    @Override
    public Resource clone()
    {
        Image src = new Image();
        src.URIpath = this.URIpath;
        src.classification = this.classification;
        src.fileSize = this.fileSize;
        return src;
    }

    /*
     * Check for logical equivalence
     * 
     * @param rhs object for which a comparison is desired
     */
    @Override
    public boolean equals(Object rhs)
    {
        Image rhsResource = (Image) rhs;

        return this.classification.equals(rhsResource.classification) 
        && this.URIpath.equals(rhsResource.URIpath) 
        && (this.fileSize == rhsResource.fileSize);
    }

    /*
     * Generate a hash code
     */
    @Override
    public int hashCode()
    {
        return (this.URIpath != null ? this.URIpath.hashCode() : 0)
        + this.classification.hashCode()
        + Long.hashCode(this.fileSize);
    }

    /*
     * *Print* one Image.
     */
    @Override
    public String toString()
    {
        return "{" + this.URIpath.toString() + ", \n" + this.classification.toString() + ",\n" + this.fileSize + "}\n";
    }
}
