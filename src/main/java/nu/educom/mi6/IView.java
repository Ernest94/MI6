package nu.educom.mi6;

public interface IView {
    public void triggerLogin();
    public boolean getResult();
    public void showMessage(String msg);
    public String getServiceNumber();
    public String getSecretCode();
    public String checkView();
}
