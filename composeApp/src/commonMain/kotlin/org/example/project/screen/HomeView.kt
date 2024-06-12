package org.example.project.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.Headphones
import compose.icons.evaicons.fill.Monitor
import compose.icons.evaicons.fill.Radio
import compose.icons.evaicons.outline.Headphones
import compose.icons.evaicons.outline.Monitor
import compose.icons.evaicons.outline.Radio
import io.github.alexzhirkevich.compottie.LottieAnimation
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.LottieConstants
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.example.project.data.navigation.Routes


enum class AppPages(val title: String, val image: ImageVector, val selectImage: ImageVector) {
    PAGE_ONE("游戏", EvaIcons.Outline.Monitor, EvaIcons.Fill.Monitor),
    PAGE_TWO("发现", EvaIcons.Outline.Radio, EvaIcons.Fill.Radio),
    PAGE_THEE("音乐", EvaIcons.Outline.Headphones, EvaIcons.Fill.Headphones)
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView() {

    val pagerState = rememberPagerState(pageCount = { AppPages.entries.size }, initialPage = AppPages.PAGE_TWO.ordinal)
    val coroutineScope = rememberCoroutineScope()

    androidx.compose.material3.Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            // 此处顶部栏样式，可扩展为 -> 自定义+TabRow+自定义
            androidx.compose.material3.TabRow(selectedTabIndex = pagerState.currentPage) {
                AppPages.entries.forEachIndexed { index, page ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = { androidx.compose.material3.Text(page.title, fontSize = 12.sp) },
                        icon = {
                            Icon(
                                imageVector = if (pagerState.currentPage == index) page.selectImage else page.image,
                                "pageIcon",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                }
            }
            HorizontalPager(state = pagerState) { page ->
                when (AppPages.entries[page]) {
                    AppPages.PAGE_ONE -> PageOneContent()
                    AppPages.PAGE_TWO -> PageTwoContent()
                    AppPages.PAGE_THEE -> PageThrContent()
                }
            }
        }
    }


}


@Composable
fun PageOneContent() {
    // Your content for Page One here
    Box(modifier = Modifier.fillMaxSize().background(Color.Gray)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("This is Page One")

        }

    }
}

@Composable
fun PageTwoContent() {
    // Your content for Page Two here
    Box(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("This is Page Two")
            Loader()
        }
    }
}

@Composable
fun PageThrContent() {
    // Your content for Page Two here
    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
        androidx.compose.material3.Text("This is Page Thr", modifier = Modifier.align(Alignment.Center))
    }
}

private const val lottieData = """
{"v":"4.8.0","meta":{"g":"LottieFiles AE ","a":"","k":"","d":"","tc":""},"fr":60,"ip":0,"op":120,"w":1024,"h":1024,"nm":"BIRD","ddd":0,"assets":[],"layers":[{"ddd":0,"ind":1,"ty":4,"nm":"beanie ","parent":6,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":75,"s":[0.083]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":93,"s":[17.314]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":110,"s":[-1.931]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":115,"s":[1.95]},{"t":120,"s":[0.083]}],"ix":10},"p":{"a":1,"k":[{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":75,"s":[15.449,9.782,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":93,"s":[17.576,10.696,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":110,"s":[14.532,10.015,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":115,"s":[15.65,9.724,0],"to":[0,0,0],"ti":[0,0,0]},{"t":120,"s":[15.449,9.782,0]}],"ix":2},"a":{"a":0,"k":[110.033,142.38,0],"ix":1},"s":{"a":1,"k":[{"i":{"x":[0.833,0.833,0.833],"y":[1,1,1]},"o":{"x":[0.333,0.333,0.333],"y":[0,0,0]},"t":1,"s":[11.854,11.854,100]},{"i":{"x":[0.667,0.667,0.667],"y":[1,1,1]},"o":{"x":[0.333,0.333,0.333],"y":[0,0,0]},"t":8,"s":[12.1,11.7,100]},{"i":{"x":[0.833,0.833,0.833],"y":[1,1,1]},"o":{"x":[0.333,0.333,0.333],"y":[0,0,0]},"t":15,"s":[11.854,11.854,100]},{"i":{"x":[0.667,0.667,0.667],"y":[1,1,1]},"o":{"x":[0.333,0.333,0.333],"y":[0,0,0]},"t":22,"s":[12.1,11.7,100]},{"i":{"x":[0.833,0.833,0.833],"y":[1,1,1]},"o":{"x":[0.333,0.333,0.333],"y":[0,0,0]},"t":29,"s":[11.854,11.854,100]},{"i":{"x":[0.667,0.667,0.667],"y":[1,1,1]},"o":{"x":[0.333,0.333,0.333],"y":[0,0,0]},"t":36,"s":[12.1,11.7,100]},{"i":{"x":[0.833,0.833,0.833],"y":[1,1,1]},"o":{"x":[0.333,0.333,0.333],"y":[0,0,0]},"t":43,"s":[11.854,11.854,100]},{"i":{"x":[0.667,0.667,0.667],"y":[1,1,1]},"o":{"x":[0.333,0.333,0.333],"y":[0,0,0]},"t":50,"s":[12.1,11.7,100]},{"i":{"x":[0.833,0.833,0.833],"y":[1,1,1]},"o":{"x":[0.333,0.333,0.333],"y":[0,0,0]},"t":57,"s":[11.854,11.854,100]},{"i":{"x":[0.667,0.667,0.667],"y":[1,1,1]},"o":{"x":[0.333,0.333,0.333],"y":[0,0,0]},"t":64,"s":[12.1,11.7,100]},{"t":71,"s":[11.854,11.854,100]}],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0.025,0],[0.039,-0.525],[-0.175,-0.203],[-0.267,-0.021],[-5.538,10.546],[-3.844,-0.57],[-0.082,0.546],[0.546,0.081],[5.555,-10.578],[3.863,0.292]],"o":[[-0.519,0],[-0.02,0.267],[0.174,0.202],[4.813,0.349],[5.04,-9.595],[0.545,0.078],[0.08,-0.545],[-4.838,-0.716],[-5.056,9.628],[-0.025,-0.001]],"v":[[-14.485,13.775],[-15.484,14.702],[-15.243,15.431],[-14.559,15.775],[0.812,0.629],[14.188,-12.978],[15.326,-13.822],[14.481,-14.958],[-0.962,-0.301],[-14.411,13.777]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ind":1,"ty":"sh","ix":2,"ks":{"a":0,"k":{"i":[[5.459,0],[0.215,0.017],[0.496,0.575],[-0.056,0.757],[-0.574,0.495],[-0.755,-0.059],[-4.486,8.542],[-5.785,-0.854],[0.228,-1.549],[1.55,0.226],[6.309,-12.011]],"o":[[-0.215,0],[-0.757,-0.055],[-0.495,-0.574],[0.056,-0.757],[0.575,-0.496],[2.945,0.222],[6.045,-11.513],[1.55,0.23],[-0.23,1.55],[-1.35,-0.198],[-5.771,10.989]],"v":[[-14.049,17.634],[-14.693,17.61],[-16.636,16.632],[-17.318,14.568],[-16.34,12.626],[-14.275,11.944],[-2.589,-1.156],[14.751,-16.78],[17.146,-13.553],[13.92,-11.158],[2.441,1.486]],"c":true},"ix":2},"nm":"Path 2","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"mm","mm":1,"nm":"Merge Paths 1","mn":"ADBE Vector Filter - Merge","hd":false},{"ty":"fl","c":{"a":0,"k":[1,1,1,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[78.301,40.759],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-5.444,-0.804],[0.155,-1.05],[1.05,0.155],[4.928,-9.383],[5.374,0.396],[-0.078,1.058],[-1.059,-0.079],[-4.948,9.422]],"o":[[1.05,0.155],[-0.155,1.049],[-3.202,-0.473],[-5.618,10.699],[-1.059,-0.079],[0.079,-1.058],[3.271,0.241],[5.64,-10.739]],"v":[[14.66,-16.077],[16.28,-13.895],[14.098,-12.276],[1.67,0.85],[-14.582,16.485],[-16.357,14.427],[-14.299,12.653],[-1.732,-0.936]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[1,1,1,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[78.257,40.967],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 2","np":2,"cix":2,"bm":0,"ix":2,"mn":"ADBE Vector Group","hd":false},{"ty":"tr","p":{"a":0,"k":[76.808,40.126],"ix":2},"a":{"a":0,"k":[78.227,41.139],"ix":1},"s":{"a":0,"k":[117,117],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 9","np":2,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[0,0]],"o":[[0,0],[0,0]],"v":[[-3.949,-16.081],[3.949,16.081]],"c":false},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"st","c":{"a":0,"k":[0.078000005086,0.078000005086,0.195999998205,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":4.351,"ix":5},"lc":2,"lj":2,"bm":0,"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false},{"ty":"tr","p":{"a":0,"k":[95.478,108.917],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 3","np":2,"cix":2,"bm":0,"ix":2,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[4.248,-22.049]],"o":[[0,0],[0,0]],"v":[[-6.99,-16.796],[2.742,16.796]],"c":false},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"st","c":{"a":0,"k":[0.078000005086,0.078000005086,0.195999998205,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":4.351,"ix":5},"lc":2,"lj":2,"bm":0,"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false},{"ty":"tr","p":{"a":0,"k":[159.212,93.184],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 4","np":2,"cix":2,"bm":0,"ix":3,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[-11.855,-14.497]],"o":[[0,0],[0,0]],"v":[[1.034,-21.74],[5.928,21.74]],"c":false},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"st","c":{"a":0,"k":[0.078000005086,0.078000005086,0.195999998205,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":4.351,"ix":5},"lc":2,"lj":2,"bm":0,"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false},{"ty":"tr","p":{"a":0,"k":[27.717,119.035],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 5","np":2,"cix":2,"bm":0,"ix":4,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[-49.431,-1.965]],"o":[[0,0],[0,0]],"v":[[-66.169,11.325],[66.169,-9.361]],"c":false},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"st","c":{"a":0,"k":[0.078000005086,0.078000005086,0.195999998205,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":4.351,"ix":5},"lc":2,"lj":2,"bm":0,"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false},{"ty":"tr","p":{"a":0,"k":[88.697,70.995],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 6","np":2,"cix":2,"bm":0,"ix":5,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[-4.262,15.401],[-6.758,17.106],[-19.327,0.522],[-12.696,-8.154],[-6.562,-21.617],[0,0]],"o":[[0,0],[4.261,-15.401],[6.026,-15.256],[26.747,-0.723],[10.856,6.974],[2.06,6.783],[0,0]],"v":[[-69.689,58.134],[-69.36,32.11],[-64.585,-23.954],[-28.633,-57.411],[21.457,-44.653],[71.562,15.995],[71.326,34.699]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.216000007181,0.74900004069,0.773000021542,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[90.666,58.385],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 7","np":2,"cix":2,"bm":0,"ix":6,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[10.042,-17.98],[-7.192,-13.216],[-63.793,20.076],[-16.182,19.942],[18.431,6.83]],"o":[[-5.311,9.51],[9.288,17.068],[63.793,-20.077],[9.416,-11.605],[-18.43,-6.83]],"v":[[-92.507,-21.136],[-88.194,38.896],[1.36,38.131],[88.401,5.114],[68.848,-51.377]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.216000007181,0.74900004069,0.773000021542,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[98.067,113.482],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 8","np":2,"cix":2,"bm":0,"ix":7,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":180,"st":0,"bm":0},{"ddd":0,"ind":2,"ty":4,"nm":"hair ","parent":6,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":0,"k":0,"ix":10},"p":{"a":0,"k":[14.025,9.825,0],"ix":2},"a":{"a":0,"k":[132.398,62.787,0],"ix":1},"s":{"a":0,"k":[12.172,12.172,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[3.653,18.583]],"o":[[31.815,-9.284],[0,0]],"v":[[-32.758,21.113],[29.105,-21.113]],"c":false},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"st","c":{"a":0,"k":[0.078000005086,0.078000005086,0.195999998205,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":4.351,"ix":5},"lc":2,"lj":2,"bm":0,"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false},{"ty":"tr","p":{"a":0,"k":[190.051,52.438],"ix":2},"a":{"a":0,"k":[-31.11,20.448],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":2,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":9,"s":[-3]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":16,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":23,"s":[-3]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":30,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":37,"s":[-3]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":44,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":51,"s":[-3]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":58,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":65,"s":[-3]},{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":72,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":95,"s":[42]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":112,"s":[-15]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":116,"s":[2]},{"t":120,"s":[0]}],"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":2,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[18.007,30.134]],"o":[[0,0],[0,0]],"v":[[60.857,-33.251],[-60.857,3.117]],"c":false},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"st","c":{"a":0,"k":[0.078000005086,0.078000005086,0.195999998205,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":4.351,"ix":5},"lc":2,"lj":2,"bm":0,"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false},{"ty":"tr","p":{"a":0,"k":[131.289,49.075],"ix":2},"a":{"a":0,"k":[59.555,-32.37],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":2,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":9,"s":[3]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":16,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":23,"s":[3]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":30,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":37,"s":[3]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":44,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":51,"s":[3]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":58,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":65,"s":[3]},{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":72,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":95,"s":[22]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":112,"s":[-7]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":116,"s":[2]},{"t":120,"s":[0]}],"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 2","np":2,"cix":2,"bm":0,"ix":2,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[13.046,32.59]],"o":[[0,0],[0,0]],"v":[[62.959,-24.688],[-62.959,-7.902]],"c":false},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"st","c":{"a":0,"k":[0.078000005086,0.078000005086,0.195999998205,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":4.351,"ix":5},"lc":2,"lj":2,"bm":0,"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false},{"ty":"tr","p":{"a":0,"k":[140.997,42.933],"ix":2},"a":{"a":0,"k":[60.546,-24.138],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":2,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":9,"s":[3]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":16,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":23,"s":[3]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":30,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":37,"s":[3]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":44,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":51,"s":[3]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":58,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":65,"s":[3]},{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":72,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":95,"s":[22]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":112,"s":[-8]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":116,"s":[2]},{"t":120,"s":[0]}],"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 3","np":2,"cix":2,"bm":0,"ix":3,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":180,"st":0,"bm":0},{"ddd":0,"ind":3,"ty":4,"nm":"wing bottom ","sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.601],"y":[1]},"o":{"x":[0.261],"y":[0]},"t":1,"s":[0]},{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.239],"y":[0]},"t":8,"s":[2.452]},{"i":{"x":[0.601],"y":[1]},"o":{"x":[0.261],"y":[0]},"t":15,"s":[0]},{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.239],"y":[0]},"t":22,"s":[2.452]},{"i":{"x":[0.601],"y":[1]},"o":{"x":[0.261],"y":[0]},"t":29,"s":[0]},{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.239],"y":[0]},"t":36,"s":[2.452]},{"i":{"x":[0.601],"y":[1]},"o":{"x":[0.261],"y":[0]},"t":43,"s":[0]},{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.239],"y":[0]},"t":50,"s":[2.452]},{"i":{"x":[0.601],"y":[1]},"o":{"x":[0.261],"y":[0]},"t":57,"s":[0]},{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.239],"y":[0]},"t":64,"s":[2.452]},{"i":{"x":[0.686],"y":[1]},"o":{"x":[0.261],"y":[0]},"t":71,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":74,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":92,"s":[26.602]},{"t":109,"s":[0]}],"ix":10},"p":{"a":1,"k":[{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":1,"s":[520.75,633.63,0],"to":[0,0.333,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":8,"s":[520.75,635.63,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":15,"s":[520.75,633.63,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":22,"s":[520.75,635.63,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":29,"s":[520.75,633.63,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":36,"s":[520.75,635.63,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":43,"s":[520.75,633.63,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":50,"s":[520.75,635.63,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":57,"s":[520.75,633.63,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":64,"s":[520.75,635.63,0],"to":[0,0,0],"ti":[0,0.333,0]},{"t":71,"s":[520.75,633.63,0]}],"ix":2},"a":{"a":0,"k":[29.781,15.333,0],"ix":1},"s":{"a":0,"k":[987,987,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[0,0],[0,-46.704]],"o":[[0,0],[46.704,0],[0,0]],"v":[[-59.813,-42.458],[-25.103,-42.458],[59.813,42.458]],"c":false},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"st","c":{"a":0,"k":[0.078000005086,0.078000005086,0.195999998205,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":4.351,"ix":5},"lc":2,"lj":2,"bm":0,"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false},{"ty":"tr","p":{"a":0,"k":[32.354,10.197],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[12,12],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 3","np":2,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[5.684,0],[0,0],[0,10.681],[-0.686,0],[0,0],[0,-5.684]],"o":[[0,0],[-10.68,0],[0,-0.686],[0,0],[5.684,0],[0,5.684]],"v":[[6.947,10.333],[2.139,10.333],[-17.281,-9.086],[-16.034,-10.333],[6.947,-10.333],[17.281,0]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.929411824544,0.705882352941,0.117647066303,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[22.28,15.333],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":3,"cix":2,"bm":0,"ix":2,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":120,"st":0,"bm":0},{"ddd":0,"ind":4,"ty":4,"nm":"legs ","sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":0,"k":0,"ix":10},"p":{"a":0,"k":[509.908,762.219,0],"ix":2},"a":{"a":0,"k":[13.583,8.079,0],"ix":1},"s":{"a":0,"k":[987,987,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[0,0],[0,0]],"o":[[0,0],[0,0],[0,0]],"v":[[-2.465,-3.079],[-2.465,3.079],[2.465,3.079]],"c":false},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"st","c":{"a":0,"k":[0.301999978458,0.184000007779,0.118000000598,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":0.5,"ix":5},"lc":2,"lj":2,"bm":0,"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false},{"ty":"tr","p":{"a":1,"k":[{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":0,"s":[19.701,8.079],"to":[0,-0.417],"ti":[0,0]},{"i":{"x":0.833,"y":1},"o":{"x":0.333,"y":0},"t":7,"s":[19.701,5.579],"to":[0,0],"ti":[0,-0.417]},{"i":{"x":0.833,"y":0.833},"o":{"x":0.167,"y":0.167},"t":14,"s":[19.701,8.079],"to":[0,0],"ti":[0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":28,"s":[19.701,8.079],"to":[0,-0.417],"ti":[0,0]},{"i":{"x":0.833,"y":1},"o":{"x":0.333,"y":0},"t":35,"s":[19.701,5.579],"to":[0,0],"ti":[0,-0.417]},{"i":{"x":0.833,"y":0.833},"o":{"x":0.167,"y":0.167},"t":42,"s":[19.701,8.079],"to":[0,0],"ti":[0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":56,"s":[19.701,8.079],"to":[0,-0.417],"ti":[0,0]},{"i":{"x":0.833,"y":1},"o":{"x":0.333,"y":0},"t":63,"s":[19.701,5.579],"to":[0,0],"ti":[0,-0.417]},{"t":70,"s":[19.701,8.079]}],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":2,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[0,0],[0,0]],"o":[[0,0],[0,0],[0,0]],"v":[[-2.475,-8.141],[-2.465,3.079],[2.465,3.079]],"c":false},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"st","c":{"a":0,"k":[0.301999978458,0.184000007779,0.118000000598,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":0.5,"ix":5},"lc":2,"lj":2,"bm":0,"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false},{"ty":"tr","p":{"a":1,"k":[{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":14,"s":[7.465,8.079],"to":[0,-0.417],"ti":[0,0]},{"i":{"x":0.833,"y":1},"o":{"x":0.333,"y":0},"t":21,"s":[7.465,5.579],"to":[0,0],"ti":[0,-0.417]},{"i":{"x":0.833,"y":0.833},"o":{"x":0.167,"y":0.167},"t":28,"s":[7.465,8.079],"to":[0,0],"ti":[0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":42,"s":[7.465,8.079],"to":[0,-0.417],"ti":[0,0]},{"i":{"x":0.833,"y":1},"o":{"x":0.333,"y":0},"t":49,"s":[7.465,5.579],"to":[0,0],"ti":[0,-0.417]},{"t":56,"s":[7.465,8.079]}],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 2","np":2,"cix":2,"bm":0,"ix":2,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":120,"st":0,"bm":0},{"ddd":0,"ind":5,"ty":4,"nm":"eye ","parent":6,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":0,"k":0,"ix":10},"p":{"a":0,"k":[19.557,14.742,0],"ix":2},"a":{"a":0,"k":[1.316,1.316,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,-0.589],[0.588,0],[0,0.589],[-0.589,0]],"o":[[0,0.589],[-0.589,0],[0,-0.589],[0.588,0]],"v":[[1.066,0],[0,1.066],[-1.066,0],[0,-1.066]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.301999978458,0.184000007779,0.118000000598,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[1.316,1.316],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":2,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":120,"st":0,"bm":0},{"ddd":0,"ind":6,"ty":4,"nm":"body ","sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":63,"s":[0]},{"i":{"x":[0.368],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":74,"s":[-3]},{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.77],"y":[0]},"t":92,"s":[90]},{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":109,"s":[-1.67]},{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.167],"y":[0]},"t":114,"s":[2]},{"t":119,"s":[0]}],"ix":10},"p":{"a":1,"k":[{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":0,"s":[557.132,603.182,0],"to":[0,0.667,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":7,"s":[557.132,607.182,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":14,"s":[557.132,603.182,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":21,"s":[557.132,607.182,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":28,"s":[557.132,603.182,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":35,"s":[557.132,607.182,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":42,"s":[557.132,603.182,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":49,"s":[557.132,607.182,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":56,"s":[557.132,603.182,0],"to":[0,0,0],"ti":[0,0,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":63,"s":[557.132,607.182,0],"to":[0,0,0],"ti":[0,0.667,0]},{"t":70,"s":[557.132,603.182,0]}],"ix":2},"a":{"a":0,"k":[13.463,38.918,0],"ix":1},"s":{"a":0,"k":[987,987,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-1.296,0],[-1.021,-0.298],[0,-4.949],[0.036,-7.068],[12.204,0],[0,2.533],[-4.376,1.491]],"o":[[1.12,0],[4.622,1.351],[0,0.99],[-0.062,12.204],[0,0],[0,-4.777],[1.158,-0.395]],"v":[[0.001,-23.668],[3.223,-23.209],[11.212,-12.729],[11.146,1.603],[-11.031,23.668],[-11.212,-12.729],[-3.698,-23.059]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[1,0.800000059838,0.20000001496,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[16.212,28.668],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":3,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":120,"st":0,"bm":0},{"ddd":0,"ind":7,"ty":4,"nm":"beak top ","parent":6,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":0,"k":0,"ix":10},"p":{"a":0,"k":[29.739,18.103,0],"ix":2},"a":{"a":0,"k":[7.719,7.406,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[0,0],[0.321,-0.177],[0,0]],"o":[[0,0],[0.321,0.177],[0,0],[0,0]],"v":[[-2.719,-2.406],[2.398,-0.408],[2.398,0.409],[-2.719,2.406]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"st","c":{"a":0,"k":[0.301999978458,0.184000007779,0.118000000598,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":0.5,"ix":5},"lc":2,"lj":2,"bm":0,"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false},{"ty":"fl","c":{"a":0,"k":[0.89019613827,0.36862745098,0.278431372549,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[7.719,7.406],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":3,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":120,"st":0,"bm":0}],"markers":[]}"""

@Composable
fun Loader() {

    val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(lottieData))
    val progress by animateLottieCompositionAsState(composition)
//    LottieAnimation(
//        composition = composition,
//        progress = {progress},
//    )
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}


//@Composable
//fun LottieSample() {
//    var isPlaying by remember { mutableStateOf(true) }
//    var speed by remember { mutableStateOf(1f) }
//
//    val lottieComposition by rememberLottieComposition(
//        //spec = RawRes(R.raw.img_practice),
//        spec = LottieCompositionSpec.Asset("sound.json")
//    )
//
//    val lottieAnimationState by animateLottieCompositionAsState (
//        composition = lottieComposition,
//        iterations = LottieConstants.IterateForever,
//        isPlaying = isPlaying,
//        speed = speed,
//        restartOnPlay = false
//    )
//
//
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Text(
//                text = "Lottie Animation In Jetpack Compose",
//                fontSize = 30.sp
//            )
//            LottieAnimation(
//                lottieComposition,
//                lottieAnimationState,
//                modifier = Modifier.size(300.dp)
//            )
//
//            Row(
//                horizontalArrangement = Arrangement.SpaceAround,
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Row(
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//
//                    Button(
//                        onClick = {
//                            speed = max(speed - 0.25f, 0f)
//                        },
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = Color(0xFF0F9D58)
//                        )
//                    ) {
//                        Text(
//                            text = "-",
//                            color = Color.White,
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 20.sp,
//
//                            )
//                    }
//
//                    Text(
//                        text = "Speed ( $speed ) ",
//                        color = Color.Black,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 15.sp, modifier = Modifier.padding(horizontal = 10.dp)
//
//                    )
//                    Button(
//                        onClick = {
//                            speed += 0.25f
//                        },
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = Color(0xFF0F9D58)
//                        )
//                    ) {
//                        Text(
//                            text = "+",
//                            color = Color.White,
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 20.sp
//                        )
//                    }
//                }
//
//                Button(
//                    onClick = {
//                        isPlaying = !isPlaying
//                    },
//                    colors = ButtonDefaults.buttonColors(
//                        backgroundColor = Color(0xFF0F9D58)
//                    )
//                ) {
//                    Text(
//                        text = if (isPlaying) "Pause" else "Play",
//                        color = Color.White
//                    )
//                }
//            }
//        }
//    }
//}




