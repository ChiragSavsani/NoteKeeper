package com.synbustech.notekeeper

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    fun addNote(course: CourseInfo, noteTitle: String, noteText: String): Int {

        val note = NoteInfo(course, noteTitle, noteText)
        notes.add(note)
        return notes.lastIndex;
    }

    fun findNote(course: CourseInfo, noteTitle: String, noteText: String): NoteInfo? {
        for (note in notes)
            if (course == note.course && noteTitle == note.title && noteText == note.text)
                return note

        return null
    }

    private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android Programming with Intents")
        courses.set(course.courseId, course)

        course =
            CourseInfo(courseId = "android_async", title = "Android Async Programming and Services")
        courses.set(course.courseId, course)

        course = CourseInfo(title = "Java Fundamentals: The Java Language", courseId = "java_lang")
        courses.set(course.courseId, course)

        course = CourseInfo("java_core", "Java Fundamentals: The Core Platform")
        courses.set(course.courseId, course)

    }

    fun initializeNotes() {
        notes.add(
            NoteInfo(
                courses.get("android_intents"),
                "Dynamic intent resolution",
                "Wow, intents allow components to be resolved at runtime"
            )
        )
        notes.add(
            NoteInfo(
                courses.get("android_intents"),
                "Delegating intents",
                "Pendingintents are powerful; they delegate much more that just a component invocation"
            )
        )
        notes.add(
            NoteInfo(
                courses.get("android_async"),
                "Service default threads",
                "Did you know that by default an Android Service will tie up the UI thread?"
            )
        )
        notes.add(
            NoteInfo(
                courses.get("android_async"),
                "Long running operations",
                "Foreground service can be tied to a notification icon"
            )
        )
        notes.add(
            NoteInfo(
                courses.get("java_lang"),
                "Parameters",
                "Leverage variable-length parameter lists"
            )
        )
        notes.add(
            NoteInfo(
                courses.get("java_lang"),
                "Anonymous classes",
                "Anonymous classes Simplify implementing one-use types"
            )
        )
        notes.add(
            NoteInfo(
                courses.get("java_core"),
                "Compiler options",
                "The -jar option isn't compatible with with the -cp option"
            )
        )
        notes.add(
            NoteInfo(
                courses.get("java_core"),
                "Serialization",
                "Remember to include SerialVersionUID to assure version compatibility"
            )
        )
    }

    fun loadNotes(vararg noteIds: Int): List<NoteInfo> {
        val noteList: List<NoteInfo>

        if (noteIds.isEmpty())
            noteList = notes
        else {
            noteList = ArrayList(noteIds.size)
            for (noteId in noteIds)
                noteList.add(notes[noteId])
        }
        return noteList
    }

    fun noteIdsAsIntArray(recentlyViewedNotes: ArrayList<NoteInfo>): IntArray? {
        val noteIds = ArrayList<Int>(recentlyViewedNotes.size)
        for (note in recentlyViewedNotes) {
            val noteId = notes.indexOf(note)
            noteIds.add(noteId)
        }
        return noteIds.toIntArray()
    }
}