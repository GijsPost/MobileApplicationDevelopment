package nl.gijspost.bucketlist;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class BucketListService {

    private BucketListDao bucketListDao;
    private LiveData<List<BucketList>> lists;
    private Executor mExecutor = Executors.newSingleThreadExecutor();


    public BucketListService(Context context) {
        BucketListDatabase db = BucketListDatabase.getInstance(context);
        this.bucketListDao = db.bucketListDao();
        lists = bucketListDao.getAllBucketLists();
    }


    public LiveData<List<BucketList>> getAllBucketLists() {
        return this.lists;
    }

    public void insert(final BucketList BucketList) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bucketListDao.insertBucketList(BucketList);
            }
        });

    }

    public void update(final BucketList BucketList) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bucketListDao.updateBucketList(BucketList);
            }
        });

    }

    public void delete(final BucketList BucketList) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bucketListDao.deleteBucketList(BucketList);
            }
        });
    }
}