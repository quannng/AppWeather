package com.android.duongdb.weather.helpers;

import com.android.duongdb.weather.R;

import java.util.Random;


public class ImageHelper {
    public static int getIconIdFromCode(int code) {
        switch (code) {
            case 0:
                return R.drawable.icon_0;
            case 1:
                return R.drawable.icon_1;
            case 2:
                return R.drawable.icon_2;
            case 3:
                return R.drawable.icon_3;
            case 4:
                return R.drawable.icon_4;
            case 5:
                return R.drawable.icon_5;
            case 6:
                return R.drawable.icon_6;
            case 7:
                return R.drawable.icon_7;
            case 8:
                return R.drawable.icon_8;
            case 9:
                return R.drawable.icon_9;
            case 10:
                return R.drawable.icon_10;
            case 11:
                return R.drawable.icon_11;
            case 12:
                return R.drawable.icon_12;
            case 13:
                return R.drawable.icon_13;
            case 14:
                return R.drawable.icon_14;
            case 15:
                return R.drawable.icon_15;
            case 16:
                return R.drawable.icon_16;
            case 17:
                return R.drawable.icon_17;
            case 18:
                return R.drawable.icon_18;
            case 19:
                return R.drawable.icon_19;
            case 20:
                return R.drawable.icon_20;
            case 21:
                return R.drawable.icon_21;
            case 22:
                return R.drawable.icon_22;
            case 23:
                return R.drawable.icon_23;
            case 24:
                return R.drawable.icon_24;
            case 25:
                return R.drawable.icon_25;
            case 26:
                return R.drawable.icon_26;
            case 27:
                return R.drawable.icon_27;
            case 28:
                return R.drawable.icon_28;
            case 29:
                return R.drawable.icon_29;
            case 30:
                return R.drawable.icon_30;
            case 31:
                return R.drawable.icon_31;
            case 32:
                return R.drawable.icon_32;
            case 33:
                return R.drawable.icon_33;
            case 34:
                return R.drawable.icon_34;
            case 35:
                return R.drawable.icon_35;
            case 36:
                return R.drawable.icon_36;
            case 37:
                return R.drawable.icon_37;
            case 38:
                return R.drawable.icon_38;
            case 39:
                return R.drawable.icon_39;
            case 40:
                return R.drawable.icon_40;
            case 41:
                return R.drawable.icon_41;
            case 42:
                return R.drawable.icon_42;
            case 43:
                return R.drawable.icon_43;
            case 44:
                return R.drawable.icon_44;
            case 45:
                return R.drawable.icon_45;
            case 46:
                return R.drawable.icon_46;
            case 47:
                return R.drawable.icon_47;
            default:
                return R.drawable.icon_na;
        }
    }

    public static int getBackgroundNameFromCode(int code){
        switch (code){
            case 0: return 1;
            case 1: return 1;
            case 2: return 1;
            case 3: return 1;
            case 4: return 1;
            case 5: return 4;
            case 6: return 4;
            case 7: return 4;
            case 8: return 8;
            case 9: return 8;
            case 10: return 8;
            case 11: return 8;
            case 12: return 8;
            case 13: return 4;
            case 14: return 4;
            case 15: return 4;
            case 16: return 4;
            case 17: return 1;
            case 18: return 4;
            case 19: return 0;
            case 20: return 0;
            case 21: return 0;
            case 22: return 0;
            case 23: return 3;
            case 24: return 3;
            case 25: return 0;
            case 26: return 0;
            case 27: return 5;
            case 28: return 7;
            case 29: return 5;
            case 30: return 2;
            case 31: return 5;
            case 32: return 2;
            case 33: return 5;
            case 34: return 2;
            case 35: return 1;
            case 36: return 2;
            case 37: return 7;
            case 38: return 7;
            case 39: return 7;
            case 40: return 8;
            case 41: return 7;
            case 42: return 4;
            case 43: return 4;
            case 44: return 6;
            case 45: return 8;
            case 46: return 8;
            case 47: return 8;
            default: return 6;
        }
    }
    
    public static int getBackgroundIdFromCode(int code){
        int myId = getBackgroundNameFromCode(code);
        switch (myId){
            case 0: return getBackgroundRandom(cloud);
            case 1: return getBackgroundRandom(lightning);
            case 2: return getBackgroundRandom(sunny);
            case 3: return getBackgroundRandom(windy);
            case 4: return getBackgroundRandom(snow);
            case 5: return getBackgroundRandom(night);
            case 6: return getBackgroundRandom(fog);
            case 7: return getBackgroundRandom(sunny_cloud);
            case 8: return getBackgroundRandom(rain);
            default: return getBackgroundRandom(windy);
        }
    }
    public static int getBackgroundRandom(int[] imageArrays){
        int count = imageArrays.length;
        Random random = new Random();
        return imageArrays[random.nextInt(count)];
    }
    public static int getBackgroundIdNextFromCode(int code, int drawableId){
        int myId = getBackgroundNameFromCode(code);
        switch (myId){
            case 0: return getBackgroundNext(cloud, drawableId);
            case 1: return getBackgroundNext(lightning, drawableId);
            case 2: return getBackgroundNext(sunny, drawableId);
            case 3: return getBackgroundNext(windy, drawableId);
            case 4: return getBackgroundNext(snow, drawableId);
            case 5: return getBackgroundNext(night, drawableId);
            case 6: return getBackgroundNext(fog, drawableId);
            case 7: return getBackgroundNext(sunny_cloud, drawableId);
            case 8: return getBackgroundNext(rain, drawableId);
            default: return getBackgroundNext(windy, drawableId);
        }
    }
    public static int getBackgroundNext(int[] imageArrays, int drawableId){
        int position = 0;
        for(int i = 0; i < imageArrays.length; i++){
            if(imageArrays[i] == drawableId){
                position = i;
                break;
            }
        }
        position = (position +1) % imageArrays.length;
        return imageArrays[position];
    }

    public static int[] cloud = {
            R.drawable.bg_cloud,
            R.drawable.bg_cloud1,
            R.drawable.bg_cloud2,
            R.drawable.bg_cloud3};
    public static int[] lightning = {
            R.drawable.bg_lightning,
            R.drawable.bg_lightning1,
            R.drawable.bg_lightning2};
    public static int[] sunny = {
            R.drawable.bg_sunny,
            R.drawable.bg_sunny1,
            R.drawable.bg_sunny2,
            R.drawable.bg_sunny3};
    public static int[] windy = {
            R.drawable.bg_windy,
            R.drawable.bg_windy1};
    public static int[] rain = {
            R.drawable.bg_rain,
            R.drawable.bg_rain1,
            R.drawable.bg_rain2};
    public static int[] snow = {
            R.drawable.bg_snow,
            R.drawable.bg_snow1,
            R.drawable.bg_snow2};
    public static int[] night = {
            R.drawable.bg_night,
            R.drawable.bg_night2};
    public static int[] fog = {
            R.drawable.bg_fog};
    public static int[] sunny_cloud = {
            R.drawable.bg_sunny_mixed_cloud};
}
