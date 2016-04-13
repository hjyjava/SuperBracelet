package com.example;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.DaoGenerator;

public class MyDaoGenerator {
    public static void main(String[] args) throws Exception{
        Schema schema = new Schema(1,"com.huang.greendao");
        schema.setDefaultJavaPackageDao("com.huang.dao");
        schema.enableActiveEntitiesByDefault();
        schema.enableKeepSectionsByDefault();
        addNote(schema);
        new DaoGenerator().generateAll(schema,"/Users/D04/StudioProjects/SuperBracelet/app/src/main/java-gen");
    }

    private static void addNote(Schema schema){
        Entity note = schema.addEntity("Note");
        note.setTableName("NOTE");
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("creationDate");
    }
}
