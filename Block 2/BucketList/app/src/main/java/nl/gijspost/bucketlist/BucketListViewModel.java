package nl.gijspost.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

class BucketListViewModel extends ViewModel {
    private BucketListService service;
    private LiveData<List<BucketList>> bucketLists;

    public BucketListViewModel(Context context) {
        this.service = new BucketListService(context);
        this.bucketLists = this.service.getAllBucketLists();
    }

    public LiveData<List<BucketList>> getAllBucketLists() {
        return this.bucketLists;
    }

    public void insert(BucketList list) {
        this.service.insert(list);
    }

    public void update(BucketList list) {
        this.service.update(list);
    }

    public void delete(BucketList list) {
        this.service.delete(list);
    }

}
