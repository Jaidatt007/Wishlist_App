package com.example.wishlistapplication.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Wishlist_Table")
data class wishlist_table_entity(

    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,

    @ColumnInfo(name = "Wish_Title")
    val title : String,

    @ColumnInfo(name = "Wish_Description")
    val description : String,

    @ColumnInfo(name = "Wish_Created_Time")
    val time : String,
)
