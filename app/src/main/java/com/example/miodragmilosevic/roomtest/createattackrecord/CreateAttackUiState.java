package com.example.miodragmilosevic.roomtest.createattackrecord;

/**
 * Created by miodrag.milosevic on 2/5/2018.
 */

public class CreateAttackUiState {

    private boolean isTimeMissing;
    private boolean isDateMissing;
    private boolean isDataBaseInsertFailed;
    private boolean isCompleted;

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isTimeMissing() {
        return isTimeMissing;
    }

    public void setTimeMissing(boolean timeMissing) {
        isTimeMissing = timeMissing;
    }

    public boolean isDateMissing() {
        return isDateMissing;
    }

    public void setDateMissing(boolean dateMissing) {
        isDateMissing = dateMissing;
    }

    public boolean isDataBaseInsertFailed() {
        return isDataBaseInsertFailed;
    }

    public void setDataBaseInsertFailed(boolean dataBaseInsertFailed) {
        isDataBaseInsertFailed = dataBaseInsertFailed;
    }

    public boolean isError(){
        return isTimeMissing||isDataBaseInsertFailed||isDateMissing;
    }

    public void clearErrors(){
        isTimeMissing = false;
        isDateMissing = false;
        isDataBaseInsertFailed = false;
    }

}
