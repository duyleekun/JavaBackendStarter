package vn.tripath.backend_demo.jpa_converters;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter
public class ListStringJpaConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute != null)
            return String.join(";", attribute);
        return null;
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            List<String> r = new ArrayList();
            for (String s : dbData.split(";")) {
                if (s.length() > 0)
                    r.add(s);
            }
            return r;
        } else return null;
    }

}
