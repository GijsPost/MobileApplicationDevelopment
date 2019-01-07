package nl.gijspost.individualassignment.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

import nl.gijspost.individualassignment.models.App;
import nl.gijspost.individualassignment.services.AppService;

public class AppViewModel extends ViewModel {
    private AppService service;
    private LiveData<List<App>> apps;

    public AppViewModel(Context context) {
        this.service = new AppService(context);
        this.apps = this.service.getApps();
    }

    public LiveData<List<App>> getApps() {
        return this.apps;
    }

    public void insert(App app) {
        this.service.insert(app);
    }

    public void update(App app) {
        this.service.update(app);
    }

    public void delete(App app) {
        this.service.delete(app);
    }

}
