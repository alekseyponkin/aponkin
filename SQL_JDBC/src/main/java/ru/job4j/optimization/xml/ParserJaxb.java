package ru.job4j.optimization.xml;

import ru.job4j.optimization.entity.Entries;
import ru.job4j.optimization.entity.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Class ParserJaxb.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 18.04.2018.
 */
public class ParserJaxb {

    /**
     * Parse xml file and counting sum field entry.
     * @param inFile input xml file.
     * @return long sum.
     */
    public long parse(String inFile) {
        long sum = 0;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Entries entries = (Entries) unmarshaller.unmarshal(new File(inFile));
            for (Entry entry : entries.getEntries()) {
                sum = sum + entry.getField();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sum;
    }
}