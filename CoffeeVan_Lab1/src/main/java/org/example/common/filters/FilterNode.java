package org.example.common.filters;

import org.example.common.enums.Condition;

import java.util.List;

public class FilterNode {
    String attrEntity;
    String attrFilter;
    List<String> values;
    Condition condition;

    public FilterNode(String attrEntity, String attrFilter, List<String> values, Condition condition) {
        this.attrEntity = attrEntity;
        this.attrFilter = attrFilter;
        this.values = values;
        this.condition = condition;
    }

    public FilterNode() {
    }

    public String getAttrEntity() {
        return attrEntity;
    }

    public void setAttrEntity(String attrEntity) {
        this.attrEntity = attrEntity;
    }

    public String getAttrFilter() {
        return attrFilter;
    }

    public void setAttrFilter(String attrFilter) {
        this.attrFilter = attrFilter;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
