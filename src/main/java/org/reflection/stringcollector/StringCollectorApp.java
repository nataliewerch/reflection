package org.reflection.stringcollector;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringCollectorApp {

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("org.reflection.stringcollector.StudentBE",
                "org.reflection.stringcollector.StudentFE",
                "org.reflection.stringcollector.StudentQA");
        int countStudents = 20;
        List<AbstractStudent> studentList = createList(strings, countStudents);
        Result result = new Result();
        String resultString = collectAnnotatedField(studentList);
        invokeAnnotatedMethods(result, resultString);
    }

    private static List<AbstractStudent> createList(List<String> classNames, int count) {
        List<AbstractStudent> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int index = ThreadLocalRandom.current().nextInt(0, 3);
            String className = classNames.get(index);

            try {
                AbstractStudent student = (AbstractStudent) Class.forName(className).getDeclaredConstructor().newInstance();
                list.add(student);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    private static String collectAnnotatedField(List<AbstractStudent> studentList) {
        StringBuilder resultBuilder = new StringBuilder();
        for (AbstractStudent student : studentList) {
            Class clazz = student.getClass();

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(MyAnnotation.class)) {
                    field.setAccessible(true);
                    MyAnnotation fieldAnnotation = field.getAnnotation(MyAnnotation.class);
                    try {
                        resultBuilder.append(fieldAnnotation.prefix()).append(":").append(field.get(student)).append("; ");
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return resultBuilder.toString();
    }

    private static void invokeAnnotatedMethods(Result result, String resultString) {
        Method[] methods = result.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                method.setAccessible(true);
                try {
                    method.invoke(result, resultString);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
