package backend.tpservices.Modules.Company;

import backend.tpservices.Modules.General.Filter.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CompanySpecification implements Specification<Company> {

    private List<SearchCriteria> criteriaList = new ArrayList<>();

    public CompanySpecification(){}
    public CompanySpecification(List<SearchCriteria> criteria) {
        this.criteriaList = criteria;
    }

    public void add(SearchCriteria criteria) {
        criteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        //add criteria to predicates
        for (SearchCriteria criteria : criteriaList) {

            switch (criteria.getOperation()) {
                case GREATER_THAN -> predicates.add(builder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
                case LESS_THAN -> predicates.add(builder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
                case NOT_EQUAL -> predicates.add(builder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue()));
                case EQUAL -> predicates.add(builder.equal(
                        root.get(criteria.getKey()), criteria.getValue()));
            }
            /* else if (op.equals(SearchOperation.GREATER_THAN_EQUAL)) {
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (op.equals(SearchOperation.LESS_THAN_EQUAL)) {
                predicates.add(builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (op.equals(SearchOperation.MATCH)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (op.equals(SearchOperation.MATCH_END)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"));
            } else if (op.equals(SearchOperation.MATCH_START)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase()));
            } else if (op.equals(SearchOperation.IN)) {
                predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValue()));
            } else if (op.equals(SearchOperation.NOT_IN)) {
                predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.getValue()));
            }*/
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
    // ------------------------------------------------

    public List<SearchCriteria> getCriteria() {
        return criteriaList;
    }
    public void setCriteria(List<SearchCriteria> criteria) {
        this.criteriaList = criteria;
    }
}