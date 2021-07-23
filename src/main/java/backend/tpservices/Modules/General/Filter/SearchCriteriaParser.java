package backend.tpservices.Modules.General.Filter;

import Config.Constants.*;
import java.util.*;
import java.util.regex.*;

public class SearchCriteriaParser {

    private static final String wordRegex = "[a-zA-Z]\\w*";
    private static final String valueRegex = "\\w+";
    private static final String operatorRegex = "(:|<|>|!|\\+|-|\\s)";
    private static final String timestampRegex = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0 -9]{2}:[0-9]{2}:[0-9]{2}-[0-9]{2}:[0-9]{2}";
    private static final String idRegex = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}";
    private static final String fullRegex = "(" + wordRegex + ")" + operatorRegex + "(" + timestampRegex + "|" + idRegex + "|" + valueRegex + ")?,";
    private static final Pattern searchPattern = Pattern.compile(fullRegex);

    public static List<SearchCriteria> parse(String searchString) {

        if (searchString == null) return new ArrayList<>();

        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
        Matcher matcher = searchPattern.matcher(searchString + ",");

        while (matcher.find()) {

            SearchCriteria searchCriteria = new SearchCriteria(matcher.group(1),
                                                               matcher.group(3),
                                                               SearchOperation.getOperation(matcher.group(2)));

            if (searchCriteria.getValue() != null)
                searchCriteriaList.add(searchCriteria);

        }
        return searchCriteriaList;
    }
}