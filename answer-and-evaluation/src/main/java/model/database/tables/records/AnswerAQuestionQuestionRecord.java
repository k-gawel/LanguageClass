/*
 * This file is generated by jOOQ.
 */
package model.database.tables.records;


import model.database.tables.AnswerAQuestionQuestion;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AnswerAQuestionQuestionRecord extends UpdatableRecordImpl<AnswerAQuestionQuestionRecord> implements Record3<Long, String, String> {

    private static final long serialVersionUID = 73126466;

    /**
     * Setter for <code>public.answer_a_question_question.key</code>.
     */
    public void setKey(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.answer_a_question_question.key</code>.
     */
    public Long getKey() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.answer_a_question_question.id</code>.
     */
    public void setId(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.answer_a_question_question.id</code>.
     */
    public String getId() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.answer_a_question_question.question_content</code>.
     */
    public void setQuestionContent(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.answer_a_question_question.question_content</code>.
     */
    public String getQuestionContent() {
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
        return AnswerAQuestionQuestion.ANSWER_A_QUESTION_QUESTION.KEY;
    }

    @Override
    public Field<String> field2() {
        return AnswerAQuestionQuestion.ANSWER_A_QUESTION_QUESTION.ID;
    }

    @Override
    public Field<String> field3() {
        return AnswerAQuestionQuestion.ANSWER_A_QUESTION_QUESTION.QUESTION_CONTENT;
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
        return getQuestionContent();
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
        return getQuestionContent();
    }

    @Override
    public AnswerAQuestionQuestionRecord value1(Long value) {
        setKey(value);
        return this;
    }

    @Override
    public AnswerAQuestionQuestionRecord value2(String value) {
        setId(value);
        return this;
    }

    @Override
    public AnswerAQuestionQuestionRecord value3(String value) {
        setQuestionContent(value);
        return this;
    }

    @Override
    public AnswerAQuestionQuestionRecord values(Long value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AnswerAQuestionQuestionRecord
     */
    public AnswerAQuestionQuestionRecord() {
        super(AnswerAQuestionQuestion.ANSWER_A_QUESTION_QUESTION);
    }

    /**
     * Create a detached, initialised AnswerAQuestionQuestionRecord
     */
    public AnswerAQuestionQuestionRecord(Long key, String id, String questionContent) {
        super(AnswerAQuestionQuestion.ANSWER_A_QUESTION_QUESTION);

        set(0, key);
        set(1, id);
        set(2, questionContent);
    }
}
