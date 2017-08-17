package com.androidessence.todo_room.viewmodel

import android.arch.lifecycle.ViewModel
import com.androidessence.todo_room.AppDatabase
import com.androidessence.todo_room.Task
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * ViewModel for a task
 */
class TaskViewModel: ViewModel() {
    fun getTasks(): Maybe<List<Task>> = AppDatabase.getInstance().taskDao().getAll()

    fun insertTasks(tasks: List<Task>): List<Long> = AppDatabase.getInstance().taskDao().insertAll(tasks)

//    public class TeamViewModel extends ViewModel {
//
//    private final TeamRepository repository;
//
//    public TeamViewModel() {
//        repository = TeamRepository.getInstance();
//    }
//
//    public Single<Team> createOrUpdate(Team team) {
//        return repository.createOrUpdate(team);
//    }
//
//    public Flowable<Team> getTeam(Team team) {
//        return repository.get(team.getId());
//    }
//
//    public Single<List<Team>> findTeams(String queryText) {
//        return repository.findTeams(queryText);
//    }
//
//    public Flowable<List<Team>> getMyTeams(String userId) {
//        return repository.getMyTeams(userId);
//    }
//
//    public Single<Team> deleteTeam(Team team) {
//        return repository.delete(team);
//    }
//}
}