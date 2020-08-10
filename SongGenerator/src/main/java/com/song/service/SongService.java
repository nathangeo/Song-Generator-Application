package com.song.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;
import javax.sound.midi.Track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.dao.SongDAO;
import com.song.entity.Song;

@Service
public class SongService {

	private SongDAO songdao;
	
	@Autowired
	public SongService(SongDAO songdao) {
		this.songdao = songdao;
	}
	
	public List<Song> findSongByUsername(String username) {
		return this.songdao.findSongByUsername(username);
	}
	
	public Song addUpdateSong(Song song) {
		return this.songdao.save(song);
	}
	
	public static void initializeIt(Track t, byte instrument) {
		
		try {
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
			mm.setMessage(0xC0, instrument, 0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);
		} catch (Exception e) {
			System.out.println("Exception: " + e.toString());
		}
		
	}
	
	public File polyrythm(int left, int right, int beatLength) {
		System.out.println("midifile start");
		byte[] thenotes = {0x3C, 0x3D, 0x3E, 0x3F, 0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47};
		double time = 0;
		double offset1 = (double)(beatLength / left);
		double offset2 = (double)(beatLength / right);
		
		try {
			Sequence s = new Sequence(javax.sound.midi.Sequence.PPQ,24);
			Track t1 = s.createTrack();
			Track t2 = s.createTrack();
			ShortMessage mm;
			MidiEvent me;
			
			//loop for track 1
			while(time <= beatLength + 0.5) {
				
				initializeIt(t1, (byte) 0x03);
				//note 1 on
				mm = new ShortMessage();
				mm.setMessage(0x90,0x3C,0x60);
				me = new MidiEvent(mm, (long) time);
				t1.add(me);
				
				time = time + 50;
				
				//note 1 off
				mm = new ShortMessage();
				mm.setMessage(0x80,0x3C,0x40);
				me = new MidiEvent(mm, (long) time);
				t1.add(me);
				
				time = time + offset1 - 50;
				
			}
			
			time = 0;
			
			//loop for track 2
			while(time <= beatLength + 0.5) {
				
				initializeIt(t2, (byte) 0x02);
				//note 2 on
				mm = new ShortMessage();
				mm.setMessage(0x90,0x3E,0x60);
				me = new MidiEvent(mm, (long) time);
				t2.add(me);
				
				time = time + 50;
				
				//note 2 off
				mm = new ShortMessage();
				mm.setMessage(0x80,0x3E,0x40);
				me = new MidiEvent(mm, (long) time);
				t2.add(me);
				
				time = time + offset2 - 50;
				
			}
			
			time = time + 0;
			
			//set end of track 19 ticks past last note
			MetaMessage mt = new MetaMessage();
	        byte[] bet = {}; // empty array
			mt.setMessage(0x2F,bet,0);
			me = new MidiEvent(mt, (long) time);
			t1.add(me);
			t2.add(me);

			//write the MIDI sequence to a MIDI file
			File f = new File("midifilenotes.mid");
			MidiSystem.write(s,1,f);
			return f;
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.toString());
		}
		System.out.println("midifile end");
		return null;
	}
	
	
	public File cofOrdered(String tonic, String mode, int noteLength) {
	    System.out.println("midifile begin ");
		long time = 0;
		byte[] baseNotes = {0x47, 0x42, 0x3D, 0x44, 0x3F, 0x46, 0x41, 0x3C, 0x43, 0x3E, 0x45, 0x40};
		String[] tonics = {"B", "Gf", "Df", "Af", "Ef", "Bf", "F", "C", "G", "D", "A", "E"};
		int index = 0;
		int switchCase = 0;
		String[] modes = {"Lydian", "Ionian", "Mixolydian", "Dorian", "Aeolian", "Phrygian", "Locrian"};
		List<Integer> sequence = new ArrayList<Integer>();
		
		for(int i = 0; i < modes.length; i++) {
			if(modes[i].equals(mode)) {
				switchCase = i;
				break;
			}
		}
		
		switch(switchCase) {
		case 0:
			sequence.add(0,0);sequence.add(1,0);sequence.add(2,0);sequence.add(3,1);sequence.add(4,1);sequence.add(5,1);sequence.add(6,2);
			break;
		case 1:
			sequence.add(0,0);sequence.add(1,0);sequence.add(2,1);sequence.add(3,1);sequence.add(4,1);sequence.add(5,2);sequence.add(6,0);
			break;
		case 2:
			sequence.add(0,0);sequence.add(1,1);sequence.add(2,1);sequence.add(3,1);sequence.add(4,2);sequence.add(5,0);sequence.add(6,0);
			break;
		case 3:
			sequence.add(0,1);sequence.add(1,1);sequence.add(2,1);sequence.add(3,2);sequence.add(4,0);sequence.add(5,0);sequence.add(6,0);
			break;
		case 4:
			sequence.add(0,1);sequence.add(1,1);sequence.add(2,2);sequence.add(3,0);sequence.add(4,0);sequence.add(5,0);sequence.add(6,1);
			break;
		case 5:
			sequence.add(0,1);sequence.add(1,2);sequence.add(2,0);sequence.add(3,0);sequence.add(4,0);sequence.add(5,1);sequence.add(6,1);
			break;
		case 6:
			sequence.add(0,2);sequence.add(1,0);sequence.add(2,0);sequence.add(3,0);sequence.add(4,1);sequence.add(5,1);sequence.add(6,1);
			break;
		default:
			System.out.println("no good");
		}
		
		for(int i = 0; i < tonics.length; i++) {
			if(tonics[i].equals(tonic)) {
				index = i;
				break;
			}
		}
		
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
			
			//add the chords
			for(int i = 0; i < 7; i++) {
				//if major chord
				if(sequence.get(i) == 0) {
					time = time + 1;
					byte n1 = baseNotes[index];
					byte m1 = (byte) (n1 + 0x04);
					byte m2 = (byte) (n1 + 0x07);
					
					//base note on
					mm = new ShortMessage();
					mm.setMessage(0x90, n1, 0x60);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					//4th degree note on
					mm = new ShortMessage();
					mm.setMessage(0x90, m1, 0x60);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					//7th degree note on
					mm = new ShortMessage();
					mm.setMessage(0x90, m2, 0x60);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					time = time + noteLength;
					
					//base note off
					mm = new ShortMessage();
					mm.setMessage(0x80,n1,0x40);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					//note m1 off
					mm = new ShortMessage();
					mm.setMessage(0x80, m1, 0x40);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					//note m2 off
					mm = new ShortMessage();
					mm.setMessage(0x80, m2, 0x40);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					index = index + 1;
					if(index > 11) {
						index = 0;
					}
				
				//if minor chord
				} else if(sequence.get(i) == 1) {
					time = time + 1;
					byte n1 = baseNotes[index];
					byte m1 = (byte) (n1 + 0x03);
					byte m2 = (byte) (n1 + 0x07);
					
					//base note on
					mm = new ShortMessage();
					mm.setMessage(0x90, n1, 0x60);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					//3rd degree note on
					mm = new ShortMessage();
					mm.setMessage(0x90, m1, 0x60);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					//7th degree note on
					mm = new ShortMessage();
					mm.setMessage(0x90, m2, 0x60);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					time = time + noteLength;
					
					//base note off
					mm = new ShortMessage();
					mm.setMessage(0x80,n1,0x40);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					//note m1 off
					mm = new ShortMessage();
					mm.setMessage(0x80, m1, 0x40);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					//note m2 off
					mm = new ShortMessage();
					mm.setMessage(0x80, m2, 0x40);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					index = index + 1;
					if(index > 11) {
						index = 0;
					}
				
				//if diminished chord
				} else if(sequence.get(i) == 2) {
					time = time + 1;
					byte n1 = baseNotes[index];
					byte m1 = (byte) (n1 + 0x03);
					byte m2 = (byte) (n1 + 0x06);
					
					//base note on
					mm = new ShortMessage();
					mm.setMessage(0x90, n1, 0x60);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					//3rd degree note on
					mm = new ShortMessage();
					mm.setMessage(0x90, m1, 0x60);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					//6th degree note on
					mm = new ShortMessage();
					mm.setMessage(0x90, m2, 0x60);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					time = time + noteLength;
					
					//base note off
					mm = new ShortMessage();
					mm.setMessage(0x80,n1,0x40);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					//note m1 off
					mm = new ShortMessage();
					mm.setMessage(0x80, m1, 0x40);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					//note m2 off
					mm = new ShortMessage();
					mm.setMessage(0x80, m2, 0x40);
					me = new MidiEvent(mm, time);
					t.add(me);
					
					index = index - 6;
					if(index < 0) {
						index = index + 12;
					}
				}
			}
			
			time = time + 19;
			
			//set end of track 19 ticks past last note
			mt = new MetaMessage();
	        byte[] bet = {}; // empty array
			mt.setMessage(0x2F,bet,0);
			me = new MidiEvent(mt, time);
			t.add(me);

			//write the MIDI sequence to a MIDI file
			File f = new File("cof.mid");
			MidiSystem.write(s,1,f);
			return f;
			
		} catch(Exception e) {
			System.out.println("Exception caught: " + e.toString());
		}
	    System.out.println("midifile end ");
	    return null;
	}
	
}
