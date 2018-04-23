package ru.job4j.optimization.entity;

import javax.xml.bind.annotation.*;

/**
 * Class Entry.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 10.04.2018.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entry {
    /**
     * Field entry.
     */
    @XmlAttribute(name = "field", required = true)
    private int field;

    /**
     * Default constructor.
     */
    public Entry() {
    }

    public int getField() {
        return this.field;
    }

    public void setField(int field) {
        this.field = field;
    }
}