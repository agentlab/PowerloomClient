/**
 *
 */
package ru.agentlab.powerloom.model;

/**
 * Command object
 *
 * @author Kiryakov_E
 *
 */
public class Command
    extends AbstractObject {

    private String command;

    /**
     * Default constructor
     */
    public Command() {
        // Does nothing
    }

    /**
     * Constructor
     *
     * @param module - module name. Can't be <code>null</code>.
     * @param command - command. Can't be <code>null</code>.
     */
    public Command(String module, String command) {
        this.module = module;
        this.command = command;
    }

    /**
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        String result = "Command(";
        if (module != null)
        {
            result += module + ", ";
        }
        if (command != null)
        {
            result += command;
        }
        result += ")";

        return result;
    }
}
