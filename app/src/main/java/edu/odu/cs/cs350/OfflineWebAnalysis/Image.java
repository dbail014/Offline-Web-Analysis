package edu.odu.cs.cs350.OfflineWebAnalysis;

public class Image extends Resource {
    // data members
    protected long fileSize;

    /*
     * Default Constructor
     */
    public Image()
    {
        super();
        this.fileSize = 0;
    }

    /*
     * Duplicate an image
     * 
     * @param src image to duplicate
     */
    public Image(String _URIpath, Classification _classification, long _fileSize)
    {
        super(_URIpath, _classification);
        this.fileSize = _fileSize;
    }

    /**
     * Create a copy of this Image.
     *
     * @param src consumable item to duplicate
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
     * @param _fileSize replacement fileSize
     */
    public void setFileSize(long _fileSize) {
        this.fileSize = _fileSize;
    }

    /*
     * Clone--i.e., copy--this Item.
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
        if (!(rhs instanceof Image)) {
            return false;
        }

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
        return this.URIpath.hashCode() 
        + this.classification.hashCode()
        + (int)this.fileSize;
    }

    /*
     * *Print* one Image.
     */
    @Override
    public String toString()
    {
        return "{IMG:\n  URI: " + this.URIpath + ",\n  Classification: " + this.classification.toString() + (this.fileSize > 0 ? (",\n  Size: " + this.fileSize) : "") + "}\n";
    }
}