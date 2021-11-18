package com.cursosant.android.userssp

/****
 * Project: Users SP
 * From: com.cursosant.android.userssp
 * Created by Alain Nicolás Tello on 20/11/20 at 14:41
 * Course: Android Practical with Kotlin from zero.
 * All rights reserved 2021.
 *
 * All my Courses(Only on Udemy):
 * https://www.udemy.com/user/alain-nicolas-tello/
 ***/
interface OnClickListener {
    fun onClick(user: User, position: Int)
}