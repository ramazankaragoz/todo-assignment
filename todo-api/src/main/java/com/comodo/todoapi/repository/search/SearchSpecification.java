package com.comodo.todoapi.repository.search;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.criteria.internal.path.RootImpl;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class SearchSpecification<T> implements Specification<T> {

    private final SearchCriteria criteria;

    public SearchSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @SneakyThrows
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder builder) {

        if (checkSearchCriteria()) {
            return null;
        }

        Type genericSuperclass = ((RootImpl) root).getEntityType().getJavaType().getGenericSuperclass();

        switch (criteria.getOperation()) {
            case EQUALS:
                if (root.get(criteria.getField()).getJavaType().equals(String.class)) {
                    return builder.equal(builder.lower(root.get(criteria.getField())), criteria.getValue().toString().toLowerCase(Locale.ROOT));
                }

                if (root.get(this.criteria.getField()).getJavaType().equals(Serializable.class)
                        && criteria.getField().equals("id") && Objects.nonNull(genericSuperclass)) {
                    ParameterizedType test = (ParameterizedType) genericSuperclass;
                    if (test.getActualTypeArguments().length > 0) {
                        return getGenericTypeIdPredicate(root, builder, test, true);
                    }
                }

                return builder.equal(root.get(criteria.getField()),
                        castToRequiredType(root.get(criteria.getField()).getJavaType(), criteria.getValue().toString()));
            case NOT_EQ:
                if (root.get(criteria.getField()).getJavaType().equals(String.class)) {
                    return builder.notEqual(builder.lower(root.get(criteria.getField())), criteria.getValue().toString().toLowerCase(Locale.ROOT));
                }

                if (root.get(this.criteria.getField()).getJavaType().equals(Serializable.class)
                        && criteria.getField().equals("id") && Objects.nonNull(genericSuperclass)) {
                    ParameterizedType test = (ParameterizedType) genericSuperclass;
                    if (test.getActualTypeArguments().length > 0) {
                        return getGenericTypeIdPredicate(root, builder, test, false);
                    }
                }

                return builder.notEqual(root.get(criteria.getField()),
                        castToRequiredType(root.get(criteria.getField()).getJavaType(), criteria.getValue().toString()));
            case GREATER_THAN:
                return builder.greaterThanOrEqualTo(
                        root.get(criteria.getField()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThanOrEqualTo(
                        root.get(criteria.getField()), criteria.getValue().toString());
            case LIKE:
                String specialCapitals = "ÜİÖÇĞŞ";
                String lowerCaseCriteria = "";
                if(StringUtils.containsAny(specialCapitals, criteria.getValue().toString())){
                    lowerCaseCriteria = criteria.getValue().toString().toLowerCase(Locale.forLanguageTag("tr-TR"));
                } else {
                    lowerCaseCriteria = criteria.getValue().toString().toLowerCase(Locale.ROOT);
                }
                return builder.like(
                        builder.lower(root.get(criteria.getField())), "%" + lowerCaseCriteria + "%");
            case IN:
                return builder.in(root.get(criteria.getField()))
                        .value(castToRequiredType(root.get(criteria.getField()).getJavaType(),
                                (List<Object>) castToRequiredType(root.get(criteria.getField()).getJavaType(),
                                        criteria.getValues())));
            default:

        }
        return null;
    }

    /**
     * In order to filter id field in generic entities
     *
     * @param root
     * @param builder
     * @param test
     * @return
     * @throws ClassNotFoundException
     */
    private Predicate getGenericTypeIdPredicate(Root<T> root, CriteriaBuilder builder,
                                                ParameterizedType test, boolean isEqual) throws ClassNotFoundException {
        Type actualTypeArgument = test.getActualTypeArguments()[0];
        var idTypeName = actualTypeArgument.getTypeName();
        Class idType = Class.forName(idTypeName);
        if (isEqual) {
            return builder.equal(root.get(criteria.getField()),
                    castToRequiredType(idType, criteria.getValue().toString()));
        } else {
            return builder.notEqual(root.get(criteria.getField()),
                    castToRequiredType(idType, criteria.getValue().toString()));
        }
    }

    private boolean checkSearchCriteria() {
        return criteria == null || criteria.getValue() == null && !criteria.getOperation().equals(SearchOperator.IN) ||
                criteria.getValues() == null && criteria.getOperation().equals(SearchOperator.IN);
    }

    private Object castToRequiredType(Class fieldType, String value) {
        if (fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if (fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if (Enum.class.isAssignableFrom(fieldType)) {
            return Enum.valueOf(fieldType, value);
        } else if (fieldType.isAssignableFrom(Long.class)) {
            return Long.valueOf(value);
        } else if (fieldType.isAssignableFrom(Boolean.class)) {
            return Boolean.valueOf(value);
        }
        return value;
    }

    private Object castToRequiredType(Class fieldType, List<Object> value) {
        List<Object> lists = new ArrayList<>();
        for (Object o : value) {
            lists.add(castToRequiredType(fieldType, o.toString()));
        }
        return lists;
    }
}
