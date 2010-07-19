package qedit.task;

import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.TaskMonitor;
import org.jdesktop.application.TaskService;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public abstract class AbstractTask extends org.jdesktop.application.Task {

    public AbstractTask() {
        super(qedit.QEditApp.getApplication());
    }

    public void runInBackground() {
        ApplicationContext appC = qedit.QEditApp.getInstance().getContext();
        TaskMonitor taskMonitor = appC.getTaskMonitor();
        TaskService taskService = appC.getTaskService();
        taskService.execute(this);
        taskMonitor.setForegroundTask(this);
    }
}
