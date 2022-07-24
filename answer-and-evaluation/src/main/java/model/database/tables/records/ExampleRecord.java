/*
 * This file is generated by jOOQ.
 */
package model.database.tables.records;


import model.database.tables.Example;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ExampleRecord extends UpdatableRecordImpl<ExampleRecord> implements Record3<Long, String, String> {

    private static final long serialVersionUID = 353458487;

    /**
     * Setter for <code>public.example.key</code>.
     */
    public void setKey(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.example.key</code>.
     */
    public Long getKey() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.example.id</code>.
     */
    public void setId(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.example.id</code>.
     */
    public String getId() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.example.title</code>.
     */
    public void setTitle(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.example.title</code>.
     */
    public String getTitle() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Example.EXAMPLE.KEY;
    }

    @Override
    public Field<String> field2() {
        return Example.EXAMPLE.ID;
    }

    @Override
    public Field<String> field3() {
        return Example.EXAMPLE.TITLE;
    }

    @Override
    public Long component1() {
        return getKey();
    }

    @Override
    public String component2() {
        return getId();
    }

    @Override
    public String component3() {
        return getTitle();
    }

    @Override
    public Long value1() {
        return getKey();
    }

    @Override
    public String value2() {
        return getId();
    }

    @Override
    public String value3() {
        return getTitle();
    }

    @Override
    public ExampleRecord value1(Long value) {
        setKey(value);
        return this;
    }

    @Override
    public ExampleRecord value2(String value) {
        setId(value);
        return this;
    }

    @Override
    public ExampleRecord value3(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public ExampleRecord values(Long value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ExampleRecord
     */
    public ExampleRecord() {
        super(Example.EXAMPLE);
    }

    /**
     * Create a detached, initialised ExampleRecord
     */
    public ExampleRecord(Long key, String id, String title) {
        super(Example.EXAMPLE);

        set(0, key);
        set(1, id);
        set(2, title);
    }
}
