package com.asi.sda.sample.repository;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.config.JdbcConfig;
import com.asi.sda.sample.database.SampleSimDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.asi.sda.sample.constant.CommonMessages.*;
import static com.asi.sda.sample.constant.SampleMessages.*;


public class SampleJdbcDao implements SampleRepository {
    private static final Logger LOGGER = LogManager.getLogger(SampleJdbcDao.class);

    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    private static final JdbcConfig jdbcConfig = new JdbcConfig();
    private static final String URL = jdbcConfig.getUrl();
    private static final String USERNAME = jdbcConfig.getUsername();
    private static final String PASSWORD = jdbcConfig.getPassword();

    public static int lastInsertId;

    @Override
    public List<Sample> createAll(List<Sample> samples) {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> results = new ArrayList<>();
        List<Sample> clones = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection
                     .prepareStatement(CREATE_ALL_SQL, Statement.RETURN_GENERATED_KEYS)) {

            LOGGER.info(SAMPLES_START + PLEASE_WAIT);
            Integer foundId = null;
            for (Sample item : samples) {
                preparedStatement.setString(1, item.getText());
                int rowsAffected = preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                clones.add(item);

                if ((resultSet.next()) && (rowsAffected == 1)) {
                    foundId = resultSet.getInt(1);
                    Sample result = new Sample();
                    result.setText(item.getText());
                    result.setId(foundId);
                    results.add(result);

                    if (((foundId - 1) % 100 == 0) || (foundId == samples.size())) {
                        displayProgressBar(foundId, samples.size());
                    }
                }
                resultSet.close();
            }
            LOGGER.info(SAMPLES_FINAL, foundId);
            clones = SampleSimDatabase.generateIdAll(clones, lastInsertId);

            lastInsertId += samples.size();
            LOGGER.info(SAMPLES_SAVED, samples.size());
            if (!foundId.equals(lastInsertId)) {
                LOGGER.warn(LAST_INSERT, lastInsertId, foundId);
            }

            duplicates.addAll(clones);
            database.setSampleList(duplicates); // export
        } catch (SQLException exception) {
            LOGGER.error(CREATE_ERROR);
        }

        database.displayTable(clones);
        return results;
    }

    @Override
    public Sample create(Sample sample) {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> clones = new ArrayList<>();
        Sample result = new Sample();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection
                     .prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, sample.getText());
            int rowsAffected = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            clones.add(SampleSimDatabase.generateIdOne(sample, lastInsertId));

            Integer foundId = null;
            if ((resultSet.next()) && (rowsAffected == 1)) {
                foundId = resultSet.getInt(1);
                LOGGER.info(SAMPLE_SAVED, foundId);
                result.setText(sample.getText());
                result.setId(foundId);
                lastInsertId++;
            }
            resultSet.close();

            if (!foundId.equals(lastInsertId)) {
                LOGGER.warn(LAST_INSERT, lastInsertId, foundId);
            }

            duplicates.addAll(clones);
            database.setSampleList(duplicates); // export
        } catch (SQLException exception) {
            LOGGER.error(CREATE_ERROR);
        }

        database.displayTable(clones);
        return result;
    }

    @Override
    public List<Sample> findAll() {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> results = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer foundId = resultSet.getInt("id");
                String foundText = resultSet.getString("text");
                results.add(new Sample(foundId, foundText));
            }
            resultSet.close();

            if (results.isEmpty()) {
                LOGGER.warn(SAMPLES_NOT_FOUND, 0, "all");
            } else {
                LOGGER.info(SAMPLES_FOUND, results.size(), "all");
            }
        } catch (SQLException exception) {
            LOGGER.error(SEARCH_ERROR);
        }

        database.displayTable(duplicates);
        return results;
    }

    @Override
    public List<Sample> findByText(String text) {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> results = new ArrayList<>();
        List<Sample> clones = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_TEXT_SQL)) {

            preparedStatement.setString(1, text);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Sample> entities = new ArrayList<>();
            while (resultSet.next()) {
                Integer foundId = resultSet.getInt("id");
                String foundText = resultSet.getString("text");
                entities.add(new Sample(foundId, foundText));
            }
            resultSet.close();

            // sql search is case insensitive by default
            for (Sample item : entities) {
                if (item.getText().equals(text)) {
                    results.add(item);
                }
            }

            if (results.isEmpty()) {
                LOGGER.warn(SAMPLES_NOT_FOUND, 0, text);
            } else {
                LOGGER.info(SAMPLES_FOUND, results.size(), text);
            }

            for (Sample item : duplicates) {
                if (item.getText().equals(text)) {
                    clones.add(item);
                }
            }
        } catch (SQLException exception) {
            LOGGER.error(SEARCH_ERROR);
        }

        database.displayTable(clones);
        return results;
    }

    @Override
    public Optional<Sample> find(Integer id) {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> clones = new ArrayList<>();
        Sample result = null;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_SQL)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Integer foundId = resultSet.getInt("id");
                String foundText = resultSet.getString("text");
                result = new Sample(foundId, foundText);
            }
            resultSet.close();

            if (result == null) {
                LOGGER.warn(SAMPLE_NOT_FOUND, id);
            } else {
                LOGGER.info(SAMPLE_FOUND, result.getId());
            }

            Integer index = null;
            for (int k = 0; k < duplicates.size(); k++) {
                if (duplicates.get(k).getId().equals(id)) {
                    index = k;
                }
            }
            if (index != null) {
                clones.add(duplicates.get(index));
            }
        } catch (SQLException exception) {
            LOGGER.error(SEARCH_ERROR);
        }

        database.displayTable(clones);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Sample> update(Integer id, Sample data) {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> clones = new ArrayList<>();
        Sample result = null;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {

            preparedStatement.setString(1, data.getText());
            preparedStatement.setInt(2, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1) {
                result = new Sample(id, data.getText());
                LOGGER.info(SAMPLE_UPDATED, id);
            } else {
                LOGGER.warn(SAMPLE_NOT_UPDATED + SAMPLE_NOT_FOUND, id);
            }

            Integer index = null;
            for (int k = 0; k < duplicates.size(); k++) {
                if (duplicates.get(k).getId().equals(id)) {
                    index = k;
                }
            }
            if (index != null) {
                duplicates.get(index).setText(data.getText());
                database.setSampleList(duplicates); // export
                clones.add(duplicates.get(index));
            }
        } catch (SQLException exception) {
            LOGGER.error(UPDATE_ERROR);
        }

        database.displayTable(clones);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Sample> delete(Integer id) {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> clones = new ArrayList<>();
        Sample result = null;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement1 = connection.prepareStatement(FIND_SQL);
             PreparedStatement preparedStatement2 = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement1.setInt(1, id);
            ResultSet resultSet = preparedStatement1.executeQuery();

            if (resultSet.next()) {
                Integer deletedId = resultSet.getInt("id");
                String deletedText = resultSet.getString("text");
                result = new Sample(deletedId, deletedText);
            }
            resultSet.close();

            preparedStatement2.setInt(1, id);
            int rowsAffected = preparedStatement2.executeUpdate();

            if (rowsAffected == 1) {
                LOGGER.info(SAMPLE_DELETED, id);
            } else {
                LOGGER.warn(SAMPLE_NOT_DELETED + SAMPLE_NOT_FOUND, id);
            }

            Integer index = null;
            for (int k = 0; k < duplicates.size(); k++) {
                if (duplicates.get(k).getId().equals(id)) {
                    index = k;
                }
            }
            if (index != null) {
                clones.add(duplicates.get(index));
                duplicates.remove((int) index);
                database.setSampleList(duplicates); // export
            }
        } catch (SQLException exception) {
            LOGGER.error(DELETE_ERROR);
        }

        database.displayTable(clones);
        return Optional.ofNullable(result);
    }

    public boolean createTable() {
        String samplesTableHeader = "Column names from samples table are ";
        StringBuilder tableColumnNames = new StringBuilder(samplesTableHeader);

        boolean isSamplesTableCreated = false;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement1 = connection.prepareStatement(CREATE_TABLE_SQL);
             PreparedStatement preparedStatement2 = connection.prepareStatement(SELECT_FROM_TABLE_SQL)) {

            LOGGER.warn("Creating samples table named anysample...");

            preparedStatement1.executeUpdate();
            ResultSet resultSet = preparedStatement2.executeQuery();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();

            for (int i = 1; i < numberOfColumns + 1; i++) {
                String columnName = resultSetMetaData.getColumnName(i);
                tableColumnNames.append(columnName).append(" / ");
            }
            LOGGER.info(String.valueOf(tableColumnNames));
            isSamplesTableCreated = true;
            resultSet.close();
        } catch (SQLException exception) {
            LOGGER.error("Samples table is not created!");
        }

        return isSamplesTableCreated;
    }

    public boolean deleteTable() {
        boolean isSamplesTableDeleted = true;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DROP_TABLE_SQL)) {

            LOGGER.warn("Deleting samples table named anysample...");

            preparedStatement.executeUpdate();

            ResultSet resultSet = connection.getMetaData()
                    .getTables(null, null, SAMPLES_TABLE_NAME, null);

            if (resultSet.next()) {
                String foundTableName = resultSet.getString("TABLE_NAME");
                if (foundTableName != null && foundTableName.equals(SAMPLES_TABLE_NAME)) {
                    isSamplesTableDeleted = false;
                }
            }

            if (isSamplesTableDeleted) {
                LOGGER.info("Samples table successfully deleted!");
            } else {
                LOGGER.warn("Samples table is not deleted!");
            }
            resultSet.close();
        } catch (SQLException exception) {
            LOGGER.error("Deleting samples table unfortunately failed!");
        }

        return isSamplesTableDeleted;
    }

    private void displayProgressBar(int lastLoadedId, int resourceListSize) {
        int allUnits = 10; // 10 units for 100%
        int remainingUnits = ((100 * lastLoadedId) / (resourceListSize * allUnits));
        char emptyUnit = '-';
        String fullUnit = "*";
        String bar = new String(new char[allUnits]).replace('\0', emptyUnit);
        StringBuilder fullBar = new StringBuilder();
        fullBar.append("[");
        for (int i = 0; i < remainingUnits; i++) {
            fullBar.append(fullUnit);
        }
        fullBar.append("]");
        String emptyBar = bar.substring(remainingUnits);
        System.out.print("\r" + fullBar + emptyBar + " " + remainingUnits * 10 + "%");
        if (lastLoadedId == resourceListSize) {
            System.out.print(" Done!\n");
        }
    }
}
