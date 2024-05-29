package com.example.Doctor.presentation.HomeScreen.calender

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Doctor.R
import com.example.Doctor.ui.theme.MyDoctorTheme
import java.time.YearMonth

/**
 * Created by meyta.taliti on 20/05/23.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun CalendarAppPreview() {
    MyDoctorTheme {
        CalendarApp()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarApp(
    viewModel: CalendarViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    CalendarWidget(
        days = DateUtil.daysOfWeek,
        yearMonth = uiState.yearMonth,
        dates = uiState.dates,
        onPreviousMonthButtonClicked = { prevMonth ->
            viewModel.toPreviousMonth(prevMonth)
        },
        onNextMonthButtonClicked = { nextMonth ->
            viewModel.toNextMonth(nextMonth)
        },
        onDateClickListener = {
            // TODO("set on date click listener")
        }
    )

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarWidget(
    days: Array<String>,
    yearMonth: YearMonth,
    dates: List<CalendarUiState.Date>,
    onPreviousMonthButtonClicked: (YearMonth) -> Unit,
    onNextMonthButtonClicked: (YearMonth) -> Unit,
    onDateClickListener: (CalendarUiState.Date) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 10.dp)
    ) {
        Header(
            yearMonth = yearMonth,
            onPreviousMonthButtonClicked = onPreviousMonthButtonClicked,
            onNextMonthButtonClicked = onNextMonthButtonClicked
        )
        Row {
            repeat(days.size) {
                val item = days[it]
                DayItem(item, modifier = Modifier.weight(1f))
            }
        }
        Content(
            dates = dates,
            onDateClickListener = onDateClickListener
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Header(
    yearMonth: YearMonth,
    onPreviousMonthButtonClicked: (YearMonth) -> Unit,
    onNextMonthButtonClicked: (YearMonth) -> Unit,
) {
    Row {
        IconButton(onClick = {
            onPreviousMonthButtonClicked.invoke(yearMonth.minusMonths(1))
        }) {

            Icon(
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_left_24),
                contentDescription = null,
                modifier = Modifier.size(35.dp)
                    ,
                tint = colorResource(id = R.color.darkBlue)
            )
        }
        Text(
            text = yearMonth.getDisplayName(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            color = colorResource(id = R.color.darkBlue),
            fontWeight = FontWeight.SemiBold
        )
        IconButton(onClick = {
            onNextMonthButtonClicked.invoke(yearMonth.plusMonths(1))
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp),
                tint = colorResource(id = R.color.darkBlue)
            )
        }
    }
}

@Composable
fun DayItem(day: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            text = day,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.DarkblueGrey),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(6.dp)
        )
    }
}

@Composable
fun Content(
    dates: List<CalendarUiState.Date>,
    onDateClickListener: (CalendarUiState.Date) -> Unit,
) {
    Column {
        var index = 0
        var selectedRow by rememberSaveable {
            mutableStateOf(0)
        }
        val listOfRows = (1..99).toList()
        repeat(6) { columnIndex ->
            if (index >= dates.size) return@repeat
            Row {
                var selectedIndex by rememberSaveable {
                    mutableStateOf(0)
                }
                val listOfItems = (1..99).toList()
                repeat(7) {
                    val item = if (index < dates.size) dates[index] else CalendarUiState.Date.Empty
                    ContentItem(
                        date = item,
                        onClickListener = onDateClickListener,
                        modifier = Modifier.weight(1f),
                        isSelected = if(listOfItems[it] == selectedIndex&&listOfRows[columnIndex]==selectedRow)true else false,
                        onClick = {
                            selectedIndex = listOfItems[it]
                            selectedRow = listOfRows[columnIndex]
                        }
                    )
                    index++
                }
            }
        }
    }
}

@Composable
fun calenderRow(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit,
    index :Int
) {
    
}
@Composable
fun ContentItem(
    date: CalendarUiState.Date,
    onClickListener: (CalendarUiState.Date) -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val itembackgoundColor by animateColorAsState(
        targetValue =
        if (isSelected) {
            colorResource(id = R.color.splashBackgroundTr)
        } else if (date.isSelected) {
            Color.LightGray

        } else Color.Transparent
    )
    val itemTextColor by animateColorAsState(
        targetValue =
        if (isSelected) {
            Color.White
        } else colorResource(id = R.color.darkBlue)
    )

    Box(
        modifier = modifier
            .height(48.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(
                color =
                itembackgoundColor
            )
            .clickable(enabled = if (date.dayOfMonth.isBlank()) false else true, onClick = {
                //                onClickListener(date)
                onClick()
            })
    ) {
        Text(
            text = date.dayOfMonth,
            style = MaterialTheme.typography.titleMedium,
            color = itemTextColor,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(10.dp),
            fontWeight = FontWeight.SemiBold
        )
    }
}

