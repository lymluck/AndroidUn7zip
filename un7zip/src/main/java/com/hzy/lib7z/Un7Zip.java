package com.hzy.lib7z;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Un7Zip {

    /**
     * Extract 7z file
     * @param filePath src file path
     * @param outPath dst directory
     * @return status
     */
    public static boolean extract7z(String filePath, String outPath) {
        File outDir = new File(outPath);
        if (!outDir.exists() || !outDir.isDirectory()) {
            outDir.mkdirs();
        }
        return (Un7Zip.un7zip(filePath, outPath) == 0);
    }

    /**
     * Extract 7z file from assets
     *
     * @param context context
     * @param assetPath asset path
     * @param outPath dst directory
     * @return status
     */
    public static boolean extract7zFromAssets(Context context, String assetPath, String outPath) {
        File outDir = new File(outPath);
        if (!outDir.exists() || !outDir.isDirectory()) {
            outDir.mkdirs();
        }
        return (Un7Zip.un7zipFromAssets(context.getAssets(), assetPath, outPath) == 0);
    }

    //JNI interface
    public static native String getLzmaVersion();
    private static native int un7zip(String filePath, String outPath);
    private static native int un7zipFromAssets(AssetManager assetManager, String filePath, String outPath);

    static {
        System.loadLibrary("un7zip");
    }
}
