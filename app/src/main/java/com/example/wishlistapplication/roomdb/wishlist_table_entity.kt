package com.example.wishlistapplication.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Wishlist_Table")
data class wishlist_table_entity(

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L,

    @ColumnInfo(name = "Wish_Title")
    var title : String,

    @ColumnInfo(name = "Wish_Description")
    var description : String,

    @ColumnInfo(name = "Wish_Created_Time")
    var time : Long,

    @ColumnInfo(name = "User_Token")
    var userToken : String,

    @ColumnInfo(name = "Check_Box_State")
    var checkBoxState : Boolean = false,

    @ColumnInfo(name = "Wish_Reminder_Time")
    var reminderTime : String = ""
)
