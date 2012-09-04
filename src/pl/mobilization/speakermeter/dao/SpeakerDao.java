package pl.mobilization.speakermeter.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.Property;

import pl.mobilization.speakermeter.dao.Speaker;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table SPEAKER.
*/
public class SpeakerDao extends AbstractDao<Speaker, Long> {

    public static final String TABLENAME = "SPEAKER";

    /**
     * Properties of entity Speaker.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Presentation = new Property(2, String.class, "presentation", false, "PRESENTATION");
        public final static Property Votes_up = new Property(3, int.class, "votes_up", false, "VOTES_UP");
        public final static Property Votes_down = new Property(4, int.class, "votes_down", false, "VOTES_DOWN");
        public final static Property Venue = new Property(5, String.class, "venue", false, "VENUE");
        public final static Property Start_time = new Property(6, java.util.Date.class, "start_time", false, "START_TIME");
        public final static Property End_time = new Property(7, java.util.Date.class, "end_time", false, "END_TIME");
    };


    public SpeakerDao(DaoConfig config) {
        super(config);
    }
    
    public SpeakerDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'SPEAKER' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'NAME' TEXT NOT NULL ," + // 1: name
                "'PRESENTATION' TEXT NOT NULL ," + // 2: presentation
                "'VOTES_UP' INTEGER NOT NULL ," + // 3: votes_up
                "'VOTES_DOWN' INTEGER NOT NULL ," + // 4: votes_down
                "'VENUE' TEXT NOT NULL ," + // 5: venue
                "'START_TIME' INTEGER NOT NULL ," + // 6: start_time
                "'END_TIME' INTEGER NOT NULL );"); // 7: end_time
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'SPEAKER'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Speaker entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindString(3, entity.getPresentation());
        stmt.bindLong(4, entity.getVotes_up());
        stmt.bindLong(5, entity.getVotes_down());
        stmt.bindString(6, entity.getVenue());
        stmt.bindLong(7, entity.getStart_time().getTime());
        stmt.bindLong(8, entity.getEnd_time().getTime());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Speaker readEntity(Cursor cursor, int offset) {
        Speaker entity = new Speaker( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.getString(offset + 2), // presentation
            cursor.getInt(offset + 3), // votes_up
            cursor.getInt(offset + 4), // votes_down
            cursor.getString(offset + 5), // venue
            new java.util.Date(cursor.getLong(offset + 6)), // start_time
            new java.util.Date(cursor.getLong(offset + 7)) // end_time
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Speaker entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setPresentation(cursor.getString(offset + 2));
        entity.setVotes_up(cursor.getInt(offset + 3));
        entity.setVotes_down(cursor.getInt(offset + 4));
        entity.setVenue(cursor.getString(offset + 5));
        entity.setStart_time(new java.util.Date(cursor.getLong(offset + 6)));
        entity.setEnd_time(new java.util.Date(cursor.getLong(offset + 7)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Speaker entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Speaker entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
