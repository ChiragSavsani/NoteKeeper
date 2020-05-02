package com.synbustech.notekeeper

import android.os.Bundle
import androidx.lifecycle.ViewModel

class ItemsActivityViewModel : ViewModel() {

    var isNewlyCreated = true
    var navDrawerDisplaySelectionName = "com.synbustech.notekeeper.ItemsActivityViewModel.navDrawerDisplaySelection"
    var recentlyViewedNoteIdsName = "com.synbustech.notekeeper.ItemsActivityViewModel.recentlyViewedNoteIds"

    var navDrawerDisplaySelection = R.id.nav_notes

    private val maxRecentlyViewedNotes = 5
    val recentlyViewedNotes = ArrayList<NoteInfo>(maxRecentlyViewedNotes)

    fun addToRecentlyViewedNotes(note : NoteInfo){
        val existingIndex = recentlyViewedNotes.indexOf(note)
        if(existingIndex == -1) {
            recentlyViewedNotes.add(0, note)
            for(index in recentlyViewedNotes.lastIndex downTo maxRecentlyViewedNotes){
                recentlyViewedNotes.removeAt(index)
            }
        } else {
            for(index in (existingIndex - 1) downTo 0) {
                recentlyViewedNotes[index + 1] = recentlyViewedNotes[index]
            }
            recentlyViewedNotes[0] = note
        }

    }

    fun saveState(outState: Bundle) {
        outState.putInt(navDrawerDisplaySelectionName, navDrawerDisplaySelection)
        val noteIds = DataManager.noteIdsAsIntArray(recentlyViewedNotes)
        outState.putIntArray(recentlyViewedNoteIdsName, noteIds)
    }

    fun restoreState(savedInstanceState: Bundle) {
        navDrawerDisplaySelection = savedInstanceState.getInt(navDrawerDisplaySelectionName)
        val noteIds = savedInstanceState.getIntArray(recentlyViewedNoteIdsName)
        val noteList = DataManager.loadNotes(*noteIds!!)
        recentlyViewedNotes.addAll(noteList)
    }
}
