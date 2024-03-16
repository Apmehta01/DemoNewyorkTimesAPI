package com.example.demojsonplaceholderapi.screens.activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demojsonplaceholderapi.screens.fragment.getMostPopularModel
import com.example.demojsonplaceholderapi.screens.fragment.getNewsViewModel
import com.example.demojsonplaceholderapi.screens.fragment.getsBookViewModel
import com.example.demojsonplaceholderapi.web.model.TabBarItem
import com.example.demorandomuserapi.ui.CustomText
import com.example.demorandomuserapi.ui.theme.Purple500
import com.example.demorandomuserapi.ui.theme.normalFont
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mContext = this@MainActivity

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // setting up the individual tabs
            val homeTab = TabBarItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            )
            val alertsTab = TabBarItem(
                title = "Alerts",
                selectedIcon = Icons.Filled.Notifications,
                unselectedIcon = Icons.Outlined.Notifications,
            )
            val settingsTab = TabBarItem(
                title = "Settings",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings
            )
            val moreTab = TabBarItem(
                title = "More",
                selectedIcon = Icons.Filled.List,
                unselectedIcon = Icons.Outlined.List
            )

            // creating a list of all the tabs
            val tabBarItems = listOf(homeTab, alertsTab, settingsTab, moreTab)
            // creating our navController
            val navController = rememberNavController()

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = {
                            CustomText(
                                text = "New York Times Data",
                                style = TextStyle(
                                    fontFamily = normalFont,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                ),
                                textAlign = TextAlign.Center, color = Color.White
                            )
                        }, backgroundColor = Purple500, contentColor = Color.White)
                    }, bottomBar = { TabView(tabBarItems, navController) }) {
                    NavHost(navController = navController, startDestination = homeTab.title) {
                        composable(homeTab.title) {
                            getNewsViewModel()
                        }
                        composable(alertsTab.title) {
                            getsBookViewModel(mContext = mContext)
                        }
                        composable(settingsTab.title) {
                            getMostPopularModel(mContext = mContext)
                        }
                    }
                }
            }
        }
    }

    // ----------------------------------------
// This is a wrapper view that allows us to easily and cleanly
// reuse this component in any future project
    @Composable
    fun TabView(tabBarItems: List<TabBarItem>, navController: NavController) {
        var selectedTabIndex by rememberSaveable {
            mutableIntStateOf(0)
        }

        NavigationBar {
            // looping over each tab to generate the views and navigation for each item
            tabBarItems.forEachIndexed { index, tabBarItem ->
                NavigationBarItem(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        navController.navigate(tabBarItem.title)
                    },
                    icon = {
                        TabBarIconView(
                            isSelected = selectedTabIndex == index,
                            selectedIcon = tabBarItem.selectedIcon,
                            unselectedIcon = tabBarItem.unselectedIcon,
                            title = tabBarItem.title,
                            badgeAmount = tabBarItem.badgeAmount
                        )
                    },
                    label = { Text(tabBarItem.title) })
            }
        }
    }

    // This component helps to clean up the API call from our TabView above,
// but could just as easily be added inside the TabView without creating this custom component
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TabBarIconView(
        isSelected: Boolean,
        selectedIcon: ImageVector,
        unselectedIcon: ImageVector,
        title: String,
        badgeAmount: Int? = null
    ) {
        BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
            Icon(
                imageVector = if (isSelected) {
                    selectedIcon
                } else {
                    unselectedIcon
                },
                contentDescription = title
            )
        }
    }

    // This component helps to clean up the API call from our TabBarIconView above,
// but could just as easily be added inside the TabBarIconView without creating this custom component
    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun TabBarBadgeView(count: Int? = null) {
        if (count != null) {
            Badge {
                Text(count.toString())
            }
        }
    }
// end of the reusable components that can be copied over to any new projects
// ----------------------------------------

    // This was added to demonstrate that we are infact changing views when we click a new tab
    @Composable
    fun MoreView() {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Thing 1")
            Text("Thing 2")
            Text("Thing 3")
            Text("Thing 4")
            Text("Thing 5")
        }
    }
}