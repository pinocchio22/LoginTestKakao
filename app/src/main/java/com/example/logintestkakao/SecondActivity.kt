package com.example.logintestkakao

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.logintestkakao.databinding.ActivitySecondBinding
import com.kakao.sdk.user.UserApi
import com.kakao.sdk.user.UserApiClient

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        UserApiClient.instance.me { user, _ ->
            binding.id.text = "회원번호: ${user?.id}"
            binding.nickname.text = "닉네임: ${user?.kakaoAccount?.profile?.nickname}"
            binding.profileimageUrl.text = "프로필 링크:" +
                    "${user?.kakaoAccount?.profile?.profileImageUrl}"
            binding.thumbnailimageUrl.text = "썸네일 링크:" +
                    "${user?.kakaoAccount?.profile?.thumbnailImageUrl}"
        }

        binding.kakaoLogoutButton.setOnClickListener{
            UserApiClient.instance.logout { error ->
                if(error != null) {
                    Toast.makeText(this, "로그아웃 실패 #$error",
                    Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
            }
        }

        binding.kakaoUnlinkButton.setOnClickListener{
            UserApiClient.instance.unlink { error ->
                if (error != null) {
                    Toast.makeText(this, "회원 탈퇴 실패 $error",
                    Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "회원 탈퇴 성공", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
                }
            }
        }
    }
}