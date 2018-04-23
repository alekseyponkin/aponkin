package ru.job4j.optimization.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Entries.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 19.04.2018.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entries {
    /**
     * List entries.
     */
    @XmlElement(name = "entry")
    protected List<Entry> entries = new ArrayList<>();

    /**
     * Default constructor.
     */
    public Entries() {
    }

    public List<Entry> getEntries() {
        return this.entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}