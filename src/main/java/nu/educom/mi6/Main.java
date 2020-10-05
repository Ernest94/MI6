package nu.educom.mi6;

public class Main {

    public static void main(String[] args)
    {        
        IView view;
        view = new FrameView();

        Presenter p = new Presenter(view);
        p.run();
    }
}