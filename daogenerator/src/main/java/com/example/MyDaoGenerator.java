package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.huang.bean");
        schema.setDefaultJavaPackageDao("com.huang.dao");
        schema.enableActiveEntitiesByDefault();
        schema.enableKeepSectionsByDefault();
        addSutdent(schema);
        addParentSubject(schema);
        addJindu(schema);
        new DaoGenerator().generateAll(schema, "../SuperBracelet/app/src/main/java-gen");
    }

    private static void addSutdent(Schema schema) {
        Entity student = schema.addEntity("Student");
        student.setTableName("Student");
        student.addStringProperty("Id").primaryKey().notNull();
        student.addStringProperty("username");
        student.addStringProperty("xingming");
        student.addStringProperty("xuehao");
        student.addStringProperty("nianji");
        student.addStringProperty("banji");
        student.addStringProperty("xingbie");
    }

    private static void addParentSubject(Schema schema) {
        Entity childSubject = schema.addEntity("ChildSubject");
        childSubject.setTableName("ChildSubject");
        childSubject.addStringProperty("Id").notNull().primaryKey();
        childSubject.addStringProperty("Anum");
        childSubject.addStringProperty("kaoshiname");
        childSubject.addStringProperty("kaoshitime");
        childSubject.addStringProperty("logo");
        childSubject.addStringProperty("moniname");
        childSubject.addStringProperty("monitime");
        childSubject.addStringProperty("name");
        childSubject.addStringProperty("rounds");
        childSubject.addStringProperty("rules");
        childSubject.addStringProperty("startdate");

        Entity parentSubject = schema.addEntity("ParentSubject");
        parentSubject.setTableName("ParentSubject");
        parentSubject.addStringProperty("Id").notNull().primaryKey();
        parentSubject.addStringProperty("logo");
        parentSubject.addStringProperty("name");

        Property parentSubjectId = childSubject.addStringProperty("parentSubjectId").getProperty();
        parentSubject.addToMany(childSubject, parentSubjectId).setName("kulist");
    }

    private static void addJindu(Schema schema){
        Entity childSubject = schema.addEntity("Jindu");
        childSubject.setTableName("Jindu");
        childSubject.addIdProperty();
        childSubject.addStringProperty("studentId");
        childSubject.addStringProperty("childSubjectId");
        childSubject.addStringProperty("addtime");
        childSubject.addStringProperty("grade");
        childSubject.addStringProperty("num");
        childSubject.addStringProperty("rank");
    }
}
