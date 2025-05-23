module artikli {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    exports app.gui;
    exports app.util;
    exports app.start;
    exports app.model;
    exports app.dao;
    exports app.test;
}