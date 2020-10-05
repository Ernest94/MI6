package nu.educom.mi6;

public class Presenter{
 
    private IView view;
    private Model model;
    
    boolean stopped = false;

    public Presenter(IView view) {
        this.model  = new Model();
		this.view = view;
    }

    public void run() {
        
        while(!stopped) {
            view.triggerLogin();
            int serviceNumber = model.convertServiceNumber(view.getServiceNumber());
            if (!(model.validateServiceNumber(serviceNumber))) {
                view.showMessage("ACCESS DENIED");
            } else {
                model.getAgent(serviceNumber);
                if (!model.validateLoginAttempts()) {
                    view.showMessage("ACCESS DENIED");
                } else {
                    if (!model.validateSecretCode(view.getSecretCode()) || !model.validateActivity()) {
                        model.createLoginAttempt(false);
                        view.showMessage("<html><div style='text-align:center'> ACCESS DENIED <br> " + 
                                             "Try again at: " + model.getNewAllowedToLoginTime() + "</div></html>");      
                        } else {
                            view.showMessage("<html><div style='text-align:center'> Welcome agent " + 
                                    model.getPaddedServiceNumberStr() + 
                                    ", <br>" + model.getLicenseToKillMessage() + 
                                    "</div></html>");       
                            view.showTable(model.getLastLoginAttempts());
                            model.createLoginAttempt(true);  
                            }
                }
            } 
        }
    }
}

