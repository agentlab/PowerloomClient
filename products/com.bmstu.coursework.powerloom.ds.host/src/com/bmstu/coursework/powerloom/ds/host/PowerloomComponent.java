/**
 *
 */
package com.bmstu.coursework.powerloom.ds.host;

import java.io.IOException;
import java.util.Dictionary;

import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.powerloom.service.PowerLoomService;

import edu.isi.powerloom.PLI;
import edu.isi.powerloom.PlIterator;
import edu.isi.powerloom.logic.TruthValue;
import edu.isi.stella.Module;
import edu.isi.stella.Stella_Object;
import ru.agentlab.powerloom.IPowerloomServer;
import ru.agentlab.powerloom.model.Command;
import ru.agentlab.powerloom.model.Query;
import ru.agentlab.powerloom.model.Sentence;

/**
 *
 * @author Krasovsky
 *
 */
@Component(enabled = true, immediate = true,
    property = { "service.exported.interfaces=*", "service.exported.configs=ecf.jaxrs.jersey.server",
        "ecf.jaxrs.jersey.server.urlContext=http://localhost:8080", "ecf.jaxrs.jersey.server.alias=/powerloom",
        "service.pid=com.bmstu.coursework.powerloom.ds.host.PowerloomComponent" })
public class PowerloomComponent
    implements IPowerloomServer, ManagedService {

    private static final String INITIAL_SETTINGS_FILE_NAME = "initialSettings.plm";
    //initial settings use this module
    //private static final String MODULE_NAME = "BMSTU";
    PowerLoomService powerLoomService;
    Module powerLoomModule;

    @Override
    public String hello() {
        return "Hello, powerloom";
    }

    @Override
    public String command(Command command) {
        Stella_Object result = PLI.sEvaluate(command.getCommand(), command.getModule(), null);
        return "Command: " + command + " accepted\nResult: " + result;
    }

    @Override
    public String assertion(Sentence sentence) {
        PlIterator result = PLI.sAssertProposition(sentence.getSentence(), sentence.getModule(), null);
        return "Assertion sentence: " + sentence + " accepted\nResult: " + result;
    }

    @Override
    public String retraction(Sentence sentence) {
        PlIterator result = PLI.sRetractProposition(sentence.getSentence(), sentence.getModule(), null);
        return "Retraction sentence: " + sentence + " accepted\nResult: " + result;
    }

    @Override
    public String ask(Query query) {
        TruthValue result = PLI.sAsk(query.getQuery(), query.getModule(), null);
        return "Ask query: " + query + " accepted\nResult: " + result;
    }

    @Override
    public String retrieval(Query query) {
        PlIterator result = PLI.sRetrieve(query.getQuery(), query.getModule(), null);
        return "retrieval query: " + query + " accepted\nResult: " + result;
    }

    @Activate
    public void activate(ComponentContext context) throws IOException {
        powerLoomService = PowerLoomService.getInstance();
        powerLoomService.loadPlm(INITIAL_SETTINGS_FILE_NAME);

        System.out.println("Powerloom service started"); //$NON-NLS-1$
    }

    @Deactivate
    public void deactivate(ComponentContext context) {
        System.out.println("Powerloom service stopped"); //$NON-NLS-1$
    }

    @Modified
    public void modify() {
        System.out.println("Powerloom service modified"); //$NON-NLS-1$
    }

    @Override
    public void updated(Dictionary<String, ?> properties) throws ConfigurationException {
        // Does nothing
    }

}
