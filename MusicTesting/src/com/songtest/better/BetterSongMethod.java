package com.songtest.better;

import java.io.File;
import java.util.Random;

import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;
import javax.sound.midi.Track;

public class BetterSongMethod {

	public static void generateSong(int noteNumber) {
	    System.out.println("midifile begin ");
		Random rand = new Random();
		byte[] thenotes = {0x3C, 0x3D, 0x3E, 0x3F, 0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47};
		long time = 0;
		
		try {
			//Create a new MIDI sequence with 24 ticks per beat
			Sequence s = new Sequence(javax.sound.midi.Sequence.PPQ,24);
			//Obtain a MIDI track from the sequence
			Track t = s.createTrack();
			
			//turn on General MIDI sound set
			byte[] b = {(byte)0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte)0xF7};
			SysexMessage sm = new SysexMessage();
			sm.setMessage(b, 6);
			MidiEvent me = new MidiEvent(sm,(long)0);
			t.add(me);

			//set tempo
			MetaMessage mt = new MetaMessage();
	        byte[] bt = {0x02, (byte)0x00, 0x00};
			mt.setMessage(0x51 ,bt, 3);
			me = new MidiEvent(mt,(long)0);
			t.add(me);

			//set track name
			mt = new MetaMessage();
			String TrackName = new String("midifile track");
			mt.setMessage(0x03 ,TrackName.getBytes(), TrackName.length());
			me = new MidiEvent(mt,(long)0);
			t.add(me);

			//set omni on
			ShortMessage mm = new ShortMessage();
			mm.setMessage(0xB0, 0x7D,0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);

			//set poly on
			mm = new ShortMessage();
			mm.setMessage(0xB0, 0x7F,0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);

			//set instrument to Piano
			mm = new ShortMessage();
			mm.setMessage(0xC0, 0x00, 0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);
			
			for(int i = 0; i < noteNumber; i++) {
				
				byte n1 = thenotes[rand.nextInt(12)];
				time = time + 1;
				
				//note i on
				mm = new ShortMessage();
				mm.setMessage(0x90,n1,0x60);
				me = new MidiEvent(mm, time);
				t.add(me);
				
				time = time + 120;
				
				//note i off (120 ticks later)
				mm = new ShortMessage();
				mm.setMessage(0x80,n1,0x40);
				me = new MidiEvent(mm, time);
				t.add(me);
			}
			
			time = time + 19;
			
			//set end of track 19 ticks past last note
			mt = new MetaMessage();
	        byte[] bet = {}; // empty array
			mt.setMessage(0x2F,bet,0);
			me = new MidiEvent(mt, time);
			t.add(me);

			//write the MIDI sequence to a MIDI file
			File f = new File("midifilenotes.mid");
			MidiSystem.write(s,1,f);
			
		} catch(Exception e) {
			System.out.println("Exception caught: " + e.toString());
		}
	    System.out.println("midifile end ");
	}
	
	
	public static void generateChords(int numChords) {
	    System.out.println("midifile begin ");
		Random rand = new Random();
		byte[] thenotes = {0x3C, 0x3D, 0x3E, 0x3F, 0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47};
		long time = 0;
		
		try {
			//Create a new MIDI sequence with 24 ticks per beat
			Sequence s = new Sequence(javax.sound.midi.Sequence.PPQ,24);
			//****  Obtain a MIDI track from the sequence  ****
			Track t = s.createTrack();
			
			//turn on General MIDI sound set
			byte[] b = {(byte)0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte)0xF7};
			SysexMessage sm = new SysexMessage();
			sm.setMessage(b, 6);
			MidiEvent me = new MidiEvent(sm,(long)0);
			t.add(me);

			//set tempo
			MetaMessage mt = new MetaMessage();
	        byte[] bt = {0x02, (byte)0x00, 0x00};
			mt.setMessage(0x51 ,bt, 3);
			me = new MidiEvent(mt,(long)0);
			t.add(me);

			//set track name
			mt = new MetaMessage();
			String TrackName = new String("midifile track");
			mt.setMessage(0x03 ,TrackName.getBytes(), TrackName.length());
			me = new MidiEvent(mt,(long)0);
			t.add(me);

			//set omni on
			ShortMessage mm = new ShortMessage();
			mm.setMessage(0xB0, 0x7D,0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);

			//set poly on
			mm = new ShortMessage();
			mm.setMessage(0xB0, 0x7F,0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);

			//set instrument to Piano
			mm = new ShortMessage();
			mm.setMessage(0xC0, 0x00, 0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);
			
			for(int i = 0; i < numChords; i++) {
				
				byte n1 = thenotes[rand.nextInt(12)];
				byte n2 = thenotes[rand.nextInt(12)];
				byte n3 = thenotes[rand.nextInt(12)];
				time = time + 1;
				
				//note i1 on
				mm = new ShortMessage();
				mm.setMessage(0x90,n1,0x60);
				me = new MidiEvent(mm, time);
				t.add(me);
				
				//note i2 on
				mm = new ShortMessage();
				mm.setMessage(0x90,n2,0x60);
				me = new MidiEvent(mm, time);
				t.add(me);
				
				//note i3 on
				mm = new ShortMessage();
				mm.setMessage(0x90,n3,0x60);
				me = new MidiEvent(mm, time);
				t.add(me);
				
				time = time + 120;
				
				//note i1 off (120 ticks later)
				mm = new ShortMessage();
				mm.setMessage(0x80,n1,0x40);
				me = new MidiEvent(mm, time);
				t.add(me);
				
				//note i2 off (120 ticks later)
				mm = new ShortMessage();
				mm.setMessage(0x80,n2,0x40);
				me = new MidiEvent(mm, time);
				t.add(me);
				
				//note i3 off (120 ticks later)
				mm = new ShortMessage();
				mm.setMessage(0x80,n3,0x40);
				me = new MidiEvent(mm, time);
				t.add(me);
			}
			
			time = time + 19;
			
			//set end of track 19 ticks past last note
			mt = new MetaMessage();
	        byte[] bet = {}; // empty array
			mt.setMessage(0x2F,bet,0);
			me = new MidiEvent(mt, time);
			t.add(me);

			//write the MIDI sequence to a MIDI file
			File f = new File("midifilechords.mid");
			MidiSystem.write(s,1,f);
			
		} catch(Exception e) {
			System.out.println("Exception caught: " + e.toString());
		}
	    System.out.println("midifile end ");
	}
	
}
