package ru.job4j.optimization.xml;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.nio.file.Paths;

/**
 * Class TransformerXslt.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 14.04.2018.
 */
public class TransformerXslt {
    /**
     * The input file xml to transform.
     */
    private String inFile;
    /**
     * The output file xml.
     */
    private String outFile;

    /**
     * Constructor with parameters.
     * @param inFile the input xml file.
     * @param outFile the output xml file.
     */
    public TransformerXslt(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    /**
     * Start transformation.
     */
    public void start() {
        String pathFolder = Paths.get(inFile).toAbsolutePath().getParent().toString() + File.separator;
        File file = new File(pathFolder + "converter.xsl");
        StreamSource streamSource = new StreamSource(file);
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(streamSource);
            StreamResult result = new StreamResult(outFile);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(new StreamSource(new File(inFile)), result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}