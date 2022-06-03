package com.comodo.todoapi.repository.search;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

public class SpecificationBuilder {

    private SpecificationBuilder() {
    }

    public static Specification build(List<SearchCriteria> params) {
        if (params.size() == 0) {
            return null;
        }

        List<SearchSpecification> specs = params.stream()
                .map(SearchSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (var i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));

        }
        return result;
    }
}
