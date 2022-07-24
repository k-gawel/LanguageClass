/*
 * This file is generated by jOOQ.
 */
package model.database.tables;


import java.util.Arrays;
import java.util.List;

import model.database.Keys;
import model.database.Public;
import model.database.tables.records.ExerciseEvaluationRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ExerciseEvaluation extends TableImpl<ExerciseEvaluationRecord> {

    private static final long serialVersionUID = 1831191339;

    /**
     * The reference instance of <code>public.exercise_evaluation</code>
     */
    public static final ExerciseEvaluation EXERCISE_EVALUATION = new ExerciseEvaluation();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ExerciseEvaluationRecord> getRecordType() {
        return ExerciseEvaluationRecord.class;
    }

    /**
     * The column <code>public.exercise_evaluation.key</code>.
     */
    public final TableField<ExerciseEvaluationRecord, Long> KEY = createField(DSL.name("key"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.exercise_evaluation.id</code>.
     */
    public final TableField<ExerciseEvaluationRecord, String> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.exercise_evaluation.comment</code>.
     */
    public final TableField<ExerciseEvaluationRecord, String> COMMENT = createField(DSL.name("comment"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.exercise_evaluation.rating</code>.
     */
    public final TableField<ExerciseEvaluationRecord, Integer> RATING = createField(DSL.name("rating"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.exercise_evaluation.answer</code>.
     */
    public final TableField<ExerciseEvaluationRecord, Long> ANSWER = createField(DSL.name("answer"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.exercise_evaluation.teacher</code>.
     */
    public final TableField<ExerciseEvaluationRecord, Long> TEACHER = createField(DSL.name("teacher"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>public.exercise_evaluation</code> table reference
     */
    public ExerciseEvaluation() {
        this(DSL.name("exercise_evaluation"), null);
    }

    /**
     * Create an aliased <code>public.exercise_evaluation</code> table reference
     */
    public ExerciseEvaluation(String alias) {
        this(DSL.name(alias), EXERCISE_EVALUATION);
    }

    /**
     * Create an aliased <code>public.exercise_evaluation</code> table reference
     */
    public ExerciseEvaluation(Name alias) {
        this(alias, EXERCISE_EVALUATION);
    }

    private ExerciseEvaluation(Name alias, Table<ExerciseEvaluationRecord> aliased) {
        this(alias, aliased, null);
    }

    private ExerciseEvaluation(Name alias, Table<ExerciseEvaluationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> ExerciseEvaluation(Table<O> child, ForeignKey<O, ExerciseEvaluationRecord> key) {
        super(child, key, EXERCISE_EVALUATION);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<ExerciseEvaluationRecord> getPrimaryKey() {
        return Keys.EXERCISE_EVALUATION_PKEY;
    }

    @Override
    public List<UniqueKey<ExerciseEvaluationRecord>> getKeys() {
        return Arrays.<UniqueKey<ExerciseEvaluationRecord>>asList(Keys.EXERCISE_EVALUATION_PKEY, Keys.UK_B2CJU4AWEUK5D3UY3JHGADDR2);
    }

    @Override
    public List<ForeignKey<ExerciseEvaluationRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ExerciseEvaluationRecord, ?>>asList(Keys.EXERCISE_EVALUATION__FKDART7T8CI3T75UB2WF4X2T8XH, Keys.EXERCISE_EVALUATION__FKJUP4J44JWP11BMN69LMOP7BHF);
    }

    public ExerciseAnswer exerciseAnswer() {
        return new ExerciseAnswer(this, Keys.EXERCISE_EVALUATION__FKDART7T8CI3T75UB2WF4X2T8XH);
    }

    public AppUser appUser() {
        return new AppUser(this, Keys.EXERCISE_EVALUATION__FKJUP4J44JWP11BMN69LMOP7BHF);
    }

    @Override
    public ExerciseEvaluation as(String alias) {
        return new ExerciseEvaluation(DSL.name(alias), this);
    }

    @Override
    public ExerciseEvaluation as(Name alias) {
        return new ExerciseEvaluation(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ExerciseEvaluation rename(String name) {
        return new ExerciseEvaluation(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ExerciseEvaluation rename(Name name) {
        return new ExerciseEvaluation(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, String, String, Integer, Long, Long> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}