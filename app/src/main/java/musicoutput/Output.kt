package musicoutput

import android.media.AudioFormat
import android.media.AudioTrack
import android.media.AudioFormat.ENCODING_PCM_16BIT
import android.media.AudioFormat.CHANNEL_OUT_STEREO
import android.media.AudioManager



/**
 * Created by basaile92 on 08/11/2017.
 */

class Output{


    //Ce qui est dans companion object est static
    companion object {
        fun generateTone(freqHz: Double, durationMs: Int): AudioTrack {
            val count = (44100.0 * 2.0 * (durationMs / 1000.0)).toInt() and 1.inv()
            val samples = ShortArray(count)
            var i = 0
            while (i < count) {
                val sample = (Math.sin(2.0 * Math.PI * i.toDouble() / (44100.0 / freqHz)) * 0x7FFF).toShort()
                samples[i + 0] = sample
                samples[i + 1] = sample
                i += 2
            }
            val track = AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
                    count * (java.lang.Short.SIZE / 8), AudioTrack.MODE_STATIC)
            track.write(samples, 0, count)
            return track
        }
    }

    /* On leur demande de traduire ce java en kotlin grace à l'IDE.

    public class Output{
        private static AudioTrack generateTone(double freqHz, int durationMs)
        {
            int count = (int)(44100.0 * 2.0 * (durationMs / 1000.0)) & ~1;
            short[] samples = new short[count];
            for(int i = 0; i < count; i += 2){
                short sample = (short)(Math.sin(2 * Math.PI * i / (44100.0 / freqHz)) * 0x7FFF);
                samples[i + 0] = sample;
                samples[i + 1] = sample;
            }
            AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
                count * (Short.SIZE / 8), AudioTrack.MODE_STATIC);
            track.write(samples, 0, count);
            return track;
        }
     }
     */

}