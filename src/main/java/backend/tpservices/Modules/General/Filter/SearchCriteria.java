package backend.tpservices.Modules.General.Filter;

import Config.Constants.*;

public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;

    public SearchCriteria(){}

    public SearchCriteria(String key, Object value, SearchOperation operation) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchOperation getOperation() {
        return operation;
    }
    public void setOperation(SearchOperation operation) {
        this.operation = operation;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "key='" + key + '\'' +
                ", operation=" + operation +
                ", value=" + value +
                '}';
    }
}