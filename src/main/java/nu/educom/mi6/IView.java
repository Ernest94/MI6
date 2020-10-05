package nu.educom.mi6;

public interface IView {
    public void triggerLogin();
    public void triggerEnterSecret();
    /**
     * Get the result of the view
     * @return true if the login is done, false if we exit 
     */
    public boolean getResult();
    /**
     * Show a message to the user
     * @param msg the message to show
     */
    public void showMessage(String msg);
    /**
     * Show a table to the user
     * @param data is the table data
     */
    public void showTable(Object[][] data);
    public String getServiceNumber();
    public String getSecretCode();
}
