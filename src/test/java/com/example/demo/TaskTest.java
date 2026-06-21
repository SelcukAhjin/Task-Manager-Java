package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    void setPrioLow_returntrue(){
        Task t = new Task("de","",false);
        t.setPrio(Task.Priority.LOW);
        Assertions.assertEquals(Task.Priority.LOW,t.getPrio());
    }
    @Test
    void setPrioHigh_returntrue(){
        Task t = new Task("de","",false);
        t.setPrio(Task.Priority.HIGH);
        Assertions.assertEquals(Task.Priority.HIGH,t.getPrio());
    }
    @Test
    void getPrioMedium_returntrue(){
        Task t = new Task("de","",false);
        t.setPrio(Task.Priority.MEDIUM);
        Assertions.assertEquals(Task.Priority.MEDIUM,t.getPrio());
    }
    @Test
    void getKategorieSchool_returntrue(){
        Task t = new Task("de","",false);
        t.setKat(Task.Kategorie.SCHOOL);
        Assertions.assertEquals(Task.Kategorie.SCHOOL,t.getKat());
    }
    @Test
    void getKategorieWork_returntrue() {
        Task t = new Task("de", "", false);
        t.setKat(Task.Kategorie.WORK);
        Assertions.assertEquals(Task.Kategorie.WORK, t.getKat());
    }
    @Test
    void getKategoriePersonal_returntrue() {
        Task t = new Task("de", "", false);
        t.setKat(Task.Kategorie.PERSONAL);
        Assertions.assertEquals(Task.Kategorie.PERSONAL,t.getKat());
    }
}
