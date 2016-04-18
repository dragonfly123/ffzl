package org.dragonfei.ffzl.annotation.parse;

import org.dragonfei.ffzl.exception.SystemExcption;
import org.dragonfei.ffzl.exception.Type;
import org.dragonfei.ffzl.utils.annotation.AnnotationUtils;
import org.dragonfei.ffzl.annotation.domain.Column;
import org.dragonfei.ffzl.annotation.domain.Foreign;
import org.dragonfei.ffzl.annotation.domain.Table;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.number.Numberutils;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * Created by longfei on 16-4-10.
 */
public class MetaDataBuilder {
    private static MetaDataBuilder INSTANCE ;
    private MetaDataBuilder(){}

    private FieldParser fieldParser = new FieldParser();
    public static MetaDataBuilder instance(){
        if(INSTANCE == null){
            synchronized (MetaDataBuilder.class){
                if(INSTANCE == null){
                    INSTANCE = new MetaDataBuilder();
                }
            }
        }
        return INSTANCE;
    }

    public <T> MetaData build(T object){
        Objects.requireNonNull(object);
        Class clazz = object.getClass();
        TableMeta tm = buildTable(clazz);

        List<FieldMeta> fieldMetaList = Lists.newArrayList();
        Field[] fields =  clazz.getDeclaredFields();
        for(Field field : fields){
            FieldMeta fieldMeta = buildField(field);
            fieldMetaList.add(fieldMeta);
        }
        MetaData metaData =  new MetaData(tm,fieldMetaList);
        return metaData;
    }

    private FieldMeta buildField(final Field field){
        final ForeignMeta foreignMeta = buildForeign(field);
        final FieldMeta fieldMeta =  new FieldMeta();
        if(foreignMeta.isForeign()){
            fieldMeta.setForeignMeta(foreignMeta);
        }
        fieldParser.parse(field, new FieldParseCommand() {
            @Override
            public Annotation annotation(AccessibleObject accessibleObject) {
                return accessibleObject.getAnnotation(Column.class);
            }

            @Override
            public void parseAnnotation(Annotation annotation) {
                if(Column.class.isAssignableFrom(annotation.getClass())){
                    Column column =  (Column) annotation;
                    fieldMeta.setColumnName(StringUtils.nvl(column.value(),field.getName()));
                    fieldMeta.setDesc(StringUtils.nvl(column.desc(),field.getName()));
                    fieldMeta.setRequired(column.required());
                    fieldMeta.setNotNull(column.notNull());
                    fieldMeta.setUnique(column.unique());
                    fieldMeta.setPk(column.pk());
                    fieldMeta.setBk(column.bk());
                } else {
                    throw new SystemExcption(Type.SYSTEM);
                }
            }

            @Override
            public void parseDefault(Field field) {
                String name = field.getName().toUpperCase();
                fieldMeta.setColumnName(name);
                fieldMeta.setDesc(name);
            }
        });
        return fieldMeta;
    }

    private ForeignMeta buildForeign(final Field field){

        final ForeignMeta foreignMeta = new ForeignMeta();

        fieldParser.parse(field, new FieldParseCommand() {
            @Override
            public Annotation annotation(AccessibleObject accessibleObject) {
                return accessibleObject.getAnnotation(Foreign.class);
            }

            @Override
            public void parseAnnotation(Annotation annotation) {
                if(Foreign.class.isAssignableFrom(annotation.getClass())){
                    Foreign foreign =  (Foreign) annotation;
                    Class clazz = foreign.clazz();
                    int defaults = foreign.defaults();
                    String field = foreign.field();
                    foreignMeta.setForeign(true);
                    foreignMeta.setClazz(clazz);
                    foreignMeta.setDefaults(Numberutils.newBigInter(defaults));
                    foreignMeta.setField(field);
                } else {
                   throw new SystemExcption(Type.SYSTEM);
                }
            }

            @Override
            public void parseDefault(Field field) {

            }
        });
        return foreignMeta;
    }
    private TableMeta buildTable(Class clazz){
        Table table = AnnotationUtils.findAnnotation(clazz, Table.class);
        TableMeta tm;
        if(Objects.nonNull(table)){
            tm = new TableMeta(table.value(),table.desc());
        } else {
            tm = new TableMeta(clazz.getSimpleName().toUpperCase(),clazz.getSimpleName().toUpperCase());
        }
        return tm;
    }
}

/**
 * 解析属性
 */
interface FieldParseCommand<T extends Annotation>{
    T annotation(final AccessibleObject accessibleObject);

    void parseAnnotation(final T annotation);
    void parseDefault(final Field field);
}

class FieldParser<T extends Annotation>{

    public void parse(final Field field,FieldParseCommand command){
        T annotation = (T)command.annotation(field);
        if(annotation == null){
            Class clazz = field.getDeclaringClass();
            String name = field.getName();
            try {
                PropertyDescriptor ps = new PropertyDescriptor(name,clazz);
                Method readMethod = ps.getReadMethod();
                annotation = (T)command.annotation(readMethod);
                if(annotation == null){
                    Method writeMethod = ps.getReadMethod();
                    annotation = (T)command.annotation(writeMethod);
                    if(annotation == null){
                        command.parseDefault(field);
                        return;
                    }
                }

            } catch (IntrospectionException e){
                throw new SystemExcption(e, Type.SYSTEM);
            }
        }
        command.parseAnnotation(annotation);
    }
}

