package org.collcal.collcal.presentation.sign

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.platform.PlatformType
import org.collcal.collcal.platform.getPlatformType
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.presentation.sign.component.SignUpCollegeInfo
import org.collcal.collcal.presentation.sign.component.SignUpUserInfo
import org.collcal.collcal.presentation.ui.theme.Strings

@Composable
fun SignUpScreen(
    navigator: Navigator,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    viewModel: SignViewModel = SignViewModel(),
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val modifier = when (getPlatformType()) {
        PlatformType.WEB -> Modifier.fillMaxWidth(0.4f)
        PlatformType.ANDROID -> Modifier.fillMaxWidth(0.8f)
        PlatformType.IOS -> Modifier.fillMaxWidth(0.8f)
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        if (getPlatformType() == PlatformType.WEB) {
            Text(
                text = Strings.signUp,
                fontSize = 30.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.align(Alignment.TopStart).padding(start = 60.dp, top = 30.dp)
            )
        }

        AnimatedContent(
            targetState = selectedTabIndex,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInHorizontally(initialOffsetX = { it }) togetherWith slideOutHorizontally(
                        targetOffsetX = { -it })
                } else {
                    slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(
                        targetOffsetX = { it })
                }
            },
            label = "AnimatedContent"
        ) { targetState ->
            when (targetState) {
                0 -> SignUpUserInfo(modifier, viewModel) { selectedTabIndex++ }
                1 -> SignUpCollegeInfo(modifier, viewModel, navigator)
            }
        }
    }
}