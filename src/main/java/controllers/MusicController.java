package controllers;

public class MusicController {
    private static MusicController instance = null;

    private MusicController() {
    }

    public static MusicController getInstance() {
        if (instance == null)
            instance = new MusicController();
        return instance;
    }
}
