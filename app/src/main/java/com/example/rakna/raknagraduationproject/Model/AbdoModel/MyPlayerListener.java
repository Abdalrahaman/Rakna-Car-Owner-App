package com.example.rakna.raknagraduationproject.Model.AbdoModel;

import android.util.Log;

import com.example.rakna.raknagraduationproject.View.AbdoView.OwnerPreviewFragment;

import org.videolan.libvlc.MediaPlayer;

import java.lang.ref.WeakReference;

public class MyPlayerListener implements MediaPlayer.EventListener {

    private static String TAG = "PlayerListener";
    private WeakReference<OwnerPreviewFragment> mOwner;


    public MyPlayerListener(OwnerPreviewFragment owner) {
        mOwner = new WeakReference<OwnerPreviewFragment>(owner);
    }

    @Override
    public void onEvent(MediaPlayer.Event event) {
        OwnerPreviewFragment player = mOwner.get();

        switch(event.type) {
            case MediaPlayer.Event.EndReached:
                Log.d(TAG, "MediaPlayerEndReached");
                player.releasePlayer();
                break;
            case MediaPlayer.Event.Playing:
            case MediaPlayer.Event.Paused:
            case MediaPlayer.Event.Stopped:
            default:
                break;
        }
    }
}
