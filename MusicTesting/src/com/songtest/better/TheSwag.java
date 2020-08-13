package com.songtest.better;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TheSwag {

    public static void main(String[] args) throws IOException {
    // TODO code application logic here

    // // read KeyWestTemp.txt

    // create token1
    String token1 = "";

    // for-each loop for calculating heat index of May - October

    // create Scanner inFile1
    Scanner inFile1 = new Scanner(new File("C:\\Users\\natha\\Desktop\\sts-4.6.0.RELEASE\\git\\Song-Generator-Application\\MusicTesting\\thefile\\sdf.txt")).useDelimiter(",\\s*");

    // Original answer used LinkedList, but probably preferable to use ArrayList in most cases
    // List<String> temps = new LinkedList<String>();
    List<String> temps = new ArrayList<String>();

    // while loop
    while (inFile1.hasNext()) {
      // find next line
      token1 = inFile1.next();
      temps.add(token1);
    }
    inFile1.close();
    List<String> tt = new ArrayList<String>();
    String[] tempsArray = temps.toArray(new String[0]);
    for (String s : tempsArray) {
      tt.add(s);
    }
    String[] ttt = tt.toArray(new String[0]);
    System.out.println(Arrays.toString(ttt));
  }
	
}
