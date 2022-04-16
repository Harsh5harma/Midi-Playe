import javax.sound.midi.*;

public class MiniMusicCMdLine{
    public static void main(String[] args){
        MiniMusicCMdLine mini = new MiniMusicCMdLine();
        if (args.length<2){
            System.out.println("Don't forget the Instrument and note args!");
        }
        else{
            int Instrument = Integer.parseInt(args[0]);
            int Note = Integer.parseInt(args[1]);
            mini.play(Instrument,Note);
        }
    }

    public void play(int Instrument, int Note){
        try{
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ,4);
            Track track=seq.createTrack();

            MidiEvent event = null;

            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, Instrument, 0);
            MidiEvent changeInstruement  = new MidiEvent(first, 1);
            track.add(changeInstruement);

            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,Note,100);
            MidiEvent noteOn = new MidiEvent(a,1);
            track.add(noteOn);

            ShortMessage b  = new ShortMessage();
            b.setMessage(128,1,Note,100);
            MidiEvent noteOff = new MidiEvent(b,16);
            track.add(noteOff);

            player.setSequence(seq);
            player.start();
        }
        catch(Exception ex){
            
        }
    }
}