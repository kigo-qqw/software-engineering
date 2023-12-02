package ru.nstu.se.lab2;

import org.junit.jupiter.api.Test;


class A {
}

class B {
}


class ClassWithCONSTAnnotationsOnField {
    @CONST(title = "test-title-for-A", group = "test-group-for-A", clazz = A.class)
    private static final int constantForClassA = 1;
    @CONST(title = "test-title-for-B", group = "test-group-for-B", clazz = B.class)
    private static final int constantForClassB = 2;
}

public class CONSTTest {
    @Test
    void givenClass_whenGetFieldDescriptors_thenReturnArrayListOfFieldDescriptors() {
//        Field[] fields = ClassWithCONSTAnnotationsOnField.class.getFields();
//        for (Field field : fields) {
//            System.out.println(Arrays.toString(field.getAnnotations()));
//            System.out.println(Arrays.toString(field.getDeclaredAnnotations()));
////            Annotation[] fieldAnnotations = field.getAnnotations();
//        }

//        System.out.println(Arrays.toString(ClassWithCONSTAnnotationsOnField.class.getDeclaredFields()));
        System.out.println(ClassWithCONSTAnnotatedFieldsToArrayListOfCONSTFieldDescriptors.convert(new ClassWithCONSTAnnotationsOnField()));
    }
}
