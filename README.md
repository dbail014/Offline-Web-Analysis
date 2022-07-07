# Offline-Web-Analysis

CS350, Old Dominion Univ., Summer 2022

Thursday 1

* [Stories](https://trello.com/invite/b/TBR89wEb/ea18b25b95216d7d1f95d55cb45668ef/offline-web-analysis)

![Alt text](initial_uml.svg?raw=true "Title")

class Website {
  Path localPath
  Collection<URL> siteURLs
  Collection<HTMLDocument> allPages
}

Class HTMLDocument {
  Path localPath
  Collection<StyleSheet> styles
  Collection<JavaScript> scripts
  Collection<Anchor> links
  Collection<Image> images
  
}

class Stylesheet {

}

class Script {

}

class Image {

}

class Anchor {
    URI path
    Classification linkType
    TargetType destinationType
}

enum Classification {
    INTERNAL
    INTRAPAGE
    EXTERNAL
}

enum TargetType {
    HTMLDOCUMENT
    ARCHIVE
    VIDEO
    AUDIO
}

class DocumentParser {

}

class WebsiteWalker {

}

class ExcelWriter {

}

class JSONWriter {

}

class TextWriter {

}

Website *-- HTMLDocument
DocumentParser -->"populates" HTMLDocument
