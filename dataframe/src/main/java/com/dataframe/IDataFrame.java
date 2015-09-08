package com.dataframe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.datautils.DataFrameTypeException;

public interface IDataFrame {

	public abstract void dropColumn(int column);

	/**
	 * protected void dropColumns(int[] columns)
	 * 
	 * Removes many columns at once.
	 * 
	 * @param columns
	 *            an int[] array of columns to be removed.
	 * 
	 */
	public abstract void dropColumns(int[] columns);

	/**
	 * Drops one or more columns and then rebuilds the index.
	 * @param columns
	 */
	public abstract void dropColumns(Set<Integer> columns);

	/**
	 * public void dropRow(int row)
	 * 
	 * Function used in order to remove a single row.
	 *
	 * 
	 */
	public abstract void dropRow(int row);

	public abstract void dropRows(ArrayList<Integer> rows);

	/**
	 * public void dropRows(int[] rows)
	 * 
	 * Function used in order to remove many rows at once. Rebuilds the index
	 * after the rows are removed.
	 * 
	 * @param rows
	 *            an int[] array with the indices of the rows.
	 * 
	 */
	public abstract void dropRows(int[] rows);

	public abstract void dropRows(Set<Integer> rows);

	/**
	 * public ArrayList<DataPoint> getColumn(int column)
	 * 
	 * Returns an arraylist containing all the rows for a particular column.
	 *
	 * @param column
	 *            the key (index) of the column
	 * @throws DataFrameIndexException
	 */
	public abstract ArrayList<DataPoint> getColumn(int column)
			throws DataFrameIndexException;

	/**
	 * public ArrayList<DataPoint> getColumn(string columnName)
	 * 
	 * Finds a column with the corresponding name and returns it. Note, that if
	 * two columns share the same name, then it will return a column randomly,
	 * among all the columns that share the same name.
	 *
	 * @param columnName
	 *            the name of the column
	 * @throws DataFrameIndexException
	 */
	public abstract ArrayList<DataPoint> getColumn(String columnName)
			throws DataFrameIndexException;
	
	public abstract ArrayList<DataPoint> getColumns(int[] columns)
			throws DataFrameIndexException;
	
	public abstract ArrayList<DataPoint> getColumns(int columnstart,int columnend)
			throws DataFrameIndexException;

	/**
	 * public String getColumnNames()
	 * 
	 * Gets the column names.
	 */
	public abstract String getColumnNames();

	public abstract DataPointType getColumnType(int column);

	/**
	 * public int getColumnsNumber()
	 * 
	 * Returns the total number of columns.
	 */
	public abstract int getNumberColumns();

	/**
	 * 
	 * Gets the variance for a single column.
	 * 
	 * @param column
	 * @return
	 * @throws DataFrameTypeException 
	 */
	public abstract Double getVariance(int column) throws DataFrameTypeException;

	/**
	 * public int getRowsNumber()
	 * 
	 * Returns the total number of rows.
	 */
	public abstract Double getNumberRows();

	public abstract Double getNumMissingValuesForColumn(int column)
			throws DataFrameIndexException;

	/**
	 * public int getNumMissingValuesForRow(int row)
	 * 
	 * Gets the number of missing values for a particular row.
	 * 
	 * @throws DataFrameIndexException
	 * 
	 */
	public abstract Double getNumMissingValuesForRow(int row);

	/**
	 * public int getNumMissingValues()
	 * 
	 * returns the total number of cells with a missing value
	 * 
	 */
	public abstract Double getNumMissingValues();

	/**
	 * public ArrayList<DataPoint> getRow(int row)
	 * 
	 * Returns an arraylist containing all the elements of a row
	 *
	 * @param column
	 *            the key (index) of the row.
	 * @throws DataFrameIndexException
	 */
	public abstract ArrayList<DataPoint> getRow(int row)
			throws DataFrameIndexException;

	/**
	 * 
	 * Method for getting the values of many rows at once. Returns a HashMap
	 * where the key is the row index.
	 * 
	 * @param rows
	 * @return
	 * @throws DataFrameIndexException
	 */
	public abstract HashMap<Integer, ArrayList<DataPoint>> getRows(int[] rows)
			throws DataFrameIndexException;
	
	
	
	/**
	 * Gets the rows from index 'start' to index 'end'
	 * @param start start index
	 * @param end end index
	 * @return A hashmap of the form <row number, datapoints>
	 * 
	 * @throws DataFrameIndexException
	 */
	public HashMap<Integer,ArrayList<DataPoint>> getRows(int start, int end) throws DataFrameIndexException;


	/**
	 * returns the number of columns that follow 'type'
	 * @param type
	 * @return
	 */
	public abstract int numColumnsType(DataPointType type);

	/**
	 * public void setColumn(int columnIndex, ArrayList<DataPoint> column)
	 * 
	 * Sets the column of the selected columnIndex to the particular column.
	 * 
	 * @param columnIndex
	 *            the index (column) of the column.
	 * @param the
	 *            column(contents) that will replace the old column.
	 * 
	 */
	public abstract void setColumn(int columnIndex, ArrayList<DataPoint> column);

	public abstract void setColumns(HashMap<Integer, ArrayList<DataPoint>> newdata);
	
	/**
	 * public void setRow(int rowIndex,ArrayList<DataPoint> row)
	 * 
	 * Sets the row of the selected rowIndex to the particular row.
	 * 
	 * @param rowIndex
	 *            the index (row) of the row.
	 * @param the
	 *            row(contents) that will replace the old row.
	 * 
	 */
	public abstract void setRow(int rowIndex, ArrayList<DataPoint> row);

	/**
	 * This method is used in order to set the change the values of many rows at
	 * once.
	 * 
	 * @param imputed
	 *            a HashMap where the key is the row index and the value is an
	 *            ArrayList<DataPoint> with the new row.
	 */
	public abstract void setRows(HashMap<Integer, ArrayList<DataPoint>> newdata);

	public abstract double getMean(int column) throws DataFrameTypeException;

}