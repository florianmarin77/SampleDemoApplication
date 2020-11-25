package com.asi.sda.sample.constant;

public final class SampleMessages {

    // sample positive messages
    public static final String SAMPLE_SAVED = "SAMPLE SAVED. ID={}";
    public static final String SAMPLES_SAVED = "SAMPLES SAVED. SIZE={}";
    public static final String SAMPLE_FOUND = "SAMPLE FOUND. ID={}";
    public static final String SAMPLES_FOUND = "SAMPLES FOUND. SIZE={}/{}";
    public static final String SAMPLE_UPDATED = "SAMPLE UPDATED. ID={sample}";
    public static final String SAMPLE_DELETED = "SAMPLE DELETED. ID={}";
    public static final String SAMPLES_DELETED = "SAMPLES DELETED. SIZE={}";

    // sample negative messages
    public static final String SAMPLE_NOT_FOUND = "SAMPLE NOT FOUND! ID={}";
    public static final String SAMPLES_NOT_FOUND = "SAMPLES NOT FOUND! SIZE={}/{}";
    public static final String SAMPLE_NOT_UPDATED = "SAMPLE NOT UPDATED! ";
    public static final String SAMPLE_NOT_DELETED = "SAMPLE NOT DELETED! ";
    public static final String SAMPLES_NOT_DELETED = "SAMPLES NOT DELETED! ";

    // sample list messages
    public static final String SAMPLES_LIST = "SAMPLES LIST IS EMPTY! ";

    // sample validator messages
    public static final String SAMPLE_OUT_OF_RANGE = "SAMPLE OUT OF RANGE! {}>=<{}";

    // sample table messages
    public static final String SAMPLES_START = "Loading all samples from resource list... ";
    public static final String SAMPLES_FINAL = "Last insert id from samples table is {}";

    // sample exception messages
    public static final String SAMPLE_NOT_FOUND_ERROR = "ERROR: Sample not found!";
    public static final String SAMPLE_NOT_SAVED_ERROR = "ERROR: Sample not saved!";
    public static final String SAMPLES_NOT_SAVED_ERROR = "ERROR: Samples not saved!";

    // sample jpql => jpa
    public static final String FIND_ALL_JPQL = "SELECT s FROM Sample s";
    public static final String FIND_BY_TEXT_JPQL = "SELECT s FROM Sample s WHERE s.text = :text";

    // sample sql => jdbc
    public static final String CREATE_ALL_SQL = "INSERT INTO sample(text) VALUES (?)";
    public static final String CREATE_SQL = "INSERT INTO sample(text) VALUES (?)";
    public static final String FIND_ALL_SQL = "SELECT * FROM sample";
    public static final String FIND_BY_TEXT_SQL = "SELECT * FROM sample WHERE text = ?";
    public static final String FIND_SQL = "SELECT * FROM sample WHERE id = ?";
    public static final String UPDATE_SQL = "UPDATE sample SET text = ? WHERE id = ?";
    public static final String DELETE_SQL = "DELETE FROM sample WHERE id = ?";
    public static final String DELETE_ALL_SQL = "DELETE FROM sample";
    public static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS sample" +
            "(id INT NOT NULL AUTO_INCREMENT, " +
            "text VARCHAR(30) NULL, " +
            "PRIMARY KEY (id));";
    public static final String SELECT_FROM_TABLE_SQL = "SELECT * FROM sample";
    public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS sample";
    public static final String SAMPLES_TABLE_NAME = "sample";

    // sample hql => hibernate
    public static final String FIND_ALL_HQL = "FROM Sample";
    public static final String FIND_BY_TEXT_HQL = "FROM Sample s WHERE s.text = :text";

    private SampleMessages() {
        // private constructor by default
    }
}
