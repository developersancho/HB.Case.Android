package com.developersancho.hb.compose.features.detail.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersancho.hb.compose.app.theme.HBCaseTheme

@Composable
fun DetailTextRow(key: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = key,
            maxLines = 1,
            overflow = TextOverflow.Visible,
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Start
        )

        Text(
            text = value,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailTextRowPreview() {
    HBCaseTheme {
        LazyColumn {
            items(4) {
                DetailTextRow(key = "key", value = "value")
            }
        }
    }
}
