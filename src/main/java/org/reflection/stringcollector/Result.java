package org.reflection.stringcollector;

public class Result extends AbstractStudent {

    public Result() {

    }

    @MyAnnotation(prefix = "Result")
    private void printStudentsInfo(String info) {
        System.out.println(info);
    }
}
