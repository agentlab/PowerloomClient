/**
 *
 */
package ru.agentlab.powerloom.client;

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
    implements IPowerloomClient {
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
		if (powerloomServer.equals(this.powerloomServer)) {
			this.powerloomServer = null;
		}
	}

	@Override
    public String sendCommand(Command command) {
		if (powerloomServer != null) {
			return powerloomServer.command(command);
		}

		return null;
	}

	@Override
    public String sendAssertion(Sentence sentence) {
		if (powerloomServer != null) {
			return powerloomServer.assertion(sentence);
		}

		return null;
	}

	@Override
    public String sendRetraction(Sentence sentence) {
		if (powerloomServer != null) {
			return powerloomServer.retraction(sentence);
		}

		return null;
	}

	@Override
    public String sendAsk(Query query) {
		if (powerloomServer != null) {
			return powerloomServer.ask(query);
		}

		return null;
	}

	@Override
    public String sendRetrieval(Query query) {
		if (powerloomServer != null) {
			return powerloomServer.retrieval(query);
		}

		return null;
	}
}
