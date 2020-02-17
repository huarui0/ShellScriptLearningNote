/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android_kotlin.observability.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
// import com.example.android.observability.Injection
/** 更改为目录 android_kotlin 中的 observability **/
import com.example.android_kotlin.observability.Injection
// import com.example.android.observability.R
import com.lzsoft.lzdata.mobile.purex.R

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
// 以下需要修改为对应的 Activity
// import kotlinx.android.synthetic.main.activity_user.update_user_button
// import kotlinx.android.synthetic.main.activity_user.user_name
// import kotlinx.android.synthetic.main.activity_user.user_name_input

import kotlinx.android.synthetic.main.activity_rxjava_sample_user_kotlin.update_user_button
import kotlinx.android.synthetic.main.activity_rxjava_sample_user_kotlin.user_name
import kotlinx.android.synthetic.main.activity_rxjava_sample_user_kotlin.user_name_input

/**
 * Main screen of the app. Displays a user name and gives the option to update the user name.
 */
class UserActivityKotlin : AppCompatActivity() {

    private lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: UserViewModel by viewModels { viewModelFactory }

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava_sample_user_kotlin)

        // 由于默认的字体颜色为白色，在这里需要更改 TextView 的字体颜色
        user_name.setTextColor(Color.BLACK);
        // 由于默认的字体颜色为白色，在这里需要更改 EditText 的字体颜色
        user_name_input.setTextColor(Color.BLUE);

        viewModelFactory = Injection.provideViewModelFactory(this)
        update_user_button.setOnClickListener { updateUserName() }
    }

    override fun onStart() {
        super.onStart()
        // Subscribe to the emissions of the user name from the view model.
        // Update the user name text view, at every onNext emission.
        // In case of error, log the exception.
        disposable.add(viewModel.userName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.user_name.text = it },
                        { error -> Log.e(TAG, "Unable to get username", error) }))
    }

    override fun onStop() {
        super.onStop()

        // clear all the subscription
        disposable.clear()
    }

    private fun updateUserName() {
        val userName = user_name_input.text.toString()
        // Disable the update button until the user name update has been done
        update_user_button.isEnabled = false
        // Subscribe to updating the user name.
        // Enable back the button once the user name has been updated
        disposable.add(viewModel.updateUserName(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ update_user_button.isEnabled = true },
                        { error -> Log.e(TAG, "Unable to update username", error) }))
    }

    companion object {
        private val TAG = UserActivityKotlin::class.java.simpleName
    }
}
