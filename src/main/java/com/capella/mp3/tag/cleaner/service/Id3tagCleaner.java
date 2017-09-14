package com.capella.mp3.tag.cleaner.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagConstant;
import org.farng.mp3.TagException;
import org.farng.mp3.TagOptionSingleton;
import org.farng.mp3.id3.AbstractID3v2;

/**
 * @author Ramesh Rajendran
 */
public class Id3tagCleaner {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\RameshRajendran\\Music\\Vivegam");
        if(file.isDirectory()){
            File[] files = file.listFiles();
            Stream.of(files).filter(mp3 -> mp3.getName().endsWith(".mp3")).forEach(mp3 -> printTags(mp3));
        }
    }

    private static void printTags(File mp3) {
        try {
            System.out.println(mp3.getName());
            MP3File mp3file = new MP3File(mp3);
            TagOptionSingleton.getInstance().setDefaultSaveMode(TagConstant.MP3_FILE_SAVE_OVERWRITE);

            AbstractID3v2 id3v2Tag = mp3file.getID3v2Tag();
            String replace = id3v2Tag.getAlbumTitle().replace(" - MassTamilan.com", "");
            id3v2Tag.setAlbumTitle(replace);
            System.out.println("Title: " + id3v2Tag.getAlbumTitle());
            mp3file.save();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        }
    }
}
