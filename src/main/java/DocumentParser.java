package main.java;
package org.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
    

public class DocumentParser {

    // private String rawHTML;

    public static void main(String[] ars) throws IOException {
        Document testDoc = Jsoup.connect("http://example.com").get();
        System.out.println(testDoc.title());
    }
}

