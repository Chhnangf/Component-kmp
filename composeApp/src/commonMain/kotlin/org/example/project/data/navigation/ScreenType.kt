package org.example.project.data.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.Home
import compose.icons.evaicons.fill.MessageSquare
import compose.icons.evaicons.fill.Pantone
import compose.icons.evaicons.fill.PaperPlane
import compose.icons.evaicons.fill.Person
import compose.icons.evaicons.fill.PlusSquare
import compose.icons.evaicons.fill.Settings2
import compose.icons.evaicons.outline.Home
import compose.icons.evaicons.outline.MessageSquare
import compose.icons.evaicons.outline.Pantone
import compose.icons.evaicons.outline.PaperPlane
import compose.icons.evaicons.outline.Person
import compose.icons.evaicons.outline.Settings2

enum class ScreenType(
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val buttonName: String
) {
    HOME_SCREEN(
        EvaIcons.Outline.Home,
        EvaIcons.Fill.Home,
        "首页"
    ),
    CHAT_SCREEN(
        EvaIcons.Outline.MessageSquare,
        EvaIcons.Fill.MessageSquare,
        "社区"
    ),
    PUSH_SCREEN(
        EvaIcons.Fill.PlusSquare,
        EvaIcons.Fill.PlusSquare,
        "发布"
    ),
    SET_SCREEN(
        EvaIcons.Outline.Person,
        EvaIcons.Fill.Person,
        "设置"
    );
}