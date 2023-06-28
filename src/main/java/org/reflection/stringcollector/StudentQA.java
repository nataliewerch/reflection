package org.reflection.stringcollector;

public class StudentQA extends AbstractStudent {

    private final String name;
    private final String surname;

    @MyAnnotation(prefix = "QA")
    private String number;

    public StudentQA() {
        name = NameUtil.getRandomName();
        surname = NameUtil.getRandomName();
        number = NameUtil.getRandomNumber();
    }
}
