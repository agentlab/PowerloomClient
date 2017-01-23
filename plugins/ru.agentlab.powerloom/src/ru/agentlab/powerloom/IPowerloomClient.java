/**
 *
 */
package ru.agentlab.powerloom;

import ru.agentlab.powerloom.model.Command;
import ru.agentlab.powerloom.model.Query;
import ru.agentlab.powerloom.model.Sentence;

/**
 *
 * PowerLoom Client
 *
 * @author Валерия
 *
 */
public interface IPowerloomClient {
    /**
    *
    * Sends command to server.
    *
    * @param command - command to send. Can't be <code>null</code>.
    *
    * @return command result. Can return <code>null</code>.
    */
    String sendCommand(Command command);

    /**
    *
    * Sends assertion to server.
    *
    * @param sentence - sentence to send. Can't be <code>null</code>.
    *
    * @return assertion result. Can return <code>null</code>.
    */
    String sendAssertion(Sentence sentence);

    /**
    *
    * Sends retraction to server.
    *
    * @param sentence - sentence to send. Can't be <code>null</code>.
    *
    * @return retraction result. Can return <code>null</code>.
    */
    String sendRetraction(Sentence sentence);

    /**
    *
    * Sends ask to server.
    *
    * @param query - query to send. Can't be <code>null</code>.
    *
    * @return ask result. Can return <code>null</code>.
    */
    String sendAsk(Query query);

    /**
    *
    * Sends retrieval to server.
    *
    * @param query - query to send. Can't be <code>null</code>.
    *
    * @return retrieval result. Can return <code>null</code>.
    */
    String sendRetrieval(Query query);
}
