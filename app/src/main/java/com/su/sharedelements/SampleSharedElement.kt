package com.su.sharedelements

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.su.sharedelements.ui.theme.Purple40
import com.su.sharedelements.ui.theme.Purple80

/* what we need
  * AnimatedContent with SharedTransicationLayout in order to make Shared Element anim
  * Then passed them into MainContent and DetailsContent
  * */

@Preview
@Composable
fun SampleSharedElement(modifier: Modifier = Modifier) {

    var showDetail by remember {
        mutableStateOf(true)
    }

    AnimatedContent(showDetail, label = "basic") { targetScope ->
        if (!targetScope) {
            MainContent(
                modifier = modifier,
                onShowDetail = { showDetail = !showDetail }
            )
        } else {
            DetailsContent(
                modifier = modifier,
                onBack = { showDetail = !showDetail }
            )
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    onShowDetail: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .size(186.dp)
            .background(Purple40, CircleShape)
            .clickable { onShowDetail() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.strawberry),
            contentDescription = "strawberry",
            modifier = Modifier
                .size(160.dp)
                .padding(10.dp)
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsContent(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(
                Purple80,
                RoundedCornerShape(
                    bottomStart = 30.dp,
                    bottomEnd = 30.dp
                )
            )
            .clickable { onBack() }
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.strawberry),
            contentDescription = "strawberry",
            modifier = Modifier
                .size(200.dp)
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Strawberry",
            modifier = Modifier.padding(horizontal = 10.dp),
            fontSize = 21.sp
        )
        Text(
            text = "provides more vitamin C than an orange. " +
                    "Strawberries also have minerals such as calcium, " +
                    "iron, potassium, folate, and magnesium, along with antioxidants " +
                    "called anthocyanins. Those are healthful plant compounds " +
                    "that give strawberries their red color.",
            modifier = Modifier.padding(10.dp),
        )
    }
}

