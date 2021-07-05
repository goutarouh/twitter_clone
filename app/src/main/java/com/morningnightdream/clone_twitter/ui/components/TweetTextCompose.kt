package com.morningnightdream.clone_twitter.ui.components

import android.os.TestLooperManager
import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import java.util.regex.Pattern

const val HASH_TAG = "HASH"

@Preview
@Composable
fun Test() {
    ComposeTweetFeedText(
        text = "abc #abc abc",
        modifier = Modifier,
        urlRecognizer = { Log.i("hasegawa", it) },
        hashTagNavigator = { Log.i("hasegawa", it) }) {
        Log.i("hasegawa", "click")
    }
}

@Composable
fun ComposeTweetFeedText(
    text: String,
    modifier: Modifier = Modifier,
    urlRecognizer: (url: String) -> Unit,
    hashTagNavigator: (String) -> Unit,
    textClick: () -> Unit
) {
    val uriHandler = LocalUriHandler.current
    val layoutResult = remember {
        mutableStateOf<TextLayoutResult?>(null)
    }

    val linksList = extractSpans(text, listOf(hashTagPattern))
    val annotatedString = buildAnnotatedString {
        append(text)
        linksList.forEach {
            addStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                ),
                start = it.start,
                end = it.end
            )
            addStringAnnotation(
                tag = it.tag,
                annotation = it.spanText,
                start = it.start,
                end = it.end
            )
        }
    }
    Text(
        text = annotatedString,
        style = MaterialTheme.typography.body1,
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures { offsetPosition ->
                layoutResult.value?.let {
                    val position = it.getOffsetForPosition(offsetPosition)
                    annotatedString.getStringAnnotations(position, position).firstOrNull()
                        ?.let { result ->
                            when (result.tag) {
                                HASH_TAG -> {
                                    hashTagNavigator(result.item)
                                } else -> {
                                    textClick.invoke()
                                }
                            }
                        }
                }
            }
        },
        onTextLayout = {
            layoutResult.value = it
        }
    )
}

private val hashTagPattern = Pattern.compile(".*?\\s(#\\w+).*?")

fun extractSpans(text: String, patterns: List<Pattern>): List<SpanInfos> {
    val links = arrayListOf<SpanInfos>()
    patterns.forEach { pattern ->
        val matcher = pattern.matcher(text)
        var matchStart: Int
        var matchEnd: Int

        while (matcher.find()) {
            matchStart = matcher.start(1)
            matchEnd = matcher.end()

            val checkText = text.substring(matchStart, matchEnd)
            if (checkText.startsWith("#")) {
                links.add(SpanInfos(checkText, matchStart, matchEnd, HASH_TAG))
            }
        }

    }
    return links
}

data class SpanInfos(
    val spanText: String,
    val start: Int,
    val end: Int,
    val tag: String
)