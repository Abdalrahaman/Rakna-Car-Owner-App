package com.example.rakna.raknagraduationproject.View.AbdoView;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.example.rakna.raknagraduationproject.Model.AbdoModel.MyPlayerListener;
import com.example.rakna.raknagraduationproject.R;

import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.util.ArrayList;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class OwnerPreviewFragment extends Fragment implements IVLCVout.Callback {

    public final static String TAG = "VideoActivity";

    public static final String RTSP_URL = "rtspurl";

    // display surface
    private SurfaceView mSurface;
    private SurfaceHolder holder;

    // media player
    private LibVLC libvlc;
    private MediaPlayer mMediaPlayer = null;
    private int mVideoWidth;
    private int mVideoHeight;
    private final static int VideoSizeChanged = -1;


    private MediaPlayer.EventListener mPlayerListener = new MyPlayerListener(this);

    private String rtspUrl;


    public OwnerPreviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_owner_preview, container, false);


//        // Get URL
//        Intent intent = getIntent();
//        rtspUrl = intent.getExtras().getString(RTSP_URL);
        rtspUrl = "rtsp://192.168.1.8:554/onvif1";
        Log.d(TAG, "Playing back " + rtspUrl);

        mSurface = (SurfaceView) rootView.findViewById(R.id.surface);
        holder = mSurface.getHolder();
        //holder.addCallback(this);

        ArrayList<String> options = new ArrayList<String>();
        options.add("--aout=opensles");
        options.add("--audio-time-stretch"); // time stretching
        options.add("-vvv"); // verbosity
        options.add("--aout=opensles");
        options.add("--avcodec-codec=h264");
        options.add("--file-logging");
        options.add("--logfile=vlc-log.txt");


        libvlc = new LibVLC(getActivity(), options);
        holder.setKeepScreenOn(true);

        // Create media player
        mMediaPlayer = new MediaPlayer(libvlc);
        mMediaPlayer.setEventListener(mPlayerListener);

        // Set up video output
        final IVLCVout vout = mMediaPlayer.getVLCVout();
        vout.setVideoView(mSurface);
        //vout.setSubtitlesView(mSurfaceSubtitles);
        vout.addCallback(this);
        vout.attachViews();

        Media m = new Media(libvlc, Uri.parse(rtspUrl));

        mMediaPlayer.setMedia(m);
        mMediaPlayer.play();


//        FloatingActionButton parkingFinishedButton = (FloatingActionButton)rootView.findViewById(R.id.fab);
//
//        parkingFinishedButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Intent intentPay = new Intent(getActivity() , PayActivity.class);
////                startActivity(intentPay);
//
////                Intent intentPay = new Intent(getActivity() , HistoryActivity.class);
////                startActivity(intentPay);
//            }
//        });

        return rootView ;
    }


    @Override
    public void onResume() {
        super.onResume();
        // createPlayer(mFilePath);
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onSurfacesCreated(IVLCVout vlcVout) {

    }

    @Override
    public void onSurfacesDestroyed(IVLCVout vlcVout) {

    }



//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }


    public void releasePlayer() {
        if (libvlc == null)
            return;
        mMediaPlayer.stop();
        final IVLCVout vout = mMediaPlayer.getVLCVout();
        vout.removeCallback(this);
        vout.detachViews();
        holder = null;
        libvlc.release();
        libvlc = null;

        mVideoWidth = 0;
        mVideoHeight = 0;
    }

}
