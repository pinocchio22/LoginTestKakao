package com.example.logintestkakao

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-07-13
 * @desc
 */
class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "c308aa956a47aab3dea6e74e98b5a3ed")
    }
}
