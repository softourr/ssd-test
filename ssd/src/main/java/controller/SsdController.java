package controller;
import repository.SsdRepository;
import view.SsdView;

public class SsdController {
    private SsdRepository ssdRepository;
    private SsdView ssdView;

    public SsdController(SsdRepository ssdRepository, SsdView ssdView) {
        this.ssdRepository = ssdRepository;
        this.ssdView = ssdView;
    }

    public void read(int index){
        String result = ssdRepository.read(index);
        // 뷰 해줘
    }

    public void write(int index, String string){
        ssdRepository.write(index, string);
    }

}
