package nu.educom.mi6;

public class Main {

    public static void main(String[] args)
    {
//	    ConsoleView view = new ConsoleView();
	    GuiView view = new GuiView();

	    Presenter p = new Presenter(view);
	    p.run();
    }
}