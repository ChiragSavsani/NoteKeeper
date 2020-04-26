package com.synbustech.notekeeper

import androidx.test.espresso.Espresso.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.Espresso.pressBack
import org.hamcrest.Matchers.*


@RunWith(AndroidJUnit4::class)
class CreateNewNoteTest{

    @Rule @JvmField
    val noteListActivity = ActivityTestRule(NoteListActivity::class.java)
    @Test
    fun createNewNote(){

        val course = DataManager.courses["android_async"]
        val noteTitle = "This is a test note"
        val noteText = "This is the body of our test note"

        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.spinnerCourses)).perform(click())

        onData(allOf(instanceOf(CourseInfo::class.java), equalTo(course))).perform(click())
        onView(withId(R.id.edtTxtNoteTitle)).perform(typeText(noteTitle))
        onView(withId(R.id.edtTxtNoteText)).perform(typeText(noteText), closeSoftKeyboard())

        pressBack()

        val note = DataManager.notes.last()

        assertEquals(course, note.course)
        assertEquals(noteTitle, note.title)
        assertEquals(noteText, note.text)
    }
}