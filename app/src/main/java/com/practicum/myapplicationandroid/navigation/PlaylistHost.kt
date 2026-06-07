package com.practicum.myapplicationandroid.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practicum.myapplicationandroid.R
import com.practicum.myapplicationandroid.ui.main.MainScreen
import com.practicum.myapplicationandroid.ui.search.SearchScreen
import com.practicum.myapplicationandroid.ui.settings.SettingsScreen

@Composable
fun PlaylistHost(
    onPlaylistsClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    fun navigateToSearch() {
        navController.navigate(PlaylistScreen.Search.route)
    }

    fun navigateToSettings() {
        navController.navigate(PlaylistScreen.Settings.route)
    }

    NavHost(
        navController = navController,
        startDestination = PlaylistScreen.Main.route,
        modifier = modifier.fillMaxSize(),
    ) {
        composable(PlaylistScreen.Main.route) {
            MainScreen(
                modifier = Modifier.fillMaxSize(),
                onSearchClick = ::navigateToSearch,
                onPlaylistsClick = onPlaylistsClick,
                onFavoritesClick = onFavoritesClick,
                onSettingsClick = ::navigateToSettings,
            )
        }

        composable(PlaylistScreen.Search.route) {
            SearchScreen(
                modifier = Modifier.fillMaxSize(),
                onBackClick = { navController.popBackStack() },
            )
        }

        composable(PlaylistScreen.Settings.route) {
            SettingsRoute(
                modifier = Modifier.fillMaxSize(),
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}

@Composable
private fun SettingsRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val shareMessage = stringResource(R.string.share_message)
    val shareTitle = stringResource(R.string.share_app)
    val developerEmail = stringResource(R.string.developer_email)
    val emailSubject = stringResource(R.string.developer_email_subject)
    val emailBody = stringResource(R.string.developer_email_body)
    val userAgreementUrl = stringResource(R.string.user_agreement_url)
    val contactSupportTitle = stringResource(R.string.write_to_developers)

    SettingsScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onShareClick = {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareMessage)
            }
            context.startActivity(Intent.createChooser(shareIntent, shareTitle))
        },
        onSupportClick = {
            val supportIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(developerEmail))
                putExtra(Intent.EXTRA_SUBJECT, emailSubject)
                putExtra(Intent.EXTRA_TEXT, emailBody)
            }
            context.startActivity(Intent.createChooser(supportIntent, contactSupportTitle))
        },
        onUserAgreementClick = {
            val agreementIntent = Intent(Intent.ACTION_VIEW, Uri.parse(userAgreementUrl))
            context.startActivity(agreementIntent)
        },
    )
}
