/**
 *
 */
package ru.agentlab.powerloom.client;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

import ru.agentlab.powerloom.IPowerloomClient;
import ru.agentlab.powerloom.IPowerloomServer;
import ru.agentlab.powerloom.model.Command;
import ru.agentlab.powerloom.model.Query;
import ru.agentlab.powerloom.model.Sentence;

/**
 *
 * Implementation {@link IPowerLoomClient}.
 *
 * @author Leonova
 *
 */
@Component(name = "com.bmstu.coursework.powerloom.client")
public class PowerLoomClient
    implements IPowerloomClient, CommandProvider {

    private static final String DEFAULT_MODULE = "BMSTU"; //$NON-NLS-1$

    private IPowerloomServer powerloomServer;

    /**
     * Default constructor
     */
    public PowerLoomClient() {
        // Does nothing
    }

    @Activate
    void activate() {
        System.out.println("PowerLoomClient started"); //$NON-NLS-1$
    }

    @Reference(policy = ReferencePolicy.DYNAMIC)
    void bindServer(IPowerloomServer powerloomServer) {
        System.out.println("Discovered IPowerloomServer= " + powerloomServer); //$NON-NLS-1$
        this.powerloomServer = powerloomServer;
    }

    void unbindServer(IPowerloomServer powerloomServer) {
        if (powerloomServer.equals(this.powerloomServer))
        {
            this.powerloomServer = null;
        }
    }

    @Override
    public String sendCommand(Command command) {
        if (powerloomServer != null)
        {
            return powerloomServer.command(command);
        }

        return null;
    }

    @Override
    public String sendAssertion(Sentence sentence) {
        if (powerloomServer != null)
        {
            return powerloomServer.assertion(sentence);
        }

        return null;
    }

    @Override
    public String sendRetraction(Sentence sentence) {
        if (powerloomServer != null)
        {
            return powerloomServer.retraction(sentence);
        }

        return null;
    }

    @Override
    public String sendAsk(Query query) {
        if (powerloomServer != null)
        {
            return powerloomServer.ask(query);
        }

        return null;
    }

    @Override
    public String sendRetrieval(Query query) {
        if (powerloomServer != null)
        {
            return powerloomServer.retrieval(query);
        }

        return null;
    }

    public void _command(CommandInterpreter interpreter) {
        System.out.println(sendCommand(new Command(DEFAULT_MODULE, interpreter.nextArgument())));
    }

    public void _assertion(CommandInterpreter interpreter) {
        System.out.println(sendAssertion(new Sentence(DEFAULT_MODULE, interpreter.nextArgument())));
    }

    public void _retraction(CommandInterpreter interpreter) {
        System.out.println(sendRetraction(new Sentence(DEFAULT_MODULE, interpreter.nextArgument())));
    }

    public void _ask(CommandInterpreter interpreter) {
        System.out.println(sendAsk(new Query(DEFAULT_MODULE, interpreter.nextArgument())));
    }

    public void _retrieval(CommandInterpreter interpreter) {
        System.out.println(sendRetrieval(new Query(DEFAULT_MODULE, interpreter.nextArgument())));
    }

    public void _quit(CommandInterpreter interpreter) {
        interpreter.execute("exit"); //$NON-NLS-1$
    }

    @Override
    public String getHelp() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("---Powerloom Commands---\n\t"); //$NON-NLS-1$
        buffer.append("command, assertion, retraction, ask, retrieval"); //$NON-NLS-1$

        return buffer.toString();
    }
}
