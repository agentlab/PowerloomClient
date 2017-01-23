/**
 *
 */
package ru.agentlab.powerloom.model;

/**
 *
 * Sentence object
 *
 * @author Kiryakov_E
 *
 */
public class Sentence
    extends AbstractObject {

    private String sentence;

    /**
     * Default constructor
     */
    public Sentence() {
        //Does nothing
    }

    /**
     *
     * Constructor.
     *
     * @param module - module name. Can't be <code>null</code>.
     * @param sentence - sentence. Can't be <code>null</code>.
     */
    public Sentence(String module, String sentence) {
        this.module = module;
        this.sentence = sentence;
    }

    /**
     * @return the sentence
     */
    public String getSentence() {
        return sentence;
    }

    @Override
    public String toString() {
        String result = "Sentence(";
        if (module != null)
        {
            result += module + ", ";
        }
        if (sentence != null)
        {
            result += sentence;
        }
        result += ")";

        return result;
    }
}
