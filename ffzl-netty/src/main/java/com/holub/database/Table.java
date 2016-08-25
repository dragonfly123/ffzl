package com.holub.database;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by longfei on 16-7-15.
 * a table is a database-like table that provided support for queries
 */
public interface Table  extends Serializable,Cloneable{
    /**
     * return a shallow copy of the table (the contents are not copied)
     */
    Object clone() throws CloneNotSupportedException;

    /**
     * return then table name that was passedd to che constructor (or read from
     * the disk in the case of a table that was loaded from the disk.) this is a
     * "getter, but it's a harmless one since it's just giving back a piece of information
     * that it was given
     */
    String name();

    /**
     * rename the table to the indiated name. this method can alse be the used for naming the
     * anonymous table that's return from (@link #select select(...)) or one of its variants
     */
    void rename(String name);

    /**
     * return true if this table has changed since it was created.This status isn't entirely
     * accurate since it's possible for a user to change some object that's in the table without
     * telling the table about the change, so a certain amount of user discipline is required.
     * Returns ture if you modify the table  using a Table method(such as update,insert,etc)
     */
    boolean isDirty();

    /**
     * Insert new values into the table corresponding to the specified columns. for example, the
     * value at <code>vaues[i]</code> is put into the column specified in <code>columnNames[i]</code>.
     * Columns that are not specified are initialiszed to <code>null</code>.
     * @return the number of rows affected by the operation.
     * @throws IndexOutOfBoundsException one of the requested columns doesn't exist in either table
     */
    int insert(String[] columnNames,Object[] values);

    /**
     * a convnience overload of {@link #insert(String[], Object[])}
     */
    int insert(Collection columnNames,Collection values);

    /**
     * In this version of insert,values must have as manay elements as there are columns,and the values
     * must be in the order specified the table was created.
     * @return the number of rows affected by the operation
     */
    int insert(Object[] values);

    /**
     * A convenience overload of {@link #insert(Object[])}
     */
    int insert(Collection values);

    /**
     * Update celles in the table. the {@link Selector} object serves as a visitor whose <code>includeInSelect(...)</code>
     * method is called for each row in the table. The return value is ignored, but the Selector can modify cells as it
     * examines them.It's your responsibility not to modify primary-key and other constant fields
     * @return the number of rows affected by the operation
     */
    int update(Selector where);

    /**
     * Delete from the table all rows approved by the Selector.
     * @return the number of rows affected by the operation
     */
    int delete(Selector where);

    /**
     * begin a transaction
     */
    void begin();

    /**
     * Commit a transaction
     * @throws IllegalStateException if no {@link #begin()}was issued.
     * @param all if false, commit onlu the innermost transaction,otherwise commit all transacion at all levels
     * @see #THIS_LEVEL
     * @see #ALL
     */
    void commit(boolean all) throws IllegalStateException;

    /**
     * Roll back a transaction
     * @param all
     * @throws IllegalStateException
     */
    void rollback(boolean all) throws IllegalStateException;

    /**
     * a convenience constant that makes calls to {@link #commit(boolean)} and {@link #rollback(boolean)}
     * more readable when used as an argument to those methods.
     */
    boolean THIS_LEVEL = false;

    boolean ALL = true;

    Table select(Selector where,String[] requestedColumns,Table[] other);

    /**
     * A more efficient version of <code>select</code>
     * @param where
     * @param requestedColumns
     * @return
     */
    Table select(Selector where,String[] requestedColumns);

    Table select(Selector where);

    Table select(Selector where,Collection requestedColumns,Collection other);

    Table select(Selector where,Collection requestedColumns);

    Cursor rows();

    void export(Exporter importer) throws IOException;

    /***************************************************************
     * used for exporting tables in various formats. Note that I can add methods to this interface if the representation
     * requires it without impacting the Table's clients at all
     */
    public interface Exporter{
        void startTable() throws IOException;
        void storeMetadata(String tableName, int width, int height, Iterator columnNames) throws IOException;
        void storeRow(Iterator data) throws IOException;
        void endTable() throws IOException;
    }

    /**************************************************************
     * used for importing tables in various formats.Methods are called in the following order
     * <ul>
     *     <li><code>start()</code></li>
     *     <li><code>loadTableName()</code></li>
     *     <li><code>loadWidth()</code></li>
     *     <li><code>loadColumnNames()</code></li>
     *     <li><code>loadRow()</code>mutiple times</li>
     *     <li><code>done()</li>
     * </ul>
     */
    public interface Importer{
        void startTable() throws IOException;
        String loadTableName() throws IOException;
        int loadWidth() throws IOException;
        Iterator loadColumnNames() throws IOException;
        Iterator loadRow() throws IOException;
        void endTable() throws IOException;
    }

}
