package com.example.update.screen.component


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cu.xetid.dtvc.androidtrainingapp.model.dto.Contact


@Composable
fun GenericContactAccountImage(
    contact: Contact,
    modifier: Modifier = Modifier,
    contactImageSize: Int = 80
) {
    Box(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .size(contactImageSize.dp)
            .background(
                if (contact.fontNumber[1] == '1') Color.Gray
                else if (contact.fontNumber[1] == '2') Color.Blue
                else if (contact.fontNumber[1] == '3') Color.Red
                else if (contact.fontNumber[1] == '4') Color.Yellow
                else if (contact.fontNumber[1] == '5') Color.Green
                else if (contact.fontNumber[1] == '6') Color.Black
                else if (contact.fontNumber[1] == '7') Color.Cyan
                else if (contact.fontNumber[1] == '8') Color.DarkGray
                else if (contact.fontNumber[1] == '9') Color.LightGray
                else Color.Red
            ).border(width = 1.dp, color =  MaterialTheme.colorScheme.tertiary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = contact.firstName[0].toString(),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            textAlign = TextAlign.Center,
            fontSize =56.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}


