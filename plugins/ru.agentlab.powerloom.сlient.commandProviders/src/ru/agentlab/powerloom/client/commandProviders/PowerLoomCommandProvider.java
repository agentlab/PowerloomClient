/**
 *
 */
package ru.agentlab.powerloom.client.commandProviders;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

import ru.agentlab.powerloom.IPowerloomClient;
import ru.agentlab.powerloom.model.Command;
import ru.agentlab.powerloom.model.Query;
import ru.agentlab.powerloom.model.Sentence;

/**
 * @author Валерия
 *
 */
@Component(name = "com.bmstu.coursework.powerloom.commandProviders")
public class PowerLoomCommandProvider implements CommandProvider {

    private static final String DEFAULT_MODULE = "BMSTU"; //$NON-NLS-1$

    private IPowerloomClient powerLoomClient;

    /**
     * Default constructor
     */
    public PowerLoomCommandProvider() {
        // Does nothing
    }

    @Activate
    void activate() {
        System.out.println("Powerloom command provider activated"); //$NON-NLS-1$
    }

    @Reference(policy = ReferencePolicy.DYNAMIC)
    void bindClient(IPowerloomClient powerLoomClient) {
        System.out.println("Discovered powerloomClient= " + powerLoomClient); //$NON-NLS-1$
        this.powerLoomClient = powerLoomClient;
    }

    void unbindClient(IPowerloomClient powerLoomClient) {
        if (powerLoomClient.equals(this.powerLoomClient))
        {
            this.powerLoomClient = null;
        }
    }


    public void _command(CommandInterpreter interpreter) {
        System.out.println(powerLoomClient.sendCommand(new Command(DEFAULT_MODULE, interpreter.nextArgument())));
    }

    public void _assertion(CommandInterpreter interpreter) {
        System.out.println(powerLoomClient.sendAssertion(new Sentence(DEFAULT_MODULE, interpreter.nextArgument())));
    }

    public void _retraction(CommandInterpreter interpreter) {
        System.out.println(powerLoomClient.sendRetraction(new Sentence(DEFAULT_MODULE, interpreter.nextArgument())));
    }

    public void _ask(CommandInterpreter interpreter) {
        System.out.println(powerLoomClient.sendAsk(new Query(DEFAULT_MODULE, interpreter.nextArgument())));
	}

    public void _retrieval(CommandInterpreter interpreter) {
        System.out.println(powerLoomClient.sendRetrieval(new Query(DEFAULT_MODULE, interpreter.nextArgument())));
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
