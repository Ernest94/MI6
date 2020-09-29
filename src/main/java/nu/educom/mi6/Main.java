package nu.educom.mi6;

public class Main {

    public static void main(String[] args)
    {
        String viewVar;
        IView view;
        
        try {
            viewVar = args[0];
        } catch (Exception e) {
            viewVar = "";
        }
        
        switch (viewVar) {
        case "Console":
            view = new ConsoleView();
            break; 
        case "Dialogue":
            view = new DialogueView();
            break;
        default:
            view = new FrameView();
        }
       
        Presenter p = new Presenter(view);
        p.run();
    }
}