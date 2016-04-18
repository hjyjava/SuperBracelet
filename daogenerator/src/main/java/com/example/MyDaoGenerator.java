package com.example;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.DaoGenerator;

public class MyDaoGenerator {
    public static void main(String[] args) throws Exception{
        Schema schema = new Schema(1,"com.huang.bean");
        schema.setDefaultJavaPackageDao("com.huang.dao");
        schema.enableActiveEntitiesByDefault();
        schema.enableKeepSectionsByDefault();
        addNote(schema);
        adduser(schema);
        new DaoGenerator().generateAll(schema,"../SuperBracelet/app/src/main/java-gen");
    }

    private static void addNote(Schema schema){

        Entity note = schema.addEntity("Note");
        note.setTableName("NOTE");
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("creationDate");

    }

    private static void adduser(Schema schema){

        Entity note = schema.addEntity("User");
        note.setTableName("User");
        note.addIdProperty();
        note.addStringProperty("user_id");
        note.addStringProperty("username");
        note.addStringProperty("usertoken");
        note.addStringProperty("nickname");
    }
}
