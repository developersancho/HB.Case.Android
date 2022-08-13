package com.developersancho.hb.compose.features.search.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.developersancho.data.model.dto.SearchItemDto
import com.developersancho.framework.extensions.toReleaseDate
import com.developersancho.hb.compose.R
import com.developersancho.hb.compose.app.theme.HBCaseTheme

@Composable
fun SearchRow(
    dto: SearchItemDto,
    onItemClick: () -> Unit = {}
) {
    Card(
        onClick = onItemClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
            .padding(
                vertical = 4.dp,
                horizontal = 4.dp
            ),
        elevation = 4.dp
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(dto.artworkUrl100)
                    .scale(Scale.FIT)
                    .error(R.drawable.ic_error_image)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
            )
            Text(
                text = dto.collectionName.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                style = MaterialTheme.typography.subtitle2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${dto.collectionPrice}-${dto.currency}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                style = MaterialTheme.typography.body2
            )
            Text(
                text = dto.releaseDate.toString().toReleaseDate(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchRowPreview() {
    HBCaseTheme {
        SearchRow(dto = SearchItemDto.init())
    }
}