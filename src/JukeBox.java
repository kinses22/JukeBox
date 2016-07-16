import com.kinses22.model.JukeBoxMenu;
import com.kinses22.model.Song;
import com.kinses22.model.SongLibrary;

public class JukeBox {

    public static void main(String[] args) {
        SongLibrary songLibrary = new SongLibrary();
        JukeBoxMenu jbm = new JukeBoxMenu(songLibrary);

        jbm.run();

    }

}
