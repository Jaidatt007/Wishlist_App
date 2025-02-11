package com.example.wishlistapplication.screens

import AlarmScheduler
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlistapplication.R
import com.example.wishlistapplication.resources.UserTokenManager
import com.example.wishlistapplication.resources.extractDate
import com.example.wishlistapplication.resources.extractTime
import com.example.wishlistapplication.resources.getCurrentAMPM
import com.example.wishlistapplication.resources.getCurrentDate
import com.example.wishlistapplication.resources.getDateFromMillis
import com.example.wishlistapplication.resources.getCurrentDateTime
import com.example.wishlistapplication.resources.getCurrentDayFromDate
import com.example.wishlistapplication.resources.getCurrentHourFromMillis
import com.example.wishlistapplication.resources.getCurrentMinuteFromMillis
import com.example.wishlistapplication.resources.getDateAndTimeString
import com.example.wishlistapplication.resources.getMillisecondsDifference
import com.example.wishlistapplication.resources.getTimeDifferenceInMillisForAlarm
import com.example.wishlistapplication.resources.getTimeInFuture
import com.example.wishlistapplication.resources.getTimeInMillisFromTimePickerState
import com.example.wishlistapplication.resources.getTimerAfterAddingMinutes
import com.example.wishlistapplication.roomdb.wishlist_table_entity
import com.example.wishlistapplication.routes.Routes
import com.example.wishlistapplication.units.Top_app_bar
import com.example.wishlistapplication.viewmodel.WishViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditDetailsScreen(id:Long,
                         modifier: Modifier,
                         navController: NavController,
                         wishViewModel: WishViewModel,
                         alarmContext : Context
){

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val context = LocalContext.current

    var operationState by remember { mutableStateOf("") }

    operationState = if (id != 0L) "Edit" else "Add"

    val currentTimeMillis by remember { mutableLongStateOf( System.currentTimeMillis()) }

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = currentTimeMillis)

    val timePickerState = rememberTimePickerState(
        initialHour = getCurrentHourFromMillis(currentTimeMillis),
        initialMinute = getCurrentMinuteFromMillis(currentTimeMillis+300000),
        is24Hour = false
    )

    val inputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    val alarmScheduler = AlarmScheduler(alarmContext)

    var setReminderState by remember { mutableStateOf(false) }

    var openDateSelector by remember { mutableStateOf(false) }
    var openTimeSelector by remember { mutableStateOf(false) }

    var selectedDateState by remember { mutableStateOf(getDateFromMillis(currentTimeMillis)) }
    var selectedTimeState by remember { mutableStateOf(getTimerAfterAddingMinutes(currentTimeMillis,5)) }

    if (operationState == "Edit"){
        val wish = wishViewModel.getAWishById(id).collectAsState(initial = wishlist_table_entity(0L,"","",0,"",false,""))
        title = wish.value.title
        description = wish.value.description
    }else{
        title = ""
        description = ""
    }


    Scaffold(modifier = modifier,
        topBar = {
            Top_app_bar(title = if(operationState == "Edit") "Edit Wish" else "Add Wish",
                icon = R.drawable.baseline_arrow_back_24 ,
                onIconClick = {
                    navController.navigate(Routes.homeScreen)
                })
        }
    ) { innerPadding->
        Column(modifier = Modifier.padding(innerPadding).padding(start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                value = title,
                label = {
                    Text(text = "Title")
                        },
                onValueChange = {
                    title=it
                }
            )
            OutlinedTextField(modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                value = description,
                label = {
                    Text(text = "Description")
                        },
                onValueChange = {
                    description=it
                }
            )

            Button(
                modifier = Modifier.padding(top = 20.dp, bottom = 4.dp)
                    .height(36.dp)
                    .width(132.dp)
                    .border(border = BorderStroke(1.dp,ButtonDefaults.buttonColors().containerColor), shape = RoundedCornerShape(100))
                    .padding(vertical = 0.dp),
                onClick = {
                    setReminderState = !setReminderState
                },
                enabled = true,
                colors = if (!setReminderState)  ButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = ButtonDefaults.buttonColors().contentColor
                ) else ButtonDefaults.buttonColors()
            ) {
                Text("Set Reminder ?", fontSize = 12.sp)
            }

            Row(modifier = Modifier.fillMaxWidth()){

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth()
                        .weight(1f)
                        .height(56.dp)
                        .padding(start = 32.dp, end = 4.dp),
                    value = selectedDateState,
                    readOnly = true,
                    enabled = setReminderState,
                    textStyle = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center),
                    label = {
                        Text(text = getCurrentDayFromDate(selectedDateState).uppercase(), fontSize = 10.sp)
                    },
                    onValueChange = {
                        selectedDateState=it
                    },
                    trailingIcon = {
                        Icon(painter = painterResource(R.drawable.baseline_calendar_month_24),"Date",
                        modifier = if(setReminderState) Modifier.clickable {
                            Log.d("Calender","Tapped")
                            openDateSelector = true
                        } else Modifier )
                    }
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth()
                        .weight(1f)
                        .height(56.dp)
                        .padding(end = 32.dp, start = 4.dp),
                    value = selectedTimeState,
                    readOnly = true,
                    enabled = setReminderState,
                    textStyle = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center),
                    label = {
                        Text(text = getCurrentAMPM().uppercase(), fontSize = 10.sp)
                    },
                    onValueChange = {
                        selectedTimeState=it
                    },
                    trailingIcon = {
                        Icon(painter = painterResource(R.drawable.baseline_access_time_24), contentDescription = "Time",
                            modifier = if(setReminderState) Modifier.clickable {
                                Log.d("Clock","Tapped")
                                openTimeSelector = true
                            } else Modifier )
                    }
                )
            }


            Row(modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {
                    title=""
                    description=""
                    selectedDateState = getCurrentDate()
                    selectedTimeState = getTimerAfterAddingMinutes(5)
                    Toast.makeText(context,"Values Resetted !",Toast.LENGTH_SHORT).show()
                }
                ) {
                    Text(text = "Reset", fontSize = 16.sp, modifier = Modifier.padding(4.dp))
                    Icon(painter = painterResource(R.drawable.baseline_refresh_24), contentDescription = "Reset values",
                        modifier = Modifier.size(24.dp))
                }
                Button(onClick = {
                    if(title.isNotEmpty()){
                        val reminderDateAndTime = getDateAndTimeString(selectedDateState,selectedTimeState)
                        val alarmInMillis = getTimeDifferenceInMillisForAlarm(reminderDateAndTime)
                        if(operationState == "Add"){
                            wishViewModel.addAWish(wish = wishlist_table_entity(title = title, description = description,
                                time = currentTimeMillis , userToken = UserTokenManager.userToken.toString(),
                                reminderTime = if(setReminderState) reminderDateAndTime else ""
                            ))
                            if(setReminderState){
                                alarmScheduler.setAlarm(alarmInMillis)
                                Log.d("Alarm at : ","$alarmInMillis")
                            }
                        } else {
                            wishViewModel.updateAWish(wishlist_table_entity(id = id, title = title, description = description,
                                time = currentTimeMillis , userToken = UserTokenManager.userToken.toString(),
                                reminderTime = if(setReminderState) reminderDateAndTime else ""
                            ))
                            if(setReminderState){
                                alarmScheduler.setAlarm(alarmInMillis)
                                Log.d("Alarm at : ","$alarmInMillis")
                            }
                        }
                        navController.navigateUp()
                        Toast.makeText(context,if(operationState == "Edit") "Wish Updated !" else "Wish Added !",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context,"Title cannot be empty !",Toast.LENGTH_SHORT).show()
                    }
                }
                ) {
                    Text(text = if(operationState == "Edit") "Edit Wish" else "Add Wish" , fontSize = 16.sp, modifier = Modifier.padding(4.dp))
                    Icon(painter = painterResource(R.drawable.baseline_edit_24),"Add/Edit data",
                        modifier = Modifier.size(22.dp))
                }
            }

        }
        Column(modifier = modifier.then(Modifier.fillMaxSize())) {
            if (openDateSelector) {
                DatePickerDialog(
                    onDismissRequest = {
                        openDateSelector = false
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            val selectedMillis = datePickerState.selectedDateMillis
                            if (selectedMillis != null && selectedMillis >= currentTimeMillis) {
                                selectedDateState = getDateFromMillis(selectedMillis)
                            }else{
                                Toast.makeText(context,"Please choose Right date",Toast.LENGTH_SHORT).show()
                                selectedDateState = getDateFromMillis(currentTimeMillis)
                            }
                            openDateSelector = false
                        }) {
                            Text(text = "Ok")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            openDateSelector = false
                        }) {
                            Text(text = "Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState,
                        title = { Text(text = "Select Reminder Date : ", fontSize = 16.sp, modifier = Modifier.padding(start = 24.dp, top = 16.dp)) }
                    )
                }
            }
            if(openTimeSelector){
                AlertDialog(
                    modifier = Modifier
                        .clip(RoundedCornerShape(28.dp))
                        .background(Color.Cyan),
                    onDismissRequest = {
                        openTimeSelector = false
                    }
                ) {
                    Column(modifier = Modifier.padding(20.dp)
                        .weight(1f),
                        verticalArrangement = Arrangement.Center) {
                        Text(text = "Select Reminder Time : ", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(24.dp))
                        TimePicker(state = timePickerState)
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            TextButton(onClick = {
                                openTimeSelector = false
                            }) {
                                Text(text = "Cancel")
                            }
                            TextButton(onClick = {

                                val selectedMillis = getTimeInMillisFromTimePickerState(timePickerState = timePickerState)

                                if (selectedMillis >= currentTimeMillis) {
                                    val formattedHour = if (timePickerState.hour % 12 == 0) 12 else timePickerState.hour % 12
                                    val formattedMinute = String.format("%02d", timePickerState.minute)
                                    val amPm = if (timePickerState.hour >= 12) "PM" else "AM"

                                    val timeInStr = "$formattedHour:$formattedMinute $amPm"
                                    selectedTimeState = timeInStr
                                } else {
                                    selectedTimeState = getTimeInFuture(selectedTimeState)
                                    Toast.makeText(context, "Please choose the right time", Toast.LENGTH_SHORT).show()
                                }

                                openTimeSelector = false
                            }) {
                                Text(text = "Ok")
                            }

                        }
                    }
                }
            }
        }
    }
}
