package pc.dialog.com.wutils;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.TimedMetaData;
import android.media.TimedText;
import android.view.View;
import android.widget.Button;

import com.jungle.mediaplayer.base.AudioFocusPlayerListener;
import com.jungle.mediaplayer.base.SimpleMediaPlayerListener;
import com.jungle.mediaplayer.base.VideoInfo;
import com.jungle.mediaplayer.player.BaseMediaPlayer;
import com.jungle.mediaplayer.player.SystemImplMediaPlayer;

import butterknife.BindView;
import pc.wlt.com.superlibrary.Interface.OnMediaPlayerListener;
import pc.wlt.com.superlibrary.base.BaseAppCompatActivity;
import pc.wlt.com.superlibrary.utils.L;
import pc.wlt.com.superlibrary.utils.MMediaPlayer;

public class MediaPlayActivity extends BaseAppCompatActivity implements OnMediaPlayerListener{

    @BindView(R.id.btn_stop)
    Button mBtnStop;
    @BindView(R.id.btn_playOrPause)
    Button mBtnPlay;
    private MMediaPlayer mMediaPlayer;
    private String playUrl="http://jiayin-10076642.video.myqcloud.com/jsmy02/W38833d49-f783-4d27-919a-c198de8678ef.mp3";
    ProgressDialog mDialog;

    boolean isForceStop=false;

    private BaseMediaPlayer baseMediaPlayer;
    @Override
    protected int resId() {
        return R.layout.activity_media_play;
    }

    @Override
    protected void initData() {
        baseMediaPlayer = new SystemImplMediaPlayer(this);
        baseMediaPlayer.addPlayerListener(new AudioFocusPlayerListener(this, new AudioFocusPlayerListener.OnAudioFocusListener() {
            @Override
            public void onLoss() {

            }
        }));
        mDialog=new ProgressDialog(this,ProgressDialog.STYLE_SPINNER);
//        mDialog.show();
        mBtnPlay= (Button) findViewById(R.id.btn_playOrPause);
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mMediaPlayer!=null){
////                    if (mMediaPlayer.isPlaying()){
////                        mMediaPlayer.destroy();
////                    }else {
//                        mMediaPlayer.playUrl(playUrl);
////                    }
//                }
                if (!baseMediaPlayer.isLoadingOrPlaying()){
                    baseMediaPlayer.play(new VideoInfo(playUrl));
                }else {
                    baseMediaPlayer.stop();
                    baseMediaPlayer.play(new VideoInfo(playUrl));
                }
//                ToastUtils.showToast(MediaPlayActivity.this,"play");
            }
        });
        baseMediaPlayer.addPlayerListener(new SimpleMediaPlayerListener() {

            @Override
            public void onStartPlay() {
                L.e(TAG,"onStartPlay");
            }

            @Override
            public void onLoadFailed() {
                L.e(TAG,"onLoadFailed");
            }

            // ...
        });

        mMediaPlayer=new MMediaPlayer(this);
        mMediaPlayer.setmOnMediaPlayerListener(this);
    }

    @Override
    public void onStart(String playUrl) {
        L.d(TAG,"onStart="+playUrl);
        if (mMediaPlayer.isPlaying()){
            isForceStop=true;
        }else {
            isForceStop=false;
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        L.d(TAG,"onError="+what);
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        L.d(TAG,"onInfo="+what);
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
//        mDialog.show();
            if (isForceStop){
                isForceStop=false;
            }else {
                mp.start();
            }
        L.d(TAG,"onPrepared");
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {
        L.d(TAG,"onSeekComplete");
        mMediaPlayer.stop();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        L.d(TAG,"onBufferingUpdate percent="+percent);
        mp.seekTo(percent);
    }

    @Override
    public void onTimedText(MediaPlayer mp, TimedText text) {
        L.d(TAG,"onTimedText");
    }

    @Override
    public void onTimedMetaDataAvailable(MediaPlayer mp, TimedMetaData data) {
        L.d(TAG,"onTimedMetaDataAvailable");
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        L.d(TAG,"onVideoSizeChanged");
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        L.d(TAG,"onCompletion"+"***************************************en********************");
        if (mDialog.isShowing())
            mDialog.dismiss();
    }
}
