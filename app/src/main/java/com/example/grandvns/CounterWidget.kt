package com.example.grandvns

import android.content.Context
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.*
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.grandvns.App.Api_Connection.Agent.agentDataItem
import com.example.grandvns.App.App_UI.AgentProfileViewModel


class SimpleCounterWidgetReceiver: GlanceAppWidgetReceiver() {



    override val glanceAppWidget: GlanceAppWidget
        get() = CounterWidget

}




object CounterWidget: GlanceAppWidget() {


    private val countKey = intPreferencesKey("count")

    @Composable
    override fun Content() {
        val count = currentState(key = countKey) ?: 0
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color.DarkGray),
            verticalAlignment = Alignment.Vertical.CenterVertically,
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally
        ) {
            Text(
                text = count.toString(),
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    color = ColorProvider(Color.White),
                    fontSize = 26.sp
                )
            )
//            Button(
//                text = "Inc",
//                onClick = actionRunCallback(IncrementActionCallback::class.java)
//            )
        }
    }
}


//class IncrementActionCallback: ActionCallback {
//    override suspend fun onAction(
//        context: Context,
//        glanceId: GlanceId,
//        parameters: ActionParameters
//    ) {
//        updateAppWidgetState(context, glanceId) { prefs ->
//            val currentCount = prefs[CounterWidget.countKey]
//            if(currentCount != null) {
//                prefs[CounterWidget.countKey] = currentCount + 1
//            } else {
//                prefs[CounterWidget.countKey] = 1
//            }
//        }
//        CounterWidget.update(context, glanceId)
//    }
//}