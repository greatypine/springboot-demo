package com.example.demo.common.utils.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NotDuplicateValidator<T extends ExcelRow> implements ConstraintValidator<NotDuplicate, T> {

    private Map<String, Set<String>> fieldMap = new HashMap<>();

    private Field[] fields;

    public <T extends ExcelRow> boolean validate(Field field, String name, String value, T r) {
        if (field.isAnnotationPresent(NotDuplicate.class)) {
            if (fieldMap.containsKey(name)) {
                if (fieldMap.get(name).contains(value)) {
                    NotDuplicate notDuplicate = field.getAnnotation(NotDuplicate.class);
                    r.setValidateCode(notDuplicate.code());
                    r.setValidateMessage(notDuplicate.message());
                    return false;
                } else {
                    fieldMap.get(name).add(value);
                }
            } else {
                Set<String> values = new HashSet<>();
                values.add(value);
                fieldMap.put(name, values);
            }
        }
        return true;
    }


    @Override
    public boolean isValid(T r, ConstraintValidatorContext constraintValidatorContext) {
        fields = r.getClass().getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            String value = null;
            try {
                field.setAccessible(true);
                value = (String) field.get(r);
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value != null && !"".equals(value) && !this.validate(field, name, value, r)) {
                return false;
            }
        }
        return true;
    }

}
