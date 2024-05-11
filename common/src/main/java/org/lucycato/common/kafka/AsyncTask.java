package org.lucycato.common.kafka;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AsyncTask<T> {
    private TaskKey taskKey;

    private T value;

    public static <T> AsyncTask<T> CREATE(TaskKey taskKey, T value) {
         AsyncTask<T> asyncTask = new AsyncTask<>();
         asyncTask.taskKey = taskKey;
         asyncTask.value = value;
         return asyncTask;
    }
}



