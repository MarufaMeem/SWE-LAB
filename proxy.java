// Roll: 2007105


interface TV {
    //  basic TV operations
    boolean isEnabled();
    void enable();
    void disable();
    int getVolume();
    void setVolume(int vol);
    int getChannel();
    void setChannel(int channel);
}

class SmartTV implements TV {
    // Smart TV 
    boolean powerOn = false;
    int volume = 25;
    int channel = 1;

   
   
    //YouTube on Smart TV
    public void Youtube(YoutubeClass yt) {
        System.out.println("Youtube for SmartTV::");
        yt.runYoutube();
    }
}

class GeneralTV implements TV {
    // General TV 
    boolean powerOn = false;
    int volume = 15;
    int channel = 1;

    //  TV interface 
  
}

class Remote {
    //  remotes and TV
    protected TV tv;

    Remote() {
        tv = null;
    }

    Remote(TV tv) {
        this.tv = tv;
    }

    //  remote control 
  
}

class SmartRemote extends Remote {
    // Extension of Remote for Smart TV
    SmartRemote() {
    }

    SmartRemote(TV tv) {
        super(tv);
    }

    // Additional to show YouTube on Smart TV
    void showYoutube(YoutubeClass yt) {
        ((SmartTV) tv).Youtube(yt);
    }
}

// YoutubeClass  Proxy Design 
interface YoutubeClass {
    void runYoutube();
}

class FirstLoadYoutube implements YoutubeClass {
    //loading of YouTube
    @Override
    public void runYoutube() {
        System.out.println("-> Running Youtube");
    }
}

class ProxyLoadYoutube implements YoutubeClass {
    // Proxy to manage YouTube 
    private FirstLoadYoutube firstLoadYoutube;

    @Override
    public void runYoutube() {
        if (firstLoadYoutube == null) {
            firstLoadYoutube = new FirstLoadYoutube();
            System.out.println("-> Starting Youtube");
        }
        firstLoadYoutube.runYoutube();
    }
}

public class Main {
    public static void main(String[] args) {
        // General TV
        GeneralTV gtv = new GeneralTV();
        Remote remote = new Remote(gtv);
        System.out.println("General Tv::");
        remote.togglePower();
        remote.volumeUp();
        remote.channelUp();
        remote.channelDown();
        remote.volumeDown();

        // Smart TV
        System.out.println();
        System.out.println("Smart Tv::");
        SmartTV stv = new SmartTV();
        SmartRemote sremote = new SmartRemote(stv);
        sremote.togglePower();
        sremote.volumeUp();
        sremote.channelUp();
        sremote.channelDown();
        sremote.volumeDown();

        System.out.println();

        // Proxy for YouTube 
        YoutubeClass yt = new ProxyLoadYoutube();

        // Show YouTube on Smart TV
        sremote.showYoutube(yt);
        sremote.showYoutube(yt);
    }
}
