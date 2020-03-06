package vn.tripath.backend_demo.jpa_converters;


import vn.tripath.backend_demo.enums.UserPermission;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class ListUserPermissionJpaConverter implements AttributeConverter<List<UserPermission>, String> {

    @Override
    public String convertToDatabaseColumn(List<UserPermission> attribute) {
        return attribute.stream().map(UserPermission::name).collect(Collectors.joining(","));
    }

    @Override
    public List<UserPermission> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(",")).map(UserPermission::valueOf).collect(Collectors.toList());
    }

}
