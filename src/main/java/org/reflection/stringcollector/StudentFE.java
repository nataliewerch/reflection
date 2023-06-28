package org.reflection.stringcollector;

public class StudentFE extends AbstractStudent {

    private final String name;

    @MyAnnotation(prefix = "FE")
    private final String surname;

    public StudentFE() {
        name = NameUtil.getRandomName();
        surname = NameUtil.getRandomName();
    }
}