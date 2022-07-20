package main.java;

import java.util.Map;
import java.util.HashMap;

// import java.util.Vector;

public final class HTMLDocument {
    /**
     * ItemFactory is a collection of static functions. There is no reason to
     * instatiate an ItemFactory object.
     */
    private HTMLDocument()
    {
        // do not allow ItemFactory to be instantiated.
    }

    /**
     * This Lookup table contains a listing of all known keywords and the Item
     * sub-classes to which the correspond.
     */
    private static final Map<String, Resource> KNOWN_RESOURCES = new HashMap<String, Resource>() {{
        // put("StyleSheet", new Stylesheet());
        // put("JavaScript", new Script());
        // put("Anchor", new Anchor());
        put("Image", new Image());
    }};



}