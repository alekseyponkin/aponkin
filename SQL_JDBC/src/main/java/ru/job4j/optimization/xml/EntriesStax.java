package ru.job4j.optimization.xml;

import ru.job4j.optimization.entity.Entries;
import ru.job4j.optimization.entity.Entry;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Class EntriesStax.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 13.04.2018.
 */
public class EntriesStax {

    /**
     * Write entries to xml.
     * @param outFile output file xml.
     * @param entries entries for write.
     */
    public void writeXml(String outFile, Entries entries) {
        XMLStreamWriter writer = null;
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(new FileOutputStream(outFile));
            writer.writeStartDocument();
            writer.writeStartElement("entries");
            for (Entry entry : entries.getEntries()) {
               writer.writeStartElement("entry");
               writer.writeStartElement("field");
               writer.writeCharacters(String.valueOf(entry.getField()));
               writer.writeEndElement();
               writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();

        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}