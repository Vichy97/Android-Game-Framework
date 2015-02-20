package com.vincent.simpleandroidgamedevelopmentframework;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.vincent.framework.tile.Tile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Vincent on 1/18/2015.
 *
 * This class is used for loading assets into the memory.
 * Nothing is actually loaded in this Class but you do need to
 * declare bitmaps and sounds in this class. load assets
 * in separate load state classes. This is done so that several
 * load state classes can be made for each different state of
 * the game. That allows you to unload the previous game states
 * assets before switching states and free up memory.
 */
public class Assets {

    private static MediaPlayer mediaPlayer;

    private static SoundPool soundPool;

    public static Bitmap menuBackground;

    public static void onPause() {
        if(soundPool != null) {
            soundPool.release();
            soundPool = null;
        }

        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static void onResume() {

    }

    //used for loading Bitmap Images
    public static Bitmap loadBitmap(String filename, boolean transparency) {
        InputStream inputStream = null;
        try {
            inputStream = GameMainActivity.assets.open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Options options = new Options();
      //  options.inDither = true;
        if (transparency) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        } else {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null,
                options);
        return bitmap;
    }

    //unloads the bitmap and clears up memory
    public static void unloadBitmap(Bitmap bitmap) {
        bitmap.recycle();
        bitmap = null;
    }

    // loads sounds (I think they have to be in .wav format)
    public static int loadSound(String filename) {
        int soundID = 0;
        if (soundPool == null) {
            buildSoundPool();
        }
        try {
            soundID = soundPool.load(GameMainActivity.assets.openFd(filename), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return soundID;
    }


    //SoundPool was deprecated as of android 5.0 (lolipop) so
    //we have to check what the version of android is in order
    //to determine which is the best way to load sound
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static SoundPool buildSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(25)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(25, AudioManager.STREAM_MUSIC, 0);
        }
        return soundPool;
    }


    //load vector Images. This makes them into a Bitmap object and you
    //will need to use the method drawImage
    public static Bitmap loadVectorImage(String filename, int width, int height) {
        Picture picture = null;
        PictureDrawable pictureDrawable = null;
        Bitmap b;
        try {
             picture = SVG.getFromAsset(GameMainActivity.assets, filename).renderToPicture(width, height);
        } catch (SVGParseException | IOException e) {
            e.printStackTrace();
        }
        pictureDrawable = new PictureDrawable(picture);
        b = pictureDrawableToBitmap(pictureDrawable);
        return b;
    }

    //unloads vector images i think. there is no way to .recyle() them...
    public static void unloadVectorImage(SVG svg) {
        svg = null;
    }

    //converts a PictureDrawable to a Bitmap because hardware acceleration does
    //not support drawing Drawables or Pictures
    private static Bitmap pictureDrawableToBitmap(PictureDrawable pictureDrawable){
        Bitmap bitmap = Bitmap.createBitmap(pictureDrawable.getIntrinsicWidth(), pictureDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawPicture(pictureDrawable.getPicture());
        return bitmap;
    }


    //play a sound that is already loaded into memory
    public static void playSound(int soundID) {
        if (soundPool != null) {
            soundPool.play(soundID, 1, 1, 1, 0, 1);
        }
    }

    //Used to stream music without loading it into the memory
    public static void playMusic(String fileName, boolean looping) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        try {
            AssetFileDescriptor afd = GameMainActivity.assets.openFd(fileName);
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            mediaPlayer.setLooping(looping);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
