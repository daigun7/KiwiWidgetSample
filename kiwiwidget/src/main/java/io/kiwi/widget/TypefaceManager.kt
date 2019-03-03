package io.kiwi.widget

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface

class TypefaceManager private constructor(context: Context) {
    private val mAssetManager: AssetManager = context.assets
    private val mNanumBarunGothicRegularPath: String = context.getString(R.string.font_nanum_barun_gothic_regular)
    private val mNanumBarunGothicBoldPath: String = context.getString(R.string.font_nanum_barun_gothic_bold)
    private val mNanumBarunGothicLightPath: String = context.getString(R.string.font_nanum_barun_gothic_light)
    private val mNanumBarunGothicUltraLightPath: String = context.getString(R.string.font_nanum_barun_gothic_ultra_light)
    private val mNanumSquareRegularPath: String = context.getString(R.string.font_nanum_square_regular)
    private val mNanumSquareBoldPath: String = context.getString(R.string.font_nanum_square_bold)

    val nanumBarunGothicRegular: Typeface by lazy { Typeface.createFromAsset(mAssetManager, mNanumBarunGothicRegularPath) }
    val nanumBarunGothicBold: Typeface by lazy { Typeface.createFromAsset(mAssetManager, mNanumBarunGothicBoldPath) }
    val nanumBarunGothicLight: Typeface by lazy { Typeface.createFromAsset(mAssetManager, mNanumBarunGothicLightPath) }
    val nanumBarunGothicUltraLight: Typeface by lazy { Typeface.createFromAsset(mAssetManager, mNanumBarunGothicUltraLightPath) }
    val nanumSquareRegular: Typeface by lazy { Typeface.createFromAsset(mAssetManager, mNanumSquareRegularPath) }
    val nanumSquareBold: Typeface by lazy { Typeface.createFromAsset(mAssetManager, mNanumSquareBoldPath) }

    companion object {
        private var mInstance: TypefaceManager? = null

        @Synchronized
        fun getInstance(context: Context): TypefaceManager {
            if(mInstance == null) {
                mInstance = TypefaceManager(context)
            }

            return mInstance as TypefaceManager
        }
    }
}