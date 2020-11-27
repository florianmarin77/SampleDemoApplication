package com.asi.sda.sample.demo.repository;

import com.asi.sda.sample.demo.database.Database;
import com.asi.sda.sample.demo.model.Single;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SingleDao implements Repository {
    private static final String SOURCE = "DAO => "; // logger

    private static final Database database = Database.getInstance(); // resource

    private static int lastInsertId; // table insert control

    @Override
    public List<Single> createAll(List<Single> singles) {
        List<Single> entities = database.getSingleList(); // import resource
        List<Single> results = database.generateIdAll(singles, lastInsertId); // get ids
        entities.addAll(results); // is done ok

        lastInsertId += results.size(); // adjust insert
        database.setSingleList(entities); // export resource
        boolean isDone = true; // database scenario

        if (isDone) {
            System.out.println(SOURCE + "CREATE=TRUE/SIZE=" + results.size() + "/all"); // logger
        } else {
            System.out.println(SOURCE + "CREATE=FALSE/SIZE=" + 0 + "/all"); // logger
        }

        return results;
    }

    @Override
    public Single create(Single single) {
        List<Single> entities = database.getSingleList(); // import resource
        Single result = database.generateIdOne(single, lastInsertId); // get id
        entities.add(result); // is done ok

        lastInsertId++; // adjust insert
        database.setSingleList(entities); // export resource
        boolean isDone = true; // database scenario

        if (isDone) {
            System.out.println(SOURCE + "CREATE=TRUE/ID=" + result.getId()); // logger
        } else {
            System.out.println(SOURCE + "CREATE=FALSE/ID=" + 0); // logger
        }

        return result;
    }

    @Override
    public List<Single> findAll() {
        List<Single> entities = database.getSingleList(); // import resource

        boolean isDone = true; // database scenario

        if (isDone) {
            System.out.println(SOURCE + "FIND=TRUE/SIZE=" + entities.size() + "/all"); // logger
        } else {
            System.out.println(SOURCE + "FIND=FALSE/SIZE=" + 0 + "/all"); // logger
        }

        return entities;
    }

    @Override
    public List<Single> findByText(String text) {
        List<Single> entities = database.getSingleList(); // import resource

        List<Single> results = new ArrayList<>(); // perform search
        for (Single item : entities) {
            if (item.getText().equals(text)) {
                results.add(item);
            }
        }

        boolean isDone = true; // database scenario

        if (isDone) {
            System.out.println(SOURCE + "FIND=TRUE/SIZE=" + results.size() + "/" + text); // logger
        } else {
            System.out.println(SOURCE + "FIND=FALSE/SIZE=" + 0 + "/" + text); // logger
        }

        return results;
    }

    @Override
    public Optional<Single> find(Integer id) {
        List<Single> entities = database.getSingleList(); // import resource

        Single entity = null; // perform search
        for (Single item : entities) {
            if (item.getId().equals(id)) {
                entity = item;
            }
        }

        boolean isDone = true; // database scenario

        if (isDone) {
            System.out.println(SOURCE + "FIND=TRUE/ID=" + id); // logger
        } else {
            System.out.println(SOURCE + "FIND=FALSE/ID=" + id); // logger
        }

        return Optional.ofNullable(entity); // return optional anyway
    }

    @Override
    public Optional<Single> update(Integer id, Single data) {
        List<Single> entities = database.getSingleList(); // import resource

        Integer index = null; // perform search
        for (Single item : entities) {
            if (item.getId().equals(id)) {
                index = entities.indexOf(item);
            }
        }
        Single entity = null; // update only if exists
        if (index != null) {
            entities.get(index).setText(data.getText());
            entity = entities.get(index);
        }

        database.setSingleList(entities); // export resource

        boolean isDone = true; // database scenario

        if (isDone) {
            System.out.println(SOURCE + "UPDATE=TRUE/ID=" + id); // logger
        } else {
            System.out.println(SOURCE + "UPDATE=FALSE/ID=" + id); // logger
        }

        return Optional.ofNullable(entity); // return optional anyway
    }

    @Override
    public Optional<Single> delete(Integer id) {
        List<Single> entities = database.getSingleList(); // import resource

        Integer index = null; // perform search
        for (Single item : entities) {
            if (item.getId().equals(id)) {
                index = entities.indexOf(item);
            }
        }
        Single entity = null; // delete only if exists
        if (index != null) {
            entity = entities.get(index);
            entities.remove((int) index);
        }

        database.setSingleList(entities); // export resource

        boolean isDone = true; // database scenario

        if (isDone) {
            System.out.println(SOURCE + "DELETE=TRUE/ID=" + id); // logger
        } else {
            System.out.println(SOURCE + "DELETE=FALSE/ID=" + id); // logger
        }

        return Optional.ofNullable(entity); // return optional anyway
    }
}
