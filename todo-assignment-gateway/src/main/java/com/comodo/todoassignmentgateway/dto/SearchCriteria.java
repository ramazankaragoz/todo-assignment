package com.comodo.todoassignmentgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria implements Serializable {
    private static final long serialVersionUID = 6826408328336771632L;

    private String field;
    private SearchOperator operation;
    private Object value;
    private List<Object> values;


}
