package org.example.project.screen.bottomScreen

//
//enum class AppPages(val title: String, val image: ImageVector, val selectImage: ImageVector) {
//    PAGE_ONE("游戏", EvaIcons.Outline.Monitor, EvaIcons.Fill.Monitor),
//    PAGE_TWO("发现", EvaIcons.Outline.Radio, EvaIcons.Fill.Radio),
//    PAGE_THEE("音乐", EvaIcons.Outline.Headphones, EvaIcons.Fill.Headphones)
//}


//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun HomeView() {
//
//    val pagerState = rememberPagerState(pageCount = { AppPages.entries.size }, initialPage = AppPages.PAGE_TWO.ordinal)
//    val coroutineScope = rememberCoroutineScope()
//
//    androidx.compose.material3.Scaffold(
//        modifier = Modifier.fillMaxSize()
//    ) { paddingValues ->
//        Column(modifier = Modifier.padding(paddingValues)) {
//            // 此处顶部栏样式，可扩展为 -> 自定义+TabRow+自定义
//            androidx.compose.material3.TabRow(selectedTabIndex = pagerState.currentPage) {
//                AppPages.entries.forEachIndexed { index, page ->
//                    Tab(
//                        selected = pagerState.currentPage == index,
//                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
//                        text = { androidx.compose.material3.Text(page.title, fontSize = 12.sp) },
//                        icon = {
//                            Icon(
//                                imageVector = if (pagerState.currentPage == index) page.selectImage else page.image,
//                                "pageIcon",
//                                modifier = Modifier.size(24.dp)
//                            )
//                        }
//                    )
//                }
//            }
//            HorizontalPager(state = pagerState) { page ->
//                when (AppPages.entries[page]) {
//                    AppPages.PAGE_ONE -> PageOneContent()
//                    AppPages.PAGE_TWO -> PageTwoContent()
//                    AppPages.PAGE_THEE -> PageThrContent()
//                }
//            }
//        }
//    }
//
//
//}


//@Composable
//fun PageOneContent() {
//    // Your content for Page One here
//    Box(modifier = Modifier.fillMaxSize().background(Color.Gray)) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Text("This is Page One")
//
//        }
//
//    }
//}
//
//@Composable
//fun PageTwoContent() {
//    // Your content for Page Two here
//    Box(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Text("This is Page Two")
//            Loader()
//        }
//    }
//}
//
//@Composable
//fun PageThrContent() {
//    // Your content for Page Two here
//    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
//        val navigator = LocalNavigator.currentOrThrow
//
//    }
//}
//
//@Composable
//fun Loader() {
//
//    val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(Lottie_Chicken))
//    val progress by animateLottieCompositionAsState(composition)
////    LottieAnimation(
////        composition = composition,
////        progress = {progress},
////    )
//    LottieAnimation(
//        composition = composition,
//        iterations = LottieConstants.IterateForever,
//    )
//}




