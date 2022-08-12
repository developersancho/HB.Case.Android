package com.developersancho.hb.compose.features.search.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersancho.hb.compose.R
import com.developersancho.hb.compose.app.theme.HBCaseTheme

@Composable
fun SearchTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.White
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search_bar_small),
                contentDescription = stringResource(R.string.hint_search),
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp),
                tint = Color.White
            )
        },
        trailingIcon = {
            AnimatedVisibility(
                visible = value.text.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(
                    onClick = { onValueChange(TextFieldValue()) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close_circle_red_tint),
                        contentDescription = stringResource(R.string.text_clear),
                        tint = Color.White
                    )
                }
            }
        },
        placeholder = { Text(text = hint, color = colorResource(id = R.color.red_tint_20)) },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = 1,
        singleLine = true,
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HBCaseTheme {
        Box(
            Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.red_primary))
        ) {
            var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
            SearchTextField(
                value = searchQuery, onValueChange = { value ->
                    searchQuery = value
                },
                hint = stringResource(R.string.hint_search),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .background(
                        color = colorResource(id = R.color.red_shade_20),
                        shape = RoundedCornerShape(percent = 10)
                    )
            )
        }
    }
}