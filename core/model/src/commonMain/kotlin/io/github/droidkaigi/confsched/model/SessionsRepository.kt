package io.github.droidkaigi.confsched.model

import kotlinx.coroutines.flow.Flow

interface SessionsRepository {
    fun getTimetableStream(): Flow<Timetable>
    fun getTimetableItemWithBookmarkStream(id: TimetableItemId): Flow<Pair<TimetableItem, Boolean>>
    suspend fun toggleBookmark(id: TimetableItemId)
}
