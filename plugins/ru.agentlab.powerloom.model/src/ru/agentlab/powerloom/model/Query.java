/**
 *
 */
package ru.agentlab.powerloom.model;

/**
 * @author Kiryakov_E
 *
 */
public class Query
    extends AbstractObject {

    private String query;

    /**
     * Default constructor.
     */
    public Query() {
        // Does nothing
    }

    /**
     *
     * Constructor
     *
     * @param module - module name. Can't be <code>null</code>.
     * @param query - query. Can't be <code>null</code>.
     */
    public Query(String module, String query) {
        this.module = module;
        this.query = query;
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    @Override
    public String toString() {
        String result = "Query(";
        if (module != null)
        {
            result += module + ", ";
        }
        if (query != null)
        {
            result += query;
        }
        result += ")";

        return result;
    }
}
