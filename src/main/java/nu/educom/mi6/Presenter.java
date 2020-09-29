package nu.educom.mi6;

public class Presenter{
 
    private IView view;
    private Model model;
    
    boolean stopped = false;

    public Presenter(IView view) {
        this.model  = new Model();
		this.view = view;
    }
    
    public void login() {
    }
 
    public void run() {
		
        while(!stopped) {
            view.triggerLogin();
            if (view.getResult()) {
                String serviceNumberStr = view.getServiceNumber();
                if (model.checkExit(serviceNumberStr)) {
                    this.stopped = true;
                    break;
                }
                if ((model.validateServiceNumber(serviceNumberStr)) && (!model.onBlacklist(serviceNumberStr))) {
                    if (view.checkView().equals("console")) {
                        view.showMessage("Enter the secret code:");
                    }
                    String secretCode = view.getSecretCode();
                    if (model.validateSecretCode(secretCode)) {
                        String paddedSecretNumber = model.padLeftZeros(serviceNumberStr,3);
                        view.showMessage("Welcome agent " + paddedSecretNumber);
                    } else {
                        view.showMessage("ACCESS DENIED");
                        model.addToBlacklist(serviceNumberStr);
                    }
                } else {
                    view.showMessage("ACCESS DENIED");
                }
            }
        }
    }
}
