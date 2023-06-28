package org.reflection.stringcollector;

public class StudentBE extends AbstractStudent {

    @MyAnnotation(prefix = "BE")
    private final String name;
    private final String surname;

    public StudentBE() {
        name = NameUtil.getRandomName();
        surname = NameUtil.getRandomName();
    }
}