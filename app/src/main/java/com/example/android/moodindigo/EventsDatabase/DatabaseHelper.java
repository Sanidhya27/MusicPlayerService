package com.example.android.moodindigo.EventsDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrunz on 7/12/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "eventsManager";

    // Table Names
    private static final String TABLE_EVENT= "events";
    private static final String TABLE_TAG = "tags";
    private static final String TABLE_EVENT_TAG = "events_tags";

    // Common column names
    private static final String KEY_ID = "id";

    // NOTES Table - column nmaes
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TIME = "time";
    private static final String KEY_VENUE = "venue";
    private static final String KEY_GOING_NUMBER = "goingNumber";
    private static final String KEY_GOING = "going";





    // TAGS Table - column names
    private static final String KEY_TYPE="type";
    private static final String KEY_DAY="day";


    // NOTE_TAGS Table - column names
    private static final String KEY_EVENT_ID = "todo_id";
    private static final String KEY_TAG_ID = "tag_id";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_EVENT= "CREATE TABLE "
            + TABLE_EVENT + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
            + " TEXT,"+ KEY_DESCRIPTION + " TEXT," + KEY_TIME + " TEXT," + KEY_VENUE
            + " TEXT," + KEY_GOING_NUMBER + " INTEGER," + KEY_GOING
            + " INTEGER" + ")";

    // Tag table create statement
    private static final String CREATE_TABLE_TAG= "CREATE TABLE "
            + TABLE_TAG + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TYPE
            + " TEXT,"+ KEY_DAY
            + " INTEGER" + ")";

    // todo_tag table create statement
    private static final String CREATE_TABLE_EVENT_TAG = "CREATE TABLE "
            + TABLE_EVENT_TAG + " (" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_EVENT_ID + " INTEGER," + KEY_TAG_ID + " INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_EVENT);
        Log.i("events","done");
        db.execSQL(CREATE_TABLE_TAG);
        Log.i("tags","done");
        db.execSQL(CREATE_TABLE_EVENT_TAG);
        Log.i("event_tags","done");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT_TAG);

        // create new tables
        onCreate(db);
    }

    /*
 * Creating a event
 */
    public long createEvent(Event event, long[] tag_ids) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, event.getName());
        values.put(KEY_DESCRIPTION, event.getDescription());
        values.put(KEY_GOING, event.getGoing());
        values.put(KEY_GOING_NUMBER, event.getGoing_total());
        values.put(KEY_TIME, event.getTime_start());
        values.put(KEY_VENUE, event.getVenue());

        // insert row
        long event_id = db.insert(TABLE_EVENT, null, values);

        // assigning tags to event
        for (long tag_id : tag_ids) {
            createEventTag(event_id, tag_id);
        }

        return event_id;
    }

    /*
 * get single EVENT
 */
    public Event getEvent(long event_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_EVENT + " WHERE "
                + KEY_ID + " = " + event_id;

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)

            c.moveToFirst();

        Event event = new Event();
        event.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        event.setName((c.getString(c.getColumnIndex(KEY_NAME))));
        event.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
        event.setVenue((c.getString(c.getColumnIndex(KEY_VENUE))));
        event.setTime_start((c.getString(c.getColumnIndex(KEY_TIME))));
        event.setGoing((c.getInt(c.getColumnIndex(KEY_GOING))));
        event.setGoing_total((c.getInt(c.getColumnIndex(KEY_GOING_NUMBER))));


        return event;
    }

    /*
 * getting all events
 * */
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<Event>();
        String selectQuery = "SELECT  * FROM " + TABLE_EVENT;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Event event = new Event();
                event.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                event.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                event.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                event.setVenue((c.getString(c.getColumnIndex(KEY_VENUE))));
                event.setTime_start((c.getString(c.getColumnIndex(KEY_TIME))));
                event.setGoing((c.getInt(c.getColumnIndex(KEY_GOING))));
                event.setGoing_total((c.getInt(c.getColumnIndex(KEY_GOING_NUMBER))));

                // adding to event list
                events.add(event);
            } while (c.moveToNext());
        }

        return events;
    }

    /*
 * getting all events under single tag
 * */
    public List<Event> getAllEventsByTag(String type,int day) {
        List<Event> events = new ArrayList<Event>();

        String selectQuery = "SELECT  * FROM " + TABLE_EVENT + " te, "
                + TABLE_TAG + " tg, " + TABLE_EVENT_TAG+ " et WHERE tg."
                + KEY_TYPE + " = '" + type + "' AND tg."
                + KEY_DAY + " = '" + day + "'" + " AND tg." + KEY_ID
                + " = " + "et." + KEY_TAG_ID + " AND te." + KEY_ID + " = "
                + "et." + KEY_EVENT_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Event event = new Event();
                event.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                event.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                event.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                event.setVenue((c.getString(c.getColumnIndex(KEY_VENUE))));
                event.setTime_start((c.getString(c.getColumnIndex(KEY_TIME))));
                event.setGoing((c.getInt(c.getColumnIndex(KEY_GOING))));
                event.setGoing_total((c.getInt(c.getColumnIndex(KEY_GOING_NUMBER))));

                // adding to event list
                events.add(event);
            } while (c.moveToNext());
        }

        return events;
    }
    public List<Event> getOrderedEventsByTag(String type,int day) {
        List<Event> events = new ArrayList<Event>();

        String selectQuery = "SELECT  * FROM " + TABLE_EVENT + " te, "
                + TABLE_TAG + " tg, " + TABLE_EVENT_TAG+ " et WHERE tg."
                + KEY_TYPE + " = '" + type + "' AND tg."
                + KEY_DAY + " = '" + day + "'" + " AND tg." + KEY_ID
                + " = " + "et." + KEY_TAG_ID + " AND te." + KEY_ID + " = "
                + "et." + KEY_EVENT_ID+" ORDER BY "+ KEY_TIME+ "+0 " + " ASC";;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Event event = new Event();
                event.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                event.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                event.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                event.setVenue((c.getString(c.getColumnIndex(KEY_VENUE))));
                event.setTime_start((c.getString(c.getColumnIndex(KEY_TIME))));
                event.setGoing((c.getInt(c.getColumnIndex(KEY_GOING))));
                event.setGoing_total((c.getInt(c.getColumnIndex(KEY_GOING_NUMBER))));

                // adding to event list
                events.add(event);
            } while (c.moveToNext());
        }

        return events;
    }

    public List<Event> getOrderedEventsByDay(int day) {
        List<Event> events = new ArrayList<Event>();

        String selectQuery = "SELECT  * FROM " + TABLE_EVENT + " te, "
                + TABLE_TAG + " tg, " + TABLE_EVENT_TAG+ " et WHERE tg."
                + KEY_DAY + " = '" + day + "'" + " AND tg." + KEY_ID
                + " = " + "et." + KEY_TAG_ID + " AND te." + KEY_ID + " = "
                + "et." + KEY_EVENT_ID+ " ORDER BY "+ KEY_TIME + "+0 " + "ASC";

        Log.e("Query order", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Event event = new Event();
                event.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                event.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                event.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                event.setVenue((c.getString(c.getColumnIndex(KEY_VENUE))));
                event.setTime_start((c.getString(c.getColumnIndex(KEY_TIME))));
                event.setGoing((c.getInt(c.getColumnIndex(KEY_GOING))));
                event.setGoing_total((c.getInt(c.getColumnIndex(KEY_GOING_NUMBER))));

                // adding to event list
                events.add(event);
            } while (c.moveToNext());
        }

        return events;
    }

    /*
     * Updating a event
     */
    public int updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, event.getName());
        values.put(KEY_DESCRIPTION, event.getDescription());
        values.put(KEY_TIME, event.getTime_start());
        values.put(KEY_GOING_NUMBER, event.getGoing_total());
        values.put(KEY_GOING, event.getGoing());
        values.put(KEY_VENUE, event.getVenue());

        // updating row
        return db.update(TABLE_EVENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(event.getId()) });
    }
    public long updateEventifFound(Event event, long[] tag_ids) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_EVENT + " WHERE "
                + KEY_NAME+ " = '" + event.getName()+"'";

        Cursor c=db.rawQuery(selectQuery,null);
        if(c!=null){
            if(c.moveToFirst()){
                ContentValues values = new ContentValues();
                values.put(KEY_NAME, event.getName());
                values.put(KEY_DESCRIPTION, event.getDescription());
                values.put(KEY_TIME, event.getTime_start());
                values.put(KEY_GOING_NUMBER, event.getGoing_total());
                values.put(KEY_GOING, event.getGoing());
                values.put(KEY_VENUE, event.getVenue());

                // updating row
                return db.update(TABLE_EVENT, values, KEY_ID + " = ?",
                        new String[] { String.valueOf(event.getId()) });

            }
            else {
                ContentValues values = new ContentValues();
                values.put(KEY_NAME, event.getName());
                values.put(KEY_DESCRIPTION, event.getDescription());
                values.put(KEY_GOING, event.getGoing());
                values.put(KEY_GOING_NUMBER, event.getGoing_total());
                values.put(KEY_TIME, event.getTime_start());
                values.put(KEY_VENUE, event.getVenue());

                // insert row
                long event_id = db.insert(TABLE_EVENT, null, values);

                // assigning tags to event
                for (long tag_id : tag_ids) {
                    createEventTag(event_id, tag_id);
                }

                return event_id;
            }
        }


    return -2;
    }

    /*
 * Deleting a event
 */
    public void deleteEvent(long event_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENT, KEY_ID + " = ?",
                new String[] { String.valueOf(event_id) });
    }

    /*
 * Creating tag
 */
    public long createTag(Tag tag) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TYPE,tag.getType());
        values.put(KEY_DAY,tag.getDay());

        // insert row
        long tag_id = db.insert(TABLE_TAG,null,values);

        return tag_id;
    }

    /**
     * getting all tags
     * */
    public List<Tag> getAllTags() {
        List<Tag> tags = new ArrayList<Tag>();
        String selectQuery = "SELECT  * FROM " + TABLE_TAG;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Tag t = new Tag();
                t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                t.setType(c.getString(c.getColumnIndex(KEY_TYPE)));
                t.setDay(c.getInt(c.getColumnIndex(KEY_DAY)));
                // adding to tags list
                tags.add(t);
            } while (c.moveToNext());
        }
        return tags;
    }

    /*
 * Updating a tag
 */
    public int updateTag(Tag tag) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, tag.getType());
        values.put(KEY_DAY, tag.getDay());


        // updating row
        return db.update(TABLE_TAG, values, KEY_ID + " = ?",
                new String[] { String.valueOf(tag.getId()) });
    }

    /*
 * Deleting a tag
 */
//    public void deleteTag(Tag tag, boolean should_delete_all_tag_events) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // before deleting tag
//        // check if events under this tag should also be deleted
//        if (should_delete_all_tag_events) {
//            // get all events under this tag
//            List<Event> allTagEvents = getAllEventsByTag(tag.getName());
//
//            // delete all events
//            for (Event event : allTagEvents) {
//                // delete event
//                deleteEvent(event.getId());
//            }
//        }
//
//        // now delete the tag
//        db.delete(TABLE_TAG, KEY_ID + " = ?",
//                new String[] { String.valueOf(tag.getId()) });
//    }
    /*
     * Creating event_tag
     */
    public long createEventTag(long event_id, long tag_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EVENT_ID, event_id);
        values.put(KEY_TAG_ID, tag_id);

        long id = db.insert(TABLE_EVENT_TAG, null, values);

        return id;
    }

    /*
 * Removing a event tag
 */
    public int removeEventTag(long id, long tag_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_EVENT, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    /*
 * Updating a event tag
 */
    public int updateEventTag(long id, long tag_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TAG_ID, tag_id);

        // updating row
        return db.update(TABLE_EVENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
