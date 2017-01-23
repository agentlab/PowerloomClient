/**
 *
 */
package ru.agentlab.powerloom;

import ru.agentlab.powerloom.model.Command;
import ru.agentlab.powerloom.model.Query;
import ru.agentlab.powerloom.model.Sentence;

/**
 *
 * @author Krasovsky
 *
 */
public interface IPowerloomServer {

    /**
     *
     * Says hello to user.
     *
     * @return hello sentence.
     */
    public String hello();

    /**
     *
     * Processes powerloom command.
     *
     * @param command - command text. Can't be <code>null</code>.
     *
     * @return command result. Can't return <code>null</code>.
     */
    public String command(Command command);

    /**
    *
    * Processes powerloom assertion.
    *
    * @param sentence - powerloom sentence. Can't be <code>null</code>.
    *
    * @return assertion result. Can't return <code>null</code>.
    */
    public String assertion(Sentence sentence);

    /**
    *
    * Processes powerloom retraction.
    *
    * @param sentence - powerloom sentence. Can't be <code>null</code>.
    *
    * @return retraction result. Can't return <code>null</code>.
    */
    public String retraction(Sentence sentence);

    /**
    *
    * Processes powerloom ask.
    *
    * @param query - powerloom query. Can't be <code>null</code>.
    *
    * @return ask result. Can't return <code>null</code>.
    */
    public String ask(Query query);

    /**
    *
    * Processes powerloom retrieval.
    *
    * @param query - powerloom query. Can't be <code>null</code>.
    *
    * @return retrieval result. Can't return <code>null</code>.
    */
    public String retrieval(Query query);
}
