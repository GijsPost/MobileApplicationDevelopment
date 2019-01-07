package nl.gijspost.individualassignment.services;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import nl.gijspost.individualassignment.AppDatabase;
import nl.gijspost.individualassignment.models.App;
import nl.gijspost.individualassignment.models.AppDao;

public class AppService {

    private AppDao appDao;
    private LiveData<List<App>> list;
    private Executor mExecutor = Executors.newSingleThreadExecutor();


    public AppService(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.appDao = db.appDao();
        list = appDao.getApps();
    }


    public LiveData<List<App>> getApps() {
        return this.list;
    }

    public void insert(final App app) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                appDao.insertApp(app);
            }
        });

    }

    public void update(final App app) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                appDao.updateApp(app);
            }
        });

    }

    public void delete(final App app) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                appDao.deleteApp(app);
            }
        });
    }
}